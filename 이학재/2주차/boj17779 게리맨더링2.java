package b17779;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] map, zone;
	static int[] population;
	static int answer, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		answer = Integer.MAX_VALUE;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 모든 구역을 순회
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				// 5번 선거구(기준) 설정
				for (int L = 1; L <= N; L++) {
					for (int R = 1; R <= N; R++) {
						// 5번 구역의 유효한 범위인지 확인
						if (y - L <= 0 || x + L + R > N || y + R > N) continue;
						
						// 구역별 인구수 구하기
						sumOfArea(x, y, L, R);
						
						// 최대인구수와 최소인구수의 차이를 구한 뒤,
						// 차이값이 최소가 되는 값을 구합니다.
						answer = Math.min(answer, maxOfDiff());		
					}
				}
			}
		}
		// 정답출력
		System.out.println(answer);
	}

	private static void sumOfArea(int x, int y, int L, int R) {
		// 1~5 선거구별 인구수
		population = new int[6];
		zone = new int[N+1][N+1];
		
		// 5 구역
		int left = 0, right = 0;
		boolean turnLeft = true, turnRight = true;
		for(int row = x; row <= x + L + R; row++) {
			for(int col= y + left; col<= y+ right; col++) {
				zone[row][col] = 5;
				population[5] += map[row][col];
			}

			if(left == -L) turnLeft = !turnLeft;
			if(right == R) turnRight = !turnRight;
			
			if(turnLeft) left--;
			else left++;
			
			if(turnRight) right++;
			else right--;
		}
		
		// 1 구역
		for (int i = 1; i < x+L; i++) {
			for (int j = 1; j <= y; j++) {
				if (zone[i][j] != 5) {
					population[1] += map[i][j];
				}
			}
		}
		// 2 구역
		for (int i = 1; i <= x+R; i++) {
			for (int j = y+1; j <= N; j++) {
				if (zone[i][j] != 5) {
					population[2] += map[i][j];
				}
			}
		}
		// 3 구역
		for (int i = x+L; i <= N; i++) {
			for (int j = 1; j < y-L+R; j++) {
				if (zone[i][j] != 5) {
					population[3] += map[i][j];
				}
			}
		}
		// 4 구역
		for (int i = x+R+1; i <= N; i++) {
			for (int j = y-L+R; j <= N; j++) {
				if (zone[i][j] != 5) {
					population[4] += map[i][j];
				}
			}
		}
	}

	private static int maxOfDiff() {
		int maxVal = Integer.MIN_VALUE;
		int minVal = Integer.MAX_VALUE;
		
		for (int i = 1; i <= 5; i++) {
			maxVal = Math.max(maxVal, population[i]);
			minVal = Math.min(minVal, population[i]);
		}
		return maxVal-minVal;
	}
}