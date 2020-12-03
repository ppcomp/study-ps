# 7주차 - 1182. 부분수열의 합

# 1. 문제

[https://www.acmicpc.net/problem/1182](https://www.acmicpc.net/problem/1182)

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
public class Main {
```

```java
static int[] array;
static int n;
static int m;
static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
static int count = 0;

public static void backT(int current, int sum) throws IOException {
	
	if(current == n)
	{
		if (sum == m && current != 0) {
			count++;
		}
		else {
			return;
		}
	}else
	{
		backT(current+1, sum+array[current]);
		backT(current+1, sum);
	}

}

public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub

	String line = input.readLine();
	StringTokenizer st = new StringTokenizer(line, " ");

	n = Integer.parseInt(st.nextToken());
	m = Integer.parseInt(st.nextToken());
	array = new int[n];
	
	line = input.readLine();
	st = new StringTokenizer(line, " ");

	
	for(int i = 0; i < n; i++)
	{
		array[i] = Integer.parseInt(st.nextToken());
	}
	
	if(m == 0)
	{
		count--;
	}

	backT(0, 0);
	
	output.write(count+" ");

	input.close();
	output.close();
 }
}

```

# 3. 설명

1. 구현 방법
- 백트래킹을 사용하여 구현했다.

2.  입력 방법 및 변수 지정

```java
public static void backT(int current, int sum) throws IOException {
	
	if(current == n)
	{
		if (sum == m && current != 0) {
			count++;
		}
		else {
			return;
		}
	}else
	{
		backT(current+1, sum+array[current]);
		backT(current+1, sum);
	}

}
```

- backT 함수에서는 백트래킹을 하며 부분수열의 합이 입력값 m과 같을경우 count를 1씩 더한다

```java
		backT(current+1, sum+array[current]);
		backT(current+1, sum);
```

- 백트래킹을 할 때 위의 경우에는 현재값을 더하면서 백트래킹을 하는 구문이고, 밑의 경우에는 현재값을 더하지않고 백트래킹을 하는 구문이다.
- 

```
5 0
-7 -3 -2 5 8
```

- 예제의 경우 백트래킹을 하면 -7 -3 -2 +5 +8 순으로 먼저 함수를 호출하고, 현재값이 n인 5에 도달하면 부분수열의 끝에 갔다는 의미이므로 sum과 m값을 비교한다
- -7-3-2+5+8의 값은 1로써, sum과 m이 같지않으므로 return 하고, 다음에 현재 값을 더하지 않는 backT(current+1, sum); 로 들어가 -7 -3 -2 +5를 계산하고 계속 비교한다.
- 이런식으로 찾다보면 모든 부분수열의 합을 찾게되며 count 갯수를 셀 수 있다.

```java
if(m == 0)
{
	count--;
}
```

★ 처음에 backT 할때 m값이 0이라면 공집합의 경우도 포함하게되므로(맨 마지막에 sum이 0됨)

    m이 0일때는 count를 -1로 시작하게한다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/100876805-3d93fe00-34eb-11eb-8374-eb9e23f74831.png](https://user-images.githubusercontent.com/64006699/100876805-3d93fe00-34eb-11eb-8374-eb9e23f74831.png)