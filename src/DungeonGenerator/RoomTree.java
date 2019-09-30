package DungeonGenerator;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class RoomTree {
	
	public RoomTree left, right;
	public RoomBox leaf;
	
	
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
	public void draw(Graphics g) {
		if(left == null && right == null) {
			leaf.draw(g);
		} else {
			left.draw(g);
			right.draw(g);
			Graphics2D g2 = (Graphics2D) g;
		    g2.setStroke(new java.awt.BasicStroke(8));
			g2.drawLine(left.leaf.cx, left.leaf.cy, right.leaf.cx, right.leaf.cy);
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
}
