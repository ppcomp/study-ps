package coin2;

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
		int[] dp = new int[k+1];
		
		for(int i = 0; i < n; i++)
		{
			coin[i] = Integer.parseInt(input.readLine());
		}
		
		for(int i = 0; i<=k; i++)
		{
			dp[i] = 99999;
		}
		
		
		

		dp[0] = 0;

		for (int i = 0; i < n; i++)
		{
			for (int j = coin[i]; j <= k; j++)
			{
				dp[j] = Math.min(dp[j], dp[j-coin[i]]+1 );
			}
		}
		
		if(dp[k] == 99999)
		{
			output.write(String.valueOf(-1));	
		}else
		{
			output.write(String.valueOf(dp[k]));
		}

		
		input.close();
		output.close();

	}

}
