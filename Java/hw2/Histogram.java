/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*   Histogram class:
*   Grapse edw
* 
*/

package ce325.hw2;

import java.io.*;

public class Histogram{
  int []histogram;
  int noPixels;

  public Histogram(YUVImage img){
    // creates the histogram of the yuv image
    short maxLum = img.pixels[0][0].getY();

    noPixels = img.pixels.length * img.pixels[0].length;

    for(int i = 0; i < img.pixels.length; i++){
      for(int j = 0; j < img.pixels[0].length; j++){
        if(img.pixels[i][j].getY() > maxLum){
          maxLum = img.pixels[i][j].getY();
        }
      }
    }
    histogram = new int[maxLum + 1];

    for(int i = 0; i < img.pixels.length; i++){
      for(int j = 0; j < img.pixels[0].length; j++){
        histogram[img.pixels[i][j].getY()]++;
      }
    }
  }

  /* Returns the histogram as a String
   * Each line includes:
   * the value of luminocity with stars * (max 80 stars per line) */
  public String toString(){
    String str = "";
    for(int i = 0; i < histogram.length; i++){
      str += i + ": ";
      for(int j = 0; j < histogram[i] && j < 80; j++){
        str += "*";
      }
      str += "\n";
    }
    return str;
  }

  // saves the return value of toString in a file
  public void toFile(File file)throws FileNotFoundException, SecurityException{
    try (PrintWriter fWriter = new PrintWriter(file)) {
      fWriter.println(toString());
    }catch(FileNotFoundException ex){
      System.err.println("histogr: File was not found! in toFile");
      throw new FileNotFoundException("histogr: In toFile: file was not found!");
    }catch(SecurityException ex){
      throw new SecurityException("histogr: Can't write on this file! (toFile)");
    }
  }

  public void equalize(){
    int sumLum = 0;
    int maxLum = histogram.length - 1;
    // equalizes histogram
    for(int i = 0; i < histogram.length; i++){
      sumLum += histogram[i];
      histogram[i] = (sumLum * maxLum) / noPixels;
    }
  }

  public short getEqualizedLuminocity(int luminocity){
    // returns the equalized value of luminocity
    return (short)histogram[luminocity];
  }
}
