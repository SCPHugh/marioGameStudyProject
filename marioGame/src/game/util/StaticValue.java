package game.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
/*
 * @author: Hu Ganglong
 * id:m24w0656
 * 
 * */
public class StaticValue {
	//
	public static BufferedImage bg= null;
	public static BufferedImage bg2= null;
	//move
	public static BufferedImage jump_L= null;
	public static BufferedImage jump_R= null;
	public static BufferedImage stand_L= null;
	public static BufferedImage stand_R= null;
	
	
	//building
	public static BufferedImage tower= null;
	public static BufferedImage gan= null;
	
	public static List<BufferedImage> run_L = new ArrayList<>();
	public static List<BufferedImage> run_R = new ArrayList<>();
	
	public static List<BufferedImage> obstacle = new ArrayList<>();
	
	public static List<BufferedImage> mogu = new ArrayList<>();
	public static BufferedImage mogu_death= null;
	public static List<BufferedImage> flower = new ArrayList<>();
	
	//new enemy ****head***
	public static List<BufferedImage> tortoise_L = new ArrayList<>();
	public static List<BufferedImage> tortoise_R = new ArrayList<>();
	public static List<BufferedImage> shell = new ArrayList<>();
	//***end***
	
	public static String path = System.getProperty("user.dir")+"/images/";
	
	public static void init() {
		try {
			bg = ImageIO.read(new File(path+"bg.png"));
			bg2 = ImageIO.read(new File(path+"bg2.png"));		
			//mario
			stand_L = ImageIO.read(new File(path+"stand_L.png"));
			stand_R= ImageIO.read(new File(path+"stand_R.png"));
			jump_L= ImageIO.read(new File(path+"jump1_L.png"));
			jump_R = ImageIO.read(new File(path+"jump1_R.png"));
			//
			tower= ImageIO.read(new File(path+"tower.png"));
			gan = ImageIO.read(new File(path+"gan.png"));
			
			mogu_death= ImageIO.read(new File(path+"fungus3.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i=1;i<=2;i++) {
			try {
				run_L.add(ImageIO.read(new File(path+"run"+i+"_L.png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		for(int i=1;i<=2;i++) {
			try {
				run_R.add(ImageIO.read(new File(path+"run"+i+"_R.png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//zhangaiwu(kepohuai)
		try {
			obstacle.add(ImageIO.read(new File(path+"brick.png")));//brick 0
			obstacle.add(ImageIO.read(new File(path+"soil_base.png")));//base 1
			obstacle.add(ImageIO.read(new File(path+"soil_up.png")));//soil 2
		}catch(IOException e) {
			e.printStackTrace();
		}
		//pip
		for(int i=1;i<=4;i++) {
			try {
				obstacle.add(ImageIO.read(new File(path+"pipe"+i+".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//(bukepohuai)
		try {
			obstacle.add(ImageIO.read(new File(path+"brick2.png")));
			obstacle.add(ImageIO.read(new File(path+"flag.png")));
		}catch(IOException e) {
			e.printStackTrace();
		}
		//enemy_mogu
		for(int i=1;i<=3;i++) {
			try {
				mogu.add(ImageIO.read(new File(path+"fungus"+i+".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		//enemy_flower
		for(int i=1;i<=2;i++) {
			try {
				flower.add(ImageIO.read(new File(path+"flower1."+i+".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		//enemy_tortoise ****new****
		//left
		for(int i=1;i<=2;i++) {
			try {
				tortoise_L.add(ImageIO.read(new File(path+"tortoise"+i+"_L.png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		//right
		for(int i=1;i<=2;i++) {
			try {
				tortoise_R.add(ImageIO.read(new File(path+"tortoise"+i+"_R.png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		//shell
		for(int i=0;i<=1;i++) {
			try {
				shell.add(ImageIO.read(new File(path+"shell_"+i+".png")));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
