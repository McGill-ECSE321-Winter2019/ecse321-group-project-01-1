package computation;

public class Computation {

	public int add(int arg1, int arg2) {
		return arg1 + arg2;
	}
	
	public int multiply(int n, int m) {
	    int result = 0;
	    for (int j = 0; j < m; j++) {
            result += n;
        }
	    return result;
	}
	
	public int subtract(int arg1, int arg2) {
		return arg1 - arg2;
	}
	
	public double divide(double divisor, double divident) {
		double result;
		if (divident == 0) { 
		    result = Double.POSITIVE_INFINITY;
		} else {
		    result = divisor / divident;
		}
		return result;
	}
	
}
