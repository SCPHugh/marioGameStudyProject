package game.util;

import java.awt.image.BufferedImage;

import game.obj.*;
import game.obj.enemy.*;


import java.util.ArrayList;
import java.util.List;
/*
 * @author: Hu Ganglong
 * id:m24w0656
 * 
 * */
public class BackGround {

	private BufferedImage bg;
	
	private int sort;//第几关
	
	private boolean flag;//最后一关的标记
	
	private List<Obstacle> obstacleList = new ArrayList<>();
	private List<Enemy> enemyList = new ArrayList<>();
	
	private BufferedImage gan = null;
	private BufferedImage tower =null;
	
	private boolean isReach=false;//到杆子
	private boolean isBase=false;//到基地
	
	public BackGround() {
	}
	
	public BackGround(int sort,boolean flag) {
		this.sort = sort;
		this.flag=flag;
		if(flag) {
			bg = StaticValue.bg2;
		}else {
			bg = StaticValue.bg;
		}
		
		//第一关
		if(sort==1) {
			//检测地板，type 2 上地面，1 下地面
			for(int i=0;i<=27;i++) {//一层27个方块，每个方块30*30
				obstacleList.add(new Obstacle(i*30,420,2,this));
			}
			for(int j=0;j<=120;j+=30) {
				for(int i=0;i<27;i++) {
					obstacleList.add(new Obstacle(i*30,570-j,1,this));
				}
			}
			
			//可破坏 0,不可 7
			for(int i=120;i<=150;i+=30) {
				obstacleList.add(new Obstacle(i,300,7,this));
			}
			
			for(int i=300;i<=570;i+=30) {
				if(i == 360 || i==390||i==480||i==510||i==540) {
					obstacleList.add(new Obstacle(i,300,7,this));
				}else {
					obstacleList.add(new Obstacle(i,300,0,this));
				}
			}
			
			for(int i=420;i<=450;i+=30) {
				obstacleList.add(new Obstacle(i,240,7,this));
			}
			
			//pipe
			for(int i=360;i<=600;i+=25) {
				if(i == 360) {
					obstacleList.add(new Obstacle(620,i,3,this));
					obstacleList.add(new Obstacle(645,i,4,this));
				}else {
					obstacleList.add(new Obstacle(620,i,5,this));
					obstacleList.add(new Obstacle(645,i,6,this));
				}
			}
			
			//loading enemy
			enemyList.add(new Mogu(500,385,Dir.LEFT,this));
			enemyList.add(new Flower(635,420,Dir.UP,this,328,428));
			enemyList.add(new Tortoise(580,385,Dir.LEFT,this));
		}
		//第二关
		if(sort==2) {
			//地面
			for(int i=0;i<=27;i++) {//一层27个方块，每个方块30*30
				obstacleList.add(new Obstacle(i*30,420,2,this));
			}
			for(int j=0;j<=120;j+=30) {
				for(int i=0;i<27;i++) {
					obstacleList.add(new Obstacle(i*30,570-j,1,this));
				}
			}
			//左一pipe
			for(int i=360;i<=600;i+=25) {
				if(i == 360) {
					obstacleList.add(new Obstacle(60,i,3,this));
					obstacleList.add(new Obstacle(85,i,4,this));
				}else {
					obstacleList.add(new Obstacle(60,i,5,this));
					obstacleList.add(new Obstacle(85,i,6,this));
				}
			}
			//右二pipe
			for(int i=330;i<=600;i+=25) {
				if(i == 330) {
					obstacleList.add(new Obstacle(620,i,3,this));
					obstacleList.add(new Obstacle(645,i,4,this));
				}else {
					obstacleList.add(new Obstacle(620,i,5,this));
					obstacleList.add(new Obstacle(645,i,6,this));
				}
			}
			//障碍物
			obstacleList.add(new Obstacle(300,330,0,this));
			
			for(int i=270;i<=330;i+=30) {
				if(i==270||i==330) {
					obstacleList.add(new Obstacle(i,360,0,this));
				}else {
					obstacleList.add(new Obstacle(i,360,7,this));
				}
			}
			
			for(int i=240;i<=360;i+=30) {
				if(i==240||i==360) {
					obstacleList.add(new Obstacle(i,390,0,this));
				}else {
					obstacleList.add(new Obstacle(i,390,7,this));
				}
			}
			
			obstacleList.add(new Obstacle(240,300,0,this));
			for(int i=360;i<=540;i+=60) {
				obstacleList.add(new Obstacle(i,270,7,this));
			}
			
			//enemy
			enemyList.add(new Flower(75,420,Dir.UP,this,328,428));
			enemyList.add(new Flower(635,420,Dir.UP,this,298,388));
			
			enemyList.add(new Mogu(200,385,Dir.LEFT,this));
			enemyList.add(new Mogu(500,385,Dir.LEFT,this));
			
			enemyList.add(new Tortoise(220,385,Dir.LEFT,this));
		}
		
		//第三关
		if(sort==3) {
			//地面
			for(int i=0;i<=27;i++) {//一层27个方块，每个方块30*30
				obstacleList.add(new Obstacle(i*30,420,2,this));
			}
			for(int j=0;j<=120;j+=30) {
				for(int i=0;i<27;i++) {
					obstacleList.add(new Obstacle(i*30,570-j,1,this));
				}
			}
			//障碍物
			int temp= 290;
			for(int i=390;i>=270;i-=30) {
				for(int j=temp;j<=410;j+=30) {
					obstacleList.add(new Obstacle(j,i,7,this));
				}
				temp+=30;
			}
			temp= 60;
			for(int i=390;i>=360;i-=30) {
				for(int j=temp;j<=90;j+=30) {
					obstacleList.add(new Obstacle(j,i,7,this));
				}
				temp+=30;
			}
			//enemy
			
			enemyList.add(new Mogu(150,385,Dir.LEFT,this));
			
			//旗杆&城堡
			gan=StaticValue.gan;
			tower =StaticValue.tower;
			
			obstacleList.add(new Obstacle(515,220,8,this));//旗面位置
			
		}
	}
	
	public BufferedImage getBG() {return bg;}
	
	public int getSort() {return sort;}
	
	public boolean isFlag() {return flag;}
	
	public List<Obstacle> getObstacleList(){return obstacleList;}
	
	public List<Enemy> getEnemyList(){return enemyList;}
	
	public BufferedImage getGan() {return gan;}
	
	public BufferedImage getTower() {return tower;}
	
	public boolean getIsReach() {return isReach;}
	
	public void setIsReach(boolean isReach) {this.isReach=isReach;}
	
	public boolean getIsBase() {return isBase;}
	
	public void setIsBase(boolean isBase) {this.isBase=isBase;}
}
