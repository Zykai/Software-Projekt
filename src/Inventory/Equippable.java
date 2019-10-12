package Inventory;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.imageio.ImageIO;

import Constants.Constants;
import Inventory.Inventory.ItemType;
import Player.Player;

public class Equippable extends Item {

    public static Weight[] WEIGHTS = Weight.values();
    public static ArmorType[] ARMORTYPES = ArmorType.values();
    public static Image[][][] armorImages;
    private static int[][] MULTIPLIER = new int[][]{getLightMultiplier(), getNormalMultiplier(), getHeavyMultiplier()};


    public Weight weight;

    public int xpBonus;
    public int hpRegenBonus;
    public int critChance;
    public int hpBonus;
	public int armorBonus;
	public int attackBonus;
    public int abilityBonus;
    public int lifeStealBonus;
	public int coolDownReductionBonus;
	public double attackSpeedBonus;
	public float movespeedBonus;

    enum AttributeType{
        xpBonus,hpRegenBonus,critChance,hpBonus,armorBonus,attackBonus,abilityBonus,lifeStealBonus,coolDownReductionBonus,attackSpeedBonus,movespeedBonus,
    }

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
        switch(armorType){
            case helmet: 
                loadStats(12,16, new AttributeType[]{AttributeType.movespeedBonus});
                break;
            case plate:
                loadStats(16,20, new AttributeType[]{AttributeType.attackBonus, AttributeType.abilityBonus});
                break;
            case gauntlet:
                loadStats(8,12, new AttributeType[]{AttributeType.movespeedBonus});
                break;
            case boots:
                loadStats(8,12, new AttributeType[]{AttributeType.hpRegenBonus});
                break;
            default:
                System.exit(0);
        }
    }

    @Override
    public void equip(Player p){
        p.xpBoost += this.xpBonus;
        p.hpRegen += this.hpRegenBonus;
        p.critChance += this.critChance;
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
        p.xpBoost -= this.xpBonus;
        p.hpRegen -= this.hpRegenBonus;
        p.critChance -= this.critChance;
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
        if(xpBonus != 0){
            rows++;
        }
        if(hpBonus != 0){
           rows++;
        }
        if(critChance != 0){
            rows++;
        }
        if(hpRegenBonus != 0){
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
        if(xpBonus != 0){
            g.drawString(getBonusString(xpBonus) + "% Erfahrungsbonus ☀", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(hpBonus != 0){
            g.drawString(getBonusString(hpBonus) + " Leben ❤️", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(hpRegenBonus != 0){
            g.drawString(getBonusString(hpRegenBonus) + "/s Lebensgeneration ➕", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(armorBonus != 0){
            g.drawString(getBonusString(armorBonus) + " Rüstung 🛡️", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(attackBonus != 0){
            g.drawString(getBonusString(attackBonus) + " Angriffsschaden ⚔️", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(abilityBonus != 0){
            g.drawString(getBonusString(abilityBonus) + " Fähigkeitsstärke 🌠", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(critChance != 0){
            g.drawString(getBonusString(critChance) + "% Chance kritischer Treffer ⚠️", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(lifeStealBonus != 0){
            g.drawString(getBonusString(lifeStealBonus) + " Lebensraub 🦇", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(coolDownReductionBonus != 0){
            g.drawString(getBonusString(coolDownReductionBonus) + " Abklingzeitverringerung ⌛", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(attackSpeedBonus != 0){
            g.drawString(getBonusString((int)attackSpeedBonus) + " Angriffsgeschwindigkeit 🗡️", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(movespeedBonus != 0){
            g.drawString(getBonusString((int)movespeedBonus) + " Bewegungsgeschwindigkeit 🏃", hoverX+10, yoffset);
            yoffset += 32;
        }
    }

    private void loadStats(int min, int max, AttributeType[] notAllowed){
        int nAttributes = getNumberOfAttributes();
        ArrayList<AttributeType> types = new ArrayList<AttributeType>(Arrays.asList(AttributeType.values()));
        Collections.shuffle(types);
        int[] attributes = new int[types.size()];
        int discarded = 0;
        for(int i = 0; i < nAttributes + discarded; i++){
            AttributeType currentType = types.get(i);
            boolean shouldContinue = false;
            for(AttributeType t : notAllowed){
                if(t == currentType){
                    discarded++;
                    shouldContinue = true;
                    break;
                }
            }
            if(shouldContinue){
                continue;
            }
            int base = Constants.random(min, max);
            attributes[currentType.ordinal()] = base + base * MULTIPLIER[this.weight.ordinal()][currentType.ordinal()] * (this.rarity.ordinal()+1) / 100;
        }
        applyStats(attributes);
    }

    private void applyStats(int[] stats){
        this.xpBonus += stats[AttributeType.xpBonus.ordinal()];
        this.hpRegenBonus += stats[AttributeType.hpRegenBonus.ordinal()];
        this.critChance += stats[AttributeType.critChance.ordinal()];
        this.hpBonus += stats[AttributeType.hpBonus.ordinal()];
        this.armorBonus += stats[AttributeType.armorBonus.ordinal()];
        this.attackBonus += stats[AttributeType.attackBonus.ordinal()];
        this.abilityBonus += stats[AttributeType.abilityBonus.ordinal()];
        this.lifeStealBonus += stats[AttributeType.lifeStealBonus.ordinal()];
        this.coolDownReductionBonus += stats[AttributeType.coolDownReductionBonus.ordinal()];
        this.attackSpeedBonus += stats[AttributeType.attackSpeedBonus.ordinal()];
        this.movespeedBonus += stats[AttributeType.movespeedBonus.ordinal()];
    }

    private int getNumberOfAttributes(){
        switch(this.rarity){
            case common:
                return Constants.random(1, 2);
            case rare:
                return Constants.random(2, 3);
            case epic:
                return Constants.random(3, 4);
            case legendary:
                return Constants.random(4,5);
            default:
                return 0;
        }
    }

    private static int[] getHeavyMultiplier(){
        int[] mulitplier = new int[11];
        mulitplier[AttributeType.xpBonus.ordinal()] = 0;
        mulitplier[AttributeType.hpRegenBonus.ordinal()] = 3;
        mulitplier[AttributeType.critChance.ordinal()] = 0;
        mulitplier[AttributeType.hpBonus.ordinal()] = 15;
        mulitplier[AttributeType.armorBonus.ordinal()] = 20;
        mulitplier[AttributeType.attackBonus.ordinal()] = 5;
        mulitplier[AttributeType.abilityBonus.ordinal()] = 0;
        mulitplier[AttributeType.lifeStealBonus.ordinal()] = 3;
        mulitplier[AttributeType.coolDownReductionBonus.ordinal()] = 0;
        mulitplier[AttributeType.attackSpeedBonus.ordinal()] = 0;
        mulitplier[AttributeType.movespeedBonus.ordinal()] = 0;
        return mulitplier;
    }

    private static int[] getNormalMultiplier(){
        int[] mulitplier = new int[11];
        mulitplier[AttributeType.xpBonus.ordinal()] = 5;
        mulitplier[AttributeType.hpRegenBonus.ordinal()] = 5;
        mulitplier[AttributeType.critChance.ordinal()] = 15;
        mulitplier[AttributeType.hpBonus.ordinal()] = 8;
        mulitplier[AttributeType.armorBonus.ordinal()] = 10;
        mulitplier[AttributeType.attackBonus.ordinal()] = 5;
        mulitplier[AttributeType.abilityBonus.ordinal()] = 2;
        mulitplier[AttributeType.lifeStealBonus.ordinal()] = 1;
        mulitplier[AttributeType.coolDownReductionBonus.ordinal()] = 5;
        mulitplier[AttributeType.attackSpeedBonus.ordinal()] = 10;
        mulitplier[AttributeType.movespeedBonus.ordinal()] = 10;
        return mulitplier;
    }

    private static int[] getLightMultiplier(){
        int[] mulitplier = new int[11];
        mulitplier[AttributeType.xpBonus.ordinal()] = 10;
        mulitplier[AttributeType.hpRegenBonus.ordinal()] = 8;
        mulitplier[AttributeType.critChance.ordinal()] = 10;
        mulitplier[AttributeType.hpBonus.ordinal()] = 5;
        mulitplier[AttributeType.armorBonus.ordinal()] = 0;
        mulitplier[AttributeType.attackBonus.ordinal()] = 0;
        mulitplier[AttributeType.abilityBonus.ordinal()] = 15;
        mulitplier[AttributeType.lifeStealBonus.ordinal()] = 0;
        mulitplier[AttributeType.coolDownReductionBonus.ordinal()] = 10;
        mulitplier[AttributeType.attackSpeedBonus.ordinal()] = 5;
        mulitplier[AttributeType.movespeedBonus.ordinal()] = 5;
        return mulitplier;
    }

}