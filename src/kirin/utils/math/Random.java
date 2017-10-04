package kirin.utils.math;

@SuppressWarnings("serial")
public class Random extends java.util.Random {

	public Random() {
		super();
	}
	
	public double nextDouble(double bound) {
		return (bound * nextDouble());
	}
}
