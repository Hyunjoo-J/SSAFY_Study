package BFS_DFS.p2310_어드벤처게임;
//1~N까지의 번호가 붙은 방 -> 각 방에는 번호가 붙은 문이 있을 수 있다 -> 그러면 해당 문을 지나가야 함
//방 안에는 레프리콘이나 트롤이 있을 수도
//레프리콘 : 모험가의 소지금을 일정량까지 채워줌, 이미 그 이상 가지고 있다면 채워주지 않음
//트롤 : 일정량의 통행료를 지불해야함
//모험가는 소지금이 0인 상태에서 시작, 1번에서 N번방까지 가야함
//입력 : 여러 개의 미로가 들어 옴, 첫 번째 줄에 방의 개수 1 <= N <= 1000이 들어옴
//그 다음 N개의 줄에는 방의 정보가 들어옴
//방의 내용물, 그 방의 레프리콘이나 트롤이 정해놓은 금액, 다른 방으로 갈 수 있는 번호들
//0이 들어오면 미로는 더 이상 없거나, 다른 방으로 갈 수 없다는 것을 의미
//E : 빈방, L : 레프리콘, T : 트롤
//출력 : 1번 방에서 N번 방까지 갈 수 있는지 "Yes" or "No"

import java.util.*;
import java.io.*;

public class p2310_HJ {
	private static class Node {
		int v; // 현재 방 번호
		int w; //현재 소지금

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	private static final char EMPTY = 'E';
	private static final char LEPRECHAUN = 'L';
	private static final char TROLL = 'T';

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N, next;
		char[] type = new char[1001];
		int[] cost = new int[1001];
		List<List<Integer>> list = new ArrayList<>(1001);
		for (int i = 0; i < 1001; ++i)
			list.add(new ArrayList<>());
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			for (int i = 1; i <= N; ++i)
				list.get(i).clear();
			for (int i = 1; i <= N; ++i) {
				st = new StringTokenizer(br.readLine());
				type[i] = st.nextToken().charAt(0);
				cost[i] = Integer.parseInt(st.nextToken());
				while ((next = Integer.parseInt(st.nextToken())) != 0) {
					list.get(i).add(next);
				}
			}
			if (check(list, N, type, cost)) {
				sb.append("Yes\n");
			} else {
				sb.append("No\n");
			}
		}
		System.out.print(sb);
	}

	private static boolean check(List<List<Integer>> list, int N, char[] type, int[] cost) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(1, 0));
		boolean[][] visited = new boolean[N + 1][501]; //레프리콘이 채워줄 수 있는 최대 금액 500
		visited[1][0] = true;
		while(!q.isEmpty()){
			Node cur = q.poll();
			if(cur.v == N)
				return true;
			int price = 0;
			for(int next : list.get(cur.v)){
				switch (type[next]){
					case EMPTY :
						price = cur.w;
						break;
					case LEPRECHAUN:
						price = Math.max(cur.w, cost[next]);
						break;
					case TROLL:
						price = cur.w - cost[next];
						break;
				}
				if(price >= 0 && !visited[next][price]){
					visited[next][price] = true;
					q.add(new Node(next, price));
				}
			}
		}
		return false;
	}
}