package co.udea.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class LatinSquareTest {

	@Test
	public void test() {
		
		char[] lettersLSQ = new char[5];
		
		for (int i = 0; i < 5; i++) {
			int temp = 65+i;
			lettersLSQ[i] = (char) temp;
			System.out.println(lettersLSQ[i]);
		}
		
	}

}
