package Greedy.p1700_멀티탭스케줄링;
import java.io.*;
import java.util.*;

public class p1700_HJ {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] schedule = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; ++i) {
			schedule[i] = Integer.parseInt(st.nextToken());
		}
		//지금 내가 진행하려는 스케줄이 이미 꽂혀있는지 확인
		boolean[] plug = new boolean[101];
		int use = 0;
		int ans = 0;
		for (int i = 0; i < K; ++i) {
			int now = schedule[i];

			if (!plug[now]) {
				if (use < N) {
					plug[now] = true;
					++use;
				} else {
					ArrayList<Integer> list = new ArrayList<>();
					for (int j = i; j < K; ++j) {
						if (plug[schedule[j]] && !list.contains(schedule[j])) {
							list.add(schedule[j]);
						}
					}
					if (list.size() != N) {
						for (int j = 0; j < 101; ++j) {
							if (plug[j] && !list.contains(j)) {
								plug[j] = false;
								break;
							}
						}
					} else { //현재 꽂혀있는 콘센트가 나중에 모두 사용될 경우
						int pop = list.get(list.size() - 1);//가장 마지막에 사용될 스케줄 제거
						plug[pop] = false;
					}
					plug[now] = true;
					++ans;
				}
			}
		}
		System.out.println(ans);
	}
}
