import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.lang.Math;

public class week06_5 {
    public static void main(String[] args)throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(input.readLine());
        String[] star = new String[N];
        star[0] = "  *  ";
        star[1] = " * * ";
        star[2] = "*****";
        for(int i = 1; 3 * (int)Math.pow(2, i) <= N; i++)
            makeStar(i, star);

        for(int i = 0; i < N; i++)
            System.out.println(star[i]);
        input.close();
        output.flush();
        output.close(); 
    }

    public static void makeStar(int num, String[] star)
    {
        int bottom = 3 * (int)Math.pow(2, num);
        int middle = bottom / 2;
         
        for (int i = middle; i < bottom; i++)
            star[i] = star[i - middle] + " " + star[i -middle];
        
        String space = "";
        while (space.length() < middle)
            space += " ";
        
        for (int i = 0; i < middle; ++i)
            star[i] = space + star[i] + space;
    }    
}
