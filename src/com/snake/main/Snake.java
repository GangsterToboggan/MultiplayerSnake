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
	public double snakeWidth;
	public double speed;
	public Vec2 pos;
	public Vec2 vel;
	public String username;
	private double elapsedTimeSpecial;
	public List<Vec2> tailPositions = new ArrayList<Vec2>();
	
	
	public  Snake(String username, Collection<Snake> snakes) {
		this.username=username;
		this.resetSnake(snakes);
		
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval((int)pos.x -(int)snakeWidth/2, (int)pos.y-(int)snakeWidth/2, (int)snakeWidth, (int)snakeWidth);
		for (Vec2 vec : tailPositions) {
			g.fillOval((int)vec.x - (int)snakeWidth/2, (int)vec.y - (int)snakeWidth/2, (int)snakeWidth, (int)snakeWidth);
			g.drawString(username, (int)pos.x, (int)pos.y+30);
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
		
		List<Vec2> newTail = new ArrayList<>();
		if (detectEdgeContact(pos) == true) {
			resetSnake(snakes);
		}
		
		pos.add(vel.clone().norm().scale(speed).scale(deltaMs/1000.0));
		while (elapsedTimeSpecial>TIME_BETWEEN_TAIL) {
			elapsedTimeSpecial-=TIME_BETWEEN_TAIL;
			newTail.add(pos.clone());
			if(tailPositions.size()+1<score) {
				tailPositions.add(pos);
				
			}
			for (int i =0; i<tailPositions.size()-1; i++) {
				newTail.add(tailPositions.get(i));
			}
			tailPositions=newTail;
			
		}
	
		
		for (Snake other : snakes) {
			if (other.isPosOccupied(pos, this.snakeWidth) && !other.equals(this)) {
				resetSnake(snakes);
			}
		}
		
		for (Apple apple : apples) {
			if (this.isPosOccupied(apple.pos,apple.appleWidth)) {
				apple.setEaten(true);
				this.score+=apple.numPoints;
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
