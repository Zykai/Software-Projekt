package DungeonGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RoomTree {
	
	public RoomTree left, right;
	public RoomBox leaf;
	private static Color ROOM_COLOR = new Color(128,128,128);
	
	private static Image MINIMAP_BACKGROUND;

	static{
		try {
            MINIMAP_BACKGROUND = ImageIO.read(new File("res/dsui/menu_itembox.png")).getSubimage(0, 21, 562, 658).getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
	}

	public RoomTree(RoomBox c) {
		leaf = c;
	}
	
	public void print() {
		if(left != null) {
			left.print();
		}
		System.out.println(leaf + ", ");
		if(right != null) {
			right.print();
		}
	}

	private boolean draw(Graphics g){
		if(this.left != null){
			this.left.draw(g);
			this.right.draw(g);
			if(left.leaf.width == right.leaf.width){
				g.fillRect(left.leaf.cx-1, left.leaf.cy, 3, right.leaf.cy - left.leaf.cy);
			} else {
				g.fillRect(left.leaf.cx, left.leaf.cy-1, right.leaf.cx- left.leaf.cx, 3);
			}
			return true;
		} else {
			return this.leaf.draw(g);
		}
	}

	public boolean drawMap(Graphics g){
		g.drawImage(MINIMAP_BACKGROUND, -25, -25 ,null);
		g.setColor(ROOM_COLOR);
		return this.draw(g);
	}
	
	public void fillGrid(int[][] grid) {
		if(left == null && right == null) {
			leaf.fillGrid(grid);
		} else {
			left.fillGrid(grid);
			right.fillGrid(grid);
			RoomBox leftLeaf = left.leaf;
			RoomBox rightLeaf = right.leaf;
			if(leftLeaf.width == rightLeaf.width) {
				// Horizontal split (top/down)
				for(int x = leftLeaf.cx-1; x < leftLeaf.cx+2; x++) {
					for(int y = leftLeaf.cy; y < rightLeaf.cy; y++) {
						grid[x][y] = 1;
					}
				}
			} else {
				// Vertical split (right/left)
				for(int y = leftLeaf.cy-1; y < leftLeaf.cy+2; y++) {
					for(int x = leftLeaf.cx; x < rightLeaf.cx; x++) {
						grid[x][y] = 1;
					}
				}
			}
		}	
	}
	public Room getLefternMostRoom() {
		RoomTree current = this.left;
		if (current == null) {
			return this.leaf.room;
		}
		while(current.left != null) {
			current = current.left;
		}
		return current.leaf.room;
	}
	
	private void collectList(ArrayList<Room> roomList) {
		if(left != null && right != null) {
			left.collectList(roomList);
			right.collectList(roomList);
		} else {
			roomList.add(leaf.room);
		}
	}
	
	public ArrayList<Room> getRoomList(){
		ArrayList<Room> roomList = new ArrayList<Room>();
		collectList(roomList);
		return roomList;
	}
}
