import java.util.Vector;
import java.awt.*;
class Knight extends ChessPiece{

	public Knight(int position, int play, Image knight){
		//position is initial position in which knight is created
		//play is the player number 
		super();
		pos=position; 
		player=play;
		moves = new Vector<Integer>(8,1);
		piece=knight;
		isMoved=false;
	}

	public void potentialMoves(ChessPiece[][] board){
		// calculates all the potential moves the pawn can make and puts 
		// them in a vector 
		isChecking=false; 
		moves.clear();
		moves.add(pos+19);
		moves.add(pos+8);
		moves.add(pos-12);
		moves.add(pos-21);
		moves.add(pos-19);
		moves.add(pos-8);
		moves.add(pos+12);
		moves.add(pos+21);

		int i =0; //integer used in iteration

		while (i!=moves.size()){ // while iterator does not exceed size of vector
			if(!inGrid(moves.get(i))){
				//check if it's not in the grid
				//remove if it's not
				moves.remove(i);				
			}else if(board[xBox(moves.get(i))-1][yBox(moves.get(i))-1]!=null){
				//if this potential move is occupied				
				if(board[xBox(moves.get(i))-1][yBox(moves.get(i))-1].player()==player){
					//check if the piece there is on the same team
					//if so, then remove
					moves.remove(i);
				}else{
					//if it's a foe, then keep it as a possible move
					i++;
				}
			}else{
				//if it's not occupied then keep it as a possible move
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
		return "Knight";
	}

	@Override
	public void draw(Graphics g){
		g.drawOval(findX(), findY(), 30, 30);
		g.setColor(Color.green);
		g.fillOval(findX(),findY(), 30, 30);

	}



}

