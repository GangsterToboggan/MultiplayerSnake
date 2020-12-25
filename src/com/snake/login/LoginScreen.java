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

import com.snake.main.ClientGame;

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
	      username.setBounds(frameWidth/2-usernameWidth/2,7*frameHeight/16-usernameHeight/2, usernameWidth, usernameHeight);
	      setFontSize(username, 35);
	      Border border = BorderFactory.createLineBorder(Color.BLACK);
	      username.setBorder(border);
	      
	      JLabel usernameLabel = new JLabel("Username:");
	      setFontSize(usernameLabel, 40);
	      Dimension usernameLabelSize = usernameLabel.getPreferredSize();
	      usernameLabel.setBounds(7*frameWidth/16-usernameLabelSize.width/2,12*frameHeight/32-usernameLabelSize.height/2, usernameLabelSize.width, usernameLabelSize.height);
	      
	      
	      JTextField ip = new JTextField();
	      int ipWidth = 300;
	      int ipHeight = 50;
	      ip.setBounds(frameWidth/2-ipWidth/2,19*frameHeight/32-ipHeight/2, ipWidth, ipHeight);
	      setFontSize(ip, 35);
	      ip.setBorder(border);
	      
	      JLabel ipLabel = new JLabel("Server Ip Adress:");
	      setFontSize(ipLabel, 35);
	      Dimension ipLabelSize = ipLabel.getPreferredSize();
	      ipLabel.setBounds(frameWidth/2-ipLabelSize.width/2, 17*frameHeight/32-ipLabelSize.height/2, ipLabelSize.width, ipLabelSize.height);
	      
	      JButton start = new JButton("Start!");
	      setFontSize(start,60);
	      int startWidth = 300;
	      int startHeight = 100;
	      start.setBounds(frameWidth/2-startWidth/2, 24*frameHeight/32-startHeight/2, startWidth, startHeight);
	      start.addActionListener(new ActionListener() { 
	    	    public void actionPerformed(ActionEvent e) { 
	    	        connectMethod(username.getText(),ip.getText(),6666);
	    	        frame.dispose();
	    	    } 
	    	});
	      
	      
	      panel.setLayout(null);
	      panel.add(start);
	      panel.add(ipLabel);
	      panel.add(ip);
	      panel.add(usernameLabel);
	      panel.add(username);
	      panel.add(welcome);
	      panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	      frame.add(panel);
	      frame.setSize(frameWidth, frameHeight);
	      frame.setVisible(true);
	
		
	}
	
	public static void connectMethod(String username, String ip, int port) { //ZACK THIS SHOULD HAVE ALL THE INFORMATION YOU NEED
		System.out.println("Connecting to ");
		System.out.println("Ip: "+ip);
		System.out.println("Username: "+username);
		System.out.println("Port: "+port);
		ClientGame.run(ip, port, username);
		
	}
	public static void setFontSize(JTextField field, float size) {
		 field.setFont (field.getFont ().deriveFont (size));
	}
	public static void setFontSize(JLabel label, float size) {
		 label.setFont (label.getFont ().deriveFont (size));
	}
	public static void setFontSize(JButton Button, float size) {
		 Button.setFont (Button.getFont ().deriveFont (size));
	}
}



