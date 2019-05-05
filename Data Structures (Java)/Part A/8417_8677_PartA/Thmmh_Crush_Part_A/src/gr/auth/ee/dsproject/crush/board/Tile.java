/* 1. Name : Eythymios Topalidhs     A.E.M. :8417    PhoneNumber :6989622188   Email :eatopalid@auth.gr
 
   2. Name : Konstantinos Papadopoylos     A.E.M. :8677    PhoneNumber :6951918979   Email :konserpap@auth.gr  
 
 */





package gr.auth.ee.dsproject.crush.board;

/* H klash Tile einai ousiasthka ayth pou prosdidei 
   monadikothta sta plakidia tou board dinontas tous xrwma ,thesi,
   tautothta kai thn plhrofofria an exei epilegei ena plakidio h oxi   
 */
//
public class Tile
{

  protected int id; 
  private int x;
  private int y;
  private int color;
  private boolean mark;

  //TODO Constructor, getters, setters
        
  /*Ylopoioume ton constructor me orismata opou: id = ident, x = xi, y = yi, color = col,
	mark = mar
   dinontas arxikew times stis parapanw 5 metablhtes
   */
    
  Tile(int ident, int xi, int yi, int col, boolean mar){
          
	  id = ident; //Monadikh tautothta kathe plakidiou
	  x = xi;     //Syntetagmenh x tou plakidiou
	  y = yi;    //Syntetagmenh y tou plakidiou
	  color = col;  //Xrwma tou plakidiou
	  mark = mar;   // Logikos deikths tou an exei epilegei ena plakidio h oxi
	  
  }
  /* Ylopoihsh twn getters gia thn epistrofh timwn opote einai anagkaio logw prosdioristikou 
    klhronomhkothtas private */
  
  //Epistrofh ths taytothtas plakidiou
  
  public int getId(){
	  return id;  //Tautotha plakidiou
  }
  
  //Epistrofh syntetagmenhs X
  
  public int getX(){
	  return x; //Syntetagmenh X
  }
  
  //Epistrofh syntetagmenhs Y
  
  public int getY(){
	  return y; //Syntetagmenh Y
  }
  
  //Epistrofh xrwmatos plakidiou
  
  public int getColor(){
	  return color; //Xrwma
  }
  
  //Epistrofh logikhs timhs gia epilogh h oxi tou plakidiou
  
  public boolean getMark(){
	  return mark; //Logikh timh epiloghs
  }
  
/*Ylopoihsh twn setters gia thn metabolh twn metablhtwn opote xreiazetai logw prosdioristikou 
  klhronomhkothtas private */
  
  
  //Kataxwrei thn tautothta
  
  public void setId(int ident){
	  id = ident; //Tautothta plakidiou
  }
  
  //Kataxwrei thn syntetagmenh X
  
  public void setX(int xi){
	  x = xi; //Syntetagmenh X plakidiou
  }
  
  //Kataxwrei thn syntetagmenh Y
  
  public void setY(int yi){
	  y = yi; //Syntetagmenh Y plakidiou
  }
  
  //Kataxwrei to Xrwma
  
  public void setColor(int col){
	  color = col; //Xrwma plakidiou
  }
  
  //Kataxwrei thn logikh timh analogws an yparxei h dynatothta epiloghs tou plakidiou
  
  public void setMark(boolean mar){
	  mark = mar; //logikh timh epiloghs
	  
  }
}
