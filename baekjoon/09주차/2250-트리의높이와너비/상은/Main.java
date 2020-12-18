package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Node{
	int num;
	int left;
	int right;

	Node(int num, int left, int right)
	{
		this.num = num;
		this.left = left;
		this.right = right;
	}
}

public class Main
{

	static Node[] array;
	static int x = 0;
	static int leftHeightMin[] = new int[50];
	static int rightHeightMax[] = new int[50];
	static int allHeight = 0;
	public static void dfs(int node, int left, int right, int height) // ������ȸ
	{
		if(left != -1)
		{
			dfs(array[left].num, array[left].left, array[left].right, height+1);
		}
		
		x++;
		
		if(leftHeightMin[height] == 0) // ���̺� �� ���ʳ�� ���ϱ�
		{
			leftHeightMin[height] = x;
		}
		else
		{
			leftHeightMin[height] = Math.min(leftHeightMin[height], x);
		}
		
		if(rightHeightMax[height] == 0) // ���̺� �� �����ʳ�� ���ϱ�
		{
			rightHeightMax[height] = x;
		}
		else
		{
			rightHeightMax[height] = Math.max(rightHeightMax[height], x);
		}
		
		
		if(right != -1)
		{
			dfs(array[right].num, array[right].left, array[right].right, height+1);
		}
		
		
		if(height > allHeight)
		{
			allHeight = height;
		}

		
		
	}
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = input.readLine();
		int n = Integer.parseInt(line);
		int count = 1;
		int[] check = new int[n+1];
		array = new Node[n+1];
		int rootNode = 0;
		int max = 0;
		int maxI = 0;
		
		while(count <= n)
		{
			line = input.readLine();
			StringTokenizer st = new StringTokenizer(line, " ");
			int node = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			if(left != -1) // ���� �ڽĳ�尡 �ִ� ���
			{
				check[left]++;
				
			}
			if(right != -1) // ���� �ڽĳ�尡 �ִ� ���
			{
				check[right]++;
			}
			
			if(left == -1 && right == -1 && n != 1) // �ڽ� ��尡 ���� ��� -> ��Ʈ ��� ���� �� -999�� ��
			{
				check[node] = -999;
			}
			array[node] = new Node(node, left, right);
			
			
			count++;
		}
		
		for(int i = 1 ; i < n+1; i++)
		{
			if(check[i] == 0)
			{
				rootNode = i; // ��Ʈ��� ã��
			}
		}
		
		dfs(rootNode, array[rootNode].left, array[rootNode].right, 0);
		
		for(int i = 0; i < allHeight; i++)
		{
			if(leftHeightMin[i] != 0 && rightHeightMax[i] != 0) // ���� �ִ°�쿡��
			{
				if((rightHeightMax[i] - leftHeightMin[i] + 1) > max)
				{
					max = rightHeightMax[i] - leftHeightMin[i] + 1;
					maxI = i+1;
				}
			}
		}
		
		output.write(maxI + " " + max);
		
		input.close();
		output.close();
	}

}
