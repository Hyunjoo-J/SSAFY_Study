package DynamicProgramming.p20303_할로윈의양아치;
import java.util.*;
import java.io.*;
public class p20303_HJ {
    static class Node{
        int fr;
        long ca;

        public Node(int fr, long ca) {
            this.fr = fr;
            this.ca = ca;
        }
    }
    static int N, M, K;
    static int[] candy;
    static int[] parents;
    static int[] person;
    static ArrayList<Node> list = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candy = new int[N + 1];
        parents = new int[N + 1];
        person = new int[N + 1];
        Arrays.fill(person, 1);
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; ++i) {
            candy[i] = Integer.parseInt(st.nextToken());
            parents[i] = i;
        }

        for(int i = 0, a, b; i < M; ++i){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            union(a,b);
        }

        cal();
        System.out.println(dp());
    }

    private static long dp() {
        int size = list.size() + 1;
        long[][] dp = new long[size][K];
        for(int i = 1; i < size; ++i){
            int f = list.get(i - 1).fr;
            long c = list.get(i - 1).ca;
            for(int j = 0; j < K; ++j){
                if(f <= j)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - f] + c);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[size - 1][K - 1];
    }

    private static void cal(){
        for(int i = 1; i <= N; ++i){
            if(parents[i] != i){
                int tmp = find(i);
                candy[tmp] += candy[i];
                person[tmp] += person[i];
            }
        }

        for(int i = 1; i <= N; ++i){
            if(parents[i] == i){
                list.add(new Node(person[i], candy[i]));
            }

        }
    }
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a < b)
            parents[b] = a;
        else
            parents[a] = b;
    }

    private static  int find(int v){
        if(parents[v] == v)
            return v;
        return parents[v] = find(parents[v]);
    }
}
