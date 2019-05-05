/* 1. Name : Eythymios Topalidhs     A.E.M. :8417    PhoneNumber :6989622188   Email :eatopalid@auth.gr
 
   2. Name : Konstantinos Papadopoylos     A.E.M. :8677    PhoneNumber :6951918979   Email :konserpap@auth.gr  
 
 */



package gr.auth.ee.dsproject.crush.board;

import gr.auth.ee.dsproject.crush.CrushUtilities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel
{

  protected int rows;
  protected int cols;
  private int WIDTH;
  private int HEIGHT;
  private ArrayList<ArrayList<Tile>> fullBoard;
  private int[] scores;

  private int graphics_WIDTH;
  private int graphics_HEIGHT;

  @Override
  public void paintComponent (Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    // You add/change the statements here to draw
    // the picture you want.

    g2.setColor(Color.BLACK);

    Stroke drawingStroke =
      new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0);
    g2.setStroke(drawingStroke);
    for (int j = (rows / 2) - 1; j >= 0; j--) {

      for (int i = 0; i < cols; i++) {

        if (getTile(i, j).getColor() == CrushUtilities.BLACK)
          g2.setColor(Color.BLACK);

        if (getTile(i, j).getColor() == CrushUtilities.BLUE)
          g2.setColor(Color.BLUE);

        if (getTile(i, j).getColor() == CrushUtilities.GREEN)
          g2.setColor(Color.GREEN);

        if (getTile(i, j).getColor() == CrushUtilities.RED)
          g2.setColor(Color.RED);

        if (getTile(i, j).getColor() == CrushUtilities.YELLOW)
          g2.setColor(Color.YELLOW);

        if (getTile(i, j).getColor() == CrushUtilities.PURPLE)
          g2.setColor(Color.MAGENTA);

        if (getTile(i, j).getColor() == CrushUtilities.CYAN)
          g2.setColor(Color.cyan);

        if (getTile(i, j).getColor() == CrushUtilities.blueplayer)
          g2.setColor(Color.BLUE);

        if (getTile(i, j).getColor() == CrushUtilities.redplayer)
          g2.setColor(Color.RED);

        if (getTile(i, j).getColor() == CrushUtilities.redplayer
            || getTile(i, j).getColor() == CrushUtilities.blueplayer)
          g.fillRect((2 * graphics_WIDTH + 1) + (i * graphics_WIDTH + 1),
                     (cols * graphics_HEIGHT + 1) - (j * graphics_HEIGHT + 1),
                     graphics_WIDTH, graphics_HEIGHT);

        else
          g2.fillOval((2 * graphics_WIDTH + 1) + (i * graphics_WIDTH + 1),
                      (cols * graphics_HEIGHT + 1) - (j * graphics_HEIGHT + 1),
                      graphics_WIDTH, graphics_HEIGHT);

      }

    }

    for (int j = (rows / 2) - 1; j >= 0; j--) {

      for (int i = 0; i < cols; i++) {

        g2.setColor(Color.BLACK);

        g2.drawLine((2 * graphics_WIDTH + 1) + (i * graphics_WIDTH + 1),
                    (cols * graphics_HEIGHT + 1) - (j * graphics_HEIGHT + 1),
                    (2 * graphics_WIDTH + 1) + (i * graphics_WIDTH + 1)
                            + graphics_WIDTH, (cols * graphics_HEIGHT + 1)
                                              - (j * graphics_HEIGHT + 1));

        g2.drawLine((2 * graphics_WIDTH + 1) + (i * graphics_WIDTH + 1),
                    (cols * graphics_HEIGHT + 1) - (j * graphics_HEIGHT + 1),
                    (2 * graphics_WIDTH + 1) + (i * graphics_WIDTH + 1),
                    (cols * graphics_HEIGHT + 1) - (j * graphics_HEIGHT + 1)
                            + graphics_WIDTH);

        g2.drawLine((2 * graphics_WIDTH + 1) + (i * graphics_WIDTH + 1)
                            + graphics_WIDTH, (cols * graphics_HEIGHT + 1)
                                              - (j * graphics_HEIGHT + 1),
                    (2 * graphics_WIDTH + 1) + (i * graphics_WIDTH + 1)
                            + graphics_WIDTH, (cols * graphics_HEIGHT + 1)
                                              - (j * graphics_HEIGHT + 1)
                                              + graphics_WIDTH);

        g2.drawLine((2 * graphics_WIDTH + 1) + (i * graphics_WIDTH + 1),
                    (cols * graphics_HEIGHT + 1) - (j * graphics_HEIGHT + 1)
                            + graphics_WIDTH, (2 * graphics_WIDTH + 1)
                                              + (i * graphics_WIDTH + 1)
                                              + graphics_WIDTH,
                    (cols * graphics_HEIGHT + 1) - (j * graphics_HEIGHT + 1)
                            + graphics_WIDTH);

      }
    }
    g2.setFont(new Font("default", Font.BOLD, 16));
    g2.setColor(Color.BLUE);
    g2.drawString("     Player A", 10, 480);
    g2.drawString("Move Score: " + scores[0], 10, 500);
    g2.drawString("Total Score: " + scores[1], 10, 520);

    g2.setColor(Color.RED);
    g2.drawString("     Player B", 450, 480);
    g2.drawString("Move Score: " + scores[2], 450, 500);
    g2.drawString("Total Score: " + scores[3], 450, 520);

  }

  @Override
  public Dimension getPreferredSize ()
  {
    return new Dimension(WIDTH, HEIGHT);
  }

  public Board ()
  {

  }

  public Board (int cols, int rows, int width, int height)
  {

    this.graphics_WIDTH = 40;
    this.graphics_HEIGHT = 40;
    scores = new int[4];
    scores[0] = 0;
    scores[1] = 0;
    scores[2] = 0;
    scores[3] = 0;

    this.cols = cols;
    this.rows = rows;
    this.HEIGHT = height;
    this.WIDTH = width;

    fullBoard = new ArrayList<ArrayList<Tile>>();
    for (int i = 0; i < cols; i++) {
      fullBoard.add(new ArrayList<Tile>());
    }
  }

  public void initializeBoard ()
  {

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {

        setTile(CrushUtilities.tileId++, j, i, (int) (Math.random() * 7), false);
        while (checkForInitialTriple(j, i, CrushUtilities.LEFT)
               || checkForInitialTriple(j, i, CrushUtilities.DOWN))
          getTile(j, i).setColor((int) (Math.random() * 7));

      }
    }
  }

  public void resetBoard ()
  {
    for (int i = 0; i < cols; i++)
      fullBoard.get(i).clear();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {

        setTile(CrushUtilities.tileId++, j, i, (int) (Math.random() * 7), false);
        while (checkForInitialTriple(j, i, CrushUtilities.LEFT)
               || checkForInitialTriple(j, i, CrushUtilities.DOWN))
          getTile(j, i).setColor((int) (Math.random() * 7));

      }
    }

    System.out.println("NO MORE MOVES! BOARD RESETED");
  }

  public int getCols ()
  {
    return cols;
  }

  public int getRows ()
  {
    return rows;
  }

  public void setTile (int id, int x, int y, int color, boolean mark)
  {
    fullBoard.get(x).add(new Tile(id, x, y, color, mark));
  }

  public Tile getTile (int x, int y)
  {
    return fullBoard.get(x).get(y);
  }

  public void removeTile (int x, int y)
  {
    fullBoard.get(x).remove(y);
    // fullBoard.get(x).add(new Tile(x,rows-1));
    setTile(CrushUtilities.tileId++, x, rows - 1, (int) (Math.random() * 7),
            false);

    for (int i = 0; i < cols; i++) {

      for (int j = 0; j < rows; j++) {

        fullBoard.get(i).get(j).setX(i);
        fullBoard.get(i).get(j).setY(j);

      }

    }

  }

  public boolean checkForInitialTriple (int x, int y, int direction)
  {
    if (direction == CrushUtilities.DOWN) {

      if (y < 2)
        return false;
      else {

        if (fullBoard.get(x).get(y).getColor() == fullBoard.get(x).get(y - 1)
                .getColor()
            && fullBoard.get(x).get(y).getColor() == fullBoard.get(x)
                    .get(y - 2).getColor())
          return true;
        else
          return false;
      }
    }

    if (direction == CrushUtilities.LEFT) {
      if (x < 2)
        return false;
      else {

        if (fullBoard.get(x).get(y).getColor() == fullBoard.get(x - 1).get(y)
                .getColor()
            && fullBoard.get(x).get(y).getColor() == fullBoard.get(x - 2)
                    .get(y).getColor())
          return true;
        else
          return false;

      }

    }

    return false;

  }

  public void printBoard ()
  {

    for (int j = rows - 1; j >= 0; j--) {

      for (int i = 0; i < cols; i++) {

        System.out.print(getTile(i, j).getColor() + "/" + getTile(i, j).getId()
                         + "/" + getTile(i, j).getX() + "/"
                         + getTile(i, j).getY() + " ");

      }

      System.out.println();

    }

  }

  public ArrayList<int[]> checkForTriples (Board board)
  {
    ArrayList<int[]> availTriples = new ArrayList<int[]>();
    availTriples.clear();

    int x = board.getCols();
    int y = board.getRows() / 2;

    for (int i = 0; i < x; i++) {
      for (int j = 0; j < y; j++) {

        if (board.avTriplesUp(board, board.getTile(i, j))) {
          int[] a = { i, j, CrushUtilities.UP };
          availTriples.add(a);
        }

        if (board.avTriplesDown(board, board.getTile(i, j))) {
          int[] a = { i, j, CrushUtilities.DOWN };
          availTriples.add(a);
        }

        if (board.avTriplesLeft(board, board.getTile(i, j))) {
          int[] a = { i, j, CrushUtilities.LEFT };
          availTriples.add(a);
        }

        if (board.avTriplesRight(board, board.getTile(i, j))) {
          int[] a = { i, j, CrushUtilities.RIGHT };
          availTriples.add(a);
        }

      }
    }

    // for (int i = 0; i < availTriples.size(); i++) {
    // int[] b = availTriples.get(i);
    // System.out.print(b[0] + " " + b[1] + " " + b[2]);
    // System.out.println();
    // }
    // System.out.println("-----");

    return availTriples;

  }

  public boolean avTriplesUp (Board board, Tile tile)
  {
    int x = tile.getX();
    int y = tile.getY();
    int color = tile.getColor();
    int sColumns = board.getCols() - 1;
    int sRows = board.getRows() / 2 - 1;

    if ((y <= ((sRows) - 3) && color == board.getTile(x, y + 2).getColor() && color == board
            .getTile(x, y + 3).getColor()))
      return true;

    if (x >= 2 && y <= (sRows - 1)
        && color == board.getTile(x - 2, y + 1).getColor()
        && color == board.getTile(x - 1, y + 1).getColor())
      return true;

    if (x >= 1 && y <= (sRows - 1) && x <= sColumns - 1
        && color == board.getTile(x - 1, y + 1).getColor()
        && color == board.getTile(x + 1, y + 1).getColor())
      return true;

    if (x <= sColumns - 2 && y <= (sRows - 1)
        && color == board.getTile(x + 1, y + 1).getColor()
        && color == board.getTile(x + 2, y + 1).getColor())
      return true;

    return false;

  }

  public boolean avTriplesDown (Board board, Tile tile)
  {
    int x = tile.getX();
    int y = tile.getY();
    int color = tile.getColor();
    int sColumns = board.getCols() - 1;

    if ((y >= 3 && color == board.getTile(x, y - 2).getColor() && color == board
            .getTile(x, y - 3).getColor())
        || (x >= 2 && y >= 1 && color == board.getTile(x - 2, y - 1).getColor() && color == board
                .getTile(x - 1, y - 1).getColor())
        || (x >= 1 && x <= sColumns - 1 && y >= 1
            && color == board.getTile(x - 1, y - 1).getColor() && color == board
                .getTile(x + 1, y - 1).getColor())
        || (x <= sColumns - 2 && y >= 1
            && color == board.getTile(x + 1, y - 1).getColor() && color == board
                .getTile(x + 2, y - 1).getColor()))
      return true;
    else
      return false;

  }

  public boolean avTriplesLeft (Board board, Tile tile)
  {
    int x = tile.getX();
    int y = tile.getY();
    int color = tile.getColor();
    int sColumns = board.getCols() - 1;

    if ((x >= 3 && color == board.getTile(x - 2, y).getColor() && color == board
            .getTile(x - 3, y).getColor())
        || (x >= 1 && y >= 2 && color == board.getTile(x - 1, y - 2).getColor() && color == board
                .getTile(x - 1, y - 1).getColor())
        || (y >= 1 && y <= sColumns - 1 && x >= 1
            && color == board.getTile(x - 1, y - 1).getColor() && color == board
                .getTile(x - 1, y + 1).getColor())
        || (y <= sColumns - 2 && x >= 1
            && color == board.getTile(x - 1, y + 1).getColor() && color == board
                .getTile(x - 1, y + 2).getColor()))
      return true;
    else
      return false;

  }

  public boolean avTriplesRight (Board board, Tile tile)
  {
    int x = tile.getX();
    int y = tile.getY();
    int color = tile.getColor();
    int sColumns = board.getCols() - 1;

    if ((x <= sColumns - 3 && color == board.getTile(x + 2, y).getColor() && color == board
            .getTile(x + 3, y).getColor())
        || (x <= sColumns - 1 && y >= 2
            && color == board.getTile(x + 1, y - 2).getColor() && color == board
                .getTile(x + 1, y - 1).getColor())
        || (y >= 1 && y <= sColumns - 1 && x <= sColumns - 1
            && color == board.getTile(x + 1, y - 1).getColor() && color == board
                .getTile(x + 1, y + 1).getColor())
        || (y <= sColumns - 2 && x <= sColumns - 1
            && color == board.getTile(x + 1, y + 1).getColor() && color == board
                .getTile(x + 1, y + 2).getColor()))
      return true;
    else
      return false;

  }

  public void moveTile (int x1, int y1, int x2, int y2)
  {

    int colorA = getTile(x1, y1).getColor();
    int colorB = getTile(x2, y2).getColor();

    getTile(x1, y1).setColor(colorB);
    getTile(x2, y2).setColor(colorA);

  }

  public int findCreatedNples (Board board)
  {
    int points = 0;
    int countMarked = 0;

    for (int i = 0; i < board.getCols(); i++) {
      for (int j = 0; j < board.getRows() / 2; j++) {
        markNples(board, i, j, CrushUtilities.LEFT);
        markNples(board, i, j, CrushUtilities.DOWN);
      }
    }

    for (int i = 0; i < board.getCols(); i++)
      for (int j = 0; j < board.getRows() / 2; j++)
        if (board.getTile(i, j).getMark())
          countMarked++;

    points = countMarked;
    return points;
  }

  public void markNples (Board board, int x, int y, int dir)
  {

    if (dir == CrushUtilities.DOWN) {

      if (y >= 2) {

        if (board.getTile(x, y).getColor() == board.getTile(x, y - 1)
                .getColor()
            && board.getTile(x, y).getColor() == board.getTile(x, y - 2)
                    .getColor()) {
          board.getTile(x, y).setMark(true);
          board.getTile(x, y - 1).setMark(true);
          board.getTile(x, y - 2).setMark(true);

          if (y > 3
              && board.getTile(x, y).getColor() == board.getTile(x, y - 3)
                      .getColor()) {
            board.getTile(x, y - 3).setMark(true);
            if (y > 4
                && board.getTile(x, y).getColor() == board.getTile(x, y - 4)
                        .getColor()) {
              board.getTile(x, y - 4).setMark(true);
            }
          }

        }

      }

    }

    if (dir == CrushUtilities.LEFT) {
      if (x >= 2) {

        if (board.getTile(x, y).getColor() == board.getTile(x - 1, y)
                .getColor()
            && board.getTile(x, y).getColor() == board.getTile(x - 2, y)
                    .getColor()) {
          board.getTile(x, y).setMark(true);
          board.getTile(x - 1, y).setMark(true);
          board.getTile(x - 2, y).setMark(true);
          if (x > 3
              && board.getTile(x, y).getColor() == board.getTile(x - 3, y)
                      .getColor()) {
            board.getTile(x - 3, y).setMark(true);
            if (x > 4
                && board.getTile(x, y).getColor() == board.getTile(x - 4, y)
                        .getColor()) {
              board.getTile(x - 4, y).setMark(true);
            }
          }

        }

      }

    }

  }

  public void paintMarkedTiles (Board board, int id)
  {
    for (int i = 0; i < board.getCols(); i++) {
      for (int j = 0; j < board.getRows() / 2; j++) {
        if (board.getTile(i, j).getMark()) {
          if (id == 1)
            board.getTile(i, j).setColor(CrushUtilities.blueplayer);
          else
            board.getTile(i, j).setColor(CrushUtilities.redplayer);
        }
      }
    }
  }

  public void removeMarkedTiles (Board board)
  {
    for (int k = 0; k < 3; k++)
      for (int i = 0; i < board.getCols(); i++)
        for (int j = 0; j < board.getRows() / 2; j++)
          if (board.getTile(i, j).getMark())
            board.removeTile(i, j);

  }

  public boolean validMove (int[] move, ArrayList<int[]> availMoves)
  {
    int x = move[0];
    int y = move[1];
    int dir = -1;
    boolean answer = false;

    if (move[3] == move[1] + 1)
      dir = CrushUtilities.UP;

    if (move[3] == move[1] - 1)
      dir = CrushUtilities.DOWN;

    if (move[2] == move[0] - 1)
      dir = CrushUtilities.LEFT;

    if (move[2] == move[0] + 1)
      dir = CrushUtilities.RIGHT;

    for (int[] avMove: availMoves)
      if (x == avMove[0] && y == avMove[1] && dir == avMove[2])
        answer = true;

    return answer;

  }

  public void getPlayersScore (int totalScoreA, int currentScoreA,
                               int totalScoreB, int currentScoreB)
  {
    scores[1] = totalScoreA;
    scores[0] = currentScoreA;
    scores[3] = totalScoreB;
    scores[2] = currentScoreB;
  }

}
