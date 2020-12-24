package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.snake.connection.GameState;

class SnakeCanvas extends JPanel implements MouseListener{

	GameState state;
    public SnakeCanvas(){
        this.addMouseListener(this);
    }
    public void setGameState(GameState state) {
    	this.state=state;
    }
    public void paintComponent(Graphics g) {
    	g.setColor(new Color(20,30,20));
    	g.drawRect(0, 0,3000,2000);
    	g.setColor(Color.red);
    	for (Entity e : state.entities) {
    		e.paintComponent(g);
    	}
    }

    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

    }

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
