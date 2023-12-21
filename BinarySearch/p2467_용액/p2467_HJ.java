package BinarySearch.p2467_용액;
import java.util.*;
import java.io.*;
public class p2467_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 용액의 수
		long[] fluid = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++){
			fluid[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(fluid);
		int ans_left = 0;
		int ans_right = 0;
		long approx = Long.MAX_VALUE;
		for(int i = 0; i < N; ++i){
			int left = i + 1;
			int right = N - 1;
			while(left <= right){
				int mid = (left + right) / 2;
				long sum = fluid[i] + fluid[mid];
				if(sum == 0){
					ans_left = i;
					ans_right = mid;
					break;
				}
				if(Math.abs(sum) < approx){
					approx = Math.abs(sum);
					ans_left = i;
					ans_right = mid;
				}

				if(sum < 0){
					left = mid + 1;
				}else{
					right = mid - 1;
				}
			}
		}
		System.out.println(fluid[ans_left] + " " + fluid[ans_right]);
	}
}
