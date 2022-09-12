package b14658;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static class star{
		int x;
		int y;
		public star(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		star[] stars = new star[K];
		
		int res = Integer.MIN_VALUE;
		
		for(int i =0; i<K;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			stars[i] = new star(x,y);
		}

		for(star s1 : stars) {
			for(star s2 : stars) {
				res = Math.max(res, calc(s1.x, s2.y, stars, L));
			}
		}
		
		System.out.println(K-res);
		br.close();

	}
	public static int calc(int x, int y, star[] stars, int L) {
		int res = 0;
		for(star s : stars) {
			if(x <= s.x && s.x <= x+L && y <= s.y && s.y <= y+L) {
				res++;
			}
		}
		return res;
	}

}
