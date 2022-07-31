package s1247;

import java.util.*;

public class Solution {
	static int N;
	static int Min;
	
	public static void Permutation(int num,int[][] map, int[] result, boolean[] check) { // 순열 생성
		//집도착
		if(num==N+1) {
			int sum = 0;
			//최소 거리의 합 계산
			for(int i=0;i<N+1;i++) {
				sum += Math.abs(map[result[i]][0]-map[result[i+1]][0]) + Math.abs(map[result[i]][1]-map[result[i+1]][1]);
			}
			
			if(sum<Min) Min = sum;
			return;
		}
		
		//집을 0과 방문으로 체크
		result[0] = 0;
		check[0] = true;
		
		//회사를 N+1과 방문으로 체크
		result[N+1] = N+1;
		check[N+1] = true;
		
		
		//순열 생성
		for(int i=1;i<N+1;i++) {
			if(check[i]) continue;
			result[num] = i;
			check[i] = true;
			Permutation(num+1,map,result,check);
			check[i] = false;
		}
	}
	/*
	 * res [0 1 2 3 4 5 6 7 8 9 N (N+1 -> if)
	 * chk [f t t t t t t t t t t
	 * 
	 * 
	 */ 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			N = sc.nextInt();
			
			int[][] map = new int[N+2][2]; //좌표저장배열
			boolean[] check = new boolean[N+2]; //방문 체크
			int[] result = new int[N+2]; //뽑는 결과
			
			Min = Integer.MAX_VALUE;
			//집의 좌표 입력
			map[0][0] = sc.nextInt();
			map[0][1] = sc.nextInt();
			
			//회사의 좌표 입력
			map[N+1][0] = sc.nextInt();
			map[N+1][1] = sc.nextInt();
			
			//고객의 좌표 입력
			for(int i=1;i<=N;i++) {
				map[i][0] = sc.nextInt();
				map[i][1] = sc.nextInt();
			}
			
			Permutation(1,map,result,check);
			System.out.printf("#%d %d\n",tc,Min);
		}
		
	}
	
	
}