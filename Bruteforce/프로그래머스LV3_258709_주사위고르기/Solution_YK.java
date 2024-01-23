package Bruteforce.프로그래머스LV3_258709_주사위고르기;

public class Solution_YK {

    static int N, R, max;
    static int[] diceA, diceB, answer;
    static boolean[] visited;
    static int[][] dices;
    static int[] sumA, sumB, psumA, psumB, selected;

    public int[] solution(int[][] dice) {
        N = dice.length;
        R = N >> 1;

        answer = new int[R];
        visited = new boolean[N];
        selected = new int[R];
        dices = dice;
        diceA = new int[R];
        diceB = new int[R];

        combi(0, 0);
        return answer;
    }

    private void combi(int cnt, int start) {
        if (cnt == R) {
            simul();
            return;
        }

        for (int i = start; i < N; ++i) {
            visited[i] = true;
            combi(cnt + 1, i + 1);
            visited[i] = false;
        }
    }

    private void simul() {
        for (int i = 0, a = 0, b = 0; i < N; ++i) {
            if (visited[i]) diceA[a++] = i;
            else diceB[b++] = i;
        }

        // 주사위 눈 합을 계산 해놓기
        sumA = new int[501];
        sumB = new int[501];
        psumA = new int[501];
        psumB = new int[501];

        perm(0);

        // 누적 합
        for (int i = 1; i <= 500; ++i) {
            psumA[i] += psumA[i - 1];
            psumB[i] += psumB[i - 1];
        }

        // 승리 수 계산
        int wins = play();

        // answer 갱신 시 
        if (wins > max) {
            max = wins;
            for (int i = 0; i < R; ++i) {
                answer[i] = diceA[i] + 1;
            }
        }
    }

    private void perm(int cnt) {
        if (cnt == R) {
            int s1 = 0, s2 = 0;
            for (int i = 0; i < R; ++i) {
                s1 += dices[diceA[i]][selected[i]];
                s2 += dices[diceB[i]][selected[i]];
            }
            sumA[s1] += 1;
            sumB[s2] += 1;
            psumA[s1] += 1;
            psumB[s2] += 1;

            return;
        }

        for (int i = 0; i < 6; ++i) {
            selected[cnt] = i;
            perm(cnt + 1);
        }
    }

    private int play() {
        int wins = 0;
        for (int i = 1; i <= 500; ++i) {
            wins += psumB[i - 1] * sumA[i];
        }
        return wins;
    }
}