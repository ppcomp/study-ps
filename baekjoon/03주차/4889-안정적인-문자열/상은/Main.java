package stable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Character> stack = new Stack<Character>();
		String line = "";
		Character token;
		int n = 0;
		int count = 0;
		while (true) {
			line = input.readLine();
			if (line.contains("-")) {
				break;
			}

			for (int i = 0; i < line.length(); i++) {
				token = line.charAt(i);
				if (token.equals('{')) {
					stack.push(token);

				} else {
					if (stack.isEmpty()) {
						stack.push('{');
						count++;
					} else {
						stack.pop();
					}
				}

			}

			count += stack.size() / 2;

			n++;
			stack.clear();
			output.write(String.valueOf(n) + ". " + String.valueOf(count));
			output.newLine();
			count = 0;
		}

		input.close();
		output.close();

	}

}
