# 3주차 - 2493. 탑

# 1. 문제

[https://www.acmicpc.net/problem/2493](https://www.acmicpc.net/problem/2493)

# 2. 코드

```jsx
package top;
```

```jsx
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
class top {
int height;
int number;
```

```
	public top(int number, int height) {
		this.number = number;
		this.height = height;
	}

```

```jsx
}
public class Main {
public static void main(String[] args) throws IOException {
// TODO Auto-generated method stub
BufferedReader input = new BufferedReader(new InputStreamReader([System.in](http://system.in/)));
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
```

```
	int n = Integer.parseInt(input.readLine());
	String line = input.readLine();
	StringTokenizer st = new StringTokenizer(line);
	int temp;
	Stack<top> top = new Stack<top>();
	Stack<Integer> solution = new Stack<Integer>(); // 답을 저장할 스택

	for (int i = 1; i <= n; i++) {
		if (top.isEmpty()) {
			output.write(String.valueOf(0) + " ");
			top.push(new top(i, Integer.parseInt(st.nextToken())));
		} else {
			temp = Integer.parseInt(st.nextToken());
			while (top.size() > 0) {
				if (top.peek().height > temp) {
					output.write(String.valueOf(top.peek().number) + " ");
					top.push(new top(i, temp));
					break;
				} else {
					top.pop();
				}
			}
			if (top.isEmpty()) {
				output.write(String.valueOf(0) + " ");
				top.push(new top(i, temp));
			}
		}
	}
	input.close();
	output.close();
 }
```

```jsx
}
```

# 3. 설명

1. 구현 방법
- 맨 처음에는 배열로 모든 탑의 높이를 저장 후, for문을 사용하여 맨 오른쪽부터 하나하나씩 비교하는 방법을 사용하려했다.

```
	for (int i = 0; i < n; i++) {
		for (int j = i - 1; j >= 0; j--) {
			if (top[j] >= top[i]) {
				output.write(String.valueOf(j + 1) + " ");
				check = 1;
				break;
			}

		}
		if (check == 0) {
			output.write(String.valueOf(0) + " ");
		}
	}
```

- 결과는 하나하나씩 비교하며 하는 방법이 시간복잡도가 커서 **시간 초과**가 발생하였다.

![https://user-images.githubusercontent.com/64006699/98269555-330c4480-1fd1-11eb-8411-95b413adb677.png](https://user-images.githubusercontent.com/64006699/98269555-330c4480-1fd1-11eb-8411-95b413adb677.png)

- 그 후, 시간복잡도를 해결하기위해 찾은 방법은 **스택**을 사용하여  구현하는 것이었다. 스택을 2개를 사용하여 한개의 스택에는 번호를, 나머지 스택에는 높이를 넣었다.
- 마지막자리부터 하나하나씩 POP하며, 부딪히는 탑을 찾고, 찾은 후에는 기준이 된 탑을 제외한 나머지 POP한 탑들을 다시 스택에 넣고, 다시 하나하나씩 비교하는 식으로 구현했다.

```jsx
Stack<Integer> st = new Stack<Integer>();
Stack<Integer> st2 = new Stack<Integer>();
Stack<Integer> solution = new Stack<Integer>(); // 답을 저장할 스택
```

```jsx
for (int i = 0; i < n; i++) {
st.push(Integer.parseInt(tk.nextToken()));
}
```

```
	for (int i = n; i > 0; i--) {
		pop = st.pop();
		while(st.size() > 0) {
			temp = st.pop();
			if(temp >= pop) {
				solution.push(i - count);
				st.push(temp);
				break;
				
			}else {
				count++;
				st2.add(temp);
			}	
		}
		
		if(st.size() == 0) {
			solution.push(i - count);
		}
		
		while(st2.size() > 0)
		{
			st.push(st2.pop());
		}
		count = 1;
			
		
	}

	for (int i = 0; i < n; i++) {
		output.write(String.valueOf(solution.pop()) + " ");
	}
```

- 결과는 스택을 사용했지만, 불필요하게 넣다 뺐다하는 과정으로 **메모리 초과**가 발생하였다.

![https://user-images.githubusercontent.com/64006699/98269669-5df69880-1fd1-11eb-9325-3b20d74fe36a.png](https://user-images.githubusercontent.com/64006699/98269669-5df69880-1fd1-11eb-9325-3b20d74fe36a.png)

- 마지막으로 생각한 방법은 스택을사용하는것은 같되, 스택 2개를 사용하는것이 아닌, <번호, 높이>를 인자로 갖는 class를 하나 생성한 후,  그 클래스를 기준으로 스택을 사용하는 것 이었다.

```jsx
class top {
int height;
int number;
```

```
public top(int number, int height) {
	this.number = number;
	this.height = height;
}

```

```jsx
}
```

- 그리고 레이저가 튕기고 돌아온 탑을 찾는 과정은 맨 처음부터 하나하나씩 찾는것으로 구현했다.
1. 맨 처음탑은 왼쪽에 탑이 하나도 없으므로 0을 바로 출력하도록 한 후, 스택에 저장을 한다.
2. 2번째 탑부터는 스택이 빌 때까지 스택의 맨위에 있는 요소와 비교를한다.
- 탑의 높이가 스택의 요소들보다 작은 경우에는 스택의 제일 높은 위치에 있는 요소가 레이져가 튕겨져 나오는 탑이므로 탑의 번호 출력
- 탑의 높이가 스택의 요소들보다 큰 경우에는 스택의 요소들을 사이즈가 0이될때가지 모두 pop
- 만약 스택이 비워져 있다면 기준이 되는 탑보다 큰 탑이 없으므로 0출력

- 이번 코드에서 **핵심키**는 **기준이 되는 탑보다 큰 탑이 있다면 큰 탑의 왼쪽의 요소는 무시**하는것이다. (어차피 튕겨져 나오므로)

```jsx
for (int i = 1; i <= n; i++) {
		if (top.isEmpty()) {
			output.write(String.valueOf(0) + " ");
			top.push(new top(i, Integer.parseInt(st.nextToken())));
		} else {
			temp = Integer.parseInt(st.nextToken());
		while (top.size() > 0) {
			if (top.peek().height > temp) {
				output.write(String.valueOf(top.peek().number) + " ");
				top.push(new top(i, temp));
				break;
			} else {
				top.pop();
			}
		}
		if (top.isEmpty()) {
			output.write(String.valueOf(0) + " ");
			top.push(new top(i, temp));
		}
	}
}
```

# 4. 결과

![https://user-images.githubusercontent.com/64006699/98269073-a9f50d80-1fd0-11eb-83d7-853a4cb02519.png](https://user-images.githubusercontent.com/64006699/98269073-a9f50d80-1fd0-11eb-83d7-853a4cb02519.png)