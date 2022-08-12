package b19236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = new int[] {-1,-1,0,1,1,1,0,-1};
	static int[] dy = new int[] {0,-1,-1,-1,0,1,1,1};
	static int best = 0;


	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][][] fish = new int[4][4][2];
		for(int i =0; i<4; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j = 0; j<4; j++) {
				int num = Integer.parseInt(st.nextToken()); // 물고기의 번호
				int dir = Integer.parseInt(st.nextToken()) -1 ; // 물고기의 방향
				fish[i][j][0] = num;
				fish[i][j][1] = dir;
			}
		}
		int score = fish[0][0][0]; // 상어가 (0,0)에 들어가 먹음
		fish[0][0][0] = -1; // -1은 상어를 의미함
		

		
		
		moveshark(fish,0,0,score);
		System.out.println(best);
		br.close();
	}
	public static void movefish(int[][][] fish) {
		int num = 1;
		while(num<= 16) {
			nextnum : for(int i = 0; i<4; i++) {
				for(int j =0; j<4; j++) {
					if(fish[i][j][0] == num) {
						int dir = fish[i][j][1];
						for(int d = 0; d< 8; d++) {
							int nx = i + dx[dir];
							int ny = j + dy[dir];
							if(nx< 0 || ny < 0 || nx>=4 || ny>= 4 || fish[nx][ny][0] == -1) {
								dir = (++dir)%8;		
								continue;
							}
							int tmpnum = fish[nx][ny][0]; 
							int tmpdir = fish[nx][ny][1]; 
							fish[nx][ny][0] = num;
							fish[nx][ny][1] = dir;
							fish[i][j][0] = tmpnum;
							fish[i][j][1] = tmpdir;
				
							break nextnum;
							
						}
					}
				}
			}
			num++;
			
		}
	}
	public static void moveshark(int[][][] fish, int x, int y, int score) {
		movefish(fish);
		best = Math.max(best, score);
		int dir = fish[x][y][1];
		int depth = 1;
		int[][][] tmp = new int[4][4][2];	 //fish 배열 세이빙
		for(int i = 0; i<4; i++) {
			for(int j= 0; j< 4; j++) {
				tmp[i][j][0] = fish[i][j][0];
				tmp[i][j][1] = fish[i][j][1];
			}
		}
		
		while(true) {
			int nx = x + dx[dir]*depth;
			int ny = y + dy[dir]*depth;
			if(nx< 0 || ny < 0 || nx>=4 || ny>= 4) {
				break;
			}
			else if(fish[nx][ny][0] >= 1) {
				fish[x][y][0] = 0;
				int tmp_score = fish[nx][ny][0];
				fish[nx][ny][0] = -1;
				moveshark(fish,nx,ny,score + tmp_score);
				
				
				for(int i = 0; i<4; i++) {
					for(int j= 0; j< 4; j++) {
						fish[i][j][0] = tmp[i][j][0];
						fish[i][j][1] = tmp[i][j][1];
					}
				}
				
			}
			depth++;
			
	
		}
		
	}
}
