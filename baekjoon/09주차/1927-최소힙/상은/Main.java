package minHeap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = input.readLine();
		int n = Integer.parseInt(line);
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		// PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); ∞° √÷¥Î»¸¿”
		
		for(int i = 0; i < n; i++)
		{
			int x = Integer.parseInt(input.readLine());
			
			if(x == 0)
			{
				if(!minHeap.isEmpty())
				{
					output.write(minHeap.poll() + "\n");
				}else
				{
					output.write(String.valueOf(0)+ "\n");
				}
			}else
			{
				minHeap.add(x);
			}
				
		}
		
		input.close();
		output.close();
	}
}
