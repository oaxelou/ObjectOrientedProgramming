/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*   PPMImageStacker class:
*   The objects occured by this class manage the stacking of a set of PPMImages into one.
* 
*/


package ce325.hw2;

import java.io.*;
import java.util.*;

public class PPMImageStacker{

	List <PPMImage> fileList;
	PPMImage finalImage;

	public PPMImageStacker(java.io.File dir) throws FileNotFoundException, UnsupportedFileFormatException {

		File [] fileTable;
		PPMImage temp;

		if(dir.isDirectory() == false){
			throw new FileNotFoundException("Not a directory.");
		}
		fileTable = dir.listFiles();
		fileList = new LinkedList<PPMImage>();

		for(int i = 0 ; i < fileTable.length; i++){
			try{
				temp = new PPMImage(fileTable[i]);
				fileList.add(temp);
			}
			catch( UnsupportedFileFormatException ex1 ){
				throw new UnsupportedFileFormatException("Directory must contain only PPM files.");
			}
			catch( FileNotFoundException ex2){
				throw new FileNotFoundException("Directory must contain only PPM files.");
			}
		}
	}

	public void stack(){

		int sum, average;
		int i,j,k;
		int height = fileList.get(0).getPixelsArray().length;
		int width  = fileList.get(0).getPixelsArray()[0].length;

		finalImage = new PPMImage(width, height, 0);

		for(i = 0; i < height; i++){
			for(j =0; j < width; j++){

				//SET RED FOR EACH PIXEL
				sum = 0;

				for(k =0; k < fileList.size(); k++)
					sum += fileList.get(k).getPixelsArray()[i][j].getRed();

				average = sum / fileList.size()  ;

				finalImage.getPixelsArray()[i][j].setRed( (short)average );

				//SET GREEN FOR EACH PIXEL
				sum = 0;

				for(k =0; k < fileList.size(); k++)
					sum += fileList.get(k).getPixelsArray()[i][j].getGreen();

				average = sum / fileList.size()  ;

				finalImage.getPixelsArray()[i][j].setGreen( (short)average );

				//SET BLUE FOR EACH PIXEL
				sum = 0;

				for(k =0; k < fileList.size(); k++)
					sum += fileList.get(k).getPixelsArray()[i][j].getBlue();

				average = sum / fileList.size()  ;

				finalImage.getPixelsArray()[i][j].setBlue( (short)average );
			}
		}
	}

	public PPMImage getStackedImage(){
		return finalImage;
	}
}
