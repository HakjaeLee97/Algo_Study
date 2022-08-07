package study;

import java.util.*;
import java.io.*;

public class BOJ_17779_게리맨더링2 {

	//구역 번호를 표시하기 위한 배열
		static int[][] city = new int[21][21];
		static int[][] divide = new int[21][21];
		static int n;
		
		//영역 내부에 경계선이 들어가 있는 경우 true, 벗어나있는 경우 false
		static boolean check(int r, int c) {
			if(r < 1 || r > n || c < 1 || c > n) {
				return false;
			}
			return true;
		}
		
		//1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
		static int getPartOne(int x, int y, int d1, int d2) {
			int sum = 0;
			
			for(int row = 1; row < x + d1; row++) {
				for(int col = 1; col <= y; col++) {
					if(divide[row][col] != 5) sum += city[row][col];
				}
			}
			
			return sum;
		}
		
		//2번 선거구: 1 ≤ r ≤ x+d2, y + 1 ≤ c ≤ N
		static int getPartTwo(int x, int y, int d1, int d2) {
			int sum = 0;
			
			for(int row = 1; row <= x + d2; row++) {
				for(int col = y + 1; col <= n; col++) {
					if(divide[row][col] != 5) sum += city[row][col];
				}
			}
			
			return sum;
		}
		
		//3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
		static int getPartThree(int x, int y, int d1, int d2) {
			int sum = 0;
				
			for(int row = x + d1; row <= n; row++) {
				for(int col = 1; col < y - d1 + d2; col++) {
					if(divide[row][col] != 5) sum += city[row][col];
				}
			}
				
			return sum;
		}
		
		//4번 선거구: x+d2+1 ≤ r ≤ N, y-d1+d2 ≤ c ≤ N
		static int getPartFour(int x, int y, int d1, int d2) {
			int sum = 0;
				
			for(int row = x + d2 + 1; row <= n; row++) {
				for(int col = y - d1 + d2; col <= n; col++) {
					if(divide[row][col] != 5) sum += city[row][col];
				}
			}
				
			return sum;
		}
		
		//5번 선거구 지정
		static boolean setPartFive(int x, int y, int d1, int d2) {
			divide = new int[21][21];
			divide[x][y] = 5;
			
			//(x, y), (x+1, y-1), ..., (x+d1, y-d1)
			int initR = x;
			int initC = y;
			
			for(int k = 0; k < d1; k++) {
				int nr = initR + 1;
				int nc = initC - 1;
				if(!check(nr, nc)) return false;
				initR = nr;
				initC = nc;
				
				for(int col = nc; col < y; col++) {
					divide[nr][col] = 5;
				}
			}
			
			//(x, y), (x+1, y+1), ..., (x+d2, y+d2)
			initR = x;
			initC = y;
			
			for(int k = 0; k < d2; k++) {
				int nr = initR + 1;
				int nc = initC + 1;
				if(!check(nr, nc)) return false;
				initR = nr;
				initC = nc;
				
				for(int col = y; col <= nc; col++) {
					divide[nr][col] = 5;
				}
			}
			
			//(x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
			initR = x + d1;
			initC = y - d1;
			
			for(int k = 0; k < d2; k++) {
				int nr = initR + 1;
				int nc = initC + 1;
				if(!check(nr, nc)) return false;
				initR = nr;
				initC = nc;
				
				for(int col = nc; col < y - d1 + d2; col++) {
					divide[nr][col] = 5;
				}
			}
			
			//(x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
			initR = x + d2;
			initC = y + d2;
			
			for(int k = 0; k < d1; k++) {
				int nr = initR + 1;
				int nc = initC - 1;
				if(!check(nr, nc)) return false;
				initR = nr;
				initC = nc;
				
				for(int col = y - d1 + d2; col <= nc; col++) {
					divide[nr][col] = 5;
				}
			}
			
			return true;
		}
		
	public static void main(String[] args) {
		//System.setIn(new FileInputStream("res/input_boj_17779.txt"));
				Scanner sc = new Scanner(System.in);
				
				n = sc.nextInt();
				int totalSum = 0;
				
				for(int row = 1; row <= n; row++) {
					for(int col = 1; col <= n; col++) {
						city[row][col] = sc.nextInt();
						totalSum += city[row][col];
					}
				}
				
				int resultMin = Integer.MAX_VALUE;
				
				for(int x = 1; x <= n; x++) {
					for(int y = 2; y <= n; y++) {
						for(int d1 = 1; d1 <= n; d1++) {
							for(int d2 = 1; d2 <= n; d2++) {
								if(setPartFive(x, y, d1, d2)) {
									int sum1 = getPartOne(x, y, d1, d2);
									int sum2 = getPartTwo(x, y, d1, d2);
									int sum3 = getPartThree(x, y, d1, d2);
									int sum4 = getPartFour(x, y, d1, d2);
									int sum5 = totalSum - (sum1 + sum2 + sum3 + sum4);
									
									if(sum1 * sum2 * sum3 * sum4 * sum5 != 0) {
										int[] sumArr = {sum1, sum2, sum3, sum4, sum5};
										Arrays.sort(sumArr);
										int maxminDif = sumArr[4] - sumArr[0];
										if(resultMin > maxminDif) resultMin = maxminDif;
									}
								}
							}
						}
					}
				}
				
				System.out.println(resultMin);
	}

}
