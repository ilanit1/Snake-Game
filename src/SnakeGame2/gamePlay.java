package SnakeGame2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class gamePlay extends JPanel implements ActionListener, KeyListener {

	private ImageIcon titleImage;

	private int[] snakeXlength=new int[750];
	private int[] snakeYlength=new int[750];

	private boolean left=false;
	private boolean rigth=false;
	private boolean up=false;
	private boolean down=false;

	private ImageIcon leftMouth;
	private ImageIcon rigthMouth;
	private ImageIcon upMouth;
	private ImageIcon downMouth;

	private Timer timer;
	private int delay=100;

	private ImageIcon snakeImage;

	private int lengthOfSnake=3;

	private int moves=0;

	//enemies
	private int[] enemyXpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] enemyYpos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	private ImageIcon enemyImage;
	private Random random=new Random();
	private int Xpos=random.nextInt(34);
	private int Ypos=random.nextInt(23);

	private int score=0;

	private boolean gameOver=false;

	private ArrayList scores=new ArrayList<Integer>();
	private int highScore=0;

	public gamePlay(){

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();
	}

	public void paint(Graphics g){

		//the frame of the snake title
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55); //24- left start, 10- up start, 851- right end, 55- down end
		g.setColor(Color.black);
		g.fillRect(25, 11, 850, 54);
		titleImage=new ImageIcon("C:\\Users\\Ilanit Horwitz\\myProjects\\snake\\download.jpg");
		titleImage.paintIcon(this, g, 325, 11);

		//the frame of the snake game
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);

		//the score
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,16));
		g.drawString("Scores: "+score, 780, 30);

		//the length
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,16));
		g.drawString("Length: "+lengthOfSnake, 780, 50);


		////lets start play:
		if(moves==0){
			snakeXlength[2]=50;
			snakeXlength[1]=75;
			snakeXlength[0]=100;

			snakeYlength[2]=100;
			snakeYlength[1]=100;
			snakeYlength[0]=100;

			rigthMouth=new ImageIcon("C:\\Users\\Ilanit Horwitz\\myProjects\\snake\\rightMouth.png");
			rigthMouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		}




		for (int i = 0; i < lengthOfSnake; i++) {

			if(i==0&&rigth){
				rigthMouth=new ImageIcon("C:\\Users\\Ilanit Horwitz\\myProjects\\snake\\rightMouth.png");
				rigthMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0&&up){
				upMouth=new ImageIcon("C:\\Users\\Ilanit Horwitz\\myProjects\\snake\\upMouth.png");
				upMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0&&left){
				leftMouth=new ImageIcon("C:\\Users\\Ilanit Horwitz\\myProjects\\snake\\leftMouth.png");
				leftMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0&&down){
				downMouth=new ImageIcon("C:\\Users\\Ilanit Horwitz\\myProjects\\snake\\downMouth.png");
				downMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}

			if(i!=0){
				snakeImage=new ImageIcon("C:\\Users\\Ilanit Horwitz\\myProjects\\snake\\snakeImage.png");
				snakeImage.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);

			}

		}
		enemyImage=new ImageIcon("C:\\Users\\Ilanit Horwitz\\myProjects\\snake\\dot.png");

		if((enemyXpos[Xpos]==snakeXlength[0])&&(enemyYpos[Ypos]==snakeYlength[0])){
			score++;
			lengthOfSnake++;
			Xpos=random.nextInt(34);
			Ypos=random.nextInt(23);
		}

		enemyImage.paintIcon(this, g, enemyXpos[Xpos], enemyYpos[Ypos]);


		for (int i = 1; i < lengthOfSnake; i++) {
			if(snakeXlength[i]==snakeXlength[0]&&snakeYlength[i]==snakeYlength[0]){
				rigth=false;
				left=false;
				up=false;
				down=false;
				scores.add(score);

				for (int j = 0; j < scores.size(); j++) {
					
					if((int)scores.get(j)>highScore){
						highScore=(int)scores.get(j);
						
						g.setFont(new Font("arial",Font.BOLD,30));
						g.drawString("Congrudulations! You got the highest score!", 100, 300);
						
						g.setFont(new Font("arial",Font.BOLD,30));
						g.drawString("Length: "+lengthOfSnake, 370, 340);
						g.setFont(new Font("arial",Font.BOLD,30));
						g.drawString("Score: "+score, 370, 370);
						g.setFont(new Font("arial",Font.BOLD,30));
						g.drawString("High Score: "+highScore, 355, 400);

						g.setFont(new Font("arial",Font.BOLD,20));
						g.drawString("press SPACE to RESTART, or ESC to EXIT", 290, 450);
						
					}
					
					else{
						
						g.setColor(Color.white);
						
						g.setFont(new Font("arial",Font.BOLD,50));
						g.drawString("GAME OVER!", 300, 300);
						
						g.setFont(new Font("arial",Font.BOLD,30));
						g.drawString("Length: "+lengthOfSnake, 370, 340);
						g.setFont(new Font("arial",Font.BOLD,30));
						g.drawString("Score: "+score, 370, 370);
						g.setFont(new Font("arial",Font.BOLD,30));
						g.drawString("High Score: "+highScore, 355, 400);

						g.setFont(new Font("arial",Font.BOLD,20));
						g.drawString("press SPACE to RESTART, or ESC to EXIT", 290, 450);
						

					}
					
				}
				

				gameOver=true;

			}
		}

		g.dispose();

	}


	public void actionPerformed(ActionEvent arg0) {
		timer.start();

		if(rigth){

			for (int i = lengthOfSnake-1; i >=0; i--) {
				snakeYlength[i+1]=snakeYlength[i];
			}
			for (int i = lengthOfSnake-1; i >=0; i--) {
				if(i==0){
					snakeXlength[i]=snakeXlength[i]+25;
				}
				else{
					snakeXlength[i]=snakeXlength[i-1];
				}

				if(snakeXlength[i]>850)
					snakeXlength[i]=25;
			}
			repaint();
		}

		if(left){

			for (int i = lengthOfSnake-1; i >=0; i--) {
				snakeYlength[i+1]=snakeYlength[i];
			}
			for (int i = lengthOfSnake-1; i >=0; i--) {
				if(i==0){
					snakeXlength[i]=snakeXlength[i]-25;
				}
				else{
					snakeXlength[i]=snakeXlength[i-1];
				}

				if(snakeXlength[i]<25)
					snakeXlength[i]=850;

			}
			repaint();
		}

		if(up){
			for (int i = lengthOfSnake-1; i >=0; i--) {
				snakeXlength[i+1]=snakeXlength[i];
			}
			for (int i = lengthOfSnake-1; i >=0; i--) {
				if(i==0){
					snakeYlength[i]=snakeYlength[i]-25;
				}
				else{
					snakeYlength[i]=snakeYlength[i-1];
				}

				if(snakeYlength[i]<75)
					snakeYlength[i]=625;

			}
			repaint();
		}

		if(down){
			for (int i = lengthOfSnake-1; i >=0; i--) {
				snakeXlength[i+1]=snakeXlength[i];
			}
			for (int i = lengthOfSnake-1; i >=0; i--) {
				if(i==0){
					snakeYlength[i]=snakeYlength[i]+25;
				}
				else{
					snakeYlength[i]=snakeYlength[i-1];
				}

				if(snakeYlength[i]>625)
					snakeYlength[i]=75;

			}
			repaint();
		}
	}


	public void keyPressed(KeyEvent arg0) {

		if(!gameOver&&arg0.getKeyCode()==KeyEvent.VK_RIGHT){
			moves++;
			rigth=true;
			if(!left){
				rigth=true;
			}
			else{
				rigth=false;
				left=true;
			}
			up=false;
			down=false;
		}

		if(!gameOver&&arg0.getKeyCode()==KeyEvent.VK_LEFT){
			moves++;
			left=true;
			if(!rigth){
				left=true;
			}
			else{
				left=false;
				rigth=true;
			}
			up=false;
			down=false;
		}

		if(!gameOver&&arg0.getKeyCode()==KeyEvent.VK_UP){
			moves++;
			up=true;
			if(!down){
				up=true;
			}
			else{
				up=false;
				down=true;
			}
			left=false;
			rigth=false;
		}

		if(!gameOver&&arg0.getKeyCode()==KeyEvent.VK_DOWN){
			moves++;
			down=true;
			if(!up){
				down=true;
			}
			else{
				down=false;
				up=true;
			}
			left=false;
			rigth=false;

		}

		if(arg0.getKeyCode()==KeyEvent.VK_SPACE){
			moves=0;
			lengthOfSnake=3;
			score=0;
			repaint();
			gameOver=false;
		}

		


	}


	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
}