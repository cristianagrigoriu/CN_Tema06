package Polynomials;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Polynomials_Helper {

	double[] myPolynomial;
	int n; //gradul polinomului
	
	public Polynomials_Helper(String fileName) {
		this.readFromFile(fileName);
	}

	public void readFromFile(String fileName) {
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			/*get the object of DataInputStream*/
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String myStringPol[] = new String[10];
			int count = 0;
			
			/*read the first line of the file*/
			while ((strLine = br.readLine()) != null) {
				if (count == 0) {
					this.n = Integer.parseInt(strLine);
					myPolynomial = new double[this.n + 1];
					count++;
					continue;
				}
				else {
					myStringPol = strLine.split(" ");
					break;
				}
			}
			
			/*close the input stream*/
			in.close();
			
			/*convert indices of polynomial from String to double*/
			for (int i=0; i<this.n + 1; i++) {
				myPolynomial[i] = Double.parseDouble(myStringPol[i]);
			}
			
			this.printArray(myPolynomial, "Polinomul initial:");
			
			/*catch exception if any*/
			}catch (Exception e){
				System.err.println("Error: " + e.getMessage());
		}
	}
	
	public void findRoot() {
		//this.derivativeArray(this.derivativeArray(myPolynomial));
		//System.out.println(hornerValue(myPolynomial, 3));
		double root = 2.001, delta = 0, deltaUp, deltaDown, firstDerivValue, secondDerivValue, polynValue;
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
		
		if (Math.abs(delta) < Math.pow(10, -6))
			System.out.println("Aceasta este o radacina a polinomului: " + root + " -- " + k);
		else
			System.out.println("Trebuie ales alta radacina initala, sirul formal este divergent. -- " + k);
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
		
		//this.printArray(result, "Polinomul derivat:");
		
		return result;
	}
	
	public void printArray(double[] array, String message) {
		System.out.println(message);
		for (int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
