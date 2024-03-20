package Implematation.프로그래머스LV2_92341_주차요금계산;

import java.util.*;

public class Solution_YK {

    int baseTime, baseFee, unitTime, unitFee;

    class Fee implements Comparable<Fee>{
        String car;
        int fee;

        @Override
        public int compareTo(Fee o) {
            return Integer.compare(Integer.parseInt(this.car), Integer.parseInt(o.car));
        }

        public Fee(String car, int fee) {
            this.car = car;
            this.fee = fee;
        }
    }

    public int[] solution(int[] fees, String[] records) {
        baseTime = fees[0];
        baseFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];
        int closingTime = timeToMinutes("23:59");
        StringTokenizer st;

        Queue<Fee> pq = new PriorityQueue<>();
        Map<String, Integer> inTimeMap = new HashMap<>();
        Map<String, Integer> sumMap = new HashMap<>();

        for (String record : records) {
            st = new StringTokenizer(record);
            int time = timeToMinutes(st.nextToken());
            String car = st.nextToken();
            String action = st.nextToken();

            if (action.equals("IN")) {
                inTimeMap.put(car, time);
            } else {
                int inTime = inTimeMap.remove(car);
                sumMap.put(car, sumMap.getOrDefault(car, 0) + (time - inTime));
            }
        }

        Set<String> keys = inTimeMap.keySet();
        for (String car : keys) {
            int inTime = inTimeMap.get(car);
            sumMap.put(car, sumMap.getOrDefault(car, 0) + (closingTime - inTime));
        }

        keys = sumMap.keySet();
        for (String car : keys) {
            int sum = sumMap.get(car);
            pq.add(new Fee(car, calculateFee(sum)));
        }

        int[] answer = new int[pq.size()];
        int i = 0;
        while (!pq.isEmpty()) {
            answer[i++] = pq.poll().fee;
        }
        return answer;
    }

    private int calculateFee(int time) {
        if (time <= baseTime) return baseFee;
        time -= baseTime;
        if (time % unitTime != 0) time += unitTime;
        return baseFee + (time / unitTime) * unitFee;
    }

    private int timeToMinutes(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(3, 5));

        return hour * 60 + minute;
    }
}
