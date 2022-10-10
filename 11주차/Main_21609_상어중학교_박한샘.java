package October_first;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N,C;
	static int [][] map;
	static int answer;
	
	static class BGroup implements Comparable<BGroup>{
		
		int count,rain,i,j;

		public BGroup(int count, int rain, int i, int j) {
			super();
			this.count = count;
			this.rain = rain;
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(BGroup o) {
			
			if(count==o.count) {
				if(rain==o.rain) {
					if(i==o.i) {
						if(j==o.j) {
							return j - o.j;
						}
						return i - o.i;
					}
					return -(rain - o.rain);
				}
				return -(count - o.count);
			}
	
		}
	
	/*
	 맨해튼 거리 == 인접칸
	 
	 일반 블록 적어도 하나,
	 일반 블록 색은 다 같아야 함
	 무지개는 ㄱㅊ 검은색은 x
	 블록의 개수는 2이상, 블록 행이 가장 작은, 아니면 열이 가장 작은 블록이 기준
	 
	 오토플레이 : 
	  
	 1. 크기가 가장 큰 블록 그룹을 찾는다.(무지개 블록 수가 많은 그룹 -> 기준 블록
	 	행 -> 기준 블록 열)
	 
 	2. 1에서 찾은 블로 그룹 다 제거, ( 제거된 수^2 점수) 
 	3. 격자에 중력 작용
 	4. 격자가 90도 반시계 방향 회전
 	5. 다시 격자에 중력이 작용
 	6. 오토 플레이가 다 끝났을 때의 합 출력
	  
	 
	 
	 */
	
	static void input()throws Exception{
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void output() {
		System.out.println(answer);
	}
	
	static void calc() {
		
		//블록그룹찾기
		
		
		
		
		
		
		
		
		
		
	}
	public static void main(String[] args) throws Exception {
		input();
		calc();
		output();
	}

}
