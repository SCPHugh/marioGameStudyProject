package game;

import javax.swing.*;

import game.obj.Dir;
import game.obj.Obstacle;
import game.obj.enemy.Enemy;
import game.obj.player.Mario;
import game.obj.player.PlayerStatus;
import game.util.BackGround;
import game.util.StaticValue;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

/*
 * @author: Hu Ganglong
 * id:m24w0656
 * 
 * */
public class MyFrame extends JFrame implements Runnable{

	private List<BackGround> allBG = new ArrayList<>();
	private BackGround nowBG = new BackGround();

	private Image offScreenImage = null;
	
	private Mario player = new Mario();
	
	private Thread thread = new Thread(this);
	
	public MyFrame() {
		init();
		repaint();
		thread.start();
	}
	
	public void init() {
		this.setTitle("maliao");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setResizable(false);
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.status0= PlayerStatus.RUN;
					player.dir=Dir.LEFT;
					break;
				case KeyEvent.VK_RIGHT:
					player.status0= PlayerStatus.RUN;
					player.dir=Dir.RIGHT;
					break;
				case KeyEvent.VK_UP:
					player.status0= PlayerStatus.JUMP;
					break;
				}
			}
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_RIGHT:
					player.status0=PlayerStatus.STOP;
					break;
				}
			}
		});
		StaticValue.init();		
		//create bg
		for(int i=1;i<=3;i++){
			allBG.add(new BackGround(i,i==3 ? true:false));
		}
		
		nowBG = allBG.get(0);
	}
	@Override
	public void paint(Graphics g) {
		if(offScreenImage == null) {
		   offScreenImage = createImage(800,600);
		}
		
		Graphics graphics = offScreenImage.getGraphics();
		graphics.fillRect(0, 0, 800, 600);
		
		graphics.drawImage(nowBG.getBG(),0,0,this);
		
		for(Enemy e:nowBG.getEnemyList()) {
			graphics.drawImage(e.getShow(),e.getX(),e.getY(),this);
		}
		
		for(Obstacle ob : nowBG.getObstacleList()) {
			graphics.drawImage(ob.getShow(),ob.getX(),ob.getY(),this);
		}
		
		graphics.drawImage(nowBG.getGan(),500,220,this);
		graphics.drawImage(nowBG.getTower(),620,270,this);
		
		graphics.drawImage(player.getShow(),player.getX(),player.getY(),this);
		
		g.drawImage(offScreenImage, 0,0,this);
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}

	@Override
	public void run() {
		while(true) {
			repaint();		
			try {
				Thread.sleep(50);
				player.setBG(nowBG);
				if(player.getX()>=775) {			
					nowBG=allBG.get(nowBG.getSort());
					player.setX(10);
				}
				
				if(player.getIsDeath()) {
					JOptionPane.showMessageDialog(this, "LOSE!");
					System.exit(0);
				}
				
				if(player.getIsWin()) {
					JOptionPane.showMessageDialog(this, "YOU WIN!");
					System.exit(0);
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
