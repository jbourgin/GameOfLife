class Game2D extends Game {
  int width;
  int height;

  Game2D(int width, int height) {
    super(width * height);
    this.width = width;
    this.height = height;
  }

  private int get(int i, int j) {
    return this.grid[i * width + j];
  }

  public String toString() {
    String s = "";
    for(int j = 0; j < this.height; j++){
      for(int i = 0; i < this.width; i++){
        if (this.get(i,j) == 0) {
          s = s.concat(" ");
        }
        else {
          s = s.concat("X");
        }
        //s = s.concat(" ");
      }
      s = s.concat("\n");
    }
    return s;
  }

  public int neighbours(int i, int j) {
    if(i == 0) {
      if(j == 0) {
        return this.get(i+1,j) + this.get(i,j+1) + this.get(i+1,j+1);
      } else if(j == this.width-1) {
        return this.get(i+1,j) + this.get(i,j-1) + this.get(i+1,j-1);
      } else {
        return this.get(i+1,j) + this.get(i,j-1)+ this.get(i+1,j-1) + this.get(i,j+1) + this.get(i+1,j+1);
      }
    } else if(i == this.height-1) {
      if(j == 0) {
        return this.get(i-1,j) + this.get(i,j+1) + this.get(i-1,j+1);
      } else if(j == this.width-1) {
        return this.get(i-1,j) + this.get(i,j-1)+ this.get(i-1,j-1);
      } else {
        return this.get(i-1,j) + this.get(i,j-1)+ this.get(i-1,j-1) + this.get(i,j+1) + this.get(i-1,j+1);
      }
    } else {
      if(j == 0) {
        return this.get(i-1,j) + this.get(i,j+1) + this.get(i-1,j+1) + this.get(i+1,j) + this.get(i+1,j+1);
      } else if(j == this.width-1) {
        return this.get(i-1,j) + this.get(i,j-1)+ this.get(i-1,j-1) + this.get(i+1,j) + this.get(i+1,j-1);
      } else {
        return this.get(i-1,j) + this.get(i,j-1)+ this.get(i-1,j-1) + this.get(i,j+1) + this.get(i-1,j+1) + this.get(i+1,j) + this.get(i+1,j-1) + this.get(i+1,j+1);
      }
    }
  }

  public int cellStatus(int i, int j) {
    switch(neighbours(i,j)) {
      case 3: return 1;
      case 2: return this.get(i,j);
      default: return 0;
    }
  }

  public void step() {
    int[] newGrid = new int[this.width * this.height];
    for(int i = 0; i < this.height; i++){
      for(int j = 0; j < this.width; j++){
        newGrid[i * this.width + j] = cellStatus(i,j);
      }
    }
    this.grid = newGrid;
  }
}
