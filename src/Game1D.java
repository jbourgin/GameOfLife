import java.util.*;

class Game1D extends Game {

  Game1D(int length) {
    super(length);
    this.grid[length/2] = 1;
  }

  public String toString() {
    String s = "";
    for(int i = 0; i < this.length; i++){
        if (this.grid[i] == 0) {
          s = s.concat(" ");
        }
        else {
          s = s.concat("X");
        }
        //s = s.concat(Integer.toString(this.grid[i]));
        s = s.concat(" ");
    }
    return s;
  }

  public void step() {
    super.step();
    int[] newGrid = new int[this.length];
    for(int i = 0; i < this.length; i++){
        newGrid[i] = GUI.rule.cellStatus(i, this.grid);
      }
    this.grid = newGrid;
}
}
