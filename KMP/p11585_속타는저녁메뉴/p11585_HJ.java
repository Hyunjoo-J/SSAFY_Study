package KMP.p11585_속타는저녁메뉴;
import java.io.*;
public class p11585_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] txt = new char[2 * N];
		char[] pattern = new char[N];
		String line1 = br.readLine();
		String line2 = br.readLine();
		for(int i = 0; i < N; ++i){
			txt[i] = txt[i + N] = line1.charAt(i * 2);
			pattern[i] = line2.charAt(i * 2);
		}

		int tlen = txt.length - 1;
		int plen = pattern.length;

		int[] p = new int[plen];
		for(int i = 1, j = 0; i < plen; ++i){
			while(j > 0 && pattern[i] != pattern[j]){
				j = p[j - 1];
			}
			if(pattern[i] == pattern[j])
				p[i] = ++j;
		}
		int cnt = 0;
		for(int i = 0, j = 0; i < tlen; ++i){
			while(j > 0 && txt[i] != pattern[j])
				j = p[j - 1];
			if(txt[i] == pattern[j]){
				if(j == plen - 1){
					++cnt;
					j = p[j];
				}else{
					++j;
				}
			}
		}
		int common = gcd(cnt, N);
		System.out.println(cnt/common + "/" + N/common);
	}

	private static int gcd(int a, int b){
		if(b == 0)
			return a;
		return (gcd(b, a % b));
	}
}
