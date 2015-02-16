package co.udea.expdesign.entity;

import java.io.Serializable;

public class ItemLatinSquare implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char Letter;
	private double value;
	
	
	public ItemLatinSquare(char letter, double value) {
		super();
		Letter = letter;
		this.value = value;
	}

	public char getLetter() {
		return Letter;
	}


	public void setLetter(char letter) {
		Letter = letter;
	}


	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}
	
	
}
