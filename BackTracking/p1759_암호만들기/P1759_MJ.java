import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P1759_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int L, C, mo, za;
	static char input[], output[], tmp[];
	static List<String> output_print = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	static void pwPossible(char pw[]) {
		mo = 0;
		za = 0;

		// 모음이 최소 1개 있는지 확인
		for(int i=0; i<L; i++) {
			if(pw[i]=='a' || pw[i]=='e' || pw[i]=='i' || pw[i]=='o' || pw[i]=='u') {
				mo = 1;
				break;
			}
		}

		// 자음이 최소 2개 있는지 확인
		for(int i=0; i<L; i++) {
//			if(pw[i]!='a' && pw[i]!='e' && pw[i]!='i' && pw[i]=='o' && pw[i]!='u')			
			if(!(pw[i]=='a' || pw[i]=='e' || pw[i]=='i' || pw[i]=='o' || pw[i]=='u'))
				za++;
			if(za>1)
				break;
		}
		
		// 모음이 최소 1개, 자음이 최고 2개 있다면
		if(mo>0&&za>1) {
			tmp = pw.clone();
			// 암호 하나 sort
			Arrays.sort(tmp);
			for(int i=0; i<L; i++) {
				sb.append(tmp[i]);
			}
			output_print.add(sb.toString());	
			sb.setLength(0);
		}
	}
	
	static void pwFind(int cnt, int start) {
		if(cnt == L) {
			pwPossible(output);
			return;
		}
		
		for(int i = start; i<C; i++) {
			output[cnt] = input[i];
			pwFind(cnt+1, i+1);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		input = new char[C];
		output = new char[L];
		tmp = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		
		pwFind(0,0);
		
		// 가능성 있는 암호들 전부 sort
		Collections.sort(output_print);
		for(int i=0; i<output_print.size(); i++)
			System.out.println(output_print.get(i));
		
		br.close();
	}
}
