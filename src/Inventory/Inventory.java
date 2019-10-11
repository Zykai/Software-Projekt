package Inventory;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Inventory {

    private static Image menuBackground;
    private static int XOFFSET = 200, YOFFSET = 100;

    private Slot[] activeSlots;
    private Slot[] storageSlots;

    private boolean isVisible;
    private int scroll;
    private int width, height;

    private Slot dragged;
    private int dragX, dragY, dragOffsetX, dragOffsetY;

    static{
        try {
            menuBackground = ImageIO.read(new File("res/dsui/menu_only_bg.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    enum ItemType{
        Weapon(0),
        Ring(1),
        Helmet(2),
        Breastplate(3),
        Gauntlets(4),
        Boots(5),
        Potion(6),
        All(7);

        public final int value;
        private ItemType(int value){
            this.value = value;
        }
    }

    public Inventory(){
        activeSlots = new Slot[8];
        activeSlots[0] = new Slot(50 + 0 * 160, 50 + 0 * 160, ItemType.Weapon);
        activeSlots[1] = new Slot(50 + 1 * 160, 50 + 0 * 160, ItemType.Ring);
        activeSlots[2] = new Slot(50 + 0 * 160, 50 + 1 * 160, ItemType.Helmet);
        activeSlots[3] = new Slot(50 + 1 * 160, 50 + 1 * 160, ItemType.Breastplate);
        activeSlots[4] = new Slot(50 + 0 * 160, 50 + 2 * 160, ItemType.Gauntlets);
        activeSlots[5] = new Slot(50 + 1 * 160, 50 + 2 * 160, ItemType.Boots);
        activeSlots[6] = new Slot(50 + 0 * 160, 50 + 3 * 160, ItemType.Potion);
        activeSlots[7] = new Slot(50 + 1 * 160, 50 + 3 * 160, ItemType.Potion);
        activeSlots[2].item = new Item();

        storageSlots = new Slot[42];
        int i = 0;
        for(int y = 0; y < 14; y++){
            for(int x = 0; x < 3; x++){
                storageSlots[i] = new Slot(600+x*160, 50+y*160, ItemType.All);
                i++;
            }
        }
        width = (int)(menuBackground.getWidth(null) * 1.5);
        height = (int)(menuBackground.getHeight(null) * 1.5);
        scroll = 0;
    }

    public void scroll(int amount){
        this.scroll += amount * -10;
    }

    public void draw(Graphics g){
        if(!isVisible){
            return;
        }
        g.translate(XOFFSET, YOFFSET);

        g.drawImage(menuBackground, 0, 0, width, height, null);
        for(int i = 0; i < activeSlots.length; i++){
            activeSlots[i].draw(g);
        }
        
        g.setClip(0,50, width, height-120);
        g.translate(0, scroll);
        for(int i = 0; i < storageSlots.length; i++){
            storageSlots[i].draw(g);
        }
        g.translate(0, -scroll);

        if(dragged != null){
            g.drawImage(dragged.item.image, dragX, dragY, null);
        }

        g.translate(-XOFFSET, -YOFFSET);
    }

    public void setInventory() {
		this.isVisible = !this.isVisible;
	}

    public boolean isVisible(){
        return this.isVisible;
    }

	public void startDrag(int x, int y) {
        for(int i = 0; i < activeSlots.length; i++){
            Slot active = activeSlots[i];
            if(active.isClicked(x - XOFFSET, y - YOFFSET)){
                dragged = active;
                dragOffsetX = x - XOFFSET - active.x;
                dragOffsetY = y - YOFFSET - active.y;
                dragX = x - XOFFSET - dragOffsetX;
                dragY = y - YOFFSET - dragOffsetY;
                return;
            }
        }
        for(int i = 0; i < storageSlots.length; i++){
            Slot active = storageSlots[i];
            if(active.isClicked(x - XOFFSET, y - YOFFSET - scroll)){
                dragged = active;
                dragOffsetX = x - XOFFSET - active.x;
                dragOffsetY = y - YOFFSET - active.y - scroll;
                dragX = x - XOFFSET - dragOffsetX;
                dragY = y - YOFFSET - dragOffsetY;
                return;
            }
        }   
    }
    
    public void continueDrag(int x, int y){
        dragX = x - XOFFSET - dragOffsetX;
        dragY = y - YOFFSET - dragOffsetY;
    }

    public void endDrag(int x, int y){
        Slot goalSlot = null;
        for(int i = 0; i < activeSlots.length; i++){
            Slot active = activeSlots[i];
            if(active.isClicked(x - XOFFSET, y - YOFFSET)){
                goalSlot = active;
            }
        }
        for(int i = 0; i < storageSlots.length; i++){
            Slot active = storageSlots[i];
            if(active.isClicked(x - XOFFSET, y - YOFFSET - scroll)){
                goalSlot = active;
            }
        }
        if(goalSlot.isCompatible(dragged.item) && dragged.isCompatible(goalSlot.item)){
            Item temp = goalSlot.item;
            goalSlot.item = dragged.item;
            dragged.item = temp;
        }
        dragged = null;
    }
}