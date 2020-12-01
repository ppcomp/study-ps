# 2448 - 별 찍기 - 11

---

## 개요

---

프랙탈 모양의 별을 찍는 문제

별모양의 규칙을 알아내는게 제일 어려웠다.

## 코드

---

```java
import java.io.*;

public class Baekjoon2448 {
    public static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int row = n;
        int col = 2*n-1;
        arr = new char[row][col];
        //배열 공백으로 초기화
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
            arr[i][j] = ' ';
            }
        }
        
        star(n,0,col/2);
        
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                bw.write(arr[i][j]);
            }
            bw.newLine();
        }
        bw.flush();
    }

    public static void star(int n, int row, int col) {
        if(n == 3) {
            arr[row][col] = '*';

            arr[row+1][col-1] = '*';
            arr[row+1][col+1] = '*';

            arr[row+2][col-2] = '*';
            arr[row+2][col-1] = '*';
            arr[row+2][col] = '*';
            arr[row+2][col+1] = '*';
            arr[row+2][col+2] = '*';
        }
        else {
            star(n/2,row,col);
            star(n/2,row+(n/2),col-(n/2));
            star(n/2,row+(n/2),col+(n/2));
        }
    }
}
```

## 해결 과정

---

원래 StringBuilder를 이용해 별을 만들고 재귀로 들어가면서 공백을 넣어주는 방식의 코드를 짰었는데 공백 넣어주는 게 너무 어려웠고 이 방식은 별을 찍고난 뒤 뒤에 공백을 안넣어주어 출력형식 오류를 뱉어냈기에 새로운 방법을 찾아보게 되었다.

기존에 만들던 코드

```java
import java.io.*;

public class Main {
    public static char[][] arr;
    public static StringBuilder sb1, sb2, sb3;
    public static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        sb1 = new StringBuilder();
        sb2 = new StringBuilder();
        sb3 = new StringBuilder();
        
        star(n);
        bw.flush();

        
    }

    public static void star(int n) throws IOException{
        if(n == 3) {
            sb1.append("  *  ");
            sb2.append(" * * ");
            sb3.append("*****");
        }
        else {
            blank();
            star(n/2);
            print(n);
            for(int i=0;i<n/6;i++) 
                blank();

            star(n/2);
            blank2();

            star(n/2);
            print(n);
        }
        
    }

    public static void print(int n) throws IOException {
        bw.write(sb1.toString()+"\n");
        bw.write(sb2.toString()+"\n");
        bw.write(sb3.toString()+"\n");

        sb1 = new StringBuilder();
        sb2 = new StringBuilder();
        sb3 = new StringBuilder();

    }

    public static void blank() {
        sb1.append("   ");
        sb2.append("   ");
        sb3.append("   ");
    }

    public static void blank2() {
        sb1.append(" ");
        sb2.append(" ");
        sb3.append(" ");
    }

    public static void nextLine() {
        sb1.append("\n");
        sb2.append("\n");
        sb3.append("\n");
    }

}
```

문제의 출력형식에 별의 뒷부분에도 공백이 있는 걸 보고 전체 사이즈만큼의 배열을 이용한 출력으로 바꾸었다.

![image](https://user-images.githubusercontent.com/47655983/100714710-7a7fc800-33f9-11eb-8f6e-ec27c305348b.png)

입력이 12일 경우를 그림으로 그려보면 위와 같은데

재귀로 들어갔을 때 마지막에 제일 작은 삼각형을 그리게 하였다.

기준좌표는 맨 위 꼭지점인데 이렇게 되면 삼각형을 만드는 코드와 재귀식은 다음과 같다.

```java
public static void star(int n, int row, int col) {
        if(n == 3) {
            arr[row][col] = '*';

            arr[row+1][col-1] = '*';
            arr[row+1][col+1] = '*';

            arr[row+2][col-2] = '*';
            arr[row+2][col-1] = '*';
            arr[row+2][col] = '*';
            arr[row+2][col+1] = '*';
            arr[row+2][col+2] = '*';
        }
        else {
            star(n/2,row,col);
            star(n/2,row+(n/2),col-(n/2));
            star(n/2,row+(n/2),col+(n/2));
        }
}
```

## 결과

---

![image](https://user-images.githubusercontent.com/47655983/100714676-6dfb6f80-33f9-11eb-9abe-0993f22989e6.png)