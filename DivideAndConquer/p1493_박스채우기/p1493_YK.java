package DivideAndConquer.p1493_박스채우기;

import java.io.*;
import java.util.*;

public class p1493_YK {
	static int result = 0;
	static int[] cubes;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int length = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		
		int N = Integer.parseInt(br.readLine());
		cubes = new int[20];
		
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			cubes[a] = b;
		}
		
		fillCube(length, width, height);
		System.out.println(result);
		br.close();
	}

	private static void fillCube(int length, int width, int height) {
		if (result == -1) return;
		if (length == 0 || width == 0 || height == 0) return;
//		if (length < -50) {
//			result = -1;
//			return;
//		}
//		System.out.println(length + " " + width + " " + height);
		
		int min = Math.min(length, Math.min(width, height));
		int k = 0;
		while (min >= (1 << k)) {
			++k;
		}
		if (k > 0) --k;

//		System.out.println(min + " " + k);
		while (k > -1 && cubes[k] < 1) {
			--k;
		}
		
		if (k == -1) {
			result = -1;
			return;
		}
		
		--cubes[k];
		++result;
		int len = (int)Math.pow(2, k);
		fillCube(length - len, len, len);
		fillCube(length, width - len, height);
		fillCube(length, len, height - len);
	}

}
