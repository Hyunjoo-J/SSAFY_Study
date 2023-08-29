package DynamicProgramming.p1932_정수삼각형;
import java.util.*;
import java.io.*;

public class p1932_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] tri = new int[N + 1][N + 1];
		for(int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < i + 1; ++j) {
				int tmp = Integer.parseInt(st.nextToken());
				tri[i][j] = Math.max(tri[i - 1][j - 1], tri[i - 1][j]) + tmp;
			}
		}
		int max = 0;
		for(int i = 1; i < N + 1; ++i) {
			max = Math.max(tri[N][i], max);
		}
		System.out.println(max);
	}
}
