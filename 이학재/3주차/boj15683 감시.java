package b2468;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int lowest = 100; // 제일 낮은 지대의 높이
		int highst = 1; // 제일 높은 지대의 높이
		
		for(int i = 0; i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp =  Integer.parseInt(st.nextToken());	
				map[i][j] = tmp;
				if (tmp > highst) highst = tmp;
				else if(tmp < lowest) lowest = tmp;
			}
		}
		int result = 0;
		for(int water = lowest-1; water <=highst; water++) { // 물의 수위만큼 반복
			int tmp = 0;
			boolean[][] visited = new boolean[N][N];
			for(int i =0; i< N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > water && visited[i][j] == false) {
//						dfs(i,j,N,visited,water,map);
						bfs(i,j,N,visited,water,map);
						tmp++;
					}
					
				}
			}
			result = Math.max(result, tmp);
		}
		
		
		System.out.println(result);
		br.close();
	}
/*	public static void dfs(int i,int j,int N,boolean[][] visited, int water, int[][] map) {
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};
		visited[i][j] = true;
		for(int a = 0; a< 4; a++) {
			int nx = i +dx[a];
			int ny = j +dy[a];
			if(nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == false && map[nx][ny] > water) {
				dfs(nx,ny,N,visited,water,map);
			}
		}
	}*/
	public static void bfs(int i,int j,int N,boolean[][] visited, int water, int[][] map) {
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{i,j});
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			i = now[0];
			j = now[1];
			for(int a = 0; a< 4; a++) {
				int nx = i +dx[a];
				int ny = j +dy[a];
				if(nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == false && map[nx][ny] > water) {
					q.offer(new int[]{nx,ny});
					visited[nx][ny] = true;
				}
			}
		}
	}
}
