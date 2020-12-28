# 문자열 압축

---

## 개요

---

같은 문자열이 반복될 경우 숫자와 압축된 문자열로 표기해서 문자열 길이를 줄이는 방법을 찾는 문제

## 코드

---

```java
public class StringCompression {
    public int solution(String s) {
	      int n = s.length();
	      String temp1, temp2;    //문자열 비교를 위한 변수
	      int count;              //같은 문자열의 갯수
	      int minLength = n;      //압축했을 때 최소 길이
	      String compressResult;  //압축한 문자열
	      for(int i=1;i<=n/2;i++) {   //1개부터 n/2개의 문자열 단위로 쪼갬
	          count = 1;
	          compressResult = "";
	
	          temp1 = s.substring(0, i);  //첫번째로 나누는 문자열
	          for(int j=i;j<n;j+=i) {
	              if(j+i >= n)
	                  temp2 = s.substring(j);
	              else
	                  temp2 = s.substring(j,j+i); //비교할 다음 문자열
	
	              if(temp1.equals(temp2)) //비교한 문자열이 같으면 카운트
	                  count++;
	              else {  //비교한 문자열이 다르면 결과 문자열에 저장
	                  if(count > 1) 
	                      compressResult += count + temp1;
	                  else
	                      compressResult += temp1;
	                  temp1 = temp2;  //비교한 두번째 문자열을 첫번째 문자열로 변경
	                  count = 1;  //갯수 초기화
	              }
	
	              if(j+i >= n) {  //다음 순서가 문자열 길이보다 크면 결과 문자열 처리
	                  if(count > 1)
	                      compressResult += count + temp1;
	                  else
	                      compressResult += temp1;
	              }
	          }
	
	          if(minLength > compressResult.length())     //압축한 문자열이 기존 최소길이보다 작으면 변경
	              minLength = compressResult.length();
	          
	      }
	      return minLength;
    }
}
```

## 해결 과정

---

입출력 예#5를 보면 ' 문자열은 제일 앞부터 정해진 길이만큼 잘라야 합니다. '라는 문장을 유의해서 알고리즘을 짜야한다. 이것을 생각안하고 하면 "xabcabc"라는 문자열을 압축해버리는 오답을 내놓을 수 있다. 이것을 고려해서 작성한 것이 위의 코드이다.

## 결과

---

![image](https://user-images.githubusercontent.com/47655983/103121624-5b0a5100-46c0-11eb-9a97-203e209cfc3d.png)