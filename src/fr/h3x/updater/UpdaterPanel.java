package fr.h3x.updater;

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.textured.STexturedProgressBar;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UpdaterPanel extends JPanel {
    Image image = Swinger.getResource("updater.png");
    private STexturedProgressBar progressBar = new STexturedProgressBar(Swinger.getResource("updater-progress-bar-base.png"), Swinger.getResource("updater-progress-bar.png"));
    private AntialiasingLabel infosLabel = new AntialiasingLabel();
    public UpdaterPanel() {
        setLayout(null);
        setBackground(Swinger.TRANSPARENT);
        progressBar.setBounds(37, 240, 227, 9);
        add(progressBar);

        infosLabel = new AntialiasingLabel();
        infosLabel.setForeground(Color.WHITE);
        infosLabel.setFont(new Font("NotoSans-Regular", 1, 17));
        infosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infosLabel.setBounds(37, 190, 227, 40);
        infosLabel.setText("<html><center>Recherche de mise à jour</center></html>");
        this.add(infosLabel);
    }

    public void updateInfosLabel(String infoText){
        infosLabel.setText("<html><center>"+infoText+"</center></html>");
        infosLabel.updateUI();
    }
    private void registerFont(){
        try {
            GraphicsEnvironment ge =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("/fr/h3x/bloodshed/ressource/NotoSans-Regular.ttf")));
        } catch (IOException |FontFormatException e) {
            //Handle exception
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Swinger.activateAntialias(g);
        Swinger.drawFullsizedImage(g, this, image);
    }
    public STexturedProgressBar getProgressBar() {
        return progressBar;
    }
}
