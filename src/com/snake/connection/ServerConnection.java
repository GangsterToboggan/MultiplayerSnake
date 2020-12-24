package com.snake.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerConnection {  
	
	public static void main(String[] args){  
		ServerConnection sc = new ServerConnection();
	} 
	
	
	private List<Connection> connections = new ArrayList<Connection>();
	
	public ServerConnection() {
		while(true) {
		try{  
			ServerSocket ss=new ServerSocket(6666);  
			ss.setReuseAddress(true);
			while (true) {
				Connection conn = new Connection(ss.accept());
				connections.add(conn);
				conn.start();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}  
		}
	}
	public void broadcast(Message msg) {
		for (Connection conn : connections) {
			if (conn.alive) {
				try {
					conn.oos.writeObject(msg);
					conn.oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}  

class Connection extends Thread{
	Socket sock;
	boolean alive=true;
	int remoteID;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Connection(Socket s) throws IOException{
		sock=s;
		ois = new ObjectInputStream(sock.getInputStream());
		oos = new ObjectOutputStream(sock.getOutputStream());
	}
	public void send(Message msg) {
		try {
			oos.writeObject(msg);
			oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			while (sock.isConnected()) {
				
				Message message = (Message) ois.readObject();
				System.out.println("Server received message of type: "+message.type.name()+" from "+message.sender);
				switch (message.type) {
				case USER_JOIN:
					remoteID=message.sender;
					Message sendmsg = new Message();
					sendmsg.sender=0;
					sendmsg.type = MessageType.USER_JOIN_ACK;
					sendmsg.data=0;
					send(sendmsg);
					break;
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connection closed with "+remoteID);
		
	}
}