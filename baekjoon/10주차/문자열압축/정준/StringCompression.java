import java.io.*;

public class StringCompression {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String test = "aabba";
        StringCompression s = new StringCompression();
        int result = s.solution(test);
        bw.write(result+"");
        bw.flush();

    }    

    public int solution(String s) {
        int n = s.length();
        String temp1, temp2;    //문자열 비교를 위한 변수
        int count;              //같은 문자열의 갯수
        int minLength = n;      //압축했을 때 최소 길이
        String compressResult;  //압축한 문자열
        for(int i=1;i<=n/2;i++) {   //1개부터 n/2개의 문자열 단위로 쪼갬
            count = 1;
            compressResult = "";

            temp1 = s.substring(0, i);  //첫번째로 나누는 문자열
            for(int j=i;j<n;j+=i) {
                if(j+i >= n)
                    temp2 = s.substring(j);
                else
                    temp2 = s.substring(j,j+i); //비교할 다음 문자열

                if(temp1.equals(temp2)) //비교한 문자열이 같으면 카운트
                    count++;
                else {  //비교한 문자열이 다르면 결과 문자열에 저장
                    if(count > 1) 
                        compressResult += count + temp1;
                    else
                        compressResult += temp1;
                    temp1 = temp2;  //비교한 두번째 문자열을 첫번째 문자열로 변경
                    count = 1;  //갯수 초기화
                }

                if(j+i >= n) {  //다음 순서가 문자열 길이보다 크면 결과 문자열 처리
                    if(count > 1)
                        compressResult += count + temp1;
                    else
                        compressResult += temp1;
                }
            }

            if(minLength > compressResult.length())     //압축한 문자열이 기존 최소길이보다 작으면 변경
                minLength = compressResult.length();
            
        }
        return minLength;
    }
}
