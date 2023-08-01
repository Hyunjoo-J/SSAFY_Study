package Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 숨바꼭질
public class P1697_MJ {

	static Queue<Integer> queue;
	// 메모리 초과를 방지하기 위해 visited를 만들어 queue에 들어간 적 없는 수들만 넣는다.
	static boolean[] visited;
	static int ans;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int subin = Integer.parseInt(st.nextToken());
		int sister = Integer.parseInt(st.nextToken());
		int depth = 0;
		
		queue = new LinkedList<Integer>();
		visited = new boolean[100001];
		
		queue.add(subin);
		visited[subin] = true;
		bfs(sister, depth);
		
		System.out.println(ans);
		
		br.close();
		
	}
	
	static void bfs(int sister, int depth){
		int now, queueSize;
		int[] move = new int[3];
		
		while(!queue.isEmpty()) {
			queueSize = queue.size();
			for(int i = 0; i<queueSize; i++) {
				now = queue.poll();
				
				if(now == sister) {
					ans = depth;
					return;
				}
				
				move[0] = now*2;
				move[1] = now+1;
				move[2] = now-1;
				
				
				for(int j=0; j<3; j++) {
					if(0<=move[j]&&move[j]<=100000&&!visited[move[j]]) {
						queue.add(move[j]);
						visited[move[j]] = true;
					}
				}
			}
			depth++;
		}
	}
}