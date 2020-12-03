package star2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static char[][] array;
	
	public static void star(int n, int row, int col)
	{
		if(n == 3)
		{
			for(int i = row; i < row+3; i++)
			{
				for(int j = col; j < col+3; j++)
				{
					if(i == row+1 && j == col+1)
					{
						array[i][j] = ' ';
					}else
					{
						array[i][j] = '*';
					}
					
				}
			}
		}else
		{
			star(n/3, row, col);
			star(n/3, row, col+1*(n/3));
			star(n/3, row, col+2*(n/3));
			
			star(n/3, row+1*(n/3), col);
			star(n/3, row+1*(n/3), col+2*(n/3));
			
			star(n/3, row+2*(n/3), col);
			star(n/3, row+2*(n/3), col+1*(n/3));
			star(n/3, row+2*(n/3), col+2*(n/3));
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = input.readLine();
		int n = Integer.parseInt(line);
		
		array = new char[n][n];
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				array[i][j] = ' ';
			}
		}
		
		star(n, 0, 0);
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++) 
			{
				output.write(array[i][j]);
			}
			output.newLine();
		}
		
		input.close();
		output.close();

	}

}
