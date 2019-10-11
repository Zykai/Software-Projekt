package Inventory;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Inventory.Inventory.ItemType;

public class Item {

    public final ItemType type;
    public Image image;

    public Item() {
        type = ItemType.Helmet;
        try {
            image = ImageIO.read(new File("res/dsui/item_helmet.png")).getScaledInstance(160, 160, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}