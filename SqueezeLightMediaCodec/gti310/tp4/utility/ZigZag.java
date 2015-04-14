package gti310.tp4.utility;

/**
 * Classe responsable de l'opération de tri par ZigZag
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
	 * @param array le bloc 8x8 passé en parametre
	 * @return	data	la représentation du array en paramêtre retourné en 1 dimension
	 * Inspiré depuis http://rosettacode.org/wiki/Zig-zag_matrix
	 */
	public static int[] Zig_Zag(int[][] array){
		int size = BLOCK_SIZE;
		int[] data = new int[BLOCK_SIZE*BLOCK_SIZE];
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
	 * Fonction qui sers a effectuer le traitement de parcours Zig-Zag inverse
	 *  afin de rassembler les zéros ensembles le plus possible
	 * @param QuantificationResult	Le résultat de la quantification
	 * @return	returnedZigZagArray	Le array résultant du zigzag inverse
	 */
	public static int[][][][] reverseZigzagger(int[][][][] ZigZagArray){
		
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
	 * @param data	Le tableau de données en 1 dimension
	 * @return array le tableau de données représenté en 2 dimensions
	 * Fonction statique car manque de temps pour faire marcher la version dynaimque
	 */
	public static int[][] reverseZig_Zag(int[] data){
		int size = BLOCK_SIZE;
		int[][]  array= new int[size][size];
		
		array[0][0] = data[0];
		array[0][1] = data[1];
		array[1][0] = data[2];
		array[2][0] = data[3];
		array[1][1] = data[4];
		array[0][2] = data[5];
		array[0][3] = data[6];
		array[1][2] = data[7];
		array[2][1] = data[8];
		array[3][0] = data[9];
		array[4][0] = data[10];
		array[3][1] = data[11];
		array[2][2] = data[12];
		array[1][3] = data[13];
		array[0][4] = data[14];
		array[0][5] = data[15];
		array[1][4] = data[16];
		array[2][3] = data[17];
		array[3][2] = data[18];
		array[4][1] = data[19];
		array[5][0] = data[20];
		array[6][0] = data[21];
		array[5][1] = data[22];
		array[4][2] = data[23];
		array[3][3] = data[24];
		array[2][4] = data[25];
		array[1][5] = data[26];
		array[0][6] = data[27];
		array[0][7] = data[28];
		array[1][6] = data[29];
		array[2][5] = data[30];
		array[3][4] = data[31];
		array[4][3] = data[32];
		array[5][2] = data[33];
		array[6][1] = data[34];
		array[7][0] = data[35];
		array[7][1] = data[36];
		array[6][2] = data[37];
		array[5][3] = data[38];
		array[4][4] = data[39];
		array[3][5] = data[40];
		array[2][6] = data[41];
		array[1][7] = data[42];
		array[2][7] = data[43];
		array[3][6] = data[44];
		array[4][5] = data[45];
		array[5][4] = data[46];
		array[6][3] = data[47];
		array[7][2] = data[48];
		array[7][3] = data[49];
		array[6][4] = data[50];
		array[5][5] = data[51];
		array[4][6] = data[52];
		array[3][7] = data[53];
		array[4][7] = data[54];
		array[5][6] = data[55];
		array[6][5] = data[56];
		array[7][4] = data[57];
		array[7][5] = data[58];
		array[6][6] = data[59];
		array[5][7] = data[60];
		array[6][7] = data[61];
		array[7][6] = data[62];
		array[7][7] = data[63];
		
		return array;
	}

	
	/*
	 /**
	 * Traverser un tableau 8x8 et retourner les valeurs sous forme de array 1 dimension
	 * @param array
	 * @return
	 * Inspiré depuis http://rosettacode.org/wiki/Zig-zag_matrix
	 * Tentative de zigzag inverse dynamique
	 */
	/*
	public static int[][] iZig_Zag(int[] arrayZigzager){
		int size = BLOCK_SIZE;
		int[][] data = new int[size][size];		
		
		int element = 1;
	
		for (int i = 1; i < size; i++){
			for (int j = 1; j < size; j++){
				 data[i - 1][j - 1] = arrayZigzager[element];	
				element++;
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
		}
		return data;
	}
	*/
	
	
}
