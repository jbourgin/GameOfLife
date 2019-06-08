class Main {
  public static void main(String[] args) {
    //Game1D game = new Game1D(100);
    Game2D game = new Game2D(100,100,"dartSynth.rle");
    System.out.println(game.toString());
    for (int i=0; i<110; i++){
      game.step();
      //System.out.println(game.toString());
      }
    System.out.println(game.toString());
  }
}
