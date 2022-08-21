package August_third;

import java.io.*;
import java.util.*;

public class bj_2644_촌수계산_Silver_2 {
	static int node,edge,mom,son;

	static int[][]rel;
	static boolean[]visited;
	static int answer;
	static void calc() {
		
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {mom,0});
		
		while(!q.isEmpty()) {
			
			int []c = q.poll();
			int n = c[0];
			int cnt = c[1];
			if(n==son) {
				answer = cnt;
				break;
			}
			
			for(int i=1 ; i< node+1 ; i++) {
				if(rel[n][i]==1 && !visited[i]) {
					visited[i] = true;
					q.offer(new int[] {i,cnt+1});
					
				}
			}
			
		}
		
	}
	
	static void output() {
		System.out.println(answer>0 ? answer : -1);
	}
	
	static void input() throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		node = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		mom = Integer.parseInt(st.nextToken());
		son = Integer.parseInt(st.nextToken());
		rel = new int[node+1][node+1];
		edge = Integer.parseInt(br.readLine());
		
		for(int i=0;i<edge;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			rel[from][to] = rel[to][from] = 1;
		}
		
		visited = new boolean[node+1];
		
	}
	
	public static void main(String[] args) throws Exception {
		
		
		input();
		calc();
		output();
		
	}

}
