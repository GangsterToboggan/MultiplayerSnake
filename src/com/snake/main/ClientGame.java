package com.snake.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.snake.connection.ClientConnection;

public class ClientGame {
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 800;
	public static final int CLIENT_MSPT = 20;
	public static void main(String[] args) {
		run("localhost",6666,"Zack");
	}
	public static void run(String ip, int port, String username) {
		Random rand = new Random();
		rand.setSeed(System.nanoTime());
		ClientConnection conn = new ClientConnection(ip,port,username,rand.nextInt()%100000);
		
		
		SnakeCanvas canvas = new SnakeCanvas();
		JFrame frame = new JFrame("ZnaKe");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT+35);
        frame.add(canvas);
        frame.setVisible(true);
        frame.setResizable(false);
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
        	     canvas.update(CLIENT_MSPT);
        	     try {
					Thread.sleep(CLIENT_MSPT);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		}
        	}
        };
        t.start();
        
       

	}
	
}
