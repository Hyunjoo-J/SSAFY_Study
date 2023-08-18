package BFS_DFS.p16953_AtoB;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p16953_MJ {

	static long A, B;
	static int cnt;
	static Queue<Long> queue = new LinkedList<>();
	static boolean visited[];

	static void bfs() {
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			cnt++;
			for (int i = 0; i < queueSize; i++) {
				long num = queue.poll();

				if (num == B) {
					return;
				}
				
				if (num * 2 <= B && visited[(int)(num * 2)] == false) {
					queue.offer(num * 2);
					visited[(int)(num * 2)] = true;
				}

				if (num * 10 + 1 <= B && visited[(int)(num * 10 + 1)] == false) {
					queue.offer((num * 10) + 1);
					visited[(int)(num * 10 + 1)] = true;
				}
			}
		}
		cnt = -1;
		return;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();

		visited = new boolean[1000000000];

		queue.offer(A);
		visited[(int)(A)] = true;
		
		bfs();

		System.out.println(cnt);
		
		sc.close();
	}
}
