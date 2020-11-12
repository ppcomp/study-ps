# 4주차 - 2178. 미로 탐색

# 1. 문제

[https://www.acmicpc.net/problem/2178](https://www.acmicpc.net/problem/2178)

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

```jsx
public Node(int row, int col, int count) {
	this.row = row;
	this.col = col;
	this.count = count;
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
	Queue<Node> queue = new LinkedList<Node>();

	String line = input.readLine();
	String getMap = "";

	StringTokenizer st = new StringTokenizer(line, " ");
	int row = Integer.parseInt(st.nextToken());
	int col = Integer.parseInt(st.nextToken());

	int[][] map = new int[row][col];
	int[][] canGo = new int[row][col];

	for (int i = 0; i < row; i++) {
		getMap = input.readLine();
		for (int j = 0; j < col; j++) {
			map[i][j] = getMap.charAt(j) - '0';
		}
	}

	queue.add(new Node(0, 0, 1));

	while (!queue.isEmpty()) {
		Node node = queue.poll();
		if (node.row == row - 1 && node.col == col - 1) { // 도착
			output.write(String.valueOf(node.count));
		} else {
			if (node.row - 1 >= 0) {
				if (map[node.row - 1][node.col] == 1 && canGo[node.row - 1][node.col] == 0) { // 위로 이동
					canGo[node.row - 1][node.col] = -1;
					queue.add(new Node(node.row - 1, node.col, node.count + 1));
				}
			}
			if (node.col - 1 >= 0) {
				if (map[node.row][node.col - 1] == 1 && canGo[node.row][node.col - 1] == 0) { // 왼쪽으로 이동
					canGo[node.row][node.col - 1] = -1;
					queue.add(new Node(node.row, node.col - 1, node.count + 1));
				}
			}
			if (node.row + 1 <= row - 1) {
				if (map[node.row + 1][node.col] == 1 && canGo[node.row + 1][node.col] == 0) { // 아래로 이동
					canGo[node.row + 1][node.col] = -1;
					queue.add(new Node(node.row + 1, node.col, node.count + 1));
				}
			}
			if (node.col + 1 <= col - 1) {
				if (map[node.row][node.col + 1] == 1 && canGo[node.row][node.col + 1] == 0) { // 오른쪽 이동
					canGo[node.row][node.col + 1] = -1;
					queue.add(new Node(node.row, node.col + 1, node.count + 1));
				}
			}
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
StringTokenizer st = new StringTokenizer(line, " ");
int row = Integer.parseInt(st.nextToken());
int col = Integer.parseInt(st.nextToken());
int[][] map = new int[row][col];
int[][] canGo = new int[row][col];
```

- 한 줄씩 읽기위한 **String line**
- map을 구성하기 위한 **String getMap**
- 한 줄을 [3 5] 같은 형식으로 입력받기 때문에 각각의 int값을 뽑아내기위한 **StringTokenizer st**
- 행의 수를 저장하기위한 **int row**
- 열의 수를 저장하기위한 **int col**
- map을 저장하기위한 **int [][] map**
- BFS 탐색을 할 때, 이미 간 경로를 구별하기 위한 **int[][] canGo**

```jsx
for (int i = 0; i < row; i++) {
	getMap = input.readLine();
	for (int j = 0; j < col; j++) {
		map[i][j] = getMap.charAt(j) - '0';
	}
}

queue.add(new Node(0, 0, 1));
```

- for문을 사용하여 이차원배열 값들을 저장한다

      저장할 때는 int값으로 저장하기 위해 char '0'값을 빼서 저장한다.

- queue에 첫 시작 좌표를 넣고 count는 1로 저장한다.

```jsx
while (!queue.isEmpty()) {
	Node node = queue.poll();
	if (node.row == row - 1 && node.col == col - 1) { // 도착
		output.write(String.valueOf(node.count));
	} else {
			if (node.row - 1 >= 0) {
				if (map[node.row - 1][node.col] == 1 && canGo[node.row - 1][node.col] == 0) { // 위로 이동
					canGo[node.row - 1][node.col] = -1;
					queue.add(new Node(node.row - 1, node.col, node.count + 1));
				}
		}

			if (node.col - 1 >= 0) {
				if (map[node.row][node.col - 1] == 1 && canGo[node.row][node.col - 1] == 0) { // 왼쪽으로 이동
					canGo[node.row][node.col - 1] = -1;
					queue.add(new Node(node.row, node.col - 1, node.count + 1));
				}
		}
			if (node.row + 1 <= row - 1) {
				if (map[node.row + 1][node.col] == 1 && canGo[node.row + 1][node.col] == 0) { // 아래로 이동
					canGo[node.row + 1][node.col] = -1;
					queue.add(new Node(node.row + 1, node.col, node.count + 1));
				}
		}
			if (node.col + 1 <= col - 1) {
				if (map[node.row][node.col + 1] == 1 && canGo[node.row][node.col + 1] == 0) { // 오른쪽 이동
					canGo[node.row][node.col + 1] = -1;
					queue.add(new Node(node.row, node.col + 1, node.count + 1));
				}
		}
	}
 }

	input.close();
	output.close();
}
```

- while문을 사용하여 큐가 비워질 때까지 BFS 탐색을 진행한다.
- (1,1)지점부터 (row, col) 지점까지 탐색을 진행하기때문에, (row, col)지점이 나온다면 count를

       출력 후 코드를 종료한다. 

# 4. 결과

![https://user-images.githubusercontent.com/64006699/98976850-349eb500-255b-11eb-82b3-fe9c387629af.png](https://user-images.githubusercontent.com/64006699/98976850-349eb500-255b-11eb-82b3-fe9c387629af.png)