package Inventory;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Inventory.Inventory.ItemType;

public class Slot {
    private static Image[] menuSlots;
    
    public int x, y;
    private ItemType type;
    public Item item;

    static{
        try {
            menuSlots = new Image[8];
            BufferedImage slotImage = ImageIO.read(new File("res/dsui/menu_slots.png"));
            menuSlots[0] = slotImage.getSubimage(0, 0, 160, 160);
            menuSlots[1] = slotImage.getSubimage(2*160, 0, 160, 160);
            menuSlots[2] = slotImage.getSubimage(0, 1*160, 160, 160);
            menuSlots[3] = slotImage.getSubimage(1*160, 1*160, 160, 160);
            menuSlots[4] = slotImage.getSubimage(2*160, 1*160, 160, 160);
            menuSlots[5] = slotImage.getSubimage(3*160, 1*160, 160, 160);
            menuSlots[6] = slotImage.getSubimage(5*160, 0, 160, 160);
            menuSlots[7] = slotImage.getSubimage(5*160, 1*160, 160, 160);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public Slot(int x, int y, ItemType type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

	public void draw(Graphics g) {
        if(item != null){
            g.drawImage(menuSlots[7], x, y, null);
            g.drawImage(item.image, x, y, null);
        } else {
            g.drawImage(menuSlots[type.value], x, y, null);

        }
    }
    
    public boolean isCompatible(Item i){
        if(i == null) {
            return true;
        }
        return this.type == ItemType.All || i.type == this.type;
    }

    public boolean isClicked(int x, int y){
        return x < this.x + 160 && x > this.x && y < this.y + 160 && y > this.y;
    }
}