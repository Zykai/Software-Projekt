package Inventory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import Frames.FrameManager;
import Inventory.Equippable.ArmorType;
import Player.Player;

public class Inventory {

    private static Image menuBackground;
    private static int STARTING_X, STARTING_Y;
    public static Color xpColor = new Color(209, 91, 36);
    public static Color textColor = new Color(204, 204, 204);
    public static Font cursive = new Font("Yu Gothic", Font.ITALIC, 26);
    public static Font font = new Font("TimesRoman", Font.PLAIN, 26);
    public static Font bigFont = new Font("TimesRoman", Font.PLAIN, 52);

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
    
    static {
        try {
            menuBackground = ImageIO.read(new File("res/dsui/menu_only_bg.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    enum ItemType {
        weapon(0), ring(1), helmet(2), plate(3), gauntlet(4), boots(5), potion(6), all(7);

        public final int value;

        private ItemType(int value) {
            this.value = value;
        }

        public static ItemType fromArmorType(ArmorType t) {
            switch (t) {
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

    public Inventory() {
    	int yoff = (int) ((FrameManager.frame.getHeight() - Inventory.menuBackground.getHeight(null) * 1.5) / 2);
    	int xoff = (int) ((FrameManager.frame.getWidth() - Inventory.menuBackground.getWidth(null) * 1.7) / 2);
        activeSlots = new Slot[8];
        STARTING_X = xoff;
        STARTING_Y = yoff;
        int storage_x = STARTING_X + 530;
        int storage_y = STARTING_Y + 100;
        activeSlots[0] = new Slot(storage_x + 0 * 160, storage_y + 0 * 160, ItemType.weapon);
        activeSlots[1] = new Slot(storage_x + 1 * 160, storage_y + 0 * 160, ItemType.ring);
        activeSlots[2] = new Slot(storage_x + 0 * 160, storage_y + 1 * 160, ItemType.helmet);
        activeSlots[3] = new Slot(storage_x + 1 * 160, storage_y + 1 * 160, ItemType.plate);
        activeSlots[4] = new Slot(storage_x + 0 * 160, storage_y + 2 * 160, ItemType.gauntlet);
        activeSlots[5] = new Slot(storage_x + 1 * 160, storage_y + 2 * 160, ItemType.boots);
        activeSlots[6] = new Slot(storage_x + 0 * 160, storage_y + 3 * 160, ItemType.potion);
        activeSlots[7] = new Slot(storage_x + 1 * 160, storage_y + 3 * 160, ItemType.potion);

        storageSlots = new Slot[42];
        int i = 0;
        for (int y = 0; y < 14; y++) {
            for (int x = 0; x < 3; x++) {
                storageSlots[i] = new Slot(storage_x + 350 + x * 160, y + 50 + y * 160, ItemType.all);
                i++;
            }
        }
        width = (int) (menuBackground.getWidth(null) * 1.7);
        height = (int) (menuBackground.getHeight(null) * 1.5);
        scroll = 0;
    }

    public void scroll(int amount) {
        this.scroll += amount * -10;
    }

    public void draw(Graphics g, Player p) {
        if (!isVisible) {
            return;
        }

        g.drawImage(menuBackground, STARTING_X, STARTING_Y, width, height, null);
        
        g.translate(STARTING_X, STARTING_Y);
        String as = String.format("%.2f", p.attackSpeed);
        g.setColor(textColor);
        g.setFont(bigFont);
        g.drawString("Stats 📝", 150, 70);
        g.setFont(font);
        g.drawString("Level 🌡: " + p.level, 40, 120);
        g.fillRect(220, 95, 266, 26);
        g.setColor(xpColor);
        g.fillRect(224, 99, 258 * p.currentXP / p.maxXP, 18);
        g.setColor(textColor);
        g.drawString("Leben ❤: " + p.currentHP + " / " + p.maxHP, 40, 160);
        g.drawString("Lebensregeneration ➕: " + p.hpRegen + "/s", 40, 200);
        g.drawString("Rüstung 🛡: " + p.armor, 40, 240);
        g.drawString("Angriffschaden ⚔: " + p.attackDamage, 40, 280);
        g.drawString("Fähigkeitsstärke 🌠: " + p.abilityPower, 40, 320);
        g.drawString("Chance kritischer Treffer ⚠: " + p.critChance + "%", 40, 360);
        g.drawString("Lebensraub 🦇: " + p.lifeSteal, 40, 400);
        g.drawString("Abklingzeitverringerung ⌛: " + p.coolDownReduction + "%", 40, 440);
        g.drawString("Angriffsgeschwindigkeit 🗡: " + as, 40, 480);
        g.drawString("Bewegungsgeschwindigkeit 🏃: " + p.movespeed, 40, 520);
        g.drawString("Erfahrungsbonus ☀: " + p.xpBoost + "%", 40, 560);
        g.translate(-STARTING_X, -STARTING_Y);
        
        for (int i = 0; i < activeSlots.length; i++) {
            activeSlots[i].draw(g);
        }

        g.setClip(0, 50, width, height - 120);
        g.translate(0, scroll);
        for (int i = 0; i < storageSlots.length; i++) {
            storageSlots[i].draw(g);
        }
        if (dragged != null && !draggedFromActive) {
            dragged.drawOnlySlot(g);
        }
        g.translate(0, -scroll);

        if (dragged != null && draggedFromActive) {
            dragged.drawOnlySlot(g);
        }

        g.setClip(null);

        if (dragged != null) {
            g.drawImage(dragged.item.image, dragX, dragY, null);
        }

        if (hover != null && hover.item != null) {
            hover.item.draw(g, hoverX + 10, hoverY + 50);
        }
    }

    public Inventory(String name, Player player) {
        this();
        File file = new File("saves/"+name+"/inventory.csv");
        if (!file.exists()) {
            return;
        }
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            for (int i = 0; i < activeSlots.length; i++) {
                try {
                    String line = br.readLine();
                    if(line.equals("null")){
                        continue;
                    }
                    String[] values = line.split("/");
                    if(values.length == 3){
                        this.activeSlots[i].item = new Item(values);
                    } else {
                        Equippable e = new Equippable(values);
                        e.equip(player);
                        this.activeSlots[i].item = e;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
            for (int i = 0; i < storageSlots.length; i++) {
                try {
                    String line = br.readLine();
                    if(line.equals("null")){
                        continue;
                    }
                    String[] values = line.split("/");
                    if(values.length == 3){
                        this.storageSlots[i].item = new Item(values);
                    } else {
                        Equippable e = new Equippable(values);
                        e.equip(player);
                        this.storageSlots[i].item = e;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        
    }

    public void saveInventory(String name) {
        try {
            File file = new File("saves/"+name+"/inventory.csv");
            file.getParentFile().mkdirs();
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            for(int i = 0; i < this.activeSlots.length; i++){
                Slot current = activeSlots[i];
                if(current.item != null){
                    writer.println(current.item.toCSV());
                } else {
                    writer.println("null");
                }
            }
            for(int i = 0; i < this.storageSlots.length; i++){
                Slot current = storageSlots[i];
                if(current.item != null){
                    writer.println(current.item.toCSV());
                } else {
                    writer.println("null");
                }
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    public void setInventory() {
		this.isVisible = !this.isVisible;
	}

    public boolean isVisible(){
        return this.isVisible;
    }

    public boolean addItem(Item item){
        for(int i = 0; i < this.storageSlots.length; i++){
            if(this.storageSlots[i].item == null){
                this.storageSlots[i].item = item;
                return true;
            }
        }
        return false;
    }

	public void startDrag(int x, int y) {
        for(int i = 0; i < activeSlots.length; i++){
            Slot active = activeSlots[i];
            if(active.isClicked(x, y) && active.item != null){
                dragged = active;
                dragOffsetX = x - active.x;
                dragOffsetY = y - active.y;
                dragX = x - dragOffsetX;
                dragY = y - dragOffsetY;
                draggedFromActive = true;
                return;
            }
        }
        for(int i = 0; i < storageSlots.length; i++){
            Slot active = storageSlots[i];
            if(active.isClicked(x, y - scroll) && active.item != null){
                dragged = active;
                dragOffsetX = x - active.x;
                dragOffsetY = y - active.y - scroll;
                dragX = x - dragOffsetX;
                dragY = y - dragOffsetY;
                draggedFromActive = false;
                return;
            }
        }   
    }
    
    public void continueDrag(int x, int y){
        hoverX = x;
        hoverY = y;
        dragX = x - dragOffsetX;
        dragY = y - dragOffsetY;
    }

    public void endDrag(int x, int y, Player p){
        boolean toActive = false;
        Slot goalSlot = null;
        if(dragged == null){
            return;
        }
        for(int i = 0; i < activeSlots.length; i++){
            Slot active = activeSlots[i];
            if(active.isClicked(x, y)){
                goalSlot = active;
                toActive = true;
            }
        }
        for(int i = 0; i < storageSlots.length; i++){
            Slot active = storageSlots[i];
            if(active.isClicked(x, y - scroll)){
                goalSlot = active;
            }
        }
        if(goalSlot == null){
            dragged.item = null;
            dragged = null;
            return;
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
            hover = goalSlot;
        }
        dragged = null;
    }

	public void hover(int x, int y) {
        for(int i = 0; i < activeSlots.length; i++){
            Slot active = activeSlots[i];
            if(active.isClicked(x, y) && active.item != null){
                hover = active;
                hoverX = x;
                hoverY = y;
                return;
            }
        }
        for(int i = 0; i < storageSlots.length; i++){
            Slot active = storageSlots[i];
            if(active.isClicked(x, y - scroll) && active.item != null){
                hover = active;
                hoverX = x;
                hoverY = y;
                return;
            }
        } 
        hover = null;
	}
}