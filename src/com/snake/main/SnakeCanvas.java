package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

class SnakeCanvas extends JPanel implements MouseListener{
   

    public SnakeCanvas(){
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
    	g.setColor(Color.red);
    	g.drawRect(0, 50, 10, 20);
    	g.fillOval(69, 420, 100, 200);
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

    // ... other MouseListener methods ... //
}
