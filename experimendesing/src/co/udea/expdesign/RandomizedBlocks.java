package co.udea.expdesign;

import java.util.HashMap;

import co.udea.extras.Repository;

public class RandomizedBlocks {
	
	public  static final int SStrattos = 0;
	public  static final int SSblocks = 1;
	public  static final int SSerror = 2;
	public  static final int SStotals = 3;
	public  static final int GLtrattos = 4;
	public  static final int GLblocks = 5;
	public  static final int GLerror = 6;
	public  static final int GLtotals = 7;
	public  static final int MStrattos = 8;
	public  static final int MSblocks = 9;
	public  static final int MSerror = 10;
	public  static final int F0 = 11;
	
	private double[][] dataMatrix;
	private String[] traetmentsNames;
	private String[] blockNames;
	private double[] traetmentMeans;
	private double[] traetmentsSum;
	private double[] blockMeans;
	private double[] blockSum;
	private HashMap<Integer, Double> anovaHash;
	
	private int digitNumber=2;
	
	public RandomizedBlocks(double[][] dataMatrix) {
		super();
		this.dataMatrix = dataMatrix;
		
		anovaHash = new HashMap<Integer, Double>();
		useData();
	}

	public RandomizedBlocks(double[][] dataMatrix, String[] traetmentsNames, String[] blockNames) {
		super();
		this.dataMatrix = dataMatrix;
		this.traetmentsNames = traetmentsNames;
		this.blockNames = blockNames;
		
		anovaHash = new HashMap<Integer, Double>();
		useData();
	}

	private void useData(){
		double a = dataMatrix.length;
		double b = dataMatrix[0].length;
		double N = a*b;
		
		
		//crear arreglos de medias y sumas
		traetmentMeans = new double[dataMatrix.length];
		traetmentsSum = new double[dataMatrix.length];
		
		blockMeans = new double[dataMatrix[0].length];
		blockSum = new double[dataMatrix[0].length];

		//asignar el valor de los grados libres
		anovaHash.put(GLtrattos,(double) a - 1);
		anovaHash.put(GLblocks,(double) b - 1 );
		anovaHash.put(GLerror,(double) (a - 1)*(b - 1) );
		anovaHash.put(GLtotals,(double) N-1);
		
		double sumTotalPow = 0.0;
		double sumTotal = 0.0;
		double summTrattosPow= 0.0;
		
		for (int i = 0; i < a; i++) {
			
			double sumTraetment = 0;
			int j = 0;
			for (j=0; j < b; j++) {
				sumTraetment += dataMatrix[i][j];
				
				sumTotalPow += Math.pow(dataMatrix[i][j],2);
			}
			
			traetmentsSum[i] = sumTraetment;
			double numItem = j;
			traetmentMeans[i] = sumTraetment/numItem;
			
			sumTotal += sumTraetment;
			
			summTrattosPow += Math.pow(sumTraetment, 2);
		}
		
		double sumBlocksPow = 0.0;
		
		for (int i = 0; i < b; i++) {
			
			double sumBlock = 0;
			int j = 0;
			
			for (j = 0; j < a; j++) {			
				sumBlock += dataMatrix[j][i];
			}
			blockSum[i] = sumBlock;
			double numItem = j;
			blockMeans[i] = sumBlock/numItem;
			
			sumBlocksPow += Math.pow(sumBlock, 2);
					
		}
		
		double sstotals = sumTotalPow - (Math.pow(sumTotal, 2)/N);
		double sstratos = (summTrattosPow/b) - (Math.pow(sumTotal, 2)/N);
		double ssblocks = (sumBlocksPow/a) - (Math.pow(sumTotal, 2)/N);
		
		double sserror = sstotals - sstratos - ssblocks;
		
		double mstratos = sstratos/(b-1);
		double msblocks = ssblocks/(a-1);
		double mserror = sserror/((a-1)*(b-1));
		
		double f0 = mstratos/mserror;
		anovaHash.put(SStrattos,Repository.round( sstratos, digitNumber));
		anovaHash.put(SSblocks, Repository.round(ssblocks, digitNumber));
		anovaHash.put(SSerror, Repository.round(sserror, digitNumber));
		anovaHash.put(SStotals, Repository.round(sstotals, digitNumber));
		
		anovaHash.put(MStrattos,Repository.round( mstratos, digitNumber));
		anovaHash.put(MSblocks, Repository.round(msblocks, digitNumber));
		anovaHash.put(MSerror, Repository.round(mserror, digitNumber));
		anovaHash.put(F0, Repository.round(f0, digitNumber));
		
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
	
	public String[] getBlockNames() {
		return blockNames;
	}

	public void setBlockNames(String[] blockNames) {
		this.blockNames = blockNames;
	}

	public double[] getTraetmentMeans() {
		return traetmentMeans;
	}

	public void setTraetmentMeans(double[] traetmentMeans) {
		this.traetmentMeans = traetmentMeans;
	}

	public double[] getTraetmentsSum() {
		return traetmentsSum;
	}

	public void setTraetmentsSum(double[] traetmentsSum) {
		this.traetmentsSum = traetmentsSum;
	}
	
	public double[] getBlockMeans() {
		return blockMeans;
	}

	public void setBlockMeans(double[] blockMeans) {
		this.blockMeans = blockMeans;
	}

	public double[] getBlockSum() {
		return blockSum;
	}

	public void setBlockSum(double[] blockSum) {
		this.blockSum = blockSum;
	}

	public HashMap<Integer, Double> getAnovaHash() {
		return anovaHash;
	}

	public void setAnovaHash(HashMap<Integer, Double> anovaHash) {
		this.anovaHash = anovaHash;
	}

	public int getDigitNumber() {
		return digitNumber;
	}

	public void setDigitNumber(int digitNumber) {
		this.digitNumber = digitNumber;
	}
	
	
	
}
