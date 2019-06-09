import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonPanel extends JPanel {

  public Play play;
  public static TextPanel stepField;
  public static TextPanel ruleField;

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
          if(GUI.game instanceof Game1D) {
              GUI.initGame1D();
          } else {
              GUI.initGame2D(GUI.width, GUI.height, GUI.selectedFile.getName());
          }
        GUI.grid.update();
        ButtonPanel.stepField.textValue.setText(Integer.toString(GUI.game.currentStep));
      }
    });

    TextPanel delayField = new TextPanel("Speed", play.playGame.getDelay());
    delayField.textValue.setText(Integer.toString(play.playGame.getDelay()));

    ButtonPanel.stepField = new TextPanel("Step", GUI.game.currentStep);
    ButtonPanel.stepField.textValue.setText(Integer.toString(GUI.game.currentStep));

    ButtonPanel.ruleField = new TextPanel("Rule", GUI.rule.ruleNumber);
    ButtonPanel.ruleField.textValue.setText(Integer.toString(GUI.rule.ruleNumber));

    JButton accel = new JButton("+");
    accel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        play.accelerate();
        delayField.textValue.setText(Integer.toString(play.playGame.getDelay()));
      }
    });

    JButton decel = new JButton("-");
    decel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        play.decelerate();
        delayField.textValue.setText(Integer.toString(play.playGame.getDelay()));
      }
    });

    this.add(delayField);
    this.add(stepField);
    this.add(ruleField);
    this.add(play);
    this.add(accel);
    this.add(decel);
    this.add(step);
    this.add(random);
    this.add(reset);
  }
}
