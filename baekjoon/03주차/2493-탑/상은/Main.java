package top;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
class top {
	int height;
	int number;

	public top(int number, int height) {
		this.number = number;
		this.height = height;
	}
}
public class Main {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(input.readLine());
		String line = input.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int temp;
		Stack<top> top = new Stack<top>();
		Stack<Integer> solution = new Stack<Integer>(); // 답을 저장할 스택

		for (int i = 1; i <= n; i++) {
			if (top.isEmpty()) {
				output.write(String.valueOf(0) + " ");
				top.push(new top(i, Integer.parseInt(st.nextToken())));
			} else {
				temp = Integer.parseInt(st.nextToken());
				while (top.size() > 0) {
					if (top.peek().height > temp) {
						output.write(String.valueOf(top.peek().number) + " ");
						top.push(new top(i, temp));
						break;
					} else {
						top.pop();
					}
				}
				if (top.isEmpty()) {
					output.write(String.valueOf(0) + " ");
					top.push(new top(i, temp));
				}
			}
		}
		input.close();
		output.close();
	}
}