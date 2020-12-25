package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.List;

public class Apple extends Entity {
   public Vec2 pos = new Vec2(0,0);
   private boolean eaten=false;
   public static transient final int APPLE_MEAN_WIDTH=30;
   public static transient final int APPLE_MEAN_POINTS=10;
   public int appleWidth;
   public int numPoints;
   public Apple(Collection<Snake> collection) {
	   generateAppleLocation(collection);
	   double scalar = Math.random()+Math.random(); // 2E[x]=1
	   this.appleWidth=(int)(APPLE_MEAN_WIDTH*scalar);
	   this.numPoints=(int)(APPLE_MEAN_POINTS*scalar);
   }
	public void generateAppleLocation(Collection<Snake> snakes) { //This method will need to be updated later when we add in the snake's tail
		pos.x = Utils.generateRandomNum(ClientGame.SCREEN_WIDTH-100, 100);
		pos.y = Utils.generateRandomNum(ClientGame.SCREEN_HEIGHT-100,100);
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
