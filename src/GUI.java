import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {

  static int width = 80;
  static int height = 80;
  public static JPanel mainPanel;
  public static ButtonPanel buttonPanel;
  public static JPanel gridPanel;
  public static JFrame frame;
  public static Game game;
  public static Grid grid;

  private static void createAndShowGUI() {

    GUI.frame = new JFrame("Game of Life");
    GUI.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /*JLabel emptyLabel = new JLabel("");
    emptyLabel.setPreferredSize(new Dimension(175, 100));
    frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
    JFrame.setDefaultLookAndFeelDecorated(true);*/
    GUI.mainPanel = new JPanel();
    GUI.mainPanel.setLayout(new BoxLayout(GUI.mainPanel, BoxLayout.Y_AXIS));
    GUI.gridPanel = new JPanel();
    GUI.buttonPanel = new ButtonPanel();
    GUI.setMenu();
    GUI.initGame2D();
    GUI.gridPanel.add(GUI.grid);

    GUI.frame.add(mainPanel);
    GUI.mainPanel.add(gridPanel);
    GUI.mainPanel.add(buttonPanel);

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
    GUI.gridPanel.add(GUI.grid);
    GUI.grid.update();
    GUI.mainPanel.validate();
    GUI.buttonPanel.play.pause();
  }

  public static void initGame1D() {
    GUI.game = new Game1D(GUI.width);
    GUI.removeGrid();
    GUI.grid = new Grid1D();
    GUI.gridPanel.add(GUI.grid);
    GUI.mainPanel.validate();
    GUI.buttonPanel.play.pause();
  }

  private static void removeGrid() {
    try{
      GUI.gridPanel.remove(GUI.grid);
    }
    catch(Exception e) {}
  }
}
