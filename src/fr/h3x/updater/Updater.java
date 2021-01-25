package fr.h3x.updater;

import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;

import fr.theshark34.supdate.application.integrated.FileDeleter;


import java.io.File;


public class Updater {
    private static Thread updateThread;
    private static SUpdate su;

    public static void update() throws Exception {
        su = new SUpdate("https://update.bloodshed.fr/", new File(System.getProperty("user.home") + "\\AppData\\Roaming\\." + "Bloodshed"));
        su.addApplication(new FileDeleter());
        su.getServerRequester().setRewriteEnabled(true);
        updateThread = new Thread() {
            private int val;
            private int max;
            public void run() {
                while (!isInterrupted()) {
                    Main.getInstance().getUpdaterPanel().getProgressBar().setVisible(true);
                    this.val = ((int) (BarAPI.getNumberOfTotalDownloadedBytes() / 1000l));
                    this.max = ((int) (BarAPI.getNumberOfTotalBytesToDownload() / 1000l));
                    Main.getInstance().getUpdaterPanel().getProgressBar().setMaximum(max);
                    Main.getInstance().getUpdaterPanel().getProgressBar().setValue(val);
                }
            }
        };
        updateThread.start();
        su.start();
        updateThread.interrupt();
        ProcessBuilder processBuilder = new ProcessBuilder(System.getProperty("user.home") + "\\AppData\\Roaming\\." + "Bloodshed\\BloodshedLauncher.exe");
        processBuilder.start();
        Main.getInstance().getUpdaterPanel().updateInfosLabel("Lancement du launcher... ");
        System.exit(0);
    }



}
