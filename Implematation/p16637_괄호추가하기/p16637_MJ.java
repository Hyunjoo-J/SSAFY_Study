package Implematation.p16637_괄호추가하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class p16637_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, max = Integer.MIN_VALUE;
	static ArrayList<Character> oper = new ArrayList<>();
	static ArrayList<Integer> num = new ArrayList<>();

	private static int calc(int x, int y, int idxO) {
		char op = oper.get(idxO);
		if (op == '+') {
			return x + y;
		} else if (op == '-') {
			return x - y;
		} else if (op == '*') {
			return x * y;
		}
		return 0;
	}

	private static void solve(int ans, int idxN, int idxO) {
		if (idxO >= oper.size()) {
			max = Math.max(max, ans);
			return;
		}

		// 괄호가 없을 때
		solve(calc(ans, num.get(idxN), idxO), idxN + 1, idxO + 1);

		// 괄호가 있을 때
		if (idxO + 1 < oper.size()) {
			// 괄호 부분 먼저 계산
			int tmp1 = calc(num.get(idxN), num.get(idxN + 1), idxO + 1);
			// 지금까지의 값과 괄호 부분 계산
			int tmp2 = calc(ans, tmp1, idxO);

			solve(tmp2, idxN + 2, idxO + 2);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());

		String tmpS = br.readLine();
		char tmp;
		for (int i = 0; i < N; i++) {
			tmp = tmpS.charAt(i);
			if (tmp == '+' || tmp == '-' || tmp == '*') {
				oper.add(tmp);
			} else {
				num.add(tmp - '0');
			}
		}

		solve(num.get(0), 1, 0);

		System.out.println(max);

		br.close();
	}
}
