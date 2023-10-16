package DivideAndConquer.p10830_행렬제곱;
import java.util.*;
import java.io.*;
public class p10830_HJ {
	static final int MOD = 1000;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		int[][] matrix = new int[N][N];
		for(int i = 0; i < N; ++i){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; ++j)
				matrix[i][j] = Integer.parseInt(st.nextToken()) % MOD;
		}
		int[][] ans = divide(matrix, B);
		for(int i = 0; i < N; ++i){
			for(int j = 0; j < N; ++j){
				sb.append(ans[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	private static int[][] divide(int[][] matrix, long num) {
		if(num == 1)
			return matrix;
		int[][] res = divide(matrix, num/2);
		res = multiply(res, res);
		if(num % 2 == 1)
			res = multiply(res, matrix);
		return res;
	}

	private static int[][] multiply(int[][] A, int[][] B) {
		int tmp;
		int[][] res = new int[N][N];
		for(int i = 0; i < N; ++i){
			for(int j = 0; j < N; ++j){
				tmp = A[j][i];
				for(int k = 0; k < N; ++k){
					res[j][k] += tmp * B[i][k];
					res[j][k] %= MOD;
				}
			}
		}
		return res;
	}
}

