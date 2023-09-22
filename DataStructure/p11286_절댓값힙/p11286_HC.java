package DataStructure.p11286_절댓값힙;

import java.io.*;

public class p11286_HC {

	private static class Heap {
		final int[] heap;
		int heapSize;

		public Heap(int capacity) {
			this.heap = new int[capacity];
			this.heapSize = 0;
		}

		void insert(int x) {
			heap[++heapSize] = x;
			upHeap(heapSize);
		}

		int remove() {
			if (heapSize == 0)
				return 0;
			int item = heap[1];
			heap[1] = heap[heapSize--];
			downHeap(1);
			return item;
		}

		private void upHeap(int i) {
			int key = heap[i];
			while (i > 1 && compare(key, heap[i >> 1]) < 0) {
				heap[i] = heap[i >> 1];
				i >>= 1;
			}
			heap[i] = key;
		}

		private void downHeap(int i) {
			int temp = heap[i];
			int parent = i, child = i << 1;

			while (child <= heapSize) {
				if ((child < heapSize) && compare(heap[child], heap[child + 1]) > 0)
					++child;
				if (compare(temp, heap[child]) <= 0)
					break;
				heap[parent] = heap[child];
				parent = child;
				child <<= 1;
			}
			heap[parent] = temp;
		}

		private int compare(int i1, int i2) {
			int a = i1, b = i2;
			if (a < 0) a = ~a + 1;
			if (b < 0) b = ~b + 1;
			if (a == b)
				return Integer.compare(i1, i2);
			return Integer.compare(a, b);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int x;
		Heap heap = new Heap(100001);
		while (N-- > 0) {
			x = Integer.parseInt(br.readLine());
			if (x == 0) {
				bw.write(heap.remove() + "\n");
			} else {
				heap.insert(x);
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
