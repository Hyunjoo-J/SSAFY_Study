package TwoPointer.p2531_회전초밥;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class p2531_YK {
	
	static int N, d, k, c, result;
	static int[] input;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		input = new int[N];

		for (int i = 0; i < N; ++i) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < N; ++i) {
			combi(i);
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
 	}

	private static void combi(int start) {
		// 쿠폰을 사용하면 가짓수를 늘릴 수 있는지 체크
		boolean flag = true;
		boolean[] check = new boolean[d + 1];
		int r = k;
		for (int i = start; i < start + k; ++i) {
			int ni = i % N;
			
			// 쿠폰에 해당하는 스시 등장
			if (input[ni] == c) flag = false;
			
			// 이미 나왔던 스시 등장
			if (check[input[ni]]) --r;
			
			check[input[ni]] = true;
		}
		
		if (flag) r++;
		if (r > result) result = r;
	}
}