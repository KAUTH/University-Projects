/* 1. Name : Eythymios Topalidhs     A.E.M. :8417    PhoneNumber :6989622188   Email :eatopalid@auth.gr
 
   2. Name : Konstantinos Papadopoylos     A.E.M. :8677    PhoneNumber :6951918979   Email :konserpap@auth.gr  
 
 */



package gr.auth.ee.dsproject.crush;

import gr.auth.ee.dsproject.crush.board.Board;
import gr.auth.ee.dsproject.crush.player.AbstractPlayer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * <p>
 * Title: DataStructures2006
 * </p>
 * 
 * <p>
 * Description: Data Structures project: year 2011-2012
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company: A.U.Th.
 * </p>
 * 
 * @author Michael T. Tsapanos
 * @version 1.0
 */

public class MainPlatform
{

  private static int limit;

  protected static JFrame frame;
  protected static Board board;

  private static JComboBox teamOne;
  private static JComboBox teamTwo;

  private static JButton generateMaze;
  private static JButton chase;
  private static JButton swap_chase;
  private static JButton quit;
  private static JSlider gamespeed;

  private static String preyIcon = "pacman.gif";
  private static String predatorIcon = "ghosts.gif";

  private static AbstractPlayer playerA;
  private static AbstractPlayer playerB;

  private static String PlayerOne =
    "gr.auth.ee.dsproject.crush.player.RandomPlayer";
  private static String PlayerTwo =
    "gr.auth.ee.dsproject.crush.player.RandomPlayer";

  private static String filename = " ";

  private static String[] teamNames = { "Team 0.00"/* , "Team Mike" */};
  private static String[] teamClasses =
    { "gr.auth.ee.dsproject.crush.player.RandomPlayer",
    /* "gr.auth.ee.dsproject.crush.player.MikePlayer" */};

  /*
   * private static boolean preyCaught(Labyrinth lab, int numOfGhosts,
   * int[]preyPrevPos, int [] preyCurrPos, int predPrevPos[][], int [][]
   * predCurrPos) { boolean answer = false;
   * 
   * for (int i=0; i < numOfGhosts; i++){
   * 
   * if (preyCurrPos[0] == predCurrPos[i][0] && preyCurrPos[1] ==
   * predCurrPos[i][1] ) { answer = true; } else answer = false; }
   * //maze.returnLabyrinth
   * ()[predatorPreviousPos[0]][predatorPreviousPos[1]].isPrey() ||
   * !maze.returnLabyrinth
   * ()[preyPreviousPos[0]][preyPreviousPos[1]].isPredator()) return answer; }
   */

  private static String showScore ()
  {
    // Retrieve all Elements, for transformation
    Vector prey_number = new Vector(1, 1);
    Vector prey_AEM = new Vector(1, 1);
    Vector prey_Score = new Vector(1, 1);
    Vector predator_number = new Vector(1, 1);
    Vector predator_AEM = new Vector(1, 1);
    Vector predator_Score = new Vector(1, 1);
    Vector number_of_steps = new Vector(1, 1);

    File inputFile = new File(filename);
    try {
      BufferedReader r =
        new BufferedReader(
                           new InputStreamReader(new FileInputStream(inputFile)));
      String line;
      while ((line = r.readLine()) != null) {
        // For each line, retrieve the elements...
        StringTokenizer parser = new StringTokenizer(line, "\t");
        String str_prey_number = parser.nextToken();
        String str_prey_AEM = parser.nextToken();
        String str_prey_Score = parser.nextToken();
        String str_predator_number = parser.nextToken();
        String str_predator_AEM = parser.nextToken();
        String str_predator_Score = parser.nextToken();

        if (prey_number.contains(str_prey_number)) {
          int prey_pos = prey_number.indexOf(str_prey_number);
          float previous_score =
            (float) (Float
                    .parseFloat(prey_Score.elementAt(prey_pos).toString()));
          float current_score = (float) (Float.parseFloat(str_prey_Score));
          float final_score = previous_score + current_score;
          prey_Score.removeElementAt(prey_pos);
          prey_Score.insertElementAt(final_score + "", prey_pos);
        }
        else {
          prey_number.add(str_prey_number);
          prey_AEM.add(str_prey_AEM);
          prey_Score.add(str_prey_Score);
        }

        if (predator_number.contains(str_predator_number)) {
          int predator_pos = predator_number.indexOf(str_predator_number);
          float previous_score =
            (float) (Float.parseFloat(predator_Score.elementAt(predator_pos)
                    .toString()));
          float current_score = (float) (Float.parseFloat(str_predator_Score));
          float final_score = previous_score + current_score;
          predator_Score.removeElementAt(predator_pos);
          predator_Score.insertElementAt(final_score + "", predator_pos);
        }
        else {
          predator_number.add(str_predator_number);
          predator_AEM.add(str_predator_AEM);
          predator_Score.add(str_predator_Score);
        }

      }
    }
    catch (IOException ioException) {
      System.out.println(ioException);
    }

    String output =
      " TEAM No         TEAM Name        Score (Blue)   Score (Red)   FINAL \n=======================================================\n";

    for (int i = 0; i < prey_number.size(); i++) {
      String pr_team_number = prey_number.elementAt(i).toString();
      float pr_score =
        (float) (Float.parseFloat(prey_Score.elementAt(i).toString()));
      float pd_score = 0;
      int other_pos = predator_number.indexOf(pr_team_number);
      if (other_pos != -1) {
        pd_score =
          (float) (Float.parseFloat(predator_Score.elementAt(other_pos)
                  .toString()));
      }
      float score = pr_score + pd_score;

      output +=
        pr_team_number + "       " + prey_AEM.elementAt(i).toString()
                + "           ";
      output +=
        pr_score + "                " + pd_score + "                 " + score
                + "\n";
    }

    for (int i = 0; i < predator_number.size(); i++) {
      String pd_team_number = predator_number.elementAt(i).toString();
      if (prey_number.contains(pd_team_number)) {

      }
      else {
        float pd_score =
          (float) (Float.parseFloat(predator_Score.elementAt(i).toString()));
        float pr_score = 0;
        int other_pos = prey_number.indexOf(pd_team_number);
        if (other_pos != -1) {
          pr_score =
            (float) (Float.parseFloat(prey_Score.elementAt(other_pos)
                    .toString()));
        }
        float score = pr_score + pd_score;

        output +=
          pd_team_number + "       " + predator_AEM.elementAt(i).toString()
                  + "           ";
        output +=
          pr_score + "                " + pd_score + "                 "
                  + score + "\n";
      }
    }
    return output;

  }

  private static void createAndShowGUI ()
  {

    JFrame.setDefaultLookAndFeelDecorated(false);

    frame = new JFrame("Crush Board");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    board = new Board();
    JPanel buttonPanel = new JPanel();
    BoxLayout horizontal = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
    JPanel teamsPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    generateMaze = new JButton("Generate Board");
    chase = new JButton("Play");
    chase.setEnabled(false);
    swap_chase = new JButton("Swap & Play");
    quit = new JButton("Quit");

    gamespeed = new JSlider(JSlider.HORIZONTAL, 1, 3000, 1500);
    gamespeed.addChangeListener(new SliderListener());

    gamespeed.setMajorTickSpacing(10);
    gamespeed.setPaintTicks(true);
    Font font = new Font("Serif", Font.ITALIC, 15);
    gamespeed.setFont(font);
    Hashtable labelTable = new Hashtable();
    labelTable.put(new Integer(1), new JLabel("Fast"));
    labelTable.put(new Integer(3000), new JLabel("Slow"));
    gamespeed.setLabelTable(labelTable);

    gamespeed.setPaintLabels(true);

    teamOne = new JComboBox(teamNames);
    teamTwo = new JComboBox(teamNames);
    teamOne.setSelectedIndex(0);
    teamTwo.setSelectedIndex(0);

    JLabel label = new JLabel("THE THMMY CRUSH GAME!!!", JLabel.CENTER);
    centerPanel.setLayout(new BorderLayout());
    centerPanel.add("North", label);
    centerPanel.add("Center", gamespeed);

    teamsPanel.setLayout(new BorderLayout());
    teamsPanel.add("West", teamOne);
    teamsPanel.add("East", teamTwo);
    teamsPanel.add("Center", centerPanel);
    teamsPanel.add("South", buttonPanel);

    buttonPanel.add(generateMaze);
    buttonPanel.add(chase);
    buttonPanel.add(swap_chase);
    buttonPanel.add(quit);

    frame.setLayout(new BorderLayout());
    frame.add("Center", teamsPanel);
    frame.add("South", buttonPanel);

    frame.pack();
    frame.setVisible(true);

    // ---------ActionListeners------//
    quit.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent evt)
      {
        System.exit(0);
      }
    });

    generateMaze.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent evt)
      {
        chase.setEnabled(false);
        swap_chase.setEnabled(false);
        generateMaze.setEnabled(false);

        frame.repaint();
        frame.remove(board);
        board =
          new Board(CrushUtilities.NUMBER_OF_COLUMNS,
                    CrushUtilities.NUMBER_OF_ROWS, CrushUtilities.windowWIDTH,
                    CrushUtilities.windowHEIGHT);
        board.initializeBoard();

        // board.printBoard();

        PlayerOne = teamClasses[teamOne.getSelectedIndex()];
        PlayerTwo = teamClasses[teamTwo.getSelectedIndex()];

        frame.add("North", board);
        frame.pack();
        chase.setEnabled(true);
        swap_chase.setEnabled(false);
        generateMaze.setEnabled(false);
      }
    });

    chase.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent evt)
      {

        chase.setEnabled(false);
        swap_chase.setEnabled(false);
        generateMaze.setEnabled(false);

        Thread t = new Thread(new Runnable() {
          public void run ()
          {

            // Get the players
            playerA = null;
            try {
              Class playerAClass = Class.forName(PlayerOne);
              Class partypes[] = new Class[1];
              partypes[0] = Integer.class;

              Constructor playerAArgsConstructor =
                playerAClass.getConstructor(partypes);
              Object arglist[] = new Object[1];
              arglist[0] = new Integer(1);
              Object playerObject = playerAArgsConstructor.newInstance(arglist);

              playerA = (AbstractPlayer) playerObject;

            }
            catch (ClassNotFoundException ex) {
              ex.printStackTrace();
            }
            catch (IllegalAccessException ex) {
              ex.printStackTrace();
            }
            catch (InstantiationException ex) {
              ex.printStackTrace();
            }
            catch (NoSuchMethodException ex) {
              ex.printStackTrace();
            }
            catch (InvocationTargetException ex) {
              ex.printStackTrace();
            }

            playerB = null;
            try {
              Class playerBClass = Class.forName(PlayerTwo);
              Class partypes[] = new Class[1];
              partypes[0] = Integer.class;
              Constructor playerBArgsConstructor =
                playerBClass.getConstructor(partypes);
              Object arglist[] = new Object[1];
              arglist[0] = new Integer(2);
              Object playerObject = playerBArgsConstructor.newInstance(arglist);
              playerB = (AbstractPlayer) playerObject;
            }
            catch (ClassNotFoundException ex) {
              ex.printStackTrace();
            }
            catch (IllegalAccessException ex) {
              ex.printStackTrace();
            }
            catch (InstantiationException ex) {
              ex.printStackTrace();
            }
            catch (NoSuchMethodException ex) {
              ex.printStackTrace();
            }
            catch (InvocationTargetException ex) {
              ex.printStackTrace();
            }

            filename = playerA.getName() + "_" + playerB.getName() + ".txt";

            boolean end = false;

            while (limit < CrushUtilities.stepLimit) {

              frame.remove(board);

              // -----Move for PlayerA----------------
              ArrayList<int[]> availMoves = new ArrayList<int[]>();
              availMoves = board.checkForTriples(board);
              while (availMoves.isEmpty()) {
                board.resetBoard();
                availMoves = board.checkForTriples(board);

              }

              int movesOfA[] = new int[4];
              movesOfA = playerA.getNextMove(availMoves, board);

              if (board.validMove(movesOfA, availMoves)) {

                board.moveTile(movesOfA[0], movesOfA[1], movesOfA[2],
                               movesOfA[3]);

                frame.add("North", board);
                frame.validate();
                frame.pack();
                frame.repaint();
                try {
                  Thread.sleep(CrushUtilities.timeStep);
                }
                catch (InterruptedException e) {
                }

                // Prosthese to pointsOfA sto scor toy playerA
                int pointsOfA = 0;
                // for (int j = 0; j < 10; j++) {
                while (true) {
                  // board.printBoard();
                  int currentPoints = board.findCreatedNples(board);

                  if (currentPoints == 0)
                    break;
                  pointsOfA = pointsOfA + currentPoints;
                  board.paintMarkedTiles(board, playerA.getId());
                  playerA.setScore(playerA.getScore() + pointsOfA);

                  board.getPlayersScore(playerA.getScore(), pointsOfA,
                                        playerB.getScore(), 0);

                  frame.add("North", board);
                  frame.validate();
                  frame.pack();
                  frame.repaint();

                  try {
                    Thread.sleep(CrushUtilities.timeStep);
                  }
                  catch (InterruptedException e) {
                  }

                  board.removeMarkedTiles(board);

                  frame.add("North", board);
                  frame.validate();
                  frame.pack();
                  frame.repaint();
                  board.getPlayersScore(playerA.getScore(), pointsOfA,
                                        playerB.getScore(), 0);
                }

                if (playerA.getScore() >= CrushUtilities.scoreLimit)
                  end = true;

              }

              if (end)
                break;

              // -----Move for PlayerB----------------

              frame.remove(board);

              availMoves.clear();
              availMoves = new ArrayList<int[]>();
              availMoves = board.checkForTriples(board);
              while (availMoves.isEmpty()) {
                board.resetBoard();
                availMoves = board.checkForTriples(board);

              }

              int movesOfB[] = new int[4];
              movesOfB = playerB.getNextMove(availMoves, board);

              if (board.validMove(movesOfB, availMoves)) {

                board.moveTile(movesOfB[0], movesOfB[1], movesOfB[2],
                               movesOfB[3]);

                frame.add("North", board);
                frame.validate();
                frame.pack();
                frame.repaint();
                try {
                  Thread.sleep(CrushUtilities.timeStep);
                }
                catch (InterruptedException e) {
                }

                // Prosthese to pointsOfA sto scor toy playerA
                int pointsOfB = 0;
                // for (int j = 0; j < 10; j++) {
                while (true) {
                  // board.printBoard();
                  int currentPoints = board.findCreatedNples(board);

                  if (currentPoints == 0)
                    break;
                  pointsOfB = pointsOfB + currentPoints;
                  board.paintMarkedTiles(board, playerB.getId());
                  playerB.setScore(playerB.getScore() + pointsOfB);

                  board.getPlayersScore(playerA.getScore(), 0,
                                        playerB.getScore(), pointsOfB);

                  frame.add("North", board);
                  frame.validate();
                  frame.pack();
                  frame.repaint();

                  try {
                    Thread.sleep(CrushUtilities.timeStep);
                  }
                  catch (InterruptedException e) {
                  }

                  board.removeMarkedTiles(board);

                  frame.add("North", board);
                  frame.validate();
                  frame.pack();
                  frame.repaint();
                  board.getPlayersScore(playerA.getScore(), 0,
                                        playerB.getScore(), pointsOfB);
                }

                if (playerB.getScore() >= CrushUtilities.scoreLimit)
                  end = true;

              }

              if (end)
                break;
              CrushUtilities.timeStep = gamespeed.getValue();

              try {
                Thread.sleep(CrushUtilities.timeStep);
              }
              catch (InterruptedException e) {
              }

            }

            try {

              BufferedWriter out =
                new BufferedWriter(new FileWriter(filename, true));

              if (playerA.getScore() > playerB.getScore()) {

                // System.out.println("NO WINNER (TIE)!!!   Number of Steps: "
                // +
                // limit);
                JOptionPane.showMessageDialog(null, "WINNER IS (Blue Player): "
                                                    + playerA.getName()
                                                    + "   Score of winner: "
                                                    + playerA.getScore(),
                                              "Results...",
                                              JOptionPane.INFORMATION_MESSAGE,
                                              new ImageIcon(preyIcon));
              }
              else {
                // System.out.println("WINNER IS (predator): " +
                // predator.getName() + "   Number of Steps: " +
                // limit);
                JOptionPane.showMessageDialog(null, "WINNER IS (Red Player): "
                                                    + playerB.getName()
                                                    + "   Score of winner: "
                                                    + playerB.getScore(),
                                              "Results...",
                                              JOptionPane.INFORMATION_MESSAGE,
                                              new ImageIcon(predatorIcon));

              }
              out.write(teamNames[teamOne.getSelectedIndex()] + "\t"
                        + playerA.getName() + "\t" + playerA.getScore() + "\t"
                        + teamNames[teamTwo.getSelectedIndex()] + "\t"
                        + playerB.getName() + "\t" + playerB.getScore() + "\t"
                        + "\n");
              out.close();
            }
            catch (IOException ioExc) {

            }

            chase.setEnabled(false);
            swap_chase.setEnabled(true);
            generateMaze.setEnabled(false);

          }
        });
        t.start();
      }
    });

    swap_chase.addActionListener(new ActionListener() {
      public void actionPerformed (ActionEvent evt)
      {
        chase.setEnabled(false);
        swap_chase.setEnabled(false);
        generateMaze.setEnabled(false);

        Thread t = new Thread(new Runnable() {
          public void run ()
          {

            // Get the players
            playerA = null;
            try {
              Class playerAClass = Class.forName(PlayerTwo);
              Class partypes[] = new Class[1];
              partypes[0] = Integer.class;

              Constructor playerAArgsConstructor =
                playerAClass.getConstructor(partypes);
              Object arglist[] = new Object[1];
              arglist[0] = new Integer(1);
              Object playerObject = playerAArgsConstructor.newInstance(arglist);

              playerA = (AbstractPlayer) playerObject;

            }
            catch (ClassNotFoundException ex) {
              ex.printStackTrace();
            }
            catch (IllegalAccessException ex) {
              ex.printStackTrace();
            }
            catch (InstantiationException ex) {
              ex.printStackTrace();
            }
            catch (NoSuchMethodException ex) {
              ex.printStackTrace();
            }
            catch (InvocationTargetException ex) {
              ex.printStackTrace();
            }

            playerB = null;
            try {
              Class playerBClass = Class.forName(PlayerOne);
              Class partypes[] = new Class[1];
              partypes[0] = Integer.class;
              Constructor playerBArgsConstructor =
                playerBClass.getConstructor(partypes);
              Object arglist[] = new Object[1];
              arglist[0] = new Integer(2);
              Object playerObject = playerBArgsConstructor.newInstance(arglist);
              playerB = (AbstractPlayer) playerObject;
            }
            catch (ClassNotFoundException ex) {
              ex.printStackTrace();
            }
            catch (IllegalAccessException ex) {
              ex.printStackTrace();
            }
            catch (InstantiationException ex) {
              ex.printStackTrace();
            }
            catch (NoSuchMethodException ex) {
              ex.printStackTrace();
            }
            catch (InvocationTargetException ex) {
              ex.printStackTrace();
            }

            boolean end = false;

            while (limit < CrushUtilities.stepLimit) {

              frame.remove(board);

              // -----Move for PlayerA----------------
              ArrayList<int[]> availMoves = new ArrayList<int[]>();
              availMoves = board.checkForTriples(board);
              while (availMoves.isEmpty()) {
                board.resetBoard();
                availMoves = board.checkForTriples(board);

              }

              int movesOfA[] = new int[4];
              movesOfA = playerA.getNextMove(availMoves, board);

              if (board.validMove(movesOfA, availMoves)) {

                board.moveTile(movesOfA[0], movesOfA[1], movesOfA[2],
                               movesOfA[3]);

                frame.add("North", board);
                frame.validate();
                frame.pack();
                frame.repaint();
                try {
                  Thread.sleep(CrushUtilities.timeStep);
                }
                catch (InterruptedException e) {
                }

                // Prosthese to pointsOfA sto scor toy playerA
                int pointsOfA = 0;
                // for (int j = 0; j < 10; j++) {
                while (true) {
                  // board.printBoard();
                  int currentPoints = board.findCreatedNples(board);

                  if (currentPoints == 0)
                    break;
                  pointsOfA = pointsOfA + currentPoints;
                  board.paintMarkedTiles(board, playerB.getId());
                  playerA.setScore(playerA.getScore() + pointsOfA);

                  board.getPlayersScore(playerA.getScore(), pointsOfA,
                                        playerB.getScore(), 0);

                  frame.add("North", board);
                  frame.validate();
                  frame.pack();
                  frame.repaint();

                  try {
                    Thread.sleep(CrushUtilities.timeStep);
                  }
                  catch (InterruptedException e) {
                  }

                  board.removeMarkedTiles(board);

                  frame.add("North", board);
                  frame.validate();
                  frame.pack();
                  frame.repaint();
                  board.getPlayersScore(playerA.getScore(), pointsOfA,
                                        playerB.getScore(), 0);
                }

                if (playerA.getScore() >= CrushUtilities.scoreLimit)
                  end = true;

              }

              if (end)
                break;

              // -----Move for PlayerB----------------

              frame.remove(board);

              availMoves.clear();
              availMoves = new ArrayList<int[]>();
              availMoves = board.checkForTriples(board);
              while (availMoves.isEmpty()) {
                board.resetBoard();
                System.out.println("Board Initialized");
                availMoves = board.checkForTriples(board);

              }

              int movesOfB[] = new int[4];
              movesOfB = playerB.getNextMove(availMoves, board);

              if (board.validMove(movesOfB, availMoves)) {

                board.moveTile(movesOfB[0], movesOfB[1], movesOfB[2],
                               movesOfB[3]);

                frame.add("North", board);
                frame.validate();
                frame.pack();
                frame.repaint();
                try {
                  Thread.sleep(CrushUtilities.timeStep);
                }
                catch (InterruptedException e) {
                }

                // Prosthese to pointsOfA sto scor toy playerA
                int pointsOfB = 0;
                // for (int j = 0; j < 10; j++) {
                while (true) {
                  // board.printBoard();
                  int currentPoints = board.findCreatedNples(board);

                  if (currentPoints == 0)
                    break;
                  pointsOfB = pointsOfB + currentPoints;
                  board.paintMarkedTiles(board, playerA.getId());
                  playerB.setScore(playerB.getScore() + pointsOfB);

                  board.getPlayersScore(playerA.getScore(), 0,
                                        playerB.getScore(), pointsOfB);

                  frame.add("North", board);
                  frame.validate();
                  frame.pack();
                  frame.repaint();

                  try {
                    Thread.sleep(CrushUtilities.timeStep);
                  }
                  catch (InterruptedException e) {
                  }

                  board.removeMarkedTiles(board);

                  frame.add("North", board);
                  frame.validate();
                  frame.pack();
                  frame.repaint();
                  board.getPlayersScore(playerA.getScore(), 0,
                                        playerB.getScore(), pointsOfB);
                }

                if (playerB.getScore() >= CrushUtilities.scoreLimit)
                  end = true;

              }

              if (end)
                break;
              CrushUtilities.timeStep = gamespeed.getValue();

              try {
                Thread.sleep(CrushUtilities.timeStep);
              }
              catch (InterruptedException e) {
              }

            }

            try {

              BufferedWriter out =
                new BufferedWriter(new FileWriter(filename, true));
              if (playerA.getScore() > playerB.getScore()) {

                // System.out.println("NO WINNER (TIE)!!!   Number of Steps: "
                // +
                // limit);
                JOptionPane.showMessageDialog(null, "WINNER IS (Blue Player): "
                                                    + playerA.getName()
                                                    + "   Score of winner: "
                                                    + playerA.getScore(),
                                              "Results...",
                                              JOptionPane.INFORMATION_MESSAGE,
                                              new ImageIcon(preyIcon));
              }
              else {

                // System.out.println("WINNER IS (predator): " +
                // predator.getName() + "   Number of Steps: " +
                // limit);
                JOptionPane.showMessageDialog(null, "WINNER IS (Red Player): "
                                                    + playerB.getName()
                                                    + "   Score of winner: "
                                                    + playerB.getScore(),
                                              "Results...",
                                              JOptionPane.INFORMATION_MESSAGE,
                                              new ImageIcon(predatorIcon));

              }
              out.write(teamNames[teamTwo.getSelectedIndex()] + "\t"
                        + playerA.getName() + "\t" + playerA.getScore() + "\t"
                        + teamNames[teamOne.getSelectedIndex()] + "\t"
                        + playerB.getName() + "\t" + playerB.getScore() + "\t"
                        + "\n");
              out.close();
            }
            catch (IOException ioExc) {

            }

            chase.setEnabled(true);
            swap_chase.setEnabled(true);
            generateMaze.setEnabled(true);
            JOptionPane.showMessageDialog(null, showScore(), "SCORE TABLE",
                                          JOptionPane.INFORMATION_MESSAGE);

          }
        });
        t.start();
      }
    });

  }

  public static void main (String[] args)
  {
    // TODO Auto-generated method stub
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run ()
      {
        createAndShowGUI();
      }
    });

  }

  public static AbstractPlayer getPlayerA ()
  {
    return playerA;
  }

  public static AbstractPlayer getPlayerB ()
  {
    return playerB;
  }

}
