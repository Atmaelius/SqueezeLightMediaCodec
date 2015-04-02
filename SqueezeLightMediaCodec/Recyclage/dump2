package gti310.tp4.utility;

public class Converter {

	
	//http://stackoverflow.com/questions/1067073/initialising-a-multidimensional-array-in-java
	/**
	 * Function that converts one RGBColor array into a YCbCrColor array
	 * @param color, an array {int RedValue, int GreenValue, int BlueValue}
	 * @return YCbCrResult, an array {float Y, float Cb, float Cr}
	 */
	public static float[] RGBToYCbCr(int Red, int Green, int Blue){
		float [] YCbCrResult = {0,0,0};
	/*			
		// Y = 0,299 R + 0,587 G + 0,114 B
		YCbCrResult[0] = (float) ((0.299 * Red) + (0.587 * Green) + (0.114 * Blue));  

		// Cb = (B-Y) / 1,772 + 0.5
		YCbCrResult[1] = (float) (((Blue - YCbCrResult[0]) / 1.772) + 0.5);
		
		// Cr = (R-Y) / 1,402 + 0.5
		YCbCrResult[2] = (float) (((Red - YCbCrResult[0]) / 1.402) + 0.5);
*/
		
		/*
		YCbCrResult[0] = (float) ((0.299 * Red) + (0.587 * Green) + (0.114 * Blue));  
		YCbCrResult[1] = (float) (-0.16874 * Red - 0.33126 * Green + 0.50000 * Blue);
		YCbCrResult[2] = (float) (( 0.50000 * Red - 0.41869 * Green - 0.08131 * Blue));
		*/
		
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
		
	/*	
	  // verification pour ne pas depasser les valeurs max et min de la conversion.
	  // pas necessaire avec le système de conversion par multiplications
		RGBResult[0] = Math.max(0, Math.min(255, RGBResult[0]));
		RGBResult[1] = Math.max(0, Math.min(255, RGBResult[1]));
		RGBResult[2] = Math.max(0, Math.min(255, RGBResult[2]));
		 
	*/	
		return RGBResult;
	}
	
	
	
	
	public static void DCTconverter(float[][][] bloc){
		// manipuler un ensemble(bloc) de 8x8 pixel et applique le traitement de moyennes...
		
		
		
	}
	
	
	
	public static float C(int w){
		if(w == 0){
			return (float) (1/Math.sqrt( (double) 2 ));
		}
		return 1;
	}
	
	
	
}
