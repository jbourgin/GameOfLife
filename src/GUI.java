import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

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
  public static JMenu rules1D;
  public static JMenuItem config2D;
  public static File selectedFile;
  public static Rule rule;

  private static void createAndShowGUI() {

    GUI.frame = new JFrame("Game of Life");
    GUI.defaultGame = "clown.rle";
    GUI.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GUI.mainPanel = new JPanel();
    GUI.mainPanel.setLayout(new BoxLayout(GUI.mainPanel, BoxLayout.Y_AXIS));
    GUI.gridPanel = new JPanel();
    GUI.setMenu();
    GUI.initGame2D(GUI.width, GUI.height, GUI.defaultGame);
    GUI.buttonPanel = new ButtonPanel();
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
    JMenuItem rule50 = new JMenuItem("Rule 50");
    JMenuItem rule73 = new JMenuItem("Rule 73");
    JMenuItem rule110 = new JMenuItem("Rule 110");
    JMenuItem rule250 = new JMenuItem("Rule 250");
    JMenuItem rule254 = new JMenuItem("Rule 254");
    JMenuItem ruleRandom = new JMenuItem("Random");
    JMenu menuConfig = new JMenu("Config");
    GUI.rules1D = new JMenu("Rules 1D");
    GUI.config2D = new JMenuItem("Configurations 2D");
    menuBar.add(menuGame);
    menuBar.add(menuConfig);

    rule50.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame1D();
        GUI.rule = new Rule(50);
        ButtonPanel.ruleField.textValue.setText(Integer.toString(GUI.rule.ruleNumber));
        GUI.grid.update();
      }
    });

    rule73.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame1D();
        GUI.rule = new Rule(73);
        ButtonPanel.ruleField.textValue.setText(Integer.toString(GUI.rule.ruleNumber));
        GUI.grid.update();
      }
    });

    rule110.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame1D();
        GUI.rule = new Rule(110);
        ButtonPanel.ruleField.textValue.setText(Integer.toString(GUI.rule.ruleNumber));
        GUI.grid.update();
      }
    });

    rule250.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame1D();
        GUI.rule = new Rule(250);
        ButtonPanel.ruleField.textValue.setText(Integer.toString(GUI.rule.ruleNumber));
        GUI.grid.update();
      }
    });

    rule254.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame1D();
        GUI.rule = new Rule(254);
        ButtonPanel.ruleField.textValue.setText(Integer.toString(GUI.rule.ruleNumber));
        GUI.grid.update();
      }
    });

    ruleRandom.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame1D();
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(256) + 1;
        GUI.rule = new Rule(randomInt);
        ButtonPanel.ruleField.textValue.setText(Integer.toString(GUI.rule.ruleNumber));
        GUI.grid.update();
      }
    });

    itemGame2D.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame2D(GUI.width, GUI.height, GUI.defaultGame);
        GUI.grid.update();
      }
    });

    itemGame1D.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        GUI.initGame1D();
        GUI.rule = new Rule(110);
        ButtonPanel.ruleField.textValue.setText(Integer.toString(GUI.rule.ruleNumber));
        GUI.grid.update();
      }
    });

    GUI.config2D.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("../rle/"));
        int result = fileChooser.showOpenDialog(GUI.frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            GUI.selectedFile = fileChooser.getSelectedFile();
            GUI.initGame2D(GUI.width, GUI.height, GUI.selectedFile.getName());
            GUI.grid.update();
          }
        }
      });

    menuGame.add(itemGame1D);
    menuGame.add(itemGame2D);

    menuConfig.add(rules1D);
    GUI.rules1D.add(rule50);
    GUI.rules1D.add(rule73);
    GUI.rules1D.add(rule110);
    GUI.rules1D.add(rule250);
    GUI.rules1D.add(rule254);
    GUI.rules1D.add(ruleRandom);
    menuConfig.add(GUI.config2D);

    GUI.frame.setJMenuBar(menuBar);
  }

  public static void initGame2D(int width, int height, String defaultGame) {
    GUI.game = new Game2D(width, height, defaultGame);
    GUI.removeGrid();
    GUI.grid = new Grid2D();
    GUI.gridPanel.add(GUI.grid);
    GUI.grid.update();
    GUI.mainPanel.validate();
    try
    {
      GUI.buttonPanel.play.pause();
    }
    catch (NullPointerException npe) {}
    GUI.rules1D.setEnabled(false);
    GUI.config2D.setEnabled(true);
  }

  public static void initGame1D() {
    GUI.game = new Game1D(GUI.width);
    GUI.removeGrid();
    GUI.grid = new Grid1D();
    GUI.gridPanel.add(GUI.grid);
    GUI.mainPanel.validate();
    GUI.buttonPanel.play.pause();
    GUI.rules1D.setEnabled(true);
    GUI.config2D.setEnabled(false);
  }

  private static void removeGrid() {
    try{
      GUI.gridPanel.remove(GUI.grid);
    }
    catch(Exception e) {}
  }
}
