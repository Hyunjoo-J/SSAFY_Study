package Greedy.p2138_전구와스위치;
//N개의 스위치와 N개의 전구
//i번 스위치를 누르면 i - 1, i, i + 1번째의 전구 상태가 바뀐다.
//우리가 원하는 상태를 만들기 위해 최소 몇번의 스위치를 눌러야하는가?
//Idea : 앞에서부터 다른 걸 만나면 무조건 바꿔야함
//첫번째는 첫번째 스위치에 의해 바뀔수도, 두번째 스위치에 의해 바뀔수도있다.
import java.io.*;
public class p2138_HJ {
	private static final int INF = Integer.MAX_VALUE >> 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] origin = new int[N];
		int[] want = new int[N];
		String line = br.readLine();
		for(int i = 0; i < N; ++i)
			origin[i] = line.charAt(i) - '0';
		line = br.readLine();
		for(int i = 0; i < N; ++i)
			want[i] = line.charAt(i) - '0';
		int head = simul(origin, want, N);

		//첫번쨰 바꾸고 시작
		switchOn(origin, N, 0);
		int next = simul(origin, want, N);
		int ans = Math.min(head, next + 1);
		System.out.println(ans == INF ? -1 : ans);
	}

	private static int simul(int[] origin, int[] want,int N){
		int diff = 0;
		int[] copy = origin.clone();
		for(int i = 0; i < N - 1; ++i){
			if(copy[i] != want[i]){
				++diff;
				switchOn(copy, N, i + 1);
			}
		}
		for(int i = 0; i < N; ++i){
			if(copy[i] != want[i])
				return INF;
		}
		return diff;
	}

	private static void switchOn(int[] copy, int N, int idx){
		if(idx > 0)
			copy[idx - 1] = 1 - copy[idx - 1];
		copy[idx] = 1 - copy[idx];
		if(idx < N - 1)
			copy[idx + 1] = 1 - copy[idx + 1];
	}
}
