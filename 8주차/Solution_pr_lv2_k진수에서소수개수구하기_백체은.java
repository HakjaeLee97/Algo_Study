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

		StringTokenizer st = new StringTokenizer(sb.toString(), "0");
		while (st.hasMoreTokens()) {
			long num = Long.parseLong(st.nextToken());
			if (isPrime(num)) {
				answer++;
			}
		}
		return answer;
	}

	// 소수확인
	public boolean isPrime(long l) {

		if (l < 2)
			return false;

		for (int i = 2; i <= Math.sqrt(l); i++) {
			if (l % i == 0) {
				return false;
			}
		}
		return true;
	}
}