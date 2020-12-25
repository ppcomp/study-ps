import java.io.*;

public class StringCompression {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String test = "aabba";

        int result = solution(test);
        bw.write(result+"");
        bw.flush();

    }    

    public static int solution(String s) {
        int n = s.length();
        String temp1, temp2;
        int count;
        int minLength = n;
        String compressResult;
        for(int i=1;i<=n/2;i++) {
            count = 1;
            compressResult = "";

            temp1 = s.substring(0, i);
            for(int j=i;j<n;j+=i) {
                if(j+i >= n)
                    temp2 = s.substring(j);
                else
                    temp2 = s.substring(j,j+i);

                if(temp1.equals(temp2))
                    count++;
                else {
                    if(count > 1) 
                        compressResult += count + temp1;
                    else
                        compressResult += temp1;
                    temp1 = temp2;
                    count = 1;
                }

                if(j+i >= n) {  //다음 순서가 n보다 크면 결과 문자열 처리
                    if(count > 1)
                        compressResult += count + temp1;
                    else
                        compressResult += temp1;
                }
            }

            if(minLength > compressResult.length()) 
                minLength = compressResult.length();
            
        }
        return minLength;
    }
}
