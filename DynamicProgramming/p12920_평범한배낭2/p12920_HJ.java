package DynamicProgramming.p12920_평범한배낭2;

import java.io.*;
import java.util.*;

public class p12920_HJ {
	static class Item{
		int w;
		int v;
		public Item(int w, int v) {
			this.w = w;
			this.v = v;
		}		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//배열의 사이즈를 정할 수 없기때문에 배열이 아닌 list 사용
		ArrayList<Item> item = new ArrayList<>();
		//비트 연산 한 값들을 가지고 일반 배낭 문제와 똑같이 풀다보면 
		//각각이 들어가고 안들어가는 모든 경우의 수를 만족하게 됨
		for(int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			for(int j = 0; k > 0; ++j) {
				//물건의 수가 1111(2)처럼 모두 1로만 구성 되어있지 않으면 모든 비트 값을 저장하면 값이 실제보다 커질 수 있다.
				//13 = 8 + 4 + 1만 있으면 되지만 실제 8,4,2,1 모두 저장한다면 물건의 개수가 15인것까지 표현 가능하다.
				int cnt = Math.min(1 << j, k);
				item.add(new Item(v * cnt, c * cnt));
				k -= cnt;
			}
		}
		int size = item.size();
		int[][] dp = new int[size + 1][M + 1];
		for(int i = 1; i <= size; ++i) {
			for(int j = 1; j <= M; ++j) {
				if(j < item.get(i - 1).w) {
					dp[i][j] = dp[i - 1][j];
				}else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item.get(i - 1).w] + item.get(i - 1).v);
				}
			}
		}
		System.out.println(dp[size][M]);

	}

}