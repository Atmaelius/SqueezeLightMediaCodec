package Recyclage;

import gti310.tp4.PPMReaderWriter;

public class Dump {
/*
	
	
	System.out.println("Squeeze Light Media Codec !");
	
	int facteurQualite = -1;
	
	// donner des valeurs aux args etbypasser le set on run 
	args[0] = "50";
	args[1] = "Media/monalisa.ppm";
	args[2] = "Media/monalisa_result.ppm";
	
*/	/**
	 * 3 parametres:
	 * facteur qualité: 1-100
	 * fichier_a_encoder.ppm
	 * fichier_compresse.szl
	 */
/*	
	if((Integer.valueOf(args[0]) >= 0 ) && (Integer.valueOf(args[0]) <= 100)){
		facteurQualite = Integer.valueOf(args[0]);
	}

	if(args.length == 3){ // 3 param donc encodage
		//encoder(facteurQualite, args[1], args[2]);
		System.out.println(" OPERATION ENCODAGE ");
		
		// lecture du fichier -> array 3 dimension -> 1 dimension/couleurRGB
		int[][][] imagePPMArray = PPMReaderWriter.readPPMFile(args[1]);
		int[][][] testImageResult = new int[imagePPMArray.length][imagePPMArray[0].length][imagePPMArray[0][0].length];
		
		int z = 0;
		
		
		// ICI ON VERIFIE LENCODAGE DES COULEURS EN TERMES DE FORME DANS LE ARRAY
		 * 
		for (int i = 0; i < imagePPMArray.length; i++) {
			for (int j = 0; j < imagePPMArray[i].length; j++) {
				for (int k = 0; k < imagePPMArray[i][j].length; k++) {
			//		System.out.print(imagePPMArray[i][j][k]+"-");
					testImageResult[i][j][k] = imagePPMArray[i][j][k]; 
					if(imagePPMArray[i].length - j > 8){
						j+= 4;
					}
					
					
					if(z == 2){
						z = -1;
						System.out.println();
					}
					z++;
				}
			}
		}
		// faire la conversion des couleurs du array de RGB vers YCbCr
		
		PPMReaderWriter.writePPMFile(args[2], testImageResult);
		
	}
	else if(args.length == 2){ // 2 param donc decodage
		// decoder(args[0], args[1]);
		System.out.println("OPERATION DECODAGE ");
	}
	else{ // pas le bon nombre de param
		System.out.println("ERREUR: MAUVAIS NOMBRE DE PARAMETRE");
	}

	
*/

	
}
