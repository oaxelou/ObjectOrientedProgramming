// package ce325.hw2;

public class YUVImage /*implements Image*/{
  protected YUVPixel [][] pixels; 

  private int width;
  private int height;

  public YUVImage(){
    pixels = null;
  }

  public YUVImage(int width, int height){
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
    pixels = new YUVPixel[RGBImg.pixels.length][RGBImg.pixels[0].length];

    for(int i = 0 ; i < RGBImg.pixels.length; i++){
      for(int j = 0; j < RGBImg.pixels[0].length; j++){
        pixels[i][j] = new YUVPixel(RGBImg.pixels[i][j]);
      }
    }
  }

  /*public YUVImage(java.io.File file){}*/
  

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

  public String toString(){

    StringBuffer StrBuff = new StringBuffer();

    StrBuff.append("YUV3\n");
    StrBuff.append(width + " " + height + "\n");

    for(int i = 0; i < height; i++){
      for(int j = 0; j < height; j++){
        StrBuff.append( pixels[i][j].toString() );
      }
    }

    return(StrBuff.toString());

  }

}

  