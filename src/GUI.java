import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {

  static int width = 100;
  static int height = 100;
  public static JPanel mainPanel;
  public static JFrame frame;

  private static void createAndShowGUI() {
    Game2D game = new Game2D(GUI.width, GUI.height);

    GUI.frame = new JFrame("Game of Life");
    GUI.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /*JLabel emptyLabel = new JLabel("");
    emptyLabel.setPreferredSize(new Dimension(175, 100));
    frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    JFrame.setDefaultLookAndFeelDecorated(true);*/
    Grid grid = new Grid(game);
    GUI.mainPanel = new JPanel();
    Play play = new Play(game,grid);
    GUI.mainPanel.add(grid);

    JButton step = new JButton("Step");
    step.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.step();
        grid.update();
      }
    });

    JButton random = new JButton("Random");
    random.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.initRandom();
        grid.update();
      }
    });

    JButton accel = new JButton("+");
    accel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        play.accelerate();
      }
    });

    JButton decel = new JButton("-");
    decel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        play.decelerate();
      }
    });

    GUI.mainPanel.add(random);
    GUI.mainPanel.add(step);
    GUI.mainPanel.add(play);
    GUI.mainPanel.add(accel);
    GUI.mainPanel.add(decel);

    GUI.frame.add(mainPanel);

    //Grid grid = new Grid();
    //frame.add(grid);

    //frame.setSize(100,100);
    //frame.setLayout(null);
    GUI.frame.pack();

    //Display the window.
    GUI.frame.setVisible(true);
  }

  public static void main(String[] arg) {
    createAndShowGUI();
  }
}
