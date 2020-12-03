package NM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int[] array = new int[9];
	static boolean[] check = new boolean[8];
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void backT(int count, int n, int m) throws IOException {
		
		if(count > m)
		{
			return ;
		}
		
		if (count == m) {
			for (int i = 0; i < m; i++) {
				output.write(array[i] + " ");
			}
			output.newLine();
		}

		for (int i = 0; i < n; i++) {
			if (check[i] == false) {
				check[i] = true;
				array[count] = i + 1;
				backT(count + 1, n,  m);
				check[i] = false;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String line = input.readLine();

		int n = line.charAt(0) - '0';
		int m = line.charAt(2) - '0';

		backT(0, n, m);

		input.close();
		output.close();

	}

}
