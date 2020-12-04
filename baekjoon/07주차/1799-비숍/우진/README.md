# 1799 - 비숍

## 1. 개요

[https://www.acmicpc.net/problem/1799](https://www.acmicpc.net/problem/1799)

## 2. 과정

### 1) 2중 for문을 사용한 재귀

```java
for(int i = x; i < num; i++)
{
	for(int j = 0; j < num; j++)
  {
		if(arr[i][j] == 1 && visitL[i+j] == 0 && visitR[i-j + num] == 0)
		{
			visitL[i+j] = 1;
			visitR[i-j + num] = 1;
			arr[i][j] = 5;
			nQueen(i, cnt+1);
			visitL[i+j] = 0;
			visitR[i-j + num] = 0;
			arr[i][j] = 1;
		}
	}
}
```

### 2) 체스판을 나눠서 해결
![캡처_2020_12_04_18_40_17_959](https://user-images.githubusercontent.com/32921283/101149618-88845180-3662-11eb-810b-df1ddc1e2a7b.png)
[https://gamedevlog.tistory.com/30](https://gamedevlog.tistory.com/30)

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week07_1 {
    static int num;
    static int[] max = new int[2];
    static int[][] arr;
    static int[] visitL;
    static int[] visitR;
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        
        num = Integer.parseInt(input.readLine());
        arr = new int[num][num];
        visitL = new int[num + num];
        visitR = new int[num + num];
        for(int i = 0; i < num; i++)
        {
            String[] line = input.readLine().split(" ");
            for(int j = 0; j < num; j++)
                arr[i][j] = Integer.parseInt(line[j]);
        }
        
        nQueen(0, 0, 0, 0);
        nQueen(1, 0, 0, 1);
        System.out.println(max[0] + max[1]);
    }
    public static void nQueen(int x,int y, int count, int color) {
	    if (num <= x) 
	    {
	        y++;
	        if (x % 2 == 0) 
	            x = 1;
	        else 
	            x = 0;
	    }
	    if (num <= y) 
	    {
	        if (count > max[color])
	            max[color] = count;
	        return;
	    }
     
	    if (arr[x][y] == 1 && visitL[x + y + 1] == 0 && visitR[x - y + num] == 0) 
	    {
	        visitL[x + y + 1] = 1;
	        visitR[x - y + num] = 1;
	        nQueen(x + 2, y, count + 1, color);
	        visitL[x + y + 1] = 0;
	        visitR[x - y + num] = 0;
	    }
	    nQueen(x + 2, y, count, color);
	  }
}
```

## 3. 결과
![캡처_2020_12_04_18_54_35_138](https://user-images.githubusercontent.com/32921283/101149649-94701380-3662-11eb-8b89-04dd7cddb7bf.png)