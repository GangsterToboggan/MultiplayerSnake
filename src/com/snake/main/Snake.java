package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake {
	public int score;
	public double snakeWidth=10;
	public Vec2 pos;
	public Vec2 vel;
	public List<Vec2> tailPositions = new ArrayList<Vec2>();
	
	public  Snake(Vec2 start, Vec2 initialVel) {
		pos = start;
		vel = initialVel;
	}
	
	public void Test(String s) {
		System.out.println("Snake Class: "+s);;
	
	}
	public void paintComponent(Graphics g) {
		g.fillOval((int)pos.x, (int)pos.y, (int)snakeWidth, (int)snakeWidth);
	}
	
}
