package test3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main
{

	public static boolean solution(int[][] key, int[][] lock)
	{
		int m = key.length;
		int n = lock.length;

		int[][] map = new int[m + 2 * n - 2][m + 2 * n - 2];
		int[][] temp =  new int[m + 2 * n - 2][m + 2 * n - 2];

		for (int i = 0; i < n; i++) // lock 중앙에 저장
		{
			for (int j = 0; j < n; j++)
			{
				map[m - 1 + i][m - 1 + j] = lock[i][j];
				temp[m - 1 + i][m - 1 + j] = lock[i][j];
			}
		}
		

		for (int i = 0; i < 4; i++) // rotate하며 자물쇠 확인
		{
			if (i != 0)
			{
				key = rotate(key);
			}

			for (int j = 0; j < m + n - 1; j++)
			{
				for (int k = 0; k < m + n -1; k++)
				{
					for(int a = 0; a < m; a++)
					{
						for(int b = 0; b < m; b++)
						{
							map[j+a][k+b] ^= key[a][b];  // xor연산, 다르면 1 같으면 0
						} 
					}
					
					if(unlock(m-1, m-1, n, map))
					{
						return true;
					}	
					arraycopy(temp, map);
						
				}
			}
			
		}

		return false;
	}

	public static boolean unlock(int row, int col, int n, int[][] map)
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (map[row + i][row + j] == 0)
				{
					return false;
				}
			}
		}

		return true;
	}

	public static int[][] rotate(int[][] arr)
	{
		int n = arr.length;
		int[][] rotate = new int[n][n];

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				rotate[i][j] = arr[n - 1 - j][i];
			}
		}
		return rotate;
	}
	
	public static void arraycopy(int[][] array, int[][] copyarray)
	{
		for(int i = 0; i < array.length; i++)
		{
			System.arraycopy(array[i], 0, copyarray[i], 0, array[i].length);
		}
	}

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };

		if (solution(key, lock))
		{
			output.write("true");
		} else
		{
			output.write("false");
		}

		input.close();
		output.close();

	}

}
