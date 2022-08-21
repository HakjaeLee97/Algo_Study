package a0822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_s2_2644_촌수계산 {

	static int n, a, b, m, ans;
	static List<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		graph = new List[n+1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		visited = new boolean[n+1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}
		br.close();
		ans = -1;
		dfs(a, 0);
		System.out.print(ans);
	}

	static void dfs(int i, int cnt) {
		if(i == b) {
			ans = cnt;
			return;
		}
		visited[i] = true;
		for (int j : graph[i]) {
			if(!visited[j]) {
				dfs(j, cnt+1);
			}
		}
	}
}
