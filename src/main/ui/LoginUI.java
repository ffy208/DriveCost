package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Represents the user interface for login functionality in the DriveCostPro
// application. It allows users to input their username to either log in or
// register a new account. This class manages user authentication and redirection
// to the main application interface upon successful login or registration.
public class LoginUI {
    private JPanel mainPanel;
    private JLabel label1 = new JLabel("User Name");
    private JTextField userName = new JTextField(10);

    private JButton loginBtn = new JButton("Login");
    private JButton registerBtn = new JButton("Register");
    private DriveCostProGUI driveCostProGUI;


    // REQUIRES: driveCostProGUI is not null.
    // MODIFIES: this
    // EFFECTS: Constructs a LoginUI, initializes the UI
    //          components, and sets up listeners for the login and register buttons.
    public LoginUI(DriveCostProGUI driveCostProGUI) {
        this.driveCostProGUI = driveCostProGUI;
        this.setLayout();
        this.loginBtnListener();
        this.registerBtnListener();
    }

    // MODIFIES: this
    // EFFECTS: Initializes and arranges UI components on the panel.
    public void setLayout() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JPanel panel01 = new JPanel();
        panel01.add(label1);
        panel01.add(userName);
        mainPanel.add(panel01, gbc);
        JPanel panel02 = new JPanel();
        mainPanel.add(panel02, gbc);
        JPanel panel03 = new JPanel();
        panel03.add(signInIconLabel());
        panel03.add(loginBtn);
        panel03.add(registerIconLabel());
        panel03.add(registerBtn);
        mainPanel.add(panel03, gbc);
    }

    // EFFECTS: Returns a JLabel with a scaled sign-in icon.
    public JLabel signInIconLabel() {
        ImageIcon icon = new ImageIcon("data/Icons/sign-in.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(scaledImage);
        JLabel currentLabel = new JLabel();
        currentLabel.setIcon(newIcon);
        return currentLabel;
    }

    // EFFECTS: Returns a JLabel with a scaled registration icon.
    public JLabel registerIconLabel() {
        ImageIcon icon = new ImageIcon("data/Icons/add-users.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(scaledImage);
        JLabel currentLabel = new JLabel();
        currentLabel.setIcon(newIcon);
        return currentLabel;
    }

    // MODIFIES: this
    // EFFECTS: Sets an ActionListener on the login button
    // that verifies the entered username and performs login if not empty.
    private void loginBtnListener() {
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginUserName = userName.getText();
                if (loginUserName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "User name can not be empty");
                    driveCostProGUI.displayLoginUI();
                } else {
                    driveCostProGUI.loginVerify(loginUserName);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Sets an ActionListener on the register button that
    // verifies the entered username and attempts to register it if not empty.
    private void registerBtnListener() {
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginUserName = userName.getText();
                if (loginUserName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "User name can not be empty");
                    driveCostProGUI.displayLoginUI();
                } else {
                    driveCostProGUI.registerVerify(userName.getText());
                }
            }
        });
    }

    // EFFECTS: Returns the main panel of this UI.
    public JPanel getPanel() {
        return mainPanel;
    }
}
