package b21610;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] dx; 
	static int[] dy; 
	//↖, ↗, ↘, ↙
	static int[] dx2 = {-1,-1,1,1};
	static int[] dy2 = {-1,1,1,-1};
	
	static boolean[][] rained;
	
	public static class cloud{
		int x;
		int y;
		public cloud(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		rained = new boolean[N][N];
		// ←, ↖, ↑, ↗, →, ↘, ↓, ↙
		dx = new int[] {0,N-1,N-1,N-1,0,1,1,1};
		dy = new int[] {N-1,N-1,0,1,1,1,0,N-1};
		ArrayList<cloud> clouds = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		clouds.add(new cloud(N-1,0));
		clouds.add(new cloud(N-1,1));
		clouds.add(new cloud(N-2,0));
		clouds.add(new cloud(N-2,1));
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int d = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			move_rain_copy(clouds,map,N,d,s);
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer += map[i][j];
			}
		}
		System.out.println(answer);
		br.close();
	}
	public static void move_rain_copy(ArrayList<cloud> clouds, int[][] map,int N, int d, int s) {
		int count;
		
		for(int i = 0; i<N;i++) {
			Arrays.fill(rained[i], false);
		}
		
		for(int i = 0,size = clouds.size(); i<size; i++) {
			//구름 이동
			clouds.get(i).x = (clouds.get(i).x + (dx[d] * s))%N;
			clouds.get(i).y = (clouds.get(i).y + (dy[d] * s))%N;
		}
		
		//비 내림
		for(int i = 0, size=  clouds.size(); i<size; i++) {
			cloud cur = clouds.get(i);
			map[cur.x][cur.y]++;
			rained[cur.x][cur.y] = true; 
		}

		//물복사버그
		while(!clouds.isEmpty()) {
			cloud cur = clouds.get(0);
			count = 0;
			for(int i = 0; i<4; i++ ) {
				int nx = cur.x + dx2[i];
				int ny = cur.y + dy2[i];
				if(nx<0 || nx>= N || ny<0 ||ny>= N || map[nx][ny] == 0) continue;
				count++;
			}
			map[cur.x][cur.y]+= count; 
			
			//구름 제거
			clouds.remove(0);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] >= 2 && rained[i][j] == false) {
					map[i][j] -= 2;
					clouds.add(new cloud(i,j));
				}
			}
		}
	}

}
