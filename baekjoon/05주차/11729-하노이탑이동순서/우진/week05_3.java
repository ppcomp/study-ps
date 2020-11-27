import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;

public class week05_3 {
    static int count = 0;
    static ArrayList<String> list = new ArrayList();
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(input.readLine());
        hanoi("1", "2", "3", n);
        output.write(count + "");
        
        for(int i = 0; i < list.size(); i++)
        {
            if(i % 2 == 0)
                output.newLine();
            output.write(list.get(i) + " ");
        }
        input.close();
        output.flush();
        output.close(); 
    }

    public static void hanoi(String from, String spare, String to, int ndisk){
        if(ndisk == 1)
        {
            list.add(from);
            list.add(to);
            count++;
        }
        else{
            hanoi(from, to, spare, ndisk-1);
            list.add(from);
            list.add(to);
            count++;
            hanoi(spare, from, to, ndisk-1);
        }
    }
}