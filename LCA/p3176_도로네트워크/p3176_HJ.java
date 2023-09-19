package LCA.p3176_도로네트워크;
import java.util.*;
import java.io.*;
public class p3176_HJ {
    static class Pair{
        int no, dis;

        public Pair(int no, int dis) {
            this.no = no;
            this.dis = dis;
        }
    }
    static int N, M, height;
    static List<Pair>[] list;
    static int[][] parent, max, min;
    static int[] depth;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        depth = new int[N + 1];
        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; ++i){
            list[i] = new ArrayList<>();
        }
        height = getHeight();
        parent = new int[N + 1][height];
        max = new int[N + 1][height];
        min = new int[N + 1][height];
        boolean[] visited = new boolean[N + 1];
        for(int i = 1; i < N; ++i){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Pair(b, c));
            list[b].add(new Pair(a, c));
            visited[b] = true;
        }
        int root = 0;
        for(int i = 1; i <= N ; ++i){
            if(!visited[i]){
                root = i;
                break;
            }
        }
        init(root, 1, 0);
        fillParents();

        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int[] res= LCA(d,e);
            sb.append(res[0]+" "+res[1]);
            if(i!=k-1) sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    private static void init(int root, int h, int p) {
        depth[root] = h;
        for(Pair cur : list[root]){
            if(cur.no != p){
                init(cur.no, h + 1, root);
                min[cur.no][0] = cur.dis;
                max[cur.no][0] = cur.dis;
                parent[cur.no][0] = root;
            }
        }
    }

    static int[] LCA(int a, int b) {
        int ah = depth[a];
        int bh = depth[b];
        if(ah<bh){
            int tmp = a;
            a = b;
            b = tmp;
        }

        int min_v = 1_000_001;
        int max_v = -1;
        for(int i = height - 1; i>=0; i--) {
            if(Math.pow(2, i) <= depth[a] - depth[b]) {
                min_v = Math.min(min_v, min[a][i]);
                max_v = Math.max(max_v, max[a][i]);
                a = parent[a][i];
            }
        }

        if(a==b) return new int[] {min_v,max_v};

        for(int i=height-1; i>=0; i--) {
            if(parent[a][i] != parent[b][i]) {
                min_v = Math.min(min_v, Math.min(min[a][i], min[b][i]));
                max_v = Math.max(max_v, Math.max(max[a][i], max[b][i]));
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        min_v = Math.min(min_v, Math.min(min[a][0], min[b][0]));
        max_v = Math.max(max_v, Math.max(max[a][0], max[b][0]));
        return new int[] {min_v,max_v};
    }

    static void fillParents() {
        for(int i=1; i<height; i++) {
            for(int j=1; j<=N; j++) {
                parent[j][i] = parent[parent[j][i-1]][i-1];

                max[j][i] = Math.max(max[j][i-1], max[parent[j][i-1]][i-1]);
                min[j][i] = Math.min(min[j][i-1], min[parent[j][i-1]][i-1]);
            }
        }
    }

    private static int getHeight() {
        return (int)Math.ceil(Math.log(N)/Math.log(2));
    }
}
