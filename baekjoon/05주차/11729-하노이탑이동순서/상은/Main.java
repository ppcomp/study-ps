package hanoi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
	static int count = 0;
	
	public static void hanoi1(int n, int h1, int h2, int h3) throws IOException {
		if (n == 1) {
		} else {
			hanoi1((n - 1), h1, h3, h2);
			hanoi1((n - 1), h2, h1, h3);
		}
		count++;

	}

	public static void hanoi2(int n, int h1, int h2, int h3) throws IOException {
		if (n == 1) {
			output.write(h1 + " " + h3 + "\n");
		} else {
			hanoi2((n - 1), h1, h3, h2);
			output.write(h1 + " " + h3 + "\n");
			hanoi2((n - 1), h2, h1, h3);
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String line = input.readLine();

		int n = Integer.parseInt(line);

		hanoi1(n, 1, 2, 3);
		output.write(count+"\n");
		hanoi2(n, 1, 2, 3);
		


		input.close();
		output.close();

	}

}
