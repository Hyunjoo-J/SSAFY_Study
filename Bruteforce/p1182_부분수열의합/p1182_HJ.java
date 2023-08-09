import java.io.*;
import java.util.*;

public class p1182_HJ {
	static int n, m, ans, arr[], sel[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		sel = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		sub(0, 0, 0);
		System.out.println(ans);
	}

	private static void sub(int cnt, int len, int num) {
		if (cnt == n) {
			if (num > 0) {
				int sum = 0;
				for (int i = 0; i < len; ++i) {
					sum += sel[i];
				}
				if (sum == m)
					ans++;
			}
			return;
		}
		sel[len] = arr[cnt];
		sub(cnt + 1, len + 1, num + 1);
		sub(cnt + 1, len, num);
	}
}
