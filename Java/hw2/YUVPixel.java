package ce325.hw2;

public class YUVPixel{

  private int Y;
  private int U;
  private int V;


  public YUVPixel(short Y, short U, short V){
    this.Y = Y;
    this.U = U;
    this.V = V;
  }

  public YUVPixel(YUVPixel pixel){
    this(pixel.getY(), pixel.getU(), pixel.getV());
  }

  public YUVPixel(RGBPixel pixel){

    Y = (short)(( (  66 * pixel.getRed() + 129 * pixel.getGreen() +  25 * pixel.getBlue() + 128) >> 8) +  16);
    U = (short)(( ( -38 * pixel.getRed() -  74 * pixel.getGreen() + 112 * pixel.getBlue() + 128) >> 8) + 128);
    V = (short)(( ( 112 * pixel.getRed() -  94 * pixel.getGreen() -  18 * pixel.getBlue() + 128) >> 8) + 128);

  }


  public short getY(){
    return (short)Y;
  }

  public short getU(){
    return (short)U;
  }

  public short getV(){
    return (short)V;
  }

  public void setY(short Y){
    this.Y = (int)Y;
  }

  public void setU(short U){
    this.U = (int)U;
  }

  public void setV(short V){
    this.V = (int)V;
  }

  public String toString(){
    return(Y + " " + U + " " + V + "\n");
  }


}
