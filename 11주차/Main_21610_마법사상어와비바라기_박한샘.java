package October_first;

import java.io.*;
import java.util.*;

public class Main {

	static int[][]move = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=tc;t++) {
			
			int N = Integer.parseInt(br.readLine());
			
			int [][]map = new int[N][N];
			
			boolean[][]visited = new boolean[N][N];
			
			int answer = Integer.MAX_VALUE/2;
			
			for(int i=0;i<N;i++) {
				String str = br.readLine();
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(""+str.charAt(j));
				}
			}
			
			
			PriorityQueue<int[]> q = new PriorityQueue<>();
			
			q.offer(new int[] {0,0,0});
			visited[0][0] = true;
			
			while(!q.isEmpty()) {
				
				int [] c = q.poll();
				int i = c[0];
				int j = c[1];
				int cnt = c[2];
				

				if(i== N-1 && j == N-1) {
					answer = Math.min(answer, cnt);
				}
				

				if(cnt >= answer) continue;

				for(int d=0;d<4;d++) {
					
					int ni = i + move[d][0];
					int nj = j + move[d][1];
					
					
					if(ni<0||nj<0||N-1<ni||N-1<nj) continue;
					
					
					if(!visited[ni][nj]) {
						int totalTime = cnt + map[ni][nj];
						q.offer(new int[] {ni,nj,totalTime});
						visited[ni][nj] = true;
					}
				}
				
			}
			
			System.out.println(answer);
			
			
			
		}
		
	}
}
