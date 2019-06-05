import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Cell extends JButton implements ActionListener {

  int i;
  static int size = 10;

  public Cell(int i) {
    super();;
    this.i = i;
    this.setPreferredSize(new Dimension(Cell.size,Cell.size));
    addActionListener(this);
  }

  public void applyCellStatus() {
    if (GUI.game.grid[i] == 1) {
      this.setBackground(Color.black);
    }
    else {
      this.setBackground(Color.white);
    }
  }

  public void actionPerformed(ActionEvent e) {
    //System.out.println("Bite");
    if (GUI.game.grid[i] == 0) {
      this.setBackground(Color.black);
      GUI.game.grid[i] = 1;
    }
    else {
      this.setBackground(Color.white);
      GUI.game.grid[i] = 0;
    }
  }
}
