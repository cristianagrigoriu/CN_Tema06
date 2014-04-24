package Polynomials;

import Graphics.FileChooser;
//import Polynomials.Polynomials_Helper;

public class Main_Polynomials {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*se alege fisierul*/
		FileChooser sfc = new FileChooser();
		sfc.setVisible(true);
		String fileName = sfc.createFileChooser();
		System.out.println("Ati ales fisierul " + fileName);		
		
		if (fileName != null) {
			Polynomials_Helper p = new Polynomials_Helper(fileName);
			//new class just for computing root
			
			p.solve(1.000000001);
			p.solve(2.000001);
			//p.solve(15);
			//p.solve(3.0000001);
		}
	}
}