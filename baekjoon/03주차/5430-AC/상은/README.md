# 3주차 - 5430. AC

# 1. 문제

[https://www.acmicpc.net/problem/5430](https://www.acmicpc.net/problem/5430)

# 2. 코드

```jsx
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
```

```jsx
public class Main {
public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub

	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

	int n = Integer.parseInt(input.readLine());
	Deque<Integer> deque = new LinkedList<Integer>();
	String func = "";
	String line = "";
	int change = 1; // change가 1인경우 : 정방향, -1인경우 : 역방향
	int number;
	int error = 0;

	for (int i = 0; i < n; i++) {
		func = input.readLine();
		number = Integer.parseInt(input.readLine());
		line = input.readLine();
		line = line.replace("[", "");
		line = line.replace("]", "");
		line = line.replace(" ", "");
		StringTokenizer st = new StringTokenizer(line, ",");
		while (st.hasMoreTokens()) {
			deque.addLast(Integer.parseInt(st.nextToken()));
		}
		for (int j = 0; j < func.length(); j++) {
			switch (func.charAt(j)) {
			case 'R':
				change *= -1;
				break;
			case 'D':
				if (!deque.isEmpty()) {
					if (change == 1) {
						deque.removeFirst();
					} else {
						deque.removeLast();
					}
				} else {
					error = 1;
				}
				break;
			}

		}

		if (error == 0) {
			output.write("[");
		}
		else {
			output.write("error");
		}
		while (!deque.isEmpty()) {
			if (change == 1 && deque.size() != 1) {
				output.write(String.valueOf(deque.removeFirst()) + ",");
			} else if (change == -1 && deque.size() != 1) {
				output.write(String.valueOf(deque.removeLast()) + ",");
			} else {
				output.write(String.valueOf(deque.removeLast()));
			}
		}
		if (error == 0) {
			output.write("]");
		}

		deque.clear();
		error = 0;
		change = 1;
		output.newLine();
	}

	input.close();
	output.close();

 }
}
```

# 3. 설명

1. 구현 방법
- 이번 문제는 처음부터 **Deque** 라는 자료구조를 사용하여 풀면 쉽게 풀리겠다하고 구현을 했다.
- 왜냐하면, 배열의 순서를 뒤집는 연산을 구현하기에는 시간복잡도가 커져 시간초과가 날 듯 했고, **Stack**이나 **Queue**를 사용하기보단 **Deque**를 사용한다면 배열의 순서를 뒤집지 않아도 구현이 가능해 보였기 때문이다.

 2.  입력 방법 및 변수 지정

- 입력은 BufferedReader, 출력은 BufferedWriter로 입출력 시간을 줄였다.

```jsx
BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
```

- 입력줄 수를 받기위한 **int n**
- 자료구조 **Deque**
- 입력 함수(R,D)들을 받기위한 **String func**
- 입력줄을 받기위한 **String line**
- 정방향, 역방향을 구별하기위한 **int change**
- 입력값들의 수를 받기 위한 **int number**
- error 판별을 위한 **int error**

```jsx
int n = Integer.parseInt(input.readLine());
Deque<Integer> deque = new LinkedList<Integer>();
String func = "";
String line = "";
int change = 1; // change가 1인경우 : 정방향, -1인경우 : 역방향
int number;
int error = 0;
```

- 입력줄 line이 [1, 2, 3, 4] 이런식으로 오기때문에 1 2 3 4를 추출하기 위한 작업을 했다.

```jsx
line = input.readLine();
line = line.replace("[", ""); // '[' 제거
line = line.replace("]", ""); // ']' 제거
line = line.replace(" ", ""); // 공백제거
StringTokenizer st = new StringTokenizer(line, ","); // ','를 기준으로 분리
```

- 숫자들을 추출한 후, 덱에 **Integer**값으로 바꿔 저장을 한다

```jsx
while (st.hasMoreTokens()) {
deque.addLast(Integer.parseInt(st.nextToken()));
}
```

    → 처음에 **String**값으로 그냥 저장을 했는데 정답이 틀려 고생을 했다. 입력값은 1~100이기때문에

        1자릿수가 아닌 2~3 자릿수도 고려를 해야한다.

![https://user-images.githubusercontent.com/64006699/98276044-c39a5300-1fd8-11eb-9357-143bf25e3e77.png](https://user-images.githubusercontent.com/64006699/98276044-c39a5300-1fd8-11eb-9357-143bf25e3e77.png)

- for문으로 함수(R, D)의 갯수만큼 반복문 진행
- 만약 R이라면, **change**값을 변화시킴 (정방향 : 1, 역방향 : -1)
- 만약 D라면, 덱이 비었을때와 덱이 비지않았을때로 구분

    → 덱이 비었을때 D연산을 할경우 **error** 발생

    → 정방향(**change : 1**)일 경우 덱의 첫번째 원소 제거 ex) 1, 2, 3, 4, 5 에서 1제거

    → 역방항(**change : -1**)일 경우 덱의 마지막 원소 제거

       ex) 1, 2, 3, 4, 5에서 5제거   (이유 : 역방향이라고 가정하면 5, 4, 3, 2, 1이 되고 첫번째원소 5 제거)

```jsx
for (int j = 0; j < func.length(); j++) {
	switch (func.charAt(j)) {
		case 'R':
			change *= -1;
			break;
		case 'D':
			if (!deque.isEmpty()) {
				if (change == 1) {
					deque.removeFirst();
				} else {
					deque.removeLast();
				}
			} else {
				error = 1;
			}
			break;
 }
}
```

- **error**가 발생하면 에러출력, **error**가 발생하지않았다면 출력기준에 맞춰 출력

```jsx
if (error == 0) {
	output.write("[");
} else {
	output.write("error");
}

while (!deque.isEmpty()) {
	if (change == 1 && deque.size() != 1) {
		output.write(String.valueOf(deque.removeFirst()) + ",");
	} else if (change == -1 && deque.size() != 1) {
		output.write(String.valueOf(deque.removeLast()) + ",");
	} else {
		output.write(String.valueOf(deque.removeLast()));
	}
	}

if (error == 0) {
	output.write("]");
}
```

- 맨 처음나온 for문이 끝날때마다 **변수**, **Deque 초기화**

```jsx
deque.clear();
error = 0;
change = 1;
output.newLine();
```

# 4. 결과

![https://user-images.githubusercontent.com/64006699/98276992-23ddc480-1fda-11eb-92ca-0771903ce74f.png](https://user-images.githubusercontent.com/64006699/98276992-23ddc480-1fda-11eb-92ca-0771903ce74f.png)