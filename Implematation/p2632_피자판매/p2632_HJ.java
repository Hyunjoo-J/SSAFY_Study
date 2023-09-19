package Implematation.p2632_피자판매;
import java.util.*;
import java.io.*;
public class p2632_HJ {
    static int size, m, n;
    static int[] A, B, pizza;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        A = new int[2 * m + 1];
        B = new int[2 * n + 1];
        pizza = new int[size + 1];
        for(int i = 1; i <= m; ++i){
            A[i] = A[m + i] = Integer.parseInt(br.readLine());
        }
        for(int i = 1; i <= n; ++i){
            B[i] = B[n + i] = Integer.parseInt(br.readLine());
        }
        int idx = 2 * m + 1;
        for(int i = 1; i < idx; ++i)
            A[i] += A[i - 1];
        idx = 2 * n + 1;
        for(int i = 1; i < idx; ++i)
            B[i] += B[i - 1];
        int ans = 0;
        if(A[m] == size)
            ++ans;
        if(A[m] <= size)
            ++pizza[A[m]];
        for(int i = 1; i <= m; ++i){
            for(int j = i; j < i + m - 1; ++j){
                int sum = A[j] - A[i - 1];
                if(sum == size)
                    ++ans;
                if(sum >= size)
                    continue;
                pizza[sum] += 1;

            }
        }
        if(B[n] == size)
            ++ans;
        if(size - B[n] >= 0)
            ans += (pizza[size - B[n]]);
        for(int i = 1; i <= n; ++i){
            for(int j = i; j < i + n - 1; ++j){
                int sum = B[j] - B[i - 1];
                if(sum == size)
                    ++ans;
                if(size - sum >= 0)
                    ans += pizza[size - sum];
            }
        }
        System.out.println(ans);
    }
}