package gti310.tp4.tests;

import gti310.tp4.utility.ImageManipulator;

public class DctTester {

	
	public static void main(String[] args) {
	
	float[][][] ArrayDCTTester = new float[][][]{
		{
			{200,202,189,188,189,175,175,175},
			{200,203,198,188,189,182,178,175},
			{203,200,200,195,200,187,185,175},
			{200,200,200,200,197,187,187,187},
			{200,205,200,200,195,188,187,175},
			{200,200,200,200,200,190,187,175},
			{205,200,199,200,191,187,187,175},
			{210,200,200,200,188,185,187,186}
			}
		};
	

	float[][][] ArrayDCTResult = new float[][][]{
		{
			{1539,65,-12,4,1,2,-8,5},
			{-16,3,2,0,0,-11,-2,3},
			{-12,6,11,-1,3,0,1,-2},
			{-8,3,-4,2,-2,-3,-5,-2},
			{0,-2,7,-5,4,0,-1,-4},
			{0,-3,-1,0,4,1,-1,0},
			{3,-2,-3,3,3,-1,-1,3},
			{-2,5,-2,4,-2,2,-3,0}
		}
	};
	
	
	//float[][][] convertedResult  = ImageManipulator.DCTConversion(ArrayDCTTester);
//	ImageManipulator.showDCTBlock(convertedResult[0]);
	
	/*
	
	for (int i = 0; i < convertedResult[0].length; i++) {
		for (int j = 0; j < convertedResult[0].length; j++) {
			if(convertedResult[0][i][j] != ArrayDCTResult[0][i][j]){
				System.out.println("ERROR:"+"I: " + i + " J: " + j);
			}
		}
	}
	
	*/

	
	float[][][] revertedResult  = ImageManipulator.IDCTConversion(ArrayDCTResult);

	ImageManipulator.showDCTBlock(revertedResult[0]);
	
	for (int i = 0; i < revertedResult[0].length; i++) {
		for (int j = 0; j < revertedResult[0].length; j++) {
			if(revertedResult[0][i][j] != ArrayDCTTester[0][i][j]){
				System.out.println("ERROR:"+"I: " + i + " J: " + j + " -> " + revertedResult[0][i][j] + " != " + ArrayDCTTester[0][i][j]);
			}
		}
	}
	
	

	





}
}
