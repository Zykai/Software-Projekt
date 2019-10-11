package Inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import Inventory.Equippable.ArmorType;
import Player.Player;

public class Inventory {

    private static Image menuBackground;
    private static int XOFFSET = 200, YOFFSET = 100;
    private static Color hoverColor = new Color(0,9,26, 220);

    private Slot[] activeSlots;
    private Slot[] storageSlots;

    private boolean isVisible;
    private int scroll;
    private int width, height;

    private boolean draggedFromActive;
    private Slot dragged;
    private int dragX, dragY, dragOffsetX, dragOffsetY;

    private Slot hover;
    private int hoverX, hoverY;

    static{
        try {
            menuBackground = ImageIO.read(new File("res/dsui/menu_only_bg.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    enum ItemType{
        weapon(0),
        ring(1),
        helmet(2),
        plate(3),
        gauntlet(4),
        boots(5),
        potion(6),
        all(7);

        public final int value;
        private ItemType(int value){
            this.value = value;
        }
        public static ItemType fromArmorType(ArmorType t){
            switch(t){
                case helmet:
                    return ItemType.helmet;
                case plate:
                    return ItemType.plate;
                case gauntlet:
                    return ItemType.gauntlet;
                case boots:
                    return ItemType.boots;
                default:
                    // Should never happen
                    System.exit(0);
                    return ItemType.all;
            }
        }
    }

    public Inventory(){
        activeSlots = new Slot[8];
        activeSlots[0] = new Slot(50 + 0 * 160, 50 + 0 * 160, ItemType.weapon);
        activeSlots[1] = new Slot(50 + 1 * 160, 50 + 0 * 160, ItemType.ring);
        activeSlots[2] = new Slot(50 + 0 * 160, 50 + 1 * 160, ItemType.helmet);
        activeSlots[3] = new Slot(50 + 1 * 160, 50 + 1 * 160, ItemType.plate);
        activeSlots[4] = new Slot(50 + 0 * 160, 50 + 2 * 160, ItemType.gauntlet);
        activeSlots[5] = new Slot(50 + 1 * 160, 50 + 2 * 160, ItemType.boots);
        activeSlots[6] = new Slot(50 + 0 * 160, 50 + 3 * 160, ItemType.potion);
        activeSlots[7] = new Slot(50 + 1 * 160, 50 + 3 * 160, ItemType.potion);

        storageSlots = new Slot[42];
        int i = 0;
        for(int y = 0; y < 14; y++){
            for(int x = 0; x < 3; x++){
                storageSlots[i] = new Slot(600+x*160, 50+y*160, ItemType.all);
                storageSlots[i].item = new Equippable();
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
        if(dragged != null && !draggedFromActive){
            dragged.drawOnlySlot(g);
        }
        g.translate(0, -scroll);

        if(dragged != null && draggedFromActive){
            dragged.drawOnlySlot(g);
        }
        if(dragged != null){
            g.drawImage(dragged.item.image, dragX, dragY, null);
        }

        g.translate(-XOFFSET, -YOFFSET);

        if(hover != null){
            g.setColor(hoverColor);
            g.fillRect(hoverX, hoverY, 200, 200);
        }
        g.setClip(null);
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
                draggedFromActive = true;
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
                draggedFromActive = false;
                return;
            }
        }   
    }
    
    public void continueDrag(int x, int y){
        dragX = x - XOFFSET - dragOffsetX;
        dragY = y - YOFFSET - dragOffsetY;
    }

    public void endDrag(int x, int y, Player p){
        boolean toActive = false;
        Slot goalSlot = null;
        for(int i = 0; i < activeSlots.length; i++){
            Slot active = activeSlots[i];
            if(active.isClicked(x - XOFFSET, y - YOFFSET)){
                goalSlot = active;
                toActive = true;
            }
        }
        for(int i = 0; i < storageSlots.length; i++){
            Slot active = storageSlots[i];
            if(active.isClicked(x - XOFFSET, y - YOFFSET - scroll)){
                goalSlot = active;
            }
        }
        if(goalSlot.isCompatible(dragged.item) && dragged.isCompatible(goalSlot.item)){
            if(draggedFromActive){
                dragged.item.deEquip(p);
                if(goalSlot.item != null){
                    goalSlot.item.equip(p);
                }
            }
            if(toActive){
                if(goalSlot.item != null){
                    goalSlot.item.deEquip(p);
                }
                dragged.item.equip(p);
            }
            Item temp = goalSlot.item;
            goalSlot.item = dragged.item;
            dragged.item = temp;
            
        }
        dragged = null;
    }

	public void hover(int x, int y) {
        for(int i = 0; i < activeSlots.length; i++){
            Slot active = activeSlots[i];
            if(active.isClicked(x - XOFFSET, y - YOFFSET) && active.item != null){
                hover = active;
                hoverX = x;
                hoverY = y;
                return;
            }
        }
        for(int i = 0; i < storageSlots.length; i++){
            Slot active = storageSlots[i];
            if(active.isClicked(x - XOFFSET, y - YOFFSET - scroll) && active.item != null){
                hover = active;
                hoverX = x;
                hoverY = y;
                return;
            }
        } 
        hover = null;
	}
}