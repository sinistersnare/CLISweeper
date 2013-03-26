package com.circlenaut.sweep;

import java.io.PrintStream;

public class BlankField {
   private String[][] field;
	int len;
	PrintStream pr = System.out;

	public BlankField(int length) {
		len = length;
		field = new String[length][length];

		for (int i = 0; i < len; i++) {
			for (int r = 0; r < len; r++) {
				field[r][i] = " * ";
			}// end inner for loop
		}// end outer for loop

	}// end BlankField() Constructor

	/**
	 * Prints the 2D Array to the console
	 */
	public void printField() {
		for (int i = 0; i < len; i++) {
			for (int r = 0; r < len; r++) {
				pr.print(field[r][i] + "  ");
			}// end inner for loop
			pr.println();
		}// end outer for loop
	}// end printArray()

	public String getPoint(int x, int y) {
		return field[x][y];
	}

	/**
	 * Replaces a specified value of the 2D Array with the given String
	 * 
	 * @param x
	 *           the x value of the 2D Array
	 * @param y
	 *           the y value of the 2D Array
	 * @param replacement
	 *           the Replacement String.
	 */
	public void replace(int x, int y, String replacement) {
		field[x][y] = " " + replacement.trim() + " ";
	}

}// end class
