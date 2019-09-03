package SnakeGame2;

import java.awt.Color;

import javax.swing.JFrame;


public class main {

	public static void main(String[] args) {
		
		//create the original frame
		JFrame obj=new JFrame();
		obj.setBounds(10, 10, 905, 700);
		obj.getContentPane().setBackground(Color.DARK_GRAY); // or obj.setBackground(Color.DARK_GRAY);
		obj.setResizable(false);  //can't change the size
		obj.setVisible(true);     //can see the frame 
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//put my game in the original frame
		gamePlay gamePlay=new gamePlay();
		obj.add(gamePlay);
	

	}

}
