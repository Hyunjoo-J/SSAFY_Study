package Implematation.p12100_2048Easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p12100_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][], clone[][], output[], max = 0;

	private static void move() {

		clone = new int[N][N];
		for (int i = 0; i < N; i++) {
			clone[i] = map[i].clone();
		}

		for (int num = 0; num < 5; num++) {
			// 상
			if (output[num] == 1) {
				for (int j = 0; j < N; j++) {
					for (int i = 0; i < N; i++) {
						if (clone[i][j] == 0)
							continue;

						// 0이 아닌 숫자를 지닌 내 아래쪽의 블록이 나와 같을 때
						int tmp = 1;
						while (i + tmp < N && clone[i + tmp][j] == 0) {
							tmp++;
						}
						if (i + tmp < N && clone[i][j] == clone[i + tmp][j]) {
							clone[i][j] *= 2;
							clone[i + tmp][j] = 0;
						}

						// 나와 같은 숫자블록이 없을 때 이동하기 (0이 있으면 다 무시하고 최대한 위쪽에 놓아야 함)
						tmp = 1;
						while (i - tmp >= 0 && clone[i - tmp][j] == 0) {
							tmp++;
						}
						if (tmp > 1) {
							clone[i - tmp + 1][j] = clone[i][j];
							clone[i][j] = 0;
						}
					}
				}
			}
			// 하
			else if (output[num] == 2) {
				for (int j = 0; j < N; j++) {
					for (int i = N - 1; i >= 0; i--) {
						if (clone[i][j] == 0)
							continue;

						int tmp = 1;
						while (i - tmp >= 0 && clone[i - tmp][j] == 0) {
							tmp++;
						}
						if (i - tmp >= 0 && clone[i][j] == clone[i - tmp][j]) {
							clone[i][j] *= 2;
							clone[i - tmp][j] = 0;
						}

						tmp = 1;
						while (i + tmp < N && clone[i + tmp][j] == 0) {
							tmp++;
						}
						if (tmp > 1) {
							clone[i + tmp - 1][j] = clone[i][j];
							clone[i][j] = 0;
						}
					}
				}
			}
			// 좌
			else if (output[num] == 3) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (clone[i][j] == 0)
							continue;

						int tmp = 1;
						while (j + tmp < N && clone[i][j + tmp] == 0) {
							tmp++;
						}
						if (j + tmp < N && clone[i][j] == clone[i][j + tmp]) {
							clone[i][j] *= 2;
							clone[i][j + tmp] = 0;
						}

						tmp = 1;
						while (j - tmp >= 0 && clone[i][j - tmp] == 0) {
							tmp++;
						}
						if (tmp > 1) {
							clone[i][j - tmp + 1] = clone[i][j];
							clone[i][j] = 0;
						}
					}

				}
			}
			// 우
			else if (output[num] == 4) {
				for (int i = 0; i < N; i++) {
					for (int j = N - 1; j >= 0; j--) {
						if (clone[i][j] == 0)
							continue;

						int tmp = 1;
						while (j - tmp >= 0 && clone[i][j - tmp] == 0) {
							tmp++;
						}
						if (j - tmp >= 0 && clone[i][j] == clone[i][j - tmp]) {
							clone[i][j] *= 2;
							clone[i][j - tmp] = 0;
						}

						tmp = 1;
						while (j + tmp < N && clone[i][j + tmp] == 0) {
							tmp++;
						}
						if (tmp > 1) {
							clone[i][j + tmp - 1] = clone[i][j];
							clone[i][j] = 0;
						}
					}
				}
			}
		}
	}

	private static void perm(int cnt) {
		if (cnt == 5) {

			move();

			int tmpMax = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (tmpMax < clone[i][j]) {
						tmpMax = clone[i][j];
					}
				}
			}

			max = Math.max(max, tmpMax);

			return;
		}

		for (int i = 1; i < 5; i++) {
			output[cnt] = i;
			perm(cnt + 1);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		output = new int[5];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		perm(0);

		System.out.println(max);

		br.close();
	}
}
