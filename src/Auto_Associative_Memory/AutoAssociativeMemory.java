package Auto_Associative_Memory;

import java.util.ArrayList;

// it is the same as bidirectional memory by instead of y it will be x values 
public class AutoAssociativeMemory {

	 public int m;

	public  ArrayList<int []>Xs;


	public AutoAssociativeMemory() {
		Xs = new ArrayList<int[]>();
		Xs = new ArrayList<int[]>();		
	}
	 public int[][] memory;

	public  void initiateMemory ( )
	{
		memory = new int [m][m];

		for (int i = 0; i < Xs.size(); i++) {

			int [][]xi_Mult_yi = Matrix_Operations.Multiply_Vector_Vector(Xs.get(i), Xs.get(i));
			Matrix_Operations.Addition(memory, xi_Mult_yi);

		}
		System.out.println("Memory");
		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length; j++) {
				System.out.print(memory[i][j] + " ");
			}
			System.out.println();
		}

	}

	/////////////////////////////////
	public  int [] reCallX0(int []x0)
	{
		// Y_  <-  W*x0
		int [][]y_ = Matrix_Operations.Multiply_Matrix_Vector(memory, x0);
		y_ = Matrix_Operations.convertToHorizontalVector(y_);
		
		

		boolean zeroExists = false;
		for(int i =0 ; i <y_[0].length; i++)
		{
			if(y_[0][i]>0)
				y_[0][i] = 1;
			else if(y_[0][i] <0)
				y_[0][i] = -1;
			else 
				zeroExists = true;
		}
		// solve 0 problem by getting Y with min distance
		if(zeroExists)
		{
			int []ytemp = getMinDistance(y_, Xs);

			for(int i =0 ; i <y_[0].length; i++)
			{
				if(y_[0][i] == 0)
					y_[0][i] = ytemp[i];
			}
		}

		
		/////////

		// Y_ * w -> x_
		int [][]x_ = Matrix_Operations.Multiplication(y_, memory);
		zeroExists = false;
		for(int i =0 ; i <x_[0].length; i++)
		{
			if(x_[0][i]>0)
				x_[0][i] = 1;
			else if(x_[0][i] <0)
				x_[0][i] = -1;
			else 
				zeroExists = true;
		}
		// solve 0 problem by getting Y with min distance
		if(zeroExists)
		{
			int []xtemp = getMinDistance(x_, Xs);

			for(int i =0 ; i <x_[0].length; i++)
			{
				if(x_[0][i] == 0)
					x_[0][i] = xtemp[i];
			}
		}

		x_ = Matrix_Operations.convertToVerticalVector(x_);
		


		/////////////////////////////////
		// Y__
		int [][]y__ = Matrix_Operations.Multiplication(memory, x_);
		y__ = Matrix_Operations.convertToHorizontalVector(y__);
		

		zeroExists = false;
		for(int i =0 ; i <y__[0].length; i++)
		{
			if(y__[0][i]>0)
				y__[0][i] = 1;
			else if(y__[0][i] <0)
				y__[0][i] = -1;
			else 
				zeroExists = true;
		}
		// solve 0 problem by getting Y with min distance
		if(zeroExists)
		{
			int []ytemp = getMinDistance(y__, Xs);

			for(int i =0 ; i <y__[0].length; i++)
			{
				if(y__[0][i] == 0)
					y__[0][i] = ytemp[i];
			}
		}

		
		///////////////////////////////////////////////////////
		// Y__ * w -> x__
		int [][]x__ = Matrix_Operations.Multiplication(y__, memory);
		zeroExists = false;
		for(int i =0 ; i <x__[0].length; i++)
		{
			if(x__[0][i]>0)
				x__[0][i] = 1;
			else if(x__[0][i] <0)
				x__[0][i] = -1;
			else 
				zeroExists = true;
		}
		// solve 0 problem by getting Y with min distance
		if(zeroExists)
		{
			int []xtemp = getMinDistance(x__, Xs);

			for(int i =0 ; i <x__[0].length; i++)
			{
				if(x__[0][i] == 0)
					x__[0][i] = xtemp[i];
			}
		}

		

		if(Matrix_Operations.areEqual(x_, Matrix_Operations.convertToVerticalVector(x__)) &&
				Matrix_Operations.areEqual(y_, y__))
		{
			System.out.println("Resonanse Occurred ");
			int []x = getMinDistance(x__, Xs);

			System.out.println("X = ");
			print(x__[0]);
			return x;
		}
		else{
			System.out.println("No resonance Occurred");
			return null;
		}

	}
	
	/////////////////////////////////////////
	private  int hamingDistance(int []h1, int []h2){
		int counter = 0;
		if(h1.length != h2.length)
			return -1;

		for (int i = 0; i < h2.length; i++) {
			if(h1[i] != h2[i])
				counter++;
		}
		return counter;
	}
	/////////////////
	private  int[]  getMinDistance(int []x, ArrayList<int []>arr) {
		
		int min = hamingDistance(x, arr.get(0));
		int indx = 0;
		

		for (int i = 1; i < arr.size(); i++) {
			int h = hamingDistance(x, arr.get(i));
			if(h < min )
			{
				min = h;
				indx = i;
				
			}
		}
		
		return arr.get(indx);
		
	}
	///////////////////////////////////////
	private  int[] getMinDistance(int [][]x, ArrayList<int []>arr) {
		

		int min = hamingDistance(x[0], arr.get(0));
		int indx = 0;
		

		for (int i = 1; i < arr.size(); i++) {
			int h = hamingDistance(x[0], arr.get(i));
			if(h <= min)
			{
				min = h;
				indx = i;
			}
		}
		return arr.get(indx);
		
	}
	//////////////////////////////////////////////////////////
	private  void print (int []x)
	{
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i] + " ");
		}
		System.out.println();
	}
	/////////////////////////////////
	public  void viewEnergies()
	{
		System.out.println("Energy ");
		System.out.println(calcEnergy());

		System.out.println("Computational Energy");
		calcComputationalEnergy();

	}
	////////////////////////////////
	public  int calcEnergy()
	{
		int e = 0;
		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length; j++) {
				e += (Math.abs(memory[i][j]));
			}	
		}
		return -e;
	}
	///////////
	public  void calcComputationalEnergy()
	{
		int e =0;

		for (int i = 0; i < Xs.size(); i++) {

			int [][]E = Matrix_Operations.Multiply_Vector_Matrix(Xs.get(i), memory);
			E = Matrix_Operations.Multiply_Matrix_Vector(E, Xs.get(i)); 

			//System.out.println(E.length + " * " + E[0].length);
			System.out.println("For X"+(i+1) +" Y"+ (i+1) + " Energy = " + -1*E[0][0]);

		}

	}

}
