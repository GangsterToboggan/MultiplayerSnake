package com.snake.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

class SnakeCanvas extends JPanel implements MouseListener{

	public List<Apple> apples = new ArrayList<>();  
	public List<Snake> snakes = new ArrayList<>();

    public SnakeCanvas(){
        this.addMouseListener(this);
    }
    
    public void addSnake(Snake s) {
    	this.snakes.add(s);
    }
    public void addApple(Apple a) {
    	this.apples.add(a);
    }
    
    public void paintComponent(Graphics g) {
    	g.setColor(new Color(20,30,20));
    	g.drawRect(0, 0,3000,2000);
    	g.setColor(Color.red);
    	g.fillRect(10, 10, 20, 20);
    	for (Snake s : snakes) {
    		s.paintComponent(g);
    	}
    	for (Apple a : apples) {
    		a.paintComponent(g);
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

    // ... other MouseListener methods ... //
}
