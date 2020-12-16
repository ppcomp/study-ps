# 9주차 - 2294. 동전 2

# 1. 문제

[https://www.acmicpc.net/problem/2294](https://www.acmicpc.net/problem/2294)

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
```

```java
public static void main(String[] args) throws IOException
{
	// TODO Auto-generated method stub
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	String line = input.readLine();
	StringTokenizer st = new StringTokenizer(line, " ");
	int n = Integer.parseInt(st.nextToken());
	int k = Integer.parseInt(st.nextToken());
	int[] coin = new int[n];
	int[] dp = new int[k+1];
	
	for(int i = 0; i < n; i++)
	{
		coin[i] = Integer.parseInt(input.readLine());
	}
	
	for(int i = 0; i<=k; i++)
	{
		dp[i] = 99999;
	}
	
	
	

	dp[0] = 0;

	for (int i = 0; i < n; i++)
	{
		for (int j = coin[i]; j <= k; j++)
		{
			dp[j] = Math.min(dp[j], dp[j-coin[i]]+1 );
		}
	}
	
	if(dp[k] == 99999)
	{
		output.write(String.valueOf(-1));	
	}else
	{
		output.write(String.valueOf(dp[k]));
	}

	
	input.close();
	output.close();
 }
}

```

# 3. 설명

1. 구현 방법
- DP를 사용하여 구현했다. (동전 1 참고)

2.  입력 방법 및 변수 지정

```java
for(int i = 0; i < n; i++)
{
	coin[i] = Integer.parseInt(input.readLine());
}
```

- 코인값을 받는다.

```java
for(int i = 0; i<=k; i++)
{
	dp[i] = 99999;
}

	dp[0] = 0;
```

- 해당 문제는 최소값을 구하는 문제이다. 즉 함수 Min 을 써야하기때문에 dp값을 높은값으로 초기화해주었다.
- dp[0]은 0으로 둔다. 이렇게해야 점화식에 들어갈때 알맞게 된다.

```java
for (int i = 0; i < n; i++)
	{
		for (int j = coin[i]; j <= k; j++)
		{
			dp[j] = Math.min(dp[j], dp[j-coin[i]]+1 );
		}
	}
```

- 점화식 부분은 dp[j] = Math.min(dp[j], dp[j-coin[i]]+1 ); 이다. 동전 1을 참고하고 표를 그려 도출했다.

![https://user-images.githubusercontent.com/64006699/102285702-2852b100-3f7a-11eb-9b01-4441d54a9fbd.png](https://user-images.githubusercontent.com/64006699/102285702-2852b100-3f7a-11eb-9b01-4441d54a9fbd.png)

- 표를 그려 점화식을 도출하려했다.
- 최소값이 나오는경우는 전에 가지고있던 값을 그대로 쓰거나, 동전의 크기만큼 현재에서 뺀 dp배열에서 자신의 동전크기만큼 더한 값이 dp값이 된다.
- 밑의 표에서 14를 만들기위한 것을 예로 들겠다.
- 14가 최소가 되기위한 값은 1, 5만 써서 만든 14값 vs 2를 만든값에서 12의 동전을 넣은 값이다.
- 즉 점화식으로 표현하면 최소값(dp[j], dp[j-coin[i]] + 1)을 도출해 낼 수 있다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/102286295-5e446500-3f7b-11eb-96e1-94d2423991b4.png](https://user-images.githubusercontent.com/64006699/102286295-5e446500-3f7b-11eb-96e1-94d2423991b4.png)