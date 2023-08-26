package DynamicProgramming.p11060_점프점프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11060_MJ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N];
		
		st = new StringTokenizer(br.readLine()); 
		for(int i=0; i<N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int num = 0;
		int cnt = 0;
		
		top:
		while(num!=N-1) { // 현재 숫자가 마지막 숫자가 아닐 경우에 반복문
			int max = 0;
			int maxNum = -1;
			cnt++;
			// 1부터 최대 점프 수까지
			for(int i=1; i<map[num]+1; i++) {
				// 점프하다가 마지막 숫자를 만났을 경우 전체 반복문 break
				if(num+i==N-1) {
					break top;
				}
				// 점프한 위치가 0이면 continue
				if(map[num+i]==0) {
					continue;
				}
				
				//  기존 점프한 수 + 이 위치에서 점프할 수 있는 수가 max 보다 크면 기록
				if(map[num+i] + i > max) {
					max = map[num+i] + i;
					maxNum = i;
				}
			}
			// 점프한 위치들이 전부 0일 때 cnt = -1
			if(max<1) {
				cnt = -1;
				break;
			}
			num += maxNum;
		}
		
		System.out.println(cnt);
	
		br.close();
	}
}
