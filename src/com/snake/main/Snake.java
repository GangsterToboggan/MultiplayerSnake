package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake extends Entity{
	public int score=100;
	public double snakeWidth=10;
	public double speed=50;
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
		g.setColor(Color.blue);
		for (Vec2 vec : tailPositions) {
			
			g.fillOval((int)vec.x, (int)vec.y, (int)snakeWidth, (int)snakeWidth);
		}
	}
	public void update(double deltaMs) {
		vel.norm();
		List<Vec2> newTail = new ArrayList<>();
		
		
		pos.add(vel.clone().norm().scale(speed).scale(deltaMs/1000.0));
		newTail.add(pos.clone());
		while(tailPositions.size()+1<score) {
			tailPositions.add(new Vec2(0,0));
		}
		for (int i =0; i<tailPositions.size()-1; i++) {
			newTail.add(tailPositions.get(i));
		}
		tailPositions=newTail;
		
	}
	
	public void setPos(Vec2 pos) {
		this.pos=pos;
	}
	public void setVel(Vec2 vel) {
		this.vel=vel;
	}
}
