// package ce325.hw2;

import java.io.*;

public class YUVImage implements Image{
  protected YUVPixel [][] pixels;

  private int width;
  private int height;

  public YUVImage(){
    pixels = null;
  }

  public YUVImage(int width, int height){

    this.height = height;
    this.width  = width;

    pixels = new YUVPixel[height][width];
    for(int i = 0 ; i < height; i++){
      for(int j = 0; j < width; j++){
        pixels[i][j] = new YUVPixel((short) 16, (short) 128, (short) 128);
      }
    }
  }

  public YUVImage(YUVImage copyImg){

    height = copyImg.getHeight();
    width  = copyImg.getWidth();

    pixels = new YUVPixel[height][width];


    for(int i = 0 ; i < height; i++){
      for(int j = 0; j < width; j++){
        pixels[i][j] = new YUVPixel(copyImg.getPixelsArray()[i][j]);
      }
    }
  }


  public YUVImage(RGBImage RGBImg){

    height = RGBImg.pixels.length;
    width = RGBImg.pixels[0].length;

    pixels = new YUVPixel[height][width];

    for(int i = 0 ; i < height; i++){
      for(int j = 0; j < width; j++){
        pixels[i][j] = new YUVPixel(RGBImg.pixels[i][j]);
      }
    }
  }

  public YUVImage(java.io.File file) throws FileNotFoundException, UnsupportedFileFormatException{

  	BufferedReader inputStream;

  	int Y,U,V;

  	String transString;

    String[] Numbers;

  	try{
  		inputStream = new BufferedReader(new FileReader(file));

  		transString = inputStream.readLine();

  		if(transString == null || transString.compareTo("YUV3") != 0){
  			throw new UnsupportedFileFormatException();
  		}

  		if( ( transString = inputStream.readLine() ) == null){
  			throw new UnsupportedFileFormatException();
  		}

      Numbers = transString.split(" ");
  		width  = new Integer( Numbers[0] );
  		height = new Integer( Numbers[1] );

  		pixels = new YUVPixel[height][width];


  			for(int i = 0; i < height; i++){
  				for(int j =0 ; j < width; j++){

            transString = inputStream.readLine();

            Numbers = transString.split(" ");

  					Y = new Integer( Numbers[0] );
  					U = new Integer( Numbers[1] );
  					V = new Integer( Numbers[2] );

            //System.out.println("Height: " + height + " Width " + width + " i,j " + i + "," + j + " Y,U,V " + Y+ " " + U + " " + V);

  					pixels[i][j] = new YUVPixel( (short)Y, (short)U, (short)V );
  				}
  			}

  	}
  	catch(IOException ex) {
		System.out.println("IOException occured while reading from file ");
	}
  }


  public YUVPixel [][] getPixelsArray(){
    return pixels;
  }

  public void printArrayLength(){
    System.out.print(pixels.length + " ");
    System.out.println(pixels[0].length);
  }

  public int getHeight(){
    return height;
  }

  public int getWidth(){
    return width;
  }

  public void grayscale(){
    for(int i = 0; i < pixels.length; i++){
      for(int j = 0; j < pixels[0].length; j++){
        pixels[i][j].setU((short)0);
        pixels[i][j].setV((short)0);
      }
    }
  }

  public void doublesize(){
    YUVPixel [][]newPixels = new YUVPixel[pixels.length * 2][pixels[0].length * 2];

    for(int i = 0; i < pixels.length; i++){
      for(int j = 0; j < pixels[0].length; j++){
        newPixels[2*i][2*j] = new YUVPixel(pixels[i][j]);
        newPixels[2*i][2*j + 1] = new YUVPixel(pixels[i][j]);
        newPixels[2*i + 1][2*j] = new YUVPixel(pixels[i][j]);
        newPixels[2*i + 1][2*j + 1] = new YUVPixel(pixels[i][j]);
        pixels[i][j] = null;
      }
    }
    pixels = newPixels;
  }

  public void halfsize(){
    YUVPixel [][]newPixels = new YUVPixel[pixels.length / 2][pixels[0].length / 2];
    short Y, U, V;

    for(int i = 0; i < newPixels.length; i++){
      for(int j = 0; j < newPixels[0].length; j++){
        Y = (short)((int)pixels[2 * i][2 * j].getY() + (int)pixels[2 * i][2 * j + 1].getY() + (int)pixels[2 * i + 1][2 * j].getY() + (int)pixels[2 * i][2 * j].getY());
        Y =  (short)((int)Y / 4);

        U = (short)((int)pixels[2 * i][2 * j].getU()+ (int)pixels[2 * i][2 * j + 1].getU() + (int)pixels[2 * i + 1][2 * j].getU() + (int)pixels[2 * i][2 * j].getU());
        U = (short)((int)U / 4);

        V = (short)((int)pixels[2 * i][2 * j].getU()+(int) pixels[2 * i][2 * j + 1].getU() + (int)pixels[2 * i + 1][2 * j].getU() + (int)pixels[2 * i][2 * j].getU());
        V = (short)((int)V / 4);

        newPixels[i][j] = new YUVPixel(Y, U, V);
      }
    }
    pixels = newPixels;
  }

  public void rotateClockwise(){
    YUVPixel [][]newPixels = new YUVPixel[pixels[0].length][pixels.length];

    for(int i = 0; i < pixels.length; i++){
      for(int j = 0; j < pixels[0].length; j++){
        newPixels[j][pixels.length - 1 - i] = pixels[i][j];
      }
    }
    pixels = newPixels;
  }

  public String toString(){

    StringBuffer StrBuff = new StringBuffer();

    StrBuff.append("YUV3\n");
    StrBuff.append(width + " " + height + "\n");

    for(int i = 0; i < height; i++){
      for(int j = 0; j < width; j++){
        StrBuff.append( pixels[i][j].toString() );
      }
    }

    return(StrBuff.toString());

  }

  public void toFile(java.io.File file){

  	try (FileWriter fWriter = new FileWriter(file, false)) {
      fWriter.write(toString());
    }catch(IOException ex){
      System.err.println("Something went wrong with FileWriter");
      System.exit(1);
    }
  }

  public void equalize(){
    // YUVImage equa
    YUVPixel [][]newPixels = new YUVPixel[pixels.length][pixels[0].length];
    Histogram hist = new Histogram(this);

    hist.equalize();
    for(int i = 0 ; i < pixels.length; i++){
      for(int j = 0; j < pixels[0].length; j++){
        newPixels[i][j] = new YUVPixel(hist.getEqualizedLuminocity((int)pixels[i][j].getY()), pixels[i][j].getU(), pixels[i][j].getV());
      }
    }
    pixels = newPixels;
  }

}
