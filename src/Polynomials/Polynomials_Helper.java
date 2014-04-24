package Polynomials;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Polynomials_Helper{

	double[] myPolynomial;
	int n; //gradul polinomului
	
	public Polynomials_Helper(String fileName) {
		ReadFile rf = new ReadFile();
		this.myPolynomial = rf.readFromFile(fileName);
		this.n = myPolynomial.length - 1;	
	}

	public void solve(double initialValue) {
		PolynomialRoot pr = new PolynomialRoot(this.myPolynomial, this.n, initialValue);
		//pr.findRoot(initialValue);
		pr.startThread(initialValue);
	}
	
	/*public void readFromFile(String fileName) {
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);*/
			/*get the object of DataInputStream*/
			/*DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String myStringPol[] = new String[10];
			int count = 0;*/
			
			/*read the first line of the file*/
			/*while ((strLine = br.readLine()) != null) {
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
			}*/
			
			/*close the input stream*/
			//in.close();
			
			/*convert indices of polynomial from String to double*/
			/*for (int i=0; i<this.n + 1; i++) {
				myPolynomial[i] = Double.parseDouble(myStringPol[i]);
			}
			
			this.printArray(myPolynomial, "Polinomul initial:");*/
			
			/*catch exception if any*/
			/*}catch (Exception e){
				System.err.println("Error: " + e.getMessage());
		}
	}*/
	
	/*public void findRoot(double initialRoot) {
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
			/*if (Math.abs(deltaDown) < Math.pow(10, -6))
				break;
			
			deltaUp = 2 * polynValue * firstDerivValue;
			delta = (double)deltaUp / deltaDown;
			
			root = root - delta;
			k = k + 1;
		} while (Math.abs(delta) >= Math.pow(10, -6) && k < 500 && Math.abs(delta) <= Math.pow(10, 8));
		
		if (Math.abs(delta) < Math.pow(10, -6)) {
			System.out.println("Aceasta este o radacina a polinomului: " + root);
			this.writeToFile(root);
		}
		else
			System.out.println("Trebuie ales alta radacina initala, sirul formal este divergent.");
	}*/
	
	private void writeToFile(double value) {
		/*we assume the file doesn't already exist*/
		boolean newlyCreated = true, alreadyWritten = false;
		/*we count the number of lines in the file*/
		int count = 0;
		
		try {
			File file = new File("/users/cristioara/workspace/CN_Tema06/out.txt");
			
			/*if file doesn't exists, then create it*/
			if (!file.exists()) {
				file.createNewFile();
			}
			
			BufferedReader br = null;
			 
			try {
				String sCurrentLine;
				br = new BufferedReader(new FileReader(file));
				
				int startIndex = 0;
				double currentValue;
				
				while ((sCurrentLine = br.readLine()) != null) {
					newlyCreated = false; //the file already exists
					count++;
					
					startIndex = sCurrentLine.indexOf(">");
					currentValue = Double.parseDouble(sCurrentLine.substring(startIndex + 2));
					if (Math.abs(currentValue - value) <= Math.pow(10, -1))
						alreadyWritten = true;
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			
			count++;
			
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			if (newlyCreated == true)
				bw.write("Solutia 1 --> " + String.valueOf(value));
			else {
				if (alreadyWritten == false) {
				bw.newLine();
				bw.append("Solutia " + count + " --> " + String.valueOf(value));
				}
			}
			
			fw.flush();
			bw.flush();
			fw.close();
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Computes the output of the polynomial represented in array in the point equal to value, using Horner's scheme.
	 * @param array Represents the indices of a polynomial.
	 * @param value Represents the number it computes the value of the polynomial in.
	 * @return The computed value.
	 */
	/*private double hornerValue(double[] array, double value) {
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
	/*private double[] derivativeArray(double[] array) {
		double[] result = new double[array.length - 1];
		
		for (int i=0; i<result.length; i++) {
			result[i] = array[i] * (array.length - 1 - i);
		}
		
		/*we add the last element, the one with no x*/
		//result[result.length - 1] += array[array.length - 1];
		
		//this.printArray(result, "Polinomul derivat:");
		
		//return result;
	//}
	
	/*public void printArray(double[] array, String message) {
		System.out.println(message);
		for (int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}*/
}
