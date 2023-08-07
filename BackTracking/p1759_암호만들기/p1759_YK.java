import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class p1759_YK {
	static int L, C;
	static boolean[] alpha, isSelected;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alpha = new boolean[26];
		isSelected = new boolean[26];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alpha[st.nextToken().charAt(0) - 'a'] = true;
		}
		
		combi(0, 0, 0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static void combi(int cnt, int letters, int conso) {
		while (cnt < 26 && !alpha[cnt]) ++cnt;
		
		if (cnt == 26 || letters == L) {
			// 끝까지 돌았으나 글자수 부족 or 모음 없음 or 자음이 두 개가 안됨
			if (letters < L || conso == L || conso < 2) return; 
			
			for (int i = 0; i < 26; i++) {
				if (isSelected[i]) sb.append((char) (i + 'a'));
			}
			sb.append("\n");
			return;
		}
		
		isSelected[cnt] = true;
		if (cnt == 0 || cnt == 4 || cnt == 8 || cnt == 14 || cnt == 20) 
			combi(cnt + 1, letters + 1, conso);
		else 
			combi(cnt + 1, letters + 1, conso + 1);
		
		isSelected[cnt] = false;
		combi(cnt + 1, letters, conso);
	}
}