# 4주차 - 4179. 불

# 1. 문제

[https://www.acmicpc.net/problem/4179](https://www.acmicpc.net/problem/4179)

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
int type;
```

```jsx
public Node(int row, int col, int count, int type) { // type = 1이라면 불
	this.row = row;
	this.col = col;
	this.count = count;
	this.type = type;
}

```

```jsx
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

	String getMap = "";
	String line = input.readLine();

	StringTokenizer st = new StringTokenizer(line, " ");
	int row = Integer.parseInt(st.nextToken());
	int col = Integer.parseInt(st.nextToken());
	int startJRow = 0;
	int startJCol = 0;
	char[][] map = new char[row][col];
	int[][] canGo = new int[row][col];
	int canOut = 0;

	for (int i = 0; i < row; i++) {
		getMap = input.readLine();
		for (int j = 0; j < col; j++) {
			map[i][j] = getMap.charAt(j);
			if (map[i][j] == 'J') {
				startJRow = i;
				startJCol = j;
			} else if (map[i][j] == 'F') {
				queue.add(new Node(i, j, 0, 1));
			}
		}
	}

	queue.add(new Node(startJRow, startJCol, 0, 0));

	while (!queue.isEmpty()) {
		Node node = queue.poll();
		if (node.type == 1) // 불일 때
		{
			if (node.row - 1 >= 0) {
				if (map[node.row - 1][node.col] == '.' || map[node.row - 1][node.col] == 'J') { // 위로 이동
					map[node.row - 1][node.col] = 'F';
					queue.add(new Node(node.row - 1, node.col, 0, 1));
				}
			}

			if (node.col - 1 >= 0) {
				if (map[node.row][node.col - 1] == '.' || map[node.row][node.col - 1] == 'J') { // 왼쪽 이동
					map[node.row][node.col - 1] = 'F';
					queue.add(new Node(node.row, node.col - 1, 0, 1));
				}
			}
			if (node.row + 1 <= row - 1) {
				if (map[node.row + 1][node.col] == '.' || map[node.row + 1][node.col] == 'J') { // 아래로 이동
					map[node.row + 1][node.col] = 'F';
					queue.add(new Node(node.row + 1, node.col, 0, 1));
				}
			}
			if (node.col + 1 <= col - 1) {

				if (map[node.row][node.col + 1] == '.' || map[node.row][node.col + 1] == 'J') { // 오른쪽 이동
					map[node.row][node.col + 1] = 'F';
					queue.add(new Node(node.row, node.col + 1, 0, 1));
				}
			}

		} else { // 지훈일 때
			if (node.row - 1 < 0 || node.row + 1 > row - 1 || node.col - 1 < 0 || node.col + 1 > col - 1) { // 영역
																											// 밖으로
																											// 벗어난
																											// 경우
				output.write(String.valueOf(node.count + 1));
				canOut = 1;
				break;

			} else {

				if (canGo[node.row - 1][node.col] == 0 && map[node.row - 1][node.col] == '.') { // 위로 이동
					canGo[node.row - 1][node.col] = 1;
					queue.add(new Node(node.row - 1, node.col, node.count + 1, 0));
				}
				if (canGo[node.row][node.col - 1] == 0 && map[node.row][node.col - 1] == '.') { // 왼쪽 이동
					canGo[node.row][node.col - 1] = 1;
					queue.add(new Node(node.row, node.col - 1, node.count + 1, 0));
				}
				if (canGo[node.row + 1][node.col] == 0 && map[node.row + 1][node.col] == '.') { // 아래로 이동
					canGo[node.row + 1][node.col] = 1;
					queue.add(new Node(node.row + 1, node.col, node.count + 1, 0));
				}
				if (canGo[node.row][node.col + 1] == 0 && map[node.row][node.col + 1] == '.') { // 오른쪽 이동
					canGo[node.row][node.col + 1] = 1;
					queue.add(new Node(node.row, node.col + 1, node.count + 1, 0));
				}
			}
		}
	}

	if (canOut == 0) {
		output.write("IMPOSSIBLE");
	}

	input.close();
	output.close();
 }
}
```

# 3. 설명

1. 구현 방법
- 이번 문제는 너비 우선 탐색(BFS, Breadth-First Search)을 사용하여 구현했다.

    너비 우션 탐색에 대한 설명은 [https://gmlwjd9405.github.io/2018/08/15/algorithm-bfs.html](https://gmlwjd9405.github.io/2018/08/15/algorithm-bfs.html) 

- 너비 우선 탐색 알고리즘의 이해

![https://user-images.githubusercontent.com/64006699/98973881-a412a580-2557-11eb-8973-0113203b8e32.png](https://user-images.githubusercontent.com/64006699/98973881-a412a580-2557-11eb-8973-0113203b8e32.png)

- 너비 우선 탐색 알고리즘의 기본 구성

![https://user-images.githubusercontent.com/64006699/98974022-c60c2800-2557-11eb-92a6-a6d2c4418653.png](https://user-images.githubusercontent.com/64006699/98974022-c60c2800-2557-11eb-92a6-a6d2c4418653.png)

2.  입력 방법 및 변수 지정

```jsx
class Node {
int row;
int col;
int count;
int type;
```

```jsx
public Node(int row, int col, int count, int type) { // type = 1이라면 불
	this.row = row;
	this.col = col;
	this.count = count;
	this.type = type;
 }
}
```

- row, col, count, type를 변수로 갖는 Node라는 클래스를 작성했다. 이 Node에는 각 지점에 해당하는 행과 열이 들어가고, count값을 넣어 몇번 BFS가 진행했는지 기록한다.
- type변수로 jihoon과 fire를 구분할 수 있다.

```jsx
BufferedReader input = new BufferedReader(new InputStreamReader([System.in](http://system.in/)));
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
Queue<Node> queue = new LinkedList<Node>();
```

- 입력은 BufferedReader, 출력은 BufferedWriter로 입출력 시간을 줄였다.

```jsx
String getMap = "";
String line = input.readLine();

StringTokenizer st = new StringTokenizer(line, " ");
int row = Integer.parseInt(st.nextToken());
int col = Integer.parseInt(st.nextToken());
int startJRow = 0;
int startJCol = 0;
char[][] map = new char[row][col];
int[][] canGo = new int[row][col];
int canOut = 0;
```

- map을 구성하기 위한 **String getMap**
- 한 줄씩 읽기위한 **String line**
- 한 줄을 [3 5] 같은 형식으로 입력받기 때문에 각각의 int값을 뽑아내기위한 **StringTokenizer st**
- 행의 수를 저장하기위한 **int row**
- 열의 수를 저장하기위한 **int col**
- 처음 J의 위치 중, row값을 저장하기 위한 **int startJRow**
- 처음 J의 위치 중, col값을 저장하기 위한 **int startJCol**
- map을 저장하기위한 **char[][] map**
- BFS 탐색을 할 때, 이미 간 경로를 구별하기 위한 **int[][] canGo**
- Jihoon이 탈출 가능한지 알기 위한 **int canOut**

```jsx
for (int i = 0; i < row; i++) {
	getMap = input.readLine();
	for (int j = 0; j < col; j++) {
		map[i][j] = getMap.charAt(j);
		if (map[i][j] == 'J') {
			startJRow = i;
			startJCol = j;
		} else if (map[i][j] == 'F') {
			queue.add(new Node(i, j, 0, 1));
		}
	}
}

queue.add(new Node(startJRow, startJCol, 0, 0));
```

- for문을 사용하여 이차원배열 값들을 저장한다, 저장할 때 불인 경우에는 바로 큐에넣고

      J값이 나오는 경우, 나중에 큐에 넣기위해 좌표값들을 저장해둔 뒤 큐에 넣는다.

```jsx
while (!queue.isEmpty()) {
Node node = queue.poll();
if (node.type == 1) // 불일 때
{
	if (node.row - 1 >= 0) {
		if (map[node.row - 1][node.col] == '.' || map[node.row - 1][node.col] == 'J') { // 위로 이동
			map[node.row - 1][node.col] = 'F';
			queue.add(new Node(node.row - 1, node.col, 0, 1));
		}
	}
```

```jsx
while (!queue.isEmpty()) {
Node node = queue.poll();
if (node.type == 1) // 불일 때
{
	if (node.row - 1 >= 0) {
		if (map[node.row - 1][node.col] == '.' || map[node.row - 1][node.col] == 'J') { // 위로 이동
			map[node.row - 1][node.col] = 'F';
			queue.add(new Node(node.row - 1, node.col, 0, 1));
		}
	}			
	if (node.col - 1 >= 0) {
		if (map[node.row][node.col - 1] == '.' || map[node.row][node.col - 1] == 'J') { // 왼쪽 이동
			map[node.row][node.col - 1] = 'F';
			queue.add(new Node(node.row, node.col - 1, 0, 1));
			}
		}
	if (node.row + 1 <= row - 1) {
		if (map[node.row + 1][node.col] == '.' || map[node.row + 1][node.col] == 'J') { // 아래로 이동
			map[node.row + 1][node.col] = 'F';
			queue.add(new Node(node.row + 1, node.col, 0, 1));
			}
		}
	if (node.col + 1 <= col - 1) {
		if (map[node.row][node.col + 1] == '.' || map[node.row][node.col + 1] == 'J') { // 오른쪽 이동
			map[node.row][node.col + 1] = 'F';
			queue.add(new Node(node.row, node.col + 1, 0, 1));
			}
		}
	} else { // 지훈일 때
			if (node.row - 1 < 0 || node.row + 1 > row - 1 || node.col - 1 < 0 || node.col + 1 > col - 1) { // 영역
																											// 밖으로
																											// 벗어난
																											// 경우
				output.write(String.valueOf(node.count + 1));
				canOut = 1;
				break;

			} else {

				if (canGo[node.row - 1][node.col] == 0 && map[node.row - 1][node.col] == '.') { // 위로 이동
					canGo[node.row - 1][node.col] = 1;
					queue.add(new Node(node.row - 1, node.col, node.count + 1, 0));
				}
				if (canGo[node.row][node.col - 1] == 0 && map[node.row][node.col - 1] == '.') { // 왼쪽 이동
					canGo[node.row][node.col - 1] = 1;
					queue.add(new Node(node.row, node.col - 1, node.count + 1, 0));
				}
				if (canGo[node.row + 1][node.col] == 0 && map[node.row + 1][node.col] == '.') { // 아래로 이동
					canGo[node.row + 1][node.col] = 1;
					queue.add(new Node(node.row + 1, node.col, node.count + 1, 0));
				}
				if (canGo[node.row][node.col + 1] == 0 && map[node.row][node.col + 1] == '.') { // 오른쪽 이동
					canGo[node.row][node.col + 1] = 1;
					queue.add(new Node(node.row, node.col + 1, node.count + 1, 0));
				}
			}
		}
	}
```

- while문을 사용하여 큐가 비워질 때까지 BFS 탐색을 진행한다.
- type 변수로 불인경우와 지훈이인 경우를 구분하여 BFS탐색을 한다.
- 불인 경우에는 '.' , 'J' 인 경우에만 확산이 가능하므로, if문으로 판단하여 큐에 넣는다.
- 지훈이인 경우에는 맵 밖으로 나가면 탈출을 하므로,

       if문으로 판단하여 canOut변수를 1로만들고 count를 출력한 후, 반복문을 탈출한다.

```jsx
if (canOut == 0) {
	output.write("IMPOSSIBLE");
}

input.close();
output.close();
```

- 마지막에 canOut 변수가 0이라면 탈출불가능한 상황이므로 "IMPOSSIBLE"을 출력한다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/98976220-6f541d80-255a-11eb-8ed0-d77300bfe946.png](https://user-images.githubusercontent.com/64006699/98976220-6f541d80-255a-11eb-8ed0-d77300bfe946.png)