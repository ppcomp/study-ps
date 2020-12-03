# 6주차 - 9663. N-Queen

# 1. 문제

[https://www.acmicpc.net/problem/9663](https://www.acmicpc.net/problem/9663)

# 2. 코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
```

```java
public class Main {
```

```java
static int count = 0;
static int[] array;
static int num = 0;
public static void nQueen(int row)
{
	if(row == num)
	{
		count++;
	}
	else
	{
		for(int i = 0; i < num; i++)
		{
			if(check(i, row))
			{
				array[row] = i;
				nQueen(row+1);
				array[row] = -1;
			}
		}
	}
	
}

public static boolean check(int col, int row) // 행에 대한 체크
{
	for(int i = 0; i < row; i++)
	{
		if(array[i] == col) // 같은 열에 있는경우
		{
			return false;
		}else if(Math.abs(i - row ) == Math.abs(array[i] - col)) // 대각선에 있는 경우
		{	
			return false;
		}
	}
	
	return true;
}
public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub
	
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	String line = input.readLine();
	num = Integer.parseInt(line);
	array = new int[num];
	
	for(int i = 0; i < num; i++)
	{
		array[i] = -1;
	}
	
	nQueen(0);
	
	output.write(String.valueOf(count));
	
	input.close();
	output.close();
 }

}
```

# 3. 설명

1. 구현 방법
- 재귀를 사용하여 구현했다.
- 처음에는 이차원 배열로 직접 하나하나 대입하며 구하려 했지만, 너무 오래 걸릴 것 같아 일차원 배열만 이용하기로 결정했다.

2.  입력 방법 및 변수 지정

```java
static int count = 0;
static int[] array;
static int num = 0;
```

- 전역변수로 N-Queen이 가능한 경우 갯수를 세는 count, 배열의 상황 array, 입력 변수 num 지정

```java
BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
String line = input.readLine();
num = Integer.parseInt(line);
array = new int[num];

for(int i = 0; i < num; i++)
{
	array[i] = -1;
}
```

- 입력을 받고 처음에 배열을 초기화한다. 배열은 0부터 시작하기때문에 -1로 접근을 했는지 안했는지를 판단한다.

```java
public static void nQueen(int row)
{
	if(row == num)
	{
		count++;
	}
	else
	{
		for(int i = 0; i < num; i++)
		{
			if(check(i, row))
			{
				array[row] = i;
				nQueen(row+1);
				array[row] = -1;
			}
		}
	}
	
}
```

- 재귀 함수이다. 행이 입력값 num에 다다르면 N-Queen의 조건을 완성함으로 count를 1더한다.
- 기본적으로 입력값 row를 받아 재귀가 시작된다. 처음에 입력되는 row는 0으로, 이차원 배열의 맨 윗부분부터 재귀를 시작한다.

- N-Quuen 놓는 방식

![https://user-images.githubusercontent.com/64006699/100812265-f1f83a80-347f-11eb-85d6-45abb6776d91.png](https://user-images.githubusercontent.com/64006699/100812265-f1f83a80-347f-11eb-85d6-45abb6776d91.png)

- 처음 0,0을 탐색할 때 array[0] = 0이 되고, 재귀로 2번째 행을 탐색한다

![https://user-images.githubusercontent.com/64006699/100812401-3be12080-3480-11eb-9c69-b9e1df918566.png](https://user-images.githubusercontent.com/64006699/100812401-3be12080-3480-11eb-9c69-b9e1df918566.png)

- 두번째 재귀를 시작하면 1,0일때 탐색을 하는데 1,0인경우와 1,1인경우는 Queen 이 놓여질 수 없는 경우이므로 패스하고 1,2일때 Queen을 놓는다.

![https://user-images.githubusercontent.com/64006699/100812531-9a0e0380-3480-11eb-8317-cca97f410f17.png](https://user-images.githubusercontent.com/64006699/100812531-9a0e0380-3480-11eb-8317-cca97f410f17.png)

- 세번째 행에대한 재귀를 시작할때 Queen을 놓을 수 없는 상황이므로 다시 두번째 행에 대한 재귀로 돌아가 다시 탐색을 진행한다.

```java
public static boolean check(int col, int row) // 행에 대한 체크
{
	for(int i = 0; i < row; i++)
	{
		if(array[i] == col) // 같은 열에 있는경우
		{
			return false;
		}else if(Math.abs(i - row ) == Math.abs(array[i] - col)) // 대각선에 있는 경우
		{	
			return false;
		}
	}
	
	return true;
}
```

- Queen이 놓일 수 있는지에 대한 경우를 탐색하는 check함수이다. 매개변수는 col과 row를 받으며 같은 열에 있는경우와 대각선에 있는 경우 false, 그 외의 경우 true를 반환한다.
- 대각선에 있는 경우를 판단할때는 퀸이 놓인 자리들과 매개변수로 받는 row, col의 자리와 비교를 하며 판단한다.

   ex) 판단할 매개변수가 (1,0) 인 경우

      → 대각선의 종류는 (0,1), (2,1), (3,2)의 경우로, 각각의 x값의 차이에 대한 절대값과 y값의 차이에 

          대한 절대값이 같은 경우 대각선으로 판단한다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/100812209-d3923f00-347f-11eb-95de-42772cfa85e7.png](https://user-images.githubusercontent.com/64006699/100812209-d3923f00-347f-11eb-95de-42772cfa85e7.png)