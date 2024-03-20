package Bruteforce.프로그래머스LV2_92342_양궁대회;

import java.util.Arrays;

public class Solution_YK {

    int[] answer = new int[11];
    int[] hitInfo;
    int[] info = new int[11], myInfo = new int[11];
    int maxScoreDiff = 0;
    int N;

    public int[] solution(int n, int[] info) {

        N = n;
        for (int i = 0; i < 11; ++i) {
            this.info[i] = info[10 - i];
        }

        hitInfo = new int[n];
        combiR(0, n);

        if (maxScoreDiff == 0) return new int[] {-1};
        int[] reversed = new int[11];
        for (int i = 0; i < 11; ++i) {
            reversed[i] = answer[10 - i];
        }
        return reversed;
    }

    public void combiR(int cnt, int num) {
        if (cnt > 10) {
            if (num > 0) return;

            int scoreDiff = calculateScore();
            if (scoreDiff < maxScoreDiff) return;

            maxScoreDiff = scoreDiff;
            answer = myInfo.clone();
            return;
        }

        for (int i = 0; i <= num; ++i) {
            myInfo[cnt] = i;
            combiR(cnt + 1, num - i);
        }
    }

    private int calculateScore() {
        int scoreA = 0;
        int scoreB = 0;

        for (int i = 1; i < 11; ++i) {
            if (info[i] == 0 && myInfo[i] == 0) continue;
            if (info[i] >= myInfo[i]) scoreA += i;
            else scoreB += i;
        }

        return scoreB - scoreA;
    }
}
