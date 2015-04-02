package gti310.tp4.utility;

import gti310.tp4.Main;
import gti310.tp4.PPMReaderWriter;

public class ImageManipulator {

	// The RGB color space.
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;

	// The entire application assumes that the blocks are 8x8 squares.
	public static final int BLOCK_SIZE = 8;
	

//**     CONVERSION     **\\
	
	public static float[][][] RGBToYCbCrImageConversion(int[][][] image){
		float[][][] yCbCrImageResult = new float[image.length][image[0].length][image[0][0].length];
		float [] tempYCbCrResult = {0,0,0};
		
		for (int i = 0; i < image[0].length; i++) { // on boucle sur le contenu de la seconde dimension du array()  normalement -> [0-256] 
			for (int j = 0; j < image[0][i].length; j++) { // on boucle sur le contenu de la troisiemme dimension -> [0-256]
				tempYCbCrResult = Converter.RGBToYCbCr(image[R][i][j], image[G][i][j], image[B][i][j]);
				yCbCrImageResult[R][i][j] = tempYCbCrResult[R];
				yCbCrImageResult[G][i][j] = tempYCbCrResult[G];
				yCbCrImageResult[B][i][j] = tempYCbCrResult[B];
			}
		}
		return yCbCrImageResult;
	}
	
	
	public static int[][][] YCbCrToRGBImageConversion(float[][][] image){
		int[][][] RGBImageResult = new int[image.length][image[0].length][image[0][0].length];
		int[] tempRGBResult = {0,0,0};

		for (int i = 0; i < image[0].length; i++) { // on boucle sur le contenu de la seconde dimension du array()  normalement -> [0-256] 
			for (int j = 0; j < image[0][i].length; j++) { // on boucle sur le contenu de la troisiemme dimension -> [0-256]
				tempRGBResult = Converter.YCbCrToRGB(image[R][i][j], image[G][i][j], image[B][i][j]);
				RGBImageResult[R][i][j] = tempRGBResult[R];
				RGBImageResult[G][i][j] = tempRGBResult[G];
				RGBImageResult[B][i][j] = tempRGBResult[B];
			}
		}
		return RGBImageResult;
	}
	
	
	/**
	 * Function that applies a DCT conversion to the given array
	 * @param originalArray 3 dimensionnal array [R,G,B][0-256][0-256]
	 * @return ResultDctArray the resulting array of the conversion
	 */
	public static float[][][] DCTConversion(float[][][] originalArray){
		float[][] tempDctArray = new float[8][8];
		float[][][] resultDctArray = new float[3][8][8];
		int nbBlock = originalArray[0].length/8;
		
		for (int i = 0 ; i < originalArray.length; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					for (int y = 0; y < Main.BLOCK_SIZE; y++) {
						for (int x = 0; x < Main.BLOCK_SIZE ; x++) {
							int valX = h * Main.BLOCK_SIZE + x;
							int valY = v * Main.BLOCK_SIZE + y;
						
							tempDctArray[y][x] = originalArray[i][valY][valX];
						}
					}
					// effectuer la conversion avec la fonction de DCT
					resultDctArray[i] = Converter.DCTConverter(tempDctArray);
					
					// afficher result array
	//				showDCTBlock(resultDctArray[i]);
				}
			}
		}
		return resultDctArray;
	}
	
	
	
	
	public static float[][][] IDCTConversion(float[][][] originalArray){
		float[][] tempDctArray = new float[8][8];
		float[][][] resultDctArray = new float[3][8][8];
		int nbBlock = originalArray[0].length/8;
		
		for (int i = 0 ; i < originalArray.length; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					for (int y = 0; y < Main.BLOCK_SIZE; y++) {
						for (int x = 0; x < Main.BLOCK_SIZE ; x++) {
							int valX = h * Main.BLOCK_SIZE + x;
							int valY = v * Main.BLOCK_SIZE + y;
						
							tempDctArray[y][x] = originalArray[i][valY][valX];
						}
					}
					// effectuer la conversion avec la fonction de DCT
					resultDctArray[i] = Converter.IDCTConverter(tempDctArray);
					
					// afficher result array
	//				showDCTBlock(resultDctArray[i]);
				}
			}
		}
		return resultDctArray;
	}
	
	

//**     WRITER     ** \\					
	
	public static void writeRGBFile(String path, int[][][] intArray){
		PPMReaderWriter.writePPMFile(path, intArray);
	}
	

	public static void writeYCbCrFile(String path, float[][][] floatArray){
		PPMReaderWriter.writePPMFile(path, YCbCrToRGBImageConversion(floatArray));
	}
	
	
	
//**     DISPLAY     **\\
	
	public static void show8x8List(float[][] array){
		
		for (int i = 0; i < array.length; i+=Main.BLOCK_SIZE) {
			for (int j = 0; j < Main.BLOCK_SIZE; j++) {
				for (int k = 0; k < Main.BLOCK_SIZE; k++) {
					System.out.println("("+j+","+k+")->" + array[j][k]);
				}
			}
		}
	}
	
	
	public static void showDCTBlock(float[][] array){
		System.out.println("Block 8x8 DCT");
		for (int y = 0; y < Main.BLOCK_SIZE; y++) {
			for (int x = 0; x < Main.BLOCK_SIZE; x++) {
				System.out.printf("%3.2f\t",array[y][x]);
			}
			System.out.println();
		}
	}
	
	
	// IL SEMBLE QUE LA MÉTHODE NE PERMETTE PAS DAFFICHER LES DONNES
	// IL FAUDRA EN FAIRE UNE NOUVELLE POUR ADAPTER POUR LE DCT
	public static void show8x8FloatMatrix(float[][][] array){ // testé avec les float pre-dct
		
		int valX = -1;
		int valY = -1;
		int nbBlock = (array[0][0].length/Main.BLOCK_SIZE);
		
		for (int i = 0 ; i < 3; i++) { // boucler a travers les composantes [Y,Cb,Cr]
			for (int v = 0; v < nbBlock; v++) { // boucler a travers l'ensemble des blocs verticalement > Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers l'ensemble des blocs horizontalement -> X
				   
					System.out.printf("Block(%d,%d)\n",h,v);
					
					for (int y = 0; y < Main.BLOCK_SIZE; y++) { // boucler sur la hauteur d'un block
						for (int x = 0; x < Main.BLOCK_SIZE ; x++) { // boucler sur la largeur du block
							valX = h * Main.BLOCK_SIZE + x;
							valY = v * Main.BLOCK_SIZE + y;
					//		System.out.print(array[i][valY][valX]  + " ->"+"["+i+"]"+"["+valY+"]"+"["+valX+"]  " );
				//			System.out.print(array[i][valY][valX]  );
							System.out.printf("%3.2f\t",array[i][valY][valX]);
						}
						System.out.println();
					}
				}
			}
		}
	}

	
	public static void show8x8IntMatrix(int[][][] array){ // pas testé
	
		int valX = -1;
		int valY = -1;
		int nbBlock = array[0].length/8;
		
		System.out.println(nbBlock);
		
		for (int i = 0 ; i < 2; i++) { // boucler a travers les composantes
			for (int v = 0; v< nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					    System.out.printf("Block(%d,%d)\n",h,v);
						for (int y = 0; y < 8; y++) {
							
							for (int x = 0; x < 8 ; x++) {
							
							valX = h * 8 + x;
							valY = v * 8 + y;
							
							System.out.printf("%d\t",array[i][valY][valX]);
						}
					    System.out.println();
					}
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



/*
public static void get8x8Matrix(float[][][] array){
	float[][] tempDctArray = new float[8][8];

	int nbBlock = array[0].length/8;
	
	for (int i = 0 ; i < 2; i++) { // boucler a travers les composantes
		for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
			for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
				for (int y = 0; y < 8; y++) {
					for (int x = 0; x < 8 ; x++) {
						int valX = h * 8 + x;
						int valY = v * 8 + y;
					
						System.out.printf("%3.2f\t",array[i][valY][valX]);
						tempDctArray[y][x] = array[i][valY][valX];
					}
					
					System.out.println();
				}
				// envoyer a DCT
			}
		}
	}
}
*/


