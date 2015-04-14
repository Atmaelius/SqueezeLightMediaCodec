package gti310.tp4.utility;

/**
 * Classe qui contiens et gère les opérations relatives a l'opération de DCT
 * @author eric
 */

public class DCT implements IConstants{
	
	public static float[] C = new float[BLOCK_SIZE];

	/**
	 * Fonction qui applique un traitement DCT sur un array donné
	 * @param originalArray array 3 dimension [R,G,B][0-256][0-256]
	 * @return ResultDctArray le résultat de la conversion
	 */
	public static float[][][] DCTConversion(float[][][] originalArray){
		float[][] tempDctArray = new float[BLOCK_SIZE][BLOCK_SIZE];
		float[][] resultDctArray = new float[BLOCK_SIZE][BLOCK_SIZE];
		float[][][] returnedDctArray = new float[COLOR_SPACE_SIZE][256][256];
		int nbBlock = originalArray[0].length/BLOCK_SIZE;
		int valX = 0;
		int valY = 0;

		for (int i = 0 ; i < COLOR_SPACE_SIZE; i++) { // boucler a travers les composantes
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
					resultDctArray = DCTConverter(tempDctArray);

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

		return returnedDctArray;
	}

	
	/**
	 * Fonction qui effectue le traitement de DCT inverse
	 * @param originalArray	un array 3 dimensions de float
	 * @return	resultDctArray	Le array résultat de la DCT en 3 dimensions 
	 */
	public static float[][][] IDCTConversion(float[][][] originalArray){
		float[][] tempDctArray = new float[BLOCK_SIZE][BLOCK_SIZE];
		float[][][] resultDctArray = new float[COLOR_SPACE_SIZE][BLOCK_SIZE][BLOCK_SIZE];
		int nbBlock = originalArray[0].length/BLOCK_SIZE;

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
					resultDctArray[i] = DCTConverter(tempDctArray);
				}
			}
		}
		return resultDctArray;
	}
	
	
	/**
	 * FOnction qui applique le traitement de DCT a un array en 3 dimensions de float 
	 * @param block un block 8x8 de valeurs en float -> [RGB][0-7][0-7]
	 * @return F le résultat de l'opération
	 */
	// inspiré de http://stackoverflow.com/questions/4240490/problems-with-dct-and-idct-algorithm-in-java
	// ainsi que des notes de cours et énoncé de laboratoire
	public static float[][] DCTConverter(float[][] block){
		// manipuler un ensemble(bloc) de 8x8 pixel et applique le traitement de moyennes...
		// remplir le tableau C
		remplirC();
		
		float[][] F = new float[BLOCK_SIZE][BLOCK_SIZE];
		// boucler sur les 3 premieres dimensions[R,G,B]
			for (int u = 0; u < block.length; u++) {
				for (int v = 0; v < block.length; v++) {
					float somme = 0.0f;
					for (int i = 0; i < BLOCK_SIZE; i++) {
						for (int j = 0; j < BLOCK_SIZE; j++) {
							somme += ((C[u] * C[v])/4.0)*(Math.cos(((2.0 * i + 1.0) * u * Math.PI)/16.0) * Math.cos(((2.0 * j + 1.0) * v * Math.PI)/16.0)) * block[i][j];
						}
					}
					F[u][v] = Math.round(somme);
				}
			}	
			
		return F;
	}
	
	
	/**
	 * Fonction effectuant le traitement de DCT inverse sur un bloc 8x8
	 * @param block	un array en 2 dimensions 8x8 de float
	 * @return F tableau bi-dimensionnel, le résultat de l'opération
	 */
	// inspiré de http://stackoverflow.com/questions/4240490/problems-with-dct-and-idct-algorithm-in-java
	public static float[][] IDCTConverter(float[][] block){
		// remplir le tableau C
		remplirC();
		
		float[][] F = new float[BLOCK_SIZE][BLOCK_SIZE];
		// boucler sur les 3 premieres dimensions[R,G,B]
		for (int i = 0; i < BLOCK_SIZE; i++) {
			for (int j = 0; j < BLOCK_SIZE; j++) {
				float somme = 0;
				for (int u = 0; u < BLOCK_SIZE; u++) {
					for (int v = 0; v < BLOCK_SIZE; v++) {
						somme += ((C[u] * C[v])/4)*(Math.cos(((2 * i + 1) * u * Math.PI)/16) * Math.cos(((2 * j + 1) * v * Math.PI)/16)) * block[u][v];
					}
				}
				F[i][j] = Math.round(somme);
			}
		}	
		return F;
	}
	
	
	/**
	 * Fonction qui sers a remplir le tableau C
	 */
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
