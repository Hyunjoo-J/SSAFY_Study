import java.util.Scanner;

public class P1747_MJ {

	static int startN;
	static String stringN;
	
	private static int calc(int n) {
		
		// startN이 1 or 2일 때는 답이 2다.
		if(n<3)
			return 2;
		
		// startN이 짝수면 홀수로 만들어준다.
		if(n%2==0) {
			n++;
		}
			
		top :
		while(true) {
			// 소수는 자기보다 작은 수(1 제외)로 나누었을 때 나머지가 0인 경우가 없어야 한다.
			// 홀수인 값만 이용하고 있어서 짝수로 나눠줄 필요없음(i+2)
			// Math.sqrt(n) -> a*b가 n일 때 a만 확인해주면 b는 굳이 할 필요없음
			for(int i=3; i<=Math.sqrt(n); i=i+2) {
				if(n%i==0) {
					n += 2;
					continue top;
				}
			}		
					
			stringN = Integer.toString(n);
			// idx:0과 맨끝, idx:1과 맨끝-1 이런 식으로 순서대로 비교한다.
			for(int i=0; i<=stringN.length()/2-1;i++) {
				if(stringN.charAt(i) != stringN.charAt(stringN.length()-1-i)) {
					n += 2;
					continue top;
				}
			}
			
			break;
		}
		
		return n;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		startN = sc.nextInt();
		
		System.out.println(calc(startN));
		
		sc.close();
	}
}
