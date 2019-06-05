import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {

  static int width = 100;
  static int height = 100;
  public static JPanel mainPanel;
  public static JFrame frame;
  public static Game game;
  public static Grid grid;
  public static Play play;

  private static void createAndShowGUI() {

    GUI.frame = new JFrame("Game of Life");
    GUI.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /*JLabel emptyLabel = new JLabel("");
    emptyLabel.setPreferredSize(new Dimension(175, 100));
    frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    JFrame.setDefaultLookAndFeelDecorated(true);*/
    GUI.mainPanel = new JPanel();
    GUI.setMenu();
    GUI.play = new Play();
    GUI.initGame2D();
    GUI.mainPanel.add(GUI.grid);

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
        GUI.play.accelerate();
        delayValue.setText(Integer.toString(play.playGame.getDelay()));
      }
    });

    JButton decel = new JButton("-");
    decel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.play.decelerate();
        delayValue.setText(Integer.toString(play.playGame.getDelay()));
      }
    });

    GUI.mainPanel.add(delayValue);
    GUI.mainPanel.add(random);
    GUI.mainPanel.add(step);
    GUI.mainPanel.add(play);
    GUI.mainPanel.add(accel);
    GUI.mainPanel.add(decel);

    GUI.frame.add(mainPanel);

    GUI.frame.pack();

    //Display the window.
    GUI.frame.setVisible(true);
  }

  public static void main(String[] arg) {
    createAndShowGUI();
  }

  public static void setMenu() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menuGame = new JMenu("Game");
    JMenuItem itemGame1D = new JMenuItem("Game 1D");
    JMenuItem itemGame2D = new JMenuItem("Game 2D");
    menuBar.add(menuGame);

    itemGame2D.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame2D();
        GUI.grid.update();
      }
    });

    itemGame1D.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame1D();
        GUI.grid.update();
      }
    });

    menuGame.add(itemGame1D);
    menuGame.add(itemGame2D);

    GUI.frame.setJMenuBar(menuBar);
  }

  public static void initGame2D() {
    GUI.game = new Game2D(GUI.width, GUI.height);
    GUI.removeGrid();
    GUI.grid = new Grid2D();
    GUI.mainPanel.add(GUI.grid);
    GUI.grid.update();
    GUI.mainPanel.validate();
    GUI.play.pause();
  }

  public static void initGame1D() {
    GUI.game = new Game1D(GUI.width);
    GUI.removeGrid();
    GUI.grid = new Grid1D();
    GUI.mainPanel.add(GUI.grid);
    GUI.mainPanel.validate();
    GUI.play.pause();
  }

  private static void removeGrid() {
    try{
      GUI.mainPanel.remove(GUI.grid);
    }
    catch(Exception e) {}
  }
}
