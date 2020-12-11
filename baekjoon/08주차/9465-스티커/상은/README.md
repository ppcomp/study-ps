# 8주차 - 9465. 스티커

# 1. 문제

[https://www.acmicpc.net/problem/9465](https://www.acmicpc.net/problem/9465)

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
public class Main
{
public static void main(String[] args) throws IOException
{
// TODO Auto-generated method stub
```

```java
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	String line = input.readLine();
	int caseNum = Integer.parseInt(line);

	for (int i = 0; i < caseNum; i++)
	{
		int n = Integer.parseInt(input.readLine());
		StringTokenizer st;
		int[][] array = new int[2][n];
		int[][] dp = new int[2][n];
		for (int j = 0; j < 2; j++)
		{
			line = input.readLine();
			st = new StringTokenizer(line, " ");
			for (int k = 0; k < n; k++)
			{
				array[j][k] = Integer.parseInt(st.nextToken());
			}
		}

		dp[0][0] = array[0][0];
		dp[1][0] = array[1][0];

		for (int k = 1; k < n; k++)
		{
			if (k - 2 < 0)
			{
				dp[0][k] = array[0][k] + dp[1][k - 1];
				dp[1][k] = array[1][k] + dp[0][k - 1];
			}else
			{
				dp[0][k] = array[0][k] + Math.max(dp[1][k - 1], dp[1][k - 2]);
				dp[1][k] = array[1][k] + Math.max(dp[0][k - 1], dp[0][k - 2]);
			}
		}

		output.write(Math.max(dp[0][n - 1], dp[1][n - 1]) + "\\n");
	}

	input.close();
	output.close();

 }
}
```

# 3. 설명

1. 구현 방법
- DP를 사용하여 구현했다.

2.  입력 방법 및 변수 지정

```java
for (int k = 1; k < n; k++)
		{
			if (k - 2 < 0)
			{
				dp[0][k] = array[0][k] + dp[1][k - 1];
				dp[1][k] = array[1][k] + dp[0][k - 1];
			}else
			{
				dp[0][k] = array[0][k] + Math.max(dp[1][k - 1], dp[1][k - 2]);
				dp[1][k] = array[1][k] + Math.max(dp[0][k - 1], dp[0][k - 2]);
			}
		}
```

- DP를 직접적으로 이용하는 구문이다.

![https://user-images.githubusercontent.com/64006699/101818225-e931f380-3b66-11eb-83d5-c14aef63d3bf.png](https://user-images.githubusercontent.com/64006699/101818225-e931f380-3b66-11eb-83d5-c14aef63d3bf.png)

- 경우의 수는 2가지로 나뉜다.
- 처음에 (0,0) 위치에 스티커를 붙이는 경우 or (1,0) 위치에 스티커를 붙이는 경우
1. (0,0) 위치에 스티커를 붙이는 경우, 다음 스티커를 붙이는 경우는 (1,1) or (1,2)의 경우이다.
2. (1,0) 위치에 스티커를 붙이는 경우, 다음 스티커를 붙이는 경우는 (0,1) or (0,2)의 경우이다.
3. 즉 DP배열을 생각하면, DP[0][2]에 나올 수 있는 최댓값은 (0,2) + MAX((1,0) or (1,1)) 이다.
4. 이런식으로 점화식을 도출해내면

```java
dp[0][k] = array[0][k] + Math.max(dp[1][k - 1], dp[1][k - 2]);
dp[1][k] = array[1][k] + Math.max(dp[0][k - 1], dp[0][k - 2]);
```

```java
if (k - 2 < 0)
{
	dp[0][k] = array[0][k] + dp[1][k - 1];
	dp[1][k] = array[1][k] + dp[0][k - 1];
}
```

- 이 구문을 통하여 처음접근할때 ArrayIndexOut~~ 예외를 피한다.
- 맨 처음의 for문에서 k-2의 값이 -1이 나올때 이 예외가 발생하는데, 그것을 위한 코드이다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/101818875-d66bee80-3b67-11eb-9e84-aa8d166bb1b6.png](https://user-images.githubusercontent.com/64006699/101818875-d66bee80-3b67-11eb-9e84-aa8d166bb1b6.png)