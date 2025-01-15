package game.obj.enemy;

import game.obj.Dir;
import game.util.BackGround;
import game.util.StaticValue;
/*
 * @author: Hu Ganglong
 * id:m24w0656
 * 
 * */
public class Flower extends Enemy{

	private int max_up=0;
	private int max_down = 0;
	
	public Flower(int x,int y,Dir dir,BackGround bg,int max_up,int max_down) {
		this.x=x;
		this.y=y;
		this.dir=dir;
		this.bg=bg;
		this.type=2;
		this.max_up=max_up;
		this.max_down=max_down;
		show=StaticValue.flower.get(0);
		thread.start();
	}
	
	public void run() {
		while(true) {
			//flower move
			switch(dir) {
			case UP:this.y -= 2;break;
			case DOWN:this.y += 2;break;
			default:break;
			}
			image_type = image_type == 1 ? 0:1;
				
			if(dir==Dir.UP && (this.y == max_up)) {
				dir=Dir.DOWN;
			}
			if(dir==Dir.DOWN&&(this.y==max_down)) {
				dir=Dir.UP;
			}
			show=StaticValue.flower.get(image_type);
			
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
