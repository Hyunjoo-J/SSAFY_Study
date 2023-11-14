package Tree.p1967_트리의지름;

import java.io.*;
import java.util.*;
public class p1967_YK {
    static class TreeNode {
        int num;
        int weight;

        public TreeNode(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    static int N, result;
    static boolean[] visited;
    static ArrayList<TreeNode>[] tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; ++i) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[a].add(new TreeNode(b, c));
            tree[b].add(new TreeNode(a, c));
        }

        visited = new boolean[N + 1];
        for (int i = 1; i < N; ++i) {
            Arrays.fill(visited, false);
            visited[i] = true;
            dfs(i, 0);
        }

        System.out.println(result);
        br.close();
    }

    private static void dfs(int k, int cost) {
        result = Math.max(result, cost);

        for (TreeNode next : tree[k]) {
            if (visited[next.num]) continue;
            visited[next.num] = true;
            dfs(next.num, cost + next.weight);
            visited[next.num] = false;
        }
    }
}
