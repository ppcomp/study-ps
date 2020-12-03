package redGreen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Node
{
	int row;
	int col;
	char color;
	
	public Node(int row, int col, char color)
	{
		this.row = row;
		this.col = col;
		this.color = color;
	}
}

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		int number = Integer.parseInt(input.readLine());
		char[][] map = new char[number][number];
		boolean[][][] check = new boolean[2][number][number];
		int[][] dir = {{-1,0}, {0,-1}, {1,0}, {0,1}};
		int newRow;
		int newCol;
		int noRedGreen = 0; // 적록 색약이 아닌 경우
		int redGreen = 0; // 적록 색약인 경우
		
		Queue<Node> queue = new LinkedList<Node>();
		String line = "";
		
		for(int i = 0; i < number; i++)
		{
			line = input.readLine();
			for(int j = 0; j < number; j++)
			{
				map[i][j] = line.charAt(j);
			}
		}
		
		
		for(int i = 0; i < number; i++) // 적록 색약이 아닌 사람
		{
			for(int j = 0; j < number; j++)
			{
				if(check[0][i][j] == false)
				{
					queue.add(new Node(i, j, map[i][j]));
					check[0][i][j] = true;
					
					while(!queue.isEmpty())
					{
						Node temp = queue.poll();
						for(int k = 0; k < dir.length; k++)
						{
							newRow = temp.row + dir[k][0];
							newCol = temp.col + dir[k][1];
							
							if(0 <= newRow && newRow < number && 0 <= newCol && newCol < number && map[newRow][newCol] == temp.color && check[0][newRow][newCol] == false)
							{
								queue.add(new Node(newRow, newCol, map[newRow][newCol]));
								check[0][newRow][newCol] = true;
							}
						}
						
					}
					noRedGreen++;
				}
			}
			
		}
		
		for(int i = 0; i < number; i++) // 적록 색약인 경우
		{
			for(int j = 0; j < number; j++)
			{
				if(check[1][i][j] == false)
				{
					queue.add(new Node(i, j, map[i][j]));
					check[1][i][j] = true;
					
					while(!queue.isEmpty())
					{
						Node temp = queue.poll();
						for(int k = 0; k < dir.length; k++)
						{
							newRow = temp.row + dir[k][0];
							newCol = temp.col + dir[k][1];
							
							if(0 <= newRow && newRow < number && 0 <= newCol && newCol < number && check[1][newRow][newCol] == false)
							{
								if(map[newRow][newCol] == temp.color) // 같은 색상인 경우
								{
									queue.add(new Node(newRow, newCol, map[newRow][newCol]));
									check[1][newRow][newCol] = true;
								}
								else if((temp.color == 'G' || temp.color == 'R') && (map[newRow][newCol] == 'G' || map[newRow][newCol] == 'R')) // 적색 or 녹색인 경우
								{
									queue.add(new Node(newRow, newCol, map[newRow][newCol]));
									check[1][newRow][newCol] = true;
								}
							}
						}
						
					}
					redGreen++;
				}
			}
			
		}
		
		
		output.write(noRedGreen + " " + redGreen);
		
		
		
		
		input.close();
		output.close();
		
		
		
		

	}

}
