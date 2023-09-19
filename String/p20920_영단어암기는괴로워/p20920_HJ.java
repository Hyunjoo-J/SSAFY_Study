package String.p20920_영단어암기는괴로워;
import java.util.*;
import java.io.*;

public class p20920_HJ {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, Integer> word = new HashMap<String, Integer>();
        for(int i = 0; i < N; ++i) {
            String line = br.readLine();
            if(line.length() < M) //M보다 작은건 바로 외울 수 있음
                continue;
            Integer cnt = word.getOrDefault(line, 0);
            word.put(line, cnt + 1); //<단어, 빈도수>
        }
        List<String> words = new ArrayList<>(word.keySet());
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //자주 등장하는 순서대로
                if(Integer.compare(word.get(o1), word.get(o2)) != 0) {
                    return Integer.compare(word.get(o2), word.get(o1));
                }//길이 순
                if(o1.length()!= o2.length()) {
                    return o2.length() - o1.length();
                }
                //사전 순
                return o1.compareTo(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(String str : words)
            sb.append(str+"\n");
        System.out.print(sb.toString());
    }

}