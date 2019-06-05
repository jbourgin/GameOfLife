import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Grid1D extends Grid {

  public int currentLine;

  public Grid1D() {
    this.currentLine = 0;
    setLayout(new GridLayout(GUI.width, GUI.height));
    setPreferredSize(new Dimension(GUI.width*Cell.size,GUI.height*Cell.size));
    cells = new ArrayList<Cell>();
    for (int j = 0; j < GUI.height; j++) {
      for (int i = 0; i < GUI.width; i++) {
        Cell cell = new Cell(i);
        add(cell);
        cells.add(cell);
      }
    }
  }

  public void update() {
    if(this.currentLine < GUI.height){
      for (int i = GUI.width*this.currentLine; i < GUI.width*(this.currentLine+1); i++) {
        cells.get(i).applyCellStatus();
      }
      this.currentLine++;
    } else {
      Color color;
      for (int j = 0; j < GUI.height-1; j++) {
        for (int i = 0; i < GUI.width; i++) {
          color = cells.get((j+1)*GUI.width+i).getBackground();
          cells.get(j*GUI.width+i).setBackground(color);
        }
      }
      for (int i = GUI.width*(GUI.height-1); i < GUI.width*GUI.height; i++) {
        cells.get(i).applyCellStatus();
      }
    }
  }

}
