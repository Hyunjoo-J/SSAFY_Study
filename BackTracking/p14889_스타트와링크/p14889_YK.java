package p14889_스타트와링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p14889_YK {
	static int[] arr;
	static int n;
	static int min = 1000000000;
	static int max = -1000000000;
	
	public static void dfs(int k, int res, int[] opc) {
		if (k > n - 1) {
			if (res > max) max = res;
			if (res < min) min = res;
			return;
		}
		
		if (opc[0] > 0) {
			opc[0]--;
			dfs(k + 1, res+arr[k], opc);
			opc[0]++;
		}
		if (opc[1] > 0) {
			opc[1]--;
			dfs(k + 1, res-arr[k], opc);
			opc[1]++;
		}
		if (opc[2] > 0) {
			opc[2]--;
			dfs(k + 1, res*arr[k], opc);
			opc[2]++;
		}
		if (opc[3] > 0) {
			opc[3]--;
			if (res < 0) dfs(k + 1, -(-res/arr[k]), opc);
			else dfs(k + 1, res/arr[k], opc);
			opc[3]++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		// +, -, *, /
		int[] opc = new int[4];
		for (int i = 0; i < opc.length; i++) {
			opc[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1, arr[0], opc);
		System.out.println(max);
		System.out.println(min);
	}
}