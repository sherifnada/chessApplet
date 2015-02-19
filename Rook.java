import java.util.Vector;
import java.awt.*;


public class Rook extends ChessPiece {
	//class for Rooks

	public Rook(int position, int play, Image rook){
		//position is initial position in which Rook is created
		//play is the player number 
		super();
		pos=position; 
		player=play;
		moves = new Vector<Integer>(16); // A rook will at most have 16 moves available
		moves.add(pos+10); //initially, the forward move will always be available
		// so add it as the only potential move
		piece=rook;
		isMoved=false;
	}

	public void potentialMoves(ChessPiece[][] board){
		// calculates all the potential moves the Rook can make and puts 
		// them in a vector

		moves.clear();
		//adding all potential moves for the rook
		int i = 1 ; //integer used for iteration 
		boolean isEmpty=true; //bool to indicate if we should stop adding positions
		//based on whether or not the path is blocked
		isChecking=false;
		checkpath.clear();
		while (isEmpty){
			//add all moves south
			if (inGrid(pos+i)){ //if it's in the grid
				if (board[xBox(pos+i)-1][yBox(pos+i)-1] != null){ //and not empty
					isEmpty=false; //stop adding positions 
					if (board[xBox(pos+i)-1][yBox(pos+i)-1].player() != player){
						//add this move if it's an enemy
						moves.add(pos+i); //add a max of 7 moves to the right
						if (!isChecking)
							checkpath.add(pos+i);//add this to checkpath
						if (board[xBox(pos+i)-1][yBox(pos+i)-1].type().equals("King")){
							//check if the king is in check
							isChecking=true; 
						}else if(!isChecking){ // if that piece is not a king then clear checkpath
							checkpath.clear();							
						}
					}
				}else{//if it's empty, add this move then move to the next block
					if(!isChecking)
						checkpath.add(pos+i);// add this current move to checkpath
					moves.add(pos+i);
					i++;//check next move

				}
			}else{//if it's out of the grid stop adding and clear checkpath
				isEmpty=false;
				if(!isChecking)
					checkpath.clear();
			}
		}

		i=1;//reset iterative integer
		isEmpty=true; //reset isEmpty
		while (isEmpty){
			//add all moves east
			if (inGrid(pos+(10*i))){ //if it's in the grid
				if (board[xBox(pos+(10*i))-1][yBox(pos+(10*i))-1] != null){ //and not empty
					isEmpty=false; //stop adding positions 
					if (board[xBox(pos+(10*i))-1][yBox(pos+(10*i))-1].player() != player){
						//add this move if it's an enemy
						moves.add(pos+(10*i)); //add a max of 7 moves to the right
						if (!isChecking)
							checkpath.add(pos+(10*i));
						if (board[xBox(pos+10*i)-1][yBox(pos+10*i)-1].type().equals("King")){
							//check if the king is in check
							isChecking=true; 
						}else if(!isChecking){ // if that piece is not a king then clear checkpath
							checkpath.clear();							
						}
					}
				}else{//if it's empty, add this move then move to the next block
					moves.add(pos+(10*i));
					if (!isChecking)
						checkpath.add(pos+(10*i));
					i++;

				}
			}else{//if it's out of the grid stop adding and clear checkpath
				isEmpty=false;
				if(!isChecking)
					checkpath.clear();
			}
		}

		i=1;//reset iterative integer
		isEmpty=true; //reset isEmpty
		while (isEmpty){
			//add all moves north			
			if (inGrid(pos-i)){ //if it's in the grid
				if (board[xBox(pos-i)-1][yBox(pos-i)-1] != null){ //and not empty
					isEmpty=false; //stop adding positions 
					if (board[xBox(pos-i)-1][yBox(pos-i)-1].player() != player){
						//add this move if it's an enemy
						moves.add(pos-i); //add a max of 7 moves to the right
						if (!isChecking)
							checkpath.add(pos-i);
						if (board[xBox(pos-i)-1][yBox(pos-i)-1].type().equals("King")){
							//check if the king is in check
							isChecking=true; 
						}else if(!isChecking){ // if that piece is not a king then clear checkpath
							checkpath.clear();							
						}
					}
				}else{//if it's empty, add this move then move to the next block
					moves.add(pos-i);
					if (!isChecking)
						checkpath.add(pos-i);
					i++;
				}
			}else{//if it's out of the grid stop adding
				isEmpty=false;
				if(!isChecking)
					checkpath.clear();
			}
		}

		i=1;//reset iterative integer
		isEmpty=true; //reset isEmpty
		while (isEmpty){
			//add all moves west			
			if (inGrid(pos-(10*i))){ //if it's in the grid
				if (board[xBox(pos-(10*i))-1][yBox(pos-(10*i))-1] != null){ //and not empty
					isEmpty=false; //stop adding positions 
					if (board[xBox(pos-(10*i))-1][yBox(pos-(10*i))-1].player() != player){
						//add this move if it's an enemy
						moves.add(pos-(10*i)); //add a max of 7 moves to the right
						if (!isChecking)
							checkpath.add(pos-(10*i));
						if (board[xBox(pos-(10*i))-1][yBox(pos-(10*i))-1].type().equals("King")){
							//check if the king is in check
							isChecking=true; 
						}else if(!isChecking){ // if that piece is not a king then clear checkpath
							checkpath.clear();							
						}
					}
				}else{//if it's empty, add this move then move to the next block
					moves.add(pos-(10*i));
					if (!isChecking)
						checkpath.add(pos-(10*i));
					i++;					
				}
			}else{//if it's out of the grid stop adding
				isEmpty=false;
				if(!isChecking)
					checkpath.clear();
			}
		}


	}


	@Override
	public int player() {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public int position() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public String type() {
		return "Rook";
	}

	@Override
	public void draw(Graphics g){
		g.drawOval(findX(), findY(), 30, 30);
		g.setColor(Color.yellow);
		g.fillOval(findX(),findY(), 30, 30);

	}
}
