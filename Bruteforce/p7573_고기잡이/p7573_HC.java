package Bruteforce.p7573_고기잡이;

import java.io.*;
import java.util.*;

public class p7573_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Fish[] fishes = new Fish[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			fishes[i] = new Fish(Integer.parseInt(st.nextToken()),
			 					 Integer.parseInt(st.nextToken()));
		}

		int length = l >> 1;
		int answer = 0;
		for (int xLength = 1; xLength < length; ++xLength) {
			int yLength = length - xLength;
			for (Fish fish1: fishes) {
				for (Fish fish2: fishes) {
					int x = fish1.x + xLength > N ? N - xLength : fish1.x;
					int y = fish2.y + yLength > N ? N - yLength : fish2.y;
					if (x >= 1 && y >= 1 && x + xLength <= N && y + yLength <= N)
						answer = Math.max(answer, catchFishes(fishes, x, y, xLength, yLength));
				}
			}
		}
		System.out.println(answer);
	    br.close();
	}

	private static int catchFishes(Fish[] fishes, int x, int y, int xLength, int yLength) {
		int count = 0;
		for (Fish fish: fishes) {
			if (fish.x < x || fish.x > x + xLength ||
				fish.y < y || fish.y > y + yLength)
				continue;
			++count;
		}
		return count;
	}

	private static class Fish {
		int x, y;

		public Fish(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
