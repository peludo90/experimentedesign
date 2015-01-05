package co.udea.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import co.udea.expdesign.OneFactor;

public class OneFactorTest {
	
	
	double[][] matrix =	{{7,7,15,11,9},
			 {12,17,12,18,18},
			 {14,18,18,19,19},
			 {19,25,22,19,23},
			 {7,10,11,15,11}
			};


		OneFactor oneFactor = new OneFactor(matrix);
	
	
	@Test
	public void medias() {
			
	for (int i = 0; i < oneFactor.getTraetmentsMedias().length; i++) {
		System.out.println(oneFactor.getTraetmentsMedias()[i]);
	}
		
		assertArrayEquals(new double[] {9.8,15.4,17.6,21.6,10.8},oneFactor.getTraetmentsMedias(), 0);
	}

	
	@Test
	public void sum() {
	
	for (int i = 0; i < oneFactor.getTraetmentsMedias().length; i++) {
		System.out.println(oneFactor.getTraetmentsSum()[i]);
	}
		
		assertArrayEquals(new double[] {49,77,88,108,54},oneFactor.getTraetmentsSum(), 0);
	}
	
	
	@Test
	public void anova(){
		
		double[] anova = {475.76,161.20,636.96,4,20,24,118.94,8.06,14.76};
		
		HashMap<Integer, Double> testAnova = oneFactor.getAnovaHash();
 		for (int i = 0; i < anova.length; i++) {
 			System.out.println(i);
			assertEquals(anova[i], testAnova.get(i),0);
		}

		
		
	}
	
}
