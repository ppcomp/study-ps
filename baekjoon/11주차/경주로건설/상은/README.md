# 11주차 - 경주로 건설

# 1. 문제

[https://programmers.co.kr/learn/courses/30/lessons/67259](https://programmers.co.kr/learn/courses/30/lessons/67259)

# 2. 코드

```java
class Node
{
	int row;
	int col;
	int cost;
	String direction;

	public Node(int row, int col, int cost, String direction)
	{
		this.row = row;
		this.col = col;
		this.cost = cost;
		this.direction = direction;
	}
}

public class Main
{
	static int answer = 9999999;

	public static int solution(int[][] board)
	{
		Queue<Node> queue = new LinkedList<Node>();
		int newCost = 0;

		if (board[1][0] != 1)
		{
			queue.add(new Node(1, 0, 100, "S"));
		}
		if (board[0][1] != 1)
		{
			queue.add(new Node(0, 1, 100, "E"));
		}

		board[0][0] = 1;

		while (!queue.isEmpty())
		{
			Node node = queue.poll();

			if (node.row == board.length - 1 && node.col == board.length - 1)
			{ // 도착
				answer = Math.min(answer, node.cost);
				continue;
			}

			if (node.row - 1 >= 0 && board[node.row - 1][node.col] != 1)
			{ // 위로 이동
				if (node.direction == "N") // 방향 확인 -> 전 방향이 N인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row - 1][node.col] == 0) // 처음 가보는 경우
				{
					board[node.row - 1][node.col] = newCost;
					queue.add(new Node(node.row - 1, node.col, newCost, "N"));

				} else if (board[node.row - 1][node.col] >= newCost)
				{
					board[node.row - 1][node.col] = newCost;
					queue.add(new Node(node.row - 1, node.col, newCost, "N"));
				}

			}

			if (node.col - 1 >= 0 && board[node.row][node.col - 1] != 1)
			{ // 왼쪽 이동

				if (node.direction == "W") // 방향 확인 -> 전 방향이 W인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row][node.col - 1] == 0) // 처음 가보는 경우
				{
					board[node.row][node.col - 1] = newCost;
					queue.add(new Node(node.row, node.col - 1, newCost, "W"));

				} else if (board[node.row][node.col - 1] >= newCost)
				{
					board[node.row][node.col - 1] = newCost;
					queue.add(new Node(node.row, node.col - 1, newCost, "W"));
				}

			}
			if (node.row + 1 < board.length && board[node.row + 1][node.col] != 1)
			{ // 아래로 이동, 벽 확인

				if (node.direction == "S") // 방향 확인 -> 전 방향이 S인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row + 1][node.col] == 0) // 처음 가보는 경우
				{
					board[node.row + 1][node.col] = newCost;
					queue.add(new Node(node.row + 1, node.col, newCost, "S"));

				} else if (board[node.row + 1][node.col] >= newCost) // 만약 newCost가 저장되어 있는 값보다 작다면
				{
					board[node.row + 1][node.col] = newCost;
					queue.add(new Node(node.row + 1, node.col, newCost, "S"));
				}

			}
			if (node.col + 1 < board.length && board[node.row][node.col + 1] != 1)
			{ // 오른쪽으로 이동, 벽 확인

				if (node.direction == "E") // 방향 확인 -> 전 방향이 E인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row][node.col + 1] == 0) // 처음 가보는 경우
				{
					board[node.row][node.col + 1] = newCost;
					queue.add(new Node(node.row, node.col + 1, newCost, "E"));

				} else if (board[node.row][node.col + 1] >= newCost)
				{
					board[node.row][node.col + 1] = newCost;
					queue.add(new Node(node.row, node.col + 1, newCost, "E"));
				}

			}

		}

		return answer;
	}
```

# 3. 설명

1. 구현 방법
- BFS를 사용하여 구현

2.  입력 방법 및 변수 지정

```java
class Node
{
	int row;
	int col;
	int cost;
	String direction;

	public Node(int row, int col, int cost, String direction)
	{
		this.row = row;
		this.col = col;
		this.cost = cost;
		this.direction = direction;
	}
}
```

- 좌표와, 그 좌표까지의 비용, 전방향을 담는 Node

```java
Queue<Node> queue = new LinkedList<Node>();
int newCost = 0;

if (board[1][0] != 1)
{
	queue.add(new Node(1, 0, 100, "S"));
}
if (board[0][1] != 1)
{
	queue.add(new Node(0, 1, 100, "E"));
}

board[0][0] = 1;
```

- 처음에 밑방향, 오른쪽 방향으로 움직이는것은 고정이므로 queue에 벽이 아닐경우에만 넣어줌

   → if문 처음에 안썼다가 겁나 틀림

```java
		while (!queue.isEmpty())
		{
			Node node = queue.poll();

			if (node.row == board.length - 1 && node.col == board.length - 1)
			{ // 도착
				answer = Math.min(answer, node.cost);
				continue;
			}

			if (node.row - 1 >= 0 && board[node.row - 1][node.col] != 1)
			{ // 위로 이동
				if (node.direction == "N") // 방향 확인 -> 전 방향이 N인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row - 1][node.col] == 0) // 처음 가보는 경우
				{
					board[node.row - 1][node.col] = newCost;
					queue.add(new Node(node.row - 1, node.col, newCost, "N"));

				} else if (board[node.row - 1][node.col] >= newCost)
				{
					board[node.row - 1][node.col] = newCost;
					queue.add(new Node(node.row - 1, node.col, newCost, "N"));
				}

			}

			if (node.col - 1 >= 0 && board[node.row][node.col - 1] != 1)
			{ // 왼쪽 이동

				if (node.direction == "W") // 방향 확인 -> 전 방향이 W인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row][node.col - 1] == 0) // 처음 가보는 경우
				{
					board[node.row][node.col - 1] = newCost;
					queue.add(new Node(node.row, node.col - 1, newCost, "W"));

				} else if (board[node.row][node.col - 1] >= newCost)
				{
					board[node.row][node.col - 1] = newCost;
					queue.add(new Node(node.row, node.col - 1, newCost, "W"));
				}

			}
			if (node.row + 1 < board.length && board[node.row + 1][node.col] != 1)
			{ // 아래로 이동, 벽 확인

				if (node.direction == "S") // 방향 확인 -> 전 방향이 S인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row + 1][node.col] == 0) // 처음 가보는 경우
				{
					board[node.row + 1][node.col] = newCost;
					queue.add(new Node(node.row + 1, node.col, newCost, "S"));

				} else if (board[node.row + 1][node.col] >= newCost) // 만약 newCost가 저장되어 있는 값보다 작다면
				{
					board[node.row + 1][node.col] = newCost;
					queue.add(new Node(node.row + 1, node.col, newCost, "S"));
				}

			}
			if (node.col + 1 < board.length && board[node.row][node.col + 1] != 1)
			{ // 오른쪽으로 이동, 벽 확인

				if (node.direction == "E") // 방향 확인 -> 전 방향이 E인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row][node.col + 1] == 0) // 처음 가보는 경우
				{
					board[node.row][node.col + 1] = newCost;
					queue.add(new Node(node.row, node.col + 1, newCost, "E"));

				} else if (board[node.row][node.col + 1] >= newCost)
				{
					board[node.row][node.col + 1] = newCost;
					queue.add(new Node(node.row, node.col + 1, newCost, "E"));
				}

			}

		}
```

- 전에 백준에서 풀던 BFS와 비슷함
- 방향을 기준으로 전방향과 나아가는 방향이 같으면 COST에 100을 더하고 아니라면 600을 더함
- 처음 가보는 경우와 두번째로 가보는 경우를 비교하여 더 낮은 값이 있다면 그 값을 queue에 넣음

# 4. 결과

![https://user-images.githubusercontent.com/64006699/103485506-2756c600-4e3a-11eb-8424-e549121b67fa.png](https://user-images.githubusercontent.com/64006699/103485506-2756c600-4e3a-11eb-8424-e549121b67fa.png)