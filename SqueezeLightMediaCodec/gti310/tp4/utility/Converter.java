package gti310.tp4.utility;

public class Converter implements IConstants{
	
	public static float[] C = new float[BLOCK_SIZE];
	
	
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
	
	
	/**
	 * Function that applies a DCT conversion to a 3 dimensionnal array of float values 
	 * @param block a block of 8x8 float values -> [RGB][0-7][0-7]
	 * @return
	 */
	// inspiré de http://stackoverflow.com/questions/4240490/problems-with-dct-and-idct-algorithm-in-java
	public static float[][][] DCTConverter(float[][][] block){
		// manipuler un ensemble(bloc) de 8x8 pixel et applique le traitement de moyennes...
		// remplir le tableau C
		remplirC();
		
		float[][][] F = new float[3][BLOCK_SIZE][BLOCK_SIZE];
		
		// boucler sur les 3 premieres dimensions[R,G,B]
		for (int q = 0; q < 3; q++) {
			for (int u = 0; u < block.length; u++) {
				for (int v = 0; v < block.length; v++) {
					float somme = 0;
					for (int i = 0; i < block.length; i++) {
						for (int j = 0; j < block.length; j++) {
							somme += ((C[u] * C[v])/4)*(Math.cos(((2 * i + 1) * u * Math.PI)/16) * Math.cos(((2 * j + 1) * v * Math.PI)/16)) * block[q][i][j];
						}
					}
					F[q][u][v] = Math.round(somme);
				}
			}	
		}
		return F;
	}
	
	
	// inspiré de http://stackoverflow.com/questions/4240490/problems-with-dct-and-idct-algorithm-in-java
	public static float[][] IDCTConverter(float[][] block){
		// remplir le tableau C
		remplirC();
		
		float[][] F = new float[BLOCK_SIZE][BLOCK_SIZE];
		// boucler sur les 3 premieres dimensions[R,G,B]
		for (int i = 0; i < block.length; i++) {
			for (int j = 0; j < block.length; j++) {
				float somme = 0;
				for (int u = 0; u < block.length; u++) {
					for (int v = 0; v < block.length; v++) {
						somme += ((C[u] * C[v])/4)*(Math.cos(((2 * i + 1) * u * Math.PI)/16) * Math.cos(((2 * j + 1) * v * Math.PI)/16)) * block[u][v];
					}
				}
				F[i][j] = Math.round(somme);
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
