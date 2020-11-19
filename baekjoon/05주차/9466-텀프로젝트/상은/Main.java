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
			int[] people = new int[number]; // ���ϴ� ����� ǥ���ϱ� ���� �迭
			int[] check = new int[number]; // ��湮�� ���ϰ� �ϱ� ���� �迭
			int[] perfectForm = new int[number]; // ���� �̷� ����� ǥ���ϱ����� �迭 (1:����, 2:����)
			HashSet<Integer> hs = new HashSet<Integer>();

			for (int j = 0; j < number; j++) {
				people[j] = Integer.parseInt(st.nextToken()) - 1;
			}

			for (int j = 0; j < number; j++) {
				if (check[j] == 0) { // ��湮�� ������ ���� ����� ���
					stack.push(new link(j, people[j]));
					hs.add(j);
					while (formTeam == 0) { // ���� �̷궧���� while�� Ž��
						check[stack.peek().me] = 1; // ��湮 üũ
						if (hs.contains(stack.peek().want)) // ���� �� �� �ִ� ���
						{
							formTeam = 1; // ���� �̷� ��� formTeam���� 1��
							link temp = stack.peek(); //
							while (!stack.isEmpty()) {
								link temp2 = stack.pop();
								perfectForm[temp2.me] = 1;
								if (temp2.me == temp.want) {
									while (!stack.isEmpty()) // ���� ã�⿡ �����ѻ���� ó��
									{
										perfectForm[stack.pop().me] = 2;
										count++;
									}
									hs.clear();
								}
							}
						} else // ���� �� ���ɼ��� ���� ���
						{
							if (perfectForm[stack.peek().want] == 1 || perfectForm[stack.peek().want] == 2) // ���ϴ� �����
																											// �̹� ���� �Ἲ
																											// �� ���
							{
								while (!stack.isEmpty()) // ���� ã�⿡ �����ѻ���� ó��
								{
									perfectForm[stack.pop().me] = 2;
									count++;
								}
								hs.clear();
								break;
							} else { // ���ϴ� ����� ���� �̷��� �ʰ�, �� �Ἲ�� �������� �������(���ɼ��� �����ϴ� ���)
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