package Auto_Associative_Memory;

public class Matrix_Operations {
	
	
	public static  int [][] Multiplication(int [][] m1,int [][] m2 ){
		
			int [][] result = new int [m1.length][m2[0].length];    
	  
			int mult;
			  
		    for(int i=0;i<m1.length;i++)
		    {
		    	
		      for(int j=0;j<m2[0].length;j++)
		      {
		      	mult=0;
		      	for(int k=0;k<m1[0].length;k++) 
		          {
		          	mult += m1[i][k]* m2[k][j];
		          }
		          result[i][j] = mult;
		                 
		      }
		    }
		    return result;
	}
	///////////////////////////////////////////////////////////
	public static  void  Addition(int [][] m1,int [][] m2 ){

		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m1[0].length; j++) {
				m1[i][j] += m2[i][j];
			}	
		}
	}
	///////////////////////////////////////////////////////////
	public static  int [][] Multiply_Vector_Matrix(int [] m1,int [][] m2 ){
		
		int [][]m1Modified = new int[1][m1.length];
		m1Modified[0] = m1;
		
		return Matrix_Operations.Multiplication(m1Modified, m2);
	}
	/////////////////////////////////////////////////////////////////
	
	public static  int [][] Multiply_Matrix_Vector(int [][] m1,int [] m2 ){
		
		int [][]m2Modified = new int[m2.length][1];
		
		for (int i = 0; i < m2Modified.length; i++) {
			m2Modified[i][0] = m2[i];
		}
		
		return Matrix_Operations.Multiplication(m1, m2Modified);
	}
	/////////////////////////////////////////////
	public static  int [][] Multiply_Vector_Vector(int [] m1,int [] m2 ){
		int [][]res = new int[m1.length][m2.length];
		
		for(int i=0;i<m1.length;i++)
		{
			for (int j = 0; j < m2.length; j++) {
				res[i][j] = m1[i] * m2[j];
			}
		}
		
		return res;
		
	}
	//////////////////////////////////
	public static int [][] convertToVerticalVector(int [][]m)
	{
		int[][] newM = new int [m[0].length][1];
		
		for (int i = 0; i < m[0].length; i++) {
			newM[i][0] = m[0][i];
		}
		
		return newM;
	}
	//////////////////
	public static int [][] convertToHorizontalVector(int [][]m)
	{
		int[][] newM = new int [1][m.length];
		
		for (int i = 0; i < m.length; i++) {
			newM[0][i] = m[i][0];
		}
		
		return newM;
	}
	/////////////////////////////////////////////
	public static void print(int [][]m)
	{
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
	//////////////////////////////////////////////
	public static boolean  areEqual(int [][]m1, int [][]m2) {
		
		if(m1.length != m2.length ||m1[0].length != m2[0].length )
			return false;
		
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m1[0].length; j++) {
				if(m1[i][j] !=m2[i][j])
					return false;
			}	
		}
		
		return true;
	}
}
	
	



