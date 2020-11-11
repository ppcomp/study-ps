# 3주차 - 4889. 안정적인 문자열

# 1. 문제

[https://www.acmicpc.net/problem/4889](https://www.acmicpc.net/problem/4889)

# 2. 코드

```jsx
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;
```

```jsx
public class Main {
public static void main(String[] args) throws IOException{
	// TODO Auto-generated method stub

	
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	Stack<Character> stack = new Stack<Character>();
	String line = "";
	Character token;
	int n = 0;
	int count = 0;
	while(true) {
		line = input.readLine();
		if(line.contains("-")) {
			break;
		}
		
		for(int i = 0; i < line.length(); i++)
		{
			token = line.charAt(i);
			if(token.equals('{'))
			{
				stack.push(token);
				
			}else{
				if(stack.isEmpty())
				{
					stack.push('{');
					count++;
				}
				else
				{
					stack.pop();
				}
			}
			
		}
		
		count += stack.size()/2;
		
		n++;
		stack.clear();
		output.write(String.valueOf(n) +". " + String.valueOf(count));
		output.newLine();
		count = 0;
	}
	
	input.close();
	output.close();
	
 }
}

```

# 3. 설명

1. 구현 방법
- 이번 문제는 스택을 이용하면 괜찮을 듯하여 바로 스택을 이용하여 구현했다.

 

 2.  입력 방법 및 변수 지정

- 입력은 BufferedReader, 출력은 BufferedWriter로 입출력 시간을 줄였다.

```jsx
BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
```

- 문자들을 담아 비교하기위한 **Stack**

```jsx
Stack<Character> stack = new Stack<Character>();
```

- 입력값을 받기위한 **String line**
- 입력줄 수를 받기위한 **int n**
- 몇번째 입력인지 저장하기 위한 **int count**
- 입력값을 받은 후, 문자 하나하나씩 비교하기위한 **Character token**

```jsx
String line = "";
int n = 0;
int count = 0;
Character token;
```

3.  구현

- 입력문자로 '-'를 받으면 while문을 종료한다.

```jsx
if (line.contains("-")) {
	break;
}
```

- for문을 사용하여 입력문자 하나하나씩 접근한다.
- 입력 문자가 '{' 라면 스택에 **push**
- 입력 문자가 '}'이고, 스택이 비어있을 경우, '{'로 바꿔 스택에 **push**, 바꿨기 때문에 **count++**

       → 만약 입력문자가 '}'인데, 스택에 저장된 '{'가 없다면 불완전한놈이므로 바꿔야함

- 입력 문자가 '}'이고, 스택이 비어있지않다면 스택 **pop**

       → 스택에는 '{'만 저장되고, '{' 와 '}'가 만난다면 완전한 쌍임

- 이런식으로 하다보면 마지막에는 스택이 비었거나 스택에 짝수개의 '{'만 남게됨
- 스택에 있는 짝수개의 '{'은 스택의 개수 / 2 만큼 **coun**t에 더함

       → ex) 스택에 {{{{{{ 이 남아있을 경우, 3개의 '{'만 '}'로 변경하면 완전한 쌍 3개가 나옴

```jsx
for (int i = 0; i < line.length(); i++) {
token = line.charAt(i);
if (token.equals('{')) {
stack.push(token);
} else {
				if (stack.isEmpty()) {
					stack.push('{');
					count++;
				} else {
					stack.pop();
				}
			}

		}

		count += stack.size() / 2;

		n++;
		stack.clear();
		output.write(String.valueOf(n) + ". " + String.valueOf(count));
		output.newLine();
		count = 0;
	}
```

# 4. 결과

![https://user-images.githubusercontent.com/64006699/98274061-54236400-1fd6-11eb-8302-628b1c1b6a46.png](https://user-images.githubusercontent.com/64006699/98274061-54236400-1fd6-11eb-8302-628b1c1b6a46.png)