package tomato;

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
		int canAll = 0; // �� �Ͱ� �� �� �ִ����� ���� ����
		int firstAll = 0; // ó������ �� ���������� �Ǵ��ϱ����� ����

		StringTokenizer st = new StringTokenizer(line, " ");
		int col = Integer.parseInt(st.nextToken());
		int row = Integer.parseInt(st.nextToken());

		Node lastNode = new Node(0, 0, 0);

		int[][] map = new int[row][col];
		int[][] canGo = new int[row][col];

		for (int i = 0; i < row; i++) {
			getMap = input.readLine();
			st = new StringTokenizer(getMap, " ");
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					queue.add(new Node(i, j, 0));
				}
				if (map[i][j] == 0) {
					firstAll = 1;
				}
			}
		}

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			lastNode = node;

			if (node.row - 1 >= 0) {
				if (canGo[node.row - 1][node.col] == 0 && map[node.row - 1][node.col] != -1) // ���� �̵�
				{
					map[node.row - 1][node.col] = 1;
					canGo[node.row - 1][node.col] = 1;
					queue.add(new Node(node.row - 1, node.col, node.count + 1));
				}
			}

			if (node.col - 1 >= 0) {
				if (canGo[node.row][node.col - 1] == 0 && map[node.row][node.col - 1] != -1) // �������� �̵�
				{
					map[node.row][node.col - 1] = 1;
					canGo[node.row][node.col - 1] = 1;
					queue.add(new Node(node.row, node.col - 1, node.count + 1));
				}
			}

			if (node.col + 1 <= col - 1) {
				if (canGo[node.row][node.col + 1] == 0 && map[node.row][node.col + 1] != -1) // �������� �̵�
				{
					map[node.row][node.col + 1] = 1;
					canGo[node.row][node.col + 1] = 1;
					queue.add(new Node(node.row, node.col + 1, node.count + 1));
				}
			}

			if (node.row + 1 <= row - 1) {
				if (canGo[node.row + 1][node.col] == 0 && map[node.row + 1][node.col] != -1) // ���������� �̵�
				{
					map[node.row + 1][node.col] = 1;
					canGo[node.row + 1][node.col] = 1;
					queue.add(new Node(node.row + 1, node.col, node.count + 1));
				}
			}

		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map[i][j] == 0) {
					canAll = 1;
					break;
				}
			}
		}

		if (firstAll == 0) { // ���ʿ� �丶�䰡 �� �;��ִ� ���
			output.write(String.valueOf(0));
		} else {
			if (canAll == 0) { // �� �Ͱ� �� �� �ִ°��
				output.write(String.valueOf(lastNode.count));
			} else // �� �Ͱ� ���ϴ� ���
			{
				output.write(String.valueOf(-1));
			}
		}

		input.close();
		output.close();

	}

}
