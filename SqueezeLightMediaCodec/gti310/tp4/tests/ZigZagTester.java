package gti310.tp4.tests;

import gti310.tp4.utility.DataDisplay;
import gti310.tp4.utility.ZigZag;

public class ZigZagTester {

	
	public static void main(String[] args) {
		
		
	//	int[] arrayValeursOriginal = new int[] {396,1,0,0,0,0,0,0,2,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			int[] arrayzigzaginverse = new int[] {396,1,2,1,0,0,0,0,0,0,0,-1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		int[][] array2dnoZig= new int[][] {
			{396,1,0,0,0,0,0,0},
			{2,0,0,0,1,0,0,0},
			{1,0,0,0,0,0,0,0},
			{0,-1,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0}
		};
	
		//	int[][] test = ZigZag.iZig_Zag(array2dnoZig);
		
		//int[][] test = ZigZag.reverseZig_Zag(array);
		
	//	DataDisplay.show8x8List(test);

		
		int[] teste = ZigZag.Zig_Zag(array2dnoZig);

		int[][] test =  ZigZag.reverseZig_Zag(teste);
		
		DataDisplay.show8x8List(test);
	
		/*
		int[] result = ZigZag.Zig_Zag(array2dnoZig);
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]+", ");
			
		}
		*/
	}
}
