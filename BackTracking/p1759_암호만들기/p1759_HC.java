package BackTracking.p1759_암호만들기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1759_HC {

    private static int L, C;
    private static char[] alphabet;
    private static char[] chars;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphabet = new char[C];
        chars = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; ++i) {
            alphabet[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alphabet);

        StringBuilder sb = new StringBuilder();
        dfs(0, 0, sb);
        System.out.println(sb.toString());
        br.close();
    }

    private static void dfs(int idx, int depth, StringBuilder sb) {
        if (depth == L) {
            int[] counter = {0, 0};
            for (char c: chars) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    ++counter[0];
                } else {
                    ++counter[1];
                }
            }
            if (counter[0] > 0 && counter[1] > 1) {
                for (char c: chars)
                    sb.append(c);
                sb.append("\n");
            }
            return;
        }
        for (int i = idx; i < C; ++i) {
            chars[depth] = alphabet[i];
            dfs(i + 1, depth + 1, sb);
        }
    }
}