package gti310.tp4.utility;

import gti310.tp4.Main;
import gti310.tp4.PPMReaderWriter;

public class ImageManipulator implements IConstants{

	
	private static float[][][] resultDctArray = new float[3][256][256];

	
	//**     CONVERSION     **\\
	
	public static float[][][] RGBToYCbCrImageConversion(int[][][] image){
		float[][][] yCbCrImageResult = new float[image.length][image[0].length][image[0][0].length];
		float [] tempYCbCrResult = {0,0,0};
		
		for (int i = 0; i < image[0].length; i++) { // on boucle sur le contenu de la seconde dimension du array()  normalement -> [0-256] 
			for (int j = 0; j < image[0][i].length; j++) { // on boucle sur le contenu de la troisiemme dimension -> [0-256]
				tempYCbCrResult = Converter.RGBToYCbCr(image[R][i][j], image[R][i][j], image[R][i][j]);
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
		float[][][] tempDctArray = new float[3][8][8];
		int nbBlock = originalArray[0].length/8;
		
		for (int i = 0 ; i < originalArray.length; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					for (int y = 0; y < BLOCK_SIZE; y++) {
						for (int x = 0; x < BLOCK_SIZE ; x++) {
							int valX = h * BLOCK_SIZE + x;
							int valY = v * BLOCK_SIZE + y;
						
							tempDctArray[i][y][x] = originalArray[i][valY][valX];
						}
					}
					// effectuer la conversion avec la fonction de DCT
					
					// -> la dct retourne des valeurs et il faut les ajouter au array resultat
					// les valeurs doivent êtres ajoutés a la suites des autres afin de retourner 
					// array resultat complet a la fin 
					tempDctArray = Converter.DCTConverter(tempDctArray);
					
				//	tempDctArray = Converter.DCTConverter(tempDctArray);
					
					
				/*	
					for (int j = resultDctArray.length; j < tempDctArray.length; j++) {
						for (int k = resultDctArray[0].length; k < tempDctArray[0].length; k++) {
							for (int l = resultDctArray[0][0].length;  l < tempDctArray[0][0].length; l++) {
								resultDctArray[j][k][l] = tempDctArray[j][k][l];
							}
						}
					}
			*/
					// afficher result array
					showDCTBlock(resultDctArray[i]);
				}
			}
		}
		return resultDctArray;
	}
	
	
	
	
	public static float[][][] IDCTConversion(float[][][] originalArray){
		float[][] tempDctArray = new float[BLOCK_SIZE][BLOCK_SIZE];
		float[][][] resultDctArray = new float[3][BLOCK_SIZE][BLOCK_SIZE];
		int nbBlock = originalArray[0].length/8;
		
		for (int i = 0 ; i < originalArray.length; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					for (int y = 0; y < BLOCK_SIZE; y++) {
						for (int x = 0; x < BLOCK_SIZE ; x++) {
							int valX = h * BLOCK_SIZE + x;
							int valY = v * BLOCK_SIZE + y;
						
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
	

	public static float findAlpha(int facteurQualite){
		float alpha = 0;
		
		if((facteurQualite >= 1) && (facteurQualite <= 50)){
			alpha = 50 / facteurQualite;
		}
		else{
			alpha = ((200 - (2 * facteurQualite)) / 100);
		}
		return alpha;
	}
	
	
	public static int[][][] quantification(float[][][] DCTArray, int facteurQualite){
		
		int[][][] arrayQuantifie = new int[3][BLOCK_SIZE][BLOCK_SIZE];
		float alpha = -1;
		
		if(facteurQualite == 100){ // pas de changements
			for (int j = 0; j < DCTArray[0].length; j++) {
				for (int k = 0; k < DCTArray[0][0].length; k++) {
					arrayQuantifie[Y][j][k] = (int) Math.round(DCTArray[Y][j][k]);
					arrayQuantifie[Cb][j][k] = (int) Math.round(DCTArray[Cb][j][k]);
					arrayQuantifie[Cr][j][k] = (int) Math.round(DCTArray[Cr][j][k]);
				}
			}
		}
		else{ // si facteur de qualite est autre que 100
			alpha = findAlpha(facteurQualite);
			for (int i = 0; i < DCTArray[0].length; i++) {
				for (int j = 0; j < DCTArray[0][0].length; j++) {
					arrayQuantifie[Y][i][j] =(int) Math.round(DCTArray[Y][i][j] / (alpha * QuantificationQyTable[i][j]));
					arrayQuantifie[Cb][i][j] = (int) Math.round(DCTArray[Cb][i][j] / (alpha * QuantificationQcbQcrTable[i][j]));
					arrayQuantifie[Cr][i][j] = (int) Math.round(DCTArray[Cr][i][j] / (alpha * QuantificationQcbQcrTable[i][j]));
				}
			}
		}
		
		return arrayQuantifie;
	}
	
	
	
	public static float[][][] reverseQuantification(int[][][] arrayQuantifie, int facteurQualite){
		
		float[][][] arrayQuantifieInverse = new float[3][BLOCK_SIZE][BLOCK_SIZE];
		float alpha = -1;
		
		if(facteurQualite == 100){ // pas de changements
			for (int j = 0; j < arrayQuantifie[0].length; j++) {
				for (int k = 0; k < arrayQuantifie[0][0].length; k++) {
					arrayQuantifieInverse[Y][j][k] =  Math.round(arrayQuantifie[Y][j][k]);
					arrayQuantifieInverse[Cb][j][k] = Math.round(arrayQuantifie[Cb][j][k]);
					arrayQuantifieInverse[Cr][j][k] = Math.round(arrayQuantifie[Cr][j][k]);
				}
			}
		}
		else{ // si facteur de qualite est autre que 100
			alpha = findAlpha(facteurQualite);
			for (int i = 0; i < arrayQuantifie[0].length; i++) {
				for (int j = 0; j < arrayQuantifie[0][0].length; j++) {
					arrayQuantifieInverse[Y][i][j] = (float) arrayQuantifie[Y][i][j] * (alpha * QuantificationQyTable[i][j]);
					arrayQuantifieInverse[Cb][i][j] = (float) arrayQuantifie[Cb][i][j] * (alpha * QuantificationQcbQcrTable[i][j]);
					arrayQuantifieInverse[Cr][i][j] = (float) arrayQuantifie[Cr][i][j] * (alpha * QuantificationQcbQcrTable[i][j]);
				}
			}
		}
		
		return arrayQuantifieInverse;
	}

	
	
	// ZIG-ZAG -> source : http://rosettacode.org/wiki/Zig-zag_matrix

	public static int[] Zig_Zag(int[][] QuantificationResult){
		int size = BLOCK_SIZE;
		int[] data = new int[64];
		int i = 1;
		int j = 1;

		for (int element = 0; element < 64; element++){
			data[element] = QuantificationResult[i - 1][j - 1];
			
			if ((i + j) % 2 == 0){ // Even stripes
				if (j < size){
					j++;
				}
				else{
					i+= 2;
				}
				if (i > 1){
					i--;
				}
			}
			else{// Odd stripes
				if (i < size){
					i++;
				}
				else{
					j+= 2;
				}
				if (j > 1){
					j--;
				}
			}
		}
		return data;
	}

	/*
	public static int[][] Zig_Zag(int size){
		int[][] data = new int[size][size];
		int i = 1;
		int j = 1;

		for (int element = 0; element < size * size; element++){
			data[i - 1][j - 1] = element;
			
			if ((i + j) % 2 == 0){ // Even stripes
				if (j < size){
					j++;
				}
				else{
					i+= 2;
				}
				if (i > 1){
					i--;
				}
			}
			else{// Odd stripes
				if (i < size){
					i++;
				}
				else{
					j+= 2;
				}
				if (j > 1){
					j--;
				}
			}
		}
		
		return data;
		
	}
*/


//**     WRITER     ** \\					
	
	public static void writeRGBFile(String path, int[][][] intArray){
		PPMReaderWriter.writePPMFile(path, intArray);
	}
	

	public static void writeYCbCrFile(String path, float[][][] floatArray){
		PPMReaderWriter.writePPMFile(path, YCbCrToRGBImageConversion(floatArray));
	}
	
	
	
//**     DISPLAY     **\\
	
	public static void show8x8List(int[][] array){
		
		for (int i = 0; i < array.length; i+=BLOCK_SIZE) {
			for (int j = 0; j < BLOCK_SIZE; j++) {
				for (int k = 0; k < BLOCK_SIZE; k++) {
					System.out.println("("+j+","+k+")->" + array[j][k]);
				}
			}
		}
	}
	
	
	public static void showDCTBlock(float[][] array){
		System.out.println("Block 8x8 DCT");
		for (int y = 0; y < BLOCK_SIZE; y++) {
			for (int x = 0; x < BLOCK_SIZE; x++) {
				System.out.printf("%3.2f\t",array[y][x]);
			}
			System.out.println();
		}
	}
	
	
	public static void show8x8FloatMatrix(float[][][] array){ // testé avec les float pre-dct
		
		int valX = -1;
		int valY = -1;
		int nbBlock = (array[0][0].length/BLOCK_SIZE);
		
		for (int i = 0 ; i < array.length; i++) { // boucler a travers les composantes [Y,Cb,Cr]
			for (int v = 0; v < nbBlock; v++) { // boucler a travers l'ensemble des blocs verticalement > Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers l'ensemble des blocs horizontalement -> X
				   
					System.out.printf("Block(%d,%d)\n",h,v);
					
					for (int y = 0; y < BLOCK_SIZE; y++) { // boucler sur la hauteur d'un block
						for (int x = 0; x < BLOCK_SIZE ; x++) { // boucler sur la largeur du block
							valX = h * BLOCK_SIZE + x;
							valY = v * BLOCK_SIZE + y;
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
		
		for (int i = 0 ; i < array.length; i++) { // boucler a travers les composantes
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

