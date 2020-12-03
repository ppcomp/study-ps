package star;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

class Node{
	int row;
	int col;
	
	public Node(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
}

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		int num = Integer.parseInt(input.readLine());
		
		char[][] array = new char[num][num*2-1]; 
		
		for(int i = 0; i < num; i++)
		{
			for(int j = 0; j < num*2-1; j++)
			{
				array[i][j] = '0';
			}
		}
		
		Deque<Node> queue = new LinkedList<Node>();
		
		queue.add(new Node(0, num-1));
		array[0][num-1]++;
		
		while(!queue.isEmpty()) // 삼각형별로 맨위 꼭짓점 찾기
		{
			Node temp = queue.poll();
			if(temp.row + 3 < num)
			{
				Node leftNode = new Node(temp.row+3, temp.col-3);
				array[leftNode.row][leftNode.col]++;
				Node rightNode = new Node(temp.row+3, temp.col+3);
				array[rightNode.row][rightNode.col]++;
				if(array[leftNode.row][leftNode.col] == '1') // 중복 제거
				{
					queue.add(leftNode);
					queue.add(rightNode);
				}else
				{
					queue.removeLast();
					queue.add(rightNode);
				}
			}
		}
		
		for(int i = 0; i < num; i++)
		{
			for(int j = 0; j < num*2-1; j++)
			{
//				if(array[i][j] == '1')
//				{
//					array[i][j] = '*';
//					array[i+1][j-1] = '*';
//					array[i+1][j+1] = '*';
//					for(int k = j-2; k <= j+2; k++)
//					{
//						array[i+2][k]='*';
//					}
//				}

				output.write(array[i][j] == '1' ? '*' : ' ');	
				
			}
			output.newLine();
		}
		
		input.close();
		output.close();
		
		
	}

}
