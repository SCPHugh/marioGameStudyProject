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
public class Tortoise extends Enemy {

	public Tortoise(int x, int y, Dir dir, BackGround bg) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.bg = bg;
		this.type = 3;
		show = StaticValue.tortoise_L.get(0);
		thread.start();
	}

	public void death() {
		if(this.getType()==3)
			type=4;		
		else {
			this.bg.getEnemyList().remove(this);
		}
	}
	public void run() {
		while (true) {
			if(type==3) 
				switch (dir) {
				case LEFT->{this.x -= 2;show = StaticValue.tortoise_L.get(image_type);}
				case RIGHT->{this.x += 2;show = StaticValue.tortoise_R.get(image_type);}
				default -> throw new IllegalArgumentException("Unexpected value: " + dir);
				}
			if(type==4) {
				switch (dir) {
				case LEFT:this.x -= 5;break;
				case RIGHT:this.x += 5;break;
				default:break;
				}
				show=StaticValue.shell.get(image_type);
			}
			image_type = image_type == 1 ? 0 : 1;
			

			boolean canLeft = true;
			boolean canRight = true;

			for (int i = 0; i < bg.getObstacleList().size(); i++) {
				Obstacle ob = bg.getObstacleList().get(i);

				if (ob.getX() == this.x - 36 && (ob.getY() + 65 > this.y && ob.getY() - 35 < this.y)) {
					canLeft = false;
				}
				if (ob.getX() == this.x + 36 && (ob.getY() + 65 > this.y && ob.getY() - 35 < this.y)) {
					canRight = false;
				}

				if (dir == Dir.LEFT && !canLeft || this.x == 0) {
					dir = Dir.RIGHT;
				} else if ((dir == Dir.RIGHT) && (!canRight) || this.x == 764) {
					dir = Dir.LEFT;
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
