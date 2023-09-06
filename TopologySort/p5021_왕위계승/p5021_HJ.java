import java.io.*;
import java.util.*;

public class p5021_HJ {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String name = br.readLine();
        Map<String, Integer> map = new HashMap<>();
        double[] family = new double[150];
        int[] degree = new int[150];
        family[0] = 1.0; //왕
        int idx = 0;
        map.put(name, idx++);
        ArrayList<Integer>[] list = new ArrayList[150];
        for(int i = 0; i < 150; ++i)
            list[i] = new ArrayList<>();
        String child, pa1, pa2;
        for(int i = 0; i < N; ++i){
            st = new StringTokenizer(br.readLine());
            child = st.nextToken();
            pa1 = st.nextToken();
            pa2 = st.nextToken();
            if(!map.containsKey(child))
                map.put(child, idx++);
            if(!map.containsKey(pa1))
                map.put(pa1, idx++);
            if(!map.containsKey(pa2))
                map.put(pa2, idx++);
            list[map.get(pa1)].add(map.get(child));
            list[map.get(pa2)].add(map.get(child));
            degree[map.get(child)] += 2;
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i = 0; i < 150; ++i) {
        	if(degree[i] == 0)
        		q.offer(i);
        }
        
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	for(int tmp : list[cur]) {
        		family[tmp] += family[cur]/2;
        		if(--degree[tmp] == 0)
        			q.offer(tmp);
        		
        	}
        }
        String candi;
        String ans = name;
        double tmp = 0;
        for(int i = 0; i < M; ++i) {
        	candi = br.readLine();
        	if(!map.containsKey(candi))
        		continue;
        	if(tmp < family[map.get(candi)]) {
        		ans = candi;
        		tmp = family[map.get(candi)];
        		
        	}
        }
        bw.write(ans);
        bw.flush();
    }
}
