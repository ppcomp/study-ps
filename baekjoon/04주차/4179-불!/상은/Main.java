package fire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int row;
	int col;
	int count;
	int type;

	public Node(int row, int col, int count, int type) { // type = 1이라면 불
		this.row = row;
		this.col = col;
		this.count = count;
		this.type = type;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		Queue<Node> queue = new LinkedList<Node>();

		String getMap = "";
		String line = input.readLine();

		StringTokenizer st = new StringTokenizer(line, " ");
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int startJRow = 0;
		int startJCol = 0;
		char[][] map = new char[row][col];
		int[][] canGo = new int[row][col];
		int canOut = 0;

		for (int i = 0; i < row; i++) {
			getMap = input.readLine();
			for (int j = 0; j < col; j++) {
				map[i][j] = getMap.charAt(j);
				if (map[i][j] == 'J') {
					startJRow = i;
					startJCol = j;
				} else if (map[i][j] == 'F') {
					queue.add(new Node(i, j, 0, 1));
				}
			}
		}

		queue.add(new Node(startJRow, startJCol, 0, 0));

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.type == 1) // 불일 때
			{
				if (node.row - 1 >= 0) {
					if (map[node.row - 1][node.col] == '.' || map[node.row - 1][node.col] == 'J') { // 위로 이동
						map[node.row - 1][node.col] = 'F';
						queue.add(new Node(node.row - 1, node.col, 0, 1));
					}
				}

				if (node.col - 1 >= 0) {
					if (map[node.row][node.col - 1] == '.' || map[node.row][node.col - 1] == 'J') { // 왼쪽 이동
						map[node.row][node.col - 1] = 'F';
						queue.add(new Node(node.row, node.col - 1, 0, 1));
					}
				}
				if (node.row + 1 <= row - 1) {
					if (map[node.row + 1][node.col] == '.' || map[node.row + 1][node.col] == 'J') { // 아래로 이동
						map[node.row + 1][node.col] = 'F';
						queue.add(new Node(node.row + 1, node.col, 0, 1));
					}
				}
				if (node.col + 1 <= col - 1) {

					if (map[node.row][node.col + 1] == '.' || map[node.row][node.col + 1] == 'J') { // 오른쪽 이동
						map[node.row][node.col + 1] = 'F';
						queue.add(new Node(node.row, node.col + 1, 0, 1));
					}
				}

			} else { // 지훈일 때
				if (node.row - 1 < 0 || node.row + 1 > row - 1 || node.col - 1 < 0 || node.col + 1 > col - 1) { // 영역
																												// 밖으로
																												// 벗어난
																												// 경우
					output.write(String.valueOf(node.count + 1));
					canOut = 1;
					break;

				} else {

					if (canGo[node.row - 1][node.col] == 0 && map[node.row - 1][node.col] == '.') { // 위로 이동
						canGo[node.row - 1][node.col] = 1;
						queue.add(new Node(node.row - 1, node.col, node.count + 1, 0));
					}
					if (canGo[node.row][node.col - 1] == 0 && map[node.row][node.col - 1] == '.') { // 왼쪽 이동
						canGo[node.row][node.col - 1] = 1;
						queue.add(new Node(node.row, node.col - 1, node.count + 1, 0));
					}
					if (canGo[node.row + 1][node.col] == 0 && map[node.row + 1][node.col] == '.') { // 아래로 이동
						canGo[node.row + 1][node.col] = 1;
						queue.add(new Node(node.row + 1, node.col, node.count + 1, 0));
					}
					if (canGo[node.row][node.col + 1] == 0 && map[node.row][node.col + 1] == '.') { // 오른쪽 이동
						canGo[node.row][node.col + 1] = 1;
						queue.add(new Node(node.row, node.col + 1, node.count + 1, 0));
					}
				}
			}
		}

		if (canOut == 0) {
			output.write("IMPOSSIBLE");
		}

		input.close();
		output.close();
	}

}
