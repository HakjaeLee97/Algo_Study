package a0829;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_g5_21608_상어초등학교 {

	static class Students {
		int self;
		int[] bestFriends;

		public Students(int self, int[] bestFriends) {
			this.self = self;
			this.bestFriends = bestFriends;
		}
	}

	static int[] dr = { -1, 0, 0, 1 }; // 상 좌 우 하
	static int[] dc = { 0, -1, 1, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Students[][] seats = new Students[N][N];
		List<Students> students = new ArrayList<>();

		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] bestFriends = new int[4];
			int self = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				bestFriends[j] = Integer.parseInt(st.nextToken());
			}
			students.add(new Students(self, bestFriends));
		}
		br.close();

		for (int i = 0; i < N * N; i++) { // 학생 마다
			int bestR = -1;
			int bestC = -1;
			int maxFrineds = -1;
			int maxBlanks = -1;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) { // 모든 자리 돌면서
					int friends = 0; // 좋아하는 친구 수
					int blanks = 0; // 빈자리 수
					if (seats[r][c] == null) { // 빈 자리면 주변 탐색
						for (int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if (0 <= nr && nr < N && 0 <= nc && nc < N) {
								if(seats[nr][nc] == null) {
									blanks++;
								} else {
									for (int j = 0; j < 4; j++) {
										if (seats[nr][nc].self == students.get(i).bestFriends[j]) {
											friends++;
										} 
									}
								}
							}
						}
						if (maxFrineds < friends) { // 좋아하는 친구 많은 자리가 best
							maxFrineds = friends; // 현재 친구수 저장
							maxBlanks = blanks; // 현재 빈 자리 저장
							bestR = r;
							bestC = c;
						} else if (maxFrineds == friends) { // 좋아하는 친구 수가 같으면
							if (maxBlanks < blanks) { // 빈 자리가 많은 자리가 best
								maxBlanks = blanks; // 현재 빈 자리 저장
								bestR = r;
								bestC = c;
							} else if (maxBlanks == blanks) { // 빈자리 수마저 같다면 행과 열 따지기
								if (bestR > r) { // 현재 위치가 더 위의 열에 있으면 위치 갱신
									bestR = r;
									bestC = c;
								} else if (bestR == r && bestC > c) { // 행이 같고, 현재 위치가 더 왼쪽 열에 있으면 위치 갱신
									bestR = r;
									bestC = c;
								}
							}
						}
					}
				}
			}
			seats[bestR][bestC] = students.get(i);
		}
	
		// 만족도 조사
		// 모든 r, c에 대해 사방 탐색하면서 10의 친구수-1승. 0일 경우는 건너뛰기

		int sum = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int friends = 0;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (0 <= nr && nr < N && 0 <= nc && nc < N) {
						for (int j = 0; j < 4; j++) {
							if (seats[nr][nc].self == seats[r][c].bestFriends[j]) {
								friends++;
							}
						}
					}
				}
				if (friends != 0) {
					sum += Math.pow(10, friends - 1);
				}
			}
		}
		System.out.print(sum);
	}
}
