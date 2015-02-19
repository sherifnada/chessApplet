//Final Project CS201
//Sherif A. Nada
//Enrique Dupleich

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Chess extends Applet implements ActionListener {

	private static final long serialVersionUID = 1L;

	protected Button CButton(String s, Color fg, Color bg) {
		Button b = new Button(s);
		b.setBackground(bg);
		b.setForeground(fg);
		b.addActionListener(this);
		return b;
	}

	ChessCanvas c;
	Button newGame;

	public void init () {


		setLayout(new BorderLayout());
		newGame = new Button("New Game");
		newGame.setBackground(Color.white);
		newGame.addActionListener(this);

		//code for importing images
		Image btower = getImage(getDocumentBase(), "btower.png");
		Image bknight = getImage(getDocumentBase(), "bknight.png");
		Image bking = getImage(getDocumentBase(), "bking.png");
		Image bqueen = getImage(getDocumentBase(), "bqueen.png");
		Image bbishop = getImage(getDocumentBase(), "bbishop.png");
		Image bpawn = getImage(getDocumentBase(), "bpawn.png");
		Image wpawn = getImage(getDocumentBase(), "wpawn.png");
		Image wknight = getImage(getDocumentBase(), "wknight.png");
		Image wking = getImage(getDocumentBase(), "wking.png");
		Image wqueen = getImage(getDocumentBase(), "wqueen.png");
		Image wbishop = getImage(getDocumentBase(), "wbishop.png");
		Image wtower = getImage(getDocumentBase(), "wtower.png");
		Image player1 = getImage(getDocumentBase(), "player1.jpg");
		Image player2 = getImage(getDocumentBase(), "player2.jpg");

		//constructing the chess board
		c = new ChessCanvas(btower,bknight,bking,bqueen,bbishop,bpawn,wpawn,
				wknight,wking,wqueen,wbishop,wtower, player1, player2);

		Panel p = new Panel();
		p.setBackground(Color.black);
		p.add(newGame);

		add("North", p);
		c.setBackground(Color.white);
		c.addMouseListener(c);
		add("Center", c);	

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			c.newgame();
		}
	}

}


class ChessCanvas extends Canvas implements MouseListener   {

	//Chess parent;
	ChessPiece[][] board = new ChessPiece[8][8];//The "chess board" 

	private static final long serialVersionUID = 2L;

	// instance variables representing the game go here
	int n =8 ; //number of boxes on each side of the board
	boolean color=true; 
	int size = 60; // size of the side 
	int border = 20;
	boolean isClicked= false; //boolean to keep track of if it's the first or second click
	ChessPiece electedPiece;
	boolean Check1=false;//bool to tell if player 1 has lost
	boolean Check2=false;//bool to tell if player 2 has lost
	int player=1; //int for keeping track of who's supposed to make a move
	Image bknight;
	Image bqueen;
	Image bbishop;
	Image btower;
	Image bpawn;
	Image bking;
	Image wking;
	Image wqueen;
	Image wknight;
	Image wtower;
	Image wpawn;
	Image wbishop;
	Image player1;
	Image player2;
	int turn;

	//vectors used for checkmate
	Vector<Integer> player1Moves = new Vector<Integer>(); //all of the moves player 1 can make
	Vector<Integer> player2Moves = new Vector<Integer>(); // all of the moves player 2 can make

	public ChessCanvas(Image c1, Image c2, Image c3, Image c4, Image c5,
			Image c6, Image c7, Image c8, Image c9, Image c10, Image c11, Image c12, Image c13
			,Image c14) {

		//importing images
		btower=c1;
		bknight=c2;
		bking=c3;
		bqueen=c4;
		bbishop=c5;
		bpawn=c6;
		wpawn=c7;
		wknight=c8;
		wking=c9;
		wqueen=c10;
		wbishop=c11;
		wtower=c12;
		player1=c13;
		player2=c14;
		turn=2; //integer for whose turn it is

		//setting up the board

		//adding pawns
		//player1pawns
		for(int i=0; i<8; i++){
			board[i][1] = new Pawn(i*10+12,1,bpawn);
		}
		//player2pawns
		for(int i=0; i<8; i++){
			board[i][6] = new Pawn(i*10+17,2,wpawn);
		}

		//bishops
		board[2][0] = new Bishop(31,1,bbishop);
		board[5][0] = new Bishop(61,1,bbishop);
		board[2][7] = new Bishop(38,2,wbishop);
		board[5][7] = new Bishop(68,2,wbishop);

		//royalty
		board[4][0] = new King(51,1,bking);
		board[3][0] = new Queen(41,1,bqueen);
		board[4][7] = new King(58,2,wking);
		board[3][7] = new Queen(48,2,wqueen);

		//rooks
		board[0][0] = new Rook(11,1,btower);
		board[7][0] = new Rook(81,1,btower);
		board[0][7] = new Rook(18,2,wtower);
		board[7][7] = new Rook(88,2,wtower);

		//knights
		board[1][0] = new Knight(21,1,bknight);
		board[6][0] = new Knight(71,1,bknight);
		board[1][7] = new Knight(28,2,wknight);
		board[6][7] = new Knight(78,2,wknight);

		//adding moves 
		calcMoves();
	}

	// draw the boxes
	public void paint(Graphics g) {
		if (Check1){
			g.drawImage(player1, 100,100, this);
		}
		else if(Check2){
			//player 1 wins
			g.drawImage(player2, 100,100, this);
		}else{
			for (int j=0; j<n; j++){
				for (int i = 0; i < n; i++) {
					if (color)
						g.setColor(new Color(255,255,204));
					else
						g.setColor(new Color(153,51,0));
					int x = i * size + border;
					int y = border+j*size;
					g.fillRect(x, y, size, size);
					g.setColor(Color.black);
					g.drawRect(x, y, size, size);				

					color=!color;
				}
				color=!color;
			}

			for (int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if (board[i][j]!=null){
						g.drawImage(board[i][j].piece(), board[i][j].findX()-15,
								board[i][j].findY()-20, this);
					}
				}
			}
			if(isClicked){
				//if we already chose a piece, draw potential moves
				electedPiece.drawMoves(g);
			}
		}
	}
	public int findbox(int x, int y){
		//returns the box position on the grid by combining the x and y coordinates
		//of the mouse click
		int xpos = (40+x)/60;
		int ypos= (40+y)/60;		
		return 10*xpos+ypos;
	}

	public int xBox(int position){
		return position/10;
	}

	public  int yBox(int position){
		return position%10;
	}

	// handle mouse events
	public void mousePressed(MouseEvent event) {
		Point p = event.getPoint();


		// check if clicked in box area

		int x = p.x;
		int y = p.y;

		if (x >= 0 && x < n*size &&
				y >= 0 && y < n*size) { //check if the click is in a valid box
			int posbox=findbox(x,y);
			if (!isClicked){//if this is the first click
				if (board[xBox(posbox)-1][yBox(posbox)-1]!=null && 
						board[xBox(posbox)-1][yBox(posbox)-1].player()==turn){
					//and not empty and correct player is playing
					
					//calculate potential moves for that piece
					board[xBox(posbox)-1][yBox(posbox)-1].potentialMoves(board);
					calcMoves(); //recalculate possible moves for pieces
					isCheckMate();

					//checking for castling
					//if clicked piece is a king and hasn't been moved
					if(board[xBox(posbox)-1][yBox(posbox)-1].type().equals("King")&&
							!board[xBox(posbox)-1][yBox(posbox)-1].isMoved){

						//if the corner where the rook should be contains an unmoved piece
						//then it must be the rook. 
						//Therefore, check if unmoved and spaces in between are empty
						if(!board[xBox(posbox)+2][yBox(posbox)-1].isMoved&&
								board[xBox(posbox)+1][yBox(posbox)-1]==null&&
								board[xBox(posbox)][yBox(posbox)-1]==null){
							//add castling as a possible move
							board[xBox(posbox)-1][yBox(posbox)-1].
							moves.add(board[xBox(posbox)-1][yBox(posbox)-1].position()+20);
						}
						//check for west side castling
						//if west rook is unmoved and spaces in between are null
						if(!board[xBox(posbox)-5][yBox(posbox)-1].isMoved&&
								board[xBox(posbox)-2][yBox(posbox)-1]==null&&
								board[xBox(posbox)-3][yBox(posbox)-1]==null&&
								board[xBox(posbox)-4][yBox(posbox)-1]==null){
							//add castling to the west
							board[xBox(posbox)-1][yBox(posbox)-1].
							moves.add(board[xBox(posbox)-1][yBox(posbox)-1].position()-30);
						}
					}
					if (board[xBox(posbox)-1][yBox(posbox)-1].moves.size()>0){
						//if this piece has any moves
						//elect it, and change the clicked pointer
						electedPiece=board[xBox(posbox)-1][yBox(posbox)-1];
						isClicked=!isClicked;
					}
				}
			} else {  //if this is the second click 
				if (electedPiece.moves().contains(posbox)||electedPiece.position()==posbox){
					////and the designated box
					//is a potential move for the elected piece
					//or is its current position
					calcMoves(); //recalculate possible moves for pieces
					isCheckMate();	
					//check if this is a castling move
					if (electedPiece.type().equals("King") 
							&& electedPiece.moves.contains(posbox)
							&& !electedPiece.isMoved
							&& (posbox==(electedPiece.position()+20)
							||posbox==(electedPiece.position()-30))){
						//act for east castling
						if (posbox> electedPiece.position()
								&& posbox==electedPiece.moves().get(electedPiece.moves().size()-1)){
							movePiece(
									board[xBox(electedPiece.position())+2]
											[yBox(electedPiece.position())-1],
											posbox-10);
							//act for west castling
						}else if(posbox< electedPiece.position()
								&& posbox==electedPiece.moves().get(electedPiece.moves().size()-1)){
							movePiece(
									board[xBox(electedPiece.position())-5]
											[yBox(electedPiece.position())-1],
											posbox+10);
						}
					}
					
					if (! (electedPiece.position()==posbox)){
						if (turn==1)
							turn=2;
						else if(turn==2)
							turn = 1;
					}
					movePiece (electedPiece, posbox); //move the piece to that box
					isCheckMate();
					electedPiece=null; //reset the elected piece
					isClicked=!isClicked; //change click pointer
				}		
			}
			repaint();
		}
	}

	public void movePiece(ChessPiece p, int newPos){
		int x = xBox(newPos); //get the x coordinate
		int y = yBox(newPos); //get the y coordinate

		if (board[x-1][y-1] != null){
			//if an enemy is occupying the block
			//Add code later for "pieces lost"

		}
		board[p.xBox()-1][p.yBox()-1]=null;

		board[x-1][y-1]=p;
		if (p.position()!=newPos)
			p.isMoved();
		p.setPosition(newPos);
	}

	// methods called from the event handler of the main applet

	public void newgame() {
		board = new ChessPiece[8][8];
		Check1=false;
		Check2=false;
		isClicked=false;
		electedPiece=null;
		for(int i=0; i<8; i++){
			board[i][1] = new Pawn(i*10+12,1,bpawn);			
		}
		//player2pawns
		for(int i=0; i<8; i++){
			board[i][6] = new Pawn(i*10+17,2,wpawn);			
		}

		//bishops
		board[2][0] = new Bishop(31,1,bbishop);
		board[5][0] = new Bishop(61,1,bbishop);
		board[2][7] = new Bishop(38,2,wbishop);
		board[5][7] = new Bishop(68,2,wbishop);

		//royalty
		board[4][0] = new King(51,1,bking);
		board[3][0] = new Queen(41,1,bqueen);
		board[4][7] = new King(58,2,wking);
		board[3][7] = new Queen(48,2,wqueen);

		//rooks
		board[0][0] = new Rook(11,1,btower);
		board[7][0] = new Rook(81,1,btower);
		board[0][7] = new Rook(18,2,wtower);
		board[7][7] = new Rook(88,2,wtower);

		//knights
		board[1][0] = new Knight(21,1,bknight);
		board[6][0] = new Knight(71,1,bknight);
		board[1][7] = new Knight(28,2,wknight);
		board[6][7] = new Knight(78,2,wknight);

		//adding player 1's moves
		for (int i=0; i<8; i++){
			addMoves(board[i][0]);
		}

		//adding player 2's moves
		for (int i=0; i<8; i++){
			addMoves(board[i][7]);
		}
		repaint();
	}

	//Helper Methods
	public void addMoves(ChessPiece c){
		//adds all potential moves in a piece to the player's team moves vector
		c.potentialMoves(board);
		if (c.player()==1)
			player1Moves.addAll(c.moves());
		else
			player2Moves.addAll(c.moves());
	}

	public void removeMoves(ChessPiece c){
		//removes all potential moves for a chess piece from the player's team moves vector
		c.potentialMoves(board);
		if (c.player()==1){//
			for (int i=0; i<c.moves().size(); i++){
				player1Moves.remove(c.moves().get(i));
			}
		}else{
			for (int i =0; i<c.moves().size(); i++){
				player2Moves.remove(c.moves().get(i));
			}
		}
	}


	public void calcMoves(){
		//recalculate all possible moves for the board, add them to player vectors
		player1Moves.clear();
		player2Moves.clear();
		for (int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if (board[i][j]!=null){	
					board[i][j].potentialMoves(board);
					addMoves(board[i][j]);

				}
			}
		}	
	}

	public boolean containsAny(Vector<Integer> elements, Vector<Integer> searched){
		//checks if the second vector contains any element of the first vector
		for (int i=0; i<elements.size(); i++){
			if (searched.contains(elements.get(i)))
				return true;
		}
		return false;
	}

	public void removePositions(ChessPiece c, Vector<Integer> positions){
		//removes a given set of positions from the piece's potential moves
		if (c.moves.size()>0){//if this piece has any moves
			for (int i=0; i<positions.size(); i++){
				if (c.moves.contains(positions.get(i))){ // if the piece has the current move
					//as a potential move
					//remove it from the team vector
					if (c.player()==1){
						player1Moves.removeElement(positions.get(i));
					}else{
						player2Moves.removeElement(positions.get(i));
					}
					//then remove it from the piece's moves
					c.moves.removeElement(positions.get(i));					
				}
			}
		}	
	}

	public int recurrence(Vector<Integer> v, int element){
		//returns how many times the element occurs in v
		int times=0;
		for (int i=0; i<v.size(); i++){
			if (v.get(i)==element)
				times+=1;
		}
		return times;
	}
	public void isCheckMate(){
		//find if someone is checking
		//check if opponent's moves contains checkpath 
		//check if king's moves contain anything other than checkpath
		//check if king/team can tackle the piece checking
		for (int i=0; i<8; i++){//loop over entire matrix
			for(int j=0; j<8; j++){
				if (board[i][j]!=null){ //if a block has a piece
					if (board[i][j].isChecking){ //ask if that piece is Checking a king
						//get king's positions
						int kingPos = board[i][j].checkpath.get(board[i][j].checkpath.size()-1);
						
						try{
							removePositions(board[xBox(kingPos)-1][yBox(kingPos)-1], 
								board[i][j].checkpath);	//remove the king's checkpath moves
						} catch(Exception e){
							continue;
						}
						if (board[xBox(kingPos)-1][yBox(kingPos)-1].player()==1){ // if the king
							//in check is on player 1's team
							removePositions(board[xBox(kingPos)-1][yBox(kingPos)-1], player2Moves);
							if (! player1Moves.contains(board[i][j].position()) //can we tackle? 
									&& !containsAny(board[i][j].checkpath, player1Moves) // can we intercept? 
									&& ! (board[xBox(kingPos)-1][yBox(kingPos)-1].moves.size()>0))//can king escape? 
							{
								Check1=true;
								repaint();
							} else if( board[xBox(kingPos)-1][yBox(kingPos)-1].moves.contains
									(board[i][j].pos)//can we tackle? 
									&& recurrence(player1Moves,board[i][j].pos)==1){ // can king tackle?  
								if (hypCheck(board[xBox(kingPos)-1][yBox(kingPos)-1],board[i][j])){
									Check1=true;
									repaint();}
							}
						} else{ //if it's on player 2							
							removePositions(board[xBox(kingPos)-1][yBox(kingPos)-1], player1Moves);																								
							if (! player2Moves.contains(board[i][j].position()) //can we tackle? 
									&& ! containsAny(board[i][j].checkpath, player2Moves) // can we intercept? 
									&& ! (board[xBox(kingPos)-1][yBox(kingPos)-1].moves.size()>0)){
								Check2=true;
								repaint();

							}else if( board[xBox(kingPos)-1][yBox(kingPos)-1].moves.contains
									(board[i][j].pos)//can we tackle?
									&&recurrence(player2Moves,board[i][j].pos)==1){
								if (hypCheck(board[xBox(kingPos)-1][yBox(kingPos)-1],board[i][j])){
									Check2=true;
									repaint();
								}
							}
						}
					}
				}
			}
		}
	}

	public boolean hypCheck(ChessPiece king, ChessPiece offender){
		//Within check
		//iff the king can tackle the checker
		//make a duplicate board
		//move the king where the oppressor's position. 
		//if king's position is in check
		//	
		//checkmate.

		ChessPiece replica=offender;		
		int originalPos=king.pos;
		int offenderPos=offender.pos;
		movePiece(king, offender.pos);
		calcMoves();
		if (king.player==1){
			if (player2Moves.contains(king.pos)){
				movePiece(king, originalPos);
				movePiece(replica, offenderPos);
				return true;
			}
		}else if(player1Moves.contains(king.pos)){
			movePiece(king, originalPos);
			movePiece(replica, offenderPos);	
			return true;
		}
		movePiece(king, originalPos);
		movePiece(replica, offenderPos);
		return false;
	}

	// need these also because we implement a MouseListener
	public void mouseReleased(MouseEvent event) { }
	public void mouseClicked(MouseEvent event) { }
	public void mouseEntered(MouseEvent event) { }
	public void mouseExited(MouseEvent event) { }

}

