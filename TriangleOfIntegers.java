import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * @author limecov
 *
 */
 
/*
 * Challenge:
 * 
 * Consider the following triangle of integers:
 * 
 * 1 
 * 1 2 
 * 1 2 3 
 * 1 2 3 4 
 * 1 2 3 4 5 
 * ...
 * 
 * If we were to put all of the numbers in the triangle into a list 
 * from left to right, top to bottom, 
 * the sequence of numbers would look like the following:
 * 
 * 1 1 2 1 2 3 1 2 3 4 1 2 3 4 5 ...
 * 
 * Write a program which allows you to enter the nth term in the list.
 * The program will print both the triangle 
 * and then the list of the numbers up to that term.
 * 
 * (In the example above, I would have entered 15 to generate that list.)
 * 
 * (Note - the input may not always be the number at the end of a row.)
 * 
 * Below are two solutions to the challenge. Which one resonates with you more?
 * 
 */

public class TriangleOfIntegers {
	
	public static void solution1(int n, boolean printTriangle) {
		/*
		 * Let r be the number of rows 
		 * and n the total number of elements in the triangle.
		 * 
		 * When n is a number at the end of a row, 
		 * that is basically the sum of the first n positive integers:
		 * n = r(r+1)/2.
		 * 
		 * For example when r = 5, the triangle is:
		 * 1            (r = 1)
		 * 1 2          (r = 2)
		 * 1 2 3        (r = 3)
		 * 1 2 3 4      (r = 4)
		 * 1 2 3 4 5    (r = 5)
		 * 
		 * n = 1 + 2 + 3 + 4 + 5 or 5*6/2 = 15
		 * 
		 * Let us call that the base triangle with r rows.
		 * 
		 * When n is not a number at the end of a row, 
		 * the last line is partial and above it is a complete triangle.
		 * 
		 * Therefore, to solve this problem, first we need to determine the size 
		 * of the base triangle in term of the number of rows r.
		 * Then, the remaining value goes to the line below the base triangle. 
		 * 
		 * Evaluating the relationship between r and n:
		 * r(r+1)/2 <= n
		 * r(r+1) <= 2n
		 * Since r^2 < r(r+1),
		 * r^2 < r(r+1) <= 2n
		 * Worth mentioning because the problem is concerned with whole numbers,
		 * and r(r+1) is not a perfect square for all r > 0. 
		 * 
		 * Therefore, square root of 2n is the upper limit of r.
		 * 
		 * Test the limit:
		 * For example, when n = 16, square root of 2n is ~5.7.
		 * The the upper limit of the base triangle is 5 rows, 
		 * and the number of remaining elements = 16 - 5*6/2 = 1.
		 * However, when n = 14, square root of 2n is ~5.3, and 5*6/2 = 15.
		 * In this case, the r has to be 4 because 4*5/2 < 14,
		 * and the number of remaining elements = 14 - 4*5/2 = 4.
		 * The little fraction makes a difference.
		 * 
		 */
				
		// The upper limit of r as discussed in the solution
		int r = (int)Math.sqrt(2*n);
		
		// Adjust the limit of the base triangle
		if (n < r*(r+1)/2)
			r--;
		
		// Remaining value
		int e = n - r*(r+1)/2;
		
		// Print the base triangle
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(j + " ");
			}
			if (printTriangle)
				System.out.println();
		}
		
		// Print the remaining numbers
		for (int i = 1; i <= e; i++) {
			System.out.print(i + " ");
		}
		if (e > 0)
			System.out.println();
	}
	
	public static void solution2(int n, boolean printTriangle) {
		/*
		 * Solution:
		 * 
		 * For a given number n, print the following:
		 * 
		 * First line:	1
		 * Second line:	1 2
		 * Third line:	1 2 3
		 * ...
		 * 
		 * Count the numbers until the count equals to n.
		 * 
		 * In the case of printing the list, 
		 * follow the same approach except without the line break.
		 * 
		 */
		
		int line = 1;
		int count = 1;
		while (count <= n) {
			for (int i = 1; i <= line; i++) {
				System.out.print(i + " ");
				count++;
				if (count > n)
					break;
			}
			if (printTriangle)
				System.out.println();
			line++;
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = 0;
		int s = 0;
		
		System.out.print("Enter a positive integer value for n: ");
		try {
			n = scan.nextInt();
		} catch (InputMismatchException exception) {
			System.out.println("n has to be a positive integer.");
		}
		
		System.out.print("Enter"
				+ "\n 1 to run solution 1"
				+ "\n 2 to run solution 2"
				+ "\n 3 to run both solutions"
				+ "\n Or any other key to quit without any running either solution: ");		
		try {
			s = scan.nextInt();
		} catch (InputMismatchException exception) {
			System.out.println("Quit");
			System.exit(0);
		}
		
		if (s == 1 || s == 3) {
			System.out.println("Solution 1:");
			solution1(n, true);
			solution1(n, false);
		}
		
		if (s == 2 || s == 3) {
			System.out.println("Solution 2:");
			solution2(n, true);
			solution2(n, false);
		}
		
		scan.close();
	}

}