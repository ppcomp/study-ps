# 6주차 - 15649. N과 M (1)

# 1. 문제

[https://www.acmicpc.net/problem/15649](https://www.acmicpc.net/problem/15649)

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
static int[] array = new int[9];
static boolean[] check = new boolean[8];
static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

public static void backT(int count, int n, int m) throws IOException {
	
	if(count > m)
	{
		return ;
	}
	
	if (count == m) {
		for (int i = 0; i < m; i++) {
			output.write(array[i] + " ");
		}
		output.newLine();
	}

	for (int i = 0; i < n; i++) {
		if (check[i] == false) {
			check[i] = true;
			array[count] = i + 1;
			backT(count + 1, n,  m);
			check[i] = false;
		}
	}

}

public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub

	String line = input.readLine();

	int n = line.charAt(0) - '0';
	int m = line.charAt(2) - '0';

	backT(0, n, m);

	input.close();
	output.close();
 }
}

```

# 3. 설명

1. 구현 방법
- 재귀를 사용하여 구현했다.
- backT라는 함수에 계속 재귀하여 array라는 배열에 빈 숫자를 넣고 count를 세어 입력값 m일 때 저장했던 array배열을 모두 출력했다.

2.  입력 방법 및 변수 지정

```java
static int[] array = new int[9];
static boolean[] check = new boolean[8];
static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
```

- 전역변수 array배열을 만든다. N이 8보다 같거나 작으므로 넉넉하게 9로잡았다.
- 전역변수 check배열은 0 ~ 8까지의 숫자가 사용된적이 있는지를 판단하는 배열이다

```java
public static void backT(int count, int n, int m) throws IOException {
	
	if(count > m)
	{
		return ;
	}
	
	if (count == m) {
		for (int i = 0; i < m; i++) {
			output.write(array[i] + " ");
		}
		output.newLine();
	}

	for (int i = 0; i < n; i++) {
		if (check[i] == false) {
			check[i] = true;
			array[count] = i + 1;
			backT(count + 1, n,  m);
			check[i] = false;
		}
	}

}
```

- backT라는 함수에 처음 들어가게되면 위에 if문 두개는 건너뛰고 for문으로 들어가게된다.
- check배열로 0~8의 숫자 중 쓰지 않은 숫자가 있으면 그 숫자를 array배열에 넣고 재귀로 backT함수에 다시 접근한다. 마지막에는 check[i] = false를 사용하여 썼던 숫자를 다시 안쓴 숫자로 만든다.
- 이런식으록 재귀하다보면 문제가 해결된다

예시) 자연수 1, 2, 3중에서 2개로 이루어진 수열찾기

처음 backT함수에 들어가 진행하다보면 [1 2]를 출력한다. 그다음에 array배열에서 2가 빠지고 check배열에서 2를 쓰지않은 숫자로 체크한다. 그다음에 for문을 계속진행하는데, 3이라는 숫자가 쓴적이 없으므로 [1 3]을 출력한다. 이런식으로 반복된다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/100356683-e7c8dd00-3036-11eb-9de3-7244ed217061.png](https://user-images.githubusercontent.com/64006699/100356683-e7c8dd00-3036-11eb-9de3-7244ed217061.png)