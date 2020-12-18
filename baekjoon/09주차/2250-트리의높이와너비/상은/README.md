# 9주차 - 2250. 트리의 높이와 너비

# 1. 문제

[https://www.acmicpc.net/problem/2250](https://www.acmicpc.net/problem/2250)

# 2. 코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
```

```java
class Node{
int num;
int left;
int right;
```

```java
 Node(int num, int left, int right)
 {
	this.num = num;
	this.left = left;
	this.right = right;
 }
}
```

```java
public class Main
{
```

```java
static Node[] array;
static int x = 0;
static int leftHeightMin[] = new int[50];
static int rightHeightMax[] = new int[50];
static int allHeight = 0;
public static void dfs(int node, int left, int right, int height) // 중위순회
{
	if(left != -1)
	{
		dfs(array[left].num, array[left].left, array[left].right, height+1);
	}
	
	x++;
	
	if(leftHeightMin[height] == 0) // 높이별 맨 왼쪽노드 구하기
	{
		leftHeightMin[height] = x;
	}
	else
	{
		leftHeightMin[height] = Math.min(leftHeightMin[height], x);
	}
	
	if(rightHeightMax[height] == 0) // 높이별 맨 오른쪽노드 구하기
	{
		rightHeightMax[height] = x;
	}
	else
	{
		rightHeightMax[height] = Math.max(rightHeightMax[height], x);
	}
	
	
	if(right != -1)
	{
		dfs(array[right].num, array[right].left, array[right].right, height+1);
	}
	
	
	if(height > allHeight)
	{
		allHeight = height;
	}
}
public static void main(String[] args) throws IOException
{
	// TODO Auto-generated method stub

	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	String line = input.readLine();
	int n = Integer.parseInt(line);
	int count = 1;
	int[] check = new int[n+1];
	array = new Node[n+1];
	int rootNode = 0;
	int max = 0;
	int maxI = 0;
	
	while(count <= n)
	{
		line = input.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		int node = Integer.parseInt(st.nextToken());
		int left = Integer.parseInt(st.nextToken());
		int right = Integer.parseInt(st.nextToken());
		
		if(left != -1) // 좌측 자식노드가 있는 경우
		{
			check[left]++;
			
		}
		if(right != -1) // 우측 자식노드가 있는 경우
		{
			check[right]++;
		}
		
		if(left == -1 && right == -1 && n != 1) // 자식 노드가 없는 경우 -> 루트 노드 빼고 다 -999가 됨
		{
			check[node] = -999;
		}
		array[node] = new Node(node, left, right);
		
		
		count++;
	}
	
	for(int i = 1 ; i < n+1; i++)
	{
		if(check[i] == 0)
		{
			rootNode = i; // 루트노드 찾음
		}
	}
	
	dfs(rootNode, array[rootNode].left, array[rootNode].right, 0);
	
	for(int i = 0; i <= allHeight; i++)
	{
		if(leftHeightMin[i] != 0 && rightHeightMax[i] != 0) // 값이 있는경우에만
		{
			if((rightHeightMax[i] - leftHeightMin[i] + 1) > max)
			{
				max = rightHeightMax[i] - leftHeightMin[i] + 1;
				maxI = i+1;
			}
		}
	}
	
	output.write(maxI + " " + max);
	
	input.close();
	output.close();
 }
}

```

# 3. 설명

1. 구현 방법
- dfs를 사용하여 중위순회로 해결하였다.
- 순회개념 [https://m.blog.naver.com/rlakk11/60159303809](https://m.blog.naver.com/rlakk11/60159303809)

2.  입력 방법 및 변수 지정

```java
class Node{
int num;
int left;
int right;
 Node(int num, int left, int right)
 {
	this.num = num;
	this.left = left;
	this.right = right;
 }
}
```

- Node라는 클래스로 각 노드간 자신의값, 왼쪽 자식노드의 값, 오른쪽 자식노드의 값을 저장한다.

```java
static Node[] array;
static int x = 0;
static int leftHeightMin[] = new int[50];
static int rightHeightMax[] = new int[50];
static int allHeight = 0;
```

- 전역변수에 대한 설명이다.
- array[] = 각 노드값들을 저장한 배열
- x : 각 노드의 x의 값을 세기위한 변수
- leftHeightMin[] : 각 레벨별 제일 좌측에 있는 노드의 x값을 저장한 배열, 제일 좌측이므로 최소
- rightHeightMax[] : 각 레벨별 제일 우측에 있는 노드의 x값을 저장한 배열, 제일 우측이므로 최대
- allHeight : 전위순회를 하면서 트리의 높이가 몇까지 있는지를 저장하는값, 마지막에 쓰임

```java
if(left != -1) // 좌측 자식노드가 있는 경우
{
	check[left]++;		
}
if(right != -1) // 우측 자식노드가 있는 경우
{
	check[right]++;
}
		
if(left == -1 && right == -1 && n != 1) // 자식 노드가 없는 경우 -> 루트 노드 빼고 다 -999가 됨
{
	check[node] = -999;
}

for(int i = 1 ; i < n+1; i++)
{
	if(check[i] == 0)
	{
		rootNode = i; // 루트노드 찾음
	}
}
```

- 처음 풀었을 때, 루트가 무조건 1이라고 판단하였지만, 무조건 1이 아님
- 그렇기때문에 루트노드를 찾기위한 코드를 적용
- 자식노드를 접근할때마다 자식노드값 ++
- 자식노드 없는경우는 단일노드이므로 -999
- 즉 마지막에 루트노드 빼고 모두 값이 있게됨

![https://user-images.githubusercontent.com/64006699/102392285-3821d200-401a-11eb-8a91-573a62a9cf2f.png](https://user-images.githubusercontent.com/64006699/102392285-3821d200-401a-11eb-8a91-573a62a9cf2f.png)

- n == 1인경우, 즉 노드를 하나만 입력받을 경우도 고려하여 if문에 추가해야함

```java
public static void dfs(int node, int left, int right, int height) // 중위순회
{
	if(left != -1)
	{
		dfs(array[left].num, array[left].left, array[left].right, height+1);
	}
	
	x++;
	
	if(leftHeightMin[height] == 0) // 높이별 맨 왼쪽노드 구하기
	{
		leftHeightMin[height] = x;
	}
	else
	{
		leftHeightMin[height] = Math.min(leftHeightMin[height], x);
	}
	
	if(rightHeightMax[height] == 0) // 높이별 맨 오른쪽노드 구하기
	{
		rightHeightMax[height] = x;
	}
	else
	{
		rightHeightMax[height] = Math.max(rightHeightMax[height], x);
	}
	
	
	if(right != -1)
	{
		dfs(array[right].num, array[right].left, array[right].right, height+1);
	}
	
	
	if(height > allHeight)
	{
		allHeight = height;
	}
}
```

- 전위순회 하는 DFS
- 전위순회이기때문에, 왼쪽 자식노드 → 본인 → 오른쪽 자식노드 순으로 순회를함
- 순회를 하며 높이별 왼쪽, 오른쪽 최소 최대길이 갱신

```java
for(int i = 0; i <= allHeight; i++)
	{
		if(leftHeightMin[i] != 0 && rightHeightMax[i] != 0) // 값이 있는경우에만
		{
			if((rightHeightMax[i] - leftHeightMin[i] + 1) > max)
			{
				max = rightHeightMax[i] - leftHeightMin[i] + 1;
				maxI = i+1;
			}
		}
	}
	
	output.write(maxI + " " + max);
	
	input.close();
	output.close();
```

- 각 높이별 최소값, 최대값을 구한후 너비를 구하는 for문

# 4. 결과

![https://user-images.githubusercontent.com/64006699/102392682-c1390900-401a-11eb-8dca-062f75bb3b28.png](https://user-images.githubusercontent.com/64006699/102392682-c1390900-401a-11eb-8dca-062f75bb3b28.png)