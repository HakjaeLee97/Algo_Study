package a0819.camp;

import java.io.*;
import java.util.*;

public class Main_bj_17822_원판돌리기_서울_20반_이해은 {

	static int[][] map;
	static int N, M, T;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//원판의 수
		N = Integer.parseInt(st.nextToken());
		//원판에 적힌 숫자의 수
		M = Integer.parseInt(st.nextToken());
		//회전 횟수
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][M];
		
		for(int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			//x 배수의 원판 선택
			int x = Integer.parseInt(st.nextToken());
			//회전 방향
			int d = Integer.parseInt(st.nextToken());
			//k 칸 회전
			int k = Integer.parseInt(st.nextToken());
			
			rotation(x, d, k);
			check();
		}
		
		System.out.println(getSum());
		br.close();
	}
	
	//평균 값 리턴
	static double getAvg() {
		double ret = 0;
		int cnt = 0;
		for(int row = 1; row <= N; row++) {
			for(int col = 0; col < M; col++) {
				if(map[row][col] == 0) continue;
				cnt++;
				ret += map[row][col];
			}
		}
		
		return ret / cnt;
	}

	//전체 합 리턴
	static int getSum() {
			int ret = 0;
			for(int row = 1; row <= N; row++) {
				for(int col = 0; col < M; col++) {
					ret += map[row][col];
				}
			}
			
			return ret;
		}
		
	//배열의 회전
	static void rotation(int x, int d, int k) {
		if(d == 0) {
			//0 인 경우 시계 방향
			for(int turnIdx = 0; turnIdx < k; turnIdx++) {
				for(int cir = x; cir <= N; cir += x) {
					int tmp = map[cir][M - 1];
					for(int col = M - 1; col > 0; col--) {
						map[cir][col] = map[cir][col - 1];
					}
					map[cir][0] = tmp;
				}
			}
		} else {
			//반시계 방향
			for(int turnIdx = 0; turnIdx < k; turnIdx++) {
				for(int cir = x; cir <= N; cir += x) {
					int tmp = map[cir][0];
					for(int col = 0; col < M - 1; col++) {
						map[cir][col] = map[cir][col + 1];
					}
					map[cir][M - 1] = tmp;
				}
			}
		}
		
		
	}
	
	//인접 여부 확인
	//인접한게 하나라도 없으면 평균을 구해서 평균보다 크면 1을 빼고 작으면 1을 더한다
	static void check() {
		boolean isNear = false;
		
		int[][] mapTmp = new int[N + 1][M];
		for(int row = 1; row <= N; row++) {
			for(int col = 0; col < M; col++) {
				mapTmp[row][col] = map[row][col];
			}
		}
		
		//같은 원판 내 인접 확인
		for(int row = 1; row <= N; row++) {
			for(int col = 0; col < M; col++) {
				if(col == M - 1) {
					if(map[row][col] != 0 && map[row][col] == map[row][0]) {
						isNear = true;
						mapTmp[row][col] = 0;
						mapTmp[row][0] = 0;
					}
				} else if(map[row][col] != 0 && map[row][col] == map[row][col + 1]) {
					isNear = true;
					mapTmp[row][col] = 0;
					mapTmp[row][col + 1] = 0; 
				}
			}
		}
		
		//다른 원판끼리 인접 확인
		for(int row = 1; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(map[row][col] != 0 && map[row][col] == map[row + 1][col]) {
					isNear = true;
					mapTmp[row][col] = 0;
					mapTmp[row + 1][col] = 0; 
				}
			}
		}
		
		if(isNear) {
			map = mapTmp;
			return;
		}
		
		//인접한게 없으면 평균 처리
		if(!isNear) {
			double avg = getAvg();
			
			for(int row = 1; row <= N; row++) {
				for(int col = 0; col < M; col++) {
					if(map[row][col] == 0) continue;
					if((double)map[row][col] > avg) map[row][col]--;
					else if((double)map[row][col] < avg) map[row][col]++;
				}
			}
		}
	}
	
}
