package Bruteforce.p3085_사탕게임;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class p3085_YK {
	static int N, result;
	static char[][] input;
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		input = new char[N][N];

		for (int i = 0; i < N; ++i) {
			input[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				f(i, j);
			}
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}

	private static void f(int x, int y) {
		for (int i = 0; i < 2; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= N || ny >= N || input[x][y] == input[nx][ny])
				continue;
			
			change(x, y, nx, ny);
			check();
			change(x, y, nx, ny);
		}
	}

	private static void check() {
		int tmp = 1;
		char ch = ' ';
		
		for (int i = 0; i < N; i++) {
			tmp = 1;
			ch = ' ';
			for (int j = 0; j < N; j++) {
				if (input[i][j] != ch) {
					ch = input[i][j];
					tmp = 1;
				}
				else {
					tmp += 1;
				}
				result = Math.max(result, tmp);
			}
			
			
			tmp = 1;
			ch = ' ';
			for (int j = 0; j < N; j++) {
				if (input[j][i] != ch) {
					ch = input[j][i];
					tmp = 1;
				}
				else {
					tmp += 1;
				}
				result = Math.max(result, tmp);
			}
		}
	}

	private static void change(int x, int y, int nx, int ny) {
		char tmp = input[x][y];
		input[x][y] = input[nx][ny];
		input[nx][ny] = tmp;
	}
}