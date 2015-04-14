package gti310.tp4.tests;

import gti310.tp4.main.Entropy;
import gti310.tp4.main.SZLReaderWriter;
import gti310.tp4.utility.DataDisplay;
import gti310.tp4.utility.EntropyCoding;

import java.util.ArrayList;

public class EntropyTester {

	public static void main(String[] args) {
		
	//private static int[][] IACRLE(ArrayList<int[]> list){

	ArrayList<int[]> test = new ArrayList<int[]>();
	
	int[] couple1 = new int[]{0,1};
	int[] couple2 = new int[]{0,2};
	int[] couple3 = new int[]{0,1};
	int[] couple4 = new int[]{7,-1};
	int[] couple5 = new int[]{4,1};
	int[] couple6 = new int[]{47,0};
	
	
	test.add(couple1);
	test.add(couple2);
	test.add(couple3);
	test.add(couple4);
	test.add(couple5);
	test.add(couple6);

	
	//int[][] result = EntropyCoding.IACRLE(test);
		
	int[] infoFichier = SZLReaderWriter.readSZLFile("Media/lena_result.szl");

	int height = infoFichier[0];
	int width = infoFichier[1];
	int espaceCouleur = infoFichier[2];
	int facteurQualite = infoFichier[3];
	
	int nbBlockHauteur =(height/8);
	int nbBlockLargeur = (width/8);
	int tailleMatriceZigZag = nbBlockHauteur*nbBlockLargeur;
	
	int[][][][] ZigZagMatrix = new int[espaceCouleur][nbBlockHauteur][nbBlockLargeur][tailleMatriceZigZag];
	
	EntropyCoding.IEntropyCoding("Media/lena_result.szl");
	
	for (int i = 0; i < infoFichier.length; i++) {
		System.out.println(infoFichier[i]);
		
	}
//	DataDisplay.show8x8List(result);
	
	
	}
}
