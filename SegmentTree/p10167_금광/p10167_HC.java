// https://www.acmicpc.net/problem/10167

package SegmentTree.p10167_금광;

import java.io.*;
import java.util.*;

public class p10167_HC {

    private static class Gold {
        int x, y, w;

        public Gold(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }

    private static class Node {
        long leftSum;
        long rightSum;
        long maxSum;
        long totalSum;
    }

    private static final int S = 4096;
    private static Node[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Gold[] golds = new Gold[N];
        tree = new Node[S << 1];

        for (int i = 0, S2 = S << 1; i < S2; ++i)
            tree[i] = new Node();

        // input
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            golds[i] = new Gold(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        // 좌표 압축
        coordCompression(golds);

        // y좌표 정렬
        Arrays.sort(golds, (o1, o2) -> o1.y > o2.y ? 1 : -1);

        int maxY = Arrays.stream(golds)
                .map(gold -> gold.y)
                .mapToInt(y -> y)
                .max()
                .orElse(0) + 1;

        Gold gold;
        int gIdx;
        long answer = 0;

        for (int y1 = 0; y1 < maxY; ++y1) {
            treeInit();
            for (int y2 = y1; y2 < maxY; ++y2) {
                gIdx = bisectLeft(golds, y2);

                while (gIdx < N && (gold = golds[gIdx]).y == y2) {
                    update(gold.x, gold.w);
                    ++gIdx;
                }
                answer = Math.max(answer, tree[1].maxSum);
            }
        }
        System.out.println(answer);
        br.close();
    }

    private static int bisectLeft(Gold[] golds, int find) {
        int left = 0;
        int right = golds.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (golds[mid].y < find) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right + 1;
    }

    private static void update(int target, int diff) {
        int idx = S + target;
        tree[idx].leftSum += diff;
        tree[idx].rightSum += diff;
        tree[idx].maxSum += diff;
        tree[idx].totalSum += diff;

        idx >>= 1;
        while (idx > 0) {
            merge(tree[idx << 1], tree[(idx << 1) + 1], tree[idx]);
            idx >>= 1;
        }
    }

    private static void treeInit() {
        for (Node segNode: tree) {
            segNode.leftSum = 0;
            segNode.rightSum = 0;
            segNode.maxSum = 0;
            segNode.totalSum = 0;
        }
    }

    private static void merge(Node leftNode, Node rightNode, Node target) {
        target.leftSum = Math.max(leftNode.leftSum, leftNode.totalSum + rightNode.leftSum);
        target.rightSum = Math.max(rightNode.rightSum, leftNode.rightSum + rightNode.totalSum);
        target.maxSum = Math.max(Math.max(leftNode.maxSum, rightNode.maxSum), leftNode.rightSum + rightNode.leftSum);
        target.totalSum = leftNode.totalSum + rightNode.totalSum;
    }

    private static void coordCompression(Gold[] golds) {
        Map<Integer, Integer> xccMap = new HashMap<>();
        Map<Integer, Integer> yccMap = new HashMap<>();

        for (Gold gold: golds) {
            xccMap.put(gold.x, 0);
            yccMap.put(gold.y, 0);
        }

        int idx = 0;
        for (int num: xccMap.keySet().stream()
                .mapToInt(Integer::intValue)
                .sorted().toArray())
            xccMap.put(num, idx++);

        idx = 0;
        for (int num: yccMap.keySet().stream()
                .mapToInt(Integer::intValue)
                .sorted().toArray())
            yccMap.put(num, idx++);

        for (Gold gold: golds) {
            gold.x = xccMap.get(gold.x);
            gold.y = yccMap.get(gold.y);
        }
    }
}
