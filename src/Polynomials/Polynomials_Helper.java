package Polynomials;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Polynomials_Helper{

	double[] myPolynomial;
	int n; //gradul polinomului
	
	public Polynomials_Helper(String fileName) {
		ReadFile rf = new ReadFile();
		this.myPolynomial = rf.readFromFile(fileName);
		this.n = myPolynomial.length - 1;	
	}

	public void solve(double initialValue) {
		
		ExecutorService executor = Executors.newFixedThreadPool(1);
        //create a list to hold the Future object associated with Callable
        Future<double[]> result;
        //Create MyCallable instance
        Callable<double[]> callable = (Callable<double[]>) new PolynomialRoot(this.myPolynomial, this.n, initialValue);
        Future<double[]> future = executor.submit(callable);
        result = future;
        try {
            this.writeToFile((result.get())[0]);
        } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
        }
        //shut down the executor service now
        executor.shutdown();
	}
	
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
			
			bw.flush();
			fw.close();
			bw.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
