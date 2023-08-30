package Dijkstra.p1446_지름길;

import java.io.*;
import java.util.*;

public class p1446_HC {

	private static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		List<List<Node>> shortcut = new ArrayList<>(D + 1);
		for (int i = 0; i <= D; ++i) {
			shortcut.add(new ArrayList<>());
		}

		int s, e, l;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			if (e <= D)
				shortcut.get(s).add(new Node(e, l));
		}

		int[] distance = new int[D + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
		heap.add(new Node(0, 0));
		distance[0] = 0;

		while (!heap.isEmpty()) {
			Node currNode = heap.poll();

			if (currNode.idx == D)
				break;

			if (distance[currNode.idx] < currNode.cost)
				continue;

			if (distance[currNode.idx + 1] > currNode.cost + 1) {
				distance[currNode.idx + 1] = currNode.cost + 1;
				heap.add(new Node(currNode.idx + 1, currNode.cost + 1));
			}

			int cost;
			for (Node nextNode: shortcut.get(currNode.idx)) {
				cost = currNode.cost + nextNode.cost;
				if (distance[nextNode.idx] > cost) {
					distance[nextNode.idx] = cost;
					heap.add(new Node(nextNode.idx, cost));
				}
			}
		}
		bw.write("" + distance[D]);
	    bw.flush();
	    bw.close();
	    br.close();
	}
}
