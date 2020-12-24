package com.snake.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class LoginScreen {
	public static void main(String[] args) {
		int frameWidth = 800;
		int frameHeight = 800;
		
		
		JFrame frame = new JFrame("ZnaKe");
	      final JPanel panel = new JPanel();
	      Thread colorThread = new Thread() {
	    	  public void run() {
	    		  double a=0;
	    		  while(true) {
		    			try {
						    Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		    			a+=0.06;
		    			int r = (int) (122*Math.sin(a+170)+122);
		    			int g = (int) (90*Math.sin(a+255)+90);
		    			int b = (int) (90*Math.sin(a+85)+122);
		    			
	    			    panel.setBackground(new Color(r,g,b));
	    		  }
	    	  }
	      };
	      colorThread.start();
	      
	      frame.getContentPane();
	      JLabel welcome = new JLabel("Welcome to Znake!");
	      setFontSize(welcome, 64);
	      Dimension welcomeSize = welcome.getPreferredSize();
	      welcome.setBounds(frameWidth/2-welcomeSize.width/2, frameHeight/4-welcomeSize.height/2, welcomeSize.width, welcomeSize.height);
	      
	      JTextField username = new JTextField();
	      int usernameWidth = 300;
	      int usernameHeight = 50;
	      username.setBounds(3*frameWidth/4-usernameWidth/2,frameWidth/2-usernameHeight/2, usernameWidth, usernameHeight);
	      Border border = BorderFactory.createLineBorder(Color.BLACK);
	      username.setBorder(border);
	      panel.setLayout(null);
	      panel.add(username);
	      panel.add(welcome);
	      panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      frame.add(panel);
	      frame.setSize(frameWidth, frameHeight);
	      frame.setVisible(true);
	
		
	}
	
	public static void connectMethod() {
		System.out.println("lol!");
	}
	public static void setFontSize(JLabel label, float size) {
		 label.setFont (label.getFont ().deriveFont (size));
	}
}



/*
JFrame f = new JFrame();
f.setLayout(new FlowLayout(FlowLayout.LEFT));
f.setSize(800, 800);
JLabel welcome = new JLabel();
welcome.setText("Welcome to [insert title]!");
welcome.setVisible(true);
welcome.setLocation(0,0);

JTextField ip = new JTextField();
JLabel ipLabel = new JLabel();
ipLabel.setText("Server ip");
int a = 1;
while (a==1) {
	if (ip.getText() == "") {
		ipLabel.setVisible(true);
	}else {
		ipLabel.setVisible(false);
		a = 0;
	}
}

JButton connect = new JButton();
connect.setText("Connect!");
connect.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent e) { 
        connectMethod();
    } 
});
f.add(welcome);
f.setVisible(true);
*/