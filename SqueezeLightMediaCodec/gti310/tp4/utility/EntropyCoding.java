package gti310.tp4.utility;

import gti310.tp4.main.Entropy;
import gti310.tp4.main.SZLReaderWriter;

import java.io.FileNotFoundException;
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
	
	
	public static int[][][][] IEntropyCoding(String filename){
		
		// IL FAUT LIRE LES VALEURS DANS LE MEME ORDRE QUON LES ÉCRIS !
		System.out.println("IMAGE LUE");
		int[] infoFichier = SZLReaderWriter.readSZLFile(filename);
		int height = infoFichier[0];
		int width = infoFichier[1];
		int espaceCouleur = infoFichier[2];
		int facteurQualite = infoFichier[3];
		
		int nbBlockHauteur =(height/8);
		int nbBlockLargeur = (width/8);
		int tailleMatriceZigZag = nbBlockHauteur+nbBlockLargeur;
		
		int compteur = 1; // permet de remplirle zigzagmatrix a partir de l'index 1 pour faire un tableau de 64
		int[][][][] ZigZagMatrix = new int[espaceCouleur][nbBlockHauteur][nbBlockLargeur][tailleMatriceZigZag];

		int DCValue = 0;

		
		for (int a = 0; a < espaceCouleur; a++) {
			for (int b = 0; b < nbBlockHauteur; b++) {
				for (int c = 0; c < nbBlockLargeur; c++) {
						
					DCValue = Entropy.readDC(); // 1 valeur

					if(DCValue != 0xffffffff){
						ZigZagMatrix[a][b][c][0] = DCValue;
					}
				}
			}
		}
					
		ZigZagMatrix = IDCDPCM(ZigZagMatrix);

		
		for (int i = 0; i < espaceCouleur; i++) {
			for (int j = 0; j < nbBlockHauteur; j++) {
				for (int k = 0; k < nbBlockLargeur; k++) {

					ArrayList<int[]> ar = new ArrayList<int[]>();
					
					int[] ACPaire; 
					int[][] resultat;
					boolean go = true;
					
					while (go) {
						ACPaire = Entropy.readAC();
						
						if(ACPaire[1] != 0){
							ar.add(ACPaire);
						}
						else{
						//	ar.add(ACPaire); // on ajoute le dernier quand il vaut zero
						//	resultat = IACRLE(ar); // résultat c'est l'équivalent d'un bloc
							
							resultat = IACRLE(ar);
							for (int l = 0; l < resultat.length; l++) {
								for (int m = 0; m < resultat.length; m++) {
									if(compteur != 64){
										ZigZagMatrix[i][j][k][compteur] = resultat[l][m] ; // résultat c'est l'équivalent d'un bloc
										compteur++;
									}
								}
							}
							
							go=false;
							
							
						}
					}
					
					System.out.println("HELL-O");
					
				}
			}
		}
		try {
			DataDisplay.printToFile(DISPLAYPATH, ZigZagMatrix[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ZigZagMatrix;

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
	 * Fonction inverse du DCDPCM qui maarche !
	 * @param ZigZagArray
	 * @return returnedDCArray	Le array issu du traitement DC
	 */
	private static int[][][][] IDCDPCM(int[][][][] ZigZagArray){

		int nbBlock = ZigZagArray[0].length;
		for (int i = 0 ; i < ZigZagArray.length; i++) { // boucler a travers les composantes
			for (int v = 0; v < nbBlock; v++) { // boucler a travers les blocs verticaux identifiés -> Y
				
				int actuel = -0;
				int precedent = 0;
				for (int h = 0; h < nbBlock; h++) { // boucler a travers les blocs horizontaux -> X
					precedent = actuel;
					actuel = ZigZagArray[i][v][h][0];
					// ZigZagArray[i][v][h][0] = actuel+precedent;
					ZigZagArray[i][v][h][0] = actuel+precedent;
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
	
	

	/**
	 * Fonction inverse au codage RLE des AC
	 * @param Arraylist, liste des couples contenus
	 * @return block, 8x8 representant les valeurs 
	 */
	public static int[][] IACRLE(ArrayList<int[]> list){

		int[][] block = new int[BLOCK_SIZE][BLOCK_SIZE];

		int valeur = 0;
		int nbZeros = 0;
		
		int x = 1;
		int y = 0;
		
		for (int i = 0; i < list.size(); i++) { // boucler sur  le nombre de couples
			nbZeros = list.get(i)[0];
			valeur = list.get(i)[1];
			
				if(x > 7){// ici si x vaut 7 on doit descendre dans la matrice
					y++;
					x=0;			
				}
				if(y > 8){
					return block;
				}
				
				if(nbZeros == 0){
					block[y][x] = valeur;
					x++;
				}
				else{
					for (int k = 0; k < nbZeros; k++) {
						x++;

						if(x == 8){ // si x vaut 8 on doit descendre dans la matrice
							x=0;			
							y++;
						}
						if(y==8){
							return block;
						}
						block[y][x] = 0;
					
					}
					block[y][x] = valeur;
					x++;

				}
			}
		return block;
	}
	
	public static int[][] IACRLE(int[][] list){
		int[][] block = new int[BLOCK_SIZE][BLOCK_SIZE];

		int valeur = 0;
		int nbZeros = 0;
		
		int x = 1;
		int y = 0;
		
		for (int i = 0; i < list.length; i++) { // boucler sur  le nombre de couples
			nbZeros = list[i][0];
			valeur = list[i][1];
			
				if(x == 7){// ici si x vaut 7 on doit descendre dans la matrice
					y++;
					x=0;			
				}
			
				if(nbZeros == 0){
					block[y][x] = valeur;
					x++;
				}
				else{
					for (int k = 0; k < nbZeros; k++) {
						x++;

						if(x == 8){ // si x vaut 8 on doit descendre dans la matrice
							x=0;			
							y++;
						}
						if(y==8){
							return block;
						}
						block[y][x] = 0;
					
					}
					block[y][x] = valeur;
					x++;

				}
			}
		return block;
	}
		
	
	
}
