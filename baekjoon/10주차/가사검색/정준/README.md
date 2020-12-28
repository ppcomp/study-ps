# 가사검색

---

## 개요

---

문자열을 트라이 자료구조를 통해 저장한 후 탐색하는 문제

## 코드

---

```java
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SearchLyrics {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] words = {"frodo","front","frost","frozen","frame","kakao"};
        String[] queries = {"fro??","????o","fr???","???st","pro?"};

        SearchLyrics s = new SearchLyrics();

        int[] result = s.solution(words, queries);

        for(int num : result)
            bw.write(num+" ");
        bw.newLine();
        bw.flush();
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        Trie[] t = new Trie[10000]; //정방향 트라이
        Trie[] tr = new Trie[10000];//역방향 트라이

        for(int i=0;i<10000;i++) {  //트라이 초기화
            t[i] = new Trie();
            tr[i] = new Trie();
        }

        for(String word : words) {  //문자열의 길이마다 분리해서 트라이에 삽입
            int size = word.length();
            t[size-1].insert(word);
            tr[size-1].insert((new StringBuffer(word)).reverse().toString());
        }

        for(int i=0;i<queries.length;i++) { //결과값 계산
            String query = queries[i];

            if(query.charAt(0) != '?') 
                answer[i] = t[query.length()-1].wildCard(query);
            else 
                answer[i] = tr[query.length()-1].wildCard((new StringBuffer(query)).reverse().toString());
        }

        return answer;
    }
}

class TrieNode {
    private Map<Character,TrieNode> childNodes = new HashMap<>();
    private boolean isLastChar;
    private int count = 0;

    public Map<Character,TrieNode> getChildNodes() {
        return this.childNodes;
    }

    public boolean isLastChar() {
        return this.isLastChar;
    }

    public void setLastChar(boolean isLastChar) {
        this.isLastChar = isLastChar;
    }

    public int getCount() {
        return count;
    }
    
    public void upCount() {
        this.count++;
    }
}

class Trie {
    private TrieNode rootNode;

    public Trie() {
        rootNode = new TrieNode();
    }

    public void insert(String word) {
        TrieNode thisNode = this.rootNode;

        //word 문자열의 각 단어를 확인한다.
        //thisNode 가 word 문자열의 각 단어를 key 로 하는 자식 노드가 존재하지 않을 때만 자식 노드를 생성해준다.
        for(int i=0;i<word.length();i++) {
            thisNode.upCount(); //노드의 카운트 수 올림
            thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }

        thisNode.setLastChar(true);
    }

    public int wildCard(String word) {
        TrieNode thisNode = this.rootNode;
        for(int i=0;i<word.length();i++) {
            if(thisNode == null)    //다음 순서의 노드가 없으면 0반환
                return 0;
            if(word.charAt(i) == '?') //와일드카드 문자가 나오면 노드의 카운트 수를 반환
                return thisNode.getCount();
            
            thisNode = thisNode.getChildNodes().get(word.charAt(i));    //다음 순서의 노드로 이동 없으면 null이 됨
        }
        return 0;
    }

}
```

## 해결 과정

---

맨처음엔 문자열 하나하나마다 비교했는데 이러면 효율성 테스트를 통과하지 못했다.

그래서 찾아보니깐 트라이(Trie)라는 자료구조를 사용하고 해서 트라이에 대해 자세히 알아보게되었고 이를 이용해 코드를 구현하였다.

![image](https://user-images.githubusercontent.com/47655983/103194523-4bd70d80-4923-11eb-81d3-bb87b4db1f01.png)

트라이 자료구조는 문자열을 저장하는 트리모양의 자료구조인데 해당하는 문자를 찾는데 문자열의 길이가 m이면 O(m)정도의 시간복잡도만 필요로 하니 문자열 탐색에 좋은 시간효율을 보여준다.

이 문제를 풀기위해 트라이의 각 노드마다 카운트라는 int형 변수를 저장해두었는데 트라이에 문자열을 삽입할 때 각 노드를 순회하면서 새로 생성하게 된다. 그러므로 노드를 방문할 때마다 카운트를 하나씩 증가시켜서 노드의 자식노드가 총 몇개가 있는지 알 수 있게 된다. 그러면 와일드카드 문자가 들어올 때 바로직전 노드의 카운트 값만 확인하면 되니깐 문제를 해결하기 쉬워진다.

 

## 결과

---

![image](https://user-images.githubusercontent.com/47655983/103195564-e0426f80-4925-11eb-9579-6cee80da99d1.png)