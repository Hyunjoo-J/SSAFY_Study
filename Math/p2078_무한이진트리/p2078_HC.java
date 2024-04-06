package Math.p2078_무한이진트리;

import java.io.*;
import java.util.*;

public class p2078_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[] answer = {0, 0};
		while (L > 1 || R > 1) {
			if (L > R) {
				L -= R;
				++answer[0];
			} else {
				R -= L;
				++answer[1];
			}
		}
		System.out.println(answer[0] + " " + answer[1]);
	    br.close();
	}
}
