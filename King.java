import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;


public class King extends ChessPiece {

	public King (int position, int play, Image king){
		//position is initial position in which pawn is created
		//play is the player number 
		super();
		pos=position; 
		player=play;
		moves = new Vector<Integer>(8);//A king will have at most 8 moves
		piece=king;
		isMoved=false;

	}

	public void potentialMoves(ChessPiece[][] board){
		// calculates all the potential moves the pawn can make and puts 
		// them in a vector

		moves.clear();
		moves.add(pos+1);//down
		moves.add(pos-1);//up
		moves.add(pos+10);//right
		moves.add(pos-10);//left
		moves.add(pos+9); //northwest
		moves.add(pos-9);//southeast
		moves.add(pos+11);//southwest
		moves.add(pos-11);//northeast

		int i=0; //int used in iteration
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
				i++;
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
		return "King";
	}

	@Override
	public void draw(Graphics g){
		g.drawOval(findX(), findY(), 30, 30);
		g.setColor(Color.red);
		g.fillOval(findX(),findY(), 30, 30);

	}

}
