package Inventory;

import java.awt.Graphics;
import java.awt.Image;
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
    private static double[][] MULTIPLIER = new double[][]{getLightMultiplier(), getNormalMultiplier(), getHeavyMultiplier()};


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

    public String toCSV() {
        String res = super.toCSV();
        res += "/" + weight + "/" + xpBonus + "/" + hpRegenBonus + "/" + critChance + "/" + hpBonus + "/" + armorBonus + "/" + attackBonus 
            + "/" + abilityBonus + "/" + lifeStealBonus + "/" + coolDownReductionBonus + "/" + attackSpeedBonus + "/" + movespeedBonus;
		return res;
	}

    public Equippable(String[] values){
        super(values);
        this.weight = Weight.valueOf(values[3]);
        this.xpBonus = Integer.valueOf(values[4]);
        this.hpRegenBonus = Integer.valueOf(values[5]);
        this.critChance = Integer.valueOf(values[6]);
        this.hpBonus = Integer.valueOf(values[7]);
        this.armorBonus = Integer.valueOf(values[8]);
        this.attackBonus = Integer.valueOf(values[9]);
        this.abilityBonus = Integer.valueOf(values[10]);
        this.lifeStealBonus = Integer.valueOf(values[11]);
        this.coolDownReductionBonus = Integer.valueOf(values[12]);
        this.attackSpeedBonus = Double.valueOf(values[13]);
        this.movespeedBonus = Float.valueOf(values[14]);
        this.image = armorImages[rarity.ordinal()][weight.ordinal()][type.ordinal()-2];
    }

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
        p.updateAttackSpeed();
    }

    @Override
    public void deEquip(Player p){
        p.xpBoost -= this.xpBonus;
        p.hpRegen -= this.hpRegenBonus;
        p.critChance -= this.critChance;
        p.maxHP -= this.hpBonus;
        p.currentHP = Math.min(p.currentHP, p.maxHP);
        p.armor -= this.armorBonus;
        p.attackDamage -= this.attackBonus;
        p.abilityPower -= this.abilityBonus;
        p.lifeSteal -= this.lifeStealBonus;
        p.coolDownReduction -= this.coolDownReductionBonus;
        p.attackSpeed -= this.attackSpeedBonus;
        p.movespeed -= this.movespeedBonus;
        p.updateAttackSpeed();
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
            g.drawString(getBonusString(hpBonus) + " Leben ❤", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(hpRegenBonus != 0){
            g.drawString(getBonusString(hpRegenBonus) + "/s Lebensgeneration ➕", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(armorBonus != 0){
            g.drawString(getBonusString(armorBonus) + " Rüstung 🛡", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(attackBonus != 0){
            g.drawString(getBonusString(attackBonus) + " Angriffsschaden ⚔", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(abilityBonus != 0){
            g.drawString(getBonusString(abilityBonus) + " Fähigkeitsstärke 🌠", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(critChance != 0){
            g.drawString(getBonusString(critChance) + "% Chance kritischer Treffer ⚠", hoverX+10, yoffset);
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
            g.drawString((attackSpeedBonus > 0 ? ("+" + attackSpeedBonus) : ""+attackSpeedBonus) + " Angriffsgeschwindigkeit 🗡", hoverX+10, yoffset);
            yoffset += 32;
        }
        if(movespeedBonus != 0.0){
            g.drawString(getBonusString((int)(movespeedBonus)) + " Bewegungsgeschwindigkeit 🏃", hoverX+10, yoffset);
            yoffset += 32;
        }
    }

    private void loadStats(int min, int max, AttributeType[] notAllowed){
        int nAttributes = getNumberOfAttributes();
        ArrayList<AttributeType> types = new ArrayList<AttributeType>(Arrays.asList(AttributeType.values()));
        Collections.shuffle(types);
        float[] attributes = new float[types.size()];
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
            attributes[currentType.ordinal()] = (float) (base * MULTIPLIER[this.weight.ordinal()][currentType.ordinal()]
                    * (1 + 2 * (this.rarity.ordinal() + 1) / 10));
        }
        applyStats(attributes);
    }

    private void applyStats(float[] stats){
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

    private static double[] getHeavyMultiplier(){
        double[] mulitplier = new double[11];
        mulitplier[AttributeType.xpBonus.ordinal()] = 1;
        mulitplier[AttributeType.hpRegenBonus.ordinal()] = 0.2;
        mulitplier[AttributeType.critChance.ordinal()] = 1;
        mulitplier[AttributeType.hpBonus.ordinal()] = 1.15;
        mulitplier[AttributeType.armorBonus.ordinal()] = 1.60;
        mulitplier[AttributeType.attackBonus.ordinal()] = 1.05;
        mulitplier[AttributeType.abilityBonus.ordinal()] = 0;
        mulitplier[AttributeType.lifeStealBonus.ordinal()] = 1.03;
        mulitplier[AttributeType.coolDownReductionBonus.ordinal()] = 1;
        mulitplier[AttributeType.attackSpeedBonus.ordinal()] = 0.01;
        mulitplier[AttributeType.movespeedBonus.ordinal()] = 0.90;
        return mulitplier;
    }

    private static double[] getNormalMultiplier(){
        double[] mulitplier = new double[11];
        mulitplier[AttributeType.xpBonus.ordinal()] = 1.05;
        mulitplier[AttributeType.hpRegenBonus.ordinal()] = 0.15;
        mulitplier[AttributeType.critChance.ordinal()] = 1.015;
        mulitplier[AttributeType.hpBonus.ordinal()] = 1.08;
        mulitplier[AttributeType.armorBonus.ordinal()] = 1.3;
        mulitplier[AttributeType.attackBonus.ordinal()] = 1.05;
        mulitplier[AttributeType.abilityBonus.ordinal()] = 1.02;
        mulitplier[AttributeType.lifeStealBonus.ordinal()] = 1.01;
        mulitplier[AttributeType.coolDownReductionBonus.ordinal()] = 1.05;
        mulitplier[AttributeType.attackSpeedBonus.ordinal()] = 0.02;
        mulitplier[AttributeType.movespeedBonus.ordinal()] = 1.25;
        return mulitplier;
    }

    private static double[] getLightMultiplier(){
        double[] mulitplier = new double[11];
        mulitplier[AttributeType.xpBonus.ordinal()] = 1.10;
        mulitplier[AttributeType.hpRegenBonus.ordinal()] = 0.1;
        mulitplier[AttributeType.critChance.ordinal()] = 1.10;
        mulitplier[AttributeType.hpBonus.ordinal()] = 1.05;
        mulitplier[AttributeType.armorBonus.ordinal()] = 1;
        mulitplier[AttributeType.attackBonus.ordinal()] = 1;
        mulitplier[AttributeType.abilityBonus.ordinal()] = 1.15;
        mulitplier[AttributeType.lifeStealBonus.ordinal()] = 1;
        mulitplier[AttributeType.coolDownReductionBonus.ordinal()] = 1.10;
        mulitplier[AttributeType.attackSpeedBonus.ordinal()] = 0.04;
        mulitplier[AttributeType.movespeedBonus.ordinal()] = 1.1;
        return mulitplier;
    }

}