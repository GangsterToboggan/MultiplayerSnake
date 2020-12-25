package com.snake.main;

import java.util.Collection;

public class Utils {
	public static boolean isSnakeThere(Collection<Snake> snakes, Vec2 pos, int width) {
		for (Snake snake : snakes) {
			boolean a = snake.isPosOccupied(pos, width);
			if (a==true) {
				return true;
			}
		}
		return false;
		
	}
	public static double generateRandomNum(double max, double min) {
		return Math.random() * (max - min + 1) + min;
	}
}
