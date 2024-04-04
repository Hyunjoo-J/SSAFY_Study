package Bruteforce.프로그래머스LV3_60062_외벽점검;

class Solution_HC {

    private static final int INF = 123456789;

    private int n;
    private int weakSize, distSize;
    private int[] weak;
    private int[] dist;

    public int solution(int n, int[] weak, int[] dist) {
        weakSize = weak.length;
        distSize = dist.length;

        this.weak = arrayDoubling(weak, n);
        this.dist = dist;

        int answer = INF;
        do {
            for (int i = 0; i < weakSize; ++i) {
                answer = Math.min(answer, test(i));
            }
        } while (np(this.dist, distSize));
        return answer == INF ? -1 : answer;
    }

    private int test(int startIndex) {
        int dIndex = 0;
        int curr = -1;
        for (int i = startIndex; i < startIndex + weakSize; ++i) {
            if (weak[i] <= curr) {
                continue;
            } else {
                if (dIndex == distSize) {
                    return INF;
                }
                curr = weak[i] + dist[dIndex++];
            }
        }
        return dIndex;
    }

    private static boolean np(int[] perm, int n) {
        int i = n - 1;
        while (i > 0 && perm[i - 1] >= perm[i])
            --i;

        if (i == 0)
            return false;

        int j = n - 1;
        while (perm[i - 1] >= perm[j])
            --j;

        swap(perm, i - 1, j);

        int k = n - 1;
        while (i < k)
            swap(perm, i++, k--);

        return true;
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private static int[] arrayDoubling(int[] array, int base) {
        int length = array.length;
        int[] newArr = new int[length << 1];
        System.arraycopy(array, 0, newArr, 0, length);
        for (int i = 0; i < length; ++i) {
            newArr[length + i] = base + newArr[i];
        }
        return newArr;
    }
}