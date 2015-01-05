package co.udea.extras;

public class Repository {

	public static double round(double number,int digits)
	{
	      int quantity=(int) Math.pow(10,digits);
	      return Math.rint(number*quantity)/quantity;
	}
}
