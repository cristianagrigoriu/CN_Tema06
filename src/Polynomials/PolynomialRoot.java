package Polynomials;

import java.util.concurrent.Callable;

public class PolynomialRoot implements Callable<double[]>{

	double[] myPolynomial;
	int n; //gradul polinomului
	double initialValue;
	double root;
	
	public PolynomialRoot(double[] array, int n, double initialValue) {
		this.myPolynomial = array;
		this.n = n;
		this.initialValue = initialValue;
	}

	public double findRoot(double initialRoot) {
		double root = initialRoot, delta = 0, deltaUp, deltaDown, firstDerivValue, secondDerivValue, polynValue;
		int k = 1;
		double[] firstDerivative, secondDerivative;
		
		do {
			firstDerivative = this.derivativeArray(myPolynomial);
			secondDerivative = this.derivativeArray(firstDerivative);
			
			firstDerivValue = this.hornerValue(firstDerivative, root);
			secondDerivValue = this.hornerValue(secondDerivative, root);
			polynValue = this.hornerValue(myPolynomial, root);
			
			deltaDown = 2 * firstDerivValue * firstDerivValue - polynValue * secondDerivValue;
			
			/*we need to find another initial root*/
			if (Math.abs(deltaDown) < Math.pow(10, -6))
				break;
			
			deltaUp = 2 * polynValue * firstDerivValue;
			delta = (double)deltaUp / deltaDown;
			
			root = root - delta;
			k = k + 1;
		} while (Math.abs(delta) >= Math.pow(10, -6) && k < 500 && Math.abs(delta) <= Math.pow(10, 8));
		
		if (Math.abs(delta) < Math.pow(10, -6)) {
			System.out.println("Aceasta este o radacina a polinomului: " + root);
			//this.writeToFile(root);
		}
		else
			//System.out.println("Trebuie ales alta radacina initala, sirul formal este divergent.");
			System.out.println("Radacina gasita: " + root);
		
		this.root = root;
		
		return root;
	}
	
	/**
	 * Computes the output of the polynomial represented in array in the point equal to value, using Horner's scheme.
	 * @param array Represents the indices of a polynomial.
	 * @param value Represents the number it computes the value of the polynomial in.
	 * @return The computed value.
	 */
	private double hornerValue(double[] array, double value) {
		double result = array[0];
		
		for (int i=1; i<array.length; i++) {
			result = array[i] + result * value;
		}
		
		return result;
	}
	
	/**
	 * Takes an array representing a polynomial and computes the first derivative of that polynomial.
	 * @param array The array to be derivatived.
	 * @return An array with the indices of the derivative polynomial.
	 */
	private double[] derivativeArray(double[] array) {
		double[] result = new double[array.length - 1];
		
		for (int i=0; i<result.length; i++) {
			result[i] = array[i] * (array.length - 1 - i);
		}
		
		/*we add the last element, the one with no x*/
		result[result.length - 1] += array[array.length - 1];
		
		return result;
	}
	
	/*implements the call method, from the abstract class Callable, that creates a thread and waits for the response*/
	public double[] call() {
		this.findRoot(this.initialValue);
		double[] rootArray = new double[1];
		rootArray[0] = this.root;
		return rootArray;
	}
}
