import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonPanel extends JPanel {

  public Play play;

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

    JTextField delayValue = new JTextField();
    delayValue.setText(Integer.toString(play.playGame.getDelay()));

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

    JComboBox list2D = new JComboBox();
    list2D.addItem("Clown");
    list2D.addItem("Bite");
    list2D.setSelectedIndex(0);
    //list2D.addActionListener(new ActionListener() {
      //public void actionPerformed(ActionEvent e) {
        //JComboBox cb = (JComboBox)e.getSource();
        //String config = (String)cb.getSelectedItem();
        //updateLabel(config);
      //}
    //});

    this.add(delayValue);
    this.add(play);
    this.add(accel);
    this.add(decel);
    this.add(step);
    this.add(random);
    this.add(list2D);
  }
}
