

import java.io.*;
import java.util.*;

public class Main {
	
	static class Star{
		int i,j;

		public Star(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		
		
	}
	
	static int N,M,L,K; //가로 //세로 //트럼플린 한변 길이 // 별동별 수
	static int[][]map;
	static List<Star> stars;

	static void input()throws Exception{
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		//가로 세로 바꿔주기
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		//별 개수
		L = Integer.parseInt(st.nextToken());
		//별 개수
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		stars =new ArrayList<>();
		
	
		for(int c=0;c<K;c++) {
			
			st = new StringTokenizer(br.readLine()," ");
			//가로 세로 바꿔주기
			int j = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			stars.add(new Star(i,j));
		}
		int res = 0;
		
		for(Star s1 : stars) {
			for(Star s2: stars) {
				if(s1.i<0 || s1.i+L>500_000 || s2.j+L>500_000) continue;
				res = Math.max(res, bound(s1.i,s2.j));
			}
		}
	
		System.out.println(K-res);
	}
	


	static int bound(int i,int j) {
		int res = 0;
		for(Star s : stars) {
			if(i<=s.i && s.i<=i+L && j<=s.j&& s.j<=j+L) res++;
		}
		return res;
	}
	
	public static void main(String[] args) throws Exception{
		input();
	}
	
}
