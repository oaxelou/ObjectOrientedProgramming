// package ce325.hw2;

// MENEI MONO O CONSTRUCTOR GIA TA YUVPixels !

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

  // public RGBPixel(YUVPixel pixel){
  //   short c,d,e, red, green, blue;
  //   short auxiliary = 1;
  //
  //   c = y - 16 * auxiliary;
  //   d = u - 128;
  //   e = v - 128;
  //
  //   red   = clip((298 * c           + 409 * e + 128) >> 8);
  //   green = clip((298 * c - 100 * d - 208 * e + 128) >> 8);
  //   blue  = clip((298 * c + 516 * d           + 128) >> 8);
  //
  //  this(red, green, blue);
  // }

  // // auxiliary function for constructor for YUVPixels
  // public static short clip(short value){
  //   if(value > MAXVALUE){
  //     value = MAXVALUE;
  //   }
  //   else if(value < MINVALUE){
  //     value = MINVALUE;
  //   }
  //   return value;
  // }

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
