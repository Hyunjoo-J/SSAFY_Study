package DivideAndConquer.p2630_색종이만들기;

import java.util.*;
import java.io.*;

public class p2630_HJ {
	static int white, blue;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		divide(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void divide(int r, int c, int size) {
		if(check(r, c, size)) {
			if(map[r][c] == 0)
				++white;
			else
				++blue;
			return;
		}
		int s = size/2;
		divide(r, c, s);
		divide(r, c + s, s);
		divide(r + s, c, s);
		divide(r + s, c + s, s);
	}

	private static boolean check(int r, int c, int size) {
		int color = map[r][c];
		for(int i = r; i < r + size; ++i)
			for(int j = c; j < c + size; ++j) {
				if(color != map[i][j])
					return false;
			}
		return true;
	}

}