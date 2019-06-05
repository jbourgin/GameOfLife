import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Grid2D extends Grid {

  public Grid2D() {
    setLayout(new GridLayout(GUI.height, GUI.width));
    setPreferredSize(new Dimension(GUI.width*Cell.size,GUI.height*Cell.size));
    //this.setSize(100,100);
    cells = new ArrayList<Cell>();
    for (int i = 0; i < GUI.width*GUI.height; i++) {
      Cell cell = new Cell(i);
      add(cell);
      cells.add(cell);
    }
  }

}
