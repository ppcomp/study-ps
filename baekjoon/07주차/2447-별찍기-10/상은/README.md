# 7주차 - 2447. 별 찍기 - 10

# 1. 문제

[https://www.acmicpc.net/problem/2447](https://www.acmicpc.net/problem/2447)

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
static char[][] array;

public static void star(int n, int row, int col)
{
	if(n == 3)
	{
		for(int i = row; i < row+3; i++)
		{
			for(int j = col; j < col+3; j++)
			{
				if(i == row+1 && j == col+1)
				{
					array[i][j] = ' ';
				}else
				{
					array[i][j] = '*';
				}
				
			}
		}
	}else
	{
		star(n/3, row, col);
		star(n/3, row, col+1*(n/3));
		star(n/3, row, col+2*(n/3));
		
		star(n/3, row+1*(n/3), col);
		star(n/3, row+1*(n/3), col+2*(n/3));
		
		star(n/3, row+2*(n/3), col);
		star(n/3, row+2*(n/3), col+1*(n/3));
		star(n/3, row+2*(n/3), col+2*(n/3));
	}
}

public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub

	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	String line = input.readLine();
	int n = Integer.parseInt(line);
	
	array = new char[n][n];
	for(int i = 0; i < n; i++)
	{
		for(int j = 0; j < n; j++)
		{
			array[i][j] = ' ';
		}
	}
	
	star(n, 0, 0);
	
	for(int i = 0; i < n; i++)
	{
		for(int j = 0; j < n; j++) 
		{
			output.write(array[i][j]);
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
- 재귀를 사용하여 해결했다.
- 

2.  입력 방법 및 변수 지정

```java
public static void star(int n, int row, int col)
{
	if(n == 3)
	{
		for(int i = row; i < row+3; i++)
		{
			for(int j = col; j < col+3; j++)
			{
				if(i == row+1 && j == col+1)
				{
					array[i][j] = ' ';
				}else
				{
					array[i][j] = '*';
				}
				
			}
		}
	}else
	{
		star(n/3, row, col);
		star(n/3, row, col+1*(n/3));
		star(n/3, row, col+2*(n/3));
		
		star(n/3, row+1*(n/3), col);
		star(n/3, row+1*(n/3), col+2*(n/3));
		
		star(n/3, row+2*(n/3), col);
		star(n/3, row+2*(n/3), col+1*(n/3));
		star(n/3, row+2*(n/3), col+2*(n/3));
	}
}
```

- 작은 사각형 (n이 3일 때)를 기준으로 재귀를 돌렸다
- n이 3인경우에 가운데가 뻥 뚫린 사각형을 그리게 했고, 처음 n값에 3을 나누어가며 가운데 부분은 그리지 않도록 코드를 짰다.

```java
array = new char[n][n];
for(int i = 0; i < n; i++)
{
	for(int j = 0; j < n; j++)
	{
		array[i][j] = ' ';
	}
}
```

![https://user-images.githubusercontent.com/64006699/100986576-b9945180-3590-11eb-8260-9ab945af8c05.png](https://user-images.githubusercontent.com/64006699/100986576-b9945180-3590-11eb-8260-9ab945af8c05.png)

- 처음에 배열 초기화를 안했더니 '틀렸습니다' 가 나왔다. 생각해보니 가운데부분에 재귀가 안들어가는 부분은 공백이 아닌 NULL값으로 초기화되기 때문에 그런 듯 싶어 초기화 부분을 넣어주니 해결이 되었다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/100986981-3e7f6b00-3591-11eb-8e76-85e08b39773e.png](https://user-images.githubusercontent.com/64006699/100986981-3e7f6b00-3591-11eb-8e76-85e08b39773e.png)