package game.obj;

import java.awt.image.BufferedImage;

import game.util.BackGround;
import game.util.StaticValue;
/*
 * @author: Hu Ganglong
 * id:m24w0656
 * 
 * */
public class Obstacle implements Runnable{

	private int x;
	private int y;
	
	private int type;
	
	private BufferedImage show = null;
	
	private BackGround bg = null;
	 
	Thread thread =new Thread(this);
	
	public Obstacle() {}
	
	public Obstacle(int x,int y,int type,BackGround bg){
		this.x=x;
		this.y=y;
		this.type=type;
		this.bg=bg;
		show = StaticValue.obstacle.get(type);
		
		if(type==8) {
			thread.start();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			if(this.bg.getIsReach()) {
				if(this.y < 372 ) {
					this.y +=5;
				}else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					this.bg.setIsBase(true);
				}
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getType() {
		return type;
	}
	public BackGround getBG() {
		return bg;
	}
	public BufferedImage getShow() {
		return show;
	}

	
}
