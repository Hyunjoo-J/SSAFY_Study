import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p1182_YK {
	
	static int N, S, result;
	static int[] input;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0, 0, false);
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}

	private static void subset(int cnt, int sum, boolean flag) {
		if (cnt == N) {
			if (sum == S && flag) result++;
			return;
		}
		
		subset(cnt + 1, sum + input[cnt], true);
		subset(cnt + 1, sum, flag);
	}
}
