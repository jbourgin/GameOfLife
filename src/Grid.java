import javax.swing.*;
import java.awt.*;
import java.util.*;

abstract public class Grid extends JPanel {

  ArrayList<Cell> cells;

  public void update() {
    for (int i = 0; i < GUI.width*GUI.height; i++) {
      cells.get(i).applyCellStatus();
    }
  }

}
