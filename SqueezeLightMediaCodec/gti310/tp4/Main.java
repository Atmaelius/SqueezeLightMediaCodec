package gti310.tp4;

import gti310.tp4.utility.ImageManipulator;

/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author François Caron
 */

public class Main {

	/**
	 * The application's entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Squeeze Light Media Codec !");
		
		int facteurQualite = -1;
		
		// donner des valeurs aux args etbypasser le set on run 
		args[0] = "50";
		args[1] = "Media/monalisa.ppm";
		args[2] = "Media/monalisa_result.ppm";
		
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
			
			
	//		PixelManipulation.show8x8IntMatrix(originalImageArray);
			
			// faire la conversion des couleurs du array de RGB vers YCbCr
			float[][][] YCbCrImageArray = ImageManipulator.RGBToYCbCrImageConversion(originalImageArray);
			
		//	ImageManipulator.show8x8FloatMatrix(YCbCrImageArray);
			float[][][] DCTResult = ImageManipulator.DCTConversion(YCbCrImageArray);
			
			
			// call quantification -> passer facteur qualite en param
			int[][][] QuantificationResult = ImageManipulator.quantification(DCTResult, facteurQualite);
			

//			ImageManipulator.show8x8IntMatrix(QuantificationResult);
		
			int[] data = ImageManipulator.Zig_Zag(QuantificationResult[0]);
			
			for (int i = 0; i < data.length; i++) {
				System.out.print(data[i]+", ");
			}
			
			
		}
		else if(args.length == 2){ // 2 param donc decodage
			// decoder(args[0], args[1]);
			System.out.println("OPERATION DECODAGE ");
			
		}
		else{ // pas le bon nombre de param
			System.out.println("ERREUR: MAUVAIS NOMBRE DE PARAMETRE");
			
		}
		
	}
}
