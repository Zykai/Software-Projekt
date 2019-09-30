package Constants;

import java.util.Random;

public class Constants {

	public static Random rand = new Random();
	public static int SCREEN_X = 1280;
	public static int SCREEN_Y = 960;
	
	public static int random(int min, int max) {
		return rand.nextInt(max-min+1) + min;
	}

}
