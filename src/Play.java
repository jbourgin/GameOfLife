import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Play extends JButton implements ActionListener {

  boolean playing = false;
  Timer playGame;

  public Play() {
    super("Play");
    addActionListener(this);

    int delay = 100;
    ActionListener stepGame = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GUI.game.step();
        GUI.grid.update();
      }
    };
    this.playGame = new Timer(delay, stepGame);
  }

  public void accelerate() {
      if(this.playGame.getDelay() > 12.5) {
        this.playGame.setDelay(this.playGame.getDelay()/2);
    }
  }

  public void decelerate() {
      if(this.playGame.getDelay() < 800) {
          this.playGame.setDelay(this.playGame.getDelay()*2);
      }
  }

  public void pause() {
    this.playGame.stop();
    this.playing = false;
    this.setText("Play");
  }

  public void actionPerformed(ActionEvent e) {
    if (this.playing) {
      this.pause();
    }
    else {
      this.playGame.start();
      this.playing = true;
      this.setText("Pause");
      //GUI.frame.pack();
    }
  }
}
