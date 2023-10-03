package DataStructure.p11286_절댓값힙;

import java.io.*;
import java.util.*;
public class p11286_YK {
    static class AbsoluteHeap {
        int[] heap;
        int size = 0;

        public int pop() {
            if (size == 0) return 0;
            int ans = heap[1];
            heap[1] = heap[size--];
            int tmp = 1;
            while (tmp <= size << 1 && (
                    (Math.abs(heap[tmp]) > Math.abs(heap[tmp << 1]) ||
                            (Math.abs(heap[tmp]) == Math.abs(heap[tmp << 1]) && heap[tmp] > heap[tmp << 1])) ||
                    (Math.abs(heap[tmp]) > Math.abs(heap[(tmp << 1) + 1]) ||
                             (Math.abs(heap[tmp]) == Math.abs(heap[(tmp << 1) + 1]) && heap[tmp] > heap[(tmp << 1) + 1]))
                    )
            ) {
                if (Math.abs(heap[(tmp << 1) + 1]) > Math.abs(heap[tmp << 1]) ||
                        (Math.abs(heap[(tmp << 1) + 1]) == Math.abs(heap[tmp << 1]) && heap[(tmp << 1) + 1] > heap[tmp << 1])) {
                    if (tmp << 1 > size) break;
                    swap(tmp << 1, tmp);
                    tmp = tmp << 1;
                }
                else {
                    if ((tmp << 1) + 1 > size) break;
                    swap((tmp << 1) + 1, tmp);
                    tmp = (tmp << 1) + 1;
                }
            }
            return ans;
        }

        public void insert(int k) {
            heap[++size] = k;
            int tmp = size;
            while (tmp > 1
                    && (Math.abs(heap[tmp >> 1]) > Math.abs(heap[tmp]) ||
                        (Math.abs(heap[tmp >> 1]) == Math.abs(heap[tmp]) && heap[tmp >> 1] > heap[tmp]))) {
                swap(tmp >> 1, tmp);
                tmp >>= 1;
            }
        }

        private void swap(int i, int j) {
            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int S = 1;
        while (S < N) S <<= 1;
        AbsoluteHeap aHeap = new AbsoluteHeap();
        aHeap.heap = new int[S << 1];
        Arrays.fill(aHeap.heap, Integer.MAX_VALUE);

        int k;
        while (N-- > 0) {
            k = Integer.parseInt(br.readLine());
            if (k == 0) bw.write(aHeap.pop() + "\n");
            else aHeap.insert(k);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
