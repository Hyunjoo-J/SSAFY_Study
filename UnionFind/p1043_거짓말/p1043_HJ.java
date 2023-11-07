package UnionFind.p1043_거짓말;
import java.util.*;
import java.io.*;
public class p1043_HJ {
	static int[] parents;
	static boolean[] person = new boolean[51];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int ans = 0;
		parents = new int[N + 1];
		for(int i = 1; i <= N; ++i)
			parents[i] = i;
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		for(int i = 0; i < num; ++i)
			person[Integer.parseInt(st.nextToken())] = true;

		ArrayList<Integer>[] people = new ArrayList[M];
		for(int i = 0; i < M ;++i)
			people[i] = new ArrayList<>();

		int val, pre = 0;
		for(int i = 0; i < M; ++i){
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			if(num > 0){
				pre = Integer.parseInt(st.nextToken());
				people[i].add(pre);
			}
			for(int j = 1; j < num; ++j){
				val = Integer.parseInt(st.nextToken());
				people[i].add(val);
				union(pre, val);
				pre = val;
			}
		}
		int pa;
		for(int i = 1; i < person.length; ++i){
			if(person[i])
				person[find(i)] = true;
		}

		for(int i = 0; i < M; ++i){
			if(people[i].size() > 0){
				pa = find(people[i].get(0));
				if(!person[pa])
					++ans;
			}
		}
		System.out.println(ans);
	}

	private static int find(int x) {
		if(parents[x] == x)
			return parents[x] = x;
		return find(parents[x]);

	}

	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if(a != b) {
			if(a > b) {
				parents[a] = b;
			} else {
				parents[b] = a;
			}
			return true;
		}
		return false;
	}
}