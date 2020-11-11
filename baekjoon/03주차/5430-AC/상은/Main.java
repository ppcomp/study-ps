package ac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(input.readLine());
		Deque<Integer> deque = new LinkedList<Integer>();
		String func = "";
		String line = "";
		int change = 1; // change가 1인경우 : 정방향, -1인경우 : 역방향
		int number;
		int error = 0;

		for (int i = 0; i < n; i++) {
			func = input.readLine();
			number = Integer.parseInt(input.readLine());
			line = input.readLine();
			line = line.replace("[", "");
			line = line.replace("]", "");
			line = line.replace(" ", "");
			StringTokenizer st = new StringTokenizer(line, ",");
			while (st.hasMoreTokens()) {
				deque.addLast(Integer.parseInt(st.nextToken()));
			}
			for (int j = 0; j < func.length(); j++) {
				switch (func.charAt(j)) {
				case 'R':
					change *= -1;
					break;
				case 'D':
					if (!deque.isEmpty()) {
						if (change == 1) {
							deque.removeFirst();
						} else {
							deque.removeLast();
						}
					} else {
						error = 1;
					}
					break;
				}

			}

			if (error == 0) {
				output.write("[");
			}
			else {
				output.write("error");
			}
			while (!deque.isEmpty()) {
				if (change == 1 && deque.size() != 1) {
					output.write(String.valueOf(deque.removeFirst()) + ",");
				} else if (change == -1 && deque.size() != 1) {
					output.write(String.valueOf(deque.removeLast()) + ",");
				} else {
					output.write(String.valueOf(deque.removeLast()));
				}
			}
			if (error == 0) {
				output.write("]");
			}

			deque.clear();
			error = 0;
			change = 1;
			output.newLine();
		}

		input.close();
		output.close();

	}

}
