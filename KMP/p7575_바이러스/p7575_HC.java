package KMP.p7575_바이러스;

import java.io.*;
import java.util.*;

public class p7575_HC {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] programs = new int[N][];
		for (int i = 0, length; i < N; ++i) {
			length = Integer.parseInt(br.readLine());
			programs[i] = new int[length];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < length; ++j) {
				programs[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int firstProgramLength = programs[0].length;
		int[] firstProgram = programs[0];
		int[] firstProgramRev = new int[firstProgramLength];
		for (int i = 0; i < firstProgramLength; ++i) {
			firstProgramRev[i] = firstProgram[firstProgramLength - i - 1];
		}

		boolean find = false;
		for (int i = 0, end = firstProgramLength - K + 1; i < end; ++i) {
			int[] table = failure(firstProgram, i, i + K);
			int[] revTable = failure(firstProgramRev, firstProgramLength - K - i, firstProgramLength - i);

			find = true;
			for (int j = 1; j < N; ++j) {
				find &= find(programs[j], firstProgram, i, i + K, table)
					| find(programs[j], firstProgramRev, firstProgramLength - K - i, firstProgramLength - i, revTable);
			}

			if (find)
				break;
		}
		System.out.println(find ? "YES" : "NO");
	}

	private static boolean find(int[] program, int[] pattern, int fromIndex, int toIndex, int[] table) {
		int n = program.length;
		int m = toIndex - fromIndex;

		int j = 0;
		for (int i = 0; i < n; ++i) {
			while (j > 0 && program[i] != pattern[fromIndex + j])
				j = table[j - 1];

			if (program[i] == pattern[fromIndex + j]) {
				++j;
				if (j == m) {
					return true;
				}
			}
		}
		return false;
	}

	private static int[] failure(int[] pattern, int fromIndex, int toIndex) {
		int n = toIndex - fromIndex;
		int[] table = new int[n];
		int j = 0;
		for (int i = 1; i < n; ++i) {
			while (j > 0 && pattern[fromIndex + i] != pattern[fromIndex + j])
				j = table[j - 1];

			if (pattern[fromIndex + i] == pattern[fromIndex + j])
				table[i] = ++j;
		}
		return table;
	}
}
