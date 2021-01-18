package cave;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main
{

	public static boolean solution(int n, int[][] path, int[][] order)
	{
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		boolean[] visited = new boolean[n];
		int[] beforeVisit = new int[n];
		int[] goVisit = new int[n];
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < n; i++)
		{
			map.put(i, new ArrayList<Integer>());
		}

		for (int i = 0; i < path.length; i++) // ���
		{
			map.get(path[i][0]).add(path[i][1]);
			map.get(path[i][1]).add(path[i][0]);
		}

		for (int i = 0; i < order.length; i++)
		{
			beforeVisit[order[i][1]] = order[i][0]; // �湮�ؾ߸� �ϴ� ����
		}

		if (beforeVisit[0] != 0)
		{ // 0�� ���� ������ϴ� ������ �ִٸ� false
			return false;
		}

		visited[0] = true;
		for(Integer i : map.get(0))
		{
			q.add(i);
		}

		while(!q.isEmpty())
		{
			int temp = q.poll();
			ArrayList<Integer> tempAL = map.get(temp);
			
			if(visited[temp]) // �湮�� �� -> �ٽ� �湮 ����
			{
				continue;
			}
			
			if (!visited[beforeVisit[temp]]) // �湮�ؾ߸� �ϴ� ������ �湮���� ���� ��� -> ���߿� �ٽ� Ž��
			{
				goVisit[beforeVisit[temp]] = temp;
				continue;
			}
			
			visited[temp] = true;
			
			for (Integer i : tempAL)
			{
				q.add(i);
			}
			
			if(goVisit[temp] != 0) // ���� �ϴ� ������ ���� ���
			{
				q.add(goVisit[temp]);
			}
		}

		for (int i = 0; i < n; i++)
		{
			if (visited[i] == false)
			{
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		int[][] path = { { 0, 1 }, { 0, 3 }, { 0, 7 }, { 8, 1 }, { 3, 6 }, { 1, 2 }, { 4, 7 }, { 7, 5 } };
		int[][] order =  {{8,5}, {6,7}, {4,1}};
		output.write(String.valueOf(solution(9, path, order)));
		input.close();
		output.close();
	}

}
