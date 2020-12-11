package sticker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = input.readLine();
		int caseNum = Integer.parseInt(line);

		for (int i = 0; i < caseNum; i++)
		{
			int n = Integer.parseInt(input.readLine());
			StringTokenizer st;
			int[][] array = new int[2][n];
			int[][] dp = new int[2][n];
			for (int j = 0; j < 2; j++)
			{
				line = input.readLine();
				st = new StringTokenizer(line, " ");
				for (int k = 0; k < n; k++)
				{
					array[j][k] = Integer.parseInt(st.nextToken());
				}
			}

			dp[0][0] = array[0][0];
			dp[1][0] = array[1][0];

			for (int k = 1; k < n; k++)
			{
				if (k - 2 < 0)
				{
					dp[0][k] = array[0][k] + dp[1][k - 1];
					dp[1][k] = array[1][k] + dp[0][k - 1];
				}else
				{
					dp[0][k] = array[0][k] + Math.max(dp[1][k - 1], dp[1][k - 2]);
					dp[1][k] = array[1][k] + Math.max(dp[0][k - 1], dp[0][k - 2]);
				}
			}

			output.write(Math.max(dp[0][n - 1], dp[1][n - 1]) + "\n");
		}

		input.close();
		output.close();

	}

}
