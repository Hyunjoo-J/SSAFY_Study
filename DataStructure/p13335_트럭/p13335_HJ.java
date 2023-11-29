package DataStructure.p13335_트럭;
import java.util.*;
import java.io.*;
public class p13335_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Deque<Integer> q = new ArrayDeque<>();
		int[] truck = new int[N];
		for(int i = 0; i < N; ++i){
			truck[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0, ans = 0;
		for(int w : truck){
			while(true){
				if(q.isEmpty()){
					q.add(w);
					sum += w;
					++ans;
					break;
				}else if(q.size() == W){
					sum -= q.poll();
				}else{
					++ans;
					if(sum + w > L){
						q.add(0);
					}else{
						q.add(w);
						sum += w;
						break;
					}
				}
			}
		}
		System.out.println(ans + W);
	}
}
