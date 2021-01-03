# 11주차 - 보석 쇼핑

# 1. 문제

[https://programmers.co.kr/learn/courses/30/lessons/67258](https://programmers.co.kr/learn/courses/30/lessons/67258)

# 2. 코드

```java
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
```

# 3. 설명

1. 구현 방법
- deque를 사용하여 구현했다.
- HashMap으로 각 단어별 나온 숫자를 카운트 해주었고, HashMap에 단어들을 넣어가며 HashMap과 HashSet의 길이가 같은경우에 문자를 다 채운 경우로 판단, 최소값을 갱신해주었다

2.  입력 방법 및 변수 지정

```java
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
```

- hs값에 처음에 문자들을 다 넣어 무슨 문자가 있는지 판단
- HashMap에는 각 단어와 단어가 나온 숫자를 count

```java
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
```

- 각 문자가 나올때마다 덱에 삽입
    - 만약 HashMap에 포함 되어있지않다면 → count 1로 삽입
    - 만약 HashMap에 포함 되어있다면 → 원래 카운트 +1로 갱신
- HashMap을 사용하여 덱 맨 앞에있는 문자가 2번 이상 사용되어있을경우 삭제 후 HashMap의 count도 갱신, tempStart를 1더함
- 과정을 완료한 경우, HashSet과 HashMap의 크기를 비교하여 만약 같고(모든 문자사용), 최소의 길이를 사용한 경우에만 start, length 값 갱신

"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" 의 경우

deque에 DIA삽입 (count : 1)

→ DIA

deque에 RUBY삽입 (count : 1)

→ DIA RUBY

deque에 RUBY삽입 (count : 2)

→ DIA RUBY RUBY

deque에 DIA삽입 (count : 2)

→ DIA는 2번 사용한 경우이므로 맨 앞 DIA삭제 후 count 갱신(count : 1)

→ RUBY RUBY DIA

→ 맨 앞 RUBY도 2번 사용한 경우이므로 RUBY 삭제 후 count 갱신(count : 1)

→ RUBY DIA

deque에 DIA삽입 (count : 2)

→ RUBY DIA DIA

deque에 EMERALD삽입 (count : 1)

→ RUBY DIA DIA EMERALD

deque에 SAPPHIRE삽입 (count : 1)

→ RUBY DIA DIA EMERALD SAPPHIRE

deque에 DIA삽입 (count : 1)

→ RUBY DIA DIA EMERALD SAPPHIRE DIA

이 경우 답은 3,7

# 4. 결과

![https://user-images.githubusercontent.com/64006699/103479585-0d08f200-4e12-11eb-9920-578a2b0e0f1c.png](https://user-images.githubusercontent.com/64006699/103479585-0d08f200-4e12-11eb-9920-578a2b0e0f1c.png)