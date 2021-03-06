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
			
			double r = p.getR();
			
			double random;
			
			int i = 0;
			
			while (i < 4) {
				random = Math.random() * 2 * r - r;
				System.out.println("r: " + r + " random: " + random);
				p.solve(random);
				i++;
			}
		}
	}
}