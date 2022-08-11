package study;

import java.io.*;
import java.util.*;

public class BOJ_17140_이차원배열과연산 {

	static class MyArray implements Comparable<MyArray>{
		int index;
		int value;
		
		MyArray(int index, int value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(MyArray o) {
			//숫자 등장 횟수가 다르면
			if(Integer.compare(value, o.value) != 0)
				return Integer.compare(value, o.value);
			
			return Integer.compare(index, o.index);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] A = new int[101][101];
		int[][] ATemp = new int[101][101];
		int[][] Arr = new int[101][101];
		
		for(int row = 1; row <= 3; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int col = 1; col <= 3; col++) {
				A[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		//경과 시간
		int time = 0;
		
		//변화되는 행, 열의 수를 갱신하기 위한 변수
		int ROW = 3;
		int COL = 3;
		
		//배열 인덱스는 특정 숫자, 인덱스 내부 값은 그 특정 숫자의 등장 횟수를 의미
		//ex) cntArray[1] = 10, 1이 10번 등장함.
		int[] cntArray = new int[101];
		List<MyArray> cntList = new ArrayList<>();
		
		while(true) {
			//탈출 조건
			if(A[r][c] == k) break;
			if(time == 100) {
				time = -1;
				break;
			}
			
			time ++;
			
			if(ROW >= COL) {				
				ATemp = new int[101][101];
				int maxCol = 0;
				for(int row = 1; row <= ROW; row++) {
					//초기화
					cntList = new ArrayList<>();
					cntArray = new int[101];
					
					//한 행의 데이터 입력
					for(int col = 1; col <= COL; col++) {
						cntArray[A[row][col]]++;
					}
					
					//입력된 데이터를 등장 횟수, 인덱스 순으로 오름차순 정렬
					for(int i = 0; i <= 100; i++) {
						cntList.add(new MyArray(i, cntArray[i]));
					}
					//설정한 Comparable 조건에 따라 정렬
					Collections.sort(cntList);
					
					int colCnt = 1;
					for(int i = 0; i < cntList.size(); i++) {
						if(cntList.get(i).index != 0 && cntList.get(i).value != 0) {
							if(colCnt == 101) break;
							ATemp[row][colCnt++] = cntList.get(i).index;
							ATemp[row][colCnt++] = cntList.get(i).value;
						}
					}
					
					if(maxCol < colCnt - 1) maxCol = colCnt - 1;
				}
				
				A = ATemp;
				COL = maxCol;
			} else {
				ATemp = new int[101][101];
				int maxRow = 0;
				for(int col = 1; col <= COL; col++) {
					//초기화
					cntList = new ArrayList<>();
					cntArray = new int[101];
					
					//한 열의 데이터 입력
					for(int row = 1; row <= ROW; row++) {
						cntArray[A[row][col]]++;
					}
					
					//입력된 데이터를 등장 횟수, 인덱스 순으로 오름차순 정렬
					for(int i = 0; i <= 100; i++) {
						cntList.add(new MyArray(i, cntArray[i]));
					}
					
					//설정한 Comparable 조건에 따라 정렬
					Collections.sort(cntList);
					
					int rowCnt = 1;
					for(int i = 0; i < cntList.size(); i++) {
						if(cntList.get(i).index != 0 && cntList.get(i).value != 0) {
							if(rowCnt == 101) break;
							ATemp[rowCnt++][col] = cntList.get(i).index;
							ATemp[rowCnt++][col] = cntList.get(i).value;
						}
					}
					
					if(maxRow < rowCnt - 1) maxRow = rowCnt - 1;
				}
				
				A = ATemp;
				ROW = maxRow;
			}
		}
		
		
		System.out.println(time);
	}

}
