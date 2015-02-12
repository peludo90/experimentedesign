package co.udea.expdesign.entity;

public class ItemMeanComparison {
	int firstTreatment;
	int secondTreatment;
	double differenceAbs;
	
	public ItemMeanComparison(int firstTreatment, int secondTreatment,
			double differenceAbs) {
		super();
		this.firstTreatment = firstTreatment;
		this.secondTreatment = secondTreatment;
		this.differenceAbs = differenceAbs;
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
	
	
	
	
}
