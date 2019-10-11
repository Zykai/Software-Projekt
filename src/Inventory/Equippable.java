package Inventory;

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

    public Rarity rarity;
    public Weight weight;

    public int hpBonus;
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

}