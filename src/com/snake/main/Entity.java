package com.snake.main;

import java.awt.Graphics;

public abstract class Entity {
	public Vec2 pos;
	public abstract void paintComponent(Graphics g);
}
