package b17837;

import java.io.*;
import java.util.*;

public class Main {

	public static class Token {
		
		int x, y, dir;
		
		Token(int x, int y, int dir) {
			
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	static int N, K;
	static int[][] board;
	static ArrayList<Integer>[][] pos; // 0번으로 갈수록 아래에 있고, 번호가 커질수록 위에 있음
	static Token[] tokens;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new int[N + 1][N + 1];
		tokens = new Token[K + 1];
		pos = new ArrayList[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				pos[i][j] = new ArrayList<>();
			}
		}

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			tokens[i] = new Token(x, y, dir);
			pos[x][y].add(i);
		}

		bw.write(solve() + "\n");
		bw.flush();

	}

	public static int solve() {

		int[] dx = { 0, 0, 0, -1, 1 };
		int[] dy = { 0, 1, -1, 0, 0 };

		int cnt = 0;

		while (true) {

			if (cnt > 1000)
				return -1;

			cnt++;

			for (int i = 1; i <= K; i++) {

				Token t = tokens[i];

				int nx = t.x + dx[t.dir];
				int ny = t.y + dy[t.dir];

				if ((nx < 1 || nx > N) || (ny < 1 || ny > N) || board[nx][ny] == 2) {
					int d = changeDirect(t.dir); // 방향 반대로 바꿈
					nx = t.x + dx[d]; // 방향 바뀐 후, 반대쪽으로 한칸 이동한 위치
					ny = t.y + dy[d];

					t.dir = d;
				}

				if ((1 <= nx && nx <= N) && (1 <= ny && ny <= N) && board[nx][ny] == 0) {

					List<Integer> tmp = new ArrayList<>();
					boolean flag = false;

					for (int n : pos[t.x][t.y]) { // 아래 말부터 꺼냄
						if (n == i)
							flag = true;

						if (flag) {
							pos[nx][ny].add(n);
							tmp.add(n);
						}
					}

					for (int n : tmp) {
						pos[tokens[n].x][tokens[n].y].remove((Integer) n);
						tokens[n].x = nx;
						tokens[n].y = ny;
					}
				}

				else if ((1 <= nx && nx <= N) && (1 <= ny && ny <= N) && board[nx][ny] == 1) {

					List<Integer> tmp = new ArrayList<>();

					for (int j = pos[t.x][t.y].size() - 1; j >= 0; j--) {
						int n = pos[t.x][t.y].get(j);

						pos[nx][ny].add(n);
						tmp.add(n);

						if (n == i)
							break;
					}

					for (int n : tmp) {
						pos[tokens[n].x][tokens[n].y].remove((Integer) n);
						tokens[n].x = nx;
						tokens[n].y = ny;
					}
				}
				for (int k = 1; k <= K; k++) {
					if (pos[tokens[k].x][tokens[k].y].size() >= 4)
						return cnt;
				}
			}
		}

	}

	public static int changeDirect(int i) {

		if (i == 1)
			return 2;
		else if (i == 2)
			return 1;
		else if (i == 3)
			return 4;
		else
			return 3;
	}


}