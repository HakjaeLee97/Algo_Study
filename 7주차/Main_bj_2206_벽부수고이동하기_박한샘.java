p
import java.util.*;
import java.io.*;

public class Main {


	static int N, M;
	static int map[][];
	static boolean visited[][][];
	static int answer = Integer.MAX_VALUE/2;
	static int[][]mov = {{-1,0},{0,1},{1,0},{0,-1}};
	
	
	static void input() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		
	}
	static void output() {
		
		System.out.println(answer==Integer.MAX_VALUE/2 ? -1 : answer);
	}
	
	static void calc() {
		
		Queue<int[]>q = new ArrayDeque<>();
		
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		q.offer(new int[] {0,0,0,1});
	
		//벽을 넘어서는건 1
		
		while(!q.isEmpty()) {
			int[]c = q.poll();
			
			int i = c[0];
			int j = c[1];
			int w = c[2];
			int cnt = c[3];
			//0이 벽
			
			if(i==N-1 && j==M-1) {
				answer = Math.min(answer, cnt);
			}
			
			for(int d=0;d<4;d++) {
				
				int ni = i + mov[d][0];
				int nj = j + mov[d][1];
				
				if(ni<0||N-1<ni||nj<0||M-1<nj) continue;
				//벽을 안뚫은  경우
				if(w==0) {
					
					//벽이 없는 경우 이동
					if(!visited[ni][nj][0] && map[ni][nj]==0) {
						visited[ni][nj][0]=true;
						q.offer(new int[] {ni,nj,0,cnt+1});
					}
					//벽이 있지만, 한번 뚫을 수 있는 경우 이동
					if(!visited[ni][nj][1] && map[ni][nj]==1) {
						visited[ni][nj][1] = true;
						q.offer(new int[] {ni,nj,1,cnt+1});
					
					}
				//벽을 이미 뚫은 경우
				}else{
					
					//벽이 없는 경우에만 이동 
					if(!visited[ni][nj][1] && map[ni][nj]==0) {
						visited[ni][nj][1] = true;
						q.offer(new int[] {ni,nj,1,cnt+1});
					}
					
				}
				
			}
			
			
		}
		
		
	
		
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		input();
		calc();
		output();
	}
}
