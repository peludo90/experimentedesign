package co.udea.expdesign.entity;

public class ItemMeanComparison {
	int firstTreatment;
	int secondTreatment;
	double differenceAbs;
	boolean valid;
	
	public ItemMeanComparison(int firstTreatment, int secondTreatment,
			double differenceAbs) {
		super();
		this.firstTreatment = firstTreatment;
		this.secondTreatment = secondTreatment;
		this.differenceAbs = differenceAbs;
	}
	
	

	public ItemMeanComparison(int firstTreatment, int secondTreatment,
			double differenceAbs, boolean valid) {
		super();
		this.firstTreatment = firstTreatment;
		this.secondTreatment = secondTreatment;
		this.differenceAbs = differenceAbs;
		this.valid = valid;
	}



	public int getFirstTreatment() {
		return firstTreatment;
	}

	public void setFirstTreatment(int firstTreatment) {
		this.firstTreatment = firstTreatment;
	}

	public int getSecondTreatment() {
		return secondTreatment;
	}

	public void setSecondTreatment(int secondTreatment) {
		this.secondTreatment = secondTreatment;
	}

	public double getDifferenceAbs() {
		return differenceAbs;
	}

	public void setDifferenceAbs(double differenceAbs) {
		this.differenceAbs = differenceAbs;
	} 

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
	
	
}
