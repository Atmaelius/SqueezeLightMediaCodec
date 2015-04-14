package gti310.tp4.utility;
/**
 * Interface contenant des constantes utiles au déroulement du programme
 * @author eric
 */

public interface IConstants {


	public static final String DISPLAYPATH= "./main/test.txt";
	public static final String DISPLAYPATH2 = "./main/test2.txt";

	// The entire application assumes that the blocks are 8x8 squares.
	public static final int BLOCK_SIZE = 8;

	// The number of dimensions in the color spaces.
	public static final int COLOR_SPACE_SIZE = 3;

	// The RGB color space.
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;

	// The YUV color space.
	public static final int Y = 0;
	public static final int Cb = 1;
	public static final int Cr = 2;


	// celles de l'énoncé
	public static final float[][] QuantificationQyTable = new float[][]{
		{16, 40, 40, 40, 40, 40, 51, 61},
		{40, 40, 40, 40, 40, 58, 60, 55},
		{40, 40, 40, 40, 40, 57, 69, 56},
		{40, 40, 40, 40, 51, 87, 80, 62},
		{40, 40, 40, 56, 68, 109, 103, 77},
		{40, 40, 55, 64, 81, 104, 113, 92},
		{49, 64, 78, 87, 103, 121, 120, 101},
		{72, 92, 95, 98, 112, 100, 103, 95}
	};

	// celles de l'énoncé
	public static final float[][] QuantificationQcbQcrTable = new float[][]{
		{17,40,40,95,95,95,95,95},
		{40,40,40,95,95,95,95,95},
		{40,40,40,95,95,95,95,95},
		{40,40,95,95,95,95,95,95},
		{95,95,95,95,95,95,95,95},
		{95,95,95,95,95,95,95,95},
		{95,95,95,95,95,95,95,95},
		{95,95,95,95,95,95,95,95}
	};



	/*  --- VALEURS DE TEST ---  */

	public static final float[][][] TestArrayForDCT = new float[][][]{
		{
			{200,202,189,188,189,175,175,175},
			{200,203,198,188,189,182,178,175},
			{203,200,200,195,200,187,185,175},
			{200,200,200,200,197,187,187,187},
			{200,205,200,200,195,188,187,175},
			{200,200,200,200,200,190,187,175},
			{205,200,199,200,191,187,187,175},
			{210,200,200,200,188,185,187,186}
		}
	};


	public static final float[][][] TestArrayResultFromFloatDCT = new float[][][]{
		{
			{1539,65,-12,4,1,2,-8,5},
			{-16,3,2,0,0,-11,-2,3},
			{-12,6,11,-1,3,0,1,-2},
			{-8,3,-4,2,-2,-3,-5,-2},
			{0,-2,7,-5,4,0,-1,-4},
			{0,-3,-1,0,4,1,-1,0},
			{3,-2,-3,3,3,-1,-1,3},
			{-2,5,-2,4,-2,2,-3,0}
		}
	};

	
	public static final int[][][] TestArrayResultFromIntDCT = new int[][][]{
		{
			{1539,65,-12,4,1,2,-8,5},
			{-16,3,2,0,0,-11,-2,3},
			{-12,6,11,-1,3,0,1,-2},
			{-8,3,-4,2,-2,-3,-5,-2},
			{0,-2,7,-5,4,0,-1,-4},
			{0,-3,-1,0,4,1,-1,0},
			{3,-2,-3,3,3,-1,-1,3},
			{-2,5,-2,4,-2,2,-3,0}
		}
	};


	// Celles du ppt
	public static final int[][][] TestQuantificationQyTable = new int[][][]{
		{
			{16, 11, 10, 16, 24, 40, 51, 61},
			{12, 12, 14, 19, 26, 58, 60, 55},
			{14, 13, 16, 24, 40, 57, 69, 56},
			{14, 17, 22, 29, 51, 87, 80, 62},
			{18, 22, 37, 56, 68, 109, 103, 77},
			{24, 35, 55, 64, 81, 104, 113, 92},
			{49, 64, 78, 87, 103, 121, 120, 101},
			{72, 92, 95, 98, 112, 100, 103, 99}
		}
	};


	// Celle du ppt
	public static final float[][] TestQuantificationQcbQcrTable = new float[][]{
		{17,18,24,47,99,99,99,99},
		{18,21,26,66,99,99,99,99},
		{24,26,56,99,99,99,99,99},
		{47,66,99,99,99,99,99,99},
		{99,99,99,99,99,99,99,99},
		{99,99,99,99,99,99,99,99},
		{99,99,99,99,99,99,99,99},
		{99,99,99,99,99,99,99,99}
	};

	
	// celle du ppt
	public static final float[][][] TestArrayResultFromFloatQuantification = new float[][][]{
		{
			{96,6,-1,0,0,0,0,0},
			{-1,0,0,0,0,0,0,0},
			{-1,0,1,0,0,0,0,0},
			{-1,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0}
		}
	};

	
	// celle du ppt
	public static final int[][][] TestArrayResultFromIntQuantification = new int[][][]{
		{
			{96,6,-1,0,0,0,0,0},
			{-1,0,0,0,0,0,0,0},
			{-1,0,1,0,0,0,0,0},
			{-1,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0}
		}
	};

}
