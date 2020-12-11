# 8주차 - 2293. 동전 1

# 1. 문제

[https://www.acmicpc.net/problem/2293](https://www.acmicpc.net/problem/2293)

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

	StringTokenizer st = new StringTokenizer(line, " ");
	int n = Integer.parseInt(st.nextToken());
	int k = Integer.parseInt(st.nextToken());
	int[] coin = new int[n];
	int[] sum = new int[k + 1];

	for (int i = 0; i < n; i++)
	{
		line = input.readLine();
		coin[i] = Integer.parseInt(line);
	}

	sum[0] = 1;

	for (int i = 0; i < n; i++)
	{
		for (int j = coin[i]; j <= k; j++)
		{
			sum[j] += sum[j - coin[i]];
		}
	}

	output.write(String.valueOf(sum[k]));

	input.close();
	output.close();

 }
}
```

# 3. 설명

1. 구현 방법
- Dynamic Programming를 사용하여 구현했다.
- [https://galid1.tistory.com/507](https://galid1.tistory.com/507) 참조

2.  입력 방법 및 변수 지정

```java
for (int i = 0; i < n; i++)
{
	line = input.readLine();
	coin[i] = Integer.parseInt(line);
}

```

- 처음 입력값을 coin 이라는 배열에 넣는다. ex) coin[0] = 1, coin[1] = 2, coin[2] = 5

```java
sum[0] = 1;

for (int i = 0; i < n; i++)
{
	for (int j = coin[i]; j <= k; j++)
	{
		sum[j] += sum[j - coin[i]];
	}
}
```

- sum[0] 의 값을 1로둔다. 0원을 만드는방법은  동전을 아예 안사용하는 경우이다.

![https://user-images.githubusercontent.com/64006699/101775198-727b0300-3b32-11eb-97bd-24c8f2b8f918.png](https://user-images.githubusercontent.com/64006699/101775198-727b0300-3b32-11eb-97bd-24c8f2b8f918.png)

- 각 동전이 사용되는 숫자를 나타낸 표이다.
- 4원을 예로들면, 1+1+1+1 / 1+1+2 / 2+2 의 경우가 있는데, 이것은 2원을 사용하지 않고 2를 만드는 수에 2원을 사용하고 2를 만든 수를 더한 경우와 값이 같아진다.
- 7원을 예로들면, 총 6가지의 경우의 수로, 5원을 사용하지 않고 7을 만들어낸 수 + 5원을 사용하고 7을 만들어낸 수를 더하면 된다. (4 + 2)
- 즉 이런식으로 전에 계산했던 결과를 이용하여 새로운 결과를 도출해낸다
- 식으로 나타내면 sum[j] = sum[j] + sum[j - coin[i]] 라는 식이 나온다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/101775645-1f558000-3b33-11eb-993e-90df204e1787.png](https://user-images.githubusercontent.com/64006699/101775645-1f558000-3b33-11eb-993e-90df204e1787.png)