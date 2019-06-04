import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Cell extends JButton implements ActionListener {

  Game2D game;
  int i;
  static int size = 10;

  public Cell(Game2D game, int i) {
    super();
    this.game = game;
    this.i = i;
    this.setPreferredSize(new Dimension(Cell.size,Cell.size));
    addActionListener(this);
    applyCellStatus();
  }

  public void applyCellStatus() {
    if (this.game.grid[i] == 1) {
      this.setBackground(Color.black);
    }
    else {
      this.setBackground(Color.white);
    }
  }

  public void actionPerformed(ActionEvent e) {
    //System.out.println("Bite");
    if (this.game.grid[i] == 0) {
      this.setBackground(Color.black);
      this.game.grid[i] = 1;
    }
    else {
      this.setBackground(Color.white);
      this.game.grid[i] = 0;
    }
  }
}
