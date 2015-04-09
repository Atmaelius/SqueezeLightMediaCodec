package gti310.tp4.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class DataDisplay implements IConstants{

	
	public static void printToFile(String path, float[][][] array) throws FileNotFoundException{
		
		File file = new File(path);
		String endline = "\n";

		if (file.exists()) {
			file.delete();
		}
		try {
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
		
				for (int i = 0; i < array.length; i++) {
					 bw.write("Dimension:" + i + endline);
				for (int j = 0; j < array[0].length; j++) {
					 bw.write("Ligne:" + j + endline);
					for (int k = 0; k < array[0][0].length; k++) {
						bw.write(String.valueOf(array[i][j][k]+",   "));
					}
					bw.write(endline + "   ");
				}
				bw.write(" ------------------------------------------------------------------------------------------------------------------" + endline);
			}
			bw.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());		
			e.printStackTrace();
		}
	}
	

	public static void printToFile(String path, int[][][] array) throws FileNotFoundException{
		
		File file = new File(path);
		String endline = "\n";

		if (file.exists()) {
			file.delete();
		}
		try {
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
		
				for (int i = 0; i < array.length; i++) {
					 bw.write("Dimension:" + i + endline);
				for (int j = 0; j < array[0].length; j++) {
					 bw.write("Ligne:" + j + endline);
					for (int k = 0; k < array[0][0].length; k++) {
						bw.write(String.valueOf(array[i][j][k]+",   "));
					}
					bw.write(endline + "   ");
				}
				bw.write(" ------------------------------------------------------------------------------------------------------------------" + endline);
			}
			bw.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());		
			e.printStackTrace();
		}
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
		System.out.println("Block DCT #");
		for (int y = 0; y < BLOCK_SIZE; y++) {
			for (int x = 0; x < BLOCK_SIZE; x++) {
				System.out.printf("%3.2f\t",array[y][x]);
			}
			System.out.println();
		}
	}
	
	
	public static void showDCTBlock(float[][] array, int number){
		System.out.println("Block DCT #" + number);
		for (int y = 0; y < BLOCK_SIZE; y++) {
			for (int x = 0; x < BLOCK_SIZE; x++) {
				System.out.printf("%3.2f\t",array[y][x]);
			}
			System.out.println();
		}
	}
	
	
	public static void showDCTBlock(float[][] array, int xCoor, int yCoor, int number){
		System.out.println("Block DCT #" + number + "-> ("+ xCoor+","+ yCoor+")" );
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
