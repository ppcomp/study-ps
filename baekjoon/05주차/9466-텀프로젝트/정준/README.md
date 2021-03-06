# 9466 - 텀 프로젝트

---

## 개요

---

인접리스트 탐색으로 풀이하는 어려운 문제

## 코드

---

```java
import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon9466 {
    public static int count;
    public static int[] checked;
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());
        int n;
        StringTokenizer st;
        for(int k=0;k<t;k++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            checked = new int[n];
            count = 0;
            
            st = new StringTokenizer(br.readLine()," ");
            //학생 번호는 사용하기 편하게 0부터 시작함
            for(int i=0;i<n;i++) 
                arr[i] = Integer.parseInt(st.nextToken())-1;

            for(int i=0;i<n;i++) {
                if(checked[i] == 0) //한번도 방문한 적 없는 노드만 탐색함
                    dfs(i,arr,checked);
            }
            bw.write(n-count+"\n");
        }
        bw.flush();
    }

    public static void dfs(int vector, int[] arr, int[] checked) {
        //checked {0: 방문안함, 1: 한번 방문함, -1: 탐색 끝남}
        checked[vector] = 1;
        int next = arr[vector];

        if(checked[next] == 0)
            dfs(next,arr,checked);
        else {
            if(checked[next] == 1) {    //다음 노드에 방문한 적 있음
                count++;
                for(int i=next;i != vector; i = arr[i]) //다음 노드와 연결된 다른 노드들도 같은 팀
                    count++;
            }
        }
        checked[vector] = -1;    //탐색 끝남
    }
}

```

## 해결 과정

---

처음에는 인접리스트로 구현해 깊이우선탐색하며 해결하려했다.

```java
public static void dfs(int v, LinkedList<Integer>[] list, int[] visited, LinkedList<Integer> team) {
        Iterator<Integer> it = list[v].listIterator();
        while(it.hasNext()) {
            int p =it.next();
            if(visited[p] == 0) {
                if(!team.isEmpty()) {
                    if(team.getFirst() == p) {  //끝과 끝이 이어진 경우
                        team.offer(v);
                        while(!team.isEmpty()) 
                            visited[team.poll()] = 1;   
                    }
                    else if(v == p) {   // 자기자신이 이어진 경우
                        while(!team.isEmpty()) 
                            visited[team.poll()] = -1;
                        visited[v] = 1;
                    }
                    else {
                        team.offer(v);
                        dfs(p, list, visited, team);
                    }
                }
                else {
                    team.offer(v);
                    dfs(p, list, visited, team);
                }
                
            }
        }
    }
```

테스트케이스 통과에 실패한 코드이다. 여기서 얻을 수 있던 교훈은 사이클을 만들다가 중간에 끊어지는 경우를 판별하기 힘든 구조였다는 것이다.

문제를 다시 생각해보고 탐색을 해가면서 팀을 이룰 수 없는 학생을 찾기보다는 팀을 이루는 사람을 찾아서 전체 학생수에서 빼는 방법을 생각해보았다.

문제의 예시에서는 다음과 같다

1 2 3 4 5 6 7

3 1 3 7 3 4 6

1번부터 탐색해보자

1→3→3

여기서 추측할 수 있는 점은 한번 방문한 학생을 다시 방문을 하면 사이클을 이룬다고 볼 수 있지 않을까이다.

다음 것도 차례대로 보자

2→1→3→3

3→3

4→7→6→4

5→3→3

6→4→7→6

7→6→4→7

위의 사이클에서 알 수 있는 건 두번이상 나온 학생은 무조건 팀을 이룰 수 있다는 것이다.

이사실을 기반으로 짠 코드가 다음과 같다.

```java
public static void dfs(int vector, int[] arr, int[] checked) {
        //checked {0: 방문안함, 1: 한번 방문함, -1: 탐색 끝남}
        checked[vector] = 1;
        int next = arr[vector];

        if(checked[next] == 0)
            dfs(next,arr,checked);
        else {
            if(checked[next] == 1) {    //다음 노드에 방문한 적 있음
                count++;
                for(int i=next;i != vector; i = arr[i]) //다음 노드와 연결된 다른 노드들도 같은 팀
                    count++;
            }
        }
        checked[vector] = -1;    //탐색 끝남
    }
```

방문 했는지 확인하는 배열 checked가 있다.

한번 탐색하면 1이 되고 다음 노드값도 체크해서 탐색이 끝나면 이 노드는 이제 안쓰는 노드가 되어 -1처리를 해준다.

다음 노드가 한번 방문한 노드이면 카운트를 올려주고 다음노드와 연결된 다른 노드를 순회해서 카운트를 올려준다.

어차피 중복되서 카운트 될 일이 없다. 확인해보자

1→3→3

3번은 사이클을 이뤘으니 카운트가 오르고 1,3 둘다 마지막에 checked는 -1이 된다.

2→/1→3→3

2번은 탐색하려해도 1번 checked배열이 -1이기 때문에 탐색이 안되고 -1 후 끝남

/3→3

4→7→6→4

4번이 두번방문되서 카운트되고 연결괴어있는 7, 6도 카운트 된다. 그리고 4,6,7의 checked는 -1

5→/3→3

/6→4→7→6

/7→6→4→7

전체 학생수에서 팀을 이룬 학생 카운트한 걸 빼면 팀을 이루지 못한 학생 수가 나온다.

결과

---
![image](https://user-images.githubusercontent.com/47655983/99707155-6a094c80-2adf-11eb-87c6-e8c9a4e7bdb5.png)