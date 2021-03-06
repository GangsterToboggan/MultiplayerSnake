package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Snake extends Entity implements Serializable{
	public static transient final int RESET_SCORE = 5;
	public static transient final int SNAKE_WIDTH = 10;
	public static transient final int SNAKE_SPEED = 70;
	public static transient final int TIME_BETWEEN_TAIL=100;
	public int score;
	public int length=1;
	public double snakeWidth;
	public double speed;
	public double superSpeed=0;
	public Vec2 pos;
	public Vec2 vel;
	public String username;
	private double elapsedTimeSpecial;
	public List<Vec2> tailPositions = new ArrayList<Vec2>();
	
	
	public  Snake(String username, Collection<Snake> snakes) {
		this.username=username;
		
		this.resetSnake(snakes);
		
	}
	/*public Color colorGenerator() {
		Color headColor = new Color(
				(int)Utils.generateRandomNum(220, 175),
				(int)Utils.generateRandomNum(220, 175),
				(int)Utils.generateRandomNum(220, 175));
		
		
		return headColor;
	} */

	
	private Color rainbowMaker(int location) {
		double rSin = 255.0*Math.sin(location/30.0+Math.PI/2.0);
		double gSin = 255.0*Math.sin(location/30.0);
		double bSin = 255.0*Math.sin(location/30.0+Math.PI);
		if (rSin <= 0) {
			rSin=0;
		}if (gSin <= 0) {
			gSin=0;
		}if (bSin <= 0) {
			bSin=0;
		}
		Color a = new Color((int)rSin,(int)gSin,(int)bSin);
		return a;
	}
	
	
	public void paintComponent(Graphics g) {
		g.setColor(rainbowMaker(0));
		g.fillOval((int)pos.x -(int)snakeWidth/2, (int)pos.y-(int)snakeWidth/2, (int)snakeWidth, (int)snakeWidth);
		for (int i =tailPositions.size()-1; i>=0; i--) {
/*
			double colorMultiple  = ((Math.cos(i/30.0)+1.0)/2.0);
			g.setColor(new Color(
					(int)(colorMultiple*headColor.getRed()),
					(int)(colorMultiple*headColor.getGreen()),
					(int)(colorMultiple*headColor.getBlue())));
*/
			g.setColor(rainbowMaker(i));
			Vec2 vec = tailPositions.get(i);
			g.fillOval((int)vec.x - (int)snakeWidth/2, (int)vec.y - (int)snakeWidth/2, (int)snakeWidth, (int)snakeWidth);
			g.setColor(Color.BLUE);
			g.drawString(username+" ("+this.score+")", (int)pos.x, (int)pos.y+30);
		}
	}
	
	public boolean detectEdgeContact(Vec2 pos) {
		if (pos.x <= 0 || pos.x>= ClientGame.SCREEN_WIDTH || pos.y <= 0 || pos.y >= ClientGame.SCREEN_HEIGHT){
			return true;
		}
		return false;
	}
	
	public void update(double deltaMs, Collection<Snake> snakes, Collection<Apple> apples) {
		vel.norm();
		elapsedTimeSpecial+=deltaMs;
		
		//sqrt(x)*ln(x+1)
		this.length=(int)Math.ceil(Math.sqrt(this.score)*Math.log(this.score+1));
		
		List<Vec2> newTail = new ArrayList<>();
		
		pos.add(vel.clone().norm().scale(speed).scale(deltaMs/1000.0));
		while (elapsedTimeSpecial>TIME_BETWEEN_TAIL) {
			elapsedTimeSpecial-=TIME_BETWEEN_TAIL;
			newTail.add(pos.clone());
			if(tailPositions.size()+1<length) {
				tailPositions.add(pos);
			}
			for (int i =0; i<tailPositions.size()-1 && i<length+1; i++) {
				newTail.add(tailPositions.get(i));
			}
			tailPositions=newTail;
			
		}
		superSpeed/=1.05;
		// c/(1+e^((x-c)/c))
		this.speed=this.SNAKE_SPEED+40.0/(1.0+Math.exp((this.score-20)/20.0))+superSpeed;
		this.snakeWidth=this.SNAKE_WIDTH+Math.log(this.score+2)*2.1-0.69;
		
		
		for (Snake other : snakes) {
			if (other.isPosOccupied(pos, other.snakeWidth/2.0) && !other.equals(this)) {
				other.score+=this.score*5/12;
				this.resetSnake(snakes);
			}
		}
		if (detectEdgeContact(pos)) {
			resetSnake(snakes);
		}
		
		for (Apple apple : apples) {
			if (this.isPosOccupied(apple.pos,apple.appleWidth/2.0)) {
				apple.setEaten(true);
				this.score+=apple.numPoints;
			}
		}
		for (int i=6; i<tailPositions.size(); i++) {
			Vec2 tailPos = tailPositions.get(i);
			if (pos.distTo(tailPos)<this.snakeWidth) {
				this.resetSnake(snakes);
			}
		}
		
	}
	
	public Vec2 generateRespawn(Collection<Snake> snakes) { 
		double x = generateRandomNum(1100,100);
		double y = generateRandomNum(700,100);
		Vec2 temporary = new Vec2(x,y);
		if (Utils.isSnakeThere(snakes, temporary, (int)snakeWidth) == true) {
			generateRespawn(snakes);
		}
		return temporary;
		
	}
	
	public Vec2 directionToCenter(Vec2 pos, int screenWidth, int screenHeight) {
		double x = pos.x - screenWidth/2;
		double y = pos.y - screenHeight/2;
		Vec2 a = new Vec2(x,y);
		return a.scale(-1);
	}
	
	
	public void resetSnake(Collection<Snake> snakes) {
		this.pos = generateRespawn(snakes);
		this.vel= directionToCenter(pos, ClientGame.SCREEN_WIDTH, ClientGame.SCREEN_HEIGHT);
		this.score= RESET_SCORE;
		this.tailPositions=new ArrayList<>();
		this.snakeWidth=SNAKE_WIDTH;
		this.speed=SNAKE_SPEED;
		if (this.username.equals("speedy")) {
			this.speed=SNAKE_SPEED*3;
		}
		if (this.username.equals("fatty")) {
			this.snakeWidth=SNAKE_WIDTH*3;
		}
	}
	public double generateRandomNum(double max, double min) {
		return Math.random() * (max - min + 1) + min;
	}
	public boolean isPosOccupied(Vec2 pos) {
		return this.isPosOccupied(pos,0);
	}
	public boolean isPosOccupied(Vec2 pos,double additionalRadius) {
		for (Vec2 tailpos : tailPositions) {
			if (pos.distTo(tailpos) < this.snakeWidth/2.0+additionalRadius) {
				return true;
			}
		}
		return false;
	}
	
	public void setPos(Vec2 pos) {
		this.pos=pos;
	}
	public void setVel(Vec2 vel) {
		this.vel=vel;
	}
	
}
