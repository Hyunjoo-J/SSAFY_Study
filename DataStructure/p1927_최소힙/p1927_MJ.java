package DataStructure.p1927_최소힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;

public class p1927_MJ {

    static class minHeap {
        private ArrayList<Integer> heap;

        // 최소힙 생성자
        public minHeap() {
            heap = new ArrayList<Integer>();
            heap.add(0); // 0번째 인덱스는 사용 안함
        }

        public int size(){
            return heap.size();
        }

        public void insert(int val) {
            heap.add(val);
            int p = heap.size() - 1; // p는 새로 삽입하는 노드의 인덱스 정보
            while (p > 1 && heap.get(p / 2) > heap.get(p)) {
                int tmp = heap.get(p / 2); // 부모 노드의 값
                heap.set(p / 2, val);
                heap.set(p, tmp);

                p /= 2; // 새로 삽입한 노드가 한 레벨 상승했으니 인덱스 부모 노드 인덱스 값으로 변경
            }
        }

        // 삭제
        public int delete() {
            // 힙이 비어있으면 0 리턴
            if (heap.size() - 1 < 1) {
                return 0;
            }

            // 삭제할 노드, 루트 노드
            int deleteItem = heap.get(1);

            heap.set(1, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);

            int pos = 1;    // 루트에 새로 삽입한 노드의 인덱스 정보

            // pos*2는 왼쪽자식의 인덱스 값, 자식의 인덱스 값이 힙의 사이즈 값보다 크다는 것은 삽입할 위치가 없다는 뜻
            while ((pos * 2) < heap.size()) {
                int min = heap.get(pos * 2); // 왼쪽 자식의 값
                int minPos = pos * 2; // 왼쪽 자식의 인덱스 값

                // 오른쪽 자식의 인덱스가 사이즈보다 작고 왼쪽보다 더 작을 때 오른쪽 자식을 부모와 바꿔줄 자식으로 지정
                if ((pos * 2 + 1) < heap.size() && min > heap.get(pos * 2 + 1)) {
                    min = heap.get(pos * 2 + 1);
                    minPos = pos * 2 + 1;
                }

                // 부모가 더 작으면 그만
                if (min > heap.get(pos)) {
                    break;
                }

                int tmp = heap.get(pos);
                heap.set(pos, heap.get(minPos));
                heap.set(minPos, tmp);
                pos = minPos;
            }

            return deleteItem;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        minHeap heap = new minHeap();

        for(int i=0; i<N; i++){
            int tmp = Integer.parseInt(br.readLine());
            if(tmp==0){
                if(heap.size()==1){
                    System.out.println(0);
                }
                else{
                    System.out.println(heap.delete());
                }
            }
            else{
                heap.insert(tmp);
            }
        }

        br.close();
    }
}
