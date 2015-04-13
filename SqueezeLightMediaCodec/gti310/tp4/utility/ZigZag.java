package gti310.tp4.utility;

/**
 * @author eric
 */

public class ZigZag implements IConstants{
	
	/**
	 * Fonction qui sers a effectuer le traitement de parcours Zig-Zag afin de rassemble les zéros ensembles le plus possible
	 * @param QuantificationResult	Le résultat de la quantification
	 * @return	returnedZigZagArray	Le array
	 */
	public static int[][][][] zigzagger(int[][][] QuantificationResult){

		int valX = 0;
		int valY = 0;
		int nbBlock = QuantificationResult[0].length/8;
		int[][] tempZigZagArray = new int[nbBlock][nbBlock];
		int[][][][] returnedZigZagArray = new int[COLOR_SPACE_SIZE][nbBlock][nbBlock][BLOCK_SIZE*BLOCK_SIZE];
		
		for (int i = 0 ; i < COLOR_SPACE_SIZE; i++) { // boucler a travers les composantes
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
	 * Inspiré depuis http://rosettacode.org/wiki/Zig-zag_matrix
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
	
	
	/**
	 * Fonction qui sers a effectuer le traitement de parcours Zig-Zag afin de rassemble les zéros ensembles le plus possible
	 * @param QuantificationResult	Le résultat de la quantification
	 * @return	returnedZigZagArray	Le array
	 */
	public static int[][][][] reverseZigzagger(int[][][][] ZigZagArray){
// pas fonctionnel
		
		int valX = 0;
		int valY = 0;
		int nbBlock = ZigZagArray[0].length;
		int index = 0;
		int[][] tempZigZagArray = new int[nbBlock][nbBlock];
		int[][][][] returnedZigZagArray = new int[COLOR_SPACE_SIZE][nbBlock][nbBlock][BLOCK_SIZE*BLOCK_SIZE];
		
		for (int i = 0 ; i < COLOR_SPACE_SIZE; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					for (int y = 0; y < BLOCK_SIZE; y++) {
						for (int x = 0; x < BLOCK_SIZE ; x++) {
							valX = h * BLOCK_SIZE + x;
							valY = v * BLOCK_SIZE + y;
							
							tempZigZagArray[y][x] = ZigZagArray[i][v][h][index];
							index++;
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
	 * Inspiré depuis http://rosettacode.org/wiki/Zig-zag_matrix
	 */
	public static int[][] reverseZig_Zag(int[] array){
		//pas fonctionnel
		int size = BLOCK_SIZE;
		int[][] data = new int[8][8];
		int i = 1;
		int j = 1;
	
		for (int element = 0; element < 64; element++){
			data[i - 1][j-1] = array[element];
			//	data[element] = array[i - 1][j - 1];
	
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

	
	
}
