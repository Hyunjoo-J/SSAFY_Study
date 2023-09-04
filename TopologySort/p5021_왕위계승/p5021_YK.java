package TopologySort.p5021_왕위계승;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

public class p5021_YK {
    static HashMap<String, Double> blood;
    static HashMap<String, String[]> family;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String king = br.readLine();
        blood = new HashMap<>();
        blood.put(king, 1.0);

        family = new HashMap<>();
        String child, mom, dad;
        double p1, p2;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            child = st.nextToken();
            mom = st.nextToken();
            dad = st.nextToken();

            blood.put(child, 0.0);
            family.put(child, new String[] {mom, dad});
        }

        Set<String> keySet = family.keySet();

        for (String key : keySet) {
            mom = family.get(key)[0];
            dad = family.get(key)[1];

            p1 = getInDegree(mom);
            p2 = getInDegree(dad);

            blood.replace(key, (p1 + p2) / 2);
        }

        double b = 0;
        String result = "";
        for (int i = 0; i < M; ++i) {
            String candidate = br.readLine();
            if (!blood.containsKey(candidate)) {
                if (b > 0) continue;
                result = candidate;
                continue;
            }
            if (b < blood.get(candidate)) {
                b = blood.get(candidate);
                result = candidate;
            }
        }

        bw.write(result);
        bw.flush();
        bw.close();
        br.close();
    }

    private static double getInDegree(String c) {
        if (!blood.containsKey(c)) return 0.0;
        if (blood.get(c) != 0.0) return blood.get(c);
        return (getInDegree(family.get(c)[0]) + getInDegree(family.get(c)[1])) / 2;
    }
}
