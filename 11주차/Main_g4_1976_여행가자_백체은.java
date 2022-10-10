package a10월2주차;

import java.io.*;
import java.util.*;

public class Main_g4_1976_여행가자 {

	static int[] p;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		makeSet(N);
		int[][] arr = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); 
			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
				// 1일 때마다 union
				if(arr[r][c] == 1) {
					union(r, c);
				}
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int[] plan = new int[M];
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken())-1;
		}
		br.close();
		// 여행 계획들을 union해서 true 나오면 no
		for (int i = 0; i < M-1; i++) {
			if(union(plan[i], plan[i+1])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	private static void makeSet(int i) {
		p = new int[i + 1];
		for (int j = 1; j <= i; j++) {
			p[j] = j;
		}
	}

	static int findSet(int a) {
		if (a == p[a])
			return a;
		return p[a] = findSet(p[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;
		p[bRoot] = aRoot;
		return true;
	}
}
