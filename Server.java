/*
 * Server.java 

 * 
 * Version: 1.0 11/23/2015
 * 
 * @author: Ashwini Singh
 * @author: Prajesh Jhumkhawala
 *
 *
 * Contains the Server side code of the Logic for running the game
 */


import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.MalformedURLException;
import java.nio.channels.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Connect4Field_Interface {
	int h = 0;
	static String s = "Hello";
	static Connect4Field_View cv = new Connect4Field_View();
	int row = 8;
	static int first = 0;
	int limit = 25;
	static int flag1 = 0;
	public int column = 0;
	int index = 0;
	static int comeOut = 0;
	static int cplayer1 = 0;
	static int cplayer2 = 0;
	static int cplayer3 = 0;
	static int cplayer4 = 0;
	static int alive = 0;
	int loop = 2;
	int rowx = 0;
	int counth1 = 0;
	int countv1 = 0;
	int countd1 = 0;
	static boolean gameIsOver = false;
	static int serverIsOver = 0;
	int counter = 1;
	static int count=0;
	static int Turn=0;
	static int xcv[]=new int[4];
	private char gamePiece;
	Connect4Field_Model thePlayers[] = new Connect4Field_Model[4];
	Connect4Field_Model cm = new Connect4Field_Model();
	Connect4Field_Model aPlayer = new Connect4Field_Model("A", '+');
	Connect4Field_Model bPlayer = new Connect4Field_Model("B", '*');
	Connect4Field_Model cPlayer = new Connect4Field_Model("C", '@');
	Connect4Field_Model dPlayer = new Connect4Field_Model("D", '#');
	static int cp1=0;
	public Server() {
	}

	public boolean didLastMoveWin() {
		/**
		 * This method checks if the last move entered by the user has made him
		 * win the game or not
		 * 
		 * 
		 */
		int counth = 0;
		int countv = 0;
		int countd = 0;
		// counter 2 means Computer
		if (counter == 2)
			this.gamePiece = '*';
		else if (counter == 3)
			this.gamePiece = '@';
		else if (counter == 4)
			this.gamePiece = '#';

		boolean win = false;
		// Vertically check
		for (int i = 0; i < 25; i++) {
			counth = 0;
			for (int j = 0; j < 9; j++) {
				if (cv.box_temp[j][i].equals(Character.toString(gamePiece))) {
					counth++;
					if (counth1 < counth)
						counth1 = counth;

					if (counth == 4)
						break;
				} else
					counth = 0;

			}
			if (counth == 4)
				break;
		}
		// Horizontally check
		for (int j = 0; j < 9; j++) {
			countv = 0;
			for (int i = 0; i < 25; i++) {
				if (cv.box_temp[j][i].equals(Character.toString(gamePiece))) {
					countv++;
					if (countv1 < countv)
						countv1 = countv;
					if (countv == 4)
						break;
				} else
					countv = 0;

			}
			if (countv == 4)
				break;
		}

		// Diagonal l2r
		for (int j = 0; j < 9; j++) {
			countd = 0;
			for (int i = 0; i < 25; i++) {
				if (j + 3 <= 8 && i + 3 <= 24) {
					if (cv.box[j][i].equals(Character.toString(gamePiece))) {
						countd++;
						if (countd1 < countd) {
							countd1 = countd;
						}
						if (cv.box[j + 1][i + 1].equals(Character.toString(gamePiece))) {
							countd++;
							if (countd1 < countd) {
								countd1 = countd;
							}
							if (cv.box[j + 2][i + 2].equals(Character.toString(gamePiece))) {
								countd++;
								if (countd1 < countd) {
									countd1 = countd;
								}
								if (cv.box[j + 3][i + 3].equals(Character.toString(gamePiece))) {
									countd = 4;
									break;
								}
							}
						}
					}
				} else
					countd = 0;

			}
			if (countd == 4)
				break;

		}
		// Diagonal r2l
		if (countd != 4) {
			countd = 0;
			for (int j = 0; j <= 8; j++) {
				countd = 0;
				for (int i = 24; i >= 0; i--) {
					if (j + 3 <= 8 && i - 3 >= 0) {
						if (cv.box[j][i].equals(Character.toString(gamePiece))) {
							countd++;
							if (countd1 < countd) {
								countd1 = countd;
							}
							if (cv.box[j + 1][i - 1].equals(Character.toString(gamePiece))) {
								countd++;
								if (countd1 < countd) {
									countd1 = countd;
								}
								if (cv.box[j + 2][i - 2].equals(Character.toString(gamePiece))) {
									countd++;
									if (countd1 < countd) {
										countd1 = countd;
									}
									if (cv.box[j + 3][i - 3].equals(Character.toString(gamePiece))) {
										countd = 4;
										break;
									}
								}
							}
						}
					} else
						countd = 0;
				}
				if (countd == 4)
					break;

			}

		}
		if (counth == 4 || countv == 4 || countd == 4)
			win = true;

		return win;
	}

	public void dropPieces(int column1, char gamePiece) {
		/*
		 * Inserts the GamePiece at that particular column
		 */
		comeOut = 0;
		int inside = 0;
		this.gamePiece = gamePiece;
		column = column1;
		row = column1;
		// break the bucket into 3 parts, first from 0 to 7(Diagonal L2R
		// increasing), 8 to 16(L2R) then 17-24((Diagonal L2R) decreasing)

		if (column < 8) {

			for (int i = 8; i >= 0; i--) {
				if (cv.box[i][column] == "o") {
					cv.box[i][column] = Character.toString(gamePiece);
					flag1 = 1;
					rowx = i;
					break;

				}

			}
			// If any column is full then for user it will ask to input again,

			if (flag1 == 0) {
				if (loop != 1) {
					cv.dispError("Please Enter Another Column");
					comeOut = 1;
				} else {
					for (int k = column; k < 17; k++) {
						for (int i = 8; i >= 0; i--) {
							if (cv.box[i][k].equals("o")) {
								cv.box[i][k] = Character.toString(gamePiece);
								inside = 1;
								flag1 = 1;
								break;
							}
						}
						if (inside == 1)
							break;

					}

				}
			}

		} else if (column >= 8 && column < 17) {
			row = 8;
			for (int i = row; i >= 0; i--)
				if (cv.box[i][column] == "o") {
					cv.box[i][column] = Character.toString(gamePiece);
					flag1 = 1;
					rowx = i;
					break;
				} else
					flag1 = 0;

			if (flag1 == 0) {
				if (loop != 1) {
					cv.dispError("Please Enter Another Column");
					comeOut = 1;
				} else {
					for (int k = column; k < 17; k++) {
						for (int i = 8; i >= 0; i--) {
							if (cv.box[i][k].equals("o")) {
								cv.box[i][k] = Character.toString(gamePiece);
								inside = 1;
								flag1 = 1;
								break;

							}
						}
						if (inside == 1)
							break;

					}
				}
			}
		} else {
			row = 8;
			for (int i = row; i >= 0; i--) {
				if (cv.box[i][column] == "o") {
					cv.box[i][column] = Character.toString(gamePiece);
					flag1 = 1;
					rowx = i;
					break;

				}

			}
			if (flag1 == 0) {
				if (loop != 1) {
					cv.dispError("Please Enter Another Column");
					comeOut = 1;
				} else {
					for (int k = 24; k >= 17; k--) {
						for (int i = 8; i >= 0; i--) {
							if (cv.box[i][k].equals("o")) {
								cv.box[i][k] = Character.toString(gamePiece);
								inside = 1;
								flag1 = 1;
								break;
							}
						}
						if (inside == 1)
							break;

					}
				}
			}

		}
		cv.box_temp = cv.box;
	}

	public void Display() {
		/**
		 * This method is used to display the bucket
		 */
		System.out.println("\n");
		for (int k = 0; k < 3; k++) {
			for (int col = 0; col < 8; col++) {
				System.out.print(col + " ");
			}
		}
		System.out.print("24");
		System.out.println("\n");

		for (row = 0; row < 9; row++) {
			for (int col = 0; col < 25; col++) {
				System.out.print(cv.box[row][col] + " ");
			}
			System.out.println("");
		}

	}

	public boolean isItaDraw() {
		/*
		 * This method checks if the entire board is full or not. If the board
		 * is fuill and no player has won, then it will determine it as a Draw
		 * 
		 */
		boolean draw = true;
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 25; i++) {
				if (cv.box[j][i].equals("o")) {
					draw = false;
				}

			}
		}
		return draw;
	}

	public void playTheGame1(int column, int index) throws Exception {
		/**
		 * The run method makes a call to this method. This method contains the
		 * main logic and in turn makes a call to all the other methods. Both
		 * the threads entering playTheGame() are synchronized on the static
		 * String s.
		 */

		if (isItaDraw()) {

			gameIsOver = true;
			serverIsOver = 5;
		} else {
			if (!gameIsOver) {
				while (flag1 != 1) {

					dropPieces(column, thePlayers[index - 1].getGamePiece());

					System.out.println(
							"thePlayers[" + (index - 1) + "].getGamePiece()=" + thePlayers[index - 1].getGamePiece());

					if (comeOut == 1)
						break;

				}
			}

			if (didLastMoveWin()) {
				gameIsOver = true;
				System.out.println("last move");
				if (alive == 0) {
					if (cplayer1 == 1)
						serverIsOver = 1;
					else if (cplayer2 == 2)
						serverIsOver = 2;
					else if (cplayer3 == 3)
						serverIsOver = 3;
					else if (cplayer4 == 4)
						serverIsOver = 4;
					else
						serverIsOver = index;
					alive = 1;
				}
			}
			
			flag1 = 0;
			System.out.println("AFTER WAIT");
			
		}

	}

	public void init(Connect4Field_Model playerA, Connect4Field_Model playerB, Connect4Field_Model playerC,	Connect4Field_Model playerD) {
		/*
		 * Initializes the player array
		 * 
		 */
		thePlayers[0] = playerA;
		thePlayers[1] = playerB;
		thePlayers[2] = playerC;
		thePlayers[3] = playerD;

	}

	public void draw() {
		/*
		 * Cslls the Draw method to draw the board
		 * 
		 */
		cv.Draw();

	}
	public int CP1()
	{
		return cplayer1;
	}
	public int CP2()
	{
		return cplayer2;
	}
	public int CP3()
	{
		return cplayer3;
	}
	public int CP4()
	{
		return cplayer4;
	}

	
	
	public boolean GameOver() {
		/*
		 * Checks whether the game is over or not
		 * 
		 */
		if (gameIsOver == true)
			return true;
		else
			return false;

	}

	public void finish() {
		/*
		 * If all the players have disconnected, it will close the server
		 * 
		 */
		if (cplayer1 == 1 && cplayer2 == 2 && cplayer3 == 3 && cplayer4 == 4) {
			System.exit(0);
		}
	}

	public int Winner() {
		/*
		 * Returns the winner
		 * 
		 */
		return serverIsOver;
	}

	public Object[][] disp() {
		/*
		 * Returns the updated box
		 * 
		 */
		return cv.box;
	}

	public void Cfinish(int n) {
		/*
		 * When a player finishes, it will call the Cfinish() and based on its value, it will assign the value of cplayer
		 * and call the finish() method
		 * 
		 */
		if (n == 1)
			cplayer1 = n;
		else if (n == 2)
			cplayer2 = n;
		else if (n == 3)
			cplayer3 = n;
		else if (n == 4)
			cplayer4 = n;
		else
			System.out.println("Wrong Player");
		try {
			finish();
		} catch (Exception e) {
		//	System.out.println("HERE");
		}
	}
	public int turn()
	{
		/*
		 * Sends to the player, the name of the current player
		 * 
		 */
	
	
		if (count<4)
			return 0;
		else
		{
			for (int i=0;i<3;i++)
			{
				if(xcv[i]==Turn)
				{
					return xcv[i+1];
				}
			}
			return xcv[0];
				
		}
		
	}
	public void run1(int h, int index) throws RemoteException {
		/*
		 * When the server thread is initialized, it will call the run method.
		 * 
		 */
		Turn=index;
		if (count<4)
			xcv[count]=index;
				count++;
				init(aPlayer, bPlayer, cPlayer, dPlayer);
		System.out.println("WhileLoop  :" + gameIsOver);
		try {
			if (first == 0)
				draw();
			first = 1;
			playTheGame1(h, index);

			if (!(gameIsOver == true)) {
				serverIsOver = index;
			} else {
				finish();
			}

		} catch (Exception e) {

		}

	}
	public int start()
    {
		/*
		 * returns the number of players, if 4 then it will start the game
		 * else it will wait for sometime and if still not connected, it will exit
		 * 
		 * 
		 */
    	return cp1;
    }
    public void send(int i)
    {
    	/*
    	 * Send the current number of active player 
    	 * 
    	 */
    	cp1++;
    }
	public static void main(String args[]) throws MalformedURLException, RemoteException, AlreadyBoundException {
		/*
		 * This is the main method, which is the starting point of the code 
		 * 
		 */
		try {
			Server obj = new Server();
			Connect4Field_Interface stub = (Connect4Field_Interface) UnicastRemoteObject.exportObject(obj, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("Hello11", stub);

			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}