public class SudokuAction{

	private int i;
	private int j;
	private int k;
	private int m;
	private String preVal;
	private String nextVal;

	public SudokuAction(int i, int j, int k, int m, String preVal, String nextVal){
		this.i = i;
		this.j = j;
		this.k = k;
		this.m = m;
		this.preVal = preVal;
		this.nextVal = nextVal;
	}

	public int [] getDimensions(){
		int [] dimensions = new int[4];
		dimensions[0] = i;
		dimensions[1] = j;
		dimensions[2] = k;
		dimensions[3] = m;

		return dimensions;
	}

	public String getNextValue(){
		return nextVal;
	}

	public String getPreValue(){
		return preVal;
	}

}