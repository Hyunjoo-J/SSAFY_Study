import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p12851_HC {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        queue.add(N);
        visited[N] = true;

        int t = 0, k = 0;
        boolean find = false;
        while (!queue.isEmpty()) {
            ++t;
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; ++i) {
                int now = queue.poll();

                visited[now] = true;
                if (now == K) {
                    find = true;
                    ++k;
                }

                int[] candidate = {now - 1, now + 1, now * 2};
                for (int next: candidate) {
                    if (next < 0 || next > 100000)
                        continue;
                    if (visited[next])
                        continue;
                    queue.add(next);
                }
            }
            if (find)
                break;
        }
        bw.write(String.valueOf(t - 1));
        bw.newLine();
        bw.write(String.valueOf(k));

        bw.flush();
        bw.close();
        br.close();
    }
}
