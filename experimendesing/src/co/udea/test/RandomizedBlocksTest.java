package co.udea.test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import co.udea.expdesign.RandomizedBlocks;

public class RandomizedBlocksTest {


	double[][] matrix =	{{-2,-1,1,5},
			 {-1,-2,3,4},
			 {-3,-1,0,2},
			 {2,1,5,7}
			};
	
	RandomizedBlocks block = new RandomizedBlocks(matrix);
	
	@Test
	public void sumTrattos(){
		for (int i = 0; i < block.getTraetmentsSum().length; i++) {
			System.out.println(block.getTraetmentsSum()[i]);
		}
			
			assertArrayEquals(new double[] {3,4,-2,15},block.getTraetmentsSum(), 0);
		
	}
	
	
	@Test
	public void sumBlocks(){
		for (int i = 0; i < block.getBlockSum().length; i++) {
			System.out.println(block.getBlockSum()[i]);
		}
			
			assertArrayEquals(new double[] {-4,-3,9,18},block.getBlockSum(), 0);
		
	}
	
	@Test
	public void anova() {
		
		double[] anovaexpect = {38.50,82.50,8.00,129.00,3,3,9,15,12.83,27.50,0.89,14.44};
		
		HashMap<Integer, Double> testAnova = block.getAnovaHash();
 		for (int i = 0; i < anovaexpect.length; i++) {
 			System.out.println(i);
			assertEquals(anovaexpect[i], testAnova.get(i),0);
		}
	}

}
