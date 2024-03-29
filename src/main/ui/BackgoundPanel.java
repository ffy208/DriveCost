package ui;

import javax.swing.*;
import java.awt.*;

// A custom JPanel class that supports background images. It is used
// within the DriveCostPro application to enhance the user interface by
// adding visual elements to panels. This class extends JPanel and
// JPanel and overrides the paintComponent method to draw a background image.
public class BackgoundPanel extends JPanel {
    private Image backgroundImage;

    // REQUIRES: imagePath is a valid path to an image file.
    // MODIFIES: this
    // EFFECTS: Constructs a BackgroundPanel and sets its backgroundImage
    // based on the provided imagePath.
    public BackgoundPanel(String imagePath) {
        this.backgroundImage = new ImageIcon(imagePath).getImage();
    }

    // MODIFIES: g
    // EFFECTS: Draws the backgroundImage to fill the entire panel, scaling it
    // to fit the panel's current size.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }

}
