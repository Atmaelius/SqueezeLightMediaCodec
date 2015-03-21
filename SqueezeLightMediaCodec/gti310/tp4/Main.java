package gti310.tp4;

/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author François Caron
 */

public class Main {

	/*
	 * The entire application assumes that the blocks are 8x8 squares.
	 */
	public static final int BLOCK_SIZE = 8;
	
	/*
	 * The number of dimensions in the color spaces.
	 */
	public static final int COLOR_SPACE_SIZE = 3;
	
	/*
	 * The RGB color space. salut
	 */
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;
	
	/*
	 * The YUV color space.
	 */
	public static final int Y = 0;
	public static final int U = 1;
	public static final int V = 2;
	
	/**
	 * The application's entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Squeeze Light Media Codec !");
		
		int facteurQualite = -1;
		
		// donner des valeurs aux args etbypasser le set on run 
		args[0] = "50";
		args[1] = "/SqueezeLightMediaCodec/Media/monalisa.ppm";
		args[2] = "/SqueezeLightMediaCodec/Media/monalisa_result.ppm";
		
		/**
		 * 3 parametres:
		 * facteur qualité: 1-100
		 * fichier_a_encoder.ppm
		 * fichier_compresse.szl
		 */
		
		if((Integer.valueOf(args[0]) >= 0 ) && (Integer.valueOf(args[0]) <= 100)){
			facteurQualite = Integer.valueOf(args[0]);
		}

		if(args.length == 3){ // 3 param donc encodage
			//encoder(facteurQualite, args[1], args[2]);
			System.out.println(" OPERATION ENCODAGE ");
			
			// lecture du fichier -> array 3 dimension -> 1 dimension/couleurRGB
			int[][][] imagePPMArray = PPMReaderWriter.readPPMFile(args[1]);

			// faire la conversion des couleurs du array de RGB vers YCbCr
			
			
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
