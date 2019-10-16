package DungeonGenerator;

import java.awt.Graphics;
import java.util.ArrayList;

import Constants.Constants;
import Enemies.Enemy;
import Enemies.EnemyGenInformation;
import Enemies.EnemyGenerator;
import Enemies.Ghoul;
import Enemies.Imp;
import Enemies.Phantom;
import Enemies.Reaper;
import Enemies.Slug;
import Enemies.Undead;
import Maps.Map;
import Player.Player;

public class Room {
	public int x,y,width,height,cx,cy;

	public ArrayList<Enemy> enemies;
	
	private int numberEnemies;
	
	public static int DENSITY = 35;
	
	public Room(RoomBox box) {
		x = box.x + Constants.random(0, (int) Math.floor((float)box.width / 3.0));
		y = box.y + Constants.random(0, (int) Math.floor((float)box.height / 3.0));
		width = box.width - (this.x - box.x);
		height = box.height - (this.y - box.y);
		this.width -= Constants.random(0, this.width / 3);
		this.height -= Constants.random(0, this.height / 3);
		this.cx = this.x + this.width / 2;
		this.cy = this.y + this.height / 2;
		
		int halfWidth = width / 2;
		int halfHeight = height / 2;
		enemies = new ArrayList<Enemy>();
		this.numberEnemies = width * height / DENSITY;
		EnemyGenerator g = new EnemyGenerator(this.numberEnemies);
		EnemyGenInformation info;
		while((info = g.getNext()) != null) {
			int enemyX = cx + Constants.random(-halfWidth * 3 / 4, halfWidth * 3 / 4);
			int enemyY = cy + Constants.random(-halfHeight * 3 / 4, halfHeight * 2 / 3);
			for(int i = 0; i < info.count; i++) {
				enemies.add(g.fromType(info.type, enemyX * Map.TILE_SIZE, enemyY * Map.TILE_SIZE));
			}
		}
	}
	
	public void draw(Graphics g, int xoffset, int yoffset) {
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g, xoffset, yoffset);
		}
	}

	public void fillGrid(int[][] grid) {
		for(int i = x; i < x+width; i++) {
			for(int j = y; j < y+height; j++) {
				grid[i][j] = 1;
			}
		}
	}
	public boolean hasPlayer(Player p) {
		int tileX = (int) (p.getX() / Map.TILE_SIZE);
		int tileY = (int) (p.getY() / Map.TILE_SIZE);
		return tileX > x && tileX < x+width && tileY > y-2 && tileY < y + height;
	}
}