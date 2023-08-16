import java.io.*;
import java.util.*;

public class p2357_HJ {
    static int n, m;
    static int[] arr, minTree, maxTree;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        for(int i = 1; i <= n; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        minTree = new int[4*n];
        maxTree = new int[4*n];
        minInit(1,n,1);
        maxInit(1,n,1);
        int left, right;
        for(int i = 0; i < m; ++i){
            st = new StringTokenizer(br.readLine());
            left = Integer.parseInt(st.nextToken());
            right = Integer.parseInt(st.nextToken());
            sb.append(minFind(1,n,1,left,right)+" "+maxFind(1,n,1,left,right)+"\n");
        }
        System.out.println(sb.toString());
    }

    private static int minInit(int start, int end, int node){
        if(start == end){ //제일 마지막 depth
            return minTree[node] = arr[start];
        }
        int mid = (start + end)/2;
        return minTree[node] = Math.min(minInit(start, mid, node*2), minInit(mid + 1, end, node*2 + 1));
    }

    private static int maxInit(int start, int end, int node){
        if(start == end){ //제일 마지막 depth
            return maxTree[node] = arr[start];
        }
        int mid = (start + end)/2;
        return maxTree[node] = Math.max(maxInit(start, mid, node*2), maxInit(mid + 1, end, node*2 + 1));
    }

    private static int minFind(int start, int end, int node, int left, int right){
        if(right < start || end < left){
            return Integer.MAX_VALUE;
        }
        if(left <= start && end <= right){
            return minTree[node];
        }
        int mid = (start+end)/2;
        return Math.min(minFind(start, mid, node * 2, left, right), minFind(mid + 1, end, node * 2 + 1, left, right));
    }
    private static int maxFind(int start, int end, int node, int left, int right){
        if(right < start || end < left){
            return Integer.MIN_VALUE;
        }
        if(left <= start && end <= right){
            return maxTree[node];
        }
        int mid = (start+end)/2;
        return Math.max(maxFind(start, mid, node * 2, left, right), maxFind(mid + 1, end, node * 2 + 1, left, right));
    }
}
