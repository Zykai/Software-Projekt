package Maps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

import Constants.Constants;
import DungeonGenerator.DungeonGenerator;
import DungeonGenerator.Room;
import Enemies.Enemy;
import Player.Player;

public class Darkness extends Map {

	private static int X_TILES = 200;
	private static int Y_TILES = 200;
	private BufferedImage all;
	private Image[][] scaledAll;
	private Image[] ground;
	private Image[][] background;
	
	private static int VOID = 0;
	private static int GROUND;
	
	private int[][] tiles;
	private Image[][] imageGrid;
	
	private Random rand;
	private Color backgroundColor;
	
	private double startingX, startingY;
	
	private Room currentRoom;
	private ArrayList<Room> roomList;
	
	private Image randomSpace() {
		if (rand.nextInt(5)<3) {
			return scaledAll[0][8];
		}
		return scaledAll[rand.nextInt(2)][rand.nextInt(4)+9];
	}
	
	private Image getTexture(int x, int y) {
		if (x >= X_TILES || x <= 0 || y >= Y_TILES || y <= 0) {
			return null;
		}
		if(tiles[x][y] == 0) {
			if(tiles[x][y-1] == 1) {
				if(tiles[x+1][y-1] == 0)  {
					return scaledAll[5][7];
				} else if(tiles[x-1][y-1] == 0) {
					return scaledAll[3][7];
				}
				return scaledAll[4][7];
			} 
			return null;
		} else {
			if(tiles[x][y+1] == 0) {
				if(tiles[x+1][y] == 0) {
					return scaledAll[5][3];
				} else if(tiles[x-1][y] == 0) {
					return scaledAll[3][3];
				}
				return scaledAll[4][3];
			} else if(tiles[x][y-1] == 0) {
				if(tiles[x-1][y] == 0) {
					return scaledAll[3][1];
				} else if(tiles[x+1][y] == 0) {
					return scaledAll[5][1];
				}
				return scaledAll[4][1];
			} else if(tiles[x+1][y] == 0) {
				return scaledAll[5][2];
			} else if(tiles[x-1][y] == 0) {
				return scaledAll[3][2];
			} else  if(tiles[x][y+1] == 1) {
				if(tiles[x+1][y] == 1 && tiles[x+1][y+1] == 0) {
					return scaledAll[7][2];
				}
				else if(tiles[x-1][y] == 1 && tiles[x-1][y+1] == 0) {
					return scaledAll[6][2];
				}
			}
			if(tiles[x][y-1] == 1) {
				if(tiles[x-1][y] == 1 && tiles[x-1][y-1] == 0) {
					return scaledAll[6][1];
				}
				else if(tiles[x+1][y] == 1 && tiles[x+1][y-1] == 0) {
					return scaledAll[7][1];
				}
			}
			return scaledAll[1][0];
		} 	
	}
	
	public Darkness() {
		super();
		rand = new Random();
		backgroundColor = new Color(38, 30, 48, 255);
		this.xSize = Map.TILE_SIZE * Darkness.X_TILES;
		this.ySize = Map.TILE_SIZE * Darkness.Y_TILES;
		//enemyList.add(new Enemy());
		all = null;
		try {
			all = ImageIO.read(new File("res/darkness/ground.png"));
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		scaledAll = new Image[8][13];
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 13; y++) {
				scaledAll[x][y] = all.getSubimage(16 * x, 16 * y, 16, 16).getScaledInstance(Map.TILE_SIZE, Map.TILE_SIZE, Image.SCALE_DEFAULT);
			}
		}
		
		
		imageGrid = new Image[X_TILES][Y_TILES];
		DungeonGenerator d = new DungeonGenerator();
		tiles = d.generateBooleanMap(X_TILES, Y_TILES, 4);
		
		roomList = d.tree.getRoomList();
		currentRoom = d.tree.getLefternMostRoom();
		this.startingX = currentRoom.cx;
		this.startingY = currentRoom.cy;

		for(int x = 0; x < X_TILES; x++) {
			tiles[x][0] = 0;
		}
		for(int x = 0; x < X_TILES; x++) {
			tiles[x][Y_TILES-1] = 0;
		}
		for(int y = 0; y < X_TILES; y++) {
			tiles[0][y] = 0;
		}
		for(int y = 0; y < X_TILES; y++) {
			tiles[X_TILES-1][y] = 0;
		}
		
		
		for(int x = 0; x < X_TILES; x++) {
			for(int y = 0; y < Y_TILES; y++) {
				imageGrid[x][y] = this.getTexture(x, y);
			}
		}
		background = new Image[65][65];
		for (int x = 0; x < background.length; x++) {
			for(int y = 0; y < background[0].length; y++) {
				background[x][y] = this.randomSpace();
			}
		}
	}
	
	@Override
	public void draw(Graphics g, int xoffset, int yoffset) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, Constants.SCREEN_X, Constants.SCREEN_Y);
		for(int x = -2; x < background.length-2; x++) {
			for(int y = -2; y < background[0].length-2; y++) {
				g.drawImage(background[x+2][y+2], x * Map.TILE_SIZE*2 + xoffset/10, y * Map.TILE_SIZE*2 + yoffset / 10, 64, 64, null);
			}
		}
		for(int x = 0; x < Darkness.X_TILES; x++) {
			for(int y = 0; y < Darkness.Y_TILES; y++) {
				if(imageGrid[x][y] != null) {
					g.drawImage(imageGrid[x][y], x * Map.TILE_SIZE + xoffset, y * Map.TILE_SIZE + yoffset, null);
				}
			}
		}
		for(Room r : this.roomList) {
			r.draw(g, xoffset, yoffset);
		}
		if(this.currentRoom != null) {
			this.currentRoom.draw(g, xoffset, yoffset);
		}
		super.draw(g, xoffset, yoffset);
	}

	private void setActiveRoom(Player p) {
		if(currentRoom == null) {
			for(Room r : this.roomList) {
				if(r.hasPlayer(p)) {
					this.currentRoom = r;
					break;
				}
			}
		} else {
			if(currentRoom.hasPlayer(p)) {

			} else {
				currentRoom = null;
			}
		}
	}
	
	public void update(float deltaTime, Player p) {
		setActiveRoom(p);
		if(this.currentRoom != null) {
			//Iterator<Enemy> iter = this.currentRoom.enemies.iterator();
			ArrayList<Enemy> current = currentRoom.enemies;
			for(int i = 0; i < current.size(); i++){
				Enemy e = current.get(i);
				e.update(deltaTime, this);
				double xDistance = e.getX() - p.getX();
				double yDistance = e.getY() - p.getY();
				if(Math.sqrt(xDistance * xDistance + yDistance * yDistance)< e.visionRange) {
					e.active = true;
					this.activeEnemies.add(current.remove(i));
					i--;
				} else {
					e.active = false;
				}
			}
		}
		for(Enemy e : this.activeEnemies){
			e.updateActive(deltaTime, this, p);
		}
	}
	
	@Override
	public double getStartingX() {
		return startingX * Map.TILE_SIZE;
	}
	
	@Override
	public double getStartingY() {
		return startingY * Map.TILE_SIZE;
	}
	
	@Override
	public boolean isCorrectPosition(double x, double y, double radius) {
		if(!super.isCorrectPosition(x, y, radius)) {
			return false;
		}
		if(this.tiles[(int) x / Map.TILE_SIZE][(int) (y+Map.TILE_SIZE/4) / Map.TILE_SIZE]==0 || this.tiles[(int) x / Map.TILE_SIZE][(int) (y) / Map.TILE_SIZE]==0) {
			return false;
		}
		return true;
	}
	
	public boolean isCorrectPosition(double x, double y, double width, double height) {
		if(!super.isCorrectPosition(x, y, width)) {
			return false;
		}
		if(this.tiles[(int) (x+0.25*width) / Map.TILE_SIZE][(int) (y+height) / Map.TILE_SIZE]==0 || this.tiles[(int) ((x+0.75*width)) / Map.TILE_SIZE][(int) (y+height) / Map.TILE_SIZE]==0 
				|| this.tiles[(int) (x+0.25*width) / Map.TILE_SIZE][(int) (y+0.75*height) / Map.TILE_SIZE]==0 || this.tiles[(int) (x+0.75*width) / Map.TILE_SIZE][(int) (y+0.75*height) / Map.TILE_SIZE]==0 ) {
			return false;
		}
		return true;
	}
}
