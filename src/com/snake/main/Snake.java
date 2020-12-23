package com.snake.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake {
	public int score;
	public double snakeWidth;
	public Vec2 pos;
	public Vec2 vel;
	public List<Vec2> tailPositions = new ArrayList<Vec2>();
	
	public void createSnake(Vec2 start, Vec2 initialVel) {
		pos = start;
		vel = initialVel;
	}
	
	public void Test(String s) {
		System.out.println("Snake Class: "+s);;
	
	}
	public void paintComponent(Graphics g) {
		
	}
}
