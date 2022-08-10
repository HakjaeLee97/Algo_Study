package b16236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		//사방 탐색의 순서는 상 좌 하 우 순으로 한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int x = 0;
		int y = 0;
		int size = 2;
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					x= i;
					y= j; //현재 상어의 위치
				}
			}
		}
		int result = bfs(map,x,y, N,size);
		System.out.println(result);
		
		br.close();
	}
	public static int bfs(int[][] map, int x, int y, int N,int size) {
		boolean[][]checked = new boolean[N][N];
		int[] dx = new int[] {-1,0,1,0};
		int[] dy = new int[] {0,-1,0,1};
		int time = 0;
		int size_cnt = 0;
		int fish_time = 0;
		
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> fishes = new LinkedList<>();
		q.add(new int[] {x,y,0,0});
		map[x][y] = 0;
		checked[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowx = now[0];
			int nowy = now[1];
			int nowtime = now[2];
			if((fishes.isEmpty() == false && nowtime >= fish_time) || now[3] == 1) {
				int fish_x = Integer.MAX_VALUE;
				int fish_y = Integer.MAX_VALUE;
				while(fishes.isEmpty() == false){
					int[] fish = fishes.poll();
					if (fish[0] < fish_x) {
						fish_x = fish[0];
						fish_y = fish[1];
					} else if(fish[0] == fish_x && fish[1] <fish_y) {
						fish_x = fish[0];
						fish_y = fish[1];
					}
				}
				time = fish_time;
				checked = new boolean[N][N];
				map[fish_x][fish_y] = 0;
				size_cnt++;
				if(size_cnt == size) {
					size++;
					size_cnt = 0;
				}
				q.clear();
				fishes.clear();
				q.add(new int[]{fish_x,fish_y,fish_time,0});
				checked[fish_x][fish_y] = true;
				continue;
			}
			for(int i = 0; i< 4; i++) {
				int nx = nowx + dx[i];
				int ny = nowy + dy[i];
				if(nx<0 || nx >=N || ny <0 || ny >= N || (map[nx][ny] > size && map[nx][ny] != 9) || checked[nx][ny]) continue;
				if(map[nx][ny] != 0 && map[nx][ny] < size) {
					fishes.add(new int[] {nx,ny});
					fish_time = nowtime +1 ;
					q.add(new int[]{nx,ny,nowtime + 1,1});
				}else {
					q.add(new int[]{nx,ny,nowtime + 1,0});
					
				}
				checked[nx][ny] = true;
			}
		}
		return time;
	}
}
