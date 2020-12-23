package com.snake.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Snake {
	public Vec2 pos;
	public Vec2 vel;
	public List<Vec2> tailPositions = new ArrayList<Vec2>();
	
	public void Test(String s) {
		System.out.println("Snake Class: "+s);;
	
	}
	public void paintComponent(Graphics g) {
		
	}
	
}
