package Implematation.p1713_후보추천하기;

import java.io.*;
import java.util.*;

public class p1713_HC {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		Picture[] pictures = new Picture[N];

		int cnt = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0, idx; i < M; ++i) {
			int num = Integer.parseInt(st.nextToken());
			if ((idx = find(pictures, cnt, num)) != -1) {    // no picture
				++pictures[idx].like;
				continue;
			}

			if (cnt < N) { // new frame
				pictures[cnt++] = new Picture(num, i);
			} else {
				idx = findMin(pictures, cnt);
				pictures[idx] = new Picture(num, i);
			}
		}
		StringBuilder sb = new StringBuilder();
		Arrays.stream(pictures)
			.filter(Objects::nonNull)
			.map(picture -> picture.student)
			.mapToInt(Integer::intValue)
			.sorted()
			.forEach(value -> sb.append(value).append(" "));
		System.out.println(sb.toString());
	    br.close();
	}

	private static int findMin(Picture[] pictures, int n) {
		int minIdx = -1;
		int minLikes = Integer.MAX_VALUE;
		int minTimestamp = Integer.MAX_VALUE;
		for (int i = 0; i < n; ++i) {
			if (minLikes > pictures[i].like) {
				minLikes = pictures[i].like;
				minTimestamp = pictures[i].timestamp;
				minIdx = i;
			} else if (minLikes == pictures[i].like && minTimestamp > pictures[i].timestamp) {
				minTimestamp = pictures[i].timestamp;
				minIdx = i;
			}
		}
		return minIdx;
	}

	private static int find(Picture[] pictures, int n, int student) {
		for (int i = 0; i < n; ++i) {
			if (pictures[i].student == student) {
				return i;
			}
		}
		return -1;
	}

	private static class Picture {
		int student, like, timestamp;

		public Picture(int student, int timestamp) {
			this.student = student;
			this.timestamp = timestamp;
			this.like = 0;
		}
	}
}
