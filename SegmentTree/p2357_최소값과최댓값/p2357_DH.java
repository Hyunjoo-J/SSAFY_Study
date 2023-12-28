package Algorithm4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2357_DH {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] arr, minTree, maxTree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1. arr 입력 받기
        arr = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        minTree = new int[N*4];
        maxTree = new int[N*4];

        minInit(1, N, 1);
        maxInit(1, N, 1);


        // 2. 시작구간과 종료구간 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            sb.append(minFind(1, N, 1, left, right)+" "+maxFind(1, N, 1, left, right)+"\n");
        }

        System.out.println(sb.toString());
    }

    public static int minInit(int start, int end, int node){
        if(start == end){
            return minTree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return minTree[node] = Math.min(minInit(start, mid, node*2), minInit(mid+1, end, node*2+1));
    }

    // 각 구간별 최댓값 최솟값이 저장되어 있다.
    public static int maxInit(int start, int end, int node){
        if(start == end){
            return maxTree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return maxTree[node] = Math.max(maxInit(start, mid, node*2), maxInit(mid+1, end, node*2+1));
    }



    public static int minFind(int start, int end,  int node, int left, int right){
        // 범위를 벗어난 경우
        if(right < start || end < left ){
            return Integer.MAX_VALUE;
        }

        // 찾고자 하는 범위(left ~ right)안에 start와 end 범위가 들어오게되면
        if(left <= start && end <= right)
            return minTree[node];

        int mid = (start + end) / 2;

        // 탑 다운 방식처럼 좌측과 우측을 탐색해서 그 중에 가장 작은 값을 가져오게 된다.
        return Math.min(minFind(start, mid, node*2, left, right), minFind(mid+1, end, node*2+1, left, right));
    }

    public static int maxFind(int start, int end,  int node, int left, int right){
        // 범위를 벗어난 경우
        if(right < start || end < left ){
            return Integer.MIN_VALUE;
        }

        // 찾고자 하는 범위(left ~ right)안에 start와 end 범위가 들어오게되면
        if(left <= start && end <= right)
            return maxTree[node];

        int mid = (start + end) / 2;

        // 탑 다운 방식처럼 좌측과 우측을 탐색해서 그 중에 가장 큰 값을 가져오게 된다.
        return Math.max(maxFind(start, mid, node*2, left, right), maxFind(mid+1, end, node*2+1, left, right));
    }
}
