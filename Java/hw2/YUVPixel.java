// package ce325.hw2;

public class YUVPixel{

  private short Y;
  private short U;
  private short V;


  public YUVPixel(short Y, short U, short V){
    this.Y = Y;
    this.U = U;
    this.V = V;
  }

  public YUVPixel(YUVPixel pixel){
    this(pixel.getY(), pixel.getU(), pixel.getV());
  }

  public YUVPixel(RGBPixel pixel){

    Y = ( (  66 * pixel.getRed() + 129 * pixel.getGreen() +  25 * pixel.getBlue() + 128) >> 8) +  16;
    U = ( ( -38 * pixel.getRed() -  74 * pixel.getGreen() + 112 * pixel.getBlue() + 128) >> 8) + 128;
    V = ( ( 112 * pixel.getRed() -  94 * pixel.getGreen() -  18 * pixel.getBlue() + 128) >> 8) + 128;

  }


  public short getY(){
    return Y;
  }

  public short getU(){
    return U;
  }

  public short getV(){
    return V;
  }

  public void setY(short Y){
    this.Y = Y;
  }

  public void setU(short U){
    this.U = U;
  }

  public void setV(short V){
    this.V = V;
  }

  
}