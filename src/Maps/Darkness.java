package Maps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

import Constants.Constants;
import Enemies.Enemy;

public class Darkness extends Map {

	private static int X_TILES = 60;
	private static int Y_TILES = 50;
	private BufferedImage all;
	private Image[][] scaledAll;
	private Image[] ground;
	private Image background;
	
	private static int VOID = 0;
	private static int GROUND;
	
	private int[][] tiles;
	private Image[][] imageGrid;
	
	private Random rand;
	private Color backgroundColor;
	
	private Image randomSpace() {
		return scaledAll[rand.nextInt(2)][rand.nextInt(4)+9];
	}
	
	private Image getTexture(int x, int y) {
		if (x >= X_TILES || x == 0 || y >= Y_TILES || y == 0) {
			return randomSpace();
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
			return randomSpace();
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
		
		ground = new Image[7];
		
		for(int i = 0; i < 7; i++) {
			ground[i] = all.getSubimage(i * 16 + 16, 0, 16, 16).getScaledInstance(Map.TILE_SIZE, Map.TILE_SIZE, Image.SCALE_DEFAULT);
		}
		background = all.getSubimage(0, 16*9, 128, 16 * 3).getScaledInstance(Map.TILE_SIZE * 8, Map.TILE_SIZE * 3, Image.SCALE_DEFAULT);
		
		tiles = new int[X_TILES][Y_TILES];
		imageGrid = new Image[X_TILES][Y_TILES];
		for(int x = 0; x < X_TILES; x++) {
			for(int y = 0; y < Y_TILES; y++) {
				if(x > 35 && x < 45 && y > 25 && y < 35) {
					tiles[x][y] = 1;
				} else {
					tiles[x][y] = 0;
				}
			}
		}
		for(int x = 4; x < 35; x++) {
			for(int y = 10; y <  25; y++) {
				tiles[x][y] = 1;
			}
		}
		for(int x = 35; x < 39; x++) {
			for(int y = 17; y < 23; y++) {
				tiles[x][y] = 1;
			}
		}
		for(int x = 35; x < 40; x++) {
			for(int y = 17; y < 23; y++) {
				tiles[x][y] = 1;
			}
		}
		for(int x = 39; x < 41; x++) {
			for(int y = 21; y < 27; y++) {
				tiles[x][y] = 1;
			}
		}
		for(int x = 10; x < 30; x++) {
			tiles[x][4] = 1;
		}
		for(int x = 10; x < 30; x++) {
			tiles[x][5] = 1;
		}
		for(int x = 10; x < 30; x++) {
			tiles[x][7] = 1;
		}
		for(int x = 10; x < 30; x++) {
			tiles[x][8] = 1;
		}
		for(int x = 4; x < 30; x++) {
			tiles[10][x] = 1;
		}
		for(int x = 5; x < 30; x++) {
			tiles[11][x] = 1;
		}
		for(int x = 0; x < X_TILES; x++) {
			for(int y = 0; y < Y_TILES; y++) {
				imageGrid[x][y] = this.getTexture(x, y);
			}
		}
	}
	
	@Override
	public void draw(Graphics g, int xoffset, int yoffset) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, Constants.SCREEN_X, Constants.SCREEN_Y);
		for(int x = 0; x < Darkness.X_TILES; x++) {
			for(int y = 0; y < Darkness.Y_TILES; y++) {
				g.drawImage(imageGrid[x][y], x * Map.TILE_SIZE + xoffset, y * Map.TILE_SIZE + yoffset, null);
			}
		}
		super.draw(g, xoffset, yoffset);
	}

	@Override
	public void update(float deltaTime) {
		for(Iterator<Enemy> i = enemyList.iterator(); i.hasNext();) {
			Enemy e = i.next();
			e.update(deltaTime, this);
		}
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
		if(this.tiles[(int) (x-width/2) / Map.TILE_SIZE][(int) (y+height/2+Map.TILE_SIZE / 8) / Map.TILE_SIZE]==0 || this.tiles[(int) (x+width/2) / Map.TILE_SIZE][(int) (y+height/2+Map.TILE_SIZE / 8) / Map.TILE_SIZE]==0 
				|| this.tiles[(int) (x-width/2) / Map.TILE_SIZE][(int) (y-height/8) / Map.TILE_SIZE]==0 || this.tiles[(int) (x+width/2) / Map.TILE_SIZE][(int) (y-height/8) / Map.TILE_SIZE]==0 ) {
			return false;
		}
		return true;
	}
}
