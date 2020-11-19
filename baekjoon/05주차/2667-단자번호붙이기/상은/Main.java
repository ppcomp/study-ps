package numbering;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;

class Node {
	int row;
	int col;
	int count;

	public Node(int row, int col, int count) {
		this.row = row;
		this.col = col;
		this.count = count;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Node> stack = new Stack<Node>();

		String getMap = "";
		int number = 0;
		int[] groupCount = new int[350];
		int sumRow;
		int sumCol;

		String line = input.readLine();
		int n = Integer.parseInt(line);

		int[][] map = new int[n][n];
		int[][] canGo = new int[n][n];
		
		for(int i = 0; i < n; i++)
		{
			getMap = input.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = getMap.charAt(j) - '0';
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != 0 && canGo[i][j] == 0) {
					stack.add(new Node(i, j, 1));
					while (!stack.isEmpty()) {
						Node node = stack.pop();
						int[][] dir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

						for (int k = 0; k < dir.length; k++) {
							sumRow = node.row + dir[k][0];
							sumCol = node.col + dir[k][1];

							if (0 <= sumRow && sumRow < n && 0 <= sumCol && sumCol < n) {
								if (map[sumRow][sumCol] != 0 && canGo[sumRow][sumCol] == 0) {
									canGo[sumRow][sumCol] = 1;
									groupCount[number]++;
									stack.push(new Node(sumRow, sumCol, node.count + 1));
								}
							}
						}

						
					}
					number++;
				}
			}
		}
		
		int[] sortGroup = new int[number];
		output.write(String.valueOf(number));
		output.newLine();
		for(int i = 0; i < number; i++)
		{
			sortGroup[i] = groupCount[i];
			if(sortGroup[i] == 0)
			{
				sortGroup[i]++;
			}
		}
		
		Arrays.sort(sortGroup);
		
		for(int i = 0; i < number; i++)
		{
			output.write(Integer.toString(sortGroup[i]));
			output.newLine();
		}

		input.close();
		output.close();
	}

}
