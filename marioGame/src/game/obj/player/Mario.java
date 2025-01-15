package game.obj.player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import game.obj.Dir;
import game.obj.Obstacle;
import game.obj.enemy.Enemy;
import game.util.BackGround;
import game.util.StaticValue;
/*
 * @author: Hu Ganglong
 * id:m24w0656
 * 
 * */
public class Mario implements Runnable {

	private int x,y,index,upTime;
	protected int xSpeed,ySpeed;

	public Dir dir=Dir.RIGHT;
	public PlayerStatus status0 = PlayerStatus.STOP;
	
	private BufferedImage show = null;

	private BackGround bg = new BackGround();

	private Thread thread = null;

	private boolean isWin,isDeath = false;
	private boolean isJumping=false;
	public boolean onObstacle = false;

	public Mario() {
		this.x = 10;this.y = 355;
		show = StaticValue.jump_R;
		thread = new Thread(this);
		thread.start();
	}
	//new move function ****head****
	public void movement() {
		if(status0 == PlayerStatus.JUMP) {
			switch(dir) {
			case LEFT:show = StaticValue.jump_L;jump();break;
			case RIGHT:show = StaticValue.jump_R;jump();break;
			default:break;
			}
		}
		if(status0 == PlayerStatus.RUN) {
			index = index == 0 ? 1 : 0;
			switch(dir) {
			case LEFT:show = isJumping ?  StaticValue.jump_L:StaticValue.run_L.get(index);xSpeed=-5;break;
			case RIGHT:show =isJumping ?  StaticValue.jump_R:StaticValue.run_R.get(index);xSpeed=5;break;
			default:break;
			}
		}
		if(status0 == PlayerStatus.STOP) {
			xSpeed=0;
			switch(dir) {
			case LEFT:show = isJumping ?  StaticValue.jump_L:StaticValue.stand_L;break;
			case RIGHT:show = isJumping ?  StaticValue.jump_R:StaticValue.stand_R;break;
			default:break;
			}
		}
	}
	//new move function ****end****
	
	// (jump & fall) new head
	public void jump() {
		if(!isJumping) {
			isJumping=true;
			y-=10;
			onObstacle=false;
			ySpeed = -10;
			upTime = 7;
		}else {
			if(upTime==0)
				status0= xSpeed==0 ? PlayerStatus.STOP:PlayerStatus.RUN;
		}

		if (bg.getIsReach()) {
			ySpeed = 0;
		}
	}
	public void fall() {
		switch(dir) {
		case LEFT:show = StaticValue.jump_L;break;
		case RIGHT:show = StaticValue.jump_R;break;
		default:break;
		}
		ySpeed=10;
	}
	// (jump & fall) new end
	
	public void death() {isDeath = true;}
	
	@Override
	public void run() {
		while (true) {

			boolean canRight = true;
			boolean canLeft = true;

			if (bg.isFlag() && this.x >= 500) {
				this.bg.setIsReach(true);

				if (this.bg.getIsBase()) {
					status0=PlayerStatus.RUN;
					dir=Dir.RIGHT;
					if (x < 690) {
						this.x += 5;
						this.y = 395;
					} else {
						isWin = true;
					}
				} else {
					if (y < 395) {
						xSpeed = 0;
						this.y += 5;
						status0=PlayerStatus.JUMP;
						dir=Dir.RIGHT;
					}
					if (y > 395) {
						this.y = 395;
						status0=PlayerStatus.STOP;
						dir=Dir.RIGHT;
					}
				}
			} else {
				// collision detection *****head****
				List<Obstacle> toRemove = new ArrayList<>();
				for (Obstacle ob : bg.getObstacleList()) {
					if (ob.getY() == this.y + 25 && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
						onObstacle = true;
						isJumping=false;
					}

					if ((ob.getY() >= this.y - 30 && ob.getY() <= this.y - 20)
					&& (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
						if (ob.getType() == 0) toRemove.add(ob);
						upTime = 0;
					}

					if (ob.getX() == this.x + 25 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 25)) {canRight = false;}
					if (ob.getX() == this.x - 30 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 25)) {canLeft = false;}
				}
				bg.getObstacleList().removeAll(toRemove);
				if(isJumping || !onObstacle) {
					if(upTime!=0) {upTime--;}
					else {ySpeed=0;fall();}
					y += ySpeed;
					
				}else {
					onObstacle = false;
					for(Obstacle ob : bg.getObstacleList()) 
						if (ob.getY() == this.y + 25 && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25))
							onObstacle = true;					
				}
			}
			if ((canLeft && xSpeed < 0) || (canRight && xSpeed > 0)) {
				x += xSpeed;
				if (x < 0) {x = 0;}
				if (x > 760 && bg.getSort() == 3) {	x = 760;}
			}
			// collision detection ****end****
			
			// kill enemy or killed by enemy
			for (int i = 0; i < bg.getEnemyList().size(); i++) {
				Enemy e = bg.getEnemyList().get(i);
				if (e.getY() == this.y + 20 && (e.getX() - 25 <= this.x && e.getX() + 35 >= this.x)) {
					//mogu
					if (e.getType() == 1) {
						e.death();
						upTime = 3;
						ySpeed = -10;
					}//flower 
					else if (e.getType() == 2) {death();}
					//tortoise
					else if(e.getType()==3) {
						e.death();
						e.setY(e.getY()+10);
						upTime = 3;
						ySpeed = -10;	
						
					}
					//tortoise shell
					else if(e.getType()==4) {
						e.death();
						upTime = 3;
						ySpeed = -10;
						
					}
				}

				if ((e.getX() + 35 > this.x && e.getX() - 25 < this.x)
						&& (e.getY() + 35 > this.y && e.getY() - 20 < this.y)) 
					death();
				
			}
			movement();
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public BufferedImage getShow() {return show;}
	public int getX() {return x;}
	public int getY() {return y;}
	public boolean getIsDeath() {return isDeath;}

	public void setX(int x) {this.x = x;}

	public void setY(int y) {this.y = y;}

	public void setBG(BackGround bg) {this.bg = bg;}

	public boolean getIsWin() {return isWin;}
	public void setIsWin(boolean isWin) {this.isWin = isWin;}
}
