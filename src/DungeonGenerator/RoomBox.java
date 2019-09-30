package DungeonGenerator;

import java.awt.Graphics;

public class RoomBox {
	public int x,y,width,height,cx,cy;
	Room room;
	public RoomBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.cx = x + width / 2;
		this.cy = y + height / 2;
		room = new Room(this);
	}
	
	public void draw(Graphics g) {
		//g.drawRect(x, y, width, height);
		room.draw(g);
	}

	public void fillGrid(int[][] grid) {
		room.fillGrid(grid);
	}
	
}