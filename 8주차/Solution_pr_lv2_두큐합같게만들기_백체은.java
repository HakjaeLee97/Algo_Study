import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
		int len = queue1.length;

		long sum1 = 0;
		ArrayDeque<Integer> q1 = new ArrayDeque<>();
		for (int i = 0; i < len; i++) {
			sum1 += queue1[i];
			q1.offer(queue1[i]);
		}

		long sum2 = 0;
		ArrayDeque<Integer> q2 = new ArrayDeque<>();
		for (int i = 0; i < len; i++) {
			sum2 += queue2[i];
			q2.offer(queue2[i]);
		}

		long goal = (sum1 + sum2) / 2;
		if ((sum1 + sum2) % 2 == 1) {
			return -1;
		}

		while (answer < 3 * len) {
			if (sum1 == goal) {
				return answer;
			}
			if (sum1 > sum2) {
				int n = q1.poll();
				q2.offer(n);
				sum1 -= n;
				sum2 += n;
			} else if (sum1 < sum2) {
				int n = q2.poll();
				q1.offer(n);
				sum1 += n;
				sum2 -= n;
			}
			answer++;
		}
		return -1;
	}
}