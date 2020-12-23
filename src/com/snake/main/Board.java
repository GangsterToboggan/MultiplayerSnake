package com.snake.main;

public class Board {
	
	public int boardWidth = 50;
	public int boardHeight = 50;
	
	Snake Snake = new Snake();
	Main Main = new Main();
	Apple Apple = new Apple();

	public static void Test(String S) {
		System.out.println("Board Class: "+S);
		
	}
}
