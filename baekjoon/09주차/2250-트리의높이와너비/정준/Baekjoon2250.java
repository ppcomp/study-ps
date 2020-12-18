import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon2250 {
    public static Tree[] trees;
    public static int[] levelLeft;
    public static int[] levelRight;

    public static int n;
    public static int root;
    public static int pos;
    public static int maxLevel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());

        trees = new Tree[n];
        levelLeft = new int[n];
        levelRight = new int[n];
        maxLevel = 0;

        for(int i=0;i<n;i++) {
            trees[i] = new Tree(i, -1, -1); //트리 초기화
            levelLeft[i] = n+1;
        }

        StringTokenizer st;
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());

            int value = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            trees[value-1].left = left;
            trees[value-1].right = right;

            if(left != -1)
                trees[left-1].parent = value;
            if(right != -1)
                trees[right-1].parent = value;
        }

        for(int i=0;i<n;i++) {
            if(trees[i].parent == -1)   //루트 노드 탐색
                root = i+1;
        }

        pos = 1;
        inOrder(root, 1);

        int width = 0;
        int widthLevel = 1;
        int temp;
        for(int i=0;i<maxLevel;i++) {
            temp = levelRight[i] - levelLeft[i] + 1;

            if(width < temp) {
                widthLevel = i+1;
                width = temp;
            }
        }

        bw.write(widthLevel+" "+width);
        bw.flush();



    }

    public static void inOrder(int value, int level) {
        Tree node = trees[value-1];

        if(node.left != -1)
            inOrder(node.left, level+1);    //노드의 왼쪽 탐색
        
        levelLeft[level-1] = Math.min(levelLeft[level-1], pos); //기존 값과 pos값 비교
        levelRight[level-1] = pos++;

        if(node.right != -1)
            inOrder(node.right, level+1);   //노드의 오른쪽 탐색

        if(maxLevel < level)    //현재 레벨이 최대레벨보다 크면 최대레벨 갱신
            maxLevel = level;
    }
}

class Tree {
   public int parent;
   public int value;
   public int left;
   public int right;
   
   public Tree(int value, int left, int right) {
       this.parent = -1;
       this.value = value;
       this.left = left;
       this.right = right;
   }


}
