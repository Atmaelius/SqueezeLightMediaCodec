package gti310.tp4.utility;

public class ColorConverter {


	// JE PENSE QUE LES COULEURS SONT CODÉES AINSI
	// FORMAT POUR LES COULEURS: [Pos X][Pos Y][RGB] -> {int x} {int y} {int R, int G, int B}
	// {{{R,G,B},{R,G,B}},{{R,G,B},{R,G,B}}} -> image 2x2 avec 4 couleurs différentes
	// {{{Y,Cb,Cr},{Y,Cb,Cr}},{{Y,Cb,Cr},{Y,Cb,Cr}},{{Y,Cb,Cr},{Y,Cb,Cr}}} -> image 2x3 avec 6 couleurs différentes


	//http://stackoverflow.com/questions/1067073/initialising-a-multidimensional-array-in-java

	/**
	 * Function that converts one RGBColor array into a YCbCrColor array
	 * @param color, an array {int RedValue, int GreenValue, int BlueValue}
	 * @return YCbCrResult, an array {float Y, float Cb, float Cr}
	 */
	public static float[] RGBToYCbCr(int color[]){
		float [] YCbCrResult = {0,0,0};
				
		// Y = 0,299 R + 0,587 G + 0,114 B
		YCbCrResult[0] = (float) ((0.299 * color[0]) + (0.587 * color[1]) + (0.114 * color[2]));  

		// Cb = (B-Y) / 1,772 + 0.5
		YCbCrResult[1] = (float) (((color[2] - YCbCrResult[0]) / 1.772) + 0.5);
		
		// Cr = (R-Y) / 1,402 + 0.5
		YCbCrResult[2] = (float) (((color[0] - YCbCrResult[0]) / 1.402) + 0.5);

		return YCbCrResult;
	}


	public static int[] YCbCrToRGB(float color[]){
		int [] RGBResult = {0,0,0};

		// R = Y + 1.402 (Cr - 128)
		RGBResult[0] = (int) (color[0] + 1.402 * (color[2] - 128));

		// G = Y - 0.34414 (Cb - 128) - 0.71414(Cr - 128)
		RGBResult[1] = (int) (color[0] - 0.34414 * (color[1] - 128) - 0.71414*(color[2] - 128));
		
		// B = Y + 1.772(Cb - 128)
		RGBResult[2] = (int) (color[0] + 1.772 * (color[1] - 128));

		return RGBResult;
	}
}
