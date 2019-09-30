package Entity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Maps.Map;

public class Animation {

	private Image[] frames;
	private double totalDuration;
	private double currentDuration;
	
	public Animation(String name, int nFrames, double totalDuration) {
		this.totalDuration = totalDuration;
		this.currentDuration = 0.0;
		this.frames = new Image[nFrames];
		for(int i = 0; i < frames.length; i++) {
			try {
				frames[i] = ImageIO.read(new File(name + i + ".png"));
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}
	}
	
	public void advance(double d) {
		this.currentDuration += d;
	}
	
	public void reset() {
		this.currentDuration = 0;
	}
	
	public Image getCurrentImage() {
		return this.frames[(int) ((currentDuration % totalDuration) / totalDuration * frames.length)];
	}
	
}
