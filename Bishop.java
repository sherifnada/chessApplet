import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;


public class Bishop extends ChessPiece{
	public Bishop(int position, int play, Image bishop){
		//position is initial position in which Bishop is created
		//play is the player number 
		super();
		pos = position; 
		player = play;
		moves = new Vector<Integer>(14);
		moves.add(pos+10); //initially, the forward move will always be available
		// so add it as the only potential move
		piece = bishop; // set image to bishop
		isMoved = false; 
	}
	public void potentialMoves(ChessPiece[][] board){
		// calculates all the potential moves the bishop can make and puts 
		// them in a vector
		moves.clear();
		//adding all potential moves for the bishop
		int i = 1 ; //integer used for iteration 
		boolean isEmpty=true; //bool to indicate if we should stop adding positions
		//based on whether or not the path is blocked
		isChecking=false;
		checkpath.clear();

		while(isEmpty){ //while path is not blocked
			//add all moves southeast
			if(inGrid(pos+(11*i))){ //	check if next move is in grid
				if(board[ xBox (pos + (11 * i)) -1 ][ yBox( pos + (11 * i)) -1 ]!= null){ //if next move is blocked
					isEmpty=false; //path is blocked
					if(board[xBox( pos + (11 * i)) -1 ][ yBox( pos + (11 * i)) - 1].player() != player){
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
							checkpath.add(pos-9*i);
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
		return "Bishop";
	}

	@Override
	public void draw(Graphics g){
		g.drawOval(findX(), findY(), 30, 30);
		g.setColor(Color.CYAN);
		g.fillOval(findX(),findY(), 30, 30);

	}


}
