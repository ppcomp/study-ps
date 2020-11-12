# 4주차 - 7576. 토마토

# 1. 문제

[https://www.acmicpc.net/problem/7576](https://www.acmicpc.net/problem/7576)

# 2. 코드

```jsx
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
```

```jsx
class Node {
int row;
int col;
int count;
```

```
public Node(int row, int col, int count) { // type = 1이라면 불
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
```

```jsx
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	Queue<Node> queue = new LinkedList<Node>();

	String line = input.readLine();
	String getMap = "";
	int canAll = 0; // 다 익게 할 수 있는지에 대한 변수
	int firstAll = 0; // 처음부터 다 익은것인지 판단하기위한 변수

	StringTokenizer st = new StringTokenizer(line, " ");
	int col = Integer.parseInt(st.nextToken());
	int row = Integer.parseInt(st.nextToken());

	Node lastNode = new Node(0, 0, 0);

	int[][] map = new int[row][col];
	int[][] canGo = new int[row][col];

	for (int i = 0; i < row; i++) {
		getMap = input.readLine();
		st = new StringTokenizer(getMap, " ");
		for (int j = 0; j < col; j++) {
			map[i][j] = Integer.parseInt(st.nextToken());
			if (map[i][j] == 1) {
				queue.add(new Node(i, j, 0));
			}
			if (map[i][j] == 0) {
				firstAll = 1;
			}
		}
	}

	while (!queue.isEmpty()) {
		Node node = queue.poll();
		lastNode = node;

		if (node.row - 1 >= 0) {
			if (canGo[node.row - 1][node.col] == 0 && map[node.row - 1][node.col] != -1) // 위로 이동
			{
				map[node.row - 1][node.col] = 1;
				canGo[node.row - 1][node.col] = 1;
				queue.add(new Node(node.row - 1, node.col, node.count + 1));
			}
		}

		if (node.col - 1 >= 0) {
			if (canGo[node.row][node.col - 1] == 0 && map[node.row][node.col - 1] != -1) // 왼쪽으로 이동
			{
				map[node.row][node.col - 1] = 1;
				canGo[node.row][node.col - 1] = 1;
				queue.add(new Node(node.row, node.col - 1, node.count + 1));
			}
		}

		if (node.col + 1 <= col - 1) {
			if (canGo[node.row][node.col + 1] == 0 && map[node.row][node.col + 1] != -1) // 왼쪽으로 이동
			{
				map[node.row][node.col + 1] = 1;
				canGo[node.row][node.col + 1] = 1;
				queue.add(new Node(node.row, node.col + 1, node.count + 1));
			}
		}

		if (node.row + 1 <= row - 1) {
			if (canGo[node.row + 1][node.col] == 0 && map[node.row + 1][node.col] != -1) // 오른쪽으로 이동
			{
				map[node.row + 1][node.col] = 1;
				canGo[node.row + 1][node.col] = 1;
				queue.add(new Node(node.row + 1, node.col, node.count + 1));
			}
		}

	}

	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			if (map[i][j] == 0) {
				canAll = 1;
				break;
			}
		}
	}

	if (firstAll == 0) { // 애초에 토마토가 다 익어있는 경우
		output.write(String.valueOf(0));
	} else {
		if (canAll == 0) { // 다 익게 할 수 있는경우
			output.write(String.valueOf(lastNode.count));
		} else // 다 익게 못하는 경우
		{
			output.write(String.valueOf(-1));
		}
	}

	input.close();
	output.close();

 }
}
```

# 3. 설명

1. 구현 방법
- 이번 문제는 BFS를 사용하여 구현했다.
- 방법은 4179. 불([https://www.acmicpc.net/problem/4179](https://www.acmicpc.net/problem/4179))과 비슷하다.

2.  입력 방법 및 변수 지정

```jsx
class Node {
int row;
int col;
int count;

```

```jsx
public Node(int row, int col, int count, int type) {
	this.row = row;
	this.col = col;
	this.count = count;
 }
}
```

- row, col, count, type를 변수로 갖는 Node라는 클래스를 작성했다. 이 Node에는 각 지점에 해당하는 행과 열이 들어가고, count값을 넣어 몇번 BFS가 진행했는지 기록한다.

```jsx
BufferedReader input = new BufferedReader(new InputStreamReader([System.in](http://system.in/)));
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
Queue<Node> queue = new LinkedList<Node>();
```

- 입력은 BufferedReader, 출력은 BufferedWriter로 입출력 시간을 줄였다.

```jsx
String line = input.readLine();
String getMap = "";
int canAll = 0; // 다 익게 할 수 있는지에 대한 변수
int firstAll = 0; // 처음부터 다 익은것인지 판단하기위한 변수
StringTokenizer st = new StringTokenizer(line, " ");
int col = Integer.parseInt(st.nextToken());
int row = Integer.parseInt(st.nextToken());
Node lastNode = new Node(0, 0, 0);
int[][] map = new int[row][col];
int[][] canGo = new int[row][col];
```

- 한 줄씩 읽기위한 **String line**
- map을 구성하기 위한 **String getMap**
- 다 익게 할 수 있는지 판단하기 위한 변수 **int canAll**
- 처음부터 다 익은것인지 판단하기 위한 변수 **int firstAll**
- 띄어쓰기를 제거하기위한 **StringTokenizer st**
- 행의 수를 저장하기위한 **int row**
- 열의 수를 저장하기위한 **int col**
- 마지막 노드 값을 저장하여 count값을 알기 위한 **Node lastNode**
- map을 저장하기위한 **int [][] map**
- BFS 탐색을 할 때, 이미 간 경로를 구별하기 위한 **int[][] canGo**

```jsx
for (int i = 0; i < row; i++) {
	getMap = input.readLine();
	st = new StringTokenizer(getMap, " ");
	for (int j = 0; j < col; j++) {
		map[i][j] = Integer.parseInt(st.nextToken());
		if (map[i][j] == 1) {
			queue.add(new Node(i, j, 0));
		}
		if (map[i][j] == 0) {
			firstAll = 1;
		}
	}
}
```

- for문을 사용하여 이차원배열 값들을 저장한다

      입력값 중간 중간마다 공백이 있기에 StringTokenizer로 토큰을 뽑으며 저장한다.

- 다 익은 토마토 좌표를 찾아낼 시, queue에 추가한다.
- 처음부터 안익은게 없는 경우를 구별하기위해 firstAll에 안익은 토마토가 있을시 1, 없을 시 0을 저장한다.

```jsx
while (!queue.isEmpty()) {
	Node node = queue.poll();
	lastNode = node;

	if (node.row - 1 >= 0) {
			if (canGo[node.row - 1][node.col] == 0 && map[node.row - 1][node.col] != -1) // 위로 이동
			{
				map[node.row - 1][node.col] = 1;
				canGo[node.row - 1][node.col] = 1;
				queue.add(new Node(node.row - 1, node.col, node.count + 1));
			}
		}

		if (node.col - 1 >= 0) {
			if (canGo[node.row][node.col - 1] == 0 && map[node.row][node.col - 1] != -1) // 왼쪽으로 이동
			{
				map[node.row][node.col - 1] = 1;
				canGo[node.row][node.col - 1] = 1;
				queue.add(new Node(node.row, node.col - 1, node.count + 1));
			}
		}

		if (node.col + 1 <= col - 1) {
			if (canGo[node.row][node.col + 1] == 0 && map[node.row][node.col + 1] != -1) // 왼쪽으로 이동
			{
				map[node.row][node.col + 1] = 1;
				canGo[node.row][node.col + 1] = 1;
				queue.add(new Node(node.row, node.col + 1, node.count + 1));
			}
		}

		if (node.row + 1 <= row - 1) {
			if (canGo[node.row + 1][node.col] == 0 && map[node.row + 1][node.col] != -1) // 오른쪽으로 이동
			{
				map[node.row + 1][node.col] = 1;
				canGo[node.row + 1][node.col] = 1;
				queue.add(new Node(node.row + 1, node.col, node.count + 1));
			}
		}
	}
```

- while문을 사용하여 큐가 비워질 때까지 BFS 탐색을 진행한다.
- 큐에 넣을때마다 0값을 1로 바꾸는 형식으로 진행하고, 맵을 안넘어가는 것을 기준으로 BFS 탐색
- 항상 큐에서 값들을 뺄때마다 lastNode에 저장하여, 마지막 진행 시 count값을 파악한다.

```jsx
for (int i = 0; i < row; i++) {
	for (int j = 0; j < col; j++) {
		if (map[i][j] == 0) {
			canAll = 1;
			break;
		}
	}
}
```

- map을 하나하나씩 탐색하며 안익은 토마토가 있을경우에 canAll값을 1로 변경한다.

```jsx
if (firstAll == 0) { // 애초에 토마토가 다 익어있는 경우
	output.write(String.valueOf(0));
	} else {
		if (canAll == 0) { // 다 익게 할 수 있는경우
			output.write(String.valueOf(lastNode.count));
		} else // 다 익게 못하는 경우
		{
			output.write(String.valueOf(-1));
		}
}

	input.close();
	output.close();
```

- 애초부터 토마토가 다 익어있는 경우에는 1출력
- 판단 후, 다 익게 할 수 있는 경우에는 lastNode값의 count 출력
- 다 익게 못하는 경우 -1 출력

# 4. 결과

![https://user-images.githubusercontent.com/64006699/98978908-f8208880-255d-11eb-9609-9fa247df039c.png](https://user-images.githubusercontent.com/64006699/98978908-f8208880-255d-11eb-9609-9fa247df039c.png)