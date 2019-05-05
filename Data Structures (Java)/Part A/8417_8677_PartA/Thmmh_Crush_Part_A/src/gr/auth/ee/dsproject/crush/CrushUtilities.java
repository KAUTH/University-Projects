/* 1. Name : Eythymios Topalidhs     A.E.M. :8417    PhoneNumber :6989622188   Email :eatopalid@auth.gr
 
   2. Name : Konstantinos Papadopoylos     A.E.M. :8677    PhoneNumber :6951918979   Email :konserpap@auth.gr  
 
 */




/**
 *
 */
package gr.auth.ee.dsproject.crush;

import java.util.ArrayList;

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
public class CrushUtilities
{
  // Color static variables
  final static public int RED = 0;
  final static public int GREEN = 1;
  final static public int BLUE = 2;
  final static public int YELLOW = 3;
  final static public int BLACK = 4;
  final static public int PURPLE = 5;
  final static public int CYAN = 6;
  final static public int blueplayer = 7;
  final static public int redplayer = 8;

  // Tile Ids constant
  public static int tileId = 1;

  // Direction static variables
  final static public int LEFT = 0;
  final static public int DOWN = 1;
  final static public int RIGHT = 2;
  final static public int UP = 3;

  // Window static variables
  public static final int windowWIDTH = 580;
  public static final int windowHEIGHT = 550;

  // Board static variables
  public static final int NUMBER_OF_ROWS = 20;
  public static final int NUMBER_OF_COLUMNS = 10;
  public static final boolean boarders = true;

  public static final int stepLimit = 1000;
  public static int timeStep = 100;
  public static int scoreLimit = 300;

  public static int[] getRandomMove (ArrayList<int[]> moves, int index)
  {
    return moves.get(index);
  }

}
