package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// Provides functionality for displaying hint text in text fields.
// When a text field gains focus and if it contains the hint text,
// the hint is cleared. When the text field loses focus
// and is empty, the hint is shown again. This class enhances user
// experience by providing contextual cues within text fields.
public class HintListener implements FocusListener {

    private String hint;
    private JTextField jtextField;

    // REQUIRES: jtextField is not null and hint is a non-null string.
    // MODIFIES: this, jtextField
    // EFFECTS: Constructs a HintListener that sets a hint in the given JTextField.
    //          The text field is initially set with the hint text in gray color.
    public HintListener(JTextField jtextField, String hint) {
        this.jtextField = jtextField;
        this.hint = hint;
        jtextField.setForeground(Color.GRAY);
        jtextField.setText(this.hint);
    }

    /**
     * Invoked when a component gains the keyboard focus.
     *
     * @param e the event to be processed
     */
    // REQUIRES: e is not null.
    // MODIFIES: jtextField
    // EFFECTS: Clears the hint text and sets the text color to black when
    //         the JTextField gains focus, provided the current text is
    //         exactly the hint text. Does nothing if the text is not the hint.
    @Override
    public void focusGained(FocusEvent e) {
        String temp = jtextField.getText();
        if (temp.equals(hint)) {
            jtextField.setText("");
            jtextField.setForeground(Color.BLACK);
        }
    }

    /**
     * Invoked when a component loses the keyboard focus.
     *
     * @param e the event to be processed
     */
    // REQUIRES: e is not null.
    // MODIFIES: jtextField
    // EFFECTS: Replaces an empty text field with the hint text and sets
    //          the text color to gray when the JTextField loses focus.
    //          Does nothing if the text field already contains some text.
    @Override
    public void focusLost(FocusEvent e) {
        String temp = jtextField.getText();
        if (temp.equals("")) {
            jtextField.setText(hint);
            jtextField.setForeground(Color.GRAY);
        }

    }
}