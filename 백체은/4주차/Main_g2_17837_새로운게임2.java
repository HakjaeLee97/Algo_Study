package a0822;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_g2_17837_새로운게임2 {

	static int N, K;
	static int[][] map;
	static Node[] node;
	static List<Integer>[][] list;
	static int[] dr = { 0, 0, -1, 1 }; // 우좌상하
	static int[] dc = { 1, -1, 0, 0 };
	static int[] direct = { 1, 0, 3, 2 };

	static class Node {
		int r, c, d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		list = new ArrayList[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				list[r][c] = new ArrayList<>();
			}
		}

		node = new Node[K];
		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;

			node[i] = new Node(r, c, d);
			list[r][c].add(i);
		}

		br.close();
		System.out.print(solve(1));

	}

	public static int solve(int turn) {
		if (turn > 1000)
			return -1;

		for (int i = 0; i < K; i++) {
			if (move(i) >= 4) {
				return turn;
			}
		}

		return solve(turn + 1);
	}

	public static int move(int idx) {
		int r = node[idx].r;
		int c = node[idx].c;
		int d = node[idx].d;

		int nr = r + dr[d];
		int nc = c + dc[d];
		
		// 이동할 벽이 파란 벽인 경우
		if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 2) {
			node[idx].d = direct[d];
			nr = r + dr[node[idx].d];
			nc = c + dc[node[idx].d];
			// 방향을 바꿔서 이동해도 파란 벽이면 종료
			if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 2) {
				return list[r][c].size();
			}
		}

		int index = 0;
		int size = list[r][c].size();

		// 현재 노드(말)의 높이를 구함
		for (int i = 0; i < size; i++) {
			if (list[r][c].get(i) == idx) {
				index = i;
				break;
			}
		}

		// 이동할 벽이 빨간 벽이면 노드들의 순서를 변경
		if (map[nr][nc] == 1) {
			ArrayList<Integer> temp = new ArrayList<>();

			for (int i = index; i < size; i++) {
				temp.add(list[r][c].get(i));
			}
			for (int i = 0; i < temp.size(); i++) {
				list[r][c].remove(list[r][c].size() - 1);
			}
			for (int i = temp.size() - 1; i >= 0; i--) {
				list[r][c].add(temp.get(i));
			}
		}

		// 노드들을 이동, 좌표도 변경해줌
		int cnt = 0;
		for (int i = index; i < list[r][c].size(); i++) {
			int pos = list[r][c].get(i);
			node[pos].r = nr;
			node[pos].c = nc;
			list[nr][nc].add(pos);
			cnt++;
		}

		// 이전의 노드 제거
		for (int i = 0; i < cnt; i++)
			list[r][c].remove(list[r][c].size() - 1);

		return list[nr][nc].size();
	}
}