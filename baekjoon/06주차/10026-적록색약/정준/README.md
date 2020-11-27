# 10026 - 적록색약

---

## 개요

---

5주차에 했던 단자번호 붙이기의 심화버전 두번 탐색해야 한다.

## 코드

---

```java
import java.io.*;

public class Baekjoon10026 {
    public static int n;
    public static char[][] paint;
    public static int count1, count2;
    public static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        
        paint = new char[n][n];

        count1 = 0; //첫번째 탐색에서 카운트
        count2 = 0; //두번째 탐색에서 카운트

        //배열 초기화
        String temp;
        for(int i=0;i<n;i++) {
            temp = br.readLine();
            for(int j=0;j<n;j++) 
                paint[i][j] = temp.charAt(j);
        }

        //적록색약이 아닐 때 탐색
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                switch(paint[i][j]) {
                    case 'R':
                        paint[i][j] = '0';
                        dfs1(i,j,'R');
                        count1++;
                        break;
                    case 'G':
                        paint[i][j] = '0';
                        dfs1(i,j,'G');
                        count1++;
                        break;
                    case 'B':
                        paint[i][j] = '1';
                        dfs1(i,j,'B');
                        count1++;
                }
            } 
        }

        //적록색약일 때 탐색
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                switch(paint[i][j]) {
                    case '0':
                        paint[i][j] = '2';
                        dfs2(i,j,'0');
                        count2++;
                        break;
                    case '1':
                        paint[i][j] = '3';
                        dfs2(i,j,'1');
                        count2++;
                }
            }
        }
        bw.write(count1+" "+count2);
        bw.flush();
    }

    public static void dfs1(int row, int col, char color) {
        for(int i=0;i<dir.length;i++) {
            int r = row + dir[i][0];
            int c = col + dir[i][1];

            if(r < n && r >= 0 && c < n && c >=0) {
                if(paint[r][c] == color) {
                    switch(color) {
                    case 'R':
                        paint[r][c] = '0';
                        dfs1(r,c,color);
                        break;
                    case 'G':
                        paint[r][c] = '0';
                        dfs1(r,c,color);
                        break;
                    case 'B':
                        paint[r][c] = '1';
                        dfs1(r,c,color);
                    }
                }
            }
        }
    }

    public static void dfs2(int row, int col, char color) {
        for(int i=0;i<dir.length;i++) {
            int r = row + dir[i][0];
            int c = col + dir[i][1];

            if(r < n && r >= 0 && c < n && c >=0) {
                if(paint[r][c] == color) {
                    switch(color) {
                    case '0':
                        paint[r][c] = '2';
                        dfs2(r,c,color);
                        break;
                    case '1':
                        paint[r][c] = '3';
                        dfs2(r,c,color);
                    }
                }
            }
        }
    }
}
```

## 해결 과정

---

![image](https://user-images.githubusercontent.com/47655983/100427295-97548c80-30d5-11eb-9a11-7579e43bfa6c.png)

첫번째 그림은 입력값을 받은 배열

두번째 그림은 적록색약이 아닌 사람이 탐색한 후의 배열

세번째 그림은 적록색약인 사람이 탐색한 후의 배열

2차원 char 배열에 입력으로 받은 값을 넣어준다. 그리고 탐색을 하는데

R → 0

G → 0

B → 1

위와 같이 바꿔준다. R과 G를 같은 값으로 바꾸는 이유는 적록색약탐색시 둘이 똑같은 걸로 보기 때문

적록색약인 사람이 탐색할 때는 두번째 그림을 탐색하게 되는데

0 → 2

1 → 3

으로 바꿔가며 탐색해주었다.

방문유무를 체크하는 boolean 배열을 만들어서 탐색해도 무방하나

배열하나만 가지고 코드를 작성했다.

## 결과

---

![image](https://user-images.githubusercontent.com/47655983/100428181-f49d0d80-30d6-11eb-95af-2d599a8adc5a.png)