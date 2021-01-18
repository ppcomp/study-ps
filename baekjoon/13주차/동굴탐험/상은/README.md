# 13주차 - 동굴 탐색

# 1. 문제

[https://programmers.co.kr/learn/courses/30/lessons/67260](https://programmers.co.kr/learn/courses/30/lessons/67260)

# 2. 코드

```java
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

		for (int i = 0; i < path.length; i++) // 경로
		{
			map.get(path[i][0]).add(path[i][1]);
			map.get(path[i][1]).add(path[i][0]);
		}

		for (int i = 0; i < order.length; i++)
		{
			beforeVisit[order[i][1]] = order[i][0]; // 방문해야만 하는 지점
		}

		if (beforeVisit[0] != 0)
		{ // 0이 먼저 들려야하는 정점이 있다면 false
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
			
			if(visited[temp]) // 방문한 곳 -> 다시 방문 안함
			{
				continue;
			}
			
			if (!visited[beforeVisit[temp]]) // 방문해야만 하는 지점을 방문하지 않은 경우 -> 나중에 다시 탐색
			{
				goVisit[beforeVisit[temp]] = temp;
				continue;
			}
			
			visited[temp] = true;
			
			for (Integer i : tempAL)
			{
				q.add(i);
			}
			
			if(goVisit[temp] != 0) // 가야 하는 지점이 있을 경우
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
```

# 3. 설명

1. 구현 방법
- BFS를 사용하여 전체 탐색

2.  입력 방법 및 변수 지정

```java
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		boolean[] visited = new boolean[n];
		int[] beforeVisit = new int[n];
		int[] goVisit = new int[n];
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < n; i++)
		{
			map.put(i, new ArrayList<Integer>());
		}

		for (int i = 0; i < path.length; i++) // 경로 양방향 저장
		{
			map.get(path[i][0]).add(path[i][1]);
			map.get(path[i][1]).add(path[i][0]);
		}

		for (int i = 0; i < order.length; i++)
		{
			beforeVisit[order[i][1]] = order[i][0]; // 방문해야만 하는 지점
		}
```

- map을 사용하여 해결, map에는 연결 지점들이 저장됨

    → ex) 0 = {1, 3, 7} , 1 = {0, 8, 2} 같이 양방향으로

- beforeVisit은 해당 지점을 방문하기전 필수적으로 방문해야만 하는 노드 저장

    → ex) 1번 문제에서 7번은 6번을 먼저 방문 해야함 → beforeVisit[7] = 6

```java
		while(!q.isEmpty())
		{
			int temp = q.poll();
			ArrayList<Integer> tempAL = map.get(temp);
			
			if(visited[temp]) // 방문한 곳 -> 다시 방문 안함
			{
				continue;
			}
			
			if (!visited[beforeVisit[temp]]) // 방문해야만 하는 지점을 방문하지 않은 경우 -> 나중에 다시 탐색
			{
				goVisit[beforeVisit[temp]] = temp;
				continue;
			}
			
			visited[temp] = true;
			
			for (Integer i : tempAL)
			{
				q.add(i);
			}
			
			if(goVisit[temp] != 0) // 가야 하는 지점이 있을 경우
			{
				q.add(goVisit[temp]);
			}
		}
```

- BFS문
- 만약 방문한곳을 다시 방문하려한다면 continue문으로 건너뜀
- 방문해야하는 지점을 먼저 방문하지 않은경우는 goVisit 배열에 저장

    → ex) 1번지점을 먼저 방문하려면 4번지점을 먼저 방문해야함 → goVisit[4] = 1

- 마지막에 goVisit배열로 방문해야하는 지점이 있는경우 q에 넣고 방문

# 4. 결과

![https://user-images.githubusercontent.com/64006699/104861568-b2c07300-5973-11eb-8f62-04020754cc5d.png](https://user-images.githubusercontent.com/64006699/104861568-b2c07300-5973-11eb-8f62-04020754cc5d.png)