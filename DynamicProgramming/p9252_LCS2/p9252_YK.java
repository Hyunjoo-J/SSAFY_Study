package DynamicProgramming.p9252_LCS2;

import java.io.*;
import java.util.*;

public class p9252_YK {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        int len1 = str1.length;
        int len2 = str2.length;

        // dp[i][j] : str1의 0 ~ i 과 str2의 0 ~ j의 LCS
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; ++i) {
            for (int j = 1; j <= len2; ++j) {
                if (str1[i - 1] == str2[j - 1])  {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int result = dp[len1][len2];
        int p1 = len1, p2 = len2;
        while (p1 > 0 && p2 > 0) {
            if (dp[p1][p2] == dp[p1 - 1][p2]) {
                --p1;
            } else if (dp[p1][p2] == dp[p1][p2 - 1]) {
                --p2;
            } else {
                --p1;
                --p2;
                sb.append(str1[p1]);
            }
        }

        System.out.println(result);
        if (result != 0) System.out.println(sb.reverse());
        br.close();
    }

}
