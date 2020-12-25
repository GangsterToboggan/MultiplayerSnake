package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.List;

public class Apple extends Entity {
   public Vec2 pos = new Vec2(0,0);
   private boolean eaten=false;
   public int appleWidth=30;
   public int numPoints = 10;
   public Apple(Collection<Snake> collection) {
	   generateAppleLocation(collection);
   }
	public void generateAppleLocation(Collection<Snake> snakes) { //This method will need to be updated later when we add in the snake's tail
		pos.x = Utils.generateRandomNum(1200, 0);
		pos.y = Utils.generateRandomNum(800,0);
		if (Utils.isSnakeThere(snakes, pos, appleWidth/2)) {
			generateAppleLocation(snakes);
		}
	}
	
	
	
	public boolean isEaten() {
		return eaten;
	}
	public void setEaten(boolean val) {
		this.eaten=val;
	}
	
	public static void Test(String S) {
		System.out.println("Apple Class: "+S);
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillOval((int)pos.x, (int)pos.y, (int)appleWidth, (int)appleWidth);
	}
}
