package com.snake.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener{
	Snake snake;
	public static final double scalar=3;
	public KeyboardManager(Snake s) {
		this.snake=s;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code){
		case 87: // W
			snake.vel.add(new Vec2(0,-1).scale(scalar));
			break;
		case 65: // A
			snake.vel.add(new Vec2(-1,0).scale(scalar));
			break;
		case 83: // S
			snake.vel.add(new Vec2(0,1).scale(scalar));
			break;
		case 68: // D
			snake.vel.add(new Vec2(1,0).scale(scalar));
			break;
		}
		
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
