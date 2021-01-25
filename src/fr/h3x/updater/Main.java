package fr.h3x.updater;

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.util.WindowMover;

import javax.swing.*;

public class Main extends JFrame {
    private static Main instance;
    public UpdaterPanel updaterPanel;

    public static Main getInstance() {
        return instance;
    }

    public Main() throws Exception {
        setTitle("Bloodshed Updater");
        setSize(300, 300);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(Swinger.TRANSPARENT);
        setIconImage(Swinger.getResource("logo.png"));
        setContentPane(updaterPanel = new UpdaterPanel());
        WindowMover mover = new WindowMover(this);
        addMouseListener(mover);
        addMouseMotionListener(mover);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        Swinger.setSystemLookNFeel();
        String basePath = "/" + Main.class.getPackage().getName().replaceAll("\\.", "/") + "/ressource/";
        Swinger.setResourcePath(basePath);
        instance = new Main();
        Thread t = new Thread(() -> {
            try {
                Updater.update();
            } catch (Exception e) {
                e.printStackTrace();
                Main.getInstance().getUpdaterPanel().updateInfosLabel("Erreur impossible de télécharger les fichiers !");
            }
        });
        t.start();
    }

    public UpdaterPanel getUpdaterPanel() {
        return updaterPanel;
    }
}
