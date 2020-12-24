package com.snake.connection;

import java.io.Serializable;

public enum MessageType implements Serializable{
	KEYBOARD_EVENT,
	USER_JOIN,
	USER_JOIN_ACK,
	USER_UPDATE_ACK,
	GAME_UPDATE
}
