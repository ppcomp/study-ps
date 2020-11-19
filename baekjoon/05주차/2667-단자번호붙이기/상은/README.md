# 5주차 - 2667. 단지번호붙이기

# 1. 문제

[https://www.acmicpc.net/problem/](https://www.acmicpc.net/problem/2667)2667

# 2. 코드

```jsx
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
```

```jsx
class Node {
int row;
int col;
int count;

	public Node(int row, int col, int count) {
	this.row = row;
	this.col = col;
	this.count = count;
 }
}
```

```jsx
public class Main {
public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub

	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	Stack<Node> stack = new Stack<Node>();

	String getMap = "";
	int number = 0;
	int[] groupCount = new int[350];
	int sumRow;
	int sumCol;

	String line = input.readLine();
	int n = Integer.parseInt(line);

	int[][] map = new int[n][n];
	int[][] canGo = new int[n][n];
	
	for(int i = 0; i < n; i++)
	{
		getMap = input.readLine();
		for (int j = 0; j < n; j++) {
			map[i][j] = getMap.charAt(j) - '0';
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] != 0 && canGo[i][j] == 0) {
				stack.add(new Node(i, j, 1));
				while (!stack.isEmpty()) {
					Node node = stack.pop();
					int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

					for (int k = 0; k < dir.length; k++) {
						sumRow = node.row + dir[k][0];
						sumCol = node.col + dir[k][1];

						if (0 <= sumRow && sumRow < n && 0 <= sumCol && sumCol < n) {
							if (map[sumRow][sumCol] != 0 && canGo[sumRow][sumCol] == 0) {
								canGo[sumRow][sumCol] = 1;
								groupCount[number]++;
								stack.push(new Node(sumRow, sumCol, node.count + 1));
							}
						}
					}

					
				}
				number++;
			}
		}
	}
	
	int[] sortGroup = new int[number];
	output.write(String.valueOf(number));
	output.newLine();
	for(int i = 0; i < number; i++)
	{
		sortGroup[i] = groupCount[i];
		if(sortGroup[i] == 0)
		{
			sortGroup[i]++;
		}
	}
	
	Arrays.sort(sortGroup);
	
	for(int i = 0; i < number; i++)
	{
		output.write(Integer.toString(sortGroup[i]));
		output.newLine();
	}

	input.close();
	output.close();
 }
}

```

# 3. 설명

1. 구현 방법
- DFS를 사용하여 단지번호를 부여 후, 각 번호마다 몇개인지 배열에 넣어 sort로 해결했다.

2.  입력 방법 및 변수 지정

```jsx
BufferedReader input = new BufferedReader(new InputStreamReader([System.in](http://system.in/)));
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
Stack<Node> stack = new Stack<Node>();
String getMap = "";
int number = 0;
int[] groupCount = new int[350];
int sumRow;
int sumCol;

String line = input.readLine();
int n = Integer.parseInt(line);

int[][] map = new int[n][n];
int[][] canGo = new int[n][n];
```

- 입력은 BufferedReader, 출력은 BufferedWriter로 입출력 시간을 줄였다.

```jsx
for(int i = 0; i < n; i++)
{
	getMap = input.readLine();
	for (int j = 0; j < n; j++) {
		map[i][j] = getMap.charAt(j) - '0';
	}
}
```

- for문을 사용하여 맵을 받았다.

```jsx
for (int i = 0; i < n; i++) {
	for (int j = 0; j < n; j++) {
		if (map[i][j] != 0 && canGo[i][j] == 0) {
		stack.add(new Node(i, j, 1));
		while (!stack.isEmpty()) {
		Node node = stack.pop();
		int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
			for (int k = 0; k < dir.length; k++) {
								sumRow = node.row + dir[k][0];
								sumCol = node.col + dir[k][1];
		
								if (0 <= sumRow && sumRow < n && 0 <= sumCol && sumCol < n) {
									if (map[sumRow][sumCol] != 0 && canGo[sumRow][sumCol] == 0) {
										canGo[sumRow][sumCol] = 1;
										groupCount[number]++;
										stack.push(new Node(sumRow, sumCol, node.count + 1));
									}
								}
							}
		
							
						}
						number++;
					}
			}
}
```

- for문을 사용하여 탐색하고, 0이 아니라면 DFS를 사용하여 각 단지의 번호를 부여한다.
- 번호를 부여할때마다 count를 세어 groupCount라는 배열에 저장한다.

```jsx
int[] sortGroup = new int[number];
output.write(String.valueOf(number));
output.newLine();
for(int i = 0; i < number; i++)
{
	sortGroup[i] = groupCount[i];
	if(sortGroup[i] == 0)
	{
		sortGroup[i]++;
	}
}
Arrays.sort(sortGroup);
for(int i = 0; i < number; i++)
{
	output.write(Integer.toString(sortGroup[i]));
	output.newLine();
}

input.close();
output.close();
```

- 카운트 한 배열을 따로 저장하여 sort하고 출력한다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/99675164-3b2caf80-2aba-11eb-9b61-5895381e507c.png](https://user-images.githubusercontent.com/64006699/99675164-3b2caf80-2aba-11eb-9b61-5895381e507c.png)