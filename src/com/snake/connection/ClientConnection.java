package com.snake.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection extends Thread{  
	Socket sock;
	int UUID;
	String username;
	boolean alive=true;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
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
	@Override
	public void run() {
		try {
			ois = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (alive) {
			try {
				recv((Message)ois.readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void send(Message msg) {
		try {
			System.out.println("Sent message of type "+msg.type.name());
			oos.writeObject(msg);
			oos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	public void recv(Message msg) {
		System.out.println("Client received message of type: "+msg.type.name()+" from "+msg.sender);
	}
	
	public static void main(String[] args) {  
	   ClientConnection conn = new ClientConnection("localhost",6666,"Zack",57);
	   conn.start();
	   conn.sendJoin();
	}  
}  
