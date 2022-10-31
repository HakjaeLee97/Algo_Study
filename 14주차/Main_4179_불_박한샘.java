package October_fourth;

import java.util.*;
import java.io.*;

public class Main{
	
	static int N,M;
	static char map [][];
	static int answer;
	static int ji,jj;
	static Queue<int[]> fires = new ArrayDeque<>();
	static boolean[] visited;
	static int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};
	static boolean[][] v;
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[1000001];
		v = new boolean[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='J') {
					ji = i;
					jj = j;
					map[i][j]='.';
				}else if(map[i][j]=='F') {
					fires.offer(new int[] {i,j});
				}
			}
		}
		
		bfs();
		
		System.out.println(answer == 0 ? "IMPOSSIBLE" : answer);
	}

	
	static void bfs() {
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {ji,jj,1});
		
		while(!q.isEmpty()) {
			
			int []c = q.poll();
			int i = c[0];
			int j = c[1];
			int cnt = c[2];
			
			//불 이동
			//그 초에 이미 이동한 기록이 있으면 불을 퍼뜨릴 필요가 없을듯?
			if(!visited[cnt]) {
				
				int size = fires.size();
				
				for(int s=0;s<size;s++) {
					
					int [] f = fires.poll();
					
					for(int d=0;d<4;d++) {
						
						int ni = f[0] + move[d][0];
						int nj = f[1] + move[d][1];
						
						if(ni<0||nj<0||N-1<ni||M-1<nj) continue;
						if(map[ni][nj]=='.') {
							map[ni][nj] = 'F';
							fires.offer(new int[] {ni,nj});
						}
						
					}
				}
				//방문처리
				visited[cnt] = true;
			}
			
			//진수 이동
			for(int d=0;d<4;d++) {
				int ni = i + move[d][0];
				int nj = j + move[d][1];
				
				if(ni<0||nj<0||N-1<ni||M-1<nj) {
					answer = cnt;
					return;
				}
				
				if(map[ni][nj]=='.' && !v[ni][nj]) {
					
					v[ni][nj] = true;
					q.offer(new int[] {ni,nj,cnt+1});
				}
			}
			
	
		}
		
	}
}
