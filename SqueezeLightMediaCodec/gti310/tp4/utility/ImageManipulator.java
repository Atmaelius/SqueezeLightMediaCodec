package gti310.tp4.utility;

import sun.org.mozilla.javascript.ast.Block;
import gti310.tp4.Entropy;
import gti310.tp4.PPMReaderWriter;
import gti310.tp4.utility.DataDisplay;

public class ImageManipulator implements IConstants{


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
	 *//*
	public static float[][][] DCTConversion(float[][][] originalArray){
		float[][][] tempDctArray = new float[3][8][8];
		float[][][] resultDctArray = new float[3][8][8];
		float[][][] returnedDctArray = new float[3][256][256];

		int nbBlock = originalArray[0].length/8;
		int indexCompteur = 0;

		int valX = 0;
		int valY = 0;

		for (int i = 0 ; i < originalArray.length; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					for (int y = 0; y < BLOCK_SIZE; y++) {
						for (int x = 0; x < BLOCK_SIZE ; x++) {
							valX = h * BLOCK_SIZE + x;
							valY = v * BLOCK_SIZE + y;

							tempDctArray[i][y][x] = originalArray[i][valY][valX];
						}
					}
					// -> la dct retourne des valeurs et il faut les ajouter au array resultat
					// les valeurs doivent êtres ajoutés a la suites des autres afin de retourner 
					// array resultat complet a la fin 
					resultDctArray = Converter.DCTConverter(tempDctArray);

					// afficher result array
					//		DataDisplay.showDCTBlock(resultDctArray[i], h, v, indexCompteur );

					indexCompteur++;

					for (int k = 0; k < BLOCK_SIZE; k++) {
						for (int l = 0;  l < BLOCK_SIZE; l++) {
							valX = h * BLOCK_SIZE + l;
							valY = v * BLOCK_SIZE + k;
							returnedDctArray[i][valY][valX] = resultDctArray[i][k][l];
						}
					}
				}
			}
		}

		try {
			//	printToFile("/test.txt", resultDctArray);
			DataDisplay.printToFile(DISPLAYPATH, returnedDctArray);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return returnedDctArray;
	}

	*/
	

	/**
	 * Function that applies a DCT conversion to the given array
	 * @param originalArray 3 dimensionnal array [R,G,B][0-256][0-256]
	 * @return ResultDctArray the resulting array of the conversion
	 */
	public static float[][][] DCTConversion(float[][][] originalArray){
		float[][] tempDctArray = new float[8][8];
		float[][] resultDctArray = new float[8][8];
		float[][][] returnedDctArray = new float[3][256][256];

		int nbBlock = originalArray[0].length/8;
		int indexCompteur = 0;

		int valX = 0;
		int valY = 0;

		for (int i = 0 ; i < originalArray.length; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					for (int y = 0; y < BLOCK_SIZE; y++) {
						for (int x = 0; x < BLOCK_SIZE ; x++) {
							valX = h * BLOCK_SIZE + x;
							valY = v * BLOCK_SIZE + y;

							tempDctArray[y][x] = originalArray[i][valY][valX];
						}
					}
					// -> la dct retourne des valeurs et il faut les ajouter au array resultat
					// les valeurs doivent êtres ajoutés a la suites des autres afin de retourner 
					// array resultat complet a la fin 
					resultDctArray = Converter.DCTConverter(tempDctArray);

					// afficher result array
					//		DataDisplay.showDCTBlock(resultDctArray[i], h, v, indexCompteur );

					indexCompteur++;

					for (int k = 0; k < BLOCK_SIZE; k++) {
						for (int l = 0;  l < BLOCK_SIZE; l++) {
							valX = h * BLOCK_SIZE + l;
							valY = v * BLOCK_SIZE + k;
							returnedDctArray[i][valY][valX] = resultDctArray[k][l];
						}
					}
				}
			}
		}

		try {
			//	printToFile("/test.txt", resultDctArray);
			DataDisplay.printToFile(DISPLAYPATH, returnedDctArray);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return returnedDctArray;
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
				}
			}
		}
		return resultDctArray;
	}


	public static float findAlpha(int facteurQualite){
		float alpha = -5;

		if((facteurQualite >= 1) && (facteurQualite <= 50)){
			alpha = 50 / facteurQualite;
		}
		else{
			alpha = ((200.0f - (2.0f * facteurQualite)) / 100.0f );
		}
		return alpha;
	}

	// array 3x 255 x255
	/*	public static int[][][] quantification(float[][][] DCTArray, int facteurQualite){

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
	 */


	public static int[][][] quantification(float[][][] DCTArray, int facteurQualite){

		
		int[][][] arrayQuantifie = new int[3][DCTArray[0].length][DCTArray[0].length]; // -> 3x256x256
		int[][][] tempArrayQuantifie = new int[3][BLOCK_SIZE][BLOCK_SIZE]; // -> 3x8x8

		float alpha = -1;
		int nbBlock = DCTArray[0].length/8;
		int valX = 0;
		int valY = 0;

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
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					for (int y = 0; y < BLOCK_SIZE; y++) {
						for (int x = 0; x < BLOCK_SIZE ; x++) {
							valX = h * BLOCK_SIZE + x;
							valY = v * BLOCK_SIZE + y;

							arrayQuantifie[Y][valY][valX] = (int) Math.round(DCTArray[Y][valY][valX] / (alpha * QuantificationQyTable[y][x]));
							arrayQuantifie[Cb][valY][valX] = (int) Math.round(DCTArray[Cb][valY][valX] / (alpha * QuantificationQcbQcrTable[y][x]));
							arrayQuantifie[Cr][valY][valX] = (int) Math.round(DCTArray[Cr][valY][valX] / (alpha * QuantificationQcbQcrTable[y][x]));
					
						}
					}
				}
			}
		}
		return arrayQuantifie;
	}


	public static float[][][] reverseQuantification(int[][][] arrayQuantifie, int facteurQualite){

	float[][][] arrayQuantifieInverse = new float[3][arrayQuantifie[0].length][arrayQuantifie[0].length];
	float alpha = -1;
	int nbBlock = arrayQuantifie[0].length/8;
	int valX = 0;
	int valY = 0;

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
		
		for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
			for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
				for (int y = 0; y < BLOCK_SIZE; y++) {
					for (int x = 0; x < BLOCK_SIZE ; x++) {
						valX = h * BLOCK_SIZE + x;
						valY = v * BLOCK_SIZE + y;
						arrayQuantifieInverse[Y][valY][valX] = (float) arrayQuantifie[Y][valY][valX] * (alpha * QuantificationQyTable[y][x]);
						arrayQuantifieInverse[Cb][valY][valX] = (float) arrayQuantifie[Cb][valY][valX] * (alpha * QuantificationQcbQcrTable[y][x]);
						arrayQuantifieInverse[Cr][valY][valX] = (float) arrayQuantifie[Cr][valY][valX] * (alpha * QuantificationQcbQcrTable[y][x]);
					}
				}
			}
		}
	}

	return arrayQuantifieInverse;
	}


/*
 * public static float[][][] reverseQuantification(int[][][] arrayQuantifie, int facteurQualite){

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
 */

// ZIG-ZAG -> source : http://rosettacode.org/wiki/Zig-zag_matrix

	public static int[][][][] zigzagger(int[][][] QuantificationResult){

		int valX = 0;
		int valY = 0;
		int nbBlock = QuantificationResult[0].length/8;
		int[][] tempZigZagArray = new int[nbBlock][nbBlock];
		int[][][][] returnedZigZagArray = new int[3][nbBlock][nbBlock][BLOCK_SIZE*BLOCK_SIZE];
		
		for (int i = 0 ; i < QuantificationResult.length; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					for (int y = 0; y < BLOCK_SIZE; y++) {
						for (int x = 0; x < BLOCK_SIZE ; x++) {
							valX = h * BLOCK_SIZE + x;
							valY = v * BLOCK_SIZE + y;
	
							tempZigZagArray[y][x] = QuantificationResult[i][valY][valX];
						}
					}
					returnedZigZagArray[i][v][h] = Zig_Zag(tempZigZagArray);
				}
			}
		}
		
		return returnedZigZagArray;
	}
	
	
	/**
	 * Traverser un tableau 8x8 et retourner les valeurs sous forme de array 1 dimension
	 * @param array
	 * @return
	 */
	public static int[] Zig_Zag(int[][] array){
		int size = BLOCK_SIZE;
		int[] data = new int[64];
		int i = 1;
		int j = 1;
	
		for (int element = 0; element < 64; element++){
			data[element] = array[i - 1][j - 1];
	
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

	
	public static void DPCM(int[][][][] ZigZagArray){

		int nbBlock = ZigZagArray[0].length;
		int[] valeurs = new int[1024];
		int index = 0;

		for (int i = 0 ; i < ZigZagArray.length; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X

					if(index < 1024){
						valeurs[index] = ZigZagArray[i][h][v][0];
					}
					index++;
				}
			}
		}

		int temp = 0;
		int[] diff = new int[1024];
		
		diff[0] =  valeurs[0];
		
		for (int i = 1; i < valeurs.length-1; i++) {
			diff[i] = valeurs[i] - valeurs[i-1];
		}
		diff[1023] = valeurs[1023] - valeurs[1022];
		
		
		for (int j = 0; j < diff.length; j++) {
			Entropy.writeDC(diff[j]);
		}
		
		
		
		
		
		/*
		 // par apres
		for (int i = 0 ; i < ZigZagArray.length; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X

					if(index < 1024){
						ZigZagArray[i][h][v][0] = diff[index];
					}
					index++;
				}
			}
		}
		*/
		
		System.out.println("HELL-O");
		
	}



//**     WRITER     ** \\					

	public static void writeRGBFile(String path, int[][][] intArray){
		PPMReaderWriter.writePPMFile(path, intArray);
	}
	
	
	public static void writeYCbCrFile(String path, float[][][] floatArray){
		PPMReaderWriter.writePPMFile(path, YCbCrToRGBImageConversion(floatArray));
	}


}

