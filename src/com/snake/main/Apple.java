package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.imageio.ImageIO;

public class Apple extends Entity {
   public Vec2 pos = new Vec2(0,0);
   private boolean eaten=false;
   public static transient final int APPLE_MEAN_WIDTH=50;
   public static transient final int APPLE_MEAN_POINTS=7;
   public static transient Image appleImage = null;
   public int appleWidth;
   public int numPoints;
   public Apple(Collection<Snake> collection) {
	   generateAppleLocation(collection);
	   double scalar = Math.random()+Math.random(); // 2E[x]=1
	   scalar = Math.max(scalar, 0.3);
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
		if (appleImage==null) {
			   Toolkit t=Toolkit.getDefaultToolkit();  
			   appleImage=t.getImage("apple.png"); 
		   }
		//g.fillOval((int)pos.x -appleWidth/2, (int)pos.y-appleWidth/2, (int)appleWidth, (int)appleWidth);
		 
        //g.drawImage(i, 120,100,this);  
		g.drawImage(appleImage, (int)pos.x -appleWidth/2, (int)pos.y-appleWidth/2, this.appleWidth,this.appleWidth,new Color(0,0,0),null);
	}
}
