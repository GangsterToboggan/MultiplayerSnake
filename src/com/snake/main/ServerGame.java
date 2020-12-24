package com.snake.main;

import com.snake.connection.ServerManager;

public class ServerGame {
	public static void main(String[] args) {
		ServerManager sc = new ServerManager(6666);
		sc.startGame();
	}
}
