package co.udea.expdesign;

import java.util.HashMap;

import co.udea.expdesign.entity.ItemLatinSquare;
import co.udea.extras.Repository;

public class LatinSquare {

	public  static final int SStrattos = 0;
	public  static final int SSrows = 1;
	public  static final int SScolumns = 2;
	public  static final int SSerror = 3;
	public  static final int SStotals = 4;
	public  static final int GLtrattos = 5;
	public  static final int GLrows = 6;
	public  static final int GLcolumns = 7;
	public  static final int GLerror = 8;
	public  static final int GLtotals = 9;
	public  static final int MStrattos = 10;
	public  static final int MSrows = 11;
	public  static final int MScolumns = 12;
	public  static final int MSerror = 13;
	public  static final int F0 = 14;
	
	private ItemLatinSquare[][] dataMatrix;
	private String[] columnNames;
	private String[] rowNames;
	private char[] lettersLSQ;
	private double[] rowsMeans;
	private double[] rowsSum;
	private double[] columnMeans;
	private double[] columnSum;
	private double[] trattoMeans;
	private double[] trattoSum;
	private HashMap<Integer, Double> anovaHash;
	
	private int digitNumber=2;
	
	public LatinSquare(ItemLatinSquare[][] dataMatrix) {
		super();
		this.dataMatrix = dataMatrix;
		
		anovaHash = new HashMap<Integer, Double>();
		useData();
	}

	public LatinSquare(ItemLatinSquare[][] dataMatrix, String[] rowNames, String[] columnNames) {
		super();
		this.dataMatrix = dataMatrix;
		this.columnNames = columnNames;
		this.rowNames = rowNames;
		
		anovaHash = new HashMap<Integer, Double>();
		useData();
	}

	private void useData(){
		double a = dataMatrix.length;
		double b = dataMatrix[0].length;
		double N = a*b;
		
		
		//crear arreglos de medias y sumas
		rowsMeans = new double[dataMatrix.length];
		rowsSum = new double[dataMatrix.length];
		
		columnMeans = new double[dataMatrix[0].length];
		columnSum = new double[dataMatrix[0].length];
		
		lettersLSQ = new char[dataMatrix.length];
		trattoMeans = new double[dataMatrix.length];
		trattoSum = new double[dataMatrix.length];

		//asignar el valor de los grados libres
		anovaHash.put(GLtrattos,(double) a - 1);
		anovaHash.put(GLrows,(double) a - 1 );
		anovaHash.put(GLcolumns,(double) a - 1 );
		anovaHash.put(GLerror,(double) (a - 2)*(a- 1) );
		anovaHash.put(GLtotals,(double) Math.pow(a, 2)-1);
		
		double sumTotalPow = 0.0;
		double sumTotal = 0.0;
		double sumRowsPow= 0.0;
		
		for (int i = 0; i < a; i++) {
			
			double sumRows = 0;
			int j = 0;
			for (j=0; j < b; j++) {
				sumRows += dataMatrix[i][j].getValue();
				
				sumTotalPow += Math.pow(dataMatrix[i][j].getValue(),2);
			}
			
			rowsSum[i] = sumRows;
			double numItem = j;
			rowsMeans[i] = sumRows/numItem;
			
			sumTotal += sumRows;
			
			sumRowsPow += Math.pow(sumRows, 2);
		}
		
		double sumColumnsPow = 0.0;
		
		for (int i = 0; i < b; i++) {
			
			double sumColumn = 0;
			int j = 0;
			
			for (j = 0; j < a; j++) {			
				sumColumn += dataMatrix[j][i].getValue();
			}
			columnSum[i] = sumColumn;
			double numItem = j;
			columnMeans[i] = sumColumn/numItem;
			
			sumColumnsPow += Math.pow(sumColumn, 2);
					
		}
		
		int numberItemsNegative = (int) -a;
		int cell = (int) a;
		
		for (int i = 0; i < a; i++) {
			int temp = 65+i;
			lettersLSQ[i] = (char) temp;
			System.out.println(lettersLSQ[i]);
		}
		
		
		
		for (int i = 0; i < a ; i++) {
			for (int j = 0; j <b; j++) {
				for (int j2 = 0; j2 <a; j2++) {
					if(lettersLSQ[j2] == dataMatrix[i][j].getLetter()){
						trattoSum[j2] += dataMatrix[i][j].getValue();
					}
				}
			}
		}
		
		double sumTrattosPow = 0.0;
		
		for (int i = 0; i < trattoSum.length; i++) {
			sumTrattosPow += Math.pow(trattoSum[i], 2);
		}
		
		

		
		double sstotals = sumTotalPow - (Math.pow(sumTotal, 2)/N);
		double ssrows = (sumRowsPow/b) - (Math.pow(sumTotal, 2)/N);
		double sscolumns = (sumColumnsPow/a) - (Math.pow(sumTotal, 2)/N);
		double sstrattos = (sumTrattosPow/a) - (Math.pow(sumTotal, 2)/N);
		double sserror = sstotals - ssrows - sscolumns - sstrattos;
		
		double msrows = ssrows/(b-1);
		double mscolumns = sscolumns/(a-1);
		double mstrattos = sstrattos/(a-1);
		double mserror = sserror/((a-1)*(b-1));
		
		
		double f0 = msrows/mserror;
		
		anovaHash.put(SStrattos,Repository.round( sstrattos, digitNumber));
		anovaHash.put(SSrows,Repository.round( ssrows, digitNumber));
		anovaHash.put(SScolumns, Repository.round(sscolumns, digitNumber));
		anovaHash.put(SSerror, Repository.round(sserror, digitNumber));
		anovaHash.put(SStotals, Repository.round(sstotals, digitNumber));
		
		anovaHash.put(MStrattos,Repository.round( mstrattos, digitNumber));
		anovaHash.put(MSrows,Repository.round( msrows, digitNumber));
		anovaHash.put(MScolumns, Repository.round(mscolumns, digitNumber));
		anovaHash.put(MSerror, Repository.round(mserror, digitNumber));
		anovaHash.put(F0, Repository.round(f0, digitNumber));
	}

	public ItemLatinSquare[][] getDataMatrix() {
		return dataMatrix;
	}

	public void setDataMatrix(ItemLatinSquare[][] dataMatrix) {
		this.dataMatrix = dataMatrix;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public String[] getRowNames() {
		return rowNames;
	}

	public void setRowNames(String[] rowNames) {
		this.rowNames = rowNames;
	}

	public char[] getLettersLSQ() {
		return lettersLSQ;
	}

	public void setLettersLSQ(char[] lettersLSQ) {
		this.lettersLSQ = lettersLSQ;
	}

	public double[] getRowsMeans() {
		return rowsMeans;
	}

	public void setRowsMeans(double[] rowsMeans) {
		this.rowsMeans = rowsMeans;
	}

	public double[] getRowsSum() {
		return rowsSum;
	}

	public void setRowsSum(double[] rowsSum) {
		this.rowsSum = rowsSum;
	}

	public double[] getColumnMeans() {
		return columnMeans;
	}

	public void setColumnMeans(double[] columnMeans) {
		this.columnMeans = columnMeans;
	}

	public double[] getColumnSum() {
		return columnSum;
	}

	public void setColumnSum(double[] columnSum) {
		this.columnSum = columnSum;
	}

	public double[] getTrattoMeans() {
		return trattoMeans;
	}

	public void setTrattoMeans(double[] trattoMeans) {
		this.trattoMeans = trattoMeans;
	}

	public double[] getTrattoSum() {
		return trattoSum;
	}

	public void setTrattoSum(double[] trattoSum) {
		this.trattoSum = trattoSum;
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
