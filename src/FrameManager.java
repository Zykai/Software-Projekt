import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.SwingPropertyChangeSupport;


class FrameManager {
    public static JFrame frame;

    enum Screen {
        Menu, Creator, Game, Options, Saves
    }

    public static Screen currentScreen = Screen.Menu;

    private static JPanel current;

    public static void run() {
        // new Thread(new Runnable(){
        // @Override
        // public void run() {
    	//lösche alten Screen
        if (current != null) {
            frame.remove(current);
            frame.validate();
        }
        //aktualisiere neuen Screen
        switch (currentScreen) {
        case Menu:
            current = new Menu(frame, new Main());
            frame.add(current);
            break;
        case Creator:
            current = new Creator();
            frame.add(current);
            break;
        case Options:
        	current = new Options();
        	frame.add(current);
        	break;
        case Saves:
        	current = new Saves();
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
                    while(currentScreen == Screen.Game){
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