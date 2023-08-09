import java.io.*;

public class p1747_HJ {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if(n == 1 || n == 2) {
			System.out.println("2");
			return;
		}
		int i = n;
		for(; ; i++) {
			if(!isPrime(i))
				continue;
			if(!isPali(i))
				continue;
			break;
		}
		System.out.println(i);
	}
	
	private static boolean isPrime(int num) {
		if(num % 2 == 0 || num == 1)
			return false;
		for(int i = 3; i *i <= num; i += 2) {
			if(num % i == 0)
				return false;
		}
		return true;
	}
	private static boolean isPali(int num) {
		String str = Integer.toString(num);
		int size = str.length();
		if(size == 1)
			return true;
		for(int i = 0, lim = size/2; i < lim; i++) {
			if(str.charAt(i) != str.charAt(size - i - 1))
				return false;
		}
		return true;
	}

}
