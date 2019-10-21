package Constants;

import java.util.Random;

import Player.Player;

public class Constants {

	public static Random rand = new Random();
	public static int SCREEN_X = 1920;
	public static int SCREEN_Y = 1080;
	public static int BUTTON_X = 857/2;
	public static int BUTTON_Y = 150/2;
	
	public static String safeName = "Default";
	public final static int HERO = 0;
	public final static int MAGE = 1;
	public static int PLAYER_CLASS = 0;

	public static int random(int min, int max) {
		return rand.nextInt(max-min+1) + min;
	}

	public static double getDistance(double x1, double y1, double x2, double y2){
		double xdif = x1-x2;
		double ydif = y1-y2;
		return xdif * xdif + ydif * ydif;
	}

}
