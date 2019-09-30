package DungeonGenerator;

import java.awt.Graphics;

import Constants.Constants;

public class Room {
	public int x,y,width,height,cx,cy;

	public Room(RoomBox box) {
		x = box.x + Constants.random(0, (int) Math.floor((float)box.width / 3.0));
		y = box.y + Constants.random(0, (int) Math.floor((float)box.height / 3.0));
		width = box.width - (this.x - box.x);
		height = box.height - (this.y - box.y);
		this.width -= Constants.random(0, this.width / 3);
		this.height -= Constants.random(0, this.height / 3);
		this.cx = this.x + this.width / 2;
		this.cy = this.y + this.height / 2;
	}
	
	public void draw(Graphics g) {
		g.fillRect(x, y, width, height);
	}

	public void fillGrid(int[][] grid) {
		for(int i = x; i < x+width; i++) {
			for(int j = y; j < y+height; j++) {
				grid[i][j] = 1;
			}
		}
	}
}
