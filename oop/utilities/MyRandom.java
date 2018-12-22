package utilities;

import java.util.Random;


public class MyRandom {
	
	
	public MyRandom() {
		super();
	}
	
	// tra ve so ngau nhien tu 1 den i
	public static int rand(int i) {
		Random rand = new Random();
		return rand.nextInt(i)+1;
	}
	
	/**
	 * @return int number in 1..3
	 */
	public static int rand() {
		return rand(3);
	}
	
	
}
