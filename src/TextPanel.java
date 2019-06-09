//import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

class TextPanel extends JPanel {

  public JTextField textValue;
  public static Dimension fieldDimension = new Dimension(50,20);

  TextPanel(String textName, int textValue) {
    super();
    TitledBorder title = BorderFactory.createTitledBorder(textName);

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    this.textValue = new JTextField();
    this.textValue.setPreferredSize(TextPanel.fieldDimension);
    this.textValue.setMinimumSize(TextPanel.fieldDimension);

    this.setBorder(title);
    this.add(this.textValue);

  }

}
