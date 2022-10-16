package a0905;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_g3_1238_파티 {

	static class Node {
		int to, time;

		public Node(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}

	static int N, M, X;
	static List<ArrayList<Node>> g;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		g = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i <= N; i++) {
			g.add(new ArrayList<Node>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			g.get(from).add(new Node(to, time));
		}
		br.close();

		int D[] = new int[N + 1];

		at: for (int i = 1; i <= N; i++) { // 자신으로부터 출발하여 X로 도달하기
			int start = i;
			int end = X;
			boolean[] visited = new boolean[N + 1];
			int[] D1 = new int[N + 1];
			Arrays.fill(D1, 987654321);
			D1[start] = 0;

			for (int j = 1; j <= N; j++) { // 노드 수만큼 반복
				int min = 987654321;
				int minVertex = -1;
				for (int k = 1; k <= N; k++) {
					if (!visited[k] && min > D1[k]) {
						min = D1[k];
						minVertex = k;
					}
				}
				visited[minVertex] = true;
				if (minVertex == end) {
					D[i] += D1[end];
					continue at;
				}

				for (int k = 0; k < g.get(minVertex).size(); k++) {
					Node next = g.get(minVertex).get(k);
					if (!visited[next.to] && D1[next.to] > D1[minVertex] + next.time) {
						D1[next.to] = D1[minVertex] + next.time;
					}
				}
			}
		}

		at: for (int i = 1; i <= N; i++) {
			int start = X;
			int end = i;
			int D2[] = new int[N + 1];
			boolean[] visited = new boolean[N + 1];
			Arrays.fill(D2, 987654321);
			D2[start] = 0;

			for (int j = 1; j <= N; j++) {
				int min = 987654321;
				int minVertex = -1;
				for (int k = 1; k <= N; k++) {
					if (!visited[k] && min > D2[k]) {
						min = D2[k];
						minVertex = k;
					}
				}
				visited[minVertex] = true;
				if (minVertex == end) {
					D[i] += D2[end];
					continue at;
				}

				for (int k = 0; k < g.get(minVertex).size(); k++) {
					Node next = g.get(minVertex).get(k);
					if (!visited[next.to] && D2[next.to] > D2[minVertex] + next.time) {
						D2[next.to] = D2[minVertex] + next.time;
					}
				}
			}
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			if (max < D[i]) {
				max = D[i];
			}
		}
		System.out.print(max);
	}
}
