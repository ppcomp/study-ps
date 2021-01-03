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
		HashMap<String, Integer> hm = new HashMap<String, Integer>(); // 각 문자별 갯수 카운트하기위함
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

			while (hm.get(dq.peek()) >= 2) // 문자가 2개 이상 포함되어있다면
			{
				hm.put(dq.peek(), hm.get(dq.peek()) - 1);
				dq.removeFirst();
				tempStart++;
			}

			if (hs.size() == hm.size() && length > dq.size()) // 모든 문자를 포함하고 최소 길이일 경우
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
