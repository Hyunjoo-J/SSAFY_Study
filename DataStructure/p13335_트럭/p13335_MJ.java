package DataStructure.p13335_트럭;
 
import java.util.*;
import java.io.*;

public class p13335_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		// n : 트럭수, w : 다리의 길이, L : 다리의 최대하중
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());		
		Queue<Integer> truck = new LinkedList<>();
		Queue<Integer> bridge = new LinkedList<>();
		
		// 시간
		int cnt = 0;
		// 현재 다리 위에 있는 트럭 무게
		int nowWeight = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			truck.offer(Integer.parseInt(st.nextToken()));
		}
		
		for(int i=0; i<w; i++) {
			bridge.add(0);
		}
		
		// 다리 위에 아무것도 없을 때까지
		while(!bridge.isEmpty()) {
			cnt++;
			// 다리를 건넌 트럭 무게 제거
			nowWeight -= bridge.poll();
			
			// 다리를 건너지 않은 트럭이 없을 때까지
			if(!truck.isEmpty()) {
				if(truck.peek()+nowWeight<=L) {
					nowWeight += truck.peek();
					bridge.offer(truck.poll());
				}
				else {
					bridge.offer(0);
				}
			}
		}

		System.out.println(cnt);
		
		br.close();
	}
}
