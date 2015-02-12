package co.udea.expdesign;

import java.util.HashMap;

import co.udea.expdesign.entity.ItemMeanComparison;
import co.udea.extras.Repository;

public class OneFactor {
	public  static final int SStrattos = 0;
	public  static final int SSerror = 1;
	public  static final int SStotals = 2;
	public  static final int GLtrattos = 3;
	public  static final int GLerror = 4;
	public  static final int GLtotals = 5;
	public  static final int MStrattos = 6;
	public  static final int MSerror = 7;
	public  static final int F0 = 8;
	

	private double[][] dataMatrix;
	private String[] traetmentsNames;
	private double[] traetmentMeans;
	private double[] traetmentsSum;
	private HashMap<Integer, Double> anovaHash;
	
	private int digitNumber=2;
	
	public OneFactor(double[][] dataMatrix, String[] traetmentsNames) {
		super();
		this.dataMatrix = dataMatrix;
		this.traetmentsNames = traetmentsNames;
		anovaHash = new HashMap<Integer, Double>();
		useData();
	}
	
	public OneFactor(double[][] dataMatrix) {
		super();
		this.dataMatrix = dataMatrix;
		anovaHash = new HashMap<Integer, Double>();
		useData();
	}
	
	private void useData(){
		double a = dataMatrix.length;
		double n = dataMatrix[0].length;
		double N = a*n;
		
		
		//crear arreglos de medias y sumas
		traetmentMeans = new double[dataMatrix.length];
		traetmentsSum = new double[dataMatrix.length];

		//asignar el valor de los grados libres
		anovaHash.put(GLtrattos,(double) a-1);
		anovaHash.put(GLerror,(double) N - a );
		anovaHash.put(GLtotals,(double) N-1);
		
		double sumTotalPow = 0.0;
		double sumTotal = 0.0;
		double summTrattosPow= 0.0;
		
		for (int i = 0; i < dataMatrix.length; i++) {
			
			int sumTraetment = 0;
			int j = 0;
			for (j = 0; j < dataMatrix[0].length; j++) {
				sumTraetment += dataMatrix[i][j];
				
				sumTotalPow += Math.pow(dataMatrix[i][j],2);
			}
			
			traetmentsSum[i] = sumTraetment;
			double numItem = j;
			traetmentMeans[i] = sumTraetment/numItem;
			
			sumTotal += sumTraetment;
			
			summTrattosPow += Math.pow(sumTraetment, 2);
		}
		
		System.out.println(Math.pow(sumTotal, 2)/N);
		
		
		double sstotals = sumTotalPow - (Math.pow(sumTotal, 2)/N);
		double sstratos = (summTrattosPow/n) - (Math.pow(sumTotal, 2)/N);
		double sserror = sstotals - sstratos;
		
		double mstratos = sstratos/(a-1);
		double mserror = sserror/(N-a);
		
		double f0 = mstratos/mserror;
		
		anovaHash.put(SStotals, Repository.round(sstotals, digitNumber));
		anovaHash.put(SStrattos,Repository.round( sstratos, digitNumber));
		anovaHash.put(SSerror, Repository.round(sserror, digitNumber));
		anovaHash.put(MStrattos,Repository.round( mstratos, digitNumber));
		anovaHash.put(MSerror, Repository.round(mserror, digitNumber));
		anovaHash.put(F0, Repository.round(f0, digitNumber));
		
	}
	
	public ItemMeanComparison[] compareMedias(){
		
		int numberTreatments = traetmentMeans.length;
		int lengthComparison  = 0;
		ItemMeanComparison[] totalComparisons;
		int index = 0;
		
		for (int i = 0; i < numberTreatments; i++) {
			lengthComparison = numberTreatments - i;
		}
		
		totalComparisons = new ItemMeanComparison[lengthComparison];
		
		for (int i = 0; i < numberTreatments; i++) {
			
			int j = 0;
			for (j =i+1; j < numberTreatments; j++) {
				
			
			totalComparisons[index] = new ItemMeanComparison(i, j, Math.abs(traetmentMeans[i]-traetmentMeans[j]));
			index++;
			}
			
		}
		
		return totalComparisons;
	}
	
	public double[][] getDataMatrix() {
		return dataMatrix;
	}

	public void setDataMatrix(double[][] dataMatrix) {
		this.dataMatrix = dataMatrix;
	}

	public String[] getTraetmentsNames() {
		return traetmentsNames;
	}

	public void setTraetmentsNames(String[] traetmentsNames) {
		this.traetmentsNames = traetmentsNames;
	}

	public double[] getTraetmentsMedias() {
		return traetmentMeans;
	}

	public void setTraetmentsMedias(double[] traetmentsMedias) {
		this.traetmentMeans = traetmentsMedias;
	}

	public double[] getTraetmentsSum() {
		return traetmentsSum;
	}

	public void setTraetmentsSum(double[] traetmentsSum) {
		this.traetmentsSum = traetmentsSum;
	}

	public HashMap<Integer, Double> getAnovaHash() {
		return anovaHash;
	}

	public void setAnovaHash(HashMap<Integer, Double> anovaHash) {
		this.anovaHash = anovaHash;
	}
	
	
	public void setAnovaItem(int key, double value){
		anovaHash.put(key, value);
	}
	
	public double  getAnovaItem(int key){
		return anovaHash.get(key);
	}
	
}
