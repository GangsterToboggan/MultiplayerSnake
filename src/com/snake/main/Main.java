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
		Apple Apple = new Apple();
		Snake snake = new Snake(new Vec2(600,400),new Vec2(5,5));
		Apple.Test("Nominal");
		SnakeCanvas canvas = new SnakeCanvas();
		JFrame frame = new JFrame("Snake");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.add(canvas);
        frame.setVisible(true);
        
        
        
        canvas.addSnake(snake);

	}
}
