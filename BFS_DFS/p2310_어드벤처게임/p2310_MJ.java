package BFS_DFS.p2310_어드벤처게임;

import java.util.*;
import java.io.*;

public class p2310_MJ {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, money;
	static String room[][];
	static boolean visit[], escape;

	public static void main(String[] args) throws Exception {
		
		while(true) {
		
		n = Integer.parseInt(br.readLine());
		if(n==0)
			break;
		
		room = new String[n+1][1010];
		visit = new boolean[n+1];
		money = 0;
		escape = false;
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = 0;
			while(st.hasMoreTokens()) {
				String tmp = st.nextToken();
				room[i][idx++] = tmp;
			}
		}
		
		enter(1);
		
		if(escape)
			System.out.println("Yes");
		else
			System.out.println("No");
		
		}
		
		br.close();
	}
	
	private static void enter(int roomNum) {
		
		int oriMoney = money;
		
		if(room[roomNum][0].equals("T")) {
			int Tmoney = Integer.parseInt(room[roomNum][1]);
			money -= Tmoney;
		}
		else {	
			int Lmoney = Integer.parseInt(room[roomNum][1]);
			money = (money<Lmoney)?Lmoney:money;
		}
		
		if(money>=0) {
			if(roomNum==n) {
				escape = true;
				return;
			}
			
			visit[roomNum] = true;
			for(int i=2; !room[roomNum][i].equals("0"); i++) {
				int nextRoom = Integer.parseInt(room[roomNum][i]);
				if(!visit[nextRoom]) {
					enter(nextRoom);
				}
			}
			visit[roomNum] = false;
			money = oriMoney;
		}
	}
}
