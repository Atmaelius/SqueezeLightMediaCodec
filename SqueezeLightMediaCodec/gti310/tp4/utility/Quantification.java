package gti310.tp4.utility;

/**
 * Classe responsable de la réalisation de l'opération de quantification
 * @author eric
 */

public class Quantification implements IConstants{

	/**
	 * Fonction qui a pour but de trouver la valeur du alpha utilisé dans la quantification 
	 * @param facteurQualite	un int entre 0 et 100 qui donne la qualité
	 * @return	alpha	La valeur du alpha selon la qualite
	 */
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
	
	
	/**
	 * Fonction qui effectue l'operation de quantification sur le array resultant de la dct 
	 * @param DCTArray	Le resultat de l'operation de dct
	 * @param facteurQualite	Le facteur de qualité voulu, entré par l'user a l'appel du programme
	 * @return	arrayQuantifie	Le array de la dct après la quantification
	 * Analyse de Complexité: O(N⁴);
	 */
	public static int[][][] quantification(float[][][] DCTArray, int facteurQualite){

		int[][][] arrayQuantifie = new int[COLOR_SPACE_SIZE][DCTArray[0].length][DCTArray[0].length]; // -> 3x256x256
		float alpha = -1;
		int nbBlock = DCTArray[0].length/BLOCK_SIZE;
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
	

	/**
	 * Fonction qui effectue la quantification inverse sur le array quantifié.
	 * @param arrayQuantifie	Le array quantifié
	 * @param facteurQualite	Le facteur de qualité voulu
	 * @return	arrayQuantifieInverse	Le array déquantifié
	 * Analyse de Complexité: O(N⁴);
	 */
	public static float[][][] reverseQuantification(int[][][] arrayQuantifie, int facteurQualite){
	
		float[][][] arrayQuantifieInverse = new float[COLOR_SPACE_SIZE][arrayQuantifie[0].length][arrayQuantifie[0].length];
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
	
	
}
