/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*   RGBImage class:
*   Represents an RGB formated Image in the program.
* 
*/

package ce325.hw2;

public class RGBImage implements Image{
  protected RGBPixel [][] pixels;

  public RGBImage(){
    pixels = null;
  }

  public RGBImage(int width, int height, int colordepth){
    pixels = new RGBPixel[height][width];
    for(int i = 0 ; i < pixels.length; i++){
      for(int j = 0; j < pixels[0].length; j++){
        pixels[i][j] = new RGBPixel((short) 0, (short) 0, (short) 0);
      }
    }
  }

  public RGBImage(RGBImage copyImg){
    pixels = new RGBPixel[copyImg.getPixelsArray().length][copyImg.getPixelsArray()[0].length];
    for(int i = 0 ; i < pixels.length; i++){
      for(int j = 0; j < pixels[0].length; j++){
        pixels[i][j] = new RGBPixel(copyImg.getPixelsArray()[i][j]);
      }
    }
  }

  public RGBImage(YUVImage YUVImg){
    pixels = new RGBPixel[YUVImg.pixels.length][YUVImg.pixels[0].length];

    for(int i = 0 ; i < YUVImg.pixels.length; i++){
      for(int j = 0; j < YUVImg.pixels[0].length; j++){
        pixels[i][j] = new RGBPixel(YUVImg.pixels[i][j]);
      }
    }
  }

  public RGBPixel [][] getPixelsArray(){
    return pixels;
  }

  public void printArrayLength(){
    System.out.println(pixels.length);
    System.out.println(pixels[0].length);
  }

  public void grayscale(){
    for(int i = 0; i < pixels.length; i++){
      for(int j = 0; j < pixels[0].length; j++){
        pixels[i][j].setRed((short)(0.3 * (int)pixels[i][j].getRed() +
                             0.59 * (int)pixels[i][j].getGreen() +
                             0.11 * (int)pixels[i][j].getBlue()));
        pixels[i][j].setGreen(pixels[i][j].getRed());
        pixels[i][j].setBlue(pixels[i][j].getRed());
      }
    }
  }

  public void doublesize(){
    RGBPixel [][]newPixels = new RGBPixel[pixels.length * 2][pixels[0].length * 2];

    for(int i = 0; i < pixels.length; i++){
      for(int j = 0; j < pixels[0].length; j++){
        newPixels[2*i][2*j] = new RGBPixel(pixels[i][j]);
        newPixels[2*i][2*j + 1] = new RGBPixel(pixels[i][j]);
        newPixels[2*i + 1][2*j] = new RGBPixel(pixels[i][j]);
        newPixels[2*i + 1][2*j + 1] = new RGBPixel(pixels[i][j]);
        pixels[i][j] = null;
      }
    }
    pixels = newPixels;
  }

  public void halfsize(){
    RGBPixel [][]newPixels = new RGBPixel[pixels.length / 2][pixels[0].length / 2];
    short red, green, blue;

    for(int i = 0; i < newPixels.length; i++){
      for(int j = 0; j < newPixels[0].length; j++){
        red = (short)((int)pixels[2 * i][2 * j].getRed() +
                      (int)pixels[2 * i][2 * j + 1].getRed() +
                      (int)pixels[2 * i + 1][2 * j].getRed() +
                      (int)pixels[2 * i][2 * j].getRed());
        red =  (short)((int)red / 4);

        green = (short)((int)pixels[2 * i][2 * j].getGreen()+
                        (int)pixels[2 * i][2 * j + 1].getGreen() +
                        (int)pixels[2 * i + 1][2 * j].getGreen() +
                        (int)pixels[2 * i][2 * j].getGreen());
        green = (short)((int)green / 4);

        blue = (short)((int)pixels[2 * i][2 * j].getBlue() +
                       (int) pixels[2 * i][2 * j + 1].getBlue() +
                       (int)pixels[2 * i + 1][2 * j].getBlue() +
                       (int)pixels[2 * i][2 * j].getBlue());
        blue = (short)((int)blue / 4);

        newPixels[i][j] = new RGBPixel(red, green, blue);
      }
    }
    pixels = newPixels;
  }

  public void rotateClockwise(){
    RGBPixel [][]newPixels = new RGBPixel[pixels[0].length][pixels.length];

    for(int i = 0; i < pixels.length; i++){
      for(int j = 0; j < pixels[0].length; j++){
        newPixels[j][pixels.length - 1 - i] = pixels[i][j];
      }
    }
    pixels = newPixels;
  }
}
