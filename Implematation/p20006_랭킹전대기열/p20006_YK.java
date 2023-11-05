package Implematation.p20006_랭킹전대기열;

import java.io.*;
import java.util.*;
public class p20006_YK {

    static class Player {
        String name;
        int level;

        public Player(String name, int level) {
            this.name = name;
            this.level = level;
        }
    }

    static class Room {
        int minLevel, maxLevel;
        PriorityQueue<Player> players;

        public Room(int minLevel, int maxLevel, Player player) {
            this.minLevel = minLevel;
            this.maxLevel = maxLevel;
            players = new PriorityQueue<>(Comparator.comparing(o -> o.name));
            players.add(player);
        }
    }

    static class Game {
        List<Room> rooms;

        public Game() {
            rooms = new ArrayList<>();
        }
    }

    static StringBuilder sb = new StringBuilder();
    static int m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Game game = new Game();
        while (p-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();

            List<Room> rooms = game.rooms;
            int num = check(game.rooms, l);
            if (num == -1) {
                rooms.add(new Room(l - 10, l + 10, new Player(n, l)));
            } else {
                rooms.get(num).players.add(new Player(n, l));
            }
        }

        int size = game.rooms.size();
        for (int i = 0; i < size; ++i) {
            if (game.rooms.get(i).players.size() == m) getGameStatus(game.rooms, i, "Started!");
            else getGameStatus(game.rooms, i, "Waiting!");
        }
        System.out.println(sb);
        br.close();
    }

    private static void getGameStatus(List<Room> rooms, int room, String status) {
        sb.append(status).append("\n");
        while (!rooms.get(room).players.isEmpty()) {
            Player player = rooms.get(room).players.poll();
            sb.append(player.level).append(" ").append(player.name).append("\n");
        }
    }

    private static int check(List<Room> rooms, int l) {
        if (rooms.isEmpty()) return -1;
        int size = rooms.size();
        for (int i = 0; i < size; ++i) {
            Room room = rooms.get(i);
            if (l > room.maxLevel || l < room.minLevel) continue;
            if (room.players.size() == m) continue;
            return i;
        }
        return -1;
    }
}
