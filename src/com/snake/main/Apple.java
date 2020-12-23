package com.snake.main;

import java.awt.Graphics;

public class Apple {
	public static void GenerateAppleLocation() { //This method will need to be updated later when we add in the snake's tail
		
	}
	public static double GenerateRandomNum(double max, double min) {
		return Math.random() * (max - min + 1) + min;
	}
	public static void Test(String S) {
		System.out.println("Apple Class: "+S);
	}
	public void paintComponent(Graphics g) {
		
	}
}
