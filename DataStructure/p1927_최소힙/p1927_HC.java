package DataStructure.p1927_최소힙;

import java.io.*;

public class p1927_HC {

	private static int[] heap = new int[100010];
	private static int heapSize = 0;

	private static void heapPush(int x) {
		heap[++heapSize] = x;
		upHeap(heapSize);
	}

	private static void upHeap(int idx) {
		int key = heap[idx];
		while (idx > 0 && (key < heap[idx >> 1])) {
			heap[idx] = heap[idx >> 1];
			idx >>= 1;
		}
		heap[idx] = key;
	}

	private static int heapPop() {
		if (heapSize == 0)
			return 0;
		int item = heap[1];
		heap[1] = heap[heapSize--];
		downHeap(1);
		return item;
	}

	private static void downHeap(int i) {
		int key = heap[i];
		int parent = i;
		int child = i << 1;
		while (child <= heapSize) {
			if (child < heapSize && (heap[child] > heap[child + 1]))
				++child;
			if (key <= heap[child])
				break;
			heap[parent] = heap[child];
			parent = child;
			child <<= 1;
		}
		heap[parent] = key;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		int x;
		for (int i = 0; i < N; ++i) {
			x = Integer.parseInt(br.readLine());
			if (x == 0) {
				sb.append(heapPop()).append("\n");
			} else {
				heapPush(x);
			}
		}

		System.out.println(sb.toString());
		br.close();
	}
}
