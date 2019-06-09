import java.util.*;
import java.io.*;

class Game2D extends Game {
  int width;
  int height;
  List<String> file;
  int[] gridSize;

  Game2D(int width, int height, String rlefile) {
    super(width * height);
    this.file = readRLE("../rle/" + rlefile);
    gridSize = applyRLE(this.file);
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
    super.step();
    int[] newGrid = new int[this.width * this.height];
    for(int i = 0; i < this.height; i++){
      for(int j = 0; j < this.width; j++){
        newGrid[i * this.width + j] = cellStatus(i,j);
      }
    }
    this.grid = newGrid;
  }

  public int[] applyRLE(List<String> file) {
    int elements[] = new int[2];
    String lineWidth = file.get(1);
    int[] widthElements = getElements(lineWidth, 0);
    elements[0] = widthElements[0];
    int[] heightElements = getElements(lineWidth, widthElements[1]);
    elements[1] = heightElements[0];
    if(lineWidth.indexOf("max") > -1) {
        int[] maxElements = getElements(lineWidth, heightElements[1]);
        stepMax = maxElements[0];
    }
    int currentGridLine = (GUI.height - elements[1])/2 + 1 ;
    int currentCell = currentGridLine*GUI.width+((GUI.width - elements[0])/2);
    int numberbCells;
    int numberoCells;
    for (int i = 2; i < file.size(); i++) {
      String currentLine = file.get(i);
      int minIndex = 0;
      while(minIndex <= currentLine.length() -1) {
        List<Integer> testList = new ArrayList<Integer>();
        int exclamIndex = currentLine.indexOf('$', minIndex);
        int dollarIndex = currentLine.indexOf('!', minIndex);
        int bIndex = currentLine.indexOf('b', minIndex);
        int oIndex = currentLine.indexOf('o', minIndex);
        if(bIndex > -1) {
          testList.add(bIndex);
        }
        if(dollarIndex > -1) {
          testList.add(dollarIndex);
        }
        if(oIndex > -1) {
          testList.add(oIndex);
        }
        if(exclamIndex > -1) {
          testList.add(exclamIndex);
        }
        int minList = Collections.min(testList);
        if(minList == dollarIndex || minList == exclamIndex) {
          for(int j = currentCell; j < GUI.width*currentGridLine ; j++){
            this.grid[j] = 0;
          }
          currentGridLine++;
          currentCell = currentGridLine*GUI.width+((GUI.width - elements[0])/2);
        } else if(minList == bIndex) {
          if(bIndex - minIndex > 0) {
            numberbCells = Integer.parseInt(currentLine.substring(minIndex,bIndex));
          } else {
            numberbCells = 1;
          }
          for(int j = currentCell; j < currentCell+numberbCells ; j++){
            this.grid[j] = 0;
          }
          currentCell = currentCell + numberbCells;
        } else if(minList == oIndex) {
          if(oIndex - minIndex > 0) {
            numberoCells = Integer.parseInt(currentLine.substring(minIndex,oIndex));
          } else {
            numberoCells = 1;
          }
          for(int j = currentCell; j < currentCell+numberoCells ; j++){
            this.grid[j] = 1;
          }
          currentCell = currentCell + numberoCells;
        }
        minIndex = minList+1;
      }
    }
    return elements;
  }

  public int[] getElements(String lineWidth, int minIndex) {
    int elements[] = new int[2];
    int equalIndex = lineWidth.indexOf('=', minIndex);
    int commaIndex = lineWidth.indexOf(',', minIndex);
    elements[0] = Integer.parseInt(lineWidth.substring(equalIndex+2,commaIndex));
    elements[1] = commaIndex + 1;
    return elements;
  }

  public List<String> readRLE(String filename) {
    List<String> records = new ArrayList<String>();
    try
    {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line;
      while ((line = reader.readLine()) != null)
      {
        records.add(line);
      }
      reader.close();
      return records;
    }
    catch (Exception e)
    {
      System.err.format("Exception occurred trying to read '%s'.", filename);
      e.printStackTrace();
      return null;
    }
  }


}
