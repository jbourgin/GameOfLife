//import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TextPanel extends JPanel {

  public String textname;
  public JTextField textValue;
  public static Dimension fieldDimension = new Dimension(40,20);

  TextPanel(String textName, int textValue) {
    super();

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    this.textValue = new JTextField();
    this.textValue.setPreferredSize(TextPanel.fieldDimension);
    this.textValue.setMinimumSize(TextPanel.fieldDimension);

    this.add(new Label (textName));
    this.add(this.textValue);

  }

}
