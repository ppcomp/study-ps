# 5주차 - 11729. 하노이 탑 이동 순서

# 1. 문제

[https://www.acmicpc.net/problem/11729](https://www.acmicpc.net/problem/11729)

# 2. 코드

```jsx
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
```

```jsx
public class Main {
```

```jsx
static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
static int count = 0;

public static void hanoi1(int n, int h1, int h2, int h3) throws IOException {
	if (n == 1) {
	} else {
		hanoi1((n - 1), h1, h3, h2);
		hanoi1((n - 1), h2, h1, h3);
	}
	count++;

}

public static void hanoi2(int n, int h1, int h2, int h3) throws IOException {
	if (n == 1) {
		output.write(h1 + " " + h3 + "\\n");
	} else {
		hanoi2((n - 1), h1, h3, h2);
		output.write(h1 + " " + h3 + "\\n");
		hanoi2((n - 1), h2, h1, h3);
	}

}

public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub

	String line = input.readLine();

	int n = Integer.parseInt(line);

	hanoi1(n, 1, 2, 3);
	output.write(count+"\\n");
	hanoi2(n, 1, 2, 3);
	

	input.close();
	output.close();

 }
}
```

# 3. 설명

1. 구현 방법
- 재귀를 사용하여 구현했다.
- [https://shoark7.github.io/programming/algorithm/tower-of-hanoi](https://shoark7.github.io/programming/algorithm/tower-of-hanoi) 를 참고했다.

### **재귀**

**재귀(recursion)란 같은 형태의 보다 작은 입력을 지닌 자기 자신을 호출하는 것이고, 이렇게 재귀적인 호출을 사용하는 함수를 재귀함수라고 한다.**

이 문제 어디에서 재귀의 여지가 있을까? 일단 우리가 정의한 함수의 명세를 다시 보자.

- **hanoi(N): N개의 원반을 어쩌고 저쩌고 해서 다른 곳으로 옮겨라.**

이때 위의 7번의 움직임은 모두 *hanoi(N)* 의 과정이다. 그러면 *hanoi(N-1)* 은 뭘까? 정의에 따라 다음과 같을 것이다:

- **hanoi(N-1): (N-1)개의 원반을 어쩌고 저쩌고 해서 다른 곳으로 옮겨라.**

뭐 충분히 가능한 해석이다. 원반을 100개를 옮길 수도 있고, 그보다 1개 작은 99개 옮기는 것도 얼마든지 가능할테니까. 내가 원하는 것은 ***hanoi(N)* 에서 *hanoi(N-1)* 가 발견되냐**는 것이다. **이를 현재 문제에 적용하면 *hanoi(3)* 이니 *hanoi(2)* 가 발견되는지가 될 것이다.**

2.  입력 방법 및 변수 지정

```jsx
public static void hanoi1(int n, int h1, int h2, int h3) throws IOException {
if (n == 1) {
} else {
hanoi1((n - 1), h1, h3, h2);
hanoi1((n - 1), h2, h1, h3);
}
count++;
```

```jsx
}

public static void hanoi2(int n, int h1, int h2, int h3) throws IOException {
	if (n == 1) {
		output.write(h1 + " " + h3 + "\\n");
	} else {
		hanoi2((n - 1), h1, h3, h2);
		output.write(h1 + " " + h3 + "\\n");
		hanoi2((n - 1), h2, h1, h3);
	}

}
```

- 문제의 출력이 카운트를 먼저 센후, 과정을 출력하기때문에 hanoi1으로 카운트를 센 후,

     hanoi2로 과정을 출력했다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/99675942-33213f80-2abb-11eb-9fbd-1463340ee2a2.png](https://user-images.githubusercontent.com/64006699/99675942-33213f80-2abb-11eb-9fbd-1463340ee2a2.png)