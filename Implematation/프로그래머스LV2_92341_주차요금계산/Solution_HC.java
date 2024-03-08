package Implematation.프로그래머스LV2_92341_주차요금계산;

import java.util.*;

class Solution_HC {

	// hh:mm
	private int convertTimeToMinute(String time) {
		String[] splited = time.split(":");
		int hour = Integer.parseInt(splited[0]);
		int minute = Integer.parseInt(splited[1]);
		return hour * 60 + minute;
	}

	public int[] solution(int[] fees, String[] records) {
		int baseTime = fees[0];
		int baseFee = fees[1];
		double unitTime = (double) fees[2];
		int unitFee = fees[3];
		int endTimeOfDay = convertTimeToMinute("23:59");

		StringTokenizer st;
		int[] parkingTime = new int[10000];
		Map<Integer, Integer> parkingCars = new HashMap<>();

		for (String record: records) {
			st = new StringTokenizer(record);
			int time = convertTimeToMinute(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
			String type = st.nextToken();

			if (type.equals("IN")) {
				parkingCars.put(number, time);
			} else {
				int inTime = parkingCars.remove(number);
				parkingTime[number] += time - inTime;
			}
		}
		for (int number: parkingCars.keySet()) {
			parkingTime[number] += endTimeOfDay - parkingCars.get(number);
		}

		int count = 0;
		int[] answer = new int[10000];
		for (int i = 0; i < 10000; ++i) {
			if (parkingTime[i] > 0) {
				int fee = baseFee + (int) Math.ceil((double) Math.max(parkingTime[i] - baseTime, 0) / unitTime) * unitFee;
				answer[count++] = fee;
			}
		}
		return Arrays.copyOfRange(answer, 0, count);
	}
}