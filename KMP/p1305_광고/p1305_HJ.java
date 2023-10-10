package KMP.p1305_광고;

import java.io.*;
public class p1305_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		char[] txt = br.readLine().toCharArray();
		int[] t = new int[L];
		for(int i = 1, j = 0; i < L; ++i){
			while(j > 0 && txt[i] != txt[j])
				j = t[j - 1];
			if(txt[i] == txt[j])
				t[i] = ++j;
		}
		System.out.println(L - t[L - 1]);
	}
}

