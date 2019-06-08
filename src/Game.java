import java.util.Random;
import java.util.List;
import java.util.*;

abstract class Game {
  int length;
  int[] grid;
  int currentStep;
  public static int stepMax;

  Game(int length) {
    this.length = length;
    this.grid = new int[length];
    this.initGrid();
    this.currentStep = 0;
  }

  private void initGrid() {
    for(int i = 0; i < this.length ; i++){
      this.grid[i] = 0;
    }
  }

  public void initRandom() {
    Random rand=new Random();
    for(int i = 0; i < this.length ; i++){
      this.grid[i] = rand.nextInt(2);
    }
  }

  public void step() {
    this.currentStep++;
    ButtonPanel.stepValue.setText(Integer.toString(GUI.game.currentStep));
    if(this.currentStep >= GUI.game.stepMax) {
        GUI.buttonPanel.play.pause();
    }
  };
}
