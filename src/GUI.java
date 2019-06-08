import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {

  static int width = 100;
  static int height = 100;
  public static JPanel mainPanel;
  public static ButtonPanel buttonPanel;
  public static JPanel gridPanel;
  public static JFrame frame;
  public static Game game;
  public static Grid grid;
  public static String defaultGame;

  private static void createAndShowGUI() {

    GUI.frame = new JFrame("Game of Life");
    GUI.defaultGame = "dartSynth.rle";
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
    GUI.initGame2D(GUI.width, GUI.height, GUI.defaultGame);
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
    JMenu menuConfig = new JMenu("Config");
    JMenuItem rules1D = new JMenuItem("Rules 1D");
    JMenuItem config2D = new JMenuItem("Configurations 2D");
    menuBar.add(menuGame);
    menuBar.add(menuConfig);

    itemGame2D.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame2D(GUI.width, GUI.height, GUI.defaultGame);
        GUI.grid.update();
      }
    });

    itemGame1D.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame1D();
        GUI.grid.update();
      }
    });

    rules1D.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame1D();
        GUI.grid.update();
      }
    });

    config2D.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("../rle/"));
        int result = fileChooser.showOpenDialog(GUI.frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            GUI.initGame2D(GUI.width, GUI.height, selectedFile.getName());
            GUI.grid.update();
          }
        }
      });

    menuGame.add(itemGame1D);
    menuGame.add(itemGame2D);

    menuConfig.add(rules1D);
    menuConfig.add(config2D);

    GUI.frame.setJMenuBar(menuBar);
  }

  public static void initGame2D(int width, int height, String defaultGame) {
    GUI.game = new Game2D(width, height, defaultGame);
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
