import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonPanel extends JPanel {

  public Play play;
  public static JTextField stepValue;

  ButtonPanel() {
    super();
    this.play = new Play();
    JButton step = new JButton("Step");
    step.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.game.step();
        GUI.grid.update();
      }
    });

    JButton random = new JButton("Random");
    random.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.game.initRandom();
        GUI.grid.update();
      }
    });

    JButton reset = new JButton("Reset");
    reset.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame1D();
        GUI.grid.update();
        ButtonPanel.stepValue.setText(Integer.toString(GUI.game.currentStep));
      }
    });

    JTextField delayValue = new JTextField();
    delayValue.setText(Integer.toString(play.playGame.getDelay()));

    ButtonPanel.stepValue = new JTextField();
    ButtonPanel.stepValue.setText(Integer.toString(GUI.game.currentStep));

    JButton accel = new JButton("+");
    accel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        play.accelerate();
        delayValue.setText(Integer.toString(play.playGame.getDelay()));
      }
    });

    JButton decel = new JButton("-");
    decel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        play.decelerate();
        delayValue.setText(Integer.toString(play.playGame.getDelay()));
      }
    });

    this.add(delayValue);
    this.add(stepValue);
    this.add(play);
    this.add(accel);
    this.add(decel);
    this.add(step);
    this.add(random);
    this.add(reset);
  }
}
