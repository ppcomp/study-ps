import java.io.*;

public class Baekjoon1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Heap h = new Heap();
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++) {
            int num = Integer.parseInt(br.readLine());
            if(num > 0)
                h.push(num);
            else 
                bw.write(h.pop()+"\n");
        }

        bw.flush();
    }

}

class Heap {
    private int[] heap = new int[100001];
    private int size = 0;

    public void push(int x) {
        heap[++size] = x;   //값 삽입
        int index = size;   
        while(index > 1 && heap[index/2] > heap[index]) {   
            //인덱스가 2이상이고 인덱스의 절반 값이 인덱스값보다 크면
            int temp = heap[index/2];
            heap[index/2] = heap[index];
            heap[index] = temp; //서로 값 교환
            index /= 2; //인덱스 절반으로 나눔 (이진 탐색)
        }
    }

    public int pop() {
        if(size != 0) {
            int result = heap[1];   
            //결과값으로 두번째 값을 뽑는 이유는 디폴트값 0이 있어서 무조건 첫번째는 0이기 때문
            heap[1] = heap[size--];
            //맨 끝에 있는 숫자를 맨 앞으로 이동
            int index = 1;
            int next;
            while(true) {
                next = index * 2;
                if(next<size && heap[next] > heap[next+1])
                    //사이즈를 넘지않거나 넥스트 값이 넥스트+1 값보다 클 때
                    next++;
                if(next>size || heap[next] > heap[index])   
                    //사이즈를 넘거나 넥스트 값이 인덱스 값보다 더 클 때
                    break;
                int temp = heap[index];
                heap[index] = heap[next];
                heap[next] = temp;  //서로 값 교환
                index = next;   //인덱스를 넥스트 값으로 변경
            }
            return result;
        } 
        return 0;
    }
}
