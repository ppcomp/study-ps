# 10주차 - 괄호 변환

# 1. 문제

[https://programmers.co.kr/learn/courses/30/lessons/60058](https://programmers.co.kr/learn/courses/30/lessons/60058)

# 2. 코드

```java
public static String solution(String p)
{
	String answer = "";
	if(p == "") // 빈 문자열 판단
	{
		return p;
	}
	if(isCorrect(p)) // 올바른 문자열 판단
	{
		return p;
	}else
	{
		int plus = 0;
		int minus = 0;
		String u = "";
		String v = "";
		
		for(int i = 0; i < p.length(); i++)
		{
			if(p.charAt(i) == '(')
			{
				plus++;
			}else
			{
				minus++;
			}
			if(plus == minus) // u, v분리
			{
				u = p.substring(0,i+1);
				v = p.substring(i+1, p.length());
				break;
			}
		}
		
		if(isCorrect(u)) // u가 올바른 문자열인 경우
		{
			answer += u + solution(v);
	
		}else // u가 올바른 문자열이 아닌 경우
		{
			answer += "(";

			answer += solution(v);
			answer += ")";
			u = u.substring(1, u.length()-1);
			u = u.replaceAll("\\\\(","t");
			u = u.replaceAll("\\\\)","\\\\(");
			u = u.replaceAll("t","\\\\)");

			answer += u;
		}
		
		return answer;
	}
}

public static boolean isCorrect(String p)
{
	Stack<Character> st = new Stack<Character>();
	for(int i = 0; i < p.length(); i++)
	{
		if(p.charAt(i) == '(')
		{
			st.push(p.charAt(i));
		}else
		{
			if(st.isEmpty())
			{
				return false;
			}else
			{
				st.pop();
			}
		}
	}
	return true;
}
```

# 3. 설명

1. 구현 방법
- 처음에 전에 풀었던 [3주차 - 4889.안정적인 문자열] 대로 하려다가 멘탈 나감
- 그래서 나와있는 알고리즘대로 해결했다.

![https://user-images.githubusercontent.com/64006699/103167412-5a172200-486e-11eb-8505-ce54947babe1.png](https://user-images.githubusercontent.com/64006699/103167412-5a172200-486e-11eb-8505-ce54947babe1.png)

2.  입력 방법 및 변수 지정

```java
public static String solution(String p)
{
	String answer = "";
	if(p == "") // 빈 문자열 판단
	{
		return p;
	}
	if(isCorrect(p)) // 올바른 문자열 판단
	{
		return p;
	}else
	{
		int plus = 0;
		int minus = 0;
		String u = "";
		String v = "";
		
		for(int i = 0; i < p.length(); i++)
		{
			if(p.charAt(i) == '(')
			{
				plus++;
			}else
			{
				minus++;
			}
			if(plus == minus) // u, v분리
			{
				u = p.substring(0,i+1);
				v = p.substring(i+1, p.length());
				break;
			}
		}
		
		if(isCorrect(u)) // u가 올바른 문자열인 경우
		{
			answer += u + solution(v);
	
		}else // u가 올바른 문자열이 아닌 경우
		{
			answer += "(";

			answer += solution(v);
			answer += ")";
			u = u.substring(1, u.length()-1);
			u = u.replaceAll("\\\\(","t");
			u = u.replaceAll("\\\\)","\\\\(");
			u = u.replaceAll("t","\\\\)");

			answer += u;
		}
		
		return answer;
	}
}
```

- 입력값 s가 빈 문자열일 경우 그대로 return
- isCorrect로 올바른 문자열 판단을 하여 올바른 문자열인 경우에 그대로 return
- 올바른 문자열이 아닌경우 u와 v로 문자열을 나눔
- u가 올바른 문자열일 경우 그대로 u를 붙이고 v를 매개변수로 재귀호출
- u가 올바른 문자열이 아닌경우 "(" + solution(v) + ")" + 맨앞뒤빼고 문자 뒤집은 u

```java
public static boolean isCorrect(String p)
{
	Stack<Character> st = new Stack<Character>();
	for(int i = 0; i < p.length(); i++)
	{
		if(p.charAt(i) == '(')
		{
			st.push(p.charAt(i));
		}else
		{
			if(st.isEmpty())
			{
				return false;
			}else
			{
				st.pop();
			}
		}
	}
	return true;
}
```

- 문자열이 올바른 문자열인지 확인하는 함수 isCorrect
- 스택을 사용하여 순서를 확인한다. 순서는 "(" 가 있으면 뒤에 ")"가 나타나야함
- 코테에서는 문제가 안되었으나 생각해보니 마지막에 스택이 비어있지않으면 올바른 문자열이 아님. 그러나 코테에서는 "("와 ")"이 동등한 갯수로 주어지므로 문제없음

# 4. 결과

![https://user-images.githubusercontent.com/64006699/103167481-bda14f80-486e-11eb-8079-fa4f1c81b331.png](https://user-images.githubusercontent.com/64006699/103167481-bda14f80-486e-11eb-8079-fa4f1c81b331.png)