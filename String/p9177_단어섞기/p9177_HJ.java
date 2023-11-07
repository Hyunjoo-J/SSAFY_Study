package String.p9177_단어섞기;
import java.util.*;
import java.io.*;
public class p9177_HJ {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc){
            st = new StringTokenizer(br.readLine());
            String line1 = st.nextToken();
            String line2 = st.nextToken();
            String line3 = st.nextToken();
            sb.append("Data set ").append(tc).append(": ")
                    .append((isAble(line1, line2, line3) ? "yes\n" : "no\n"));
        }
        System.out.println(sb);
    }

    private static boolean isAble(String line1, String line2, String line3) {
        int[] arr = new int[200];
        int n = line1.length();
        int m = line2.length();
        int l = n + m;

        for(int i = 0; i < n; ++i)
            ++arr[line1.charAt(i)];
        for(int i = 0; i < m; ++i)
            ++arr[line2.charAt(i)];
        for(int i = 0; i < l; ++i)
            --arr[line3.charAt(i)];
        for(int i = 0; i < 200; ++i){
            if(arr[i] != 0)
                return false;
        }

        int idx = 0;
        for(int i = 0; i < l && idx < n; ++i){
            if(line3.charAt(i) == line1.charAt(idx))
                ++idx;
        }
        if(idx < n)
            return false;

        idx = 0;
        for(int i = 0; i < l && idx < m; ++i){
            if(line3.charAt(i) == line2.charAt(idx))
                ++idx;
        }
        if(idx < m)
            return false;
        return true;
    }
}