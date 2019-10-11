package Inventory;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Constants.Constants;
import Inventory.Inventory.ItemType;
import Player.Player;

public class Equippable extends Item {

    public static Weight[] WEIGHTS = Weight.values();
    public static ArmorType[] ARMORTYPES = ArmorType.values();
    public static Image[][][] armorImages;

    public Weight weight;

    public int hpBonus = 10;
	public int armorBonus;
	public int attackBonus;
    public int abilityBonus;
    public int lifeStealBonus;
	public int coolDownReductionBonus;
	public double attackSpeedBonus;
	public float movespeedBonus;

    static {
        armorImages = new Image[4][3][4];
        for (Rarity r : Rarity.values()) {
            for (Weight w : Weight.values()) {
                for (ArmorType t : ArmorType.values()) {
                    String fileName = w + "_" + r + "_" + t;
                    try {
                        armorImages[r.ordinal()][w.ordinal()][t.ordinal()] = ImageIO.read(new File("res/dsui/armor/" + fileName + ".png")).getScaledInstance(160, 160, Image.SCALE_DEFAULT);
                    } catch (IOException e) {
                        System.out.println("Could not read file: " + fileName);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Image getArmorImage(Rarity r, Weight w, ArmorType t){
        return armorImages[r.ordinal()][w.ordinal()][t.ordinal()];
    }

    public enum Weight{
        light, normal, heavy,
    }

    public enum ArmorType{
        helmet, plate, gauntlet, boots,
    }

    public Equippable(){
        this.rarity = Item.RARITIES[Constants.random(0, Item.RARITIES.length-1)];
        this.weight = Equippable.WEIGHTS[Constants.random(0, Equippable.WEIGHTS.length-1)];
        ArmorType armorType = Equippable.ARMORTYPES[Constants.random(0, ARMORTYPES.length-1)];
        this.image = getArmorImage(this.rarity, this.weight, armorType);
        this.type = ItemType.fromArmorType(armorType);
        this.name = rarity + " " + weight + " " + armorType;
        if(Math.random()>0.5){
            this.hpBonus = 10;
        }
        if(Math.random()>0.5){
            this.armorBonus = 10;
        }
        if(Math.random()>0.5){
            this.attackBonus = 10;
        }
        if(Math.random()>0.5){
            this.abilityBonus = 10;
        }
        if(Math.random()>0.5){
            this.lifeStealBonus = 10;
        }
        if(Math.random()>0.5){
            this.coolDownReductionBonus = 10;
        }
        if(Math.random()>0.5){
            this.attackSpeedBonus = 10;
        }
        if(Math.random()>0.5){
            this.movespeedBonus = 10;
        }
    }

    @Override
    public void equip(Player p){
        p.maxHP += this.hpBonus;
        p.armor += this.armorBonus;
        p.attackDamage += this.attackBonus;
        p.abilityPower += this.abilityBonus;
        p.lifeSteal += this.lifeStealBonus;
        p.coolDownReduction += this.coolDownReductionBonus;
        p.attackSpeed += this.attackSpeedBonus;
        p.movespeed += this.movespeedBonus;
    }

    @Override
    public void deEquip(Player p){
        p.maxHP -= this.hpBonus;
        p.armor -= this.armorBonus;
        p.attackDamage -= this.attackBonus;
        p.abilityPower -= this.abilityBonus;
        p.lifeSteal -= this.lifeStealBonus;
        p.coolDownReduction -= this.coolDownReductionBonus;
        p.attackSpeed -= this.attackSpeedBonus;
        p.movespeed -= this.movespeedBonus;
    }

    @Override
    protected int getRows(){
        int rows = 0;
        if(hpBonus != 0){
           rows++;
        }
        if(armorBonus != 0){
            rows++;
        }
        if(attackBonus != 0){
            rows++;
        }
        if(abilityBonus != 0){
            rows++;
        }
        if(lifeStealBonus != 0){
            rows++;
        }
        if(coolDownReductionBonus != 0){
            rows++;
        }
        if(attackSpeedBonus != 0){
            rows++;
        }
        if(movespeedBonus != 0){
            rows++;
        }
        return rows;
    }

    private String getBonusString(int value){
        if (value>0){
            return "+" + value;
        }
        return ""+value;
    }

    @Override
    public void draw(Graphics g, int hoverX, int hoverY){
        super.draw(g, hoverX, hoverY);
        g.setColor(Inventory.textColor);
        g.setFont(Inventory.font);
        int yoffset = hoverY + 70;
        if(hpBonus != 0){
            g.drawString(getBonusString(hpBonus) + " Leben", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(armorBonus != 0){
            g.drawString(getBonusString(armorBonus) + " Rüstung", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(attackBonus != 0){
            g.drawString(getBonusString(attackBonus) + " Angriffsschaden", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(abilityBonus != 0){
            g.drawString(getBonusString(abilityBonus) + " Fähigkeitsstärke", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(lifeStealBonus != 0){
            g.drawString(getBonusString(lifeStealBonus) + " Lebensraub", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(coolDownReductionBonus != 0){
            g.drawString(getBonusString(coolDownReductionBonus) + " Abklingzeitverringerung", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(attackSpeedBonus != 0){
            g.drawString(getBonusString((int)attackSpeedBonus) + " Angriffsgeschwindigkeit", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(movespeedBonus != 0){
            g.drawString(getBonusString((int)movespeedBonus) + " Bewegungsgeschwindigkeit", hoverX+10, yoffset);
            yoffset += 32;
        }
    }

}