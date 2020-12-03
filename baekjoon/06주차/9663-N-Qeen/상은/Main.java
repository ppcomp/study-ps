package nQueen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int count = 0;
	static int[] array;
	static int num = 0;
	public static void nQueen(int row)
	{
		if(row == num)
		{
			count++;
		}
		else
		{
			for(int i = 0; i < num; i++)
			{
				if(check(i, row))
				{
					array[row] = i;
					nQueen(row+1);
					array[row] = -1;
				}
			}
		}
		
	}

	public static boolean check(int col, int row) // �࿡ ���� üũ
	{
		for(int i = 0; i < row; i++)
		{
			if(array[i] == col) // ���� ���� �ִ°��
			{
				return false;
			}else if(Math.abs(i - row ) == Math.abs(array[i] - col)) // �밢���� �ִ� ���
			{	
				return false;
			}
		}
		
		return true;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = input.readLine();
		num = Integer.parseInt(line);
		array = new int[num];
		
		for(int i = 0; i < num; i++)
		{
			array[i] = -1;
		}
		
		nQueen(0);
		
		output.write(String.valueOf(count));
		
		input.close();
		output.close();
	}

}
