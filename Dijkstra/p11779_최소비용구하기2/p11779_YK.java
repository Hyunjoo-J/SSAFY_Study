package Dijkstra.p11779_최소비용구하기2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p11779_YK {
	static class Node {
		int vertex, weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<Node>[] list = new ArrayList[N + 1];
		ArrayList<Integer>[] route = new ArrayList[N + 1];
		for (int i = 0; i <= N; ++i) {
			list[i] = new ArrayList<>();
			route[i] = new ArrayList<>();
		}
		
		int from, to, weight;
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		final int INF = Integer.MAX_VALUE;
		int[] distance = new int[N + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		
		boolean[] visited = new boolean[N + 1];
		route[start].add(start);
		
		int min, stopOver;
		for (int i = 0; i < N; ++i) {
			min = INF;
			stopOver = -1;
			for (int j = 1; j <= N; ++j) {
				if (visited[j] || min <= distance[j]) continue;
				min = distance[j];
				stopOver = j;
			}
			
			if (stopOver == -1) break;
			visited[stopOver] = true;
			if (stopOver == end) break;
			
			for (Node tmp : list[stopOver]) {
				if (!visited[tmp.vertex] && distance[tmp.vertex] > min + tmp.weight) {
					route[tmp.vertex] = new ArrayList<>();
					route[tmp.vertex].addAll(route[stopOver]);
					route[tmp.vertex].add(tmp.vertex);
					distance[tmp.vertex] = min + tmp.weight;
				}
			}
//			
//			System.out.println(stopOver + " " + Arrays.toString(distance));
//			System.out.println(Arrays.toString(route));
		}
		
		bw.write(distance[end] + "\n");
		bw.write(route[end].size() + "\n");
		for (int k : route[end]) {
			bw.write(k + " ");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
}
