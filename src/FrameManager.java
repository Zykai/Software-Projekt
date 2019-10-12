import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameManager {
    public static JFrame frame;

    enum Screen {
        Menu, Creator, Game,
    }

    public static Screen currentScreen = Screen.Menu;

    private static JPanel current;

    public static void run() {
        // new Thread(new Runnable(){
        // @Override
        // public void run() {
        if (current != null) {
            System.out.println("Deleted old window");
            frame.remove(current);
            frame.validate();
        }
        switch (currentScreen) {
        case Menu:
            current = new Menu(frame, new Main());
            frame.add(current);
            break;
        case Creator:
            current = new Creator();
            frame.add(current);
            break;
        case Game:
            new Thread(new Runnable() {
                public void run() {
                    Main main = new Main();
                    current = main;
                    frame.add(current);
                    frame.validate();
                    long time = System.currentTimeMillis();
                    boolean f = false;
                    while(true){
                        try {
                            Thread.sleep(7);
                        }
                        catch (Exception e) {}
                        long newTime = System.currentTimeMillis();
                        main.deltaTime = (float) (newTime - time);
                        time = newTime;
                        main.updateGame(main.deltaTime);
                        frame.repaint();
                    }
                }
            }).start();
            break;
        }
        frame.validate();        
    }

}