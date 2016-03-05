/*
 * Connect4field_Model.java 

 * 
 * Version: 1.0 11/23/2015
 * 
 * @author: Ashwini Singh
 * @author: Prajesh Jhumkhawala
 * 

 * This Class contains the Model implementation of the MVC architecture.
 * It contains the details of the PLayer such as
 * it's name and the character
 */

import java.io.Serializable;

@SuppressWarnings("serial")
class Connect4Field_Model extends Thread implements Serializable {
	Connect4Field_View cv1 = new Connect4Field_View();
	int row = 8;
	public int column = 0;
	int limit = 25;
	String user;
	char piece;
	int i = 0;
	// Parameterized Constructor
	Connect4Field_Model(String b, char c) {
		user = b;
		piece = c;

	}

	// Default constructor
	public Connect4Field_Model() {
	}

	public String toString() {
		return (" ");

	}

	public int nextMove() throws Exception {
		/**
		 *This  method is used to get the next move from the user 
		 * 
		 */

		if (column < limit) {
			column = cv1.getColumn();
		} else
			column = -1;
		return column;
	}

	public char getGamePiece() {
		return piece; //returns the GamePiece for that particular player
	}

	public String getName1() {
		return user; //returns the Name for that particular player
	}

}
