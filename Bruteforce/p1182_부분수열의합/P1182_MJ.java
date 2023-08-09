package Silver2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1182_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, S, input[], output[], sum, count=0;
	
	static void subset(int cnt, int length) {
		if(cnt==N) {
			sum = 0;
			// 전체가 아닌 length까지만 불러옴
			for(int i=0; i<length; i++)
				sum += output[i];
			if(sum==S)
				count++;
			return;
		}
		
		// input[cnt]가 포함될 때
		output[length] = input[cnt];
		subset(cnt+1, length+1);
		
		// 포함되지 않을 때
		subset(cnt+1, length);	
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		input = new int[N];
		output = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0, 0);
		
		// 아무것도 없을 때({ })도 부분집합에 포함되기 때문에 하나를 빼줌
		if(S==0)
			System.out.print(count-1);
		else
			System.out.print(count);
		
		br.close();
	}
}