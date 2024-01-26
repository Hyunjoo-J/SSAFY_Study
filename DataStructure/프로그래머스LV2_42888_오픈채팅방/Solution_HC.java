package DataStructure.프로그래머스LV2_42888_오픈채팅방;

import java.util.*;

class Solution_HC {

	private static final String ENTER_SUFFIX = "님이 들어왔습니다.";
	private static final String LEAVE_SUFFIX = "님이 나갔습니다.";

	public String[] solution(String[] records) {
		Map<String, String> map = new HashMap<>();
		StringTokenizer st;

		int n = 0;
		for (String record: records) {
			st = new StringTokenizer(record);
			String type = st.nextToken();
			String id = st.nextToken();
			switch (type) {
				case "Leave":
					++n;
					break;
				case "Enter":
					++n;
				case "Change":
					String nickname = st.nextToken();
					map.put(id, nickname);
					break;
			}
		}

		String[] answer = new String[n];
		int i = 0;
		for (String record: records) {
			st = new StringTokenizer(record);
			String type = st.nextToken();
			if (type.equals("Change")) {
				continue;
			}
			String id = st.nextToken();
			String nickname = map.get(id);
			answer[i++] = nickname + (type.equals("Enter") ? ENTER_SUFFIX : LEAVE_SUFFIX);
		}
		return answer;
	}
}