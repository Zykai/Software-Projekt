package DungeonGenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class RoomTree {
	
	public RoomTree left, right;
	public RoomBox leaf;
	private static Color ROOM_COLOR = new Color(128,128,128);
	
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

	public boolean draw(Graphics g){
		g.setColor(ROOM_COLOR);
		if(this.left != null){
			boolean l = this.left.draw(g);
			boolean r = this.right.draw(g);
			if(l && r){
				if(left.leaf.width == right.leaf.width){
					g.fillRect(left.leaf.cx-1, left.leaf.cy, 3, right.leaf.cy - left.leaf.cy);
				} else {
					g.fillRect(left.leaf.cx, left.leaf.cy-1, right.leaf.cx- left.leaf.cx, 3);
				}
				return true;
			}
			return false;
		} else {
			return this.leaf.draw(g);
		}
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
