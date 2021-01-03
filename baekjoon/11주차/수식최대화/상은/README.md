# 11주차 - 수식 최대화

# 1. 문제

[https://programmers.co.kr/learn/courses/30/lessons/67257](https://programmers.co.kr/learn/courses/30/lessons/67257)

# 2. 코드

```java
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

	String[] num = expression.split("\\\\*|\\\\+|\\\\-"); // 숫자 분리

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
```

# 3. 설명

1. 구현 방법
- Deque을 사용하여 구현했다.

2.  입력 방법 및 변수 지정

```java
String[] opOrder = new String[6];
opOrder[0] = "*+-";
opOrder[1] = "*-+";
opOrder[2] = "+*-";
opOrder[3] = "+-*";
opOrder[4] = "-*+";
opOrder[5] = "-+*";
```

- 3가지의 연산자들의 연산 순서는 총 6개의 경우이므로 배열에 저장

```java
Deque<Long> numLeft = new LinkedList<Long>();
Deque<Long> numRight = new LinkedList<Long>();
Deque<Character> opLeft = new LinkedList<Character>();
Deque<Character> opRight = new LinkedList<Character>();

String[] num = expression.split("\\\\*|\\\\+|\\\\-"); // 숫자 분리

```

- 4개의 덱 사용, num은 split으로 분리
- numLeft에서 옛날에 했던 커서방식으로 하나하나씩 오른쪽으로옮겨가며 연산

![https://user-images.githubusercontent.com/64006699/103477713-b77a1880-4e04-11eb-8143-3e28e2470b55.png](https://user-images.githubusercontent.com/64006699/103477713-b77a1880-4e04-11eb-8143-3e28e2470b55.png)

→ 커서를 옮겨가며(가상) 연산자에 따라 연산함

```java
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
```

- 처음에 opLeft와 numLeft에 입력값 expression을 이용하여 숫자/연산자 분리
- 연산의 순서를 바꿔가며 for문 시작
    - opLeft.peek()으로 현재 가장 밑에있는 연산자의 우선순위를 판별
        - 아니라면 → 오른쪽 덱으로 숫자, 연산자 이동
        - 맞다면 → 계산후 왼쪽 덱으로 다시 추가

# 4. 결과

![https://user-images.githubusercontent.com/64006699/103477789-5d2d8780-4e05-11eb-9295-4f9f21c84a65.png](https://user-images.githubusercontent.com/64006699/103477789-5d2d8780-4e05-11eb-9295-4f9f21c84a65.png)