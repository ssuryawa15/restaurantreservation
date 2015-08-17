package rrservices.util;

import java.util.Random;

public class MyUtils {
	
	public static int getPrimarykey()
	{
		Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((2147483 - 0) + 1) + 1;
	    return randomNum;
	}

}
