# 2667- 단자번호붙이기

---

## 개요

---

그래프 이론에서 탐색을 사용해보는 문제

## 코드

---

```java
import java.io.*;
import java.util.LinkedList;

public class Baekjoon2667 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        LinkedList<Integer> list = new LinkedList<>(); //단자번호 저장

        //지도 초기화
        for(int i=0;i<n;i++) {
            String temp = br.readLine();
            for(int j=0;j<n;j++) {
                    map[i][j] =temp.charAt(j)-'0';
            }
        }

        //깊이우선 탐색 진행 후 결과값 리스트에 저장
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(map[i][j] == 1) {
                    map[i][j] = -1;
                    list.add(dfs(i,j,1,map));
                }
                    
            }
        }
        list.sort(null);    //리스트 정렬
        bw.write(list.size()+"\n");
        while(!list.isEmpty())
            bw.write(list.poll()+"\n");
        bw.flush();

    }

    public static int dfs(int row, int col, int count, int[][] map) {
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};  //상하좌우 탐색에 사용

        for(int i=0;i<dir.length;i++) {
            int r = row + dir[i][0];
            int c = col + dir[i][1];
            if(r < map.length && c < map.length && r >= 0 && c >= 0) {
                //지도의 인덱스 범위 안에 있을 경우에만
                if(map[r][c] == 1) {
                    map[r][c] = -1;
                    count = dfs(r,c,++count,map);
                }
            }
        }
        return count;
    }
}
```

## 해결 과정

---

이문제는 너비우선탐색(BFS)를 사용하든 깊이우선 탐색(DFS)을 사용하든 상관없는 문제이다. 나는 깊이우선 탐색을 사용했고 깊이우선 탐색 구현엔 스택을 이용하거나 재귀를 이용하는 방법이 있다. 나는 재귀를 이용해 구현하였다.

여기서 중요한 점은 탐색을 진행할 때 탐색을 마친 좌표는 탐색을 했다는 표시를 해주어 다시 탐색하지 않도록 해주는 것이다.

## 결과

---
![image](https://user-images.githubusercontent.com/47655983/99701702-252de780-2ad8-11eb-86f5-c76250266606.png)