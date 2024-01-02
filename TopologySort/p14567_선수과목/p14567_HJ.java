package TopologySort.p14567_선수과목;
import java.util.*;
import java.io.*;
public class p14567_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] possible = new int[N + 1];

		int[] inDegree = new int[N + 1];
		List<Integer>[] graph = new ArrayList[N + 1];
		for(int i = 0; i <= N; ++i)
			graph[i] = new ArrayList<>();

		for(int i = 0, a, b; i < M; ++i){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			++inDegree[b];
		}

		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= N; ++i){
			if(inDegree[i] == 0){
				q.add(i);
				possible[i] = 1;
			}
		}
		for(int i = 0; i < N; ++i){
			int cur = q.remove();
			for(int next : graph[cur]){
				possible[next] = Math.max(possible[cur] + 1, possible[next]);
				if(--inDegree[next] == 0)
					q.add(next);
			}
		}

		for(int i = 1; i <= N; ++i)
			sb.append(possible[i]).append(" ");
		System.out.println(sb.toString());
	}
}
