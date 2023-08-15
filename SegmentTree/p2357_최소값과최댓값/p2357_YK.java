package SegmentTree.p2357_최소값과최댓값;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p2357_YK {
    static int N, M;
    static int[] input, minTree, maxTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];
        for (int i = 0; i < N; ++i) {
            input[i] = Integer.parseInt(br.readLine());
        }

        int s = 1;
        while (s < N) { // 트리의 크기를 2의 거듭제곱수로
            s <<= 1;
        }
        minTree = new int[s * 2];
        maxTree = new int[s * 2];
        init(s);

        int a, b;
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            bw.write(String.valueOf(findMin(1, 0 ,s - 1, a - 1, b - 1)));
            bw.write(" ");
            bw.write(String.valueOf(findMax(1, 0 ,s - 1, a - 1, b - 1)));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int findMin(int node, int left, int right, int queryLeft, int queryRight) {
        if (right < queryLeft || left > queryRight) return Integer.MAX_VALUE;
        if (queryLeft <= left && right <= queryRight) return minTree[node] == 0 ? Integer.MAX_VALUE : minTree[node];
        int mid = (left + right) / 2;
        return Math.min(
                findMin(node * 2, left, mid, queryLeft, queryRight),
                findMin(node * 2 + 1, mid + 1, right, queryLeft, queryRight)
        );
    }

    private static int findMax(int node, int left, int right, int queryLeft, int queryRight) {
        if (left > queryRight || right < queryLeft) return 0;
        if (queryLeft <= left && right <= queryRight) return maxTree[node];
        int mid = (left + right) / 2;
        return Math.max(
                findMax(node * 2, left, mid, queryLeft, queryRight),
                findMax(node * 2 + 1, mid + 1, right, queryLeft, queryRight)
        );
    }

    private static void init(int s) {
        // 리프 노드
        for (int i = 0; i < N; ++i) {
            minTree[s + i] = maxTree[s + i] = input[i];
        }

        for (int i = s - 1; i > 0; --i) {
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[i * 2 + 1]);
        }
    }

}

