// package ce325.hw2;

public class RGBPixel{
  private int color;

  static final short MAXVALUE = 255;
  static final short MINVALUE = 0;

  public RGBPixel(short red, short green, short blue){
    color = red;
    color = (color << 8) + green;
    color = (color << 8) + blue;
  }

  public RGBPixel(RGBPixel pixel){
    this(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
  }

  /*
  public RGBPixel(YUVPixel pixel){
    int c = pixel.getY() -  16;
    int d = pixel.getU() - 128;
    int e = pixel.getV() - 128;

    color =                clip((298 * c           + 409 * e + 128) >> 8);
    color = (color << 8) + clip((298 * c - 100 * d - 208 * e + 128) >> 8);
    color = (color << 8) + clip((298 * c + 516 * d           + 128) >> 8);
  }
  */

  // auxiliary function for constructor for YUVPixels
  public static int clip(int value){
    if(value > MAXVALUE){
      value = MAXVALUE;
    }
    else if(value < MINVALUE){
      value = MINVALUE;
    }
    return value;
  }

  public int getRGBValue(){
    return color;
  }

  public short getRed(){
    return (short)(color >> 16);
  }

  public short getGreen(){
    return (short)((color >> 8) & 255); // 1 byte
  }

  public short getBlue(){
    return (short)(color & 255); // 1 byte
  }

  public void setRed(short red){
    color = color & 65535; // 2 bytes: 65536 = 2^16
    color = color + ((int) red << 16);
  }

  public void setGreen(short green){
    color = color & (16711680 + 255); // ta bytes twn red kai blue
    color = color + ((int) green << 8);
  }

  public void setBlue(short blue){
    color = color & (16711680 + 65280); // ta bytes twn red kai green
    color = color + (int) blue;
  }

  // AYTH DEN TH THELEI!!!!!!!!!! GIA DEBBUGING THN EVALA!!!!!!!
  public void printValue(){
    System.out.println(color);
  }
}
