import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Grid extends JPanel {

  ArrayList<Cell> cells;

  public Grid(Game2D game) {
    //super();
    setLayout(new GridLayout(GUI.width, GUI.height));
    setPreferredSize(new Dimension(GUI.width*Cell.size,GUI.height*Cell.size));
    //this.setSize(100,100);
    cells = new ArrayList<Cell>();
    for (int i = 0; i < GUI.width*GUI.height; i++) {
      Cell cell = new Cell(game, i);
      add(cell);
      cells.add(cell);
    }
  }

  public void update() {
    for (int i = 0; i < GUI.width*GUI.height; i++) {
      cells.get(i).applyCellStatus();
    }
  }

}
