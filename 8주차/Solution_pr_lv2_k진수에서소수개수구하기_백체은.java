import java.util.*;

class Solution {
		public int solution(int n, int k) {
		int answer = 0;

		// k진수로 바꾸기 : k로 나눈 나머지들
		StringBuffer sb = new StringBuffer();
		while (n > 0) {
			sb.append(n % k);
			n /= k;
		}
		sb.reverse();

		// 0으로 구분 후 소수 판별
		StringTokenizer st = new StringTokenizer(sb.toString(), "0");
		at : while (st.hasMoreTokens()) {
			long num = Long.parseLong(st.nextToken());
			if(num < 2) continue;
			for (int i = 2; i <= Math.sqrt(num); i++) {
				if(num % i == 0) {
					continue at;
				}
			}
			answer++;
		}
		return answer;
	}
}