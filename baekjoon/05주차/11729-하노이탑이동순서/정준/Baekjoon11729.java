import java.io.*;

public class Baekjoon11729 {
    public static int count = 0;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        hanoi(n, 1, 2, 3);
        System.out.println(count);
        System.out.println(sb.toString());
    }

    public static void hanoi(int n, int one, int two, int three) {
        count++;
        if(n==1)
            sb.append(one+" "+three+"\n");
        else {
            hanoi(n-1, one, three, two);
            sb.append(one+" "+three+"\n");
            hanoi(n-1, two, one, three);
        }    
    }
}