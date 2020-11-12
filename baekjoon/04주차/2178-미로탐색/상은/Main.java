package maze;

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

	public Node(int row, int col, int count) { // type = 1�̶�� ��
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
		Queue<Node> queue = new LinkedList<Node>();

		String line = input.readLine();
		String getMap = "";

		StringTokenizer st = new StringTokenizer(line, " ");
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());

		int[][] map = new int[row][col];
		int[][] canGo = new int[row][col];

		for (int i = 0; i < row; i++) {
			getMap = input.readLine();
			for (int j = 0; j < col; j++) {
				map[i][j] = getMap.charAt(j) - '0';
			}
		}

		queue.add(new Node(0, 0, 1));

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.row == row - 1 && node.col == col - 1) { // ����
				output.write(String.valueOf(node.count));
			} else {
				if (node.row - 1 >= 0) {
					if (map[node.row - 1][node.col] == 1 && canGo[node.row - 1][node.col] == 0) { // ���� �̵�
						canGo[node.row - 1][node.col] = -1;
						queue.add(new Node(node.row - 1, node.col, node.count + 1));
					}
				}
				if (node.col - 1 >= 0) {
					if (map[node.row][node.col - 1] == 1 && canGo[node.row][node.col - 1] == 0) { // �������� �̵�
						canGo[node.row][node.col - 1] = -1;
						queue.add(new Node(node.row, node.col - 1, node.count + 1));
					}
				}
				if (node.row + 1 <= row - 1) {
					if (map[node.row + 1][node.col] == 1 && canGo[node.row + 1][node.col] == 0) { // �Ʒ��� �̵�
						canGo[node.row + 1][node.col] = -1;
						queue.add(new Node(node.row + 1, node.col, node.count + 1));
					}
				}
				if (node.col + 1 <= col - 1) {
					if (map[node.row][node.col + 1] == 1 && canGo[node.row][node.col + 1] == 0) { // ������ �̵�
						canGo[node.row][node.col + 1] = -1;
						queue.add(new Node(node.row, node.col + 1, node.count + 1));
					}
				}
			}
		}

		input.close();
		output.close();

	}

}
