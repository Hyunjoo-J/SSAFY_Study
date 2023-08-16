import java.io.*;
import java.util.*;

public class p2243_HJ {
    static int n, s;
    static int size = 1000000;
    static long ans;
    static long[] stree; //각각의 맛의 사탕 수
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        s = 1;
        while(s < size){
            s *= 2;
        }
        stree = new long[2*s]; //s는 가장 밑의 dep의 시작 번호
        int a,b,c;
        for(int i = 0; i < n; ++i){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(a == 1){
                int idx = findidx(1, b) - s + 1;//리프 위치를 리턴 하기에 -s + 1해야함
                update(idx + s -1, -1);
                sb.append(idx + "\n");
            }else{
                c = Integer.parseInt(st.nextToken());
                update(b + s - 1, c);
            }
        }
        System.out.print(sb.toString());

    }
    private static int findidx(int start, long num){
        int res = start;
        if(num == 0)
            return res;
        if(start >= s) //가장 마지막 dep에 들어옴
            return res;
        int left = start*2;//다음 dep 왼쪽
        int right = left + 1; //다음 dep의 오른쪽
        if(stree[left] >= num){
            res = findidx(left, num);
        } else{
            res = findidx(right, num - stree[left]);
        }
        return res;
    }
    private static void update(int node, int cnt){
        if(node == 0)
            return;
        stree[node] += cnt;
        update(node/2, cnt);

    }
}
