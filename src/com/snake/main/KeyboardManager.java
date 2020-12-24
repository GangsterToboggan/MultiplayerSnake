package com.snake.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.snake.connection.ClientConnection;

public class KeyboardManager implements KeyListener{
	ClientConnection conn;
	public KeyboardManager(ClientConnection s) {
		this.conn=s;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		conn.sendKeyboardUpdate(code);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
