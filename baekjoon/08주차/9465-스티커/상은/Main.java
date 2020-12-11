package coin;

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

		StringTokenizer st = new StringTokenizer(line, " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin = new int[n];
		int[] sum = new int[k + 1];

		for (int i = 0; i < n; i++)
		{
			line = input.readLine();
			coin[i] = Integer.parseInt(line);
		}

		sum[0] = 1;

		for (int i = 0; i < n; i++)
		{
			for (int j = coin[i]; j <= k; j++)
			{
				sum[j] += sum[j - coin[i]];
			}
		}

		output.write(String.valueOf(sum[k]));

		input.close();
		output.close();

	}

}
