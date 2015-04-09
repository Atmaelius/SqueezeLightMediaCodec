package gti310.tp4.tests;

import java.io.FileNotFoundException;

import gti310.tp4.utility.DataDisplay;
import gti310.tp4.utility.IConstants;
import gti310.tp4.utility.ImageManipulator;

public class quantifier_tester implements IConstants {

	public static void main(String[] args) throws FileNotFoundException {
		
		// NORMALE 
/*	
		int[][][] arrayTestQuantificationResult = ImageManipulator.quantification(TestArrayResultFromFloatDCT, 50);
		DataDisplay.show8x8IntMatrix(arrayTestQuantificationResult);

		for (int i = 0; i < arrayTestQuantificationResult[0].length; i++) {
			for (int j = 0; j < arrayTestQuantificationResult[0].length; j++) {
				if(arrayTestQuantificationResult[0][i][j] != TestArrayResultFromIntQuantification[0][i][j]){
					System.out.println("ERROR:"+"I: " + i + " J: " + j + " -> " + arrayTestQuantificationResult[0][i][j] + " != " + TestArrayResultFromIntQuantification[i][j]);
				}
			}
		}
	*/
		
	/*
	
		int[] test = ImageManipulator.Zig_Zag(arrayTestQuantificationResult[0]);
		
		for (int i = 0; i < test.length; i++) {
			System.out.print(test[i]+", ");
		}
	*/
		
		
		// INVERSE
		float[][][] arrayTestQuantificationInverseResult = ImageManipulator.reverseQuantification(TestArrayResultFromIntQuantification, 50);
	
		DataDisplay.show8x8FloatMatrix(arrayTestQuantificationInverseResult);
		//DataDisplay.printToFile(DISPLAYPATH, arrayTestQuantificationInverseResult);
/*
		for (int i = 0; i < arrayTestQuantificationInverseResult[0].length; i++) {
			for (int j = 0; j < arrayTestQuantificationInverseResult[0].length; j++) {
				if(arrayTestQuantificationInverseResult[0][i][j] != TestArrayResultFromFloatDCT[0][i][j]){
					System.out.println("ERROR:"+"I: " + i + " J: " + j + " -> " + arrayTestQuantificationInverseResult[0][i][j] + " != " + TestArrayResultFromFloatDCT[0][i][j]);
				}
			}
		}
		*/

	}

}