package com.circlenaut.sweep;

/* Write type checker method!!!
 */
import java.io.PrintStream;
import java.util.Random;

/**
 * Generates the 2D array for which the game is based upon
 * 
 * @author Davis
 * 
 */
public class Field {
   Random ran = new Random();

	int min = -1;
	int max = 5;

	private int bombs;
	private int len;
	private int field[][];
	private PrintStream pr = System.out;

	/**
	 * Constructs a 2D array to be used for minesweeper.
	 * 
	 * @param length
	 *           the length of the square.
	 */
	public Field(int length, int Bombs) {
		bombs = Bombs; // Bombs is for the const. only, bombs is for class.
		len = length;
		field = new int[length][length];
		for (int i = 0; i < len; i++) {
			for (int r = 0; r < len; r++) {
				field[r][i] = 0;

			}// end inner for loop
		}// end outer for loop
		this.placeBombs();
		pr.println(bombs);
	}// end Field() Constructor

	/**
	 * Returns the amount of bombs in the field
	 * 
	 * @return total amount of bombs
	 */
	public int getNumBombs() {
		return bombs;
	}// end numBombs()

	/**
	 * Returns the length of the field
	 * 
	 * @return the length of the field
	 */
	public int getLen() {
		return len;
	}// end fieldLen()

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

	public int getPoint(int x, int y) {
		return field[x][y];
	}

	/**
	 * Randomly places bombs throughout the given field
	 * 
	 */
	public void placeBombs() {
		Random ran = new Random();
		for (int i = 0; i < this.getNumBombs(); i++) {
			int xUse = ran.nextInt(len);
			int yUse = ran.nextInt(len);

			if (!(field[xUse][yUse] == -1)) {
				field[xUse][yUse] = -1;
			}
			else
				i--;
		}// end for
	}// end placeBombs()

	public boolean isOutside(int x, int y) {
		if (x < 0 || y < 0 || x >= len || y >= len) 
			return false;
		return true;
	}
	
	/**
	 * A method that returns the amount of adjacent bombs to a point.
	 * 
	 * @param x
	 *           the x value to test adjacentness
	 * @param y
	 *           the y value to test adjacentness
	 * @return the number of adjacent bombs
	 */
	public int getNumAdjBombs(int x, int y) {
		int mines = 0;
		for (int i = y - 1; i < y + 2; i++) {
			for (int r = x - 1; r < x + 2; r++) {
				if (isOutside(r,i) && this.getPoint(r, i) == -1) {
						mines++;
				}
			}
		}
		return mines;
	}

}// end Field class
