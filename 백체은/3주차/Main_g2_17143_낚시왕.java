package a0815;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark {
	int speed;
	int direction;
	int size;
}

class Main_g2_17143_낚시왕 {

	static int R, C, M; // 격자판의 크기, 상어의 수
	static int sumOfSize = 0;
	static Shark[][] sharks;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharks = new Shark[R][C];

		// M 개의 줄에 상어의 정보
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			Shark shark = new Shark();
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			shark.speed = Integer.parseInt(st.nextToken());
			shark.direction = Integer.parseInt(st.nextToken());
			shark.size = Integer.parseInt(st.nextToken());
			sharks[r - 1][c - 1] = shark;
		}

		for (int j = 0; j < C; j++) {
			// 상어 잡기
			for (int i = 0; i < R; i++) {
				if (sharks[i][j] != null) {
					sumOfSize += sharks[i][j].size;
					sharks[i][j] = null;
					break; // 가까운 상어 잡았으면 끝
				}
			}
			// 상어 이동
			moveAllSharks();
		}
		System.out.print(sumOfSize);
	}
	
	static void moveAllSharks() {
		Shark[][] nextSharks = new Shark[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				moveShark(nextSharks, i, j);
			}
		}

		// 이동 완료한 배열로 덮어 씌우기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sharks[i][j] = nextSharks[i][j];
			}
		}
	}

	static void moveShark(Shark[][] nextSharks, int i, int j) {
		Shark shark = sharks[i][j];
		
		if (shark == null) {
			return;
		}

		// direction 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽
		// 위아래는 R, 좌우는 C 가 X 라고 할 때 (X - 1) * 2 만큼 이동하면 동일한 위치, 방향으로 돌아온다.
		// 그러므로 상어의 속도에서 (X - 1) * 2 을 나눈 나머지만큼만 이동하면 된다.
		// 총 이동해야 할 거리 = shark.speed % ((X - 1) * 2)
		int X = shark.direction < 3 ? R : C;
		int moveDistance = shark.speed % ((X - 1) * 2);
		int row = i;
		int col = j;

		// 움직이는 거리를 구한 후 이동
		for (int k = 0; k < moveDistance; k++) {
			if (shark.direction == 1) {
				row--;
				if (row < 0) {
					shark.direction = 2;
					row = 1;
				}
			} else if (shark.direction == 2) {
				row++;
				if (row > R - 1) {
					shark.direction = 1;
					row = R - 2;
				}
			} else if (shark.direction == 3) {
				col++;
				if (col > C - 1) {
					shark.direction = 4;
					col = C - 2;
				}
			} else {
				col--;
				if (col < 0) {
					shark.direction = 3;
					col = 1;
				}
			}
		}

		// 이미 상어가 있으면 크기를 비교해서 큰 상어를 남긴다.
		if (nextSharks[row][col] != null && nextSharks[row][col].size > shark.size) {
			return;
		}
		nextSharks[row][col] = shark; // 기존 상어보다 이동한 상어가 더 크면 덮어씌운다.
	}
}