package DataStructure.p23309_철도공사;

import java.util.*;
import java.io.*;

public class p23309_HJ {
    static int N, M;
    static int[] prev = new int[1000001], next = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int pre = first;
        int now;
        for (int i = 2; i < N; ++i) {
            now = Integer.parseInt(st.nextToken());
            prev[now] = pre;
            next[pre] = now;
            pre = now;
        }
        now = Integer.parseInt(st.nextToken());
        prev[now] = pre;
        next[pre] = now;
        prev[first] = now;
        next[now] = first;

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int idx = Integer.parseInt(st.nextToken());
            if (command.equals("BN") ) {
                int nidx = Integer.parseInt(st.nextToken());
                sb.append(next[idx]+"\n");
                prev[nidx] = idx;
                next[nidx] = next[idx];
                prev[next[idx]] = nidx;
                next[idx] = nidx;
            }else if(command.equals("BP")){
                int nidx = Integer.parseInt(st.nextToken());
                sb.append(prev[idx]+"\n");
                prev[nidx] = prev[idx];
                next[nidx] = idx;
                next[prev[idx]] = nidx;
                prev[idx] = nidx;
            }else if(command.equals("CN")){
                sb.append(next[idx]+"\n");
                int find = next[idx];
                next[idx] = next[find];
                prev[next[idx]] = idx;
            }else if(command.equals("CP")){
                sb.append(prev[idx]+"\n");
                int find = prev[idx];
                prev[idx] = prev[find];
                next[prev[idx]] = idx;
            }
        }
        System.out.print(sb.toString());
    }
}
