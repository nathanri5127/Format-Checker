/**
 * FormatChecker 
 * @author Nathan Richardson
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * FormatChecker creates three scanners that runs through the given file to account for the given columns and rows
 * If an error is found, then a InputMismatchException is thrown
 * 
 *@param filescan- Reads the given file 
 *@param lineScanner- Scanner used to check the first line to create the grid
 *@param countScanner- Scanner used to measure the lines in a grid to check if the dimensions match
 *@param i, j, k - Used to measure what part of the grid currently being read
 *@param line- What line of the file is being read
 *@param count- Measures the inputs so far
 *@param ROW- Static int that is used to read row count for the file
 *@param COL- Static int that is used to read column count for the file
 *@throws Exception- InputMismatchException thrown if X found in the file, other Exception thrown for inproperly sized grids
 */
public class FormatChecker {

private static double[][] grid;
private static  int ROW;
private static int COL;


public static void checkFormat(String filename) throws Exception {
	Scanner filescan = new Scanner(new File(filename));
	Scanner lineScanner = new Scanner(filescan.nextLine());;
	Scanner countScanner = new Scanner(new File(filename));
	int i = 0;
	int line = 0;
	int count = 0;
	while(filescan.hasNextLine() && i < 2) {
		ROW = lineScanner.nextInt();
		i++;
		COL = lineScanner.nextInt();
		grid = new double[ROW][COL];
		i++;
		if (lineScanner.hasNext()) {
			throw new Exception();
		}
	}
	countScanner.nextLine();
	while(countScanner.hasNextLine()) {
		line++;
		countScanner.nextLine();
	}
	line--;
	if(line  > ROW) {
		throw new Exception();
	}
	for(int j = 0; j < ROW; j++) {
		for(int k = 0; k < COL; k++) {
			if(count != (ROW * COL) && filescan.hasNext()) {
				grid[j][k] = filescan.nextDouble();
				count++;
				}
			}
		}
		if(((count <= (ROW * COL)) && filescan.hasNext()) || (count < (ROW * COL) && !(filescan.hasNext()))) {
			throw new Exception();
		}
	filescan.close();
}
/**
 * Runs the checkFormat function taken from the user input, catching any exceptions and printing validity
 * Invalid files print File not found, String being found in the grid or incorrect grid setup prints Not all inputs are the correct type
 * Wrong dimensions print Incorrect Dimensions
 * 
 * @param args- The string input from the user being put in checkFormat() that should match a file name
 * 
 */
public static void main(String[] args) {
	if(args.length == 0) {
		System.out.println("Usage: $ java FormatChecker file1 [file2 ...fileN]");
	} else {
		for(int i = 0; i < args.length; i++) {
			try {
				checkFormat(args[i]);
				System.out.println(args[i]);
				System.out.println("VALID");
			} catch (FileNotFoundException e) {
				System.out.println(args[i]);
				System.out.println("File not found");
				System.out.println("INVALID");
			} catch (InputMismatchException e) {
				System.out.println(args[i]);
				System.out.println(e + ": Not all inputs are the correct types");
				System.out.println("INVALID");
			}  catch (Exception e) {
				System.out.println(args[i]);
				System.out.println(e + ": Incorrect Dimensions");
				System.out.println("INVALID");
			}
			}
		}
	}
}
