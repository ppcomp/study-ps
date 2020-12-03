# 6주차 - 1074. Z

# 1. 문제

[https://www.acmicpc.net/problem/1074](https://www.acmicpc.net/problem/1074)

# 2. 코드

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

```java
static int count = 0;
static boolean progress = true;
static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

public static void Z(int x, int y, int size, int r, int c) throws IOException
{
	if(progress == true)
	{
		if(size == 2)
		{
			if(x == r && y == c)
			{
				progress = false;
				output.write(count+" ");
			}
			count++;
			if(x == r && y+1 == c)
			{
				progress = false;
				output.write(count+" ");
			}
			count++;
			if(x+1 == r && y == c)
			{
				progress = false;
				output.write(count+" ");
			}
			count++;
			if(x+1 == r && y+1 == c)
			{
				progress = false;
				output.write(count+" ");
			}
			count++;
		}else
		{
			Z(x, y, size/2, r, c); // 1사분면 탐색
			Z(x, y+size/2, size/2, r, c); // 2사분면 탐색
			Z(x+size/2, y, size/2, r, c); // 3사분면 탐색
			Z(x+size/2, y+size/2, size/2, r, c); // 4사분면 탐색
		}
	}
}

public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub

	
	String line = input.readLine();
	StringTokenizer st = new StringTokenizer(line, " ");
	int n = Integer.parseInt(st.nextToken());
	int r = Integer.parseInt(st.nextToken());
	int c = Integer.parseInt(st.nextToken());
	
	int size = (int) Math.pow(2, n);
	
	Z(0, 0, size, r, c);
	
	
	input.close();
	output.close();
	
 }
}
```

# 3. 설명

1. 구현 방법
- 재귀를 사용하여 풀었다.

![https://user-images.githubusercontent.com/64006699/100333733-4af64780-3016-11eb-93e1-6d81102ab95b.png](https://user-images.githubusercontent.com/64006699/100333733-4af64780-3016-11eb-93e1-6d81102ab95b.png)

- 입력변수가 들어오면 결국 4사분면을 탐색하는 것이 반복된다.
- 위의 예에서는 전체 → 파란색 → 파란색의  0..15 → 빨간색 → 빨간색의 16...31 → 노란색 → 초록색  이런식으로 재귀가 돌아간다.
- 그렇기때문에 size가 2인경우(제일 작은 사각형)에 r과 c에 해당하는 x,y좌표라면 count를 출력하고, 아니라면 count를 1씩 더해주는 방법으로 문제를 해결했다.

2.  입력 방법 및 변수 지정

```java
static int count = 0;
static boolean progress = true;
static BufferedReader input = new BufferedReader(new InputStreamReader([System.in](http://system.in/)));
static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
```

- 전역변수로 count와 재귀를 계속 탐색할것인지에 대한 progress를 짖어해주었다.

```java
String line = input.readLine();
StringTokenizer st = new StringTokenizer(line, " ");
int n = Integer.parseInt(st.nextToken());
int r = Integer.parseInt(st.nextToken());
int c = Integer.parseInt(st.nextToken());
```

- line을 받고 StringTokenizer로 토큰을 분리해가며 n, r, c값을 입력받았다.
- 처음에 charAt()를 사용하여 풀었는데 두자리수인경우 (ex : 13) 1만 입력되는 현상이 생겨 틀렸다고 떴다. 조심하자

```java
public static void Z(int x, int y, int size, int r, int c) throws IOException
{
	if(progress == true)
	{
		if(size == 2)
		{
			if(x == r && y == c)
			{
				progress = false;
				output.write(count+" ");
			}
			count++;
			if(x == r && y+1 == c)
			{
				progress = false;
				output.write(count+" ");
			}
			count++;
			if(x+1 == r && y == c)
			{
				progress = false;
				output.write(count+" ");
			}
			count++;
			if(x+1 == r && y+1 == c)
			{
				progress = false;
				output.write(count+" ");
			}
			count++;
		}else
		{
			Z(x, y, size/2, r, c); // 1사분면 탐색
			Z(x, y+size/2, size/2, r, c); // 2사분면 탐색
			Z(x+size/2, y, size/2, r, c); // 3사분면 탐색
			Z(x+size/2, y+size/2, size/2, r, c); // 4사분면 탐색
		}
	}
}
```

- size가 2인경우(가장 작은 사각형) x,y에 1씩 더해가며 r,c에 해당하는경우 출력, 아니면 count++
- 만약 가장 작은 사각형에서 해당값이 없을경우 재귀 반복

# 4. 결과

![https://user-images.githubusercontent.com/64006699/100334527-41b9aa80-3017-11eb-90bc-f84989eb91ac.png](https://user-images.githubusercontent.com/64006699/100334527-41b9aa80-3017-11eb-90bc-f84989eb91ac.png)