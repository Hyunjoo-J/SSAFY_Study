package Greedy.p1541_잃어버린괄호;
import java.util.*;
import java.io.*;
public class p1541_HJ {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");
        while(st.hasMoreTokens()){
            int sum = 0;
            StringTokenizer st2 = new StringTokenizer(st.nextToken(), "+");
            while(st2.hasMoreTokens())
                sum += Integer.parseInt(st2.nextToken());

            if(max == Integer.MAX_VALUE)
                max = sum;
            else
                max -= sum;
        }
        System.out.println(max);
    }
}
