package gti310.tp4.utility;

public class ColorConverter {

	
	// FORMAT POUR LES COULEURS: [Pos X][Pos Y][RGB] -> {int x} {int y} {int R, int G, int B}
	// {{{R,G,B},{R,G,B}},{{R,G,B},{R,G,B}}} -> image 4x4 avec 4 couleurs différentes
	
	
	 //http://stackoverflow.com/questions/1067073/initialising-a-multidimensional-array-in-java
	
	 public static int[][][] RGBToYCbCr(int redValue, int greenValue, int blueValue){
		 int[][][] YCbCrResult = {{{1}},{{2}},{{3}}};

		 
		 return YCbCrResult;
	 }
	 
	 
	 
	 public static int[][][] YCbCrToRGB(int YValue, int CbValue, int CrValue){
		 int[][][] RGBResult = {{{1}},{{2}},{{3}}};

	 
		 return RGBResult;
	 }
}
