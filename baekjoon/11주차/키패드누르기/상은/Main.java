package test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

class currentHand{
	int row;
	int col;
	
	public currentHand(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
}

public class Main
{
	
    public static String solution(int[] numbers, String hand) {
        String answer = "";
        int leftRow = 3;
        int leftCol = 0;
        int rightRow = 3;
        int rightCol = 2;
        HashMap<Integer, currentHand> map =new HashMap<Integer, currentHand>();
        currentHand temp;
        
        int number = 1;
        for(int i = 0; i < 3; i++)
        {
        	for(int j = 0; j < 3; j++)
        	{
        		map.put(number++, new currentHand(i, j));
        	}
        }
        
        map.put(0, new currentHand(3,1));
        
        for(int i = 0; i < numbers.length; i++)
        {
        	temp = map.get(numbers[i]);
        	if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7)
        	{
        		answer+="L";
        		leftRow = temp.row;
        		leftCol = temp.col;
        	}else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9)
        	{
        		answer+="R";
        		rightRow = temp.row;
        		rightCol = temp.col;
        	}else
        	{
        		if(Math.abs(temp.row - leftRow) + Math.abs(temp.col - leftCol) > Math.abs(temp.row - rightRow) + Math.abs(temp.col - rightCol))
        		{
        			answer+="R";
            		rightRow = temp.row;
            		rightCol = temp.col;
        		}else if(Math.abs(temp.row - leftRow) + Math.abs(temp.col - leftCol) < Math.abs(temp.row - rightRow) + Math.abs(temp.col - rightCol))
        		{
        			answer+="L";
            		leftRow = temp.row;
            		leftCol = temp.col;
        		}else
        		{
        			if(hand.contentEquals("left"))
        			{
        				answer+="L";
        				leftRow = temp.row;
                		leftCol = temp.col;
        			}else
        			{
        				answer+="R";
                		rightRow = temp.row;
                		rightCol = temp.col;
        			}
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

		int[] numbers= {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		String hand = "right";
		
		output.write(solution(numbers, hand));


		input.close();
		output.close();

	}
    

}
