package DataStructure.p13335_트럭;

import java.io.*;
import java.util.*;

public class p13335_HC {

	private static class Bridge {
		int wgh, time;
		int qIdx, length;
		int[] queue;

		Bridge(int length) {
			this.wgh = 0;
			this.time = 0;
			this.qIdx = 0;
			this.length = length;
			this.queue = new int[length];
			Arrays.fill(queue, -1);
		}

		void move() {
			++time;
			wgh -= Math.max(queue[qIdx], 0);
			queue[qIdx] = -1;
			qIdx = (qIdx + 1) % length;
		}

		void goUp(int weight) {
			queue[(qIdx - 1 + length) % length] = weight;
			wgh += weight;
		}
	}

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		int[] a = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		Bridge bridge = new Bridge(w);
		for (int i = 0; i < n; ++i) {
			do {
				bridge.move();
			} while (bridge.wgh > L - a[i]);

			bridge.goUp(a[i]);
		}
		while (bridge.wgh > 0) {
			bridge.move();
		}
		System.out.println(bridge.time);
	    br.close();
	}

}
