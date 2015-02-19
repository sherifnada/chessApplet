import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;


public class Queen extends ChessPiece{

	//Class containing the Queen Chesspiece Object
	//Also a tribute to the late Freddie Mercury

	public Queen (int position, int play, Image queen){
		//position is initial position in which pawn is created
		//play is the player number 
		super();
		pos=position; 
		player=play;
		moves = new Vector<Integer>(36); //queen will always have less than 36 moves  
		piece=queen;
		isMoved=false;
	}

	public void potentialMoves(ChessPiece[][] board){
		// calculates all the potential moves the Queen can make and puts 
		// them in a vector
		moves.clear();
		//adding all potential moves for the queen
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
					moves.add(pos+i);
					checkpath.add(pos+i);// add this current move to checkpath
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

		//END OF BASIC DIRECTIONS(W,E,N,S)

		i =1; // integer used for iteration
		isEmpty=true; //boolean to check if the path is clear while 
		//building potential moves

		while(isEmpty){ //while path is not blocked
			//add all moves southeast
			if(inGrid(pos+(11*i))){ //	check if next move is in grid
				if(board[xBox(pos+(11*i))-1][yBox(pos+(11*i))-1]!=null){ //if next move is blocked
					isEmpty=false; //path is blocked
					if(board[xBox(pos+(11*i))-1][yBox(pos+(11*i))-1].player()!=player){
						//if it's a foe, it's a potential move
						moves.add(pos+(11*i));
						if (!isChecking)
							checkpath.add(pos+(11*i));
						if (board[xBox(pos+(11*i))-1][yBox(pos+(11*i))-1].type().equals("King")){
							//check if the king is in check
							isChecking=true; 

						}else if(!isChecking){ // if that piece is not a king then clear checkpath
							checkpath.clear();							
						}
					}
				}else{ // else if the next move is vacant
					moves.add(pos+(11*i)); //add move
					if (!isChecking)
						checkpath.add(pos+(11*i));
					i++; // go to the next move

				}
			}else{	
				//if it's not in the grid then stop adding moves
				isEmpty=false;
				if (!isChecking)
					checkpath.clear();
			}
		}

		i =1;
		isEmpty=true;
		while(isEmpty){
			//add all moves northwest
			if(inGrid(pos-11*i)){ //	check if next move is in grid
				if(board[xBox(pos-11*i)-1][yBox(pos-11*i)-1]!=null){ //if next move is blocked
					isEmpty=false; //path is blocked
					if(board[xBox(pos-11*i)-1][yBox(pos-11*i)-1].player()!=player){
						//if it's a foe, it's a potential move
						moves.add(pos-11*i);
						if (!isChecking)
							checkpath.add(pos-11*i);
						if (board[xBox(pos-11*i)-1][yBox(pos-11*i)-1].type().equals("King"))
							isChecking=true;
						else if(!isChecking){ // if that piece is not a king then clear checkpath
							checkpath.clear();
						}
					}
				}else{ // else if the next move is vacant
					moves.add(pos-11*i); //add move
					if (!isChecking)
						checkpath.add(pos-11*i);
					i++; // go to the next move

				}
			}else{	
				//if it's not in the grid then stop adding moves
				isEmpty=false;
				if(!isChecking)
					checkpath.clear();
			}
		}


		i =1;
		isEmpty=true;
		while(isEmpty){
			//add all moves northeast
			if(inGrid(pos+9*i)){ //	check if next move is in grid
				if(board[xBox(pos+9*i)-1][yBox(pos+9*i)-1]!=null){ //if next move is blocked
					isEmpty=false; //path is blocked
					if(board[xBox(pos+9*i)-1][yBox(pos+9*i)-1].player()!=player){
						//if it's a foe, it's a potential move
						moves.add(pos+9*i);
						if (!isChecking)
							checkpath.add(pos+9*i);
						if (board[xBox(pos+9*i)-1][yBox(pos+9*i)-1].type().equals("King"))
							isChecking=true;
						else if(!isChecking){ // if that piece is not a king then clear checkpath
							checkpath.clear();
						}
					}
				}else{ // else if the next move is vacant
					moves.add(pos+9*i); //add move
					if (!isChecking)
						checkpath.add(pos+9*i);
					i++; // go to the next move

				}
			}else{	
				//if it's not in the grid then stop adding moves
				isEmpty=false;
				if(!isChecking)
					checkpath.clear();
			}		
		}


		i =1;
		isEmpty=true;
		while(isEmpty){
			//add all moves southwest
			if(inGrid(pos-9*i)){ //	check if next move is in grid
				if(board[xBox(pos-9*i)-1][yBox(pos-9*i)-1]!=null){ //if next move is blocked
					isEmpty=false; //path is blocked
					if(board[xBox(pos-9*i)-1][yBox(pos-9*i)-1].player()!=player){
						//if it's a foe, it's a potential move
						moves.add(pos-9*i);
						if (!isChecking)
							checkpath.add(pos+9*i);
						if (board[xBox(pos-9*i)-1][yBox(pos-9*i)-1].type().equals("King"))
							isChecking=true;
						else if(!isChecking)
							checkpath.clear();
					}
				}else{ // else if the next move is vacant
					if(!isChecking)
						checkpath.add(pos-9*i);
					moves.add(pos-9*i); //add move
					i++; // go to the next move

				}
			}else{	
				//if it's not in the grid then stop adding moves
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
	public Vector<Integer> moves() {
		// TODO Auto-generated method stub
		return moves;
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "Queen";
	}

	@Override
	public void draw(Graphics g){
		g.drawOval(findX(), findY(), 30, 30);
		g.setColor(Color.blue);
		g.fillOval(findX(),findY(), 30, 30);

	}



}
