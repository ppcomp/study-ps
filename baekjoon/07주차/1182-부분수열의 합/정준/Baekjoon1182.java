import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon1182 {
    public static int n;
    public static int s;
    public static int[] arr;    //입력받은 숫자 저장
    public static boolean[] checked;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        
        for(int i=0;i<n;i++) 
            arr[i] = Integer.parseInt(st.nextToken());
        
        checked = new boolean[n];
        powerSet(0, 0);
        
        bw.write(count+"");
        bw.flush();
    }

    public static void powerSet(int k, int sumResult) {
        if(k == n) {   
            if(sumResult == s && isNotNullSet()) {  
            //부분집합의 합이 S와 같고 공집합이 아닐 경우
                count++;
            }
        }
        else {
            checked[k] = false; //k번째 정수가 포함되지 않을 때
            powerSet(k+1,sumResult);

            checked[k] = true;  //k번째 정수가 포함될 때
            sumResult += arr[k];
            powerSet(k+1,sumResult);
        }
    }

    public static boolean isNotNullSet() {  //공집합 유무를 확인
        for(int i=0;i<n;i++) {
            if(checked[i]) 
                return true;
        }
        return false;
    }
}