import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p16236_YK {
	static int N;
	static int[][] arr;
	static int level = 2;
	static int[] fishes = new int[7];
	static int dx, dy;
	static int available;
	static int ate;
	static int time;
	static boolean gameOver;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					arr[i][j] = 0;
					dx = i; dy = j;
				} else if (arr[i][j] > 0) {
					fishes[arr[i][j]]++;
				}
			}
		}

		for (int i = 0; i < level; i++) {
			available += fishes[i];
		}

		while (true) {
			// 더 이상 먹을 수 있는 물고기가 없다 (남은 물고기의 레벨이 더 높거나, 물고기가 아예 없거나)
			if (available == 0) {
				break;
			}

			// 먹을 수 있는 물고기에 접근할 수 없다.
			gameOver = true;
			bfs(dx, dy, level);
			if (gameOver) {
				break;
			} else {
				// 물고기 냠냠
				arr[dx][dy] = 0;
				ate += 1;
				available -= 1;
			}

			// 레벨 업 (다음 레벨 물고기 먹을 수 있게 됨)
			if (ate == level && level < 7) {
				available += fishes[level++];
				ate = 0;
			}
		}
		
		bw.write(String.valueOf(time));

		bw.flush();
		bw.close();
		br.close();
	}

	public static void bfs(int x, int y, int level) {
		boolean[][] visited = new boolean[N][N];
		// 위 물고기, 왼쪽 물고기가 우선 순위
		int[][] di = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
		visited[x][y] = true;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y, 0 });
		int tmpTime = 0;

		while (!q.isEmpty()) {
			int[] now = q.remove();

			if (arr[now[0]][now[1]] != 0) {
				if (arr[now[0]][now[1]] < level) {
					// 가장 위 or 가장 왼쪽 물고기를 먹을 수 있도록 처리
					if (gameOver) {
						dx = now[0]; dy = now[1]; tmpTime = now[2];
						gameOver = false;
					}
					// 같은 시간이 걸리는데 더 위에 있거나, 같은 높이에서 더 왼쪽에 있을 때
					else if (tmpTime >= now[2] && (now[0] < dx || (now[0] == dx && now[1] < dy))) {
						dx = now[0]; dy = now[1];
					}
				}
			}

			for (int[] d : di) {
				int nx = now[0] + d[0];
				int ny = now[1] + d[1];
				// 경계 검사
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					// 방문하지 않았고, 0이거나 레벨이 같거나 낮은 물고기이면
					if (!visited[nx][ny] && arr[nx][ny] <= level) {
						visited[nx][ny] = true;
						q.add(new int[] { nx, ny, now[2] + 1 });
					}
				}
			}
		}
		time += tmpTime;
	}
}