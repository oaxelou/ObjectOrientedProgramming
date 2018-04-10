// package ce325.hw2;

import java.io.*;
import java.util.*;

public class PPMImageStacker{

	List <PPMImage> fileList;

	PPMImage finalImage;

	public PPMImageStacker(java.io.File dir){

		File [] fileTable;
		PPMImage temp;

		if( !dir.exists() ){
			System.out.println("[ERROR] Directory "+ dir +" does not exist!");
			return;
		}

		if( !dir.isDirectory() ){
			System.out.println("[ERROR] "+ dir +" is not a directory!");
			return;
		}

		fileTable = dir.listFiles();

		fileList = new LinkedList<PPMImage>();


		for(int i = 0 ; i < fileTable.length; i++){
			try{
				temp = new PPMImage(fileTable[i]);
				fileList.add(temp);
			}
			catch( UnsupportedFileFormatException ex1 ){}
			catch( FileNotFoundException ex2){}
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