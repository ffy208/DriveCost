package ui;

import javax.swing.*;
import java.awt.*;

// Presents a confirmation dialog for deleting a vehicle from the user's
// fleet in the DriveCostPro application. This class
// enhances the vehicle management system by providing a safeguard against
// accidental deletions, requiring user confirmation.
public class DeleteVehicleUI {
    private JPanel mainPanel;
    private JLabel label1 = new JLabel("Do you want to XXX ? ");
    private static JButton confirmBtn = new JButton("Yes, delete it");
    private static JButton deleteBtn = new JButton("No, go back");

    // MODIFIES: this
    // EFFECTS: Constructs a DeleteVehicleUI and initializes the layout.
    public DeleteVehicleUI() {
        this.setLayout();
    }

    // MODIFIES: this
    // EFFECTS: Sets up the main panel layout, including a label
    // asking for confirmation and buttons for confirming or cancelling the deletion.
    public void setLayout() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JPanel panel01 = new JPanel();
        panel01.add(label1);
        mainPanel.add(panel01, gbc);
        JPanel panel02 = new JPanel();
        panel02.add(confirmBtn);
        mainPanel.add(panel02, gbc);
    }

    // EFFECTS: Returns the main panel containing
    // the UI components for the vehicle deletion confirmation.
    public JPanel getPanel() {
        return mainPanel;
    }
}
