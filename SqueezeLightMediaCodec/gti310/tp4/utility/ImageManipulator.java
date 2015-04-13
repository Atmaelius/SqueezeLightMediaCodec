package gti310.tp4.utility;

import gti310.tp4.main.PPMReaderWriter;

/**
 * @author eric
 */

public class ImageManipulator implements IConstants{

	
//**     WRITER     ** \\					

	public static void writeRGBFile(String path, int[][][] intArray){
		PPMReaderWriter.writePPMFile(path, intArray);
	}
	
	/*
	 * Function to write a file
	 */
	public static void writeYCbCrFile(String path, float[][][] floatArray){
		PPMReaderWriter.writePPMFile(path, ColorConverter.YCbCrToRGBImageConversion(floatArray));
	}


}

