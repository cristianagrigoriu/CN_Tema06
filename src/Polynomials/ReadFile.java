package Polynomials;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadFile {

	double[] myPolynomial;
	int n; //gradul polinomului

	public double[] readFromFile(String fileName) {
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
		
		return myPolynomial;
	}
	
	public void printArray(double[] array, String message) {
		System.out.println(message);
		for (int i=0; i<array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
