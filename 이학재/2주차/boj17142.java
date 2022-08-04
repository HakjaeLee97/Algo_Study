package b17142;

import java.io.*;
import java.util.*;


class Point{
	int row,col;
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class Main {
	static int[] dx = new int[] {1,-1,0,0}; 
	static int[] dy = new int[] {0,0,1,-1};
	static int[][] map;
	static int N,M,best_time;
	static boolean[] active;
	static ArrayList<Point> vir = new ArrayList<>();
	static int empty = 0;
	
	public static void main(String[] args) throws Exception {
		

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		

		best_time = -1; //바이러스가 퍼지는데 걸린 최소 시간
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) vir.add(new Point(i,j)); // 바이러스를 놓을 수 있는 위치일 경우 배열에 저장
				if(map[i][j] == 0) empty++;
			}
		} 
		active = new boolean[vir.size()];
		
		BFS(0,0);
		System.out.println(best_time);
		
	}
	
	static void BFS(int start, int cnt ) {
		int emptycnt = empty;
		if(cnt == M) {

			
			boolean[][] visited = new boolean[N][N];
			Queue<int[]>q = new LinkedList<int[]>();
			for(int i = 0; i< vir.size(); i++) {
				Point v = vir.get(i);
				if (active[i]) {
					q.add(new int[] {v.row,v.col,0} );
				}			
			} // 활성된 바이러스들을 큐에 초기로 입력
			

			
			int stage = 0; // 퍼지는데 걸린 시간

			while(!q.isEmpty()) {
				int[] now = q.poll();
				int nowx = now[0];
				int nowy = now[1];
				int nowstage = now[2];
				
				for(int d = 0; d<4; d++) {
					int nx = nowx + dx[d];
					int ny = nowy + dy[d];
					if(nx<0 || nx >= N || ny<0|| ny>=N) continue;
					if(check(nx,ny, map) && !visited[nx][ny]) {
						visited[nx][ny] = true;
						if(map[nx][ny] == 2) {
							q.add(new int[] {nx,ny, nowstage+1});
						}
						else{
							q.add(new int[] {nx,ny, nowstage+1});
							stage = Math.max(stage, nowstage+1);
							emptycnt--;
						}
						
					}
				}

			}
			
			// 바이러스 전부 퍼트렸는지 검사 필요
			
			if(emptycnt != 0) return;
			if(best_time == -1) best_time = stage;
			if (stage < best_time) {
				best_time = stage;
			}

		}
		for(int i = start ; i< vir.size(); i++) {
			if(active[i] ) continue;
			active[i] = true;
			BFS(i+1, cnt+1);
			active[i] = false;
		}
		
		
	}
	
	static boolean check(int x, int y, int[][] map) {
		if (map[x][y] == 0) {
			return true; // 탐색 가능
		} else if (map[x][y] == 1 ) {
			return false; // 벽이므로 탐색 불가
		} else if (map[x][y] ==2) {
			return true;
		} else { //3인경우(활성 바이러스)
			return true; //어차피 visited이므로 의미없음
		}
		
	}
	
}