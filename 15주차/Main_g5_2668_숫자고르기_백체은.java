package a11월1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_g5_2668_숫자고르기 {

	static int N;
	static int[] arr;
	static List<Integer> ans;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		
		ans = new ArrayList<>();
		visited = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			visited[i] = true;
			dfs(i, i);
			visited[i] = false;
		}
		
		System.out.println(ans.size());
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(ans.get(i));
		}
		
	}

	private static void dfs(int cycle, int start) {
		if(!visited[arr[cycle]]) {
			visited[arr[cycle]] = true;
			dfs(arr[cycle], start);
			visited[arr[cycle]] = false;
		}
		
		if(arr[cycle] == start) {
			ans.add(start);
		}
	}
}
