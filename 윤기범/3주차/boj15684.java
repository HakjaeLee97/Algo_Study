import java.util.*;
import java.awt.Label;
import java.io.*;

public class Main {
	public static int N, M, H;
	public static int[][] graph;
	public static boolean flag = false;
	public static int ans;
	
	public static boolean check() {
		for(int i = 1; i <= N; i++) {
			int nx = i;
	        int ny = 1;
	 
	        while (ny <= H) {
	        	if (graph[ny][nx] == 1) nx++; 
	            else if (graph[ny][nx] == 2) nx--; 
	            	ny++; 
	        }
	 
	        if (nx != i) 
	        	return false; 
	    }
	    return true;
	}
	
	public static void dfs(int cnt, int size) {
		if(flag)
			return;
		if(cnt == size) { // 기저조건
			if(check())
				flag = true;
			return;
		}
		
		for(int i = 1; i <= H; i++) {
			for(int j = 1; j < N; j++) {
				// 사다리 연속 제외
				if(graph[i][j] != 0)
					continue;
				if(graph[i][j+1] != 0)
					continue;
				graph[i][j] = 1;
				graph[i][j+1] = 2;

				dfs(cnt + 1, size);
				
				graph[i][j] = 0;
				graph[i][j+1] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		graph = new int[H+1][N+1]; // 계산 편하게 하기 위함.
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 1; // 우
			graph[a][b+1] = 2; // 좌
		}
		
		for(int i = 0; i <= 3; i++) {
			ans = i;
			dfs(0, i);
			if(flag) 
				break;
		}
		
		if (flag == false) 
			System.out.println(-1);
		else
			System.out.println(ans);
	}
}
