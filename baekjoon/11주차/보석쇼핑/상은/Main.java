package test3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main
{
	public static int[] solution(String[] gems)
	{
		int[] answer = new int[2];
		HashSet<String> hs = new HashSet<String>();

		for (int i = 0; i < gems.length; i++)
		{
			hs.add(gems[i]);
		}
		HashMap<String, Integer> hm = new HashMap<String, Integer>(); // �� ���ں� ���� ī��Ʈ�ϱ�����
		Deque<String> dq = new LinkedList<String>();
		int tempStart = 1;
		int length = 99999;
		int start = 0;

		for (int i = 0; i < gems.length; i++)
		{

			dq.addLast(gems[i]);
			if (!hm.containsKey(gems[i]))
			{
				hm.put(gems[i], 1);
			} else
			{
				hm.put(gems[i], hm.get(gems[i]) + 1);
			}

			while (hm.get(dq.peek()) >= 2) // ���ڰ� 2�� �̻� ���ԵǾ��ִٸ�
			{
				hm.put(dq.peek(), hm.get(dq.peek()) - 1);
				dq.removeFirst();
				tempStart++;
			}

			if (hs.size() == hm.size() && length > dq.size()) // ��� ���ڸ� �����ϰ� �ּ� ������ ���
			{
				start = tempStart;
				length = dq.size();
			}

		}

		answer[0] = start;
		answer[1] = start + length - 1;
		return answer;
	}

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] gems = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };

		int[] solutions;
		solutions = solution(gems);

		for (int i = 0; i < solutions.length; i++)
		{
			output.write(solutions[i] + " ");
		}

		input.close();
		output.close();

	}

}
