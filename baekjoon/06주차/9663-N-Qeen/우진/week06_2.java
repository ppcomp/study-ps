import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class week06_2 {
    static int num;
    static int count = 0;
    static int[] arr;
    static boolean[] visit;
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        
        num = Integer.parseInt(input.readLine());
        arr = new int[num];
        nQueen(0);
        System.out.println(count);
    }

    public static void nQueen(int deep) {
		if (deep == num) {
			count++;
			return;
		}
 
		for (int i = 0; i < num; i++) {
            arr[deep] = i;
            boolean flag = true;
            for (int j = 0; j < deep; j++) 
            {
                if (arr[deep] == arr[j])
                    flag = false;
                else if (Math.abs(deep - j) == Math.abs(arr[deep] - arr[j]))
                    flag = false;
            }
			if (flag)
				nQueen(deep + 1);
		}
 
	}
}
