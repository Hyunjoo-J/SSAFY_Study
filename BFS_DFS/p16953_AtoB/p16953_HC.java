package BFS_DFS.p16953_AtoB;

import java.io.*;
import java.util.*;

public class p16953_HC {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        Queue<Long> queue = new ArrayDeque<>(10);
        queue.add((long) A);
        Set<Long> visited = new HashSet<>();
        visited.add((long) A);

        long now, next;
        int res = 0;
        boolean find = false;
        while (!find && !queue.isEmpty()) {
            int queueSize = queue.size();
            ++res;
            for (int i = 0; i < queueSize; ++i) {
                now = queue.poll();

                if (now == B) {
                    find = true;
                    break;
                }

                long[] candidate = {now * 2, now * 10 + 1};
                for (int j = 0; j < 2; ++j) {
                    next = candidate[j];
                    if (next < 1 || next > 1e9)
                        continue;
                    if (visited.contains(next))
                        continue;
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        bw.write(String.valueOf(find ? res : -1));
        bw.flush();
        bw.close();
        br.close();
    }
}