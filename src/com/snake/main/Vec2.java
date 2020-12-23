package com.snake.main;

public class Vec2 {
	public double x,y;
	public Vec2(double x1,double y1) {
		this.x=x1;
		this.y=y1;
	}
	public double dot(Vec2 o) {
		return x*o.x+y*o.y;
	}
	public double distTo(Vec2 o) {
		return Math.sqrt((x-o.x)*(x-o.x)  + (y-o.y)*(y-o.y));
	}
	public void add(Vec2 o) {
		x+=o.x;
		y+=o.y;
	}
	public void scale(double s) {
		x*=s;
		y*=s;
	}
}
