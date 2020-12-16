# 9주차 - 1927. 최소 힙

# 1. 문제

[https://www.acmicpc.net/problem/1927](https://www.acmicpc.net/problem/1927)

# 2. 코드

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
```

```java
public class Main
{
public static void main(String[] args) throws IOException
{
BufferedReader input = new BufferedReader(new InputStreamReader([System.in](http://system.in/)));
BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
String line = input.readLine();
int n = Integer.parseInt(line);
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
// PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); 가 최대힙임
	for(int i = 0; i < n; i++)
	{
		int x = Integer.parseInt(input.readLine());
		
		if(x == 0)
		{
			if(!minHeap.isEmpty())
			{
				output.write(minHeap.poll() + "\\n");
			}else
			{
				output.write(String.valueOf(0)+ "\\n");
			}
		}else
		{
			minHeap.add(x);
		}
			
	}
	
	input.close();
	output.close();
 }
}
```

# 3. 설명

1. 구현 방법
- 우선순위큐로 구현하였다.
- 사용 예제 [https://coding-factory.tistory.com/603](https://coding-factory.tistory.com/603)

2.  입력 방법 및 변수 지정

![https://user-images.githubusercontent.com/64006699/102341851-cc207900-3fdb-11eb-91d7-b931bc75be55.png](https://user-images.githubusercontent.com/64006699/102341851-cc207900-3fdb-11eb-91d7-b931bc75be55.png)

- 우선순위 큐 사용 예제이다.

![https://user-images.githubusercontent.com/64006699/102341942-e9554780-3fdb-11eb-9589-45fcb00aa6c5.png](https://user-images.githubusercontent.com/64006699/102341942-e9554780-3fdb-11eb-9589-45fcb00aa6c5.png)

- 우선순위 큐의 동작 과정이다. 7이 들어오면 부모노드와 비교를하며 자신의 자리를 찾아간다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/102341836-c6c32e80-3fdb-11eb-976e-e8ff0a2d72d8.png](https://user-images.githubusercontent.com/64006699/102341836-c6c32e80-3fdb-11eb-976e-e8ff0a2d72d8.png)