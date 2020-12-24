package com.snake.main;


import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main  {
	public static final int SCREEN_WIDTH = 1200;
	public static final int SCREEN_HEIGHT = 800;
	public static void main(String[] args) {
		System.out.println("Hello");
		System.out.println("Hello World");
		System.out.println("ZAck mega gay");

		Snake snake = new Snake(new Vec2(600,400),new Vec2(20,5));

		SnakeCanvas canvas = new SnakeCanvas();
		JFrame frame = new JFrame("Snake");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.add(canvas);
        frame.setVisible(true);
        
        KeyboardManager keyManager = new KeyboardManager(snake);
        frame.addKeyListener(keyManager);
        
       // canvas.addSnake(snake);
       // Apple apple = new Apple(canvas.snakes);
       // canvas.addApple(apple);
        
        Thread t = new Thread() {
        	public void run() {
        		while (true) {
        		
        		 frame.invalidate();
        	     frame.validate();
        	     frame.repaint();
        	  //   canvas.update(20);
        	     try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		}
        	}
        };
        t.start();
        
       

	}
}
