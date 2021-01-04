# 11주차 - 키패드 누르기

# 1. 문제

[https://programmers.co.kr/learn/courses/30/lessons/67256](https://programmers.co.kr/learn/courses/30/lessons/67256)

# 2. 코드

```java
class currentHand{
	int row;
	int col;
	
	public currentHand(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
}

public static String solution(int[] numbers, String hand) {
		String answer = "";
		int leftRow = 3;
		int leftCol = 0;
		int rightRow = 3;
		int rightCol = 2;
		HashMap<Integer, currentHand> map =new HashMap<Integer, currentHand>();
		currentHand temp;
	  int number = 1;
    for(int i = 0; i < 3; i++)
    {
    	for(int j = 0; j < 3; j++)
    	{
    		map.put(number++, new currentHand(i, j));
    	}
    }
    
    map.put(0, new currentHand(3,1));
    
    for(int i = 0; i < numbers.length; i++)
    {
    	temp = map.get(numbers[i]);
    	if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7)
    	{
    		answer+="L";
    		leftRow = temp.row;
    		leftCol = temp.col;
    	}else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9)
    	{
    		answer+="R";
    		rightRow = temp.row;
    		rightCol = temp.col;
    	}else
    	{
    		if(Math.abs(temp.row - leftRow) + Math.abs(temp.col - leftCol) > Math.abs(temp.row - rightRow) + Math.abs(temp.col - rightCol))
    		{
    			answer+="R";
        		rightRow = temp.row;
        		rightCol = temp.col;
    		}else if(Math.abs(temp.row - leftRow) + Math.abs(temp.col - leftCol) < Math.abs(temp.row - rightRow) + Math.abs(temp.col - rightCol))
    		{
    			answer+="L";
        		leftRow = temp.row;
        		leftCol = temp.col;
    		}else
    		{
    			if(hand.contentEquals("left"))
    			{
    				answer+="L";
    				leftRow = temp.row;
            		leftCol = temp.col;
    			}else
    			{
    				answer+="R";
            		rightRow = temp.row;
            		rightCol = temp.col;
    			}
    		}
    	}
    }
    return answer;
}
```

# 3. 설명

1. 구현 방법
- class currentHand를 만들어 각 키패드별 row와 col값을 저장
- 각 숫자별 currentHand값을 HashMap에 저장함

2.  입력 방법 및 변수 지정

```java
class currentHand{
	int row;
	int col;
	
	public currentHand(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
}
```

- 키패드의 숫자별 row와 col을 저장하는 class

```java
String answer = "";
int leftRow = 3;
int leftCol = 0;
int rightRow = 3;
int rightCol = 2;
HashMap<Integer, currentHand> map =new HashMap<Integer, currentHand>();
currentHand temp;
int number = 1;
for(int i = 0; i < 3; i++)
{
  for(int j = 0; j < 3; j++)
  {
   	map.put(number++, new currentHand(i, j));
  }
 }
    
 map.put(0, new currentHand(3,1));
    
```

- 맨 처음 왼손, 오른손의 row와 col을 '*' 와 '#'기준으로 둠
- for문으로 각 키패드별 currentHand값 지정 (ex : 1 → (0,0) / 2 → (0,1))

```java
for(int i = 0; i < numbers.length; i++)
{
	temp = map.get(numbers[i]);
	if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7)
	{
		answer+="L";
		leftRow = temp.row;
		leftCol = temp.col;
	}else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9)
	{
		answer+="R";
		rightRow = temp.row;
		rightCol = temp.col;
	}else
	{
	if(Math.abs(temp.row - leftRow) + Math.abs(temp.col - leftCol) > Math.abs(temp.row - rightRow) + Math.abs(temp.col - rightCol))
	{
		answer+="R";
		rightRow = temp.row;
		rightCol = temp.col;
	}else if(Math.abs(temp.row - leftRow) + Math.abs(temp.col - leftCol) < Math.abs(temp.row - rightRow) + Math.abs(temp.col - rightCol))
	{
		answer+="L";
		leftRow = temp.row;
		leftCol = temp.col;
	}else
	{
		if(hand.contentEquals("left"))
		{
			answer+="L";
			leftRow = temp.row;
			leftCol = temp.col;
		}else
		{
			answer+="R";
			rightRow = temp.row;
			rightCol = temp.col;
		}
	}
}
```

- 문제에서 주어진대로 알고리즘 해결
- 가운데열의 문자 2, 5, 8, 0 의 경우 절댓값(각 손의 row -키패드 숫자의 row) + (col과 - col값) 로 비교

# 4. 결과

![https://user-images.githubusercontent.com/64006699/103459841-141dfa80-4d55-11eb-8a41-67adcff62b24.png](https://user-images.githubusercontent.com/64006699/103459841-141dfa80-4d55-11eb-8a41-67adcff62b24.png)