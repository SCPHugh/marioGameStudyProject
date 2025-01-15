package game.obj.enemy;

import java.awt.image.BufferedImage;

import game.obj.Dir;
import game.util.BackGround;
/*
 * @author: Hu Ganglong
 * id:m24w0656
 * 
 * */
public class Enemy implements Runnable{

	protected int x;
	protected int y;
	
	protected int type;
	
	protected boolean face =true;
	protected Dir dir;
	
	protected BufferedImage show;
	
	protected BackGround bg;
	
	protected Thread thread = new Thread(this);
	
	protected int image_type=0;
		
	public void death() {}
	
	@Override
	public void run() {	}

	public BufferedImage getShow() {return show;}
	
	public int getX() {		return x;	}
	public int getY() {		return y;	}
	public void setX(int x) {		this.x=x;	}
	public void setY(int y) {		this.y=y;	}
	
	public int getType() {return type;}
	public void setBG(BackGround bg) {		this.bg=bg;	}
}
