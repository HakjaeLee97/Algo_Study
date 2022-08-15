package a0815;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class coordinate {
	int x;
	int y;

	coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_g4_15685_드래곤커브 {

	static boolean visited[][] = new boolean[101][101];
	static int[] dr = { 0, -1, 0, 1 }; // 우 상 좌 하
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int ans = 0;

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()); // 시작 방향
			int g = Integer.parseInt(st.nextToken()); // 세대
			dragon(y, x, d, g);
		}
		br.close();

		// 사각형 세기
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if (visited[r][c] && visited[r + 1][c] && visited[r][c + 1] && visited[r + 1][c + 1]) {
					ans++;
				}
			}
		}

		System.out.print(ans);
	}

	static void dragon(int x, int y, int d, int g) {
		coordinate end = new coordinate(x, y);
		List<Integer> dir = new ArrayList<>();
		dir.add(d);
		visited[x][y] = true;

		for (int i = 0; i <= g; i++) {
			for (int j = dir.size() / 2; j < dir.size(); j++) {
				end.x = end.x + dr[dir.get(j)];
				end.y = end.y + dc[dir.get(j)];
				visited[end.x][end.y] = true;
			}

			ArrayList<Integer> temp = new ArrayList<>(); // 새로 붙일 방향 
			for (int j = dir.size() - 1; j >= 0; j--) {
				temp.add((dir.get(j) + 1) % 4); // 시계방향 회전
			}

			for (int j : temp) {
				dir.add(j);
			}
		}
	}
}
