package co.udea.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import co.udea.expdesign.LatinSquare;
import co.udea.expdesign.entity.ItemLatinSquare;

public class LatinSquareTest {
	
	
	ItemLatinSquare[][] matrix = {{  
		new ItemLatinSquare('A',-1), new ItemLatinSquare('B',-5), 
		new ItemLatinSquare('C',-6), new ItemLatinSquare('D',-1), new ItemLatinSquare('E',-1)
	},
	{  
	new ItemLatinSquare('B',-8), new ItemLatinSquare('C',-1), new ItemLatinSquare('D',5), new ItemLatinSquare('E',2), new ItemLatinSquare('A',11)},
	{  
	new ItemLatinSquare('C',-7), new ItemLatinSquare('D',13), new ItemLatinSquare('E',1), new ItemLatinSquare('A',2), new ItemLatinSquare('B',-4)},
	{  
	new ItemLatinSquare('D',1), new ItemLatinSquare('E',6), new ItemLatinSquare('A',1), new ItemLatinSquare('B',-2), new ItemLatinSquare('C',-3)},
	{  
	new ItemLatinSquare('E',-3), new ItemLatinSquare('A',5), new ItemLatinSquare('B',-5), new ItemLatinSquare('C',4), new ItemLatinSquare('D',6)}
	};

	@Test
	public void test() {
		
		char[] lettersLSQ = new char[5];
		
		for (int i = 0; i < 5; i++) {
			int temp = 65+i;
			lettersLSQ[i] = (char) temp;
			System.out.println(lettersLSQ[i]);
		}
		
	}
	
	@Test
	public void anova() {
		
		LatinSquare latin = new LatinSquare(matrix);
		
		double[] anovaexpect = {330,68,150,128,676,4,4,4,12,24,82.50,17,37.50,10.67,7.73};
		
		HashMap<Integer, Double> testAnova = latin.getAnovaHash();
 		for (int i = 0; i < anovaexpect.length; i++) {
 			System.out.println(i);
			assertEquals(anovaexpect[i], testAnova.get(i),0);
		}
	}

}
