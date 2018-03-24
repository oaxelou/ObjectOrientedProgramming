
// VOITHIKH KLASSH GIA ELEGXO TOU RGBPixel !

public class testPixel{
  public static void main(String[] args) {
    RGBPixel pix1 = new RGBPixel((short)164, (short)200, (short)249);
    RGBPixel pix = new RGBPixel(pix1);

    System.out.println(pix.getRed());
    System.out.println(pix.getGreen());
    System.out.println("old blue: " +pix.getBlue());
    System.out.println("****");
    pix.setBlue((short) 29);
    System.out.println(pix.getRed());
    System.out.println(pix.getGreen());
    System.out.println("new blue: " +pix.getBlue());

    // pix.printValue();
  }
}
