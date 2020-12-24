package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import javax.swing.JPanel;

import com.snake.connection.GameState;

public class SnakeCanvas extends JPanel implements MouseListener{

	GameState state = new GameState();
	Semaphore stateSem = new Semaphore(1);
    public SnakeCanvas(){
    	state.entities=new ArrayList<>();
        this.addMouseListener(this);
    }
    public void setGameState(GameState state) {
    	try {
			stateSem.acquire();
			this.state=state;
	    	stateSem.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    }
    public void paintComponent(Graphics g) {
    	try {
			stateSem.acquire();
			g.setColor(new Color(20,30,20));
	    	g.drawRect(0, 0,3000,2000);
	    	g.setColor(Color.red);
	    	for (Entity e : state.entities) {
	    		e.paintComponent(g);
	    	}
	    	stateSem.release();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
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
