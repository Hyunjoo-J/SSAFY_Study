package Greedy.p1700_멀티탭스케줄링;

import java.io.*;
import java.util.*;

public class p1700_YK {

    static class Plug {
        int name, next;
        boolean isPlugged;

        public Plug(int name, int next, boolean isPlugged) {
            this.name = name;
            this.next = next;
            this.isPlugged = isPlugged;
        }
    }

    static Plug[] plugs;
    static int[] multiPlug;
    static ArrayList<Integer>[] indexes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        plugs = new Plug[K];
        int[] orders = new int[K];

        indexes = new ArrayList[K];
        for (int i = 0; i < K; ++i) {
            indexes[i] = new ArrayList<>();
            plugs[i] = new Plug(i, 0, false);
        }

        for (int i = 0; i < K; ++i) {
            int plug = Integer.parseInt(st.nextToken()) - 1;
            orders[i] = plug;
            indexes[plug].add(i);
        }

        int pluggedCnt = 0;
        multiPlug = new int[N];
        int result = 0;

        for (int i = 0; i < K; ++i) {
            int now = orders[i];

            // 이미 꽂혀있는 경우
            if (plugs[now].isPlugged) {
                if (++plugs[now].next >= indexes[now].size())
                    plugs[now].next = -1;
                continue;
            }

            if (pluggedCnt < N) { // 비어있는 콘센트가 있다면
                plugin(now, pluggedCnt++);
            } else { // 비어있는 콘센트가 없다면
                ++result;

                // 뺄 콘센트 정하기 (뒤에 제일 늦게 나오는 친구 뽑을 예정)
                int maxOrder = 0; // 뒤에 언제 나오는지
                int maxIndex = -1; // 해당 플러그가 멀티탭 어디에 꽂혀있는지

                for (int j = 0; j < N; ++j) {
                    int p = multiPlug[j];

                    // 뒤에 아예 안나온다. 바로 빼고 꽂기
                    if (plugs[p].next == -1) {
                        maxIndex = j;
                        break;
                    }

                    if (indexes[p].get(plugs[p].next) > maxOrder) {
                        maxOrder = indexes[p].get(plugs[p].next);
                        maxIndex = j;
                    }
                }

                plugs[multiPlug[maxIndex]].isPlugged = false; // 꽂혀있던 애 - 꽂았음 false
                plugin(now, maxIndex);

            }
        }

        System.out.println(result);
        br.close();
    }

    private static void plugin(int k, int idx) {
        multiPlug[idx] = plugs[k].name; // 해당하는 멀티탭 자리에 꽂기
        plugs[k].isPlugged = true; // 꽂았음 true
        if (++plugs[k].next >= indexes[k].size()) // next 증가, 만약 다음에 또 안나오면 -1로
            plugs[k].next = -1;
    }

}
