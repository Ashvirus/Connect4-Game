/*
 * Connect4field_View.java 

 * 
 * Version: 1.0 11/23/2015
 * 
 * @author: Ashwini Singh
 * @author: Prajesh Jhumkhawala
 *
 *
 * This Class contains the View implementation of the MVC architecture.
 * It contains the details of the statements to be displayed on the console
 * 
 * 
 */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

@SuppressWarnings("serial")
class Connect4Field_View implements Serializable {
	//Connect4Field_Model cm=new Connect4Field_Model();
	public  static String box[][] = new String[9][25];
	public  static String box_temp[][] = new String[9][25];
	int row = 8;

	public void Draw() {

		/**
		 * This method is used to create the bucket
		 */

		int col = 0;
		int decrese = 1;
		for (row = 0; row < 9; row++) {
			for (col = 0; col < 25; col++)

				box[row][col] = "o";

		}
		for (col = 0; col < 9; col++) {
			for (row = decrese; row < 9; row++) {
				box[row][col] = " ";
			}
			decrese++;

		}
		decrese = 1;
		for (col = 24; col > 15; col--) {
			for (row = decrese; row < 9; row++) {
				box[row][col] = " ";
			}
			decrese++;

		}


	}


	public int getColumn() throws IOException {
		/**
		 * This method is used to get the column from the user
		 */
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		
		return Integer.parseInt(br1.readLine());
	}

	public void dispError(String d) {
		System.out.println(d);
	}

}
