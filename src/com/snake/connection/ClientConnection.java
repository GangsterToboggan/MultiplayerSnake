package com.snake.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import com.snake.main.*;

public class ClientConnection extends Thread{  
	Socket sock;
	int UUID;
	String username;
	SnakeCanvas canvas;
	boolean alive=true;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Semaphore sendSem = new Semaphore(1);
	
	public ClientConnection(String ip, int port, String username, int UUID) {
		try{      
			Socket sock=new Socket(ip,port);  
			this.sock=sock;
			this.username=username;
			this.UUID=UUID;
			oos = new ObjectOutputStream(sock.getOutputStream());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void setCanvas(SnakeCanvas canvas) {
		this.canvas=canvas;
	}
	@Override
	public void run() {
		try {
			ois = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (sock.isConnected()) {
			try {
				recv((Message)ois.readObject());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		System.err.println("!!Connection to server closed!!");
	}
	public void send(Message msg) {
		try {
		//	System.out.println("Sent message of type "+msg.type.name());
			sendSem.acquire();
			oos.reset();
			oos.writeObject(msg);
			oos.flush();
			sendSem.release();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendKeyboardUpdate(int keycode) {
		Message msg = new Message();
		msg.sender=UUID;
		msg.type=MessageType.KEYBOARD_EVENT;
		msg.data=keycode;
		send(msg);
	}
	public void sendJoin() {
		Message msg = new Message();
		msg.sender=UUID;
		msg.type=MessageType.USER_JOIN;
		msg.data=username;
		send(msg);
	}
	public void sendGameUpdateAck() {
		Message msg = new Message();
		msg.sender=UUID;
		msg.type=MessageType.GAME_UPDATE_ACK;
		msg.data=0;
		send(msg);
	}
	public void recv(Message msg) {
		//System.out.println("Client received message of type: "+msg.type.name()+" from "+msg.sender);
		switch (msg.type) {
		case GAME_UPDATE:
			GameState state = (GameState) msg.data;
			canvas.setGameState(state);
			sendGameUpdateAck();
			break;
		
		}
	}
	  
}  
