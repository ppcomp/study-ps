# 10주차 - 문자열 압축

# 1. 문제

[https://programmers.co.kr/learn/courses/30/lessons/60057](https://programmers.co.kr/learn/courses/30/lessons/60057)

# 2. 코드

```java
class Solution {
    public int solution(String s) {
    int answer = 0;
		int count = 1;
		String temp = "";
		String strTemp = "";
		String lastString = "";
		int min = 999;
		while (count <= s.length())
		{
			String getS = s;
			int strCount = 1;
			strTemp = s.substring(0, count);
			getS = s.substring(count, s.length());
			while(getS.length() >= count)
			{
				temp = getS.substring(0,count);
				if(strTemp.equals(temp))
				{
					strCount++;
				}else
				{
					if(strCount == 1)
					{
						lastString += strTemp;
					}else
					{
						lastString += Integer.toString(strCount) + strTemp;
						strCount = 1;
					}
					strTemp = temp;
				}
				getS = getS.substring(count, getS.length());
				
			}
			
			if(strCount == 1)
			{
				lastString += strTemp;
			}else
			{
				lastString += Integer.toString(strCount) + strTemp;
			}
			
			if(!getS.equals(""))
			{
				lastString += getS;
			}
					
			

			if (lastString.length() < min)
			{
				min = lastString.length();
			}
			lastString = "";

			count++;
		}

		answer = min;
		return answer;
    }
}
```

# 3. 설명

1. 구현 방법
- 1개 ~ 입력된 문자열 길이만큼의 갯수를 잘라가며 최소값을 구했다

2.  입력 방법 및 변수 지정

```java
while (count <= s.length())
{
		String getS = s;
		int strCount = 1;
		strTemp = s.substring(0, count);
		getS = s.substring(count, s.length());
		while(getS.length() >= count)
		{
			temp = getS.substring(0,count);
			if(strTemp.equals(temp))
			{
				strCount++;
			}else
			{
				if(strCount == 1)
				{
					lastString += strTemp;
				}else
				{
					lastString += Integer.toString(strCount) + strTemp;
					strCount = 1;
				}
				strTemp = temp;
			}
				getS = getS.substring(count, getS.length());
				
		}
			
		if(strCount == 1)
		{
			lastString += strTemp;
		}else
		{
			lastString += Integer.toString(strCount) + strTemp;
		}
			
		if(!getS.equals(""))
		{
			lastString += getS;
		}
		
		if (lastString.length() < min)
		{
			min = lastString.length();
		}
		lastString = "";

		count++;
	}
```

- while문은 count값을 매개변수로 진행된다. count값은 몇개의 문자열로 나뉘는지에 대한 값이다
- 처음 입력받은 s를 getS라는 String 변수에 저장한다. substring을 하며 진행하기때문에, 입력값을 변하지 않게 하기 위함이다.
- 문자열은 무조건 0번째 문자부터 자른다. 처음 strTemp에 첫번째로 잘리는 문자열값을 저장한다.
- getS는 strTemp에 넣는 문자열의 다음값들을 저장한다. 뒤에 while문에서 계속 갱신을 해줄것이다.
- getS의 길이를 기준으로 while문을 탐색한다.
    - temp에는 비교당할 문자들로 갱신이된다. 예를들어 문자열 "abcabcabcabcdedededede"인 경우 앞에서부터 a, b, c, a ,b 순서로 갱신을한다(count가 1인경우)
    - strTemp는 temp값을 비교하기위한 변수이다. 원래의 값을 가지고있으며, 마지막에 strCount를 더하여 lastString에 저장한다 (ex : "2" + "a"   → strCount :2, strTemp : "a")
    - strTemp와 temp값을 비교해가며 만약 같은경우에 strCount를 1씩 더해가며 진행하고, 다른경우에는 lastString에 현재 strCount값과 strTemp값을 더하여 저장한다
- 마지막에 lastString 문자열의 길이를 기준으로 min값을 갱신해가며 최소값을 구한다.

# 4. 결과

![https://user-images.githubusercontent.com/64006699/103167380-0c021e80-486e-11eb-9ef8-7e0f22d74257.png](https://user-images.githubusercontent.com/64006699/103167380-0c021e80-486e-11eb-9ef8-7e0f22d74257.png)