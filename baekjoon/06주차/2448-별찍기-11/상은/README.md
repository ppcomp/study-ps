# 6주차 - 2448. 별 찍기 - 11

# 1. 문제

[https://www.acmicpc.net/problem/2448](https://www.acmicpc.net/problem/2448)

# 2. 코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
```

```java
class Node{
int row;
int col;

	public Node(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
}
```

```java
public class Main {
```

```java
public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub

	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	int num = Integer.parseInt(input.readLine());
	
	char[][] array = new char[num][num*2-1]; 
	
	for(int i = 0; i < num; i++)
	{
		for(int j = 0; j < num*2-1; j++)
		{
			array[i][j] = '0';
		}
	}
	
	Deque<Node> queue = new LinkedList<Node>();
	
	queue.add(new Node(0, num-1));
	array[0][num-1]++;
	
	while(!queue.isEmpty()) // 삼각형별로 맨위 꼭짓점 찾기
	{
		Node temp = queue.poll();
		if(temp.row + 3 < num)
		{
			Node leftNode = new Node(temp.row+3, temp.col-3);
			array[leftNode.row][leftNode.col]++;
			Node rightNode = new Node(temp.row+3, temp.col+3);
			array[rightNode.row][rightNode.col]++;
			if(array[leftNode.row][leftNode.col] == '1') // 중복 제거
			{
				queue.add(leftNode);
				queue.add(rightNode);
			}else
			{
				queue.removeLast();
				queue.add(rightNode);
			}
		}
	}
	
	for(int i = 0; i < num; i++)
	{
		for(int j = 0; j < num*2-1; j++)
		{
			if(array[i][j] == '1')
			{
				array[i][j] = '*';
				array[i+1][j-1] = '*';
				array[i+1][j+1] = '*';
				for(int k = j-2; k <= j+2; k++)
				{
					array[i+2][k]='*';
				}
			}

			output.write(array[i][j] == '*' ? '*' : ' ');
			
		}
		output.newLine();
	}
	
	input.close();
	output.close();
	
	}
}

```

# 3. 설명

1. 구현 방법
- BFS로 먼저 각 삼각형들의 꼭짓점을 찍은 후, 꼭짓점마다 삼각형을 생성해주며 구현했다.

2.  입력 방법 및 변수 지정

```java
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	int num = Integer.parseInt(input.readLine());
	
	char[][] array = new char[num][num*2-1]; 
	
	for(int i = 0; i < num; i++)
	{
		for(int j = 0; j < num*2-1; j++)
		{
			array[i][j] = '0';
		}
	}
	
	Deque<Node> queue = new LinkedList<Node>();
	
	queue.add(new Node(0, num-1));
	array[0][num-1]++;
```

- 처음에 array를 받은 num에 따라 동적으로 할당해준 후, 모두 0의 값을 갖도록 초기화한다.
- deque를 사용하여 구현했다.
- 처음에 queue에 넣은 후, 맨 위 꼭짓점을 1로 만들어주었다.

```java
	while(!queue.isEmpty()) // 삼각형별로 맨위 꼭짓점 찾기
	{
		Node temp = queue.poll();
		if(temp.row + 3 < num)
		{
			Node leftNode = new Node(temp.row+3, temp.col-3);
			array[leftNode.row][leftNode.col]++;
			Node rightNode = new Node(temp.row+3, temp.col+3);
			array[rightNode.row][rightNode.col]++;
			if(array[leftNode.row][leftNode.col] == '1') // 중복 제거
			{
				queue.add(leftNode);
				queue.add(rightNode);
			}else
			{
				queue.removeLast();
				queue.add(rightNode);
			}
		}
	}
```

- BFS로 삼각형마다 맨 위 꼭짓점을 1로 만들어준다

![https://user-images.githubusercontent.com/64006699/100868265-bccf0500-34de-11eb-9afc-20236d7d3b3d.png](https://user-images.githubusercontent.com/64006699/100868265-bccf0500-34de-11eb-9afc-20236d7d3b3d.png)

- 예시로 N이 24인 경우 이런식으로 맨 위 꼭짓점마다 점이 찍힌다.

![https://user-images.githubusercontent.com/64006699/100868510-0d466280-34df-11eb-9259-1d752f7f5c44.png](https://user-images.githubusercontent.com/64006699/100868510-0d466280-34df-11eb-9259-1d752f7f5c44.png)

- 각 꼭짓점을 queue에 넣을때마다 count를 1씩 증가하여 처음 queue에 넣어진 경우만 거른다.
- 위의 예시에서 2번째 줄에 있는 삼각형들의 참조에의하여 3번째 줄에 가운데는 2번참조된다.
- 이런식으로 2번 참조되는삼각형은 큐에 넣지않고 진행한다.

```java
for(int i = 0; i < num; i++)
	{
		for(int j = 0; j < num*2-1; j++)
		{
			if(array[i][j] == '1')
			{
				array[i][j] = '*';
				array[i+1][j-1] = '*';
				array[i+1][j+1] = '*';
				for(int k = j-2; k <= j+2; k++)
				{
					array[i+2][k]='*';
				}
			}

			output.write(array[i][j] == '*' ? '*' : ' ');
			
		}
		output.newLine();
	}
```

- 이제 한번 참조된 경우에만 해당 꼭짓점을 통하여 삼각형을 그린다.

![https://user-images.githubusercontent.com/64006699/100868109-81343b00-34de-11eb-8453-bf0852434c2b.png](https://user-images.githubusercontent.com/64006699/100868109-81343b00-34de-11eb-8453-bf0852434c2b.png)

- 삼각형을 그린 결과

![https://user-images.githubusercontent.com/64006699/100869124-e9cfe780-34df-11eb-8b6e-fd494d1b03dd.png](https://user-images.githubusercontent.com/64006699/100869124-e9cfe780-34df-11eb-8b6e-fd494d1b03dd.png)

- 처음에 HashSet을 사용하여 중복된 꼭짓점을 거르려고했는데 무슨 일인지 계속 틀렸습니다가 나왔다. 이클립스에서는 잘 돌아갔는데 아무래도 HashSet에 엄청난 데이터가 들어가 무슨 에러가 난 것 같았다. 그래서 후에 count를 1씩 더해주는 방식으로 풀었더니 해결이 되었다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/100868918-9f4e6b00-34df-11eb-894b-9f2a7694ac96.png](https://user-images.githubusercontent.com/64006699/100868918-9f4e6b00-34df-11eb-894b-9f2a7694ac96.png)