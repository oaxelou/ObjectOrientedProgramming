// package ce325.hw2;

import java.util.*;
import java.io.*;

public class PPMImage extends RGBImage{

  public PPMImage(){
    super(0, 0, 255);
  }

  public PPMImage(int width, int height, int colordepth){
    super(width, height, colordepth);
  }

  public PPMImage(java.io.File file) throws FileNotFoundException, UnsupportedFileFormatException{
    int height = 0;
    int width = 0;
    int colordepth = 0;
    try(Scanner fsc = new Scanner(file)){
      if(fsc.hasNext() == false || fsc.next().equals("P3") == false){
        throw new UnsupportedFileFormatException("file not ppm");
      }

      height = fsc.nextInt();
      width = fsc.nextInt();
      colordepth = fsc.nextInt();

      super.pixels = new RGBPixel[width][height];
      for(int i = 0 ; i < super.pixels.length; i++){
        for(int j = 0; j < super.pixels[0].length; j++){
          super.pixels[i][j] = new RGBPixel(fsc.nextShort(),
                                            fsc.nextShort(),
                                            fsc.nextShort());
        }
      }

    } catch(FileNotFoundException ex){
      throw new FileNotFoundException("PPMImage constructor: File \"" + file + "\" not found.");
    } catch(NoSuchElementException ex){
      System.err.println("File is corrupted!");
      System.exit(1);
    }
  }

  public PPMImage(RGBImage img){
    super(img);
  }

  public PPMImage(YUVImage img){
    super(img);
  }

  public String toString(){
    StringBuilder strB = new StringBuilder("P3\n");
    int i, j;
    strB.append(super.pixels[0].length + " " + super.pixels.length + "\n255\n");

    for(i = 0 ; i < super.pixels.length; i++){
      for(j = 0; j < super.pixels[0].length; j++){
        strB.append(super.pixels[i][j].toString() + " ");
      }
      strB.append("\n");
    }
    return strB.toString();
  }

  public void toFile(java.io.File file){
    try (FileWriter fWriter = new FileWriter(file, false)) {
      fWriter.write(toString());
    }catch(IOException ex){
      System.err.println("Something went wrong with FileWriter");
      System.exit(1);
    }
  }
}
