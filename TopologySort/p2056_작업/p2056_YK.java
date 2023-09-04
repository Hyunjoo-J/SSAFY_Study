package TopologySort.p2056_작업;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class p2056_YK {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] inDegree = new int[N + 1];
        int[] times = new int[N + 1];
        ArrayList<Integer>[] input = new ArrayList[N + 1];

        for (int i = 0; i <= N; ++i) {
            input[i] = new ArrayList<>();
        }

        int k;
        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            inDegree[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < inDegree[i]; ++j) {
                k = Integer.parseInt(st.nextToken());
                input[k].add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        // 선행 작업이 끝나는 시간 저장
        int[] dp = new int[N + 1];

        int result = 0;
        for (int i = 1; i <= N; ++i) {
            if (inDegree[i] != 0) continue;
            dp[i] = times[i];
            q.offer(i);
        }

        int cur;
        while (!q.isEmpty()) {
            cur = q.poll();
            result = Math.max(result, dp[cur]);
            for (int ad : input[cur]) {
                dp[ad] = Math.max(dp[ad], dp[cur] + times[ad]);
                if (--inDegree[ad] == 0) q.offer(ad);
            }
        }

        bw.write("" + result);
        bw.flush();
        bw.close();
        br.close();
    }
}