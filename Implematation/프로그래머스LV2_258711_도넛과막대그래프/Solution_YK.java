package Implematation.프로그래머스LV2_258711_도넛과막대그래프;

import java.util.ArrayList;
import java.util.List;

public class Solution_YK {
    static int[] answer = new int[4];
    static int N, M = 1_000_001;
    static List<Integer>[] list;
    static int[] in, out;
    static int start;
    static boolean[] visited;

    public int[] solution(int[][] edges) {
        N = edges.length;

        list = new ArrayList[M];
        for (int i = 0; i < M; ++i) {
            list[i] = new ArrayList<>();
        }
        in = new int[M];
        out = new int[M];
        visited = new boolean[M];

        int max = 0;
        for (int i = 0; i < N; ++i) {
            list[edges[i][0]].add(edges[i][1]);
            ++out[edges[i][0]];
            ++in[edges[i][1]];

            max = Math.max(max, Math.max(edges[i][0], edges[i][1]));
        }

        for (int i = 0; i <= max; ++i) {
            if (out[i] > 1 && in[i] == 0) {
                start = i;
            }
        }

        // 시작 정점과 연결된 간선 삭제
        answer[0] = start;
        int len = list[start].size();
        out[start] = 0;
        for (int k : list[start]) {
            --in[k];
        }

        // 2. 막대 그래프 : 들어오는 정점만 한 개 있는 노드 개수 (막대 그래프의 끝)
        // 3. 8자 그래프 : 들어오는 정점이 2개고, 나가는 정점도 2개인 노드 개수 (8자 그래프의 중앙 노드)
        // 1. 도넛 그래프 : 나머지
        for (int i = 1; i <= max; ++i) {
            if (out[i] == 0) {
                answer[2] += 1;
            } else if (in[i] == 2 && out[i] == 2) {
                answer[3] += 1;
            }
        }

        answer[2] -= 1;
        answer[1] = len - (answer[2] + answer[3]);
        return answer;
    }
}
