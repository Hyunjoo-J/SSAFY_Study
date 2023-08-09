package Silver1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2531_MJ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int dishNum, sushiNum, inARowNum, couponNum, eat, ans, max;
	static int dishMap[], visited[];
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		dishNum = Integer.parseInt(st.nextToken());
		// 스시의 번호를 알려준 순간부터 특정 스시 방문을 하는 배열 생성을 생각해야 함
		sushiNum = Integer.parseInt(st.nextToken());
		inARowNum = Integer.parseInt(st.nextToken());
		couponNum = Integer.parseInt(st.nextToken());
		
		// 전체 배열에서 inARowNum-1 만큼 앞에 있는 스시들을 뒤에 붙여줘서 회전하는 느낌을 만들어 냄 
		dishMap = new int[dishNum+inARowNum-1];
		// 스시번호 1번은 idx 1에 넣어주기 위해 크기를 +1 함
		visited = new int[sushiNum+1];
		
		for(int i=0; i<dishNum; i++) {
			dishMap[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=dishNum; i<dishMap.length; i++) {
			dishMap[i] = dishMap[i-dishNum];
		}
		
		for(int i=0; i<inARowNum; i++) {
			// 아직 특정 스시를 방문하지 않았다면 먹음 냠냠
			if(visited[dishMap[i]]==0) eat++;
			// 특정 스시를 방문했다고 표시함
			visited[dishMap[i]]++;
			// 쿠폰 스시를 방문한 적이 있다면 그대로, 아니면 쿠폰 스시를 추가로 먹음 냠냠
			if(visited[couponNum]>0)
				ans = eat;
			else
				ans = eat+1;
			
			max = ans;
		}
		
		for(int i=inARowNum; i<dishMap.length; i++) {
			// 맨앞의 스시를 뺌
			visited[dishMap[i-inARowNum]]--;
			// 맨앞의 스시를 뺐을 때 방문횟수가 0이 된다면 먹을 수 없는 것이기 때문에 먹는 것도 뺌
			if(visited[dishMap[i-inARowNum]]==0) eat--;
			// 다음 스시를 먹고 방문하지 않았다면 먹음 냠냠
			if(visited[dishMap[i]]==0) eat++;
			visited[dishMap[i]]++;
			
			if(visited[couponNum]>0)
				ans = eat;
			else
				ans = eat+1;
			
			if(ans>max)
				max = ans;
		}

		System.out.println(max);
		
		br.close();
	}
}