import java.io.*;
import java.util.*;

public class p16953_HJ {
    static long A;
    static long B;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        int ans = 1;
        while(A != B){
            if(B < A) {
                ans = -1;
                break;
            }

            if(B % 10 == 1)
                B /= 10;
            else if(B % 2 == 0)
                B /= 2;
            else{
                ans = -1;
                break;
            }
            ++ans;
        }
        System.out.println(ans);
    }
}
