package b17143;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// d가 0이면 위, 1이면 아래, 2면 오른쪽, 3이면 왼쪽
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		int[][][] map = new int[R][C][3];
		
		for(int i = 0; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			
			map[x][y][0] = s; // 속도
			map[x][y][1] = d; // 이동방향
			map[x][y][2] = z; // 크기
		}
		int king = 0;
		int score = 0;
		//0위 1 아래 2 오른쪽3 왼쪽
		while(king <= C-1) {
			for(int i = 0; i< R; i++) {
				if(map[i][king][2] != 0) {
					score += map[i][king][2];
					map[i][king] = new int[] {0,0,0};
					break;
				}
			}
			int[][][] moved = new int[R][C][3];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if(map[i][j][2] != 0) {
						int nx = i + (dx[map[i][j][1]] * map[i][j][0]);
						int ny = j + (dy[map[i][j][1]] * map[i][j][0]);
						while(true) {
							if(nx < 0) {
								nx = -nx;
								map[i][j][1] = 1;
							}
							else if(nx >= R) {
								nx = 2*R -2 - nx;
								map[i][j][1] = 0;
							}
							else if(ny<0) {
								ny = -ny;
								map[i][j][1] = 2;
							}
							else if(ny >=C) {
								ny = 2*C- 2- ny;
								map[i][j][1] = 3;
							}
							else break;
						}
						if(map[i][j][2] > moved[nx][ny][2]) { //상어가 겹쳤는데 더 크거나 빈자리면 상어 이동			
							moved[nx][ny][0] = map[i][j][0]; 
							moved[nx][ny][1] = map[i][j][1]; 
							moved[nx][ny][2] = map[i][j][2];
						} 
						map[i][j] = new int[] {0,0,0};			
							
					}
					
				}
			}
			for(int i = 0; i<R; i++) {
				for(int j = 0; j< C; j++) {
					for(int k =0; k<3; k++) {
						map[i][j][k] = moved[i][j][k];
					}
				}
			}
			
			king++;
			
		}
		System.out.println(score);
	}

}
