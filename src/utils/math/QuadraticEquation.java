package utils.math;

public class QuadraticEquation {
	
	private double root1, root2;
	private int rootCnt = 0;
	
	public QuadraticEquation(double a, double b, double c) {
		double dtm = b * b - 4 * a * c;
		if (dtm == 0) {
			root1 = -b / (2 * a);
			rootCnt = 1;
		} else if (dtm > 0) {
			root1 = (-b + Math.sqrt(dtm)) / (2 * a);
			root2 = (-b - Math.sqrt(dtm)) / (2 * a);
			rootCnt = 2;
		}
	}
	
	public double[] getRoots() {
		if (rootCnt == 2) {
			return new double[]{ Math.min(root1, root2), Math.max(root1, root2) };
		} else if (rootCnt == 1) {
			return new double[]{ root1 };
		} else {
			return new double[]{};
		}
	}
	
	public int getRootCount() {
		return this.rootCnt;
	}
	
}
