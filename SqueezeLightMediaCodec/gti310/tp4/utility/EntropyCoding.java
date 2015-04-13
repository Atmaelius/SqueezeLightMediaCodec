package gti310.tp4.utility;

import gti310.tp4.main.Entropy;
import gti310.tp4.main.SZLReaderWriter;

import java.util.ArrayList;

/**
 * @author eric
 */
public class EntropyCoding implements IConstants{


	public static void entropyCoding(int[][][][] ZigZagArray, String fileName){
		
		int[][][][] entropyArray = DCDPCM(ZigZagArray);
		int nbBlock = 32;
		
		ArrayList<int[]> RLEValues = new ArrayList<int[]>();
		
		for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> 32
			for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> 32
				for (int i = 0; i < COLOR_SPACE_SIZE; i++) { // boucler sur les couleurs
					RLEValues = ACRLE(ZigZagArray[i][v][h]);
					
					// écrire les valeurs
					Entropy.writeDC(entropyArray[i][v][h][0]);
					
					for (int j = 0; j < RLEValues.size(); j++) {
						Entropy.writeAC(RLEValues.get(j)[0], RLEValues.get(j)[1]);
					}
				}
			}
		}
		
		SZLReaderWriter.writeSZLFile(fileName, 256,256,50);
		System.out.println("IMAGE ECRITE");
	}
	
	
	/**
	 * Fonction qui crée le array DC a partir du array resultant du ZigZag
	 * @param ZigZagArray
	 * @return returnedDCArray	Le array issu du traitement DC
	 */
	private static int[][][][] DCDPCM(int[][][][] ZigZagArray){

		int nbBlock = ZigZagArray[0].length;
		for (int i = 0 ; i < ZigZagArray.length; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				
				int actuel = -0;
				int precedent = 0;
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					precedent = actuel;
					actuel = ZigZagArray[i][v][h][0];
					ZigZagArray[i][v][h][0] = actuel-precedent;
				}
			}
		}
		return ZigZagArray;
	}

	
	/**
	 * Fonction qui crée un array AC a partir du array résultant du ZigZag
	 * @param ZigZagArray
	 * @return
	 */
	private static ArrayList<int[]> ACRLE(int[] block){

		int valeur = 0;
		int nbZeros = 0;
		int[] couple = new int[2];
		ArrayList<int[]> ar = new ArrayList<int[]>();
		
		ar.clear();
		valeur = 0;
		
		for (int l = 1; l < BLOCK_SIZE * BLOCK_SIZE-1; l++) { // boucler sur la longueur -> 64
			valeur = block[l];
			if(valeur != 0){
				// il faut absolument ré-initialiser le array couple car il est passé en référence au arraylist
				// et ainsi a chaque ajout le contenu complet de l'arraylist change pour le nouveau couple
				// http://www.coderanch.com/t/580391/java/java/Vector-object-overwritten-adding-element
				couple = new int[2];
				couple[0] = nbZeros;
				couple[1] = valeur;
		
				ar.add(couple);
				nbZeros = 0;
			}
			else{
				nbZeros++;
			}
			
			if(l == ((BLOCK_SIZE * BLOCK_SIZE)-2)){
				couple = new int[2];
				couple[0] = nbZeros;
				couple[1] = 0;
		
				ar.add(couple);
				nbZeros = 0;
			}
		}
		return ar;
	}
	
}
