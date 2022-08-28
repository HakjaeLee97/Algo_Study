package a0829;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_g4_20056_마법사상어와파이어볼 {
	
	public static int N, M, K;
	public static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	public static ArrayList<Node>[][] map;
	
	public static class Node {
		int r;
		int c;
		int m;
		int d;
		int s;
		
		public Node(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList[N][N];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if(map[r-1][c-1] == null) {
				map[r-1][c-1] = new ArrayList<Node>();
				map[r-1][c-1].add(new Node(r-1, c-1, m, s, d));
			} else {
				map[r-1][c-1].add(new Node(r-1, c-1, m, s, d));
			}
		}
		
		while(K-- > 0) {
			// 파이어볼 이동
			ArrayList<Node>[][] copy = new ArrayList[N][N];
			
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					if(map[r][c] == null) continue;
					for(int p=0;p<map[r][c].size();p++) {
						Node node = map[r][c].get(p);

						int nr = node.r + dr[node.d] * (node.s % N);
						int nc = node.c + dc[node.d] * (node.s % N);
		
						if(nr < 0) {
							nr = N - Math.abs(nr);
						} else if(nr >= N) {
							nr -= N;
						}
						if(nc < 0) {
							nc = N - Math.abs(nc);
						} else if(nc >= N) {
							nc -= N;
						}
			
						
						if(copy[nr][nc] == null) {
							copy[nr][nc] = new ArrayList<>();
						} 
						copy[nr][nc].add(new Node(nr, nc, node.m, node.s, node.d));
					}
				}
			}
			
			// 중복 처리
			for(int r=0;r<N;r++) {
				for(int c=0;c<N;c++) {
					if(copy[r][c] == null || copy[r][c].size() == 1) {
						continue;
						
					}
					int sumM = 0;
					int sumS = 0;
					int size = copy[r][c].size();
					boolean check1 = true; // 홀수 체크
					boolean check2 = true; // 짝수 체크
					for(int p=0;p<copy[r][c].size();p++) {
						Node nd = copy[r][c].get(p);
						sumM += nd.m;
						sumS += nd.s;
						if(nd.d % 2 == 1) {
							check2 = false;
						} else {
							check1 = false;
						}
					}
					copy[r][c].clear();
					if(sumM / 5 != 0) {

						for(int p=0;p<4;p++) {
							int dir = 0;
							if(check1 || check2) {
								dir = p*2;
							} else {
								dir = p*2+1;
							}
							copy[r][c].add(new Node(r, c, sumM/5, sumS/size, dir));
						}
					} 
				}
			}
			map = copy;
		}
		
		int ans = 0;
		for(int r=0;r<N;r++) {
			for(int c=0;c<N;c++) {
				if(map[r][c] == null) continue;
				for(int p=0;p<map[r][c].size();p++) {
					ans += map[r][c].get(p).m;
				}
			}
		}
		System.out.print(ans);
	}
}