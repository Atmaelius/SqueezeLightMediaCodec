package gti310.tp4.utility;

/**
 * @author eric
 */

public class ColorConverter implements IConstants{
	
	
	/** Fonction qui convertis une image RGB en format YCbCr
	 * @param image	le array de int en 3 dimensions représentant l'image
	 * @return	yCbCbImageResult	Array 3 dimensions de float représentant l'image convertie en YCbCr
	 */
	public static float[][][] RGBToYCbCrImageConversion(int[][][] image){
		float[][][] yCbCrImageResult = new float[image.length][image[0].length][image[0][0].length];
		float [] tempYCbCrResult = {0,0,0};

		for (int i = 0; i < image[0].length; i++) { // on boucle sur le contenu de la seconde dimension du array()  normalement -> [0-256] 
			for (int j = 0; j < image[0][i].length; j++) { // on boucle sur le contenu de la troisiemme dimension -> [0-256]
				tempYCbCrResult = RGBToYCbCr(image[R][i][j], image[G][i][j], image[B][i][j]);
				yCbCrImageResult[R][i][j] = tempYCbCrResult[R];
				yCbCrImageResult[G][i][j] = tempYCbCrResult[G];
				yCbCrImageResult[B][i][j] = tempYCbCrResult[B];
			}
		}
		return yCbCrImageResult;
	}

	
	//http://stackoverflow.com/questions/1067073/initialising-a-multidimensional-array-in-java
	/**
	 * Function that converts one RGBColor array into a YCbCrColor array
	 * @param redValue  integer
	 * @param greenValue integer
	 * @param blueValue integer
	 * @return yCbCrResult, an array {float Y, float Cb, float Cr}
	 */
	public static float[] RGBToYCbCr(int redValue, int greenValue, int blueValue){
		float [] YCbCrResult = {0,0,0};
		YCbCrResult[0] = (float) Math.round( 0.299 * redValue + 0.587 * greenValue + 0.114 * blueValue); 
		YCbCrResult[1] = (float) Math.round(-0.16874 * redValue - 0.33126 * greenValue + 0.50000 * blueValue + 128); 
		YCbCrResult[2] = (float) Math.round( 0.50000 * redValue - 0.41869 * greenValue - 0.08131 * blueValue + 128);
		return YCbCrResult;
	}
	

	/** Fonction qui convertis une image YCbCb en format RGB
	 * @param image	le array de float en 3 dimensions représentant l'image
	 * @return	RGBImageResult	Array 3 dimensions de int représentant l'image convertie en YCbCr
	 */
	public static int[][][] YCbCrToRGBImageConversion(float[][][] image){
		int[][][] RGBImageResult = new int[image.length][image[0].length][image[0][0].length];
		int[] tempRGBResult = {0,0,0};

		for (int i = 0; i < image[0].length; i++) { // on boucle sur le contenu de la seconde dimension du array()  normalement -> [0-256] 
			for (int j = 0; j < image[0][i].length; j++) { // on boucle sur le contenu de la troisiemme dimension -> [0-256]
				tempRGBResult = YCbCrToRGB(image[Y][i][j], image[Cb][i][j], image[Cr][i][j]);
				RGBImageResult[Y][i][j] = tempRGBResult[Y];
				RGBImageResult[Cb][i][j] = tempRGBResult[Cb];
				RGBImageResult[Cr][i][j] = tempRGBResult[Cr];
			}
		}
		return RGBImageResult;
	}
	
	
	/**
	 * Function that converts one RGBColor array into a YCbCrColor array
	 * @param Y float
	 * @param Cb float
	 * @param Cr float
	 * @return RGBResult, an array {float Y, float Cb, float Cr}
	 */
	// sauce : https://msdn.microsoft.com/en-us/library/ff635267.aspx
	public static int[] YCbCrToRGB(float Y, float Cb, float Cr){
		int [] RGBResult = {0,0,0};
		RGBResult[0] = (int) ((Y*1) + (Cb*0) + (Cr*1.403));
		RGBResult[1] = (int) ((Y*1.0) + (Cb * -0.344) + (Cr * -0.714));
		RGBResult[2] = (int) ((Y*1.0) + (Cb * 1.77) + (Cr * 0));
		return RGBResult;
	}
	
}
