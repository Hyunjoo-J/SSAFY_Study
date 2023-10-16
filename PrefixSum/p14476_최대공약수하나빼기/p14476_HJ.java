package PrefixSum.p14476_최대공약수하나빼기;
import java.util.*;
import java.io.*;
public class p14476_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N + 2];
		for(int i = 1; i <= N; ++i)
			arr[i] = Integer.parseInt(st.nextToken());
		int[] lgcd = new int[N + 2];
		for(int i = 1; i <= N; ++i){
			lgcd[i] = gcd(lgcd[i - 1], arr[i]);
		}
		int[] rgcd = new int[N + 2];
		for(int i = N; i > 0; --i){
			rgcd[i] = gcd(rgcd[i + 1], arr[i]);
		}

		int ans = -1;
		int max = -1;
		for(int i = 1; i <= N; ++i){
			int res = gcd(lgcd[i - 1], rgcd[i + 1]);
			if(res > max && arr[i] % res != 0){
				max = res;
				ans = arr[i];
			}
		}
		if(max == -1)
			System.out.println("-1");
		else{
			System.out.println(max + " " + ans);
		}
	}

	private static int gcd(int a, int b){
		if(b == 0)
			return a;
		else
			return gcd(b, a % b);
	}
}
