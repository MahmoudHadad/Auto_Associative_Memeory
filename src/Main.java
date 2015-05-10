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
			auto.Ys.add(arr);

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
				addToMemory();
			}
			else if(choice == 2)
				AutoAssociativeMemory.initiateMemory();

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
				int [] image = AutoAssociativeMemory.reCallX0(x0);
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
			}

			
			else if(choice ==4)
			{
				AutoAssociativeMemory.viewEnergies();
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


	private static void addToMemory(){
		int []x = new int [AutoAssociativeMemory.m];

		System.out.println("Enter x elements");

		for (int i = 0; i < x.length; i++) {
			x[i] = sc.nextInt();
		}

		AutoAssociativeMemory.Xs.add(x);
		AutoAssociativeMemory.Ys.add(x);

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
}
