package a10월1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_g5_14719_빗물_2차원배열 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[H][W];
		st = new StringTokenizer(br.readLine());
		
		for (int c = 0; c < W; c++) {
			int block = Integer.parseInt(st.nextToken());
			for (int r = 0; r < block; r++) {
				map[H-1-r][c] = true;
			}
		}
		int sum = 0;
		for (int r = 0; r < H; r++) {
			int left = 0;
			boolean flag = false;
			for (int c = 0; c < W; c++) {
				if(map[r][c]) {
					if(flag) sum += c-left-1;
					else flag = true;
					left = c;
				}
			}
		}
		System.out.println(sum);
		br.close();
	}
}
