package Greedy.p28353_고양이카페;

import java.io.*;
import java.util.*;

public class p28353_MJ {
    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] cat = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; ++i){
            cat[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cat);

        int a = 0;
        int b = cat.length-1;
        int max = 0;

        while (a < b) {
            if (cat[a] + cat[b] > K) {
                b--;
            } else {
                max++;
                a++;
                b--;
            }
        }

        System.out.println(max);
        br.close();
    }
}
