package gti310.tp4.utility;

import gti310.tp4.Main;

public class Converter {
	
	public static float[] C = new float[Main.BLOCK_SIZE];
	
	
	//http://stackoverflow.com/questions/1067073/initialising-a-multidimensional-array-in-java
	/**
	 * Function that converts one RGBColor array into a YCbCrColor array
	 * @param color, an array {int RedValue, int GreenValue, int BlueValue}
	 * @return YCbCrResult, an array {float Y, float Cb, float Cr}
	 */
	public static float[] RGBToYCbCr(int Red, int Green, int Blue){
		float [] YCbCrResult = {0,0,0};
		
		YCbCrResult[0] = (float) Math.round( 0.299 * Red + 0.587 * Green + 0.114 * Blue); 
		YCbCrResult[1] = (float) Math.round(-0.16874 * Red - 0.33126 * Green + 0.50000 * Blue + 128); 
		YCbCrResult[2] = (float) Math.round( 0.50000 * Red - 0.41869 * Green - 0.08131 * Blue + 128);
		
		return YCbCrResult;
	}


	// sauce : https://msdn.microsoft.com/en-us/library/ff635267.aspx
	public static int[] YCbCrToRGB(float Y, float Cb, float Cr){
		int [] RGBResult = {0,0,0};

		RGBResult[0] = (int) ((Y*1) + (Cb*0) + (Cr*1.403));
		RGBResult[1] = (int) ((Y*1.0) + (Cb * -0.344) + (Cr * -0.714));
		RGBResult[2] = (int) ((Y*1.0) + (Cb * 1.77) + (Cr * 0));
		
		return RGBResult;
	}
	
	
	
	// inspiré de http://stackoverflow.com/questions/4240490/problems-with-dct-and-idct-algorithm-in-java
	public static float[][][] DCTconverter(float[][][] bloc){
		// manipuler un ensemble(bloc) de 8x8 pixel et applique le traitement de moyennes...
	/*	
		for (int i = 0; i < bloc.length; i++) {
			for (int j = 0; j < bloc.length; j++) {
				for (int k = 0; k < 8; k++) {
					System.out.print(bloc[i][j] + " " );
				}
				System.out.println();
			}
		}
	*/	
		
		
		// remplir le tableau C
		remplirC();
		
		float[][][] F = new float[3][Main.BLOCK_SIZE][Main.BLOCK_SIZE];

		for (int w = 0; w < 3; w++) {
				
			for (int u = 0; u < bloc.length; u++) {
			
				for (int v = 0; v < bloc.length; v++) {
					float somme = 0;
					
					for (int i = 0; i < bloc.length; i++) {
	
						for (int j = 0; j < bloc.length; j++) {
							somme += ((C[u] * C[v])/4)*(Math.cos(((2 * i + 1) * u * Math.PI)/16) * Math.cos(((2 * j + 1) * v * Math.PI)/16)) * bloc[w][i][j];
						}
					}
					F[w][u][v] = Math.round(somme);
				}
			}
		}
		return F;
	}
	
	
	private static void remplirC(){
	
		for (int i = 0; i < C.length; i++) {
			if(i == 0){
				C[i] = (float) (1/Math.sqrt(2));
			}
			else{
				C[i] = 1;
			}
		}
	}
	
}
