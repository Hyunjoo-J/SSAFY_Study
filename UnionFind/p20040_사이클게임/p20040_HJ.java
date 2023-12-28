package UnionFind.p20040_사이클게임;

import java.util.*;
import java.io.*;
public class p20040_HJ {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer((br.readLine()));

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] parent = new int[N];
        for(int i = 0; i < N; ++i)
            parent[i] = i;
        boolean flag = false;
        int ans = -1;
        for(int i = 1, a, b; i <= M; ++i){
            st = new StringTokenizer(br.readLine());
            if(flag)
                continue;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(find(parent, a) == find(parent, b)){
                flag = true;
                ans = i;
            }
            else
                union(parent, a, b);
        }
        System.out.println(flag ? ans : 0);
    }

    private static int find(int[] parent, int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent, parent[x]);
    }

    private static void union(int[] parent, int x, int y){
        x = find(parent, x);
        y = find(parent, y);
        if(x < y)
            parent[y] = x;
        else
            parent[x] = y;
    }
}
