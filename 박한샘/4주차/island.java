package August_third;

import java.util.*;
import java.io.*;

public class island {
	
	static int answer;
	static int [][] map;
	static int N;
	static int M;
	static boolean[][]visited;
	static int mov [][] = {{-1,0},{-1,1},{0,1},{1,1},
							{1,0},{1,-1},{0,-1},{-1,-1}};
	
	

	
	public static void main(String[] args) throws Exception {
		
	
		while(true){
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			answer = 0;
			
			if(N==0 && M==0) {
				break;
			}
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0;j<M;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		
	
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j]==1 && !visited[i][j]) {
						bfs(i,j);
						answer++;
					}
				}
			}
			System.out.println(answer);
		
		}
		
	}

	static void bfs(int y, int x) {
		
		
		visited[y][x] = true;
		
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {y,x});
		
		while(!q.isEmpty()) {
			
			int [] c = q.poll();
			
			int i = c[0];
			int j = c[1];
			
			for(int d=0;d<8;d++) {
				
				int ni = i + mov[d][0];
				int nj = j + mov[d][1];
				
				if(ni<0||nj<0||N-1<ni||M-1<nj) continue;
				
				if(!visited[ni][nj] && map[ni][nj]==1) {
					visited[ni][nj] =true;
					q.offer(new int[] {ni,nj});
				}
				
				
			}
			
			
			
		}
		
	}
	
}
