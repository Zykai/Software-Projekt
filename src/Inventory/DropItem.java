package Inventory;

import java.awt.Graphics;

import Constants.Constants;

public class DropItem {

    public Item item;
    private double xPosition;
    private double yPosition;

    private static int SIZE = 60;

    private float age;

    public DropItem(double xPosition, double yPosition){
        this.xPosition = xPosition + Constants.random(-50, 50);
        this.yPosition = yPosition + Constants.random(-50, 50);
        this.item = new Equippable();
        this.age = 0;
    }

    public void draw(Graphics g, int xoffset, int yoffset){
        g.setColor(item.getColorTransparent());
        g.fillRect((int)(this.xPosition+xoffset), (int)(this.yPosition+yoffset), SIZE, SIZE);
        g.setColor(item.getColor());
        g.drawRect((int)(this.xPosition+xoffset), (int)(this.yPosition+yoffset), SIZE, SIZE);
        g.drawImage(this.item.image, (int)(this.xPosition+xoffset), (int)(this.yPosition+yoffset), SIZE, SIZE, null);
    }

    public boolean update(float deltaTime){
        this.age += deltaTime;
        return age > 10000;
    }

    public boolean intersects(double x, double y){
        return x > this.xPosition && x < this.xPosition + SIZE && y > this.yPosition && y < this.yPosition + SIZE;
    }

}