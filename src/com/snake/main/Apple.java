package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.List;

public class Apple extends Entity {
   public Vec2 pos = new Vec2(0,0);
   public int appleWidth=30;
   public Apple(Collection<Snake> collection) {
	   generateAppleLocation(collection);
   }
	public void generateAppleLocation(Collection<Snake> snakes) { //This method will need to be updated later when we add in the snake's tail
		pos.x = generateRandomNum(1200, 0);
		pos.y = generateRandomNum(800,0);
		if (isSnakeThere (snakes, pos) == true) {
			generateAppleLocation(snakes);
		}
	}
	private boolean isSnakeThere(Collection<Snake> snakes, Vec2 pos) {
		for (Snake snake : snakes) {
			boolean a = isPosOccupied(snake, pos);
			if (a==true) {
				return true;
			}
		}
		return false;
		
	}
	
	
	private boolean isPosOccupied(Snake s, Vec2 pos) {
		boolean a = false;
		for (Vec2 tailpos : s.tailPositions) {
			a=pos.distTo(tailpos) < appleWidth;
			if (a== true) {
				return true;
			}
		}
		return false;
		
	}
	public double generateRandomNum(double max, double min) {
		return Math.random() * (max - min + 1) + min;
	}
	public static void Test(String S) {
		System.out.println("Apple Class: "+S);
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillOval((int)pos.x, (int)pos.y, (int)appleWidth/2, (int)appleWidth/2);
	}
}
