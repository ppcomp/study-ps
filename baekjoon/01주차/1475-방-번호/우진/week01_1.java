import java.util.Scanner;

public class week01_1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String room = sc.nextLine();
        String[] number = room.split("");
        int[] num = new int[9];
        for(int i = 0; i < number.length; i++)
        {
            if(number[i].equals("0"))
                num[0]++;
            else if(number[i].equals("1"))
                num[1]++;
            else if(number[i].equals("2"))
                num[2]++;
            else if(number[i].equals("3"))
                num[3]++;
            else if(number[i].equals("4"))
                num[4]++;
            else if(number[i].equals("5"))
                num[5]++;
            else if(number[i].equals("6") || number[i].equals("9"))
                num[6]++;
            else if(number[i].equals("7"))
                num[7]++;
            else if(number[i].equals("8"))
                num[8]++;          
        }
        if(num[6] % 2 == 1)
            num[6]++;
        num[6] = num[6] / 2;
        int temp = 0;
        for(int i = 0; i < num.length; i++)
        {
            if(temp < num[i])
                temp = num[i];
        }
        System.out.print(temp);
    }
}
