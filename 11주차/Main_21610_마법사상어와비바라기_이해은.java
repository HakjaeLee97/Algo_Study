import java.io.*;
import java.util.*;

//시작시간 : 10-09 18:30
//종료시간 : 10-09 19:34
//걸린시간 : 01:04
//분 류    : 구현
//특이사항 :
//	- 
public class Main_21610_마법사상어와비바라기_이해은 {

	static int N, M;
	static int[][] map;
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	
	static class Point {
		int row;
		int col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static List<Point> cloud;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloud = new ArrayList<>();
		
		cloud.add(new Point(N - 1, 0));
		cloud.add(new Point(N - 1, 1));
		cloud.add(new Point(N - 2, 0));
		cloud.add(new Point(N - 2, 1));
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken()); // 방향
			int s = Integer.parseInt(st.nextToken()); // 거리
			
			//1. 구름 이동
			move(d, s);
			
			//2. 각 구역 구름에서 비가 내려 구름있는 칸의 바구니에 저장된 물의 양이 1 증가
			v = new boolean[N][N];
			for(int i = 0; i < cloud.size(); i++) {
				int rowTest = cloud.get(i).row;
				int colTest = cloud.get(i).col;
				map[cloud.get(i).row][cloud.get(i).col]++;
				v[cloud.get(i).row][cloud.get(i).col] = true;
			}
			
			//3. 구름이 모두 사라진다.
			//cloud.clear();
			
			//4. 2에서 물이 증가한 칸에 대해 대각선 방향에 물이 있는 바구니 수 만큼 물이 증가한다.
			//(대각선에 대해서는 맵 연장을 하지 않음)
			int[][] tmp = new int[N][N];
			for(int i = 0; i < cloud.size(); i++) {
				int cnt = 0;
				int row = cloud.get(i).row - 1;
				int col = cloud.get(i).col - 1;
				if(range(row, col) && map[row][col] >= 1)
					cnt++;
				
				row = cloud.get(i).row - 1;
				col = cloud.get(i).col + 1;
				if(range(row, col) && map[row][col] >= 1)
					cnt++;
				
				row = cloud.get(i).row + 1;
				col = cloud.get(i).col - 1;
				if(range(row, col) && map[row][col] >= 1)
					cnt++;
				
				row = cloud.get(i).row + 1;
				col = cloud.get(i).col + 1;
				if(range(row, col) && map[row][col] >= 1)
					cnt++;
				
				tmp[cloud.get(i).row][cloud.get(i).col] = cnt;
			}
			
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					map[row][col] += tmp[row][col];
				}
			}
			
			//3번을 여기서 실행함, (4번에서 사용해서)
			cloud.clear();
			
			//5. 바구니에 저장된 물의 양이 2이상인 모든 칸에 구름이 생기고 물이 2 줄어든다.
			//(이 때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야한다.)
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					if(map[row][col] >= 2 && !v[row][col]) {
						cloud.add(new Point(row, col));
						map[row][col] -= 2;
					}
				}
			}
		}
		
		int answer = 0;
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < N; col++) {
				answer += map[row][col];
			}
		}
		
		System.out.println(answer);
	}
	
	//true : 범위 내 / false : 범위 밖
	static boolean range(int row, int col) {
		if(row < 0 || row >= N || col < 0 || col >= N) return false;
		return true;
	}
	
	static void move(int d, int s) {
		for(int i = 0; i < cloud.size(); i++) {
			cloud.get(i).row += dr[d] * s;
			cloud.get(i).col += dc[d] * s;
			
			while(cloud.get(i).row >= N) {
				cloud.get(i).row -= N;
			}
			
			while(cloud.get(i).col >= N) {
				cloud.get(i).col -= N;
			}
			
			while(cloud.get(i).row < 0) {
				cloud.get(i).row += N;
			}
			
			while(cloud.get(i).col < 0) {
				cloud.get(i).col += N;
			}
		}
	}
}
