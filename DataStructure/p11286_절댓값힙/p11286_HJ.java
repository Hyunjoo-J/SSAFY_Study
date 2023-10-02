package DataStructure.p11286_절댓값힙;
import java.io.*;
public class p11286_HJ {
	static int[] heap = new int[100001];
	static int hsize;
	public static void main(String[] args) throws  IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int x;
		for(int i = 0; i < N; ++i){
			x = Integer.parseInt(br.readLine());
			if(x == 0){
				sb.append(pop() + "\n");
			}else{
				push(x);
			}
		}
		System.out.print(sb);
	}

	private static int pop() {
		if(hsize == 0)
			return 0;
		int tmp = heap[1];
		heap[1] = heap[hsize--];
		downHeap(1);
		return tmp;
	}

	private static void downHeap(int cur) {
		int tmp = heap[cur];
		int parent = cur;
		int child = cur <<= 1;
		while(child <= hsize){
			if(child < hsize && compare(heap[child], heap[child + 1]) > 0)
				++child;
			if(compare(tmp, heap[child]) <= 0)
				break;
			heap[parent] = heap[child];
			parent = child;
			child <<= 1;
		}
		heap[parent] = tmp;
	}

	private static void push(int x){
		heap[++hsize] = x;
		upHeap(hsize);
	}

	private static void upHeap(int size) {
		int tmp = heap[size];
		while(size > 0 && (compare(tmp, heap[size >> 1]) < 0)){
			heap[size] = heap[size >> 1];
			size >>= 1;
		}
		heap[size] = tmp;
	}

	private static int compare(int a, int b){
		int aa = Math.abs(a);
		int bb = Math.abs(b);
		if(aa == bb)
			return Integer.compare(a, b);
		return Integer.compare(aa, bb);
	}
}