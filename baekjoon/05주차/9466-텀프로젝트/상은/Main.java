package turmP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

class link {
	int me;
	int want;

	public link(int me, int want) {
		this.me = me;
		this.want = want;
	}
}

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));

		Stack<link> stack = new Stack<link>();
		int n = Integer.parseInt(input.readLine());
		int number = 0;
		String line = "";
		StringTokenizer st;
		int formTeam = 0;
		int count = 0;

		for (int i = 0; i < n; i++) {
			number = Integer.parseInt(input.readLine());
			line = input.readLine();
			st = new StringTokenizer(line, " ");
			int[] people = new int[number]; // 원하는 사람을 표시하기 위한 배열
			int[] check = new int[number]; // 재방문을 못하게 하기 위한 배열
			int[] perfectForm = new int[number]; // 팀을 이룬 사람을 표시하기위한 배열 (1:성공, 2:실패)
			HashSet<Integer> hs = new HashSet<Integer>();

			for (int j = 0; j < number; j++) {
				people[j] = Integer.parseInt(st.nextToken()) - 1;
			}

			for (int j = 0; j < number; j++) {
				if (check[j] == 0) { // 재방문을 한적이 없는 사람일 경우
					stack.push(new link(j, people[j]));
					hs.add(j);
					while (formTeam == 0) { // 팀을 이룰때까지 while문 탐색
						check[stack.peek().me] = 1; // 재방문 체크
						if (hs.contains(stack.peek().want)) // 팀이 될 수 있는 경우
						{
							formTeam = 1; // 팀을 이룬 경우 formTeam값을 1로
							link temp = stack.peek(); //
							while (!stack.isEmpty()) {
								link temp2 = stack.pop();
								perfectForm[temp2.me] = 1;
								if (temp2.me == temp.want) {
									while (!stack.isEmpty()) // 팀원 찾기에 실패한사람들 처리
									{
										perfectForm[stack.pop().me] = 2;
										count++;
									}
									hs.clear();
								}
							}
						} else // 팀이 될 가능성이 없는 경우
						{
							if (perfectForm[stack.peek().want] == 1 || perfectForm[stack.peek().want] == 2) // 원하는 사람이
																											// 이미 팀이 결성
																											// 된 경우
							{
								while (!stack.isEmpty()) // 팀원 찾기에 실패한사람들 처리
								{
									perfectForm[stack.pop().me] = 2;
									count++;
								}
								hs.clear();
								break;
							} else { // 원하는 사람이 팀을 이루지 않고, 팀 결성에 실패하지 않은경우(가능성이 존재하는 경우)
								hs.add(stack.peek().want);
								stack.push(new link(stack.peek().want, people[stack.peek().want]));

							}

						}
					}

					formTeam = 0;
				}
			}
			output.write(count + "\n");
			count = 0;

		}

		input.close();
		output.close();

	}

}