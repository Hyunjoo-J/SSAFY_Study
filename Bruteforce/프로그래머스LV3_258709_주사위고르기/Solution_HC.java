package Bruteforce.프로그래머스LV3_258709_주사위고르기;

import java.util.*;

class Solution_HC {

    interface Operation {
        void end(int visit);
    }

    private int n, n2;
    private int[][] gDice;
    private int[][] winCount = new int[1 << 10][501];
    private int[] score = new int[501];
    private int[] score2 = new int[501];
    private int fullVisit;

    private int maxWinCount;
    private int[] answer = new int[5];

    public int[] solution(int[][] dice) {
        n = dice.length;
        n2 = n >> 1;
        fullVisit = (1 << n) - 1;
        gDice = dice;

        // find all cases
        dfs(0, 0, 0, visit -> {
            for (int i = 0; i < 500; ++i) {
                winCount[visit][i + 1] = score[i];
            }
            for (int i = 1; i < 501; ++i) {
                winCount[visit][i] += winCount[visit][i - 1];
            }
        });

        // find answer
        dfs(0, 0, 0, visit -> {
            int other = (~visit) & fullVisit;
            int res = 0;
            for (int i = 1; i < 501; ++i) {
                res += winCount[other][i] * score[i];
            }

            if (maxWinCount < res) {
                maxWinCount = res;
                for (int i = 0, j = 0; i < n; ++i) {
                    if ((visit & (1 << i)) > 0)
                        answer[j++] = i + 1;
                }
            }
            // System.out.println(res);
        });

        // answer
        return Arrays.copyOfRange(answer, 0, n2);
    }

    private void dfs(int idx, int visit, int depth, Operation operation) {
        if (depth == n2) {
            operation.end(visit);
            return;
        }
        for (int i = idx; i < n; ++i) {
            if ((visit & (1 << i)) > 0)
                continue;
            int[] copied = score.clone();
            advance(depth, gDice[i]);
            dfs(i + 1, visit | (1 << i), depth + 1, operation);
            score = copied;
        }
    }

    // next score
    private void advance(int depth, int[] dice) {
        if (depth == 0) {
            for (int num: dice) {
                ++score[num];
            }
        } else {
            Arrays.fill(score2, 0);
            for (int num: dice) {
                for (int prev = 1; prev <= 400; ++prev) {
                    score2[prev + num] += score[prev];
                }
            }
            int[] temp = score;
            score = score2;
            score2 = temp;
        }
    }
}