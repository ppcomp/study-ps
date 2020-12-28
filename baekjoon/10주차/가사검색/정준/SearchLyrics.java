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
        Trie[] t = new Trie[10000];
        Trie[] tr = new Trie[10000];

        for(int i=0;i<10000;i++) {
            t[i] = new Trie();
            tr[i] = new Trie();
        }

        for(String word : words) {
            int size = word.length();
            t[size-1].insert(word);
            tr[size-1].insert((new StringBuffer(word)).reverse().toString());
        }

        for(int i=0;i<queries.length;i++) {
            String query = queries[i];

            if(query.charAt(0) != '?') {
                answer[i] = t[query.length()-1].wildCard(query);
            }
            else {
                answer[i] = tr[query.length()-1].wildCard((new StringBuffer(query)).reverse().toString());
            }
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
            thisNode.upCount();
            thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }

        thisNode.setLastChar(true);
    }

    public int wildCard(String word) {
        TrieNode thisNode = this.rootNode;
        for(int i=0;i<word.length();i++) {
            if(thisNode == null)
                return 0;
            if(word.charAt(i) == '?') {
                return thisNode.getCount();
            }

            thisNode = thisNode.getChildNodes().get(word.charAt(i));
        }
        return 0;
    }

}
