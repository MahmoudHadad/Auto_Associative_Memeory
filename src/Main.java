import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import Auto_Associative_Memory.AutoAssociativeMemory;



public class Main {
	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {

		ArrayList <ArrayList<Integer> >images = getMemoryImages("Training");
		if(images.size() ==0)
		{
			System.out.println("NO Training file");
			return;
		}

		AutoAssociativeMemory auto = new AutoAssociativeMemory();
		auto.m = images.get(0).size();

		// add images to memory0
		for (int i = 0; i < images.size(); i++) {
			int []arr = toArray(images.get(i));
			auto.Xs.add(arr);
			

		}
		//////////////////
		int choice;
		while(true){
			System.out.println("Choose one option");
			System.out.println("1- Add to memory");
			System.out.println("2- Initiate memory");
			System.out.println("3- Recall X0");
			System.out.println("4- View Energy");

			try{
				choice = sc.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("Enter valid input");
				continue;
			}
			if(choice == 1)
			{
				addToMemory(auto);
			}
			else if(choice == 2)
				auto.initiateMemory();

			else if(choice == 3)
			{
				File f;
				while(true){
					System.out.println("Enter x0 path");
					String path = sc.next();
					try{
						f = new File(path);
						if(f.exists())
							break;
						System.out.println("path not found");
					}
					catch(Exception e){
						System.out.println("path not found");
					}
				}
				
				int []x0 = toArray(ImageProcessing.readImage(f)) ;
				int [] image = auto.reCallX0(x0);
				int indx = searchInList(image, auto.Xs);
				
				int i = -1;
				if(indx == 0){
					i = 1;
				}
				else if(indx == 1){
					i = 3;
				}
				else if(indx == 2){
					i = 8;
				}
				
				System.out.println("Image number: " + i);
				//print1D(image);
				prepareToImage(image);
				
				//print1D(image);
				
				int [][]image2D = convertTo2D(image, 10, 5);
				print2D(image2D);
				ImageRW.writeImage(image2D, "D:\\java\\Auto_Associative_Memeory\\result.jpg");
				
			}

			
			else if(choice ==4)
			{
				auto.viewEnergies();
			}


		}

	}


	///////////////////////////////////////////////////////////////////////////////
	private static ArrayList <ArrayList<Integer> > getMemoryImages(String folderPath)
	{
		File folder = new File(folderPath);
		ArrayList<ArrayList<Integer>>images = new ArrayList<ArrayList<Integer> >();

		for(File f: folder.listFiles() )
		{

			ArrayList<Integer> i = ImageProcessing.readImage(f);
			images.add(i);
		}

		return images;
	}
	private static int [] toArray(ArrayList<Integer>i)
	{
		int []arr = new int [i.size()];
		for (int j = 0; j < arr.length; j++) {
			arr[j] = i.get(j);
		}
		return arr;
	}


	private static void addToMemory(AutoAssociativeMemory auto){
		int []x = new int [auto.m];

		System.out.println("Enter x elements");

		for (int i = 0; i < x.length; i++) {
			x[i] = sc.nextInt();
		}

		auto.Xs.add(x);

	}
	//////////////////////////////////////////
	public static int searchInList(int []arr, ArrayList<int []>list)
	{
		for (int i = 0; i < list.size(); i++) {
			boolean equal = true;
			for (int j = 0; j < arr.length; j++) {
				if(arr[j]!=list.get(i)[j])
				{
					equal = false;
					break;
				}
			}
			if(equal)
				return i;
		}
		
		return -1;
	}
	///////////////////////////////////////////////////////////////////
	public static void prepareToImage(int []arr)
	{
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] ==-1)
				arr[i] =0;
			else if(arr[i] == 1)
				arr[i] = 255;
				
		}
	}
	///////////////////////
	public static int [][] convertTo2D(int []arr, int length, int width )
	{
		int [][] newArr = new int [length][width];
		
		for (int i = 0; i < arr.length; i++) {
			newArr[i/width][i%width] = arr[i];
		}
		
		return newArr;
	}
	
	public static void print2D(int [][]image2D) {
		
		for (int j = 0; j < image2D.length; j++) {
			for (int j2 = 0; j2 < image2D[0].length; j2++) {
				System.out.print(image2D[j][j2] + " ");
			}
			System.out.println();
		}
	}
	////////////////////
	public static void print1D(int []image) {
		for (int i = 0; i < image.length; i++) {
			System.out.print(image[i] + " ");
		}
		System.out.println();
	}
}
