package KMP.p7575_바이러스;

import java.io.*;
import java.util.*;

public class p7575_YK {

    static int N, K;
    static int[][] programs;
    static boolean[] isThere = new boolean[10001];
    static ArrayList<int[]> patterns = new ArrayList<>();
    static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        programs = new int[N][];

        int len = Integer.parseInt(br.readLine());
        programs[0] = new int[len];
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < len; ++j) {
            programs[0][j] = Integer.parseInt(st.nextToken());
        }


        for (int i = 1; i < N; ++i) {
            len = Integer.parseInt(br.readLine());
            programs[i] = new int[len];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < len; ++j) {
                programs[i][j] = Integer.parseInt(st.nextToken());
                isThere[programs[i][j]] = true;
            }
        }

        if (!makePatterns()) {
            System.out.println("NO");
            return;
        }

        int size = patterns.size();
        int[] p1 = new int[K];
        int[] p2 = new int[K];
        for (int i = 0; i < size; i += 2) {
            makeFail(patterns.get(i), p1);
            makeFail(patterns.get(i + 1), p2);
            if (check(patterns.get(i), patterns.get(i + 1), p1, p2)) {
                flag = true;
                break;
            }
        }

        if (flag) bw.write("YES");
        else bw.write("NO");

        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean check(int[] pattern1, int[] pattern2, int[] p1, int[] p2) {
        boolean[] visited = new boolean[N];
        visited[0] = true;

        for (int ii = 1; ii < N; ++ii) {
            int[] program = programs[ii];
            int size = program.length;

            for (int i = 0, j = 0; i < size; ++i) {
                while (j > 0 && program[i] != pattern1[j]) {
                    j = p1[j - 1];
                }
                if (program[i] == pattern1[j]) {
                    if (j == K - 1) {
                        visited[ii] = true;
                        break;
                    }
                    else ++j;
                }
            }

            if (visited[ii]) continue;

            for (int i = 0, j = 0; i < size; ++i) {
                while (j > 0 && program[i] != pattern2[j]) {
                    j = p2[j - 1];
                }
                if (program[i] == pattern2[j]) {
                    if (j == K - 1) {
                        visited[ii] = true;
                        break;
                    }
                    else ++j;
                }
            }
        }

        for (boolean b : visited) {
            if (!b) return false;
        }
        return true;
    }

    private static void makeFail(int[] pattern, int[] p) {
        int j = 0;
        for (int i = 1; i < K; ++i) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = p[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                p[i] = ++j;
            }
        }
    }

    private static boolean makePatterns() {
        int i = 0;
        int limit = programs[0].length - K + 1;
        while (i < limit && !isThere[programs[0][i]]) ++i;
        if (i == limit) return false;

        int j = i + 1;
        while (i < limit) {
            if (j - i == K - 1) {
                int[] p1 = new int[K];
                int[] p2 = new int[K];
                int k = 0;
                for (int ii = i; ii <= j; ++ii) {
                    p1[k] = programs[0][ii];
                    p2[K - k - 1] = programs[0][ii];
                    ++k;
                }
                patterns.add(p1);
                patterns.add(p2);
                ++i;
                while (i < limit && !isThere[programs[0][i]]) ++i;
                if (i == limit) return true;

                j = i + 1;
                continue;
            }

            ++j;
            while (i < limit && (!isThere[programs[0][i]] || !isThere[programs[0][j]])) {
                i += 1;
                j += 1;
            }
        }
        return true;
    }
}