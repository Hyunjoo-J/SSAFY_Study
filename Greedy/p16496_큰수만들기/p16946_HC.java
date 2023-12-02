package Greedy.p16496_큰수만들기;

import java.io.*;
import java.util.*;

public class p16946_HC {

    private static final String ZERO = "0";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String[] nums = new String[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            nums[i] = st.nextToken();
            if (nums[i].charAt(0) == '0')
                nums[i] = ZERO;
        }

        Arrays.sort(nums, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        StringBuilder sb = new StringBuilder();
        for (String s: nums) {
            sb.append(s);
        }

        if (sb.charAt(0) == '0')
            System.out.println(ZERO);
        else
            System.out.println(sb.toString());
        br.close();
    }
}