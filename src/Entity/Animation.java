package Entity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Maps.Map;

public class Animation {

	private Image[] frames;
	private double totalDuration;
	
	public Animation(String name, int nFrames, double totalDuration) {
		this.totalDuration = totalDuration;
		this.frames = new Image[nFrames];
		for(int i = 0; i < frames.length; i++) {
			try {
				frames[i] = ImageIO.read(new File(name + i + ".png"));
			}
			catch (IOException e) {
				System.out.println(e.getMessage() + "(" + name + i + ".png)");
				System.exit(0);
			}
		}
	}
	
	public boolean isFinished(double currentDuration) {
		return this.totalDuration <= currentDuration;
	}
	
	public Image getCurrentImage(double currentDuration) {
		return this.frames[(int) ((currentDuration % totalDuration) / totalDuration * frames.length)];
	}
	
}
