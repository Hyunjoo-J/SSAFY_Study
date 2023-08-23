package DynamicProgramming.p1149_RGB거리;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1149_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int house[][], min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		house = new int[N][3];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<3; i++) {
			house[0][i] = Integer.parseInt(st.nextToken());
		}
		
		// n번째 집은 n-1번째 집 중 자신과 다른 색깔 && 비용이 작은 집 + 현재 n번째 집의 비용
		// 이전까지의 누적합들을 기록함
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			house[i][0] = Math.min(house[i-1][1], house[i-1][2]) + Integer.parseInt(st.nextToken());
			house[i][1] = Math.min(house[i-1][0], house[i-1][2]) + Integer.parseInt(st.nextToken());
			house[i][2] = Math.min(house[i-1][0], house[i-1][1]) + Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<3; i++) {
			if(house[N-1][i] < min)
				min = house[N-1][i];
		}
		
		System.out.println(min);
		
		br.close();
	}
}
