// package ce325.hw2;

public class YUVImage /*implements Image*/{
  protected YUVPixel [][] pixels; 

  public YUVImage(){
    pixels = null;
  }

  public YUVImage(int width, int height){
    pixels = new YUVPixel[height][width];
    for(int i = 0 ; i < pixels.length; i++){
      for(int j = 0; j < pixels[0].length; j++){
        pixels[i][j] = new YUVPixel((short) 16, (short) 128, (short) 128);
      }
    }
  }

  public YUVImage(YUVImage copyImg){
    pixels = new YUVPixel[copyImg.getPixelsArray().length][copyImg.getPixelsArray()[0].length];
    for(int i = 0 ; i < pixels.length; i++){
      for(int j = 0; j < pixels[0].length; j++){
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
}
