package Bruteforce.p1747_소수and팰린드롬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class p1747_YK {
	
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		
		if (N <= 2) {
			System.out.println(2);
			return;
		}
		
		int result = N - 1;
		top:
		while (++result >= N) {
			// 펠린드롬이 아니면 continue
			String num = String.valueOf(result);
			for (int i = 0, j = num.length() - 1; i <= j; i++, j--) {
				if (num.charAt(i) != num.charAt(j)) continue top;  
			}
			
			// 소수가 아니면 continue
			if (result < 8 && (result & 1) != 0 ) break;
			if ((result & 1) == 0) continue;
			for (int i = 3; i <= Math.sqrt(result); i+=2) {
				if (result % i == 0) continue top;
			}
			
			// 펠린드롬 & 소수 이면 break
			break;
		}
		
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}

}

