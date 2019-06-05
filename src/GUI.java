import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {

  static int width = 100;
  static int height = 100;
  public static JPanel mainPanel;
  public static JFrame frame;

  public static JMenuBar menuBar = new JMenuBar();
  public static JMenu menuGame = new JMenu("Game");
  public static JMenuItem itemGame1D = new JMenuItem("Game 1D");
  public static JMenuItem itemGame2D = new JMenuItem("Game 2D");

  private static void createAndShowGUI() {

    GUI.frame = new JFrame("Game of Life");
    GUI.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /*JLabel emptyLabel = new JLabel("");
    emptyLabel.setPreferredSize(new Dimension(175, 100));
    frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    JFrame.setDefaultLookAndFeelDecorated(true);*/
    GUI.mainPanel = new JPanel();

    menuBar.add(menuGame);

    itemGame2D.addActionListener(new ActionListener() {
        //@override
        public void actionPerformed(ActionEvent e) {
            Game2D game = new Game2D(GUI.width, GUI.height);
            Grid grid = new Grid(game);
            Play play = new Play(game,grid);
            GUI.mainPanel.add(grid);
        }
    });

    itemGame1D.addActionListener(new ActionListener() {
        //@override
        public void actionPerformed(ActionEvent e) {
            Game1D game = new Game1D(GUI.width * GUI.height);
            Grid1 grid = new Grid1(game);
            Play play = new Play(game,grid);
            GUI.mainPanel.add(grid);
        }
    });

    menuGame.add(itemGame1D);
    menuGame.add(itemGame2D);

    GUI.frame.setJMenuBar(menuBar);

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

    JTextField delayValue = new JTextField();

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

    GUI.mainPanel.add(delayValue);
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
