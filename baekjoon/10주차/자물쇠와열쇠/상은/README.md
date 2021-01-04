# 10주차 - 자물쇠와 열쇠

# 1. 문제

[https://programmers.co.kr/learn/courses/30/lessons/60059](https://programmers.co.kr/learn/courses/30/lessons/60059)

# 2. 코드

```java
public static boolean solution(int[][] key, int[][] lock)
{
	int m = key.length;
	int n = lock.length;	

	int[][] map = new int[m + 2 * n - 2][m + 2 * n - 2];
	int[][] temp =  new int[m + 2 * n - 2][m + 2 * n - 2];

	for (int i = 0; i < n; i++) // lock 중앙에 저장
	{
		for (int j = 0; j < n; j++)
		{
			map[m - 1 + i][m - 1 + j] = lock[i][j];
			temp[m - 1 + i][m - 1 + j] = lock[i][j];
		}
	}
	

	for (int i = 0; i < 4; i++) // rotate하며 자물쇠 확인
	{
		if (i != 0)
		{
			key = rotate(key);
		}

		for (int j = 0; j < m + n - 1; j++)
		{
			for (int k = 0; k < m + n -1; k++)
			{
				for(int a = 0; a < m; a++)
				{
					for(int b = 0; b < m; b++)
					{
						map[j+a][k+b] ^= key[a][b];  // xor연산, 다르면 1 같으면 0
					} 
				}
				
				if(unlock(m-1, m-1, n, map))
				{
					return true;
				}	
				arraycopy(temp, map);
					
			}
		}
		
	}

	return false;
}

public static boolean unlock(int row, int col, int n, int[][] map)
{
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			if (map[row + i][row + j] == 0)
			{
				return false;
			}
		}
	}

	return true;
}

public static int[][] rotate(int[][] arr)
{
	int n = arr.length;
	int[][] rotate = new int[n][n];

	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			rotate[i][j] = arr[n - 1 - j][i];
		}
	}
	return rotate;
}

public static void arraycopy(int[][] array, int[][] copyarray)
{
	for(int i = 0; i < array.length; i++)
	{
		System.arraycopy(array[i], 0, copyarray[i], 0, array[i].length);
	}
}
```

# 3. 설명

1. 구현 방법
- XOR연산 사용, arraycopy 사용

2.  입력 방법 및 변수 지정

![https://user-images.githubusercontent.com/64006699/103453953-5d088b80-4d22-11eb-8320-ff33feca783a.png](https://user-images.githubusercontent.com/64006699/103453953-5d088b80-4d22-11eb-8320-ff33feca783a.png)

- map가운데에 자물쇠를 그대로 복사한다.
- map에 key를 하나하나씩 옮겨가며 복사하고, 자물쇠와 XOR연산을하여 매번 자물쇠가 풀리는지 판단하고, 풀리지 않았다면 1번 상태를 temp배열을 사용하여 그대로 돌아간다.
- rotate는 key의 90도 회전을 위한 함수이며, 총 3번 실행된다.

```java
public static void arraycopy(int[][] array, int[][] copyarray)
{
	for(int i = 0; i < array.length; i++)
	{
		System.arraycopy(array[i], 0, copyarray[i], 0, array[i].length);
	}
}
```

- 처음에 temp배열에 map의 원소들을 하나하나 복사하려고 temp = map 이라는 구문을 사용했는데, 이 구문을 사용하면 map의 주소를 그대로 가져오는것이라 map의 값이 변경되면 temp배열의 값도 같이 변경되는 오류가 있었다.
- 위의 함수를 사용하여 arraycopy 함수로 복사를 해주면 원래 생각한 그대로 작동이 된다.
- [https://blog.naver.com/bcs0509/60154241195](https://blog.naver.com/bcs0509/60154241195) 참고

# 4. 결과

![https://user-images.githubusercontent.com/64006699/103454026-fafc5600-4d22-11eb-94b8-e3548386b1f0.png](https://user-images.githubusercontent.com/64006699/103454026-fafc5600-4d22-11eb-94b8-e3548386b1f0.png)