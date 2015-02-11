package co.udea.test;

import static org.junit.Assert.*;

import org.junit.Test;

import co.udea.extras.Repository;

public class TestRepository {
	
	/*	@Test
	public void convertIntToCharTest() {
		
		char[] lettersLSQ = new char[5];
		
		for (int i = 0; i < 5; i++) {
			int temp = 65+i;
			lettersLSQ[i] = (char) temp;
			System.out.println(lettersLSQ[i]);
		}
		
	}*/

	@Test
	public void test() {
		
		double result = Repository.getStudentF0("0.1", "23");
		assertEquals(1.3195, result,0.0);
	}
	
	@Test
	public void test1() {
		
		double result = Repository.getStudentF0("0.25", "1");
		assertEquals(1, result,0.0);
	}
		

	@Test
	public void test2() {
		
		double result = Repository.getStudentF0("0.01", "∞");
		assertEquals(2.3263 , result,0.0);
	}
	
	@Test
	public void testToFail() {
		
		double result = Repository.getStudentF0("0.0001", "5");
		assertEquals(0.0, result,0.0);
	}
	
	@Test
	public void testToFail1() {
		
		double result = Repository.getStudentF0("0.05", "102");
		assertEquals(0.0, result,0.0);
	}
	
	@Test
	public void testToFail2() {
		
		double result = Repository.getStudentF0("0.3", "101");
		assertEquals(0.0, result,0.0);
	}
}

