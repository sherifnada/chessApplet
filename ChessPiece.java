import java.util.Vector;
import java.awt.*;



public class ChessPiece {

	int player; // which team this ChessPiece is on (player 1 or player 2)
	int pos; //position in terms of box # (x, y) 
	Vector<Integer> moves;	//potential moves
	Image piece;
	boolean isMoved; //boolean to tell if the chesspiece has ever moved
	boolean isChecking;
	Vector<Integer> checkpath = new Vector<Integer>();
	


	public ChessPiece (){
		isChecking=false;
	}

	public void potentialMoves(ChessPiece[][] p){
		//calculates potential moves for each piece. Calculated differently for each object.  
		return;
	}
	public Vector<Integer> moves() {
		// returns all possible moves this chesspiece can make
		return moves;
	}


	public int player() {
		// returns the player this piece plays for
		return player;
	}

	
	public void isMoved(){
		isMoved=true;
	}
	public void setPosition(int n){
		pos=n;
	}

	public int position() {
		// returns the xy box position of the chess piece
		return pos;
	}


	public String type() {
		return null;
	}

	public int yBox (){
		// returns the y-number of the box in which this piece is in
		return yBox(pos);
	}

	public int yBox(int position){
		return position%10;
	}

	public int xBox(){
		// returns the x-number of the box in which this piece is in
		return xBox(pos);
	}

	public int xBox(int position){
		return position/10;
	}


	public int findX(){
		//returns the X coordinate of the midpoint of the box this piece is in
		return  findX(pos);		
	}

	public int findX(int position){
		return (position/10) * 60 -10 ;// 60 is the length of the side
	}

	public int findY(){
		// returns the Y coordinate of the midpoint of the box this piece is in
		return findY(pos);
	}

	public int findY(int position){
		return (position%10) * 60 - 10 ; // 60 is the length of the side
	}

	public void draw(Graphics g){
		return;
	}

	public void drawMoves(Graphics g){
		//Draws the potential moves for a piece on the board

		for (int i=0; i<moves.size(); i++){
			int xcord= findX(moves.get(i));
			int ycord= findY(moves.get(i));
			g.drawRect(xcord-30, ycord-30, 60, 60);
			if (player==1)
				g.setColor(new Color(0,255,0,75));
			else
				g.setColor(new Color(0,0,255,75));
			g.fillRect(xcord-30, ycord-30, 60, 60);
		}
	}

	public Image piece(){
		//returns the image that represents this piece
		return piece;
	}


	public boolean inGrid(int num){
		//takes a box number and determines if it's in the grid
		return !(num > 88 || num < 11 || num % 10 > 8 || num % 10 == 0);
	}

}
