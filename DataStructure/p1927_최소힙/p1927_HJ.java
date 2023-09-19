package DataStructure.p1927_최소힙;
import java.io.*;
public class p1927_HJ {
	static int[] heap = new int[100001];
	static int size = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i){
			int command = Integer.parseInt(br.readLine());
			if(command == 0){
				if(size == 0)
					sb.append("0\n");
				else{
					sb.append(hpop()+"\n");
				}
			}else{
				hpush(command);
			}
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
	}

	private static void hpush(int cur){
		heap[++size] = cur;
		upHeap(size);
	}

	private static void upHeap(int s){
		int tmp = heap[s];
		while(s > 0 && (tmp < heap[s >> 1])){
			heap[s] = heap[s >> 1];
			s >>= 1;
		}
		heap[s] = tmp;

	}
	private static int hpop(){
		int tmp = heap[1];
		heap[1] = heap[size--];
		downHeap(1);
		return tmp;
	}

	private static void downHeap(int cur){ //루트를 뽑았기에 다시 정렬
		//heap[cur]이 현재 없어짐, 그리고 그 자리를 가장 끝에 있던 노드가 차지함, pop한 값 다음으로 작은 값을 찾아야함
		int tmp = heap[cur];
		int parent = cur;
		int child = cur <<= 1;
		while(child <= size) { //현재 노드 수
			if (child < size && heap[child] > heap[child + 1])
				++child;
			if(tmp <= heap[child])
				break;
			heap[parent] = heap[child];
			parent = child;
			child <<= 1;
		}
		heap[parent] = tmp;

	}
}