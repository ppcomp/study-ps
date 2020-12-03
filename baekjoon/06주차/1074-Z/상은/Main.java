package Z;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int count = 0;
	static boolean progress = true;
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void Z(int x, int y, int size, int r, int c) throws IOException
	{
		if(progress == true)
		{
			if(size == 2)
			{
				if(x == r && y == c)
				{
					progress = false;
					output.write(count+" ");
				}
				count++;
				if(x == r && y+1 == c)
				{
					progress = false;
					output.write(count+" ");
				}
				count++;
				if(x+1 == r && y == c)
				{
					progress = false;
					output.write(count+" ");
				}
				count++;
				if(x+1 == r && y+1 == c)
				{
					progress = false;
					output.write(count+" ");
				}
				count++;
			}else
			{
				Z(x, y, size/2, r, c); // 1»çºÐ¸é Å½»ö
				Z(x, y+size/2, size/2, r, c); // 2»çºÐ¸é Å½»ö
				Z(x+size/2, y, size/2, r, c); // 3»çºÐ¸é Å½»ö
				Z(x+size/2, y+size/2, size/2, r, c); // 4»çºÐ¸é Å½»ö
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		String line = input.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int size = (int) Math.pow(2, n);
		
		Z(0, 0, size, r, c);
		
		
		input.close();
		output.close();
		
	}

}
