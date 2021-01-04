package test4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Node
{
	int row;
	int col;
	int cost;
	String direction;

	public Node(int row, int col, int cost, String direction)
	{
		this.row = row;
		this.col = col;
		this.cost = cost;
		this.direction = direction;
	}
}

public class Main
{
	static int answer = 9999999;

	public static int solution(int[][] board)
	{
		Queue<Node> queue = new LinkedList<Node>();
		int newCost = 0;

		if (board[1][0] != 1)
		{
			queue.add(new Node(1, 0, 100, "S"));
		}
		if (board[0][1] != 1)
		{
			queue.add(new Node(0, 1, 100, "E"));
		}

		board[0][0] = 1;

		while (!queue.isEmpty())
		{
			Node node = queue.poll();

			if (node.row == board.length - 1 && node.col == board.length - 1)
			{ // 도착
				answer = Math.min(answer, node.cost);
				continue;
			}

			if (node.row - 1 >= 0 && board[node.row - 1][node.col] != 1)
			{ // 위로 이동
				if (node.direction == "N") // 방향 확인 -> 전 방향이 N인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row - 1][node.col] == 0) // 처음 가보는 경우
				{
					board[node.row - 1][node.col] = newCost;
					queue.add(new Node(node.row - 1, node.col, newCost, "N"));

				} else if (board[node.row - 1][node.col] >= newCost)
				{
					board[node.row - 1][node.col] = newCost;
					queue.add(new Node(node.row - 1, node.col, newCost, "N"));
				}

			}

			if (node.col - 1 >= 0 && board[node.row][node.col - 1] != 1)
			{ // 왼쪽 이동

				if (node.direction == "W") // 방향 확인 -> 전 방향이 W인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row][node.col - 1] == 0) // 처음 가보는 경우
				{
					board[node.row][node.col - 1] = newCost;
					queue.add(new Node(node.row, node.col - 1, newCost, "W"));

				} else if (board[node.row][node.col - 1] >= newCost)
				{
					board[node.row][node.col - 1] = newCost;
					queue.add(new Node(node.row, node.col - 1, newCost, "W"));
				}

			}
			if (node.row + 1 < board.length && board[node.row + 1][node.col] != 1)
			{ // 아래로 이동, 벽 확인

				if (node.direction == "S") // 방향 확인 -> 전 방향이 S인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row + 1][node.col] == 0) // 처음 가보는 경우
				{
					board[node.row + 1][node.col] = newCost;
					queue.add(new Node(node.row + 1, node.col, newCost, "S"));

				} else if (board[node.row + 1][node.col] >= newCost) // 만약 newCost가 저장되어 있는 값보다 작다면
				{
					board[node.row + 1][node.col] = newCost;
					queue.add(new Node(node.row + 1, node.col, newCost, "S"));
				}

			}
			if (node.col + 1 < board.length && board[node.row][node.col + 1] != 1)
			{ // 오른쪽으로 이동, 벽 확인

				if (node.direction == "E") // 방향 확인 -> 전 방향이 E인 경우 = 직선, 아닌경우 : 커브
				{
					newCost = node.cost + 100;
				} else
				{
					newCost = node.cost + 600;
				}

				if (board[node.row][node.col + 1] == 0) // 처음 가보는 경우
				{
					board[node.row][node.col + 1] = newCost;
					queue.add(new Node(node.row, node.col + 1, newCost, "E"));

				} else if (board[node.row][node.col + 1] >= newCost)
				{
					board[node.row][node.col + 1] = newCost;
					queue.add(new Node(node.row, node.col + 1, newCost, "E"));
				}

			}

		}

		return answer;
	}

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

		int[][] map = { { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 0 }, { 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 1 }, { 0, 1, 1, 0, 0 } };

		output.write(String.valueOf(solution(map)));

		input.close();
		output.close();

	}

}
