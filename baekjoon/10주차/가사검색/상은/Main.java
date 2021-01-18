package test4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

class TrieNode
{
	// [ ���� ]
	// �ڽ� ��� ��
	private Map<Character, TrieNode> childNodes = new HashMap<>();
	private Map<Integer, Integer> counts = new HashMap<>();
	// ������ �������� ����
	private boolean isLastChar;

	// [ GETTER / SETTER �޼��� ]
	// �ڽ� ��� �� Getter
	Map<Character, TrieNode> getChildNodes()
	{
		return this.childNodes;
	}

	// ������ �������� ���� Getter
	boolean isLastChar()
	{
		return this.isLastChar;
	}

	// ������ �������� ���� Setter
	void setIsLastChar(boolean isLastChar)
	{
		this.isLastChar = isLastChar;
	}

	void upCount(int count)
	{
		if (this.counts.containsKey(count))
		{
			this.counts.put(count, this.counts.get(count) + 1);
		} else
		{
			this.counts.put(count, 1);
		}

	}

	int getCount(int count)
	{
		if (this.counts.containsKey(count))
		{
			return this.counts.get(count);
		} else
		{
			return 0;
		}
	}

}

class Trie
{
	// [ ���� ]
	// ��Ʈ ���
	private TrieNode rootNode;

	// [ ������ ]
	Trie()
	{
		rootNode = new TrieNode();
		rootNode.upCount(0);
	}

	// [ �޼��� ]
	// �ڽ� ��� �߰�
	void insert(String word)
	{
		TrieNode thisNode = this.rootNode;
		thisNode.upCount(word.length());
		for (int i = 0; i < word.length(); i++)
		{
			thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode());
			thisNode.upCount(word.length());
		}
		thisNode.setIsLastChar(true);
	}

	// Ư�� �ܾ ����ִ��� Ȯ��
	int contains(String word, int length)
	{
		TrieNode thisNode = this.rootNode;
		for (int i = 0; i < word.length(); i++)
		{
			char character = word.charAt(i);
			TrieNode node = thisNode.getChildNodes().get(character);
			if (node == null)
				return 0;
			thisNode = node;
		}
		return thisNode.getCount(length);
	}

	int rootContains(int length)
	{
		TrieNode thisNode = this.rootNode;
		return thisNode.getCount(length);

	}

	// Ư�� �ܾ� �����
	void delete(String word)
	{
		delete(this.rootNode, word, 0); // ���ʷ� delete ������ �κ�
	}

	private void delete(TrieNode thisNode, String word, int index)
	{
		char character = word.charAt(index);
		// APPLE, PEN�� ���� �ƿ� ���� �ܾ��� ��� ���� ���
		if (!thisNode.getChildNodes().containsKey(character))
			throw new Error("There is no [" + word + "] in this Trie.");
		TrieNode childNode = thisNode.getChildNodes().get(character);
		index++;
		if (index == word.length())
		{
			// �������� 2�� �׸�
			// PO�� ���� ���� ���������� insert�� �ܾ �ƴ� ��� ���� ���
			if (!childNode.isLastChar())
				throw new Error("There is no [" + word + "] in this Trie.");
			childNode.setIsLastChar(false);
			// �������� 1�� �׸�
			// ���� ��� ����� ���� �����ν� �ڽ� ��尡 ������(�� �ܾ �����ϴ� �� �� �ܾ ������) ���� ����
			if (childNode.getChildNodes().isEmpty())
				thisNode.getChildNodes().remove(character);
		} else
		{
			delete(childNode, word, index); // �ݹ��Լ��κ�
			// �������� 1,3�� �׸�
			// ���� ��, �ڽ� ��尡 ���� ���� ���� ������ �ٸ� �ܾ ���� ��� �� ��� ����
			if (!childNode.isLastChar() && childNode.getChildNodes().isEmpty())
				thisNode.getChildNodes().remove(character);
		}
	}
}

public class Main
{

	public static int[] solution(String[] words, String[] queries)
	{
		Trie firstTrie = new Trie();
		Trie lastTrie = new Trie();
		int[] answer = new int[queries.length];
		StringBuffer str;
		for (int i = 0; i < words.length; i++)
		{
			firstTrie.insert(words[i]);
			str = new StringBuffer(); 
			str.append(words[i]);
			lastTrie.insert(str.reverse().toString()); // ������ �߰�
		}

		for (int i = 0; i < queries.length; i++)
		{
			if (queries[i].charAt(0) == '?') // ù���ڰ� '?'�� ���
			{
				if (queries[i].charAt(queries[i].length() - 1) == '?') // ��� ���ڰ� '?'�� ���
				{
					answer[i] = firstTrie.rootContains(queries[i].length());
				} else
				{
					str = new StringBuffer();  // ������ '?' �����ϰ� contains
					str.append(queries[i]);
					answer[i] = lastTrie.contains(str.reverse().toString().replace("?",  ""), queries[i].length());
				}
			} else
			{
				if (queries[i].charAt(queries[i].length() - 1) == '?') // ���������ڰ� '?'�� ���
				{
					answer[i] = firstTrie.contains(queries[i].replace("?", ""), queries[i].length());
				} else // �ƴѰ�� -> '?'�� ���� ���ڿ�
				{
					answer[i] = firstTrie.contains(queries[i], queries[i].length());
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

		String[] words = { "frodo", "fra", "frrrr", "frost", "frozen", "frame", "kakao" };
		String[] queries = { "???do", "????o", "froa?", "fro???", "pro?" };
		int[] answer = solution(words, queries);

		for (int i = 0; i < answer.length; i++)
		{
			output.write(answer[i] + " ");
		}

		input.close();
		output.close();

	}

}
