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
	public Vec2 add(Vec2 o) {
		x+=o.x;
		y+=o.y;
		return this;
	}
	public Vec2 scale(double s) {
		x*=s;
		y*=s;
		return this;
	}
	public double magnitude() {
		return Math.sqrt(x*x+y*y);
	}
	public Vec2 norm() {
		scale(1.0/magnitude());
		return this;
	}
	public Vec2 clone() {
		return new Vec2(x,y);
	}
	
}
