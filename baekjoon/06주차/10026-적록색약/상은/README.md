# 6주차 - 10026. 적록색약

# 1. 문제

[https://www.acmicpc.net/problem/10026](https://www.acmicpc.net/problem/10026)

# 2. 코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
```

```java
class Node
{
	int row;
	int col;
	char color;

	public Node(int row, int col, char color)
	{
		this.row = row;
		this.col = col;
		this.color = color;
	}
}
```

```java
public class Main {
```

```java
public static void main(String[] args) throws NumberFormatException, IOException {
	// TODO Auto-generated method stub
	
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	int number = Integer.parseInt(input.readLine());
	char[][] map = new char[number][number];
	boolean[][][] check = new boolean[2][number][number];
	int[][] dir = {{-1,0}, {0,-1}, {1,0}, {0,1}};
	int newRow;
	int newCol;
	int noRedGreen = 0; // 적록 색약이 아닌 경우
	int redGreen = 0; // 적록 색약인 경우
	
	Queue<Node> queue = new LinkedList<Node>();
	String line = "";
	
	for(int i = 0; i < number; i++)
	{
		line = input.readLine();
		for(int j = 0; j < number; j++)
		{
			map[i][j] = line.charAt(j);
		}
	}
	
	
	for(int i = 0; i < number; i++) // 적록 색약이 아닌 사람
	{
		for(int j = 0; j < number; j++)
		{
			if(check[0][i][j] == false)
			{
				queue.add(new Node(i, j, map[i][j]));
				check[0][i][j] = true;
				
				while(!queue.isEmpty())
				{
					Node temp = queue.poll();
					for(int k = 0; k < dir.length; k++)
					{
						newRow = temp.row + dir[k][0];
						newCol = temp.col + dir[k][1];
						
						if(0 <= newRow && newRow < number && 0 <= newCol && newCol < number && map[newRow][newCol] == temp.color && check[0][newRow][newCol] == false)
						{
							queue.add(new Node(newRow, newCol, map[newRow][newCol]));
							check[0][newRow][newCol] = true;
						}
					}
					
				}
				noRedGreen++;
			}
		}
		
	}
	
	for(int i = 0; i < number; i++) // 적록 색약인 경우
	{
		for(int j = 0; j < number; j++)
		{
			if(check[1][i][j] == false)
			{
				queue.add(new Node(i, j, map[i][j]));
				check[1][i][j] = true;
				
				while(!queue.isEmpty())
				{
					Node temp = queue.poll();
					for(int k = 0; k < dir.length; k++)
					{
						newRow = temp.row + dir[k][0];
						newCol = temp.col + dir[k][1];
						
						if(0 <= newRow && newRow < number && 0 <= newCol && newCol < number && check[1][newRow][newCol] == false)
						{
							if(map[newRow][newCol] == temp.color) // 같은 색상인 경우
							{
								queue.add(new Node(newRow, newCol, map[newRow][newCol]));
								check[1][newRow][newCol] = true;
							}
							else if((temp.color == 'G' || temp.color == 'R') && (map[newRow][newCol] == 'G' || map[newRow][newCol] == 'R')) // 적색 or 녹색인 경우
							{
								queue.add(new Node(newRow, newCol, map[newRow][newCol]));
								check[1][newRow][newCol] = true;
							}
						}
					}
					
				}
				redGreen++;
			}
		}
		
	}
	
	
	output.write(noRedGreen +" "+redGreen);
	
	
	
	
	input.close();
	output.close();
	
	
	
	

}

```

```java
}
```

# 3. 설명

1. 구현 방법
- BFS를 사용하여 해결했다.
- 처음에 적록색약이 아닌사람의 경우를 실행했고, 다음에 적록색약인 사람의 경우를 실행한다.

2.  입력 방법 및 변수 지정

```java
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	int number = Integer.parseInt(input.readLine());
	char[][] map = new char[number][number];
	boolean[][][] check = new boolean[2][number][number];
	int[][] dir = {{-1,0}, {0,-1}, {1,0}, {0,1}};
	int newRow;
	int newCol;
	int noRedGreen = 0; // 적록 색약이 아닌 경우
	int redGreen = 0; // 적록 색약인 경우
	
	Queue<Node> queue = new LinkedList<Node>();
	String line = "";
```

- check 배열은 BFS를 할 때 탐색한지 안한지를 체크하는 배열이다.
- check 배열은 적록색약인 경우, 아닌 경우 총 2개가 필요하기때문에 3차원배열로 표현했다.

```java
for(int i = 0; i < number; i++) // 적록 색약이 아닌 사람
	{
		for(int j = 0; j < number; j++)
		{
			if(check[0][i][j] == false)
			{
				queue.add(new Node(i, j, map[i][j]));
				check[0][i][j] = true;
				
				while(!queue.isEmpty())
				{
					Node temp = queue.poll();
					for(int k = 0; k < dir.length; k++)
					{
						newRow = temp.row + dir[k][0];
						newCol = temp.col + dir[k][1];
						
						if(0 <= newRow && newRow < number && 0 <= newCol && newCol < number && map[newRow][newCol] == temp.color && check[0][newRow][newCol] == false)
						{
							queue.add(new Node(newRow, newCol, map[newRow][newCol]));
							check[0][newRow][newCol] = true;
						}
					}
					
				}
				noRedGreen++;
			}
		}
		
	}
```

 1. 적록 색약이 아닌 경우

  queue에 체크를 안한경우 좌표와 색깔을 Node로 생성하여 삽입한다.

  BFS로 탐색하여 같은 색깔인 경우에만 탐색을 진행한다. 탐색이 끝나면 noRedGreen의 카운트 1을    

   올린다.

 

```java
	for(int i = 0; i < number; i++) // 적록 색약인 경우
	{
		for(int j = 0; j < number; j++)
		{
			if(check[1][i][j] == false)
			{
				queue.add(new Node(i, j, map[i][j]));
				check[1][i][j] = true;
				
				while(!queue.isEmpty())
				{
					Node temp = queue.poll();
					for(int k = 0; k < dir.length; k++)
					{
						newRow = temp.row + dir[k][0];
						newCol = temp.col + dir[k][1];
						
						if(0 <= newRow && newRow < number && 0 <= newCol && newCol < number && check[1][newRow][newCol] == false)
						{
							if(map[newRow][newCol] == temp.color) // 같은 색상인 경우
							{
								queue.add(new Node(newRow, newCol, map[newRow][newCol]));
								check[1][newRow][newCol] = true;
							}
							else if((temp.color == 'G' || temp.color == 'R') && (map[newRow][newCol] == 'G' || map[newRow][newCol] == 'R')) // 적색 or 녹색인 경우
							{
								queue.add(new Node(newRow, newCol, map[newRow][newCol]));
								check[1][newRow][newCol] = true;
							}
						}
					}
					
				}
				redGreen++;
			}
		}
	}
```

 2. 적록 색약인 경우

  queue에 체크를 안한경우 좌표와 색깔을 Node로 생성하여 삽입한다.

  BFS로 탐색하여 같은 색깔인 경우에만 탐색을 진행한다. 단, R과 G의 경우 같은색깔로 판단한다.

  탐색이 끝나면 noRedGreen의 카운트 1을 올린다.

# 4. 결과

![6%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20-%2010026%20%E1%84%8C%E1%85%A5%E1%86%A8%E1%84%85%E1%85%A9%E1%86%A8%E1%84%89%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%A3%E1%86%A8%20489543500fd64824969dba7ea7bc206b/Untitled.png](6%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%20-%2010026%20%E1%84%8C%E1%85%A5%E1%86%A8%E1%84%85%E1%85%A9%E1%86%A8%E1%84%89%E1%85%A2%E1%86%A8%E1%84%8B%E1%85%A3%E1%86%A8%20489543500fd64824969dba7ea7bc206b/Untitled.png)