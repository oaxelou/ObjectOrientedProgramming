import java.io.*;

public class StackerTester{



	public static void main(String [] args){

		PPMImage Image1, Image2, Image3;
		File F_Image1, F_Image2, F_Image3;
		File First, Second, Third;
		PPMImageStacker FirstS, SecondS, ThirdS;

		First = new File("WFC3UVIS");

		System.out.println("\n\n1.Made File object for directory!");

		FirstS = new PPMImageStacker(First);

		System.out.println("1.PPMImageStacker made!");

		FirstS.stack();

		System.out.println("1.Stacked");

		Image1 = FirstS.getStackedImage();

		System.out.println("1.Took Stacked Image");

		F_Image1 = new File("__WFC3UVIS");

		System.out.println("1.Made Object for Image");

		Image1.toFile(F_Image1);

		System.out.println("First Image Finished!");

		//====================//

		Second = new File("orion_nebula");

		System.out.println("\n\n2.Made File object for directory!");

		SecondS = new PPMImageStacker(Second);

		System.out.println("2.PPMImageStacker made!");

		SecondS.stack();

		System.out.println("2.Stacked");

		Image2 = SecondS.getStackedImage();

		System.out.println("2.Took Stacked Image");

		F_Image2 = new File("__orion_nebula");

		System.out.println("2.Made Object for Image");

		Image2.toFile(F_Image2);

		System.out.println("Second Image Finished!");

		//====================//

		Third = new File("M83");

		System.out.println("\n\n3.Made File object for directory!");

		ThirdS = new PPMImageStacker(Third);

		System.out.println("3.PPMImageStacker made!");

		ThirdS.stack();

		System.out.println("3.Stacked");

		Image3 = ThirdS.getStackedImage();

		System.out.println("3.Took Stacked Image");

		F_Image3 = new File("__M83");

		System.out.println("3.Made Object for Image");

		Image3.toFile(F_Image3);

		System.out.println("Third Image Finished!");

		//====================//





	}
}