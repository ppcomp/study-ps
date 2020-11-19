# 5주차 - 2146. 다리 만들기

# 1. 문제

[https://www.acmicpc.net/problem/2](https://www.acmicpc.net/problem/2667)146

# 2. 코드

```jsx
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
```

```jsx
class Node{
int row;
int col;
int count;
int current;
	public Node(int row, int col, int count, int current)
	{
		this.row = row;
		this.col = col;
		this.count = count;
		this.current = current;
	}
}
```

```jsx
public class Main {
```

```jsx
public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub

	
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	StringTokenizer st;
	String line = "";
	Queue<Node> queue = new LinkedList<Node>();
	
	int numbering = 2;
	int number = Integer.parseInt(input.readLine());
	int[][] map = new int[number][number];
	int[][] check = new int[number][number];
	int[][] dir = {{-1,0}, {0,-1}, {1,0}, {0,1}};
	int sumRow = 0;
	int sumCol = 0;
	int min = 999;

	for(int i = 0; i < number; i++) // getMap
	{
		line = input.readLine();
		st = new StringTokenizer(line, " ");
		for(int j = 0; j < number; j++)
		{
			map[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
	for(int i = 0; i < number; i++) // numbering
	{
		for(int j = 0; j < number; j++)
		{
			if(map[i][j] == 1)
			{
				queue.add(new Node(i, j, numbering, 1));
				while(!queue.isEmpty())
				{
					Node node = queue.poll();
					map[node.row][node.col] = numbering;
					for(int k = 0; k < dir.length; k++)
					{
						sumRow = node.row + dir[k][0];
						sumCol = node.col + dir[k][1];
						
						if(0 <= sumRow && sumRow < number && 0 <= sumCol && sumCol < number && check[sumRow][sumCol] == 0 && map[sumRow][sumCol] == 1)
						{
							queue.add(new Node(sumRow,sumCol, numbering, 1));
							check[sumRow][sumCol] = 1;
						}
					}
				}
				
				numbering++;
			}
		}
	}
	
	for(int i = 0; i < number; i++)
	{
		for(int j = 0; j < number; j++)
		{
			if(map[i][j] != 0)
			{
				check = new int[number][number];
				queue.add(new Node(i, j, 0, map[i][j]));
				while(!queue.isEmpty())
				{
					Node node = queue.poll();
					for(int k = 0; k < dir.length; k++)
					{
						sumRow = node.row + dir[k][0];
						sumCol = node.col + dir[k][1];
						
						if(0 <= sumRow && sumRow < number && 0 <= sumCol && sumCol < number && check[sumRow][sumCol] == 0)
						{
							if(map[sumRow][sumCol] == node.current) { // 현재 있는 땅을 건널 때
								queue.add(new Node(sumRow,sumCol, node.count, node.current));
								check[sumRow][sumCol] = 1;
							}else if(map[sumRow][sumCol] == 0) // 바다를 건널 때(카운트값 1씩 증가)
							{
								queue.add(new Node(sumRow,sumCol, node.count+1, node.current));
								check[sumRow][sumCol] = 1;
							}
							else // 새로운 섬을 만났을때
							{
								if(min > node.count)
								{
									min = node.count;
								}
							}
							
						}
					}
					
				}
			}
		}
	}
	
	output.write(String.valueOf(min));
	
	
	
	
	input.close();
	output.close();
	}
}

```

# 3. 설명

1. 구현 방법
- BFS를 사용하여 각 단지별로 숫자를 할당한 후, 단지마다 BFS를 사용하여 거리를 쟀다.

2.  입력 방법 및 변수 지정

```jsx
for(int i = 0; i < number; i++) // getMap
	{
	line = input.readLine();
	st = new StringTokenizer(line, " ");
	for(int j = 0; j < number; j++)
	{
		map[i][j] = Integer.parseInt(st.nextToken());
	}
}
```

- for문을 사용하여 map을 받는다.

```jsx
for(int i = 0; i < number; i++) // numbering
{
	for(int j = 0; j < number; j++)
	{
		if(map[i][j] == 1)
		{
			queue.add(new Node(i, j, numbering, 1));
			while(!queue.isEmpty())
		{
		Node node = queue.poll();
		map[node.row][node.col] = numbering;
		for(int k = 0; k < dir.length; k++)
		{
			sumRow = node.row + dir[k][0];
			sumCol = node.col + dir[k][1];
			if(0 <= sumRow && sumRow < number && 0 <= sumCol && sumCol < number && check[sumRow][sumCol] == 0 && map[sumRow][sumCol] == 1)
			{
				queue.add(new Node(sumRow,sumCol, numbering, 1));
				check[sumRow][sumCol] = 1;
			}
		}
	}
				
	numbering++;
	}
 }
}
```

- BFS를 사용하여 각 단지별 숫자를 할당한다.

```jsx
for(int i = 0; i < number; i++)
{
	for(int j = 0; j < number; j++)
	{
		if(map[i][j] != 0)
		{
			check = new int[number][number];
			queue.add(new Node(i, j, 0, map[i][j]));
			while(!queue.isEmpty())
			{
					Node node = queue.poll();
					for(int k = 0; k < dir.length; k++)
					{
						sumRow = node.row + dir[k][0];
						sumCol = node.col + dir[k][1];

						if(0 <= sumRow && sumRow < number && 0 <= sumCol && sumCol < number && check[sumRow][sumCol] == 0)
						{
							if(map[sumRow][sumCol] == node.current) { // 현재 있는 땅을 건널 때
								queue.add(new Node(sumRow,sumCol, node.count, node.current));
								check[sumRow][sumCol] = 1;
							}else if(map[sumRow][sumCol] == 0) // 바다를 건널 때(카운트값 1씩 증가)
							{
								queue.add(new Node(sumRow,sumCol, node.count+1, node.current));
								check[sumRow][sumCol] = 1;
							}
							else // 새로운 섬을 만났을때
							{
								if(min > node.count)
								{
									min = node.count;
								}
							}
						}
					}
					
				}
			}
		}
	}

output.write(String.valueOf(min));
```

- BFS를 사용하여 섬이 나올때마다 BFS로 모든 경로를 탐색하여 길이를 잰다.
- 만약 다른 섬을 만났을 때, min값보다 길이가 적다면 min값을 갱신한다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/99688397-14c24080-2ac9-11eb-997c-b8f62c865624.png](https://user-images.githubusercontent.com/64006699/99688397-14c24080-2ac9-11eb-997c-b8f62c865624.png)