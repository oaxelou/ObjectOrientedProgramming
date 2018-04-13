// package ce325.hw2;

import java.io.*;

public class Histogram{
  int []histogram;
  int noPixels;

  public Histogram(YUVImage img){
    // dhmiourgei to istogramma ths eikona yuv
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

  public String toString(){
    String str = "";
    //ektupwnei to istogramma se ena string
    // kathe grammh periexei:
    // thn timh ths fwteinothtas kai tosa * oso einai h timh auth
    // (kanonikopoihsh sta 80 asterakia ws max!)
    for(int i = 0; i < histogram.length; i++){
      str += i + ": ";
      for(int j = 0; j < histogram[i] && j < 80; j++){
        str += "*";
      }
      str += "\n";
    }
    return str;
  }

  // ektupwnei to string ths methodou se ena arxeio
  public void toFile(File file)throws FileNotFoundException, SecurityException{
    // kalei thn toString
    try (PrintWriter fWriter = new PrintWriter(file)) {
      // System.out.println("before");
      fWriter.println(toString());
      // System.out.println("after");
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
    // eksisoropei to istogramma
    for(int i = 0; i < histogram.length; i++){
      sumLum += histogram[i];
      histogram[i] = (sumLum * maxLum) / noPixels;
    }
  }

  public short getEqualizedLuminocity(int luminocity){
    // epistrefei thn eksisorophmenh timh fwteinothtas pou antistoixei
    // shtn dotheisa luminocity
    return (short)histogram[luminocity];
  }
}
