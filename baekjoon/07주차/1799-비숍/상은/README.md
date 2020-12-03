# 7주차 - 1779. 비숍

# 1. 문제

[https://www.acmicpc.net/problem/1799](https://www.acmicpc.net/problem/1799)

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
	static int num;
	static int[][] map;
	static int leftRow;
	static int leftCol;
	static int rightRow;
	static int rightCol;
	static int blackMax = 0;
	static int whiteMax = 0;
```

```java
public static void blackBackT(int row, int col, int count)
{
	if (count > blackMax)
	{
		blackMax = count;
	}

	if (col >= num)
	{
		if (row % 2 == 0) // 짝수줄일경우
		{
			col = 1;
			row++;
		} else // 홀수줄인경우
		{
			col = 0;
			row++;
		}

		if (row == num)
		{
			return;
		}
	}
	if (map[row][col] == 1 && check(row, col))
	{
		map[row][col] = 2; // 놓는다고 가정 (ex 1: 비숍을 놓은경우, 2: 비숍을 놓지않은 경우)
		blackBackT(row, col + 2, count + 1);
		map[row][col] = 1;
	}

	blackBackT(row, col + 2, count);
}

public static void whiteBackT(int row, int col, int count)
{
	if (count > whiteMax)
	{
		whiteMax = count;
	}

	if (col >= num)
	{
		if (row % 2 == 0) // 짝수줄일경우
		{
			col = 0;
			row++;
		} else // 홀수줄인경우
		{
			col = 1;
			row++;
		}

		if (row >= num)
		{
			return;
		}

	}

	if (map[row][col] == 1 && check(row, col))
	{
		map[row][col] = 2;
		whiteBackT(row, col + 2, count + 1);
		map[row][col] = 1;
	}

	whiteBackT(row, col + 2, count);

}

public static boolean check(int row, int col) // 대각선 체크, 왼쪽위로 or 오른쪽위로
{
	for (int i = 1; i <= row; i++)
	{
		leftRow = row - i;
		leftCol = col - i;
		rightRow = row - i;
		rightCol = col + i;
		if (leftRow >= 0 && leftCol >= 0 && leftCol < num)
		{
			if (map[leftRow][leftCol] == 2) // 대각선에 있는 경우
			{
				return false;
			}
		}

		if (rightRow >= 0 && rightCol >= 0 && rightCol < num)
		{
			if (map[rightRow][rightCol] == 2) // 대각선에 있는 경우
			{
				return false;
			}
		}
	}

	return true;
}

public static void main(String[] args) throws IOException
{
	// TODO Auto-generated method stub

	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	String line = input.readLine();
	num = Integer.parseInt(line);
	map = new int[num][num];

	for (int i = 0; i < num; i++)
	{
		line = input.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		for (int j = 0; j < num; j++)
		{
			map[i][j] = Integer.parseInt(st.nextToken());
		}
	}

	blackBackT(0, 0, 0);
	whiteBackT(0, 1, 0);

	output.write(String.valueOf(blackMax + whiteMax));
	input.close();
	output.close();

 }
}
```

# 3. 설명

1. 구현 방법
- 백트래킹을 사용하여 해결했다.
- 처음에 그냥 백트래킹을 다 써버려서 하려했으나 시간복잡도가 심하다고 생각되어 체스판 기준으로 검은색 하얀색을 나누어 백트래킹을 했다.

2.  입력 방법 및 변수 지정

```java
public static void blackBackT(int row, int col, int count)
{
	if (count > blackMax)
	{
		blackMax = count;
	}

	if (col >= num)
	{
		if (row % 2 == 0) // 짝수줄일경우
		{
			col = 1;
			row++;
		} else // 홀수줄인경우
		{
			col = 0;
			row++;
		}

		if (row == num)
		{
			return;
		}
	}
	if (map[row][col] == 1 && check(row, col))
	{
		map[row][col] = 2; // 놓는다고 가정 (ex 1: 비숍을 놓은경우, 2: 비숍을 놓지않은 경우)
		blackBackT(row, col + 2, count + 1);
		map[row][col] = 1;
	}

	blackBackT(row, col + 2, count);
}
```

- 검은색 부분만을 하는 재귀함수이다
- (0,0)을 시작으로 재귀를 한다. if문에서 열이 주어진 num을 넘어가면 다음 행으로 이동한다.
- if (map[row][col] == 1 && check(row, col)) 문으로 비숍을 놓을 수 있는지 판단하여 진행한다.

      → 놓을 수 있다면 map[row][col]값을 2로 변화시켜 다시 재귀를 진행한다.

      → 후에 다시 map[row][col]값을 1로 다시 변화시켜 비숍을 놓지 않을때를 고려한다.

      → blackBackT(row, col + 2, count); 문장으로 비숍을 놓지 않고 진행할 경우도 재귀를 진행한다.

- whilteBackT 함수도 이와 동일하기때문에 넘어간다

![https://user-images.githubusercontent.com/64006699/101077141-bb3d3400-35e7-11eb-82e9-0753fed5dd84.png](https://user-images.githubusercontent.com/64006699/101077141-bb3d3400-35e7-11eb-82e9-0753fed5dd84.png)

```java
public static boolean check(int row, int col) // 대각선 체크, 왼쪽위로 or 오른쪽위로
{
	for (int i = 1; i <= row; i++)
	{
		leftRow = row - i;
		leftCol = col - i;
		rightRow = row - i;
		rightCol = col + i;
		if (leftRow >= 0 && leftCol >= 0 && leftCol < num)
		{
			if (map[leftRow][leftCol] == 2) // 대각선에 있는 경우
			{
				return false;
			}
		}

		if (rightRow >= 0 && rightCol >= 0 && rightCol < num)
		{
			if (map[rightRow][rightCol] == 2) // 대각선에 있는 경우
			{
				return false;
			}
		}
	}

	return true;
}
```

- 비숍을 놓을 수 있는지 check하는 함수이다.
- 인자로 받는 row, col을 기준으로 왼쪽위 대각선과 오른쪽위 대각선으로 구분한다.
- 만악 왼쪽위 대각선이나 오른위 대각선에 비숍이 놓아져있으면 false를 반환한다.

```java
if (map[row][col] == 1 && check(row, col))
	{
		map[row][col] = 2; // 놓는다고 가정 (ex 1: 비숍을 놓은경우, 2: 비숍을 놓지않은 경우)
		blackBackT(row, col + 2, count + 1);
		map[row][col] = 1;
	}
else{
	blackBackT(row, col + 2, count);
}
```

- 처음에 위의방식처럼 check를 못할때 else문으로 걸러 백트래킹을 했는데, 이렇게하면 무조건 될때만 재귀를 하므로 밑의 상황처럼 비숍을 안놓아 '틀렸습니다' 가 나왔다. 비숍을 놓을 수 있음에도 놓지 않고 다음으로 넘어갈때까지 고려를 해야한다.

![https://user-images.githubusercontent.com/64006699/101077509-46b6c500-35e8-11eb-9d5b-a52792434c96.png](https://user-images.githubusercontent.com/64006699/101077509-46b6c500-35e8-11eb-9d5b-a52792434c96.png)

# 4. 결과

![https://user-images.githubusercontent.com/64006699/101076031-2423ac80-35e6-11eb-9a01-6b9a5700183e.png](https://user-images.githubusercontent.com/64006699/101076031-2423ac80-35e6-11eb-9a01-6b9a5700183e.png)