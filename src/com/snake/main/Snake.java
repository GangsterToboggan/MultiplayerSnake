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
	
	public  Snake(Vec2 start, Vec2 initialVel) {
		pos = start;
		vel = initialVel;
	}
	
	public void Test(String s) {
		System.out.println("Snake Class: "+s);;
	
	}
	public void paintComponent(Graphics g) {
		g.fillOval((int)pos.x, (int)pos.y, (int)snakeWidth, (int)snakeWidth);
		for (int i = 0; i<tailPositions.size(); i ++) {
			Vec2 tailPos = tailPositions.get(i);
		}
		
	}
	public void update(double deltaTime, double tickRate) {
		pos.x = pos.x+deltaTime * vel.x;
		pos.y = pos.y+deltaTime * vel.y;		
	}
	
}
