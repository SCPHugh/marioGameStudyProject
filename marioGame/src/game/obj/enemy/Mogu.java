package game.obj.enemy;

import game.obj.Dir;
import game.obj.Obstacle;
import game.util.BackGround;
import game.util.StaticValue;
/*
 * @author: Hu Ganglong
 * id:m24w0656
 * 
 * */
public class Mogu extends Enemy{

	
	public Mogu(int x,int y,Dir dir,BackGround bg) {
		this.x=x;
		this.y=y;
		this.dir=dir;
		this.bg=bg;
		this.type=1;
		show=StaticValue.mogu.get(0);
		thread.start();
	}
	
	public void death() {
		show = StaticValue.mogu_death;
		this.bg.getEnemyList().remove(this);
	}
	
	public void run() {
		while(true) {
			
			switch(dir) {
			case LEFT:this.x -= 2;break;
			case RIGHT:this.x += 2;break;
			default:break;
			}
			image_type = image_type == 1 ? 0:1;
			show=StaticValue.mogu.get(image_type);
			
			boolean canLeft=true;
			boolean canRight=true;
			
			for(int i=0;i<bg.getObstacleList().size();i++) {
				Obstacle ob = bg.getObstacleList().get(i);
				
				//mogu move
				if(ob.getX()==this.x-36 && (ob.getY()+65>this.y&& ob.getY()-35<this.y)) {
					canLeft=false;
				}
				if(ob.getX()==this.x+36 && (ob.getY()+65>this.y&& ob.getY()-35<this.y)) {
					canRight=false;
				}
				
				if(dir==Dir.LEFT && !canLeft || this.x==0) {
					dir=Dir.RIGHT;
				}else if((dir==Dir.RIGHT)&&(!canRight)||this.x==764) {
					dir=Dir.LEFT;
				}
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
