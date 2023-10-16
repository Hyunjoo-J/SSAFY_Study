package String.p1294_문자열장식;

import java.util.*;
import java.io.*;
public class p1294_HJ {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<String> pq = new PriorityQueue<>();
		for(int i = 0; i < N; ++i){
			pq.add(br.readLine()+'a');
		}
		while(!pq.isEmpty()){
			String cur = pq.poll();
			sb.append(cur.charAt(0));
			if(cur.length() > 2)
				pq.add(cur.substring(1));
		}
		System.out.println(sb.toString());
	}
}

