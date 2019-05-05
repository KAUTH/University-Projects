/* 1. Name : Eythymios Topalidhs     A.E.M. :8417    PhoneNumber :6989622188   Email :eatopalid@auth.gr
 
   2. Name : Konstantinos Papadopoylos     A.E.M. :8677    PhoneNumber :6951918979   Email :konserpap@auth.gr  
 
 */




package gr.auth.ee.dsproject.crush.player;

import gr.auth.ee.dsproject.crush.board.Board;

import java.util.ArrayList;

public interface AbstractPlayer
{
  public void setId (int id);

  public int getId ();

  public void setName (String name);

  public String getName ();

  public void setScore (int score);

  public int getScore ();

  public int[] getNextMove (ArrayList<int[]> availableMoves, Board board);

}
