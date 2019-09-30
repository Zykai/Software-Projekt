package DungeonGenerator;

import java.util.Random;

import Constants.Constants;

public class DungeonGenerator {
	
	private Random rand;
	
	private float verticalRatio;
	private float horizontalRatio;
	
	public RoomTree tree;
	
	public DungeonGenerator() {
		rand = new Random();
		verticalRatio = 0.45f;
		horizontalRatio = 0.45f;
	}
	
	public RoomTree splitContainer(RoomBox c, int iter) {
		RoomTree root = new RoomTree(c);
		if(iter != 0) {
			RoomBox[] sr = randomSplit(c);
			root.left = splitContainer(sr[0], iter-1);
			root.right = splitContainer(sr[1], iter-1);
		}
		return root;
	}
	
	public RoomBox[] randomSplit(RoomBox c) {
		RoomBox c1, c2;
		if(rand.nextBoolean()) {
			// Vertical
			c1 = new RoomBox(c.x, c.y, Constants.random(c.width / 3, c.width), c.height);
			c2 = new RoomBox(c.x + c1.width, c.y, c.width- c1.width, c.height);
			float c1Ratio = (float)c1.width / (float)c1.height;
			float c2Ratio = (float)c2.width / (float)c2.height;
			if(c1Ratio < verticalRatio || c2Ratio < verticalRatio) {
				return randomSplit(c);
			}
		} else {
			// Horizontal
			c1 = new RoomBox(c.x, c.y, c.width, Constants.random(c.height / 3, c.height));
			c2 = new RoomBox(c.x, c.y+c1.height, c.width, c.height - c1.height);
			float c1Ratio = (float)c1.height / (float)c1.width;
			float c2Ratio = (float)c2.height / (float)c2.width;
			if(c1Ratio < horizontalRatio || c2Ratio < horizontalRatio) {
				return randomSplit(c);
			}
		}
		RoomBox res[] = new RoomBox[2];
		res[0] = c1;
		res[1] = c2;
		return res;
	}

	public int[][] generateBooleanMap(int width, int height, int levels) {
		int[][] grid = new int[width][height];
		RoomBox b = new RoomBox(0,0,width, height);
		tree = splitContainer(b, levels);
		tree.fillGrid(grid);
		return grid;
	}
	
}
