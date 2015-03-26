package gti310.tp4.utility;

import gti310.tp4.PPMReaderWriter;

public class PixelManipulation {

	// The RGB color space.
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;

	// The entire application assumes that the blocks are 8x8 squares.
	public static final int BLOCK_SIZE = 8;
	
	
	
	public static float[][][] RGBToYCbCrImageConversion(int[][][] image){

		float[][][] YcbCrImageResult = new float[image.length][image[0].length][image[0][0].length];

		for (int i = 0; i < image[0].length; i++) { // on boucle sur la premiere dimension du array(0,1,2) ca change rien 
			for (int j = 0; j < image[0][i].length; j++) {
				
				float [] tempYCbCrResult = {0,0,0};
				tempYCbCrResult = Converter.RGBToYCbCr(image[R][i][j], image[G][i][j], image[B][i][j]);
				YcbCrImageResult[R][i][j] = tempYCbCrResult[R];
				YcbCrImageResult[G][i][j] = tempYCbCrResult[G];
				YcbCrImageResult[B][i][j] = tempYCbCrResult[B];
			}
		}
		
		return YcbCrImageResult;
	}
	
	
	public static int[][][] YCbCrToRGBImageConversion(float[][][] image){
		
		int[][][] RGBImageResult = new int[image.length][image[0].length][image[0][0].length];

		for (int i = 0; i < image[0].length; i++) { // on boucle sur la premiere dimension du array(0,1,2) ca change rien 
			for (int j = 0; j < image[0][i].length; j++) {
				
				int[] tempRGBResult = {0,0,0};
				tempRGBResult = Converter.YCbCrToRGB(image[R][i][j], image[G][i][j], image[B][i][j]);
				RGBImageResult[R][i][j] = tempRGBResult[R];
				RGBImageResult[G][i][j] = tempRGBResult[G];
				RGBImageResult[B][i][j] = tempRGBResult[B];
			}
		}
		return RGBImageResult;
	}
	
	
	
	public static void writeRGBFile(String path, int[][][] intArray){
		PPMReaderWriter.writePPMFile(path, intArray);
	}
	

	public static void writeYCbCrFile(String path, float[][][] floatArray){
		PPMReaderWriter.writePPMFile(path, YCbCrToRGBImageConversion(floatArray));
	}
	
	
	public static void show8x8List(float[][] array){
		
		for (int i = 0; i < array.length; i+=8) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					System.out.println("("+j+","+k+")->" + array[j][k]);
				}
			}
		}
	}
	
	
	public static void show8x8FloatMatrix(float[][][] array){
	
		int nbBlock = array[0].length/8;
		
		System.out.println(nbBlock);
		
		for (int i = 0 ; i < 2; i++) { // boucler a travers les composantes
			for (int v = 0; v< nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					    System.out.printf("Block(%d,%d)\n",h,v);
						for (int y = 0; y < 8; y++) {
							
							for (int x = 0; x < 8 ; x++) {
							
							int valX = h * 8 + x;
							int valY = v * 8 + y;
							System.out.printf("%3.2f\t",array[i][valY][valX]);
						}
					    System.out.println();
					}
				}
			}
		}
		
	}
	
	

	public static void show8x8IntMatrix(int[][][] array){
	
		int nbBlock = array[0].length/8;
		
		System.out.println(nbBlock);
		
		for (int i = 0 ; i < 2; i++) { // boucler a travers les composantes
			for (int v = 0; v< nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					    System.out.printf("Block(%d,%d)\n",h,v);
						for (int y = 0; y < 8; y++) {
							
							for (int x = 0; x < 8 ; x++) {
							
							int valX = h * 8 + x;
							int valY = v * 8 + y;
							System.out.printf("%d\t",array[i][valY][valX]);
						}
					    System.out.println();
					}
				}
			}
		}
		
	}
	
	/*
	public static void show8x8Matrix(float[][] array){
		String str = "|\t";	
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				str += array[i][j] + "\t";
			}
			System.out.println(str + "|");
			str = "|\t";
		}
		System.out.println("\n");
	}
	*/
	
	public static float[][][] DCTConversion(float[][][] originalArray){
		// prendre l'image
		// boucler sur l'image et envoyer un ensemble de 8x8 pixels a la méthode de DCT
		/*
		float[][] F = new float[BLOCK_SIZE][BLOCK_SIZE];
		
		// boucler sur les couleurs
		for (int i = 0; i < originalArray.length; i++) {
			// boucler sur les 8 premieres colonnes
			for (int j = 0; j < originalArray[i].length; j+=BLOCK_SIZE) {
				// boucler sur les 8 lignes
				for (int k = 0; k < originalArray[i][j].length; k++) {
			//		System.out.println(originalArray[i][j][k]);
					// what ?
				}
				
			}
		}
		*/
	//	show8x8Matrix(originalArray);
		
		
		return null;
	}
	
	
	
	/*
	 * 
				// Reception
				temp = ColorConverter.YCbCrToRGB(YCbCrResult[R],YCbCrResult[G],YCbCrResult[B]);  
				
				imageResult[R][i][j] = temp[R];
				imageResult[G][i][j] = temp[G];
				imageResult[B][i][j] = temp[B];
	 * 
	 */
	
}
