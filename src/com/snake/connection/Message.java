package com.snake.connection;

import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int sender;
	MessageType type;
	Object data;
}
