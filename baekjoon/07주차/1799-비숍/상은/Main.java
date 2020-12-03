package bishop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main
{
	static int num;
	static int[][] map;
	static int leftRow;
	static int leftCol;
	static int rightRow;
	static int rightCol;
	static int blackMax = 0;
	static int whiteMax = 0;

	public static void blackBackT(int row, int col, int count)
	{
		if (count > blackMax)
		{
			blackMax = count;
		}

		if (col >= num)
		{
			if (row % 2 == 0) // ¦�����ϰ��
			{
				col = 1;
				row++;
			} else // Ȧ�����ΰ��
			{
				col = 0;
				row++;
			}

			if (row == num)
			{
				return;
			}
		}
		if (map[row][col] == 1 && check(row, col))
		{
			map[row][col] = 2; // ���´ٰ� ���� (ex 1: ����� �������, 2: ����� �������� ���)
			blackBackT(row, col + 2, count + 1);
			map[row][col] = 1;
		}

		blackBackT(row, col + 2, count);
	}

	public static void whiteBackT(int row, int col, int count)
	{
		if (count > whiteMax)
		{
			whiteMax = count;
		}

		if (col >= num)
		{
			if (row % 2 == 0) // ¦�����ϰ��
			{
				col = 0;
				row++;
			} else // Ȧ�����ΰ��
			{
				col = 1;
				row++;
			}

			if (row >= num)
			{
				return;
			}

		}

		if (map[row][col] == 1 && check(row, col))
		{
			map[row][col] = 2;
			whiteBackT(row, col + 2, count + 1);
			map[row][col] = 1;
		}

		whiteBackT(row, col + 2, count);

	}

	public static boolean check(int row, int col) // �밢�� üũ, �������� or ����������
	{
		for (int i = 1; i <= row; i++)
		{
			leftRow = row - i;
			leftCol = col - i;
			rightRow = row - i;
			rightCol = col + i;
			if (leftRow >= 0 && leftCol >= 0 && leftCol < num)
			{
				if (map[leftRow][leftCol] == 2) // �밢���� �ִ� ���
				{
					return false;
				}
			}

			if (rightRow >= 0 && rightCol >= 0 && rightCol < num)
			{
				if (map[rightRow][rightCol] == 2) // �밢���� �ִ� ���
				{
					return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = input.readLine();
		num = Integer.parseInt(line);
		map = new int[num][num];
		for (int i = 0; i < num; i++)
		{
			line = input.readLine();
			StringTokenizer st = new StringTokenizer(line, " ");
			for (int j = 0; j < num; j++)
			{
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		blackBackT(0, 0, 0);
		whiteBackT(0, 1, 0);

		output.write(String.valueOf(blackMax + whiteMax));
		input.close();
		output.close();

	}

}
