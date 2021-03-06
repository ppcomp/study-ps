import java.io.*;
import java.util.ArrayList;

public class BuildColumnAndBeam {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] input = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};

        BuildColumnAndBeam b = new BuildColumnAndBeam();
        int[][] result = b.solution(5, input);
        System.out.println(result.toString());
    }

    public int[][] solution(int n, int[][] build_frame) {
        Structure[][] board = new Structure[n+1][n+1];
        ArrayList<int[]> result = new ArrayList<>();

        for (int i = 0; i < board.length; i++)  //초기화
            for (int j = 0; j < board[i].length; j++) 
                board[i][j] = new Structure();
        
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int s = build_frame[i][2];
            int action = build_frame[i][3];
            
            if(action == 1) {   //설치
                if(s == 0) {    //기둥
                    if(isValidColumn(board, x, y)) 
                        board[x][y].setColumn(true);
                }
                else {  //보
                    if(isValidBeam(board, x, y, n)) 
                        board[x][y].setBeam(true);
                }
            }
            else {  //삭제
                if(s == 0) {    //기둥
                    board[x][y].setColumn(false);   //일단 삭제
                    if(!isCheckBoard(board, n))   //현재 설치된 건축물 유효한지 모두 확인
                        board[x][y].setColumn(true);    //조건을 충족 못시켜서 삭제 취소
                }
                else {  //보
                    board[x][y].setBeam(false); //일단 삭제
                    if(!isCheckBoard(board, n))   //현재 설치된 건축물 유효한지 모두 확인
                        board[x][y].setBeam(true);    //조건을 충족 못시켜서 삭제 취소
                }
            }
        }

        for (int i = 0; i < board.length; i++) {    //결과 배열 생성
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j].isColumn()) 
                    result.add(new int[] {i,j,0});
                if(board[i][j].isBeam()) 
                    result.add(new int[] {i,j,1});
            }
        }

        return result.toArray(new int[0][0]);
    }
    
    public boolean isValidColumn(Structure[][] board, int x, int y) {   //기둥 유효성 확인
        if(y == 0)      //바닥 위일 경우
            return true;
        if(board[x][y].isBeam())    //보의 한쪽 끝 부분 위
            return true;
        if(x != 0 && board[x-1][y].isBeam())   //보의 한쪽 끝 부분 위
            return true;
        if(board[x][y-1].isColumn())    //다른 기둥 위
            return true;

        return false;
    }

    public boolean isValidBeam(Structure[][] board, int x, int y, int n) {  //보 유효성 확인
        if(board[x][y-1].isColumn() || board[x+1][y-1].isColumn()) 
            //한쪽 끝 부분이 기둥 위
            return true;
        if(x > 0 && (board[x-1][y].isBeam() && board[x+1][y].isBeam())) 
            //양쪽 끝 부분이 다른 보와 동시에 연결
            return true;
        return false;
    }

    public boolean isCheckBoard(Structure[][] board, int n) {   //전체 구조물 유효성 확인
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j].isBeam() && !isValidBeam(board, i, j, n))
                    return false;
                if(board[i][j].isColumn() && !isValidColumn(board, i, j))
                    return false;
            }
        }
        return true;
    }


}

class Structure {
    private boolean column;
    private boolean beam;

    public Structure() {
        this.column = false;
        this.beam = false;
    }

    public boolean isColumn() {
        return column;
    }

    public void setColumn(boolean column) {
        this.column = column;
    }

    public boolean isBeam() {
        return beam;
    }

    public void setBeam(boolean beam) {
        this.beam = beam;
    }
    
}
