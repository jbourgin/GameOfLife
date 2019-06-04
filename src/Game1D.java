import java.util.*;

class Game1D extends Game {

  Game1D(int length) {
    super(length);
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

  public int neighboursClassic(int i) {
    if(i == 0) {
      return this.grid[i+1] + this.grid[i+2];
    }
    else if(i == 1) {
      return this.grid[i-1] + this.grid[i+1] + this.grid[i+2];
    }
    else if(i == this.length-2) {
      return this.grid[i-1] + this.grid[i-2] + this.grid[i+1];
    }
    else if(i == this.length-1) {
      return this.grid[i-1] + this.grid[i-2];
    }
    else {
      return this.grid[i-1] + this.grid[i-2] + this.grid[i+1] + this.grid[i+2];
    }
  }

  /*public int[] neighboursSE(int i) {
    int[] neighboursGrid;
    neighboursGrid = new int[2];
    //List<Integer> neighboursList = new ArrayList<Integer>();;
    if(i == 0) {
      neighboursGrid[0] = 0;
      neighboursGrid[1] = this.grid[i+1];
      return neighboursGrid;
    }
    else if(i == this.length-1) {
      neighboursGrid[0] = this.grid[i-1];
      neighboursGrid[1] = 0;
      return neighboursGrid;
    }
    else {
      neighboursGrid[0] = this.grid[i-1];
      neighboursGrid[1] = this.grid[i+1];
      return neighboursGrid;
    }
  }*/

  public HashMap<String, Integer> neighboursSE(int i) {
    HashMap<String, Integer> neighboursDic = new HashMap<String, Integer>();
    if(i == 0) {
      neighboursDic.put("Left", 0);
      neighboursDic.put("Right", this.grid[i+1]);
      return neighboursDic;
    }
    else if(i == this.length-1) {
      neighboursDic.put("Left", this.grid[i-1]);
      neighboursDic.put("Right", 0);
      return neighboursDic;
    }
    else {
      neighboursDic.put("Left", this.grid[i-1]);
      neighboursDic.put("Right", this.grid[i+1]);
      return neighboursDic;
    }
  }

  public int cellStatus(int i) {
    if(this.grid[i] == 1) {
      switch(neighboursClassic(i)) {
        case 2:
        case 4:
        return 1;
        default: return 0;
      }
    }
    else {
      switch(neighboursClassic(i)) {
        case 2:
        case 3:
        return 1;
        default: return 0;
      }
    }
  }
  /*
  public int cellStatus110(int i) {
    if(this.grid[i] == 1) {
      switch(neighboursSE(i)[0] + neighboursSE(i)[1]) {
        case 2:
        return 0;
        default: return 1;
      }
    }
    else {
      if(neighboursSE(i)[0] == 1 && neighboursSE(i)[1] == 0) {
        return 0;
      }
      else {
        switch(neighboursSE(i)[0] + neighboursSE(i)[1]) {
          case 0:
          return 0;
          default: return 1;
        }
      }
    }
  }*/

  public int cellStatus110(int i) {
    if(this.grid[i] == 1) {
      switch(neighboursSE(i).get("Left") + neighboursSE(i).get("Right")) {
        case 2:
        return 0;
        default: return 1;
      }
    }
    else {
      if(neighboursSE(i).get("Left") == 1 && neighboursSE(i).get("Right") == 0) {
        return 0;
      }
      else {
        switch(neighboursSE(i).get("Left") + neighboursSE(i).get("Right")) {
          case 0:
          return 0;
          default: return 1;
        }
      }
    }
  }

    public void step() {
      int[] newGrid = new int[this.length];
      for(int i = 0; i < this.length; i++){
          newGrid[i] = cellStatus110(i);
        }
      this.grid = newGrid;
  }
}
