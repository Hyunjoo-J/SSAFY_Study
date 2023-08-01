package Gold2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P12851_MJ {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Queue<Integer> queue = new LinkedList<Integer>();
	static int subin, sister, depth=0, queueSize, ans=0;
	static boolean visited[] = new boolean[100001];
	
	static void bfs() {
		while(!queue.isEmpty()) {
			queueSize = queue.size();
			for(int i=0; i<queueSize; i++) {
				subin = queue.poll();
				
				// sister 위치는 언제나 false 상태로 있어야 여러번 탐색 가능
				if(subin!=sister)
					// add 가 아닌 poll 할 때 방문체크해야함
					visited[subin]=true;
				
				if(subin == sister) {
					ans++;
					// 같은 depth 내에서 정답이 더 있는지 찾기 위해 continue
					continue;
				}
				
				if(subin+1<100001&&!visited[subin+1])
					queue.add(subin+1);
					
				if(subin-1>-1&&!visited[subin-1])
					queue.add(subin-1);
				
				if(subin*2<100001&&!visited[subin*2])
					queue.add(subin*2);

			}
			if(ans!=0)
				return;
			else
				depth++;	
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		subin = Integer.parseInt(st.nextToken());
		sister = Integer.parseInt(st.nextToken());
		
		queue.add(subin);
		if(subin!=sister)
			visited[subin] = true;
		bfs();
		
		System.out.println(depth);
		System.out.println(ans);
		
		br.close();
	}
}