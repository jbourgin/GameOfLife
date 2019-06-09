import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonPanel extends JPanel {

  public Play play;
  public static JTextField stepValue;
  public static JTextField ruleValue;
  public static Dimension fieldDimension = new Dimension(40,20);

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
        ButtonPanel.stepValue.setText(Integer.toString(GUI.game.currentStep));
      }
    });

    JTextField delayValue = new JTextField();
    delayValue.setPreferredSize(ButtonPanel.fieldDimension);
    delayValue.setMinimumSize(ButtonPanel.fieldDimension);
    delayValue.setText(Integer.toString(play.playGame.getDelay()));

    ButtonPanel.stepValue = new JTextField();
    ButtonPanel.stepValue.setPreferredSize(ButtonPanel.fieldDimension);
    ButtonPanel.stepValue.setMinimumSize(ButtonPanel.fieldDimension);
    ButtonPanel.stepValue.setText(Integer.toString(GUI.game.currentStep));

    ButtonPanel.ruleValue = new JTextField();
    ButtonPanel.ruleValue.setPreferredSize(ButtonPanel.fieldDimension);
    ButtonPanel.ruleValue.setMinimumSize(ButtonPanel.fieldDimension);
    ButtonPanel.ruleValue.setText(Integer.toString(GUI.rule.ruleNumber));

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
    this.add(ruleValue);
    this.add(play);
    this.add(accel);
    this.add(decel);
    this.add(step);
    this.add(random);
    this.add(reset);
  }
}
