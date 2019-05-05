/* 1. Name : Eythymios Topalidhs     A.E.M. :8417    PhoneNumber :6989622188   Email :eatopalid@auth.gr



   2. Name : Konstantinos Papadopoulos     A.E.M. :8677    PhoneNumber :6951918979   Email :konserpap@auth.gr

 */


package gr.auth.ee.dsproject.crush.player;

import gr.auth.ee.dsproject.crush.CrushUtilities;
import gr.auth.ee.dsproject.crush.board.Board;
import gr.auth.ee.dsproject.crush.board.Tile;

import java.util.ArrayList;

public class HeuristicPlayer implements AbstractPlayer
{
  // TODO Fill the class code.

  int score;
  int id;
  String name;

  public HeuristicPlayer (Integer pid)
  {
    id = pid;
    score = 0;
  }

  @Override
  public String getName ()
  {

    return "evaluation";

  }

  @Override
  public int getId ()
  {
    return id;
  }

  @Override
  public void setScore (int score)
  {
    this.score = score;
  }

  @Override
  public int getScore ()
  {
    return score;
  }

  @Override
  public void setId (int id)
  {

    this.id = id;

  }

  @Override
  public void setName (String name)
  {

    this.name = name;

  }

  @Override
  public int[] getNextMove (ArrayList<int[]> availableMoves, Board board)
  {

    int[] move = availableMoves.get(findBestMoveIndex(availableMoves, board));

    return calculateNextMove(move);

  }

  int[] calculateNextMove (int[] move)
  {

    int[] returnedMove = new int[4];

    if (move[2] == CrushUtilities.UP) {
      // System.out.println("UP");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0];
      returnedMove[3] = move[1] + 1;
    }
    if (move[2] == CrushUtilities.DOWN) {
      // System.out.println("DOWN");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0];
      returnedMove[3] = move[1] - 1;
    }
    if (move[2] == CrushUtilities.LEFT) {
      // System.out.println("LEFT");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0] - 1;
      returnedMove[3] = move[1];
    }
    if (move[2] == CrushUtilities.RIGHT) {
      // System.out.println("RIGHT");
      returnedMove[0] = move[0];
      returnedMove[1] = move[1];
      returnedMove[2] = move[0] + 1;
      returnedMove[3] = move[1];
    }
    return returnedMove;
  }


  
  
  // h sinartisi auth upologizei to ipsos tou zaxarwtou, pio konta sto katw meros tou tamplo, poy apomakrinetai

  int heightOfMove(int[] move){
	  
	  int[] returnedMove = new int[4];	
	  returnedMove = calculateNextMove (move);
	  
	  Tile oldt = new Tile();	
	  oldt.setY(returnedMove[3]);	// thetoume th sunt/gmeni y tou neou plakidiou
	  
	  int height = oldt.getY();		// anaktoume th sunt/gmeni y neou paliou plakidiou
	  
	  return height;
	  
  }
  
  
  
  
  
  
  
  
  
  int deletedCandies(int[] move, Board board)
  {
	  
	
	
	
	  
	  
	int colorc = 0;							// counter pou metraei posa plakidia skane
	int[] returnedMove = new int[4];	
	
	returnedMove = calculateNextMove (move); 	// exei tis suntetagmenes tou paliou kai tou neou plakidiou
	
	
	
	
	Tile oldt = new Tile();					// mono mia fora eksw apo ta if mallon, giat to plakidio pou tha ginei allagh("meseo")
	oldt.setX(returnedMove[0]);
	oldt.setY(returnedMove[1]);
	int oldcolmid = oldt.getColor();		// xrwma tou "paliou"-mesaiou plakidiou pou kanei swap
	
	
	Tile newtup = new Tile();		//new tile up
	newtup.setX(returnedMove[0]);
	newtup.setY(returnedMove[1]);
	
	
	if (move[2] == CrushUtilities.UP) {
		
		
      
		// elegxoume gia panw apo thn nea thesi
		
		for(int i=1; i <= CrushUtilities.NUMBER_OF_PLAYABLE_ROWS - returnedMove[1] ; i++) {
			
			
			newtup.setY(returnedMove[1] + i);
			int newcol = newtup.getColor();
    	  
			if(oldcolmid == newcol ){
			colorc++;
			}
			
			else break ;
    	 
		}
		

		// elegxoume gia aristera apo thn nea thesi

		for(int i=1;  i <= returnedMove[0] ; i++) {
			
			
			newtup.setX(returnedMove[0] - i);
			int newcol = newtup.getColor();
		  
			if(oldcolmid == newcol ){
			colorc++;
			}
			
			else break ;
		 
		}
		
		
		

		// elegxoume gia deksia apo thn nea thesi
		
		for(int i=1;  i <=  CrushUtilities.NUMBER_OF_COLUMNS - returnedMove[0] ; i++) {
			
			
			newtup.setX(returnedMove[0] + i);
			int newcol = newtup.getColor();
		  
			if(oldcolmid == newcol ){
			colorc++;
			}
			
			else break ;
		 
		}
		
		
		//////////////////////////////////////////////////
		
		
		Tile newtmid = new Tile();		//new tile middle
		
		
		newtmid.setX(returnedMove[0]);	// dinw to xrwma toy panw plakidiou
		newtmid.setY(returnedMove[3]);
		int newcolup = newtmid.getColor();
		
		newtmid.setY(returnedMove[1]);	// epanaferw stis sunt/gmenes tou mesaiou plakidiou
		
		
		// elegxoume gia katw apo thn mesea/palia thesi
		
		for(int i=1; i <= returnedMove[1] ; i++) {
			
			
			newtup.setY(returnedMove[1] - i);
			int newcol = newtup.getColor();
    	  
			if(newcolup == newcol ){
			colorc++;
			}
			
			else break ;
    	 
		}
		
		// elegxoume gia aristera apo thn mesea/palia thesi
		
		for(int i=1; i <= returnedMove[0] ; i++) {
			
			
			newtup.setX(returnedMove[0] - i);
			int newcol = newtup.getColor();
    	  
			if(newcolup == newcol ){
			colorc++;
			}
			
			else break ;
    	 
		}
		
		// elegxoume gia deksia apo thn mesea/palia thesi
		
		for(int i=1; i <=  CrushUtilities.NUMBER_OF_COLUMNS - returnedMove[0] ; i++) {
			
			
			newtup.setX(returnedMove[0] + i);
			int newcol = newtup.getColor();
    	  
			if(newcolup == newcol ){
			colorc++;
			}
			
			else break ;
    	 
		}
		
	}
		
		/////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////
		

		Tile newtdown = new Tile();		//new tile down
		
		
		newtdown.setX(returnedMove[0]);	// dinw to xrwma toy mesou plakidiou
		newtdown.setY(returnedMove[1]);
		int newcoldown = newtdown.getColor();
		
		newtdown.setY(returnedMove[3]);	// epanaferw stis sunt/gmenes tou katw plakidiou
		
		if (move[2] == CrushUtilities.DOWN) {
			
			
		      
			// elegxoume gia katw apo thn nea thesi, me suntetagenes katw thesis kai xrwma mesaias
			
			for(int i=1; i <= returnedMove[1] ; i++) {
				
				
				newtdown.setY(returnedMove[1] - i);
				int newcol = newtup.getColor();
	    	  
				if(newcoldown == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
			

			// elegxoume gia aristera apo thn nea thesi

			for(int i=1;  i <= returnedMove[0] ; i++) {
				
				
				newtup.setX(returnedMove[0] - i);
				int newcol = newtup.getColor();
			  
				if(newcoldown == newcol ){
				colorc++;
				}
				
				else break ;
			 
			}
			
			
			

			// elegxoume gia deksia apo thn nea thesi
			
			for(int i=1;  i <=  CrushUtilities.NUMBER_OF_COLUMNS - returnedMove[0] ; i++) {
				
				
				newtup.setX(returnedMove[0] + i);
				int newcol = newtup.getColor();
			  
				if(newcoldown == newcol ){
				colorc++;
				}
				
				else break ;
			 
			}
			
			
			//////////////////////////////////////////////////
			
			
			Tile newtmiddwn = new Tile();		//new tile middle down
			
			
			newtmiddwn.setX(returnedMove[0]);
			newtmiddwn.setY(returnedMove[3]);
			int newcolmiddwn = newtmiddwn.getColor();
			
			newtmiddwn.setY(returnedMove[1]);
			
			
			// elegxoume gia panw apo thn mesea/palia thesi
			
			for(int i=1; i <= CrushUtilities.NUMBER_OF_PLAYABLE_ROWS - returnedMove[1] ; i++) {
				
				
				newtmiddwn.setY(returnedMove[1] + i);
				int newcol = newtup.getColor();
	    	  
				if(newcolmiddwn == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
			
			// elegxoume gia aristera apo thn mesea/palia thesi
			
			for(int i=1; i <= returnedMove[0] ; i++) {
				
				
				newtmiddwn.setX(returnedMove[0] - i);
				int newcol = newtup.getColor();
	    	  
				if(newcolmiddwn == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
			
			// elegxoume gia deksia apo thn mesea/palia thesi
			
			for(int i=1; i <=  CrushUtilities.NUMBER_OF_COLUMNS - returnedMove[0] ; i++) {
				
				
				newtmiddwn.setX(returnedMove[0] + i);
				int newcol = newtup.getColor();
	    	  
				if(newcolmiddwn == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
		
		
		
		
		
	  
	}
      
    /////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
		
		

		Tile newtleft = new Tile();		//new tile left
		
		
		newtleft.setX(returnedMove[0]);	// dinw to xrwma toy mesou plakidiou
		newtleft.setY(returnedMove[1]);
		int newcolleft = newtdown.getColor();
		
		newtleft.setX(returnedMove[2]);	// epanaferw stis sunt/gmenes tou aristerou plakidiou
	
		if (move[2] == CrushUtilities.LEFT) {
			
			
		      
			// elegxoume gia aristera apo thn nea thesi, me suntetagenes aristeris thesis kai xrwma mesaias
			
			for(int i=1; i <= returnedMove[0] ; i++) {
				
				
				newtleft.setX(returnedMove[0] - i);
				int newcol = newtleft.getColor();
	    	  
				if(newcolleft == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
			

			// elegxoume gia panw apo thn nea thesi

			for(int i=1;  i <= CrushUtilities.NUMBER_OF_PLAYABLE_ROWS - returnedMove[1] ; i++) {
				
				
				newtleft.setY(returnedMove[1] + i);
				int newcol = newtleft.getColor();
	    	  
				if(newcolleft == newcol ){
				colorc++;
				}
				
				else break ;
			 
			}
			
			
			

			// elegxoume gia katw apo thn nea thesi
			
			for(int i=1;  i <= returnedMove[1] ; i++) {
				
				
				newtup.setX(returnedMove[1] - i);
				int newcol = newtleft.getColor();
			  
				if(newcolleft == newcol ){
				colorc++;
				}
				
				else break;
			 
			}
			
			
			//////////////////////////////////////////////////
			
			
			Tile newtmidleft = new Tile();		//new tile middle left
			
			
			newtmidleft.setX(returnedMove[2]);
			newtmidleft.setY(returnedMove[1]);
			int newcolmidleft = newtmidleft.getColor();
			
			newtmidleft.setX(returnedMove[0]);
			
			
			// elegxoume gia panw apo thn mesea/palia thesi
			
			for(int i=1; i <= CrushUtilities.NUMBER_OF_PLAYABLE_ROWS - returnedMove[1] ; i++) {
				
				
				newtmidleft.setY(returnedMove[1] + i);
				int newcol = newtmidleft.getColor();
	    	  
				if(newcolmidleft == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
			
			// elegxoume gia deksia apo thn mesea/palia thesi
			
			for(int i=1; i <=  CrushUtilities.NUMBER_OF_COLUMNS - returnedMove[0] ; i++) {
				
				
				newtmidleft.setX(returnedMove[0] + i);
				int newcol = newtmidleft.getColor();
	    	  
				if(newcolmidleft == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
			
			// elegxoume gia katw apo thn mesea/palia thesi
			
			for(int i=1; i <= returnedMove[1] ; i++) {
				
				
				newtmidleft.setX(returnedMove[1] - i);
				int newcol = newtmidleft.getColor();
	    	  
				if(newcolmidleft == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
		
		
		
		
		
	  
	}
		
		/////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////
		
		Tile newtright = new Tile();		//new tile right
		
		
		newtright.setX(returnedMove[0]);	// dinw to xrwma toy mesou plakidiou
		newtright.setY(returnedMove[1]);
		int newcolright = newtright.getColor();
		
		newtright.setX(returnedMove[2]);	// epanaferw stis sunt/gmenes tou deksiou plakidiou
	
		
		
		if (move[2] == CrushUtilities.RIGHT) {
			
			
		      
			// elegxoume gia deksia apo thn nea thesi, me suntetagenes deksias thesis kai xrwma mesaias
			
			for(int i=1; i <=  CrushUtilities.NUMBER_OF_COLUMNS - returnedMove[0] ; i++) {
				
				
				newtright.setX(returnedMove[0] + i);
				int newcol = newtright.getColor();
	    	  
				if(newcolright == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
			

			// elegxoume gia panw apo thn nea thesi

			for(int i=1;  i <= CrushUtilities.NUMBER_OF_PLAYABLE_ROWS - returnedMove[1] ; i++) {
				
				
				newtright.setY(returnedMove[1] + i);
				int newcol = newtright.getColor();
	    	  
				if(newcolright == newcol ){
				colorc++;
				}
				
				else break ;
			 
			}
			
			
			

			// elegxoume gia katw apo thn nea thesi
			
			for(int i=1;  i <= returnedMove[1] ; i++) {
				
				
				newtright.setX(returnedMove[1] - i);
				int newcol = newtright.getColor();
			  
				if(newcolright == newcol ){
				colorc++;
				}
				
				else break ;
			 
			}
			
			
			//////////////////////////////////////////////////
			
			
			Tile newtmidright = new Tile();		//new tile middle right
			
			
			newtmidright.setX(returnedMove[2]);
			newtmidright.setY(returnedMove[1]);
			int newcolmidright = newtmidright.getColor();
			
			newtmidright.setX(returnedMove[0]);
			
			
			// elegxoume gia panw apo thn mesea/palia thesi
			
			for(int i=1; i <= CrushUtilities.NUMBER_OF_PLAYABLE_ROWS - returnedMove[1] ; i++) {
				
				
				newtmidright.setY(returnedMove[1] + i);
				int newcol = newtmidright.getColor();
	    	  
				if(newcolmidright == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
			
			// elegxoume gia aristera apo thn mesea/palia thesi
			
			for(int i=1; i <=  CrushUtilities.NUMBER_OF_COLUMNS - returnedMove[0] ; i++) {
				
				
				newtmidright.setX(returnedMove[0] - i);
				int newcol = newtmidright.getColor();
	    	  
				if(newcolmidright == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
			
			// elegxoume gia katw apo thn mesea/palia thesi
			
			for(int i=1; i <= returnedMove[1] ; i++) {
				
				
				newtmidright.setX(returnedMove[1] - i);
				int newcol = newtmidright.getColor();
	    	  
				if(newcolmidright == newcol ){
				colorc++;
				}
				
				else break ;
	    	 
			}
		
		
		
		
		
	  
	}
		
		
		
	
  
	
	
	
	
	
	
	
	return colorc;
	    
  }
  
  
  
//H synarthsh ayth epistrefei mia logikh timh me to True na antisoixei se orizontio skasimo kai to False se katakoryfo
  
  
  
  ///////////////////////////////////////////////////////////////////////////////////////////////////HORUP
  boolean vertOrHor(int[] move){
	 
		int[] returnedMove = new int[4];	
		
		returnedMove = calculateNextMove (move); 	// exei tis suntetagmenes tou paliou kai tou neou plakidiou
		
		
		
		
		Tile oldt = new Tile();					// mono mia fora eksw apo ta if mallon, giat to plakidio pou tha ginei allagh("meseo")
		oldt.setX(returnedMove[0]);
		oldt.setY(returnedMove[1]);
		int oldcolmid = oldt.getColor();		// xrwma tou "paliou"-mesaiou plakidiou pou kanei swap
		
		
		Tile newtup = new Tile();		//new tile up
		newtup.setX(returnedMove[0]);
		newtup.setY(returnedMove[1]);
		
		int colorcleft1 = 0;
		int colorcright1 = 0;
		int totalcol1 = 0;
		
		if (move[2] == CrushUtilities.UP) {
			
			
			// elegxoume gia aristera apo thn nea thesi

			for(int i=1;  i <= returnedMove[0] ; i++) {
				
				
				newtup.setX(returnedMove[0] - i);
				int newcol = newtup.getColor();
			  
				if(oldcolmid == newcol ){
				colorcleft1++;
				}
				
				else break ;
			 
			}
			
			
			

			// elegxoume gia deksia apo thn nea thesi
			
			for(int i=1;  i <=  CrushUtilities.NUMBER_OF_COLUMNS - returnedMove[0] ; i++) {
				
				
				newtup.setX(returnedMove[0] + i);
				int newcol = newtup.getColor();
			  
				if(oldcolmid == newcol ){
				colorcright1++;
				}
				
				else break ;
			 
			}
			
		 totalcol1 = colorcleft1 +colorcleft1; 
			
			
			
		}
		
		/*if (totalcol1>3){
			return true;
		}
		else return false; */
		
		int colorcleft2 = 0;
		int colorcright2 = 0;
		int totalcol2 = 0;
		
		Tile newtdown = new Tile();		//new tile down
		
		newtdown.setX(returnedMove[0]);	// dinw to xrwma toy mesou plakidiou
		newtdown.setY(returnedMove[1]);
		int newcoldown = newtdown.getColor();
		
		newtdown.setY(returnedMove[3]);	// epanaferw stis sunt/gmenes tou katw plakidiou
		
		
		
		if (move[2] == CrushUtilities.DOWN) {
			
			
		      

			// elegxoume gia aristera apo thn nea thesi

			for(int i=1;  i <= returnedMove[0] ; i++) {
				
				
				newtup.setX(returnedMove[0] - i);
				int newcol = newtup.getColor();
			  
				if(newcoldown == newcol ){
				colorcleft2++;
				}
				
				else break ;
			 
			}
			
			
			

			// elegxoume gia deksia apo thn nea thesi
			
			for(int i=1;  i <=  CrushUtilities.NUMBER_OF_COLUMNS - returnedMove[0] ; i++) {
				
				
				newtup.setX(returnedMove[0] + i);
				int newcol = newtup.getColor();
			  
				if(newcoldown == newcol ){
				colorcright2++;
				}
				
				else break ;
			 
			}
			
		 totalcol2 = colorcleft2 +colorcleft2; 
			
			
			
		}
		
		/*if (totalcol2>3){
			return true;
		}
		else return false;
		*/
		
		int colorcleft3 = 0;
		

		
		Tile newtleft = new Tile();		//new tile left
		
		
		newtleft.setX(returnedMove[0]);	// dinw to xrwma toy mesou plakidiou
		newtleft.setY(returnedMove[1]);
		int newcolleft = newtdown.getColor();
		
		newtleft.setX(returnedMove[2]);	// epanaferw stis sunt/gmenes tou aristerou plakidiou
	
		if (move[2] == CrushUtilities.LEFT) {
			
			colorcleft3 = 0;
		
		      
			// elegxoume gia aristera apo thn nea thesi, me suntetagenes aristeris thesis kai xrwma mesaias
			
			for(int i=1; i <= returnedMove[0] ; i++) {
				
				
				newtleft.setX(returnedMove[0] - i);
				int newcol = newtleft.getColor();
	    	  
				if(newcolleft == newcol ){
				colorcleft3++;
				}
				
				else break ;
	    	 
			}
			
			
			
			 
		}
		
		/*if (colorcleft3>3){
			return true;
		}
		else return false;
		*/
		
		
		int colorcright4 = 0;
		
		
		Tile newtright = new Tile();		//new tile right
		
		
		newtright.setX(returnedMove[0]);	// dinw to xrwma toy mesou plakidiou
		newtright.setY(returnedMove[1]);
		int newcolright = newtright.getColor();
		
		newtright.setX(returnedMove[2]);	// epanaferw stis sunt/gmenes tou deksiou plakidiou
	
		
		if (move[2] == CrushUtilities.RIGHT) {
			
			colorcright4 = 0;
		      
			// elegxoume gia deksia apo thn nea thesi, me suntetagenes deksias thesis kai xrwma mesaias
			
			for(int i=1; i <=  CrushUtilities.NUMBER_OF_COLUMNS - returnedMove[0] ; i++) {
				
				
				
				newtright.setX(returnedMove[0] + i);
				int newcol = newtright.getColor();
	    	  
				if(newcolright == newcol ){
				colorcright4++;
				}
				
				else break ;
	    	 
			}
			
			
			
		}
		
		/*if (colorcright4>3){
			return true;
		}
		else return false;
		*/
		
		if (totalcol1>3 || totalcol2>3 || colorcleft3>3 || colorcright4 >3 ){
		return true;
		}
		else return false; 
			
  
  }
  //////////////////////////////////////////////////////////////////////////////////////////////////HORDOWN
  
  
  
  int findBestMoveIndex (ArrayList<int[]> availableMoves, Board board)
  {	
	  
	  // int[] move = availableMoves.get(findBestMoveIndex(availableMoves, board));
	  ArrayList<int[]> avmoves = new ArrayList<int[]> ();
	  avmoves = availableMoves;
	  int sizeofavmoves = avmoves.size();
	  
	  double[] evals = new double[availableMoves.size()];		// evals einai h evaluate score

	 
	  for(int i = 0; i < sizeofavmoves; i++){
		  
		 double evaluation = moveEvaluation(avmoves.get(i), board);
		 evals[i] = evaluation;
		  
	  }
	  
	  
	  // euresi megisths bathmologias gia thn epilogi ths kaluteris kinhshs
	 
	  double max = evals[0];
	  int bestmove = 0;
	  
	  for (int i = 0; i < sizeofavmoves; i++){
		  
		  
		  if (evals[i] > max){
			  max = evals[i];
			  bestmove = i;
		  }
	  }
	  
	  return bestmove;
  }
  
  
  

  double moveEvaluation (int[] move, Board board)
  {
	  // evaluation gia thn delete
	  
	  int number_of_candies_del = deletedCandies(move,board);
	  double delscore = 2.5 * number_of_candies_del;
	  
	  // evaluation gia thn height 
	  
	  int number_of_candies_height = heightOfMove(move);
	  double heightscore = 30 - 30/9 * number_of_candies_height;
	  
	  // evaluation gia thn horizontal
	  
	  boolean number_of_candies_hor = vertOrHor(move); 
	  double horscore;
	  
	  if (number_of_candies_hor){
		  horscore = 15;
	  }
	  else horscore = 5;
	  
	  
	  return delscore + heightscore + horscore;
	  
  }
	  
	  


}
