/* 1. Name : Eythymios Topalidhs     A.E.M. :8417    PhoneNumber :6989622188   Email :eatopalid@auth.gr
 
   2. Name : Konstantinos Papadopoylos     A.E.M. :8677    PhoneNumber :6951918979   Email :konserpap@auth.gr  
 
 */


package gr.auth.ee.dsproject.crush.player;

import gr.auth.ee.dsproject.crush.CrushUtilities;
import gr.auth.ee.dsproject.crush.board.Board;


import java.util.ArrayList;

/*H klash RandomPlayer axika apothikeuei ta xarakthristika toy kathe paikth
  kai sth synexeia ylopoiei tis tyxaies kinhseis sto tamplo
 */

public class RandomPlayer implements AbstractPlayer
{
  private int id;
  private String name;
  private int score;
  
  // TODO Constructor, getters, setters
  
  /*Ylopoihsh tou constructor RandomPlayer.Autos o constructor pairnei ws orisma mia metablhth
    typoy klashs Integer kai deixnei an o paikths einai o kokkinos h o Mple.
                             
  */
  
  public RandomPlayer(Integer ipd){
	  id = ipd;							//Metablhth xrwmatos paikth.
  }										
  
  /* Ylopoihsh twn getters gia thn epistrofh timwn opote einai anagkaio logw prosdioristikou 
  prosbasimothtas private */
  
  public int getId(){
	  
	  return id; //epistrofh xrwmatos paikth
  }
  
  public String getName(){
	  
	  return name; //epistrofh onomatos paikth
  }
  
  public int getScore(){
	  
	  return score; //Epistrofh tou score tou kathe paikth
  }
  
  
/* Ylopoihsh twn setters gia thn metabolh twn metablhtwn opote xreiazetai logw prosdioristikou 
  prosbasimothtas private */
  
  public void setId(int ident){
	  id = ident; ////kataxwrhsh xrwmatos paikth
  }
  
  public void setName(String nam){
	  name = nam; //Kataxwrhsh onomatos paikth
  }
  
  public void setScore(int sc){
	  score = sc; //Kataxwrish tou score kathe paikth
  }
  
  
  // TODO
  
  /*H synarthsh getNextMove , mesw ths ArrayList tou dynamikou pinaka pou periexei tis diathesimes
    kinhseis sto board , pragmatopoiei thn epomenh kinhsh tou paikth epistrefontas thn palia
    kai thn kainouria tou thesh.  
   */
  
  public int[] getNextMove (ArrayList<int[]> availableMoves, Board board)
  {
       
	 
	  //H getRandomMatrix periexei thn twrinh thesh toy plakidiou kai ton deikth pou deixnei 
	  //thn kateuthinsh sthn opoia thelei na kinhthei o paikths.
	  
	  int[] getRandomMatrix = new int[3];
	  
	  //Dhmiourgoume ena antikeimeno typou ArrayList pou periexei pinakes apo integers
	  //kai onomazetai moves , to synolo dhladh twn diathesimwn kinhsewn.
	  
	  ArrayList<int[]> moves = new ArrayList<int[]>();
 	  
	  //Sth randomIndex apothikeuetai h tyxaia epilogh (mesw ths Math.random()) tou deikth kinhshs.
	  
	  int randomIndex = (int)(Math.random() * moves.size());
	   
	 
	 
	  //H getRandomMove einai ayth poy pragmatopoiei thn tyxaia epilogh tou deikth kinhshs
	  //mesw tou dynamikou pinaka diathesimwn kinhsewn availableMoves.
	  
	  getRandomMatrix = CrushUtilities.getRandomMove(availableMoves , randomIndex);
	  
	  
	  
	  int[] solution = new int[4];  //Pinakas ston opoio apothikeuoyme tis 
	                                // syntetagmenes x1,y1 & x2,y2
	  
	  
	  	solution[0] = getRandomMatrix[0];								// x1
	  	solution[1] = getRandomMatrix[1];								// y1
	  					//solution[2]		                                  // x2
	  				    //solution[3]                                         // y2
	  

	  	//An o deikths deixnei pros ta aristera tote x2 = x1 - 1  & y2=y1
	  	 
	  	 
	  	
	  if(getRandomMatrix[2] == 0)
	  {
		  	solution[2] = solution[0] - 1; 
		  	solution[3] = solution[1] ;    
		  	//return solution ; 
	  }
	  	//An o deikths deixnei pros ta katw tote x2 = x1  & y2=y1 - 1
	  
	  else if(getRandomMatrix[2] == 1)
	  {
		  	solution[2] = solution[0] ;   
		  	solution[3] = solution[1] - 1;  
		  	//return solution ; 
	  }
	  
	//An o deikths deixnei pros ta deksia tote x2 = x1 + 1  & y2=y1
	  
	  else if(getRandomMatrix[2] == 2)
	  {
		  	solution[2] = solution[0] + 1;  
		  	solution[3] = solution[1];      
		  	//return solution ; 
	  }
	  
	//An o deikths deixnei pros ta panw tote x2 = x1  & y2=y1 + 1
	  
	  else if(getRandomMatrix[2] == 3)
	  {
		  	solution[2] = solution[0];   
		  	solution[3] =  solution[1] + 1; 
		  	//return solution ; 
	  }
	  
	  
	  /*Telos epistrofh toy pinaka soloution pou dexnei thn arxikh kai thn telikh thesh
	    tou plakidiou pou thelei na metakinhsei o ekastote paikths 
	  */
	  return solution ; 	

  }


   
   

}