package Implematation.프로그래머스LV2_92341_주차요금계산;

import java.util.*;

class Solution_MJ {
    public int[] solution(int[] fees, String[] records) {
        String[][] rec = new String[records.length][3];
        Map<String, Integer> totalTime = new HashMap<>();
        Set<String> car = new HashSet<>();
        int[] cnt = new int[10000];

        for(int i=0; i<records.length; ++i){
            String s = records[i];
            StringTokenizer st = new StringTokenizer(s);
            String time = st.nextToken();
            String carNum = st.nextToken();
            String status = st.nextToken();
            car.add(carNum);

            if(cnt[Integer.parseInt(carNum)]==0){
                totalTime.put(carNum, 0);
            }
            cnt[Integer.parseInt(carNum)]++;

            String[] t = time.split(":");
            int tmp = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
            if(status.equals("IN")){
                totalTime.put(carNum, totalTime.get(carNum) - tmp);
            } else{
                totalTime.put(carNum, totalTime.get(carNum) + tmp);
            }
        }

        ArrayList<String> list = new ArrayList<>(car);
        Collections.sort(list);
        // System.out.println(list.get(0));

        int[] result = new int[list.size()];

        for(int i=0; i<list.size(); ++i){
            String carNum = list.get(i);
            int total = totalTime.get(carNum);
            if(cnt[Integer.parseInt(carNum)]%2!=0){
                total += (23 * 60) + 59;
            }

            System.out.println("total: " + total);
            if(total<=fees[0]){
                result[i] = fees[1];
            } else {
                int extra = (int)Math. ceil((total - fees[0]) * 1.0 / fees[2]);
                result[i] = fees[1] + (extra * fees[3]);
            }
        }

        return result;
    }
}