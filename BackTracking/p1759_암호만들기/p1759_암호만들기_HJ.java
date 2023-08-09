import java.io.*;
import java.util.*;

public class p1759_암호만들기_HJ {
	static int L, C, ae, bc;
	static String[] strs;
	static String[] sel;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		strs = new String[C];
		sel = new String[L];
		for (int i = 0; i < C; i++) {
			strs[i] = st.nextToken();
		}
		Arrays.sort(strs);
		comb(0,0);
		System.out.println(sb);
	}

	private static void comb(int cnt, int start) {
		if (cnt == L) {
			ae = 0;
			bc = 0;
			for (int i = 0; i < L; i++) {
				if (sel[i].equals("a") || sel[i].equals("e") || sel[i].equals("i") || sel[i].equals("o") || sel[i].equals("u"))
					ae++;
				else
					bc++;
			}
			if (ae > 0 && bc > 1) {
				for(int i = 0; i < L; i++) {
					sb.append(sel[i]);
				}
				sb.append("\n");
			}
			return;
		}
		for (int i = start; i < C; i++) {
			sel[cnt] = strs[i];
			comb(cnt + 1, i + 1);
		}

	}
}
