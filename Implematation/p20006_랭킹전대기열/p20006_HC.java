package Implematation.p20006_랭킹전대기열;

import java.io.*;
import java.util.*;

public class p20006_HC {

	private static class Player {
		int level;
		String nickname;

		public Player(int level, String nickname) {
			this.level = level;
			this.nickname = nickname;
		}
	}

	private static class Room {
		int lowerLimit, upperLimit;
		Queue<Player> heap = new PriorityQueue<>((o1, o2) -> o1.nickname.compareTo(o2.nickname));
		int count;
		int capacity;

		Room(int level, int capacity) {
			lowerLimit = level - 10;
			upperLimit = level + 10;
			count = 0;
			this.capacity = capacity;
		}

		boolean isAvailable(int level) {
			return count < capacity && lowerLimit <= level && level <= upperLimit;
		}

		void push(Player player) {
			++count;
			heap.add(player);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		List<Room> rooms = new ArrayList<>(p + 1);

		for (int i = 0; i < p; ++i) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			String nickname = st.nextToken();

			boolean find = false;
			for (Room room: rooms) {
				if (room.isAvailable(l)) {
					room.push(new Player(l, nickname));
					find = true;
					break;
				}
			}
			if (!find) {
				Room room = new Room(l, m);
				room.push(new Player(l, nickname));
				rooms.add(room);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (Room room: rooms) {
			if (room.count == m)
				sb.append("Started!\n");
			else
				sb.append("Waiting!\n");

			Queue<Player> players = room.heap;
			while (!players.isEmpty()) {
				Player player = players.poll();
				sb.append(player.level).append(" ")
					.append(player.nickname).append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}
}
