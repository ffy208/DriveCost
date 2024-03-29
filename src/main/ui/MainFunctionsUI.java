package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Acts as the interface for accessing the main functionalities of the
// DriveCostPro application once a user is logged in.
// It provides options to view and manage vehicles, access account settings,
// and exit the application. This UI is pivotal
// for navigating to different parts of the application based on user choice.
public class MainFunctionsUI {
    private JPanel mainPanel;
    private static JButton viewMyVehiclesBtn = new JButton("View My Vehicles");
    private static JButton addVehicleBtn = new JButton("Add a Vehicle");
    private static JButton myAccountBtn = new JButton("My Account");
    private static JButton exitBtn = new JButton("Exit");
    private DriveCostProGUI driveCostProGUI;
    private JLayeredPane layeredPane;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private JPanel resultPanel;

    // REQUIRES: driveCostProGUI is not null.
    // MODIFIES: this
    // EFFECTS: Constructs a MainFunctionsUI,
    // initializes the layout and action listeners for the UI components.
    public MainFunctionsUI(DriveCostProGUI driveCostProGUI) {
        this.driveCostProGUI = driveCostProGUI;
        this.setLayout();
        this.addListeners();
        this.exitBtnListener();
        this.myAccountBtnListener();

    }

    // MODIFIES: this
    // EFFECTS: Sets up the main panel layout with background and buttons
    // for the primary functions of the application.
    public void setLayout() {
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        BackgoundPanel bgPanel = new BackgoundPanel("data/background.jpg");
        bgPanel.setBounds(0, 0, WIDTH, HEIGHT);
        layeredPane.add(bgPanel, Integer.valueOf(JLayeredPane.DEFAULT_LAYER));
        mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setBounds(0, 0, WIDTH, HEIGHT);
        mainPanel.add(viewMyVehiclesBtn);
        mainPanel.add(addVehicleBtn);
        mainPanel.add(myAccountBtn);
        mainPanel.add(exitBtn);
        layeredPane.add(mainPanel, Integer.valueOf(JLayeredPane.PALETTE_LAYER));
        resultPanel = new JPanel();
        resultPanel.add(layeredPane);
    }

    // MODIFIES: this
    // EFFECTS: Adds action listeners to the view and add vehicle buttons.
    private void addListeners() {
        viewMyVehiclesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.displayVehicleList();
            }
        });

        addVehicleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.displayAddVehicleUI();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Adds an action listener to the my account button that,
    // when triggered, displays the my account UI.
    private void myAccountBtnListener() {
        myAccountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.displayMyAccountUI();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Adds an action listener to the exit button that,
    // when triggered, executes the exit procedure of the application.
    private void exitBtnListener() {
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.exit();
            }
        });
    }

    // EFFECTS: Returns the main panel containing all UI components of this class.
    public JPanel getPanel() {
        return resultPanel;
    }
}
