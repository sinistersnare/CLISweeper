package com.circlenaut.sweep;

import java.io.PrintStream;
import java.util.Scanner;

/* 
 * Before you do size: make sure that there is a win call, 
 * and to make a new game only when you have won or lost, 
 * then at the start of the game, ask for size
 * 
 * Replace * with Ø if there are no adj. bombs
 * otherwise, replace with # adj bombs.
 * 
 */
/**
 * Main Game file.
 * 
 */
public class Game {
   private static PrintStream pr = System.out;
	int size;
	Field field;
	BlankField blank;
	int x, y;
	Scanner in;
	private int numBombs;

	/**
	 * Main Constructor, initializes shit.
	 * 
	 * @author Davis
	 */
	public Game() {
		in = new Scanner(System.in);
		pr.print("Size of minefield: ");
		size = in.nextInt();
		pr.print("number of bombs to play: ");
		numBombs = in.nextInt();
		pr.println("there are " + numBombs + " bombs");
		while (numBombs <= 2 || numBombs >= 1000) {
			pr.println("invalid number, please input again: ");
			numBombs = in.nextInt();
			pr.println();
		}
		pr.println("Making field");
		field = new Field(size, numBombs);
		pr.println("field made");
		blank = new BlankField(size);
		pr.println("field for game use: ");
		blank.printField();
		pr.println("Field values: ");
		// field.printField();
		if (field.getNumBombs() > 1)
			pr.println("There are " + field.getNumBombs() + " bombs in play");
		else if (field.getNumBombs() == 1)
			pr.println("There is a single bomb in play");

		else if (field.getNumBombs() == 0)
			pr.println("There are no bombs! WHAT.");
		else
			pr.println("an error has occured.");

		while (true) {
			pr.print("X,Y formatted: ");
			String toParse = in.next();
			String parsed = toParse.replaceAll("[\\D]", ",").trim();

			while (toParse.length() == 1) {
				pr.print("Try again: X,Y: ");
				toParse = in.next();
				parsed = toParse.replaceAll("[\\D]", ",").trim();
			}
			x = Integer.parseInt(toParse.substring(0, parsed.indexOf(",")));
			y = Integer.parseInt(toParse.substring(parsed.indexOf(",") + 1));
			int point = field.getPoint(x, y);
			if (point == -1) {
				blank.replace(x, y, "@");
				pr.println("field for game use: ");
				blank.printField();
				this.gameOver();

			}// end if
			else {
				int numAdj = field.getNumAdjBombs(x, y);
				String numAdjStr = String.valueOf(numAdj);
				pr.println("Adj bombs: " + numAdj);
				if (numAdj == 0)
					blank.replace(x, y, " Ø ");
				else
					blank.replace(x, y, numAdjStr);
				pr.println("OK!\n");
				pr.println("field for game use: ");
				blank.printField();
				pr.println("Field values: ");
				// field.printField();
			}// end else
			if (this.isWin(field, blank)) {
				pr.println("CONGRATS!!!!!");
			}
		}// end while(true)

	}// end game constructor

	public void gameOver() {
		pr.println("YOU LOSE!");
		field.printField();
		System.exit(0);
	}

	public boolean isWin(Field field, BlankField blank) {
		for (int i = 0; i < size; i++) {
			for (int r = 0; r < size; r++) {

				if (blank.getPoint(r, i).trim().equals("*")
				         && field.getPoint(r, i) != -1) {
					return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {

		while (true) {
			new Game();
			// String de = "9,9";
			// String rp = de.replaceAll("[\\D]", ",");
			// pr.println(rp);

		}
	}// end main

}
