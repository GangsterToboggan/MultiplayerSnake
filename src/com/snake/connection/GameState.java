package com.snake.connection;

import java.io.Serializable;
import java.util.List;

import com.snake.main.Entity;

public class GameState implements Serializable{
	public List<Entity> entities;
}
