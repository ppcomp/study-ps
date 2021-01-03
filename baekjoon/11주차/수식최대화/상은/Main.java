package test2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

public class Main
{
	static Long max = (long) 0;

	public static long solution(String expression)
	{
		String[] opOrder = new String[6];
		opOrder[0] = "*+-";
		opOrder[1] = "*-+";
		opOrder[2] = "+*-";
		opOrder[3] = "+-*";
		opOrder[4] = "-*+";
		opOrder[5] = "-+*";

		Deque<Long> numLeft = new LinkedList<Long>();
		Deque<Long> numRight = new LinkedList<Long>();
		Deque<Character> opLeft = new LinkedList<Character>();
		Deque<Character> opRight = new LinkedList<Character>();

		String[] num = expression.split("\\*|\\+|\\-"); // 숫자 분리



		for (int i = 0; i < opOrder.length; i++) // 연산자 우선순위
		{
			
			for (int j = 0; j < expression.length(); j++)
			{
				if (expression.charAt(j) == '*' || expression.charAt(j) == '+' || expression.charAt(j) == '-')
				{
					opLeft.add(expression.charAt(j));
				}
			}

			for (int j = 0; j < num.length; j++)
			{
				numLeft.add(Long.parseLong(num[j]));
			}
			
			
			for (int j = 0; j < opOrder[i].length(); j++)
			{
				int size = opLeft.size();
				for (int k = 0; k < size; k++)
				{
					if (opLeft.peek() == opOrder[i].charAt(j))
					{
						Long num1 = numLeft.poll();
						Long num2 = numLeft.poll();
						numLeft.addFirst(calculation(num1, num2, opLeft.poll()));
					} else
					{
						opRight.addLast(opLeft.poll());
						numRight.addLast(numLeft.poll());
					}
				}

				while (!numLeft.isEmpty()) // 왼쪽 잔여물 제거
				{
					numRight.addLast(numLeft.poll());
				}

				while (!numRight.isEmpty())
				{
					numLeft.addLast(numRight.poll());
				}

				while (!opRight.isEmpty())
				{
					opLeft.addLast(opRight.poll());
				}

			}

			max = Math.max(max, Math.abs(numLeft.poll()));

		}

		return max;
	}

	public static long calculation(long num1, long num2, char op)
	{
		long sum = 0;
		if (op == '+')
		{
			sum = num1 + num2;
		} else if (op == '*')
		{
			sum = num1 * num2;
		} else
		{
			sum = num1 - num2;
		}

		return sum;
	}
	
	public static Deque<Long> copyNum(Deque<Long> tempNum, Deque<Long> numLeft)
	{
		int size = tempNum.size();
		for(int i = 0; i < size; i++)
		{
			numLeft.addLast(tempNum.removeFirst());
		}
		return numLeft;
	}
	
	public static Deque<Character> copyOp(Deque<Character> tempOp, Deque<Character> opLeft)
	{
		int size = tempOp.size();
		for(int i = 0; i < size; i++)
		{
			opLeft.addLast(tempOp.removeFirst());
		}
		return opLeft;
	}

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

		output.write(String.valueOf(solution("50*6-3*2")));

		input.close();
		output.close();

	}

}
