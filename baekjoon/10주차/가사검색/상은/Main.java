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
	// [ 변수 ]
	// 자식 노드 맵
	private Map<Character, TrieNode> childNodes = new HashMap<>();
	private Map<Integer, Integer> counts = new HashMap<>();
	// 마지막 글자인지 여부
	private boolean isLastChar;

	// [ GETTER / SETTER 메서드 ]
	// 자식 노드 맵 Getter
	Map<Character, TrieNode> getChildNodes()
	{
		return this.childNodes;
	}

	// 마지막 글자인지 여부 Getter
	boolean isLastChar()
	{
		return this.isLastChar;
	}

	// 마지막 글자인지 여부 Setter
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
	// [ 변수 ]
	// 루트 노드
	private TrieNode rootNode;

	// [ 생성자 ]
	Trie()
	{
		rootNode = new TrieNode();
		rootNode.upCount(0);
	}

	// [ 메서드 ]
	// 자식 노드 추가
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

	// 특정 단어가 들어있는지 확인
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

	// 특정 단어 지우기
	void delete(String word)
	{
		delete(this.rootNode, word, 0); // 최초로 delete 던지는 부분
	}

	private void delete(TrieNode thisNode, String word, int index)
	{
		char character = word.charAt(index);
		// APPLE, PEN과 같이 아예 없는 단어인 경우 에러 출력
		if (!thisNode.getChildNodes().containsKey(character))
			throw new Error("There is no [" + word + "] in this Trie.");
		TrieNode childNode = thisNode.getChildNodes().get(character);
		index++;
		if (index == word.length())
		{
			// 삭제조건 2번 항목
			// PO와 같이 노드는 존재하지만 insert한 단어가 아닌 경우 에러 출력
			if (!childNode.isLastChar())
				throw new Error("There is no [" + word + "] in this Trie.");
			childNode.setIsLastChar(false);
			// 삭제조건 1번 항목
			// 삭제 대상 언어의 제일 끝으로써 자식 노드가 없으면(이 단어를 포함하는 더 긴 단어가 없으면) 삭제 시작
			if (childNode.getChildNodes().isEmpty())
				thisNode.getChildNodes().remove(character);
		} else
		{
			delete(childNode, word, index); // 콜백함수부분
			// 삭제조건 1,3번 항목
			// 삭제 중, 자식 노드가 없고 현재 노드로 끝나는 다른 단어가 없는 경우 이 노드 삭제
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
			lastTrie.insert(str.reverse().toString()); // 뒤집고 추가
		}

		for (int i = 0; i < queries.length; i++)
		{
			if (queries[i].charAt(0) == '?') // 첫글자가 '?'인 경우
			{
				if (queries[i].charAt(queries[i].length() - 1) == '?') // 모든 문자가 '?'인 경우
				{
					answer[i] = firstTrie.rootContains(queries[i].length());
				} else
				{
					str = new StringBuffer();  // 뒤집고 '?' 제거하고 contains
					str.append(queries[i]);
					answer[i] = lastTrie.contains(str.reverse().toString().replace("?",  ""), queries[i].length());
				}
			} else
			{
				if (queries[i].charAt(queries[i].length() - 1) == '?') // 마지막글자가 '?'인 경우
				{
					answer[i] = firstTrie.contains(queries[i].replace("?", ""), queries[i].length());
				} else // 아닌경우 -> '?'가 없는 문자열
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
