package cl.webdevel.puzzle15;

import java.util.LinkedList;
import java.util.List;

/**
 * Helper class for scrambling the puzzles. The class is entirely static, and
 * cannot be instantiated. Used to generate an N by N array containing the
 * numbers from 0 to (N*N - 1), where each number corresponds to a PuzzlePiece's
 * correct position (i.e. top left is 0 and blank piece is (N*N - 1)). The class
 * always returns a solvable puzzle, and since it works with integer arrays it
 * is much faster to use it to randomly generate an array of integers in a
 * solvable manner than it is to generate an array of PuzzlePieces (which are
 * large, expensive objects). If you are curious as to why it is called Array
 * "Magic", just go down to the checkIfSolvable method and see how it works.
 * There's some crazy math down there.
 * 
 * @author Michael -- I wrote this from scratch based on a mathematical paper I
 *         read and I am very proud of it.
 */
public class ArrayMagic {

	/**
	 * Private constructor ensures no one can create an instance of this class.
	 */
	private ArrayMagic() {
	}

	/**
	 * Row of the integer (N*N - 1) in the scrambled array
	 */
	private static int blankX = 0;
	/**
	 * Column of the integer (N*N - 1) in the scrambled array
	 */
	private static int blankY = 0;

	/**
	 * Public method to create a solvable randomly scrambled array of integers
	 * with dimensions equal to size x size.
	 * 
	 * @param size
	 *            - The number of rows and columns in the array.
	 * @return The solvable scrambled array of integers.
	 */
	public static int[][] create(final int size) {

		// Get a randomly created matrix (may or may not be solvable)
		int returnVal[][] = createRandom(size);

		// If not solvable, make it solvable. Otherwise return it.
		if (!checkIfSolvable(returnVal, size)) {
			if (returnVal[0][0] == size * size - 1) { // If 1st value is blank,
														// swap 2nd and 3rd
				int temp = returnVal[0][1];
				returnVal[0][1] = returnVal[0][2];
				returnVal[0][2] = temp;
			} else if (returnVal[0][1] == size * size - 1) { // If 2nd value is
																// blank, swap
																// 1st and 3rd
				int temp = returnVal[0][0];
				returnVal[0][0] = returnVal[0][2];
				returnVal[0][2] = temp;
			} else { // Otherwise swap 1st and 2nd
				int temp = returnVal[0][0];
				returnVal[0][0] = returnVal[0][1];
				returnVal[0][1] = temp;
			}
		}
		return returnVal;
	}

	/**
	 * Creates a random array of integers with values ranging from 0 to
	 * size*size - 1 with dimensions size x size.
	 * 
	 * @param size
	 *            - Number of rows and columns to put in the array.
	 * @return The randomly ordered array
	 */
	private static int[][] createRandom(final int size) {
		int returnVal[][] = new int[size][size];

		// Put all the integers from 0 to size*size - 1 into a list.
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < size * size; i++) {
			list.add(i);
		}

		// At each position in the array, randomly choose a number remaining in
		// the list, remove it from the list and put it into the array.
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int loc = (int) (Math.random() * (double) list.size());
				returnVal[i][j] = list.get(loc);
				list.remove(loc);

				// Store the location of the blank piece so solvability can be
				// checked easily
				if (returnVal[i][j] == (size * size - 1)) {
					blankX = i;
					blankY = j;
				}
			}
		}
		return returnVal;
	}

	/**
	 * Checks if an N*N array with integers 0 to (N*N - 1) is solvable. Since a
	 * randomly generated order of integers only creates a solvable puzzle half
	 * of the time, we must have a way of determining whether the randomly
	 * generated array is solveable or not.
	 * 
	 * @param input
	 *            - The input array.
	 * @param size
	 *            - The number of rows/columns of the input array.
	 * @return True if solveable, False if not.
	 */
	private static boolean checkIfSolvable(int[][] input, final int size) {

		// Create variables to track whether the total number of permutations in
		// the array is odd or even,
		// and to store the current X and Y location within the array
		int count = 0, locX = 0, locY = 0;

		// Create a list containing every integer within the array.
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < size * size; i++) {
			list.add(i);
		}

		// Loop until every member of the array has been accounted for
		while (!list.isEmpty()) {

			// Choose a member that is still in the list to be the starting
			// value. Also set this value to the current value. Get the correct
			// location for this value and store it in locX and locY. Then set
			// the current value to the value at this location. If we manage to
			// get back to where we started, we have a complete permutation
			// (i.e. these pieces all have their positions swapped). We increase
			// count once for every value within the permutation to keep track
			// of whether the permutation is even or odd.
			int startVal = list.get(0), val = startVal;
			do {
				locX = val / size;
				locY = val % size;
				val = input[locX][locY];
				count++;
				list.remove((Integer) val);
			} while (val != startVal);

			// We must increase the count one more time when a permutation is
			// complete because odd permutations have an even number of pieces
			// and vice versa. This means that once a permutation is complete,
			// if there were an even number of pieces, an extra 1 should be
			// added to count to make count odd just like the permutation. Since
			// combinations of permutations work just like permutations of
			// integers (in terms of odd and even), only one variable has to be
			// used to keep track of the array's parity.
			count++;
		}

		// If the blank piece is in an even location and the puzzle has even
		// parity, the puzzle is solvable. Likewise with odd and odd. But if the
		// parities don't match, we must return false because the puzzle is
		// unsolvable.
		return ((count % 2) == (blankX + blankY) % 2);
	}

}
