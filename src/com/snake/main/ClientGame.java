package com.snake.main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.snake.connection.ClientConnection;

public class ClientGame {
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 800;
	public static void main(String[] args) {

		ClientConnection conn = new ClientConnection("localhost",6666,"Zack",59);
		
		
		
		SnakeCanvas canvas = new SnakeCanvas();
		JFrame frame = new JFrame("ZnaKe");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.add(canvas);
        frame.setVisible(true);
        
        KeyboardManager keyManager = new KeyboardManager(conn);
        frame.addKeyListener(keyManager);
        
        conn.setCanvas(canvas);
        
        conn.start();
        conn.sendJoin();
		
        
        Thread t = new Thread() {
        	public void run() {
        		while (true) {
        		 frame.invalidate();
        	     frame.validate();
        	     frame.repaint();
        	     try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		}
        	}
        };
        t.start();
        
       

	}
	
}
