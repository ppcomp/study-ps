# 8주차 - 16986. 인싸들의 가위바위보

# 1. 문제

[https://www.acmicpc.net/problem/16986](https://www.acmicpc.net/problem/16986)

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
static int[] win = new int[3];
static boolean[] kh; // 낼 수 있는 손동작의 수
static int[][] arr;
static int[][] people;
static int[] turnCount = new int[3];
static int n;
static int k;
static int canWin = 0;
```

```java
public static void dfs(int people1, int people2)
{
	if(win[0] == k) // 경희가 조건을 만족했을경우
	{
		canWin = 1;
	}
	
	if(win[1] == k || win[2] == k) // 다른 사람이 조건을 만족했을 경우
	{
		return;
	}
	
	
	if(people1 == 0 || people2 == 0) // 경희가 포함된 게임일 경우
	{
		for(int i = 0; i < n; i++)
		{
			if(kh[i] == false)
			{
				kh[i] = true;
				int who;
				if(people1 == 0) // people1이 경희일 경우
				{	
					who = whoWin(people1, people2, i, people[people2][turnCount[people2]]);
					turnCount[people2]++;
				}else // people2가 경희일 경우
				{
					who = whoWin(people1, people2, people[people1][turnCount[people1]], i);
					turnCount[people1]++;
				}
				
				if(who != -1) // 무승부가 아닐경우
				{
					win[who]++;
					dfs(who, 3-people1-people2);
					win[who]--;
				}else // 무승부일경우
				{
					win[Math.max(people1, people2)]++;
					dfs(Math.max(people1, people2), 3-people1-people2);
					win[Math.max(people1, people2)]--;
					
				}
						
				if(people1 == 0)
				{
					turnCount[people2]--;
				}else
				{
					turnCount[people1]--;
				}
				kh[i] = false;
			}
		}
	}
	else // 경희가 없는 게임인 경우
	{
		int who =  whoWin(people1, people2, people[people1][turnCount[people1]], people[people2][turnCount[people2]]);
		turnCount[people1]++;
		turnCount[people2]++;
		if(who != -1) // 무승부가 아닐경우
		{
			win[who]++;
			dfs(who, 3-people1-people2);
			win[who]--;	
		}else // 무승부일경우
		{
			win[Math.max(people1, people2)]++;
			dfs(Math.max(people1, people2), 3-people1-people2);
			win[Math.max(people1, people2)]--;
			
		}
		
		turnCount[people1]--;
		turnCount[people2]--;
	}
	
	
}

public static int whoWin(int people1, int people2, int people1Use, int people2Use)
{
	if(arr[people1Use][people2Use] == 2) // people1 Win
	{
		return people1;
	}
	else if(arr[people1Use][people2Use] == 1) // 무승부
	{
		return -1;
	}else // people2 Win
	{
		return people2;
	}
}

public static void main(String[] args) throws IOException
{
	// TODO Auto-generated method stub
	
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	String line = input.readLine();
	StringTokenizer st = new StringTokenizer(line, " ");
	n = Integer.parseInt(st.nextToken());
	k = Integer.parseInt(st.nextToken());
	kh = new boolean[n];
	people = new int[3][20]; // 0 :지우, 1:경희, 2:민호
	arr = new int[n][n];
	
	for(int i = 0; i < n; i++)
	{
		line = input.readLine();
		st = new StringTokenizer(line, " ");
		for(int j = 0; j < n; j++)
		{
			arr[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
	
	for(int i = 1; i <= 2; i++)
	{
		line = input.readLine();
		st = new StringTokenizer(line, " ");
		for(int j = 0; j < 20; j++)
		{
			people[i][j] = Integer.parseInt(st.nextToken()) - 1;
		}
	}
	
	dfs(0, 1);
	output.write(canWin + " ");
	
	
	input.close();
	output.close();
	
	
 }
}

```

# 3. 설명

1. 구현 방법
- 백트래킹을 사용하여 구현했다.
- 문제가 너무 이해가 안되어 시간을 많이 낭비했다.
- 처음에 turnCount를 지정해주지않아 테스트케이스가 틀리게나왔다.

     —> 사람마다 각각 라운드에 무엇을 내는지 정해져있는줄 알았음

     —> 그러나 사람마다 자신이 참여한 게임의 수만큼 무엇을 내는지 정해진것이었음

2.  입력 방법 및 변수 지정

```java
if(win[0] == k) // 경희가 조건을 만족했을경우
{
	canWin = 1;
}
	
if(win[1] == k || win[2] == k) // 다른 사람이 조건을 만족했을 경우
{
	return;
}
```

- 재귀의 탈출 조건은 어떤 사람이 승수를 다 채웠을 경우이다.

```java
if(people1 == 0 || people2 == 0) // 경희가 포함된 게임일 경우
	{
		for(int i = 0; i < n; i++)
		{
			if(kh[i] == false)
			{
				kh[i] = true;
				int who;
				if(people1 == 0) // people1이 경희일 경우
				{	
					who = whoWin(people1, people2, i, people[people2][turnCount[people2]]);
					turnCount[people2]++;
				}else // people2가 경희일 경우
				{
					who = whoWin(people1, people2, people[people1][turnCount[people1]], i);
					turnCount[people1]++;
				}
				
				if(who != -1) // 무승부가 아닐경우
				{
					win[who]++;
					dfs(who, 3-people1-people2);
					win[who]--;
				}else // 무승부일경우
				{
					win[Math.max(people1, people2)]++;
					dfs(Math.max(people1, people2), 3-people1-people2);
					win[Math.max(people1, people2)]--;
					
				}
						
				if(people1 == 0)
				{
					turnCount[people2]--;
				}else
				{
					turnCount[people1]--;
				}
				kh[i] = false;
			}
		}
	}
```

- 경희가 포함된 게임일 경우
- 경희가 낼 수 있는 가위바위보를 판단한다 (kh[i])
- 매개변수 people1이 경희인지, people2가 경희인지 판단하여 가위바위보 시뮬레이션 함수인

       whoWin에 넣고, 경희가 아닌 사람의 turnCount를 증가시킨다.

- 이긴사람을 판단하여 승수1을 더해주고 계속 재귀를 해준다.

      ※ 처음에 무승부일경우에 승수를 안더해주어서 테스트케이스가 틀리게 나왔다.

         무승부일 경우에는 순서가 뒤인 사람이 이긴다는 것을 기억하자.

```java
else // 경희가 없는 게임인 경우
	{
		int who =  whoWin(people1, people2, people[people1][turnCount[people1]], people[people2][turnCount[people2]]);
		turnCount[people1]++;
		turnCount[people2]++;
		if(who != -1) // 무승부가 아닐경우
		{
			win[who]++;
			dfs(who, 3-people1-people2);
			win[who]--;	
		}else // 무승부일경우
		{
			win[Math.max(people1, people2)]++;
			dfs(Math.max(people1, people2), 3-people1-people2);
			win[Math.max(people1, people2)]--;
			
		}
		
		turnCount[people1]--;
		turnCount[people2]--;
	}
```

- 경희가 포함되지 않은 게임의 경우
- 가위바위보 시뮬레이션 함수의 매개변수를 판단해줄 필요가없다. 경희가 포함되있지 않기 때문

```java
public static int whoWin(int people1, int people2, int people1Use, int people2Use)
{
	if(arr[people1Use][people2Use] == 2) // people1 Win
	{
		return people1;
	}
	else if(arr[people1Use][people2Use] == 1) // 무승부
	{
		return -1;
	}else // people2 Win
	{
		return people2;
	}
}
```

- 가위바위보 시뮬레이션 함수이다.
- 두 사람이 낸 people1Use, people2Use 값을 arr배열의 인자로 넣어 누가 이긴지를 판단한다.
- 무승부인경우에는 -1을 반환하고, 승자가 있을경우에는 승자를 반환한다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/101873810-fb457d80-3bca-11eb-9f78-8e33d9844efd.png](https://user-images.githubusercontent.com/64006699/101873810-fb457d80-3bca-11eb-9f78-8e33d9844efd.png)