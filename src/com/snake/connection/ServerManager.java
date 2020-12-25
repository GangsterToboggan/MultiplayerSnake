package com.snake.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.snake.main.Apple;
import com.snake.main.Entity;
import com.snake.main.Snake;
import com.snake.main.Vec2;

public class ServerManager {  
	
	private HashMap<Integer, Snake> snakeMap = new HashMap<>();
	private List<Apple> apples = new ArrayList<>();
	private List<Snake> snakes = new ArrayList<>();
	private Semaphore dataSem = new Semaphore(1);
	
	private List<Connection> connections = new ArrayList<Connection>();
	
	public ServerManager(int port) {
		ServerManager parent = this;
		Thread t = new Thread() {
			public void run() {
				while(true) {
					try{  
						ServerSocket ss=new ServerSocket(port);  
						ss.setReuseAddress(true);
						while (true) {
							Connection conn = new Connection(ss.accept(),parent);
							connections.add(conn);
							conn.start();
						}
						
					}catch(Exception e){
						e.printStackTrace();
					}  
				}
			}
		};
		t.start();
		
	}
	public void broadcast(Message msg) {
		//System.out.println("Broadcasting message of type: "+msg.type.name());
		for (Connection conn : connections) {
			if (conn.alive) {
				conn.send(msg);
			}
		}
	}
	public void broadcastGameState() {
		GameState state = new GameState();
		state.entities= new ArrayList<>();
		state.entities.addAll(apples);
		state.entities.addAll(snakeMap.values());
		
		Message msg = new Message();
		msg.sender=0;
		msg.type=MessageType.GAME_UPDATE;
		msg.data=state;
		broadcast(msg);
	}
	
	public void startGame() {
		Thread t = new Thread() {
			public void run() {
				int counter = 0 ;
				while(true) {
					try {
						dataSem.acquire();
						for (Snake s : snakeMap.values()) {
							s.update(20, snakeMap.values(),apples);
						}
						List<Apple> newApples = new ArrayList<>();
						for (Apple a : apples) {
							if (!a.isEaten()) {
								newApples.add(a);
							}
						}
						apples = newApples;
						if (apples.size() < snakeMap.size()*2 && counter++%200==0) {
							apples.add(new Apple(snakeMap.values()));
						}
						//System.out.println("Num Snakes : "+snakeMap.values().size());
						broadcastGameState();
						dataSem.release();
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}
	
	public void addSnake(int userId, String username) {
		try {
			dataSem.acquire();
			snakeMap.put(userId, new Snake(username, new Vec2(300,300), new Vec2(5,5), snakes));
			dataSem.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void delSnake(int userId) {
		try {
			dataSem.acquire();
			snakeMap.remove(userId);
			dataSem.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void registerKeyPress(int userId, int keycode) {
		try {
			dataSem.acquire();
			Snake snake = snakeMap.get(userId);
			double scalar = 3.0;
			switch (keycode){
			case 87: // W
				snake.vel.add(new Vec2(0,-1).scale(scalar));
				break;
			case 65: // A
				snake.vel.add(new Vec2(-1,0).scale(scalar));
				break;
			case 83: // S
				snake.vel.add(new Vec2(0,1).scale(scalar));
				break;
			case 68: // D
				snake.vel.add(new Vec2(1,0).scale(scalar));
				break;
			}
			dataSem.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
}  

class Connection extends Thread{
	Socket sock;
	boolean alive=true;
	int remoteID;
	ServerManager serverManager;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	Semaphore sendSem = new Semaphore(1);
	Connection(Socket s, ServerManager sm) throws IOException{
		sock=s;
		serverManager=sm;
		ois = new ObjectInputStream(sock.getInputStream());
		oos = new ObjectOutputStream(sock.getOutputStream());
	}
	public void send(Message msg) {
		try {
			sendSem.acquire();
			oos.reset();
			oos.writeObject(msg);
			oos.flush();
			sendSem.release();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			while (sock.isConnected()) {
				
				Message message = (Message) ois.readObject();
				//System.out.println("Server received message of type: "+message.type.name()+" from "+message.sender);
				switch (message.type) {
				case USER_JOIN:
					remoteID=message.sender;
					serverManager.addSnake(remoteID, (String)message.data);
					Message sendmsg = new Message();
					sendmsg.sender=0;
					sendmsg.type = MessageType.USER_JOIN_ACK;
					sendmsg.data=0;
					send(sendmsg);
					break;
				case KEYBOARD_EVENT:
					serverManager.registerKeyPress(message.sender, (int)message.data);
					break;
					
				
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connection closed with "+remoteID);
		serverManager.delSnake(remoteID);
		System.out.println("Snake removed");
		
	}
}