import java.util.Random;
import java.util.List;
import java.util.*;

abstract class Game {
  int length;
  int[] grid;

  Game(int length) {
    this.length = length;
    this.grid = new int[length];
    this.initGrid();
  }

  private void initGrid() {
    //List<Integer> list_elt = Arrays.asList(13,14,15,17,18,19);
    //List<Integer> list_elt = Arrays.asList(2048,2049,2050,2148,2248,2249,2250); // Clown
    for(int i = 0; i < this.length ; i++){
      this.grid[i] = 0;
    }

    //for (Integer element : list_elt) {
      //this.grid[element] = 1;
    //}
  }

  public void initRandom() {
    Random rand=new Random();
    for(int i = 0; i < this.length ; i++){
      this.grid[i] = rand.nextInt(2);
    }
  }

  abstract public void step();
}
