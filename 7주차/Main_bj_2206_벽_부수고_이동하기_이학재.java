package b2206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for(int i = 0; i<N; i++) {
			String tmp = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j) -'0';
			}
		}
		
		int answer = bfs(map);
		System.out.println(answer);
		br.close();
	}
	public static int bfs(int[][] map) {

		boolean[][][] visited= new boolean[N][M][2];
		visited[0][0][0] = true;
				
		Queue<int[]> q = new LinkedList<int[]>();
		//x좌표, y좌표, 벽을 부순 여부, 총 길이
		q.offer(new int[] {0,0,0,1});
		
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
		
			if(now[0] == N-1 && now[1] == M-1) {
				return now[3];
			}
			
			for(int i = 0; i<4;i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if(nx< 0 || ny<0  || nx>=N || ny >=M ) continue;
				if(map[nx][ny] == 0) {
					if(now[2] == 0 && visited[nx][ny][0] == false) {
						q.offer(new int[] {nx,ny,now[2],now[3]+1});
						visited[nx][ny][0] = true;
					}
					else if(now[2] == 1 && visited[nx][ny][1] == false) {
						q.offer(new int[] {nx,ny,now[2],now[3]+1});
						visited[nx][ny][1] = true;
					}
						
				}else if(now[2] == 0) {
					q.offer(new int[] {nx,ny,1,now[3]+1});
					visited[nx][ny][1] = true;
				
				}
			}
		}
		return -1;
		
	}
}
