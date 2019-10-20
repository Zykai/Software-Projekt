package Inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Inventory.Inventory.ItemType;
import Player.Player;

public class Item {

    public static Rarity[] RARITIES = Rarity.values();
    private static Color commonColor = new Color(124, 132, 145);
    private static Color rareColor = new Color(119, 221, 237);
    private static Color epicColor = new Color(137, 55, 191);
    private static Color legendaryColor = new Color(189, 191, 55);
    private static Color commonColorTransparent = new Color(124, 132, 145, 128);
    private static Color rareColorTransparent = new Color(119, 221, 237, 128);
    private static Color epicColorTransparent = new Color(137, 55, 191, 128);
    private static Color legendaryColorTransparent = new Color(189, 191, 55, 128);
    private static Color hoverColor = new Color(0,9,26, 220);

    public enum Rarity{
        common, rare, epic, legendary,
    }

    public Rarity rarity;
    public ItemType type;
    public Image image;
    protected String name;

    public Item() {
        type = ItemType.helmet;
        try {
            image = ImageIO.read(new File("res/dsui/item_helmet.png")).getScaledInstance(160, 160, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        name = "Item";
    }

    public String getName(){
        return name;
    }

    public void equip(Player p){    }

    public void deEquip(Player p){    }

    public Color getColor(){
        switch(this.rarity){
            case common:
                return commonColor;
            case rare:
                return rareColor;
            case epic:
                return epicColor;
            case legendary:
                return legendaryColor;
            default:
                return commonColor;
        }
    }

    public Color getColorTransparent(){
        switch(this.rarity){
            case common:
                return commonColorTransparent;
            case rare:
                return rareColorTransparent;
            case epic:
                return epicColorTransparent;
            case legendary:
                return legendaryColorTransparent;
            default:
                return commonColor;
        }
    }

    protected int getRows(){
        return 0;
    }

	public void draw(Graphics g, int hoverX, int hoverY){
        g.setColor(Item.hoverColor);
        g.fillRect(hoverX, hoverY, 430, 60 + getRows() * 32);
        g.setFont(Inventory.cursive);
        g.setColor(getColor());
        g.drawString(getName(), hoverX + 10, hoverY + 30);
	}
}