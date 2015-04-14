package gti310.tp4.main;

import java.io.FileNotFoundException;
import gti310.tp4.utility.*;

/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author François Caron
 */

public class Main implements IConstants{

	/**
	 * The application's entry point.
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Squeeze Light Media Codec !");
		
		int facteurQualite = -1;
		/**
		 * 3 parametres:
		 * facteur qualité: 1-100
		 * fichier_a_encoder.ppm
		 * fichier_compresse.szl
		 */
		
		if((Integer.valueOf(args[0]) >= 0 ) && (Integer.valueOf(args[0]) <= 100)){
			facteurQualite = Integer.valueOf(args[0]);
		}

		if(args.length == 3){ // 3 param donc encodage  ->  encoder(facteurQualite, args[1], args[2]);
			System.out.println("OPERATION ENCODAGE ");
			
			// lecture du fichier -> array 3 dimension -> 1 dimension/couleurRGB -> [R,G,B][0-256][0-256]
			int[][][] originalImageArray = PPMReaderWriter.readPPMFile(args[1]);
			
			// faire la conversion des couleurs du array de RGB vers YCbCr
			float[][][] YCbCrImageArray = ColorConverter.RGBToYCbCrImageConversion(originalImageArray);
			
			float[][][] DCTResult = DCT.DCTConversion(YCbCrImageArray);
			
			// call quantification -> passer facteur qualite en param
			int[][][] QuantificationResult = Quantification.quantification(DCTResult, 90);
			
			// call zigzag passe les tableaux 8x8
			int[][][][] ZigZagResult = ZigZag.zigzagger(QuantificationResult);
			
			EntropyCoding.entropyCoding(ZigZagResult, args[2]);
			
		}
		else if(args.length == 2){ // 2 param donc decodage
			// decoder(args[0], args[1]);
			System.out.println("OPERATION DECODAGE ");
		/*	
			int[][][][] entropie = EntropyCoding.IEntropyCoding(args[1]);
			int[][][][] ZigZagArray = ZigZag.reverseZigzagger(entropie);
			
			// Fonction qui prend un array en 3 dimensions recoit un array en 4 dimensions, pas le temps de corriger malheureusement.
			float[][][] arrayQuantifie = Quantification.reverseQuantification(ZigZagArray, facteurQualite);

			float[][][] DCTArray = DCT.IDCTConversion(arrayQuantifie);
			int[][][] RGBImage = ColorConverter.YCbCrToRGBImageConversion(DCTArray);
			PPMReaderWriter.writePPMFile(args[2], RGBImage);
		*/	
			
		}
		else{ // pas le bon nombre de param
			System.out.println("ERREUR: MAUVAIS NOMBRE DE PARAMETRE");
			
		}
		
	}
}
