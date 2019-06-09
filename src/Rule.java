import java.lang.Math;
import java.util.*;

class Rule {

  int[] positions;
  public static int ruleNumber;

  Rule(int ruleNumber) {
    positions = new int[8];
    Rule.ruleNumber = ruleNumber;
    for(int i = 0; i < positions.length; i++) {
      positions[i] = 0;
    }
    String ruleByte = Integer.toBinaryString(ruleNumber);
    int j = 7;
    for(int i = ruleByte.length()-1; i >= 0; i--) {
      positions[j] = Integer.parseInt(ruleByte.substring(i,i+1));
      j--;
    }
    for(int i = 0; i < positions.length; i++) {
    }
  }

  public int cellStatus(int i, int[] grid) {
    if(i > 0 && i < GUI.width-1 && grid[i] == 1 && grid[i-1] == 1 && grid[i+1] == 1) {
      return positions[0];
    } else if(i > 0 && grid[i] == 1 && grid[i-1] == 1 && (i == GUI.width-1 || grid[i+1] == 0)) {
      return positions[1];
    } else if(i > 0 && i < GUI.width-1 && grid[i] == 0 && grid[i-1] == 1 && grid[i+1] == 1) {
      return positions[2];
    } else if(i > 0 && grid[i] == 0 && grid[i-1] == 1 && (i == GUI.width-1 || grid[i+1] == 0)) {
      return positions[3];
    } else if((i == 0 || grid[i-1] == 0) && grid[i] == 1 && i < GUI.width-1 && grid[i+1] == 1) {
      return positions[4];
    } else if((i == 0 || grid[i-1] == 0) && grid[i] == 1 && (i == GUI.width-1 || grid[i+1] == 0)) {
      return positions[5];
    } else if((i == 0 || grid[i-1] == 0) && grid[i] == 0 && i < GUI.width-1 && grid[i+1] == 1) {
      return positions[6];
    } else if((i == 0 || grid[i-1] == 0) && grid[i] == 0 && (i == GUI.width-1 || grid[i+1] == 0)) {
      return positions[7];
    } else {
      System.out.println("Wrong cellStatus Game 1D");
      return 0;
    }
  }

}
