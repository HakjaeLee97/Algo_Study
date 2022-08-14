package a0812;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Fish {
	int num;
	int dir;
	boolean isDead;

	public Fish(int num, int dir, boolean isDead) {
		this.num = num;
		this.dir = dir;
		this.isDead = isDead;
	}

	public Fish clone() {
		return new Fish(num, dir, isDead);
	}

}

public class Main_g2_19236_청소년상어_서울_20반_백체은 {

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상부터 45도씩 반시계방향
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int max = 0;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fish map[][] = new Fish[4][4];
		for (int r = 0; r < 4; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 4; c++) {
				map[r][c] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1, false);
			}
		}
		br.close();
		eatFish(0, 0, -1, 0, map);
		System.out.print(max);
	}

	static void eatFish(int r, int c, int dir, int sum, Fish[][] map) {

		// 물고기 먹음
		sum += map[r][c].num;
		dir = map[r][c].dir % 8; 
		map[r][c].isDead = true;

		max = Math.max(max, sum);

		// 물고기 이동
		for (int num = 1; num <= 16; num++) {
			moveFish(map, num, r, c);
		}

		// 상어 이동
		for (int d = 1; d <= 3; d++) {
			int nr = r + dr[dir] * d;
			int nc = c + dc[dir] * d;
			if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) // 범위 벗어난 경우
				break;
			if (map[nr][nc].isDead) // 이미 먹힌 물고기일 경우
				continue;

			Fish copyMap[][] = new Fish[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					copyMap[i][j] = map[i][j].clone(); // clone을 해주지 않으면 원본 Fish를 건드리게 됨.
				}
			}
			eatFish(nr, nc, dir, sum, copyMap); 
		}

	}

	static void moveFish(Fish[][] map, int num, int r, int c) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// 이동해야 하는 물고기이면서 살아있을 때
				if (map[i][j].num == num && !map[i][j].isDead) {
					for(int k = 0; k < 8; k++) { // 방향이 여덟개
						int dir = map[i][j].dir % 8;
						int nr = i + dr[dir];
						int nc = j + dc[dir];

						// 범위 벗어나거나 상어와 위치가 같으면 방향 전환
						if ((nr < 0 || nr >= 4 || nc < 0 || nc >= 4) || (nr == r && nc == c)) {
							map[i][j].dir++;
							continue;
						}
						
						// 위치 바꾸기
						Fish temp = map[nr][nc];
						map[nr][nc] = map[i][j];
						map[i][j] = temp;
						return; // 물고기는 한 칸만 이동 가능
					}
				}
			}
		}
	}
}