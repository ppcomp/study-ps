# 5주차 - 9466. 텀 프로젝트

# 1. 문제

[https://www.acmicpc.net/problem/9466](https://www.acmicpc.net/problem/9466)

# 2. 코드

```jsx
package turmP;
```

```jsx
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;
```

```jsx
class link {
int me;
int want;
```

```jsx
public link(int me, int want) {
	this.me = me;
	this.want = want;
 }
}

```

```jsx
public class Main {
```

```jsx
public static void main(String[] args) throws NumberFormatException, IOException {
	// TODO Auto-generated method stub

	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

	Stack<link> stack = new Stack<link>();
	int n = Integer.parseInt(input.readLine());
	int number = 0;
	String line = "";
	StringTokenizer st;
	int formTeam = 0;
	int count = 0;

	for (int i = 0; i < n; i++) {
		number = Integer.parseInt(input.readLine());
		line = input.readLine();
		st = new StringTokenizer(line, " ");
		int[] people = new int[number]; // 원하는 사람을 표시하기 위한 배열
		int[] check = new int[number]; // 재방문을 못하게 하기 위한 배열
		int[] perfectForm = new int[number]; // 팀을 이룬 사람을 표시하기위한 배열 (1:성공, 2:실패)
		HashSet<Integer> hs = new HashSet<Integer>();

		for (int j = 0; j < number; j++) {
			people[j] = Integer.parseInt(st.nextToken()) - 1;
		}

		for (int j = 0; j < number; j++) {
			if (check[j] == 0) { // 재방문을 한적이 없는 사람일 경우
				stack.push(new link(j, people[j]));
				hs.add(j);
				while (formTeam == 0) { // 팀을 이룰때까지 while문 탐색
					check[stack.peek().me] = 1; // 재방문 체크
					if (hs.contains(stack.peek().want)) // 팀이 될 수 있는 경우
					{
						formTeam = 1; // 팀을 이룬 경우 formTeam값을 1로 
						link temp = stack.peek(); // 
						while (!stack.isEmpty()) {
							link temp2 = stack.pop();
							perfectForm[temp2.me] = 1;
							if (temp2.me == temp.want) {
								while(!stack.isEmpty()) // 팀원 찾기에 실패한사람들 처리
								{
									perfectForm[stack.pop().me] = 2;
									count++;
								}
								hs.clear();
							}
						}
					} else // 팀이 될 가능성이 없는 경우
					{
						if (perfectForm[stack.peek().want] == 1 || perfectForm[stack.peek().want] == 2) // 원하는 사람이 이미 팀이 결성 된 경우
						{
							while(!stack.isEmpty()) // 팀원 찾기에 실패한사람들 처리
							{
								perfectForm[stack.pop().me] = 2;
								count++;
							}
							hs.clear();
							break;
						}else { // 원하는 사람이 팀을 이루지 않고, 팀 결성에 실패하지 않은경우(가능성이 존재하는 경우)
							hs.add(stack.peek().want);
							stack.push(new link(stack.peek().want, people[stack.peek().want]));
							
						}

					}
				}

				formTeam = 0;
			}
		}
		output.write(count + "\\n");
		count = 0;

	}

	input.close();
	output.close();

 }
}

```

# 3. 설명

1. 구현 방법
- Stack을 사용하여 DFS로 구현했다. 스택에 넣을때마다 HashSet에도 값을 넣고, 이것을 사용하여 HashSet에 원하는 사람이 나올경우, 스택에서 제거하는식으로 구현했다.

2.  입력 방법 및 변수 지정

```jsx
BufferedReader input = new BufferedReader(new InputStreamReader([System.in](http://system.in/)));
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
Stack<link> stack = new Stack<link>();
int n = Integer.parseInt(input.readLine());
int number = 0;
String line = "";
StringTokenizer st;
int formTeam = 0;
int count = 0;
```

```jsx
for (int i = 0; i < n; i++) {
number = Integer.parseInt(input.readLine());
line = input.readLine();
st = new StringTokenizer(line, " ");
int[] people = new int[number]; // 원하는 사람을 표시하기 위한 배열
int[] check = new int[number]; // 재방문을 못하게 하기 위한 배열
int[] perfectForm = new int[number]; // 팀을 이룬 사람을 표시하기위한 배열 (1:성공, 2:실패)
HashSet<Integer> hs = new HashSet<Integer>();

for (int j = 0; j < number; j++) {
			people[j] = Integer.parseInt(st.nextToken()) - 1;
}
```

- for문을 사용하여 원하는 사람이 무엇인지 people 배열에 넣는다

```jsx
for (int j = 0; j < number; j++) {
	if (check[j] == 0) {
		stack.push(new link(j, people[j]));
		hs.add(j);
		while (formTeam == 0) {
			check[stack.peek().me] = 1;
			if (hs.contains(stack.peek().want)) // 팀이 될 수 있는 경우
			{
				formTeam = 1;
				link temp = stack.peek();
				while (!stack.isEmpty()) {
					link temp2 = stack.pop();
					perfectForm[[temp2.me](http://temp2.me/)] = 1;
						if ([temp2.me](http://temp2.me/) == temp.want) {
							while(!stack.isEmpty()) // 팀원 찾기에 실패한사람들 처리
							{
								perfectForm[stack.pop().me] = 2;
								count++;
							}
						hs.clear();
						}
				}
			} else // 팀이 될 가능성이 없는 경우
				{
					if (perfectForm[stack.peek().want] == 1 || perfectForm[stack.peek().want] == 2) // 원하는 사람이 이미 팀이 결성 된 경우
					{
						while(!stack.isEmpty()) // 팀원 찾기에 실패한사람들 처리
						{
							perfectForm[stack.pop().me] = 2;
							count++;
						}
						hs.clear();
						break;
					}else {
						hs.add(stack.peek().want);
						stack.push(new link(stack.peek().want, people[stack.peek().want]));
					}
				}
		}
		
		formTeam = 0;
	}
 }
output.write(count + "\\n");
count = 0;
}
```

- for문으로 탐색하여 원하는 사람마다 stack에 넣으며 HashSet으로 팀이 완성되는지 체크한다.

    ex) 1 → 2, 2→3, 3→4, 4→2 인 경우

    1, 2, 3 순서로 스택에 넣다가(HashSet에도 1, 2, 3을 넣음)

    4를 넣는 경우에 4가 원하는 2가 HashSet에 존재하므로 팀이 완성

    Stack에서2가 나올때까지 pop()을 하고 완성한 사람들을 perfectForm배열에 저장한다.(값 1)

    Stack에 남은 1은 팀원찾기에 실패한 사람이므로 값을 2로하여 perfectForm배열에 저장한다.

- 마지막에 perfectForm배열에서 팀원찾기에 실패한 사람들의 수를 세어 출력하고 끝을 낸다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/99677680-54832b00-2abd-11eb-98fd-f0e6c63ca8d3.png](https://user-images.githubusercontent.com/64006699/99677680-54832b00-2abd-11eb-98fd-f0e6c63ca8d3.png)