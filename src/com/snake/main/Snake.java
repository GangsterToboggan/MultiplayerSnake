package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Snake extends Entity implements Serializable{
	public static transient final int RESET_SCORE = 10;
	public static transient final int SNAKE_WIDTH = 10;
	public static transient final int SNAKE_SPEED = 50;
	public static transient final int TIME_BETWEEN_TAIL=100;
	public int score;
	public double snakeWidth;
	public double speed;
	public Vec2 pos;
	public Vec2 vel;
	public String username;
	private double elapsedTimeSpecial;
	public List<Vec2> tailPositions = new ArrayList<Vec2>();
	
	public  Snake(String username, Vec2 start, Vec2 initialVel) {
		this.resetSnake();
		this.username=username;
		pos = start;
		vel = initialVel;
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval((int)pos.x, (int)pos.y, (int)snakeWidth, (int)snakeWidth);
		for (Vec2 vec : tailPositions) {
			g.fillOval((int)vec.x, (int)vec.y, (int)snakeWidth, (int)snakeWidth);
			g.drawString(username, (int)pos.x, (int)pos.y+50);
		}
	}
	public void update(double deltaMs, Collection<Snake> snakes, Collection<Apple> apples) {
		vel.norm();
		elapsedTimeSpecial+=deltaMs;
		
		List<Vec2> newTail = new ArrayList<>();
		
		
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
				resetSnake();
			}
		}
		
		for (Apple apple : apples) {
			if (this.isPosOccupied(apple.pos,apple.appleWidth)) {
				apple.setEaten(true);
				this.score+=apple.numPoints;
			}
		}
		
	}
	public void resetSnake() {
		this.pos=new Vec2(300,300);
		this.vel=new Vec2(5,5);
		this.score=RESET_SCORE;
		this.snakeWidth=SNAKE_WIDTH;
		this.speed=SNAKE_SPEED;
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
