import java.io.*;

public class LockAndKey {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] key = {{0,0,0},{1,0,0},{0,1,1}};
        int[][] lock = {{1,1,0,1},{1,1,0,1},{1,0,1,1},{1,1,1,1}};

        LockAndKey l = new LockAndKey();
        boolean result = l.solution(key, lock);
        bw.write(result+"");
        bw.flush();
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;
        int len = n+2*(m-1);
        int[][] board = new int[len][len];
        int[][] temp = new int[len][len];
        int range = n+m-1;

        for(int i=0;i<n;i++)   //보드배열 중앙에 자물쇠 값 저장
            for(int j=0;j<n;j++) 
                board[m-1+i][m-1+j] = lock[i][j];

        temp = arrayCopy(board);    //계산할 때 사용할 배열 복사
        
        if(isUnlock(temp, m-1, m-1, m)) //처음부터 자물쇠가 풀려있는 경우
            return true;

        int rotation = 0;
        do {    //회전을 위한 반복문
            
            for(int i=0;i<range;i++) {
                for(int j=0;j<range;j++) {
                    
                    for(int row=0;row<m;row++)     //키값 대입
                        for(int col=0;col<m;col++) 
                            temp[i+row][j+col] ^= key[row][col];    //xor 계산
                
                    if(isUnlock(temp, m-1, m-1, n)) //계산한 값이 답일 경우 true
                        return true;

                    temp = arrayCopy(board);
                }
            }
            key = rotate(key);  //키값 회전
            rotation++;
        }while(rotation < 4);

        return false;
    }

    public int[][] rotate(int[][] arr) {
        int n = arr.length;
        int[][] result = new int[n][n];

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                result[i][j] = arr[n-1-j][i];
            }
        }
        return result;
    }

    public int[][] arrayCopy(int[][] arr) {
        int[][] result = new int[arr.length][arr[0].length];
        for(int i=0;i<arr.length;i++) 
            System.arraycopy(arr[i], 0, result[i], 0, arr[0].length);
        return result;
    }

    public boolean isUnlock(int[][] board ,int row, int col, int lockLen) {
        for(int i=0;i<lockLen;i++) 
            for(int j=0;j<lockLen;j++) 
                if(board[row+i][col+j] == 0)
                    return false;

        return true;
    }

}
