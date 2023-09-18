package DataStructure.p1927_최소힙;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class p1927_YK {

    static int[] heap;
    static int size = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int S = 1;
        while (S <= N) S <<= 1;
        heap = new int[S];
        Arrays.fill(heap, Integer.MAX_VALUE);

        int K;
        for (int i = 0; i < N; ++i) {
            K = Integer.parseInt(br.readLine());
            if (K == 0) {
                bw.write(pop() + "\n");
            }
            else insert(K);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void insert(int k) {
        heap[++size] = k;
        int i = size;
        while (i > 1 && heap[i / 2] > heap[i]) {
            swap(i / 2 , i);
            i = i / 2;
        }
    }

    private static int pop() {
        int result = heap[1];
        if (size == 0) return 0;

        heap[1] = heap[size];
        heap[size] = Integer.MAX_VALUE;
        size--;
        int i = 1;
        while (i < size && (heap[i] > heap[i * 2] || heap[i] > heap[i * 2 + 1])) {
            if (heap[i * 2] < heap[i * 2 + 1]) {
                swap(i, i * 2);
                i = i * 2;
            }
            else {
                swap(i, i * 2 + 1);
                i = i * 2 + 1;
            }
        }
        return result;
    }

    private static void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
}
