import java.util.Vector;
import java.awt.*;


public class Pawn extends ChessPiece {
	//class for pawns

	public Pawn(int position, int play, Image pawn){
		//constructor
		//position is initial position in which pawn is created
		//play is the player number 
		super();
		pos=position; 
		player=play;
		moves = new Vector<Integer>(3); // A pawn will have at most 3 moves
		moves.add(pos+1); //initially, the forward move will always be available
		// so add it as the only potential move
		piece=pawn;
		isMoved=false;
	}

	public void potentialMoves(ChessPiece[][] board){
		// calculates all the potential moves the pawn can make and puts 
		// them in a vector

		moves.clear();
		if (player==1){ //because player 1 moves downwards
			moves.add(pos+1); //forward
			moves.add(pos+11);// forward right				 
			moves.add(pos-9); //forward left
			if (!isMoved)
				moves.add(pos+2);
		}
		else{ //player 2 moves upwards
			moves.add(pos-1);//forward
			moves.add(pos-11);//forward left
			moves.add(pos+9);//forward right
			if (!isMoved)
				moves.add(pos-2);
		}

		if (board[xBox(moves.get(0))-1][yBox(moves.get(0))-1] != null || !inGrid(moves.get(0))){ 
			// if forward move is occupied then it's not a valid move
			moves.remove(0);
			if (!isMoved)
				moves.remove(moves.size()-1);
		}
		else if (!isMoved && 
				board[xBox(moves.get(moves.size()-1))-1][yBox(moves.get(moves.size()-1))-1] !=null) 
			// if it's not moved 
			// and that position is not empty on the board
			//remove this move
			moves.remove(moves.size()-1);


		int i=0; //integer for iteration

		while (i!=moves.size()){
			int loopPos = moves.get(i);//just to abbreviate because 
			//this will be used a lot. Contains current vector element that 
			// we're looking at. 
			//System.out.println(loopPos);
			if (! inGrid(loopPos))
				//check if it's in the grid
				//if not then remove()
				moves.remove(i);

			else if (board[xBox(loopPos)-1][yBox(loopPos)-1] != null){
				//check if that potential move is vacant
				//if not
				if (board[xBox(loopPos)-1][yBox(loopPos)-1].player() == player){
					//check if the piece is a friend or foe
					//remove if friend, keep if foe
					moves.remove(i);						
				}else{
					i++;
				}
			} else{
				if ((loopPos == pos+9 || loopPos == pos-11 || loopPos == pos+11 || loopPos == pos-9))
					moves.remove(i);
				else
					i++;
			}
		}
		for (int j=0; j<moves.size(); j++){
			//checking if this piece is checking the king
			if (board[xBox(moves.get(j))-1][yBox(moves.get(j))-1]!=null){
				if (board[xBox(moves.get(j))-1][yBox(moves.get(j))-1].type()=="King"){ //checking if we 
					//are checking the king
					isChecking=true;
					checkpath.add(moves.get(j));
				}
			}
		}
	}
	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "Pawn";
	}

	@Override
	public void draw(Graphics g){
		g.drawOval(findX(), findY(), 30, 30);
		g.setColor(Color.black);
		g.fillOval(findX(),findY(), 30, 30);

	}
}
