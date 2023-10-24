package DynamicProgramming.p17404_RGB거리2;
import java.util.*;
import java.io.*;
public class p17404_HJ {
	static final int INF = 1000 * 1000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] map = new int[N][3];
		int ans = 0;
		for(int i = 0; i < N; ++i){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; ++j){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//0 - R, 1 - G, 2 - B
		int[][] dp = new int[N][3];
		dp[0][0] = map[0][0];
		dp[0][1] = INF;
		dp[0][2] = INF;
		for(int i = 1; i < N - 1; ++i){
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + map[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + map[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + map[i][2];
		}
		dp[N - 1][1] = Math.min(dp[N - 2][0], dp[N - 2][2]) + map[N - 1][1];
		dp[N - 1][2] = Math.min(dp[N - 2][0], dp[N - 2][1]) + map[N - 1][2];
		ans = Math.min(dp[N - 1][1], dp[N - 1][2]);

		dp[0][0] = INF;
		dp[0][1] = map[0][1];
		dp[0][2] = INF;
		for(int i = 1; i < N - 1; ++i){
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + map[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + map[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + map[i][2];
		}
		dp[N - 1][0] = Math.min(dp[N - 2][1], dp[N - 2][2]) + map[N - 1][0];
		dp[N - 1][2] = Math.min(dp[N - 2][0], dp[N - 2][1]) + map[N - 1][2];
		ans = Math.min(ans, Math.min(dp[N - 1][0], dp[N - 1][2]));

		dp[0][0] = INF;
		dp[0][1] = INF;
		dp[0][2] = map[0][2];
		for(int i = 1; i < N - 1; ++i){
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + map[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + map[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + map[i][2];
		}
		dp[N - 1][0] = Math.min(dp[N - 2][1], dp[N - 2][2]) + map[N - 1][0];
		dp[N - 1][1] = Math.min(dp[N - 2][0], dp[N - 2][2]) + map[N - 1][1];
		ans = Math.min(ans, Math.min(dp[N - 1][0], dp[N - 1][1]));

		System.out.println(ans);
	}
}
