import java.io.*;

public class checkEqualization{
  public static void main(String[] args) {
    YUVImage y;
    // YUVImage img = new YUVImage(2, 2);
    //
    // img.pixels[0][0].setY((short)10);
    // img.pixels[0][0].setU((short)200);
    // img.pixels[0][0].setV((short)26);
    //
    // img.pixels[0][1].setY((short)154);
    // img.pixels[0][1].setU((short)230);
    // img.pixels[0][1].setV((short)80);
    //
    // img.pixels[1][0].setY((short)16);
    // img.pixels[1][0].setU((short)100);
    // img.pixels[1][0].setV((short)20);
    //
    // img.pixels[1][1].setY((short)1);
    // img.pixels[1][1].setU((short)24);
    // img.pixels[1][1].setV((short)217);

    if(args.length < 2){
      System.err.println("Not the right number of arguments!");
      System.exit(1);
    }
    try{
      PPMImage p = new PPMImage(new java.io.File(args[0]));
      System.out.println(" *********************** ");
      y = new YUVImage(p);

      // System.out.println("YUV picture:");
      // System.out.println(y.toString());

      System.out.println("\n*****************\n");

      // Histogram hist = new Histogram(y);
      // hist.toFile(new java.io.File(args[1]));
      // System.out.println(hist.toString());
      y.equalize();
      p = new PPMImage(y);
      // System.out.println("PPM picture:");
      p.toFile(new java.io.File(args[1]));

      // y.toFile(new java.io.File(args[1]));

    } catch(FileNotFoundException ex){
      System.err.println("File was not found");
      System.out.println("ERROR: "+ex.getMessage());
    } catch(UnsupportedFileFormatException ex){
      ex.printStackTrace();
      System.out.println("ERROR: "+ex.getMessage());
    } catch(SecurityException ex){
      System.err.println("You don't have the permissions to edit this file!");
      System.out.println("ERROR: "+ex.getMessage());
    }
    System.out.println("Exiting now...");

  }
}
