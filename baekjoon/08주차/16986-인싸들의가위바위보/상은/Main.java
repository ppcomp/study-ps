package insider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main
{
	static int[] win = new int[3];
	static boolean[] kh; // �� �� �ִ� �յ����� ��
	static int[][] arr;
	static int[][] people;
	static int[] turnCount = new int[3];
	static int n;
	static int k;
	static int canWin = 0;
	
	public static void dfs(int people1, int people2)
	{
		if(win[0] == k) // ���� ������ �����������
		{
			canWin = 1;
		}
		
		if(win[1] == k || win[2] == k) // �ٸ� ����� ������ �������� ���
		{
			return;
		}
		
		
		if(people1 == 0 || people2 == 0) // ���� ���Ե� ������ ���
		{
			for(int i = 0; i < n; i++)
			{
				if(kh[i] == false)
				{
					kh[i] = true;
					int who;
					if(people1 == 0) // people1�� ������ ���
					{	
						who = whoWin(people1, people2, i, people[people2][turnCount[people2]]);
						turnCount[people2]++;
					}else // people2�� ������ ���
					{
						who = whoWin(people1, people2, people[people1][turnCount[people1]], i);
						turnCount[people1]++;
					}
					
					if(who != -1) // ���ºΰ� �ƴҰ��
					{
						win[who]++;
						dfs(who, 3-people1-people2);
						win[who]--;
					}else // ���º��ϰ��
					{
						win[Math.max(people1, people2)]++;
						dfs(Math.max(people1, people2), 3-people1-people2);
						win[Math.max(people1, people2)]--;
						
					}
							
					if(people1 == 0)
					{
						turnCount[people2]--;
					}else
					{
						turnCount[people1]--;
					}
					kh[i] = false;
				}
			}
		}
		else // ���� ���� ������ ���
		{
			int who =  whoWin(people1, people2, people[people1][turnCount[people1]], people[people2][turnCount[people2]]);
			turnCount[people1]++;
			turnCount[people2]++;
			if(who != -1) // ���ºΰ� �ƴҰ��
			{
				win[who]++;
				dfs(who, 3-people1-people2);
				win[who]--;	
			}else // ���º��ϰ��
			{
				win[Math.max(people1, people2)]++;
				dfs(Math.max(people1, people2), 3-people1-people2);
				win[Math.max(people1, people2)]--;
				
			}
			
			turnCount[people1]--;
			turnCount[people2]--;
		}
		
		
	}
	
	public static int whoWin(int people1, int people2, int people1Use, int people2Use)
	{
		if(arr[people1Use][people2Use] == 2) // people1 Win
		{
			return people1;
		}
		else if(arr[people1Use][people2Use] == 1) // ���º�
		{
			return -1;
		}else // people2 Win
		{
			return people2;
		}
	}
	

	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = input.readLine();
		StringTokenizer st = new StringTokenizer(line, " ");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		kh = new boolean[n];
		people = new int[3][20]; // 0 :����, 1:����, 2:��ȣ
		arr = new int[n][n];
		
		for(int i = 0; i < n; i++)
		{
			line = input.readLine();
			st = new StringTokenizer(line, " ");
			for(int j = 0; j < n; j++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int i = 1; i <= 2; i++)
		{
			line = input.readLine();
			st = new StringTokenizer(line, " ");
			for(int j = 0; j < 20; j++)
			{
				people[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		
		dfs(0, 1);
		output.write(canWin + " ");
		
		
		input.close();
		output.close();
		
		

	}

}
