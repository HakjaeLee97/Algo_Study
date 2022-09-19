package a0905;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_g5_13549_숨바꼭질3 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		br.close();

		if (N == K) {
			System.out.print(0);
			return;
		}

		boolean[] visited = new boolean[100_001];
		int t = 0;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { N, t });
		out: while (!q.isEmpty()) {
			int i = q.peek()[0];
			t = q.poll()[1];
			int j = i;
			// 가능한 순간이동 전부
			while (0 < j && j <= 100000) {
				if (j == K) {
					System.out.print(t);
					break out;
				}
				if (!visited[j]) {
					visited[j] = true;
					q.offer(new int[] { j, t });
				}
				j *= 2;
			}
			// 걷기
			if (i - 1 == K || i + 1 == K) {
				System.out.print(t + 1);
				break out;
			}
			// x-1
			if (0 <= i - 1 && i - 1 <= 100_000 && !visited[i - 1]) {
				visited[i - 1] = true;
				q.offer(new int[] { i - 1, t + 1 });
			}
			// x+1
			if (i + 1 <= 100_000 && !visited[i + 1]) {
				visited[i + 1] = true;
				q.offer(new int[] { i + 1, t + 1 });
			}
		}
	}
}
