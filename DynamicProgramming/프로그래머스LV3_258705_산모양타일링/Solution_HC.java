package DynamicProgramming.프로그래머스LV3_258705_산모양타일링;

class Solution_HC {

	private static final int MOD = 10007;

	public int solution(int n, int[] tops) {
		int[] a = new int[n];
		int[] b = new int[n];

		a[0] = tops[0] == 1 ? 3 : 2;
		b[0] = 1;
		for (int i = 1; i < n; ++i) {
			if (tops[i] == 1) {
				a[i] = (a[i - 1] * 3 + b[i - 1] * 2) % MOD;
				b[i] = (a[i - 1] + b[i - 1]) % MOD;
			} else {
				a[i] = (a[i - 1] * 2 + b[i - 1]) % MOD;
				b[i] = (a[i - 1] + b[i - 1]) % MOD;
			}
		}
		return (a[n - 1] + b[n - 1]) % MOD;
	}
}