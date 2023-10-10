package TwoPointer.p22857_가장긴짝수연속한부분수열small;
import java.util.*;
import java.io.*;

public class p22857_HJ {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int start = 0, end = 0;
		int odd = 0, even = 0;
		if (arr[0] % 2 == 0)
			++even;
		else
			++odd;
		int ans = even;
		while (start <= end) {
			if (odd > K) {
				if (arr[start] % 2 == 0)
					--even;
				else
					--odd;
				++start;
			} else {
				++end;
				if (end >= N)
					break;
				if (arr[end] % 2 == 0)
					++even;
				else
					++odd;
				ans = Math.max(even, ans);
			}
		}
		System.out.println(ans);
	}
}
