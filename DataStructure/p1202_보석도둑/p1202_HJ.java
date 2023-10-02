package DataStructure.p1202_보석도둑;
import java.util.*;
import java.io.*;
public class p1202_HJ {
	static class Pair{
		int weight, value;

		public Pair(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Pair bag[] = new Pair[N];
		int[] limit = new int[K];
		for(int i = 0; i < N; ++i){
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			bag[i] = new Pair(weight, value);
		}
		for(int i = 0; i < K; ++i){
			limit[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(limit);
		Arrays.sort(bag, Comparator.comparingInt(o -> o.weight));
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
		long ans = 0;
		int idx = 0;
		for(int lim : limit){
			while(idx < N && bag[idx].weight <= lim){
				pq.add(bag[idx].value);
				++idx;
			}
			if(!pq.isEmpty())
				ans += pq.poll();
		}
		System.out.println(ans);
	}
}
