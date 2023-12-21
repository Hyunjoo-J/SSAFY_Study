package DivideAndConquer.p11444_피보나치수6;
import java.io.*;

public class p11444_HJ {
	final static long MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		if(N == 1 || N == 0) {
			System.out.println(N);
			return;
		}
		N--;

		long[][] origin = {{1,1},{1,0}};
		long[][] A = {{1,0},{0,1}};
		while(N > 0) {
			if(N % 2 == 1) {
				A = multi(A, origin);
			}
			origin = multi(origin, origin);

			N /= 2;
		}
		System.out.println(A[0][0]);
	}

	private static long[][] multi(long[][] m1, long[][] m2){
		long[][] ret = new long[2][2];

		ret[0][0] = (m1[0][0]*m2[0][0] + m1[0][1]*m2[1][0]) % MOD;
		ret[0][1] = (m1[0][0]*m2[0][1] + m1[0][1]*m2[1][1]) % MOD;
		ret[1][0] = (m1[1][0]*m2[0][0] + m1[1][1]*m2[1][0]) % MOD;
		ret[1][1] = (m1[1][0]*m2[0][1] + m1[1][1]*m2[1][1]) % MOD;
		return ret;
	}
}