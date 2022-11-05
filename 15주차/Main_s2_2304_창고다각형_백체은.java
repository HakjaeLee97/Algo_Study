package a11월1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_s2_2304_창고다각형 {
	
	static class Column {
		private int l, h;

		public Column(int l, int h) {
			this.l = l;
			this.h = h;
		}
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Column[] col = new Column[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			col[i] = new Column(l, h);
		}
		br.close();
		Arrays.sort(col, (o1, o2) -> o1.l - o2.l); // 위치 기준으로 정렬
		int sum = 0;
		Column now = col[0];
		int last = -1;
		for (int i = 0; i < N; i++) {
			if(now.h <= col[i].h) {
				sum += (col[i].l - now.l)*now.h;
				now = col[i];
				last = i;
			}
		}
		now = col[N-1];
		for (int i = N-1; i >= last; i--) {
			if(now.h <= col[i].h) {
				sum += (now.l - col[i].l)*now.h;
				now = col[i];
			}
		}
		sum += now.h;
		
		System.out.println(sum);
	}
}
