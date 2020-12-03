package sum;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int[] array;
	static int n;
	static int m;
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	static int count = 0;

	public static void backT(int current, int sum) throws IOException {
		
		if(current == n)
		{
			if (sum == m && current != 0) {
				count++;
			}
			else {
				return;
			}
		}else
		{
			backT(current+1, sum+array[current]);
			backT(current+1, sum);
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String line = input.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		array = new int[n];
		
		line = input.readLine();
		st = new StringTokenizer(line, " ");

		
		for(int i = 0; i < n; i++)
		{
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		if(m == 0)
		{
			count--;
		}

		backT(0, 0);
		
		output.write(count+" ");

		input.close();
		output.close();

	}

}
