package study;

import java.io.*;
import java.util.*;

public class BOJ_17140_이차원배열과연산 {

	//등장 횟수 오름 차순 정렬, 등장 횟수가 같을 경우 숫자 오름차순 정렬
	//Comparable Interface 로 기능 구현
	static class MyArray implements Comparable<MyArray>{
		int index; // 숫자
		int value; // 해당 숫자의 등장 횟수
		
		MyArray(int index, int value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(MyArray o) {
			//숫자 등장 횟수가 다르면 등장 횟수로 오름차순 정렬
			if(Integer.compare(value, o.value) != 0)
				return Integer.compare(value, o.value);
			
			//숫자 등장 횟수가 동일하면 숫자로 오름차순 정렬
			return Integer.compare(index, o.index);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		//데이터 입력
		//A[r][c] = k 면 종료
		int r = Integer.parseInt(st.nextToken()); // 행
		int c = Integer.parseInt(st.nextToken()); // 열
		int k = Integer.parseInt(st.nextToken()); // k 값
		
		//인덱스가 1부터 시작하므로 100 + 1 크기로 배열 생성
		int[][] A = new int[101][101];     //원본 배열
		int[][] ATemp = new int[101][101]; //정렬 데이터 저장할 새로운 배열
		
		//숫자별 등장 횟수를 구하는 과정에서 원본 배열 A 가 훼손되면 안됨.
		//A 배열 숫자 별 등장 횟수 구해서 ATemp 배열에 저장 -> 한 과정이 끝나면 A 배열에 ATemp 배열 입력 -> 반복
		
		//"행 또는 열의 크기가 100을 넘어가는 경우 처음 100개를 제외한 나머지를 버린다."
		//=> ATemp 배열을 문제에서 요구하는 가장 큰 배열 크기로 생성하고, ROW, COL 변수를 통해 실제 크기 관리
		
		//초기 데이터 입력
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
					
					//한 행의 데이터를 입력받으면서 동시에 숫자 카운트
					for(int col = 1; col <= COL; col++) {
						cntArray[A[row][col]]++;
					}
					
					//숫자와 그 숫자의 정보를 동시에 저장할 리스트를 만들어서 데이터 추가
					for(int i = 0; i <= 100; i++) {
						cntList.add(new MyArray(i, cntArray[i]));
					}
					//설정한 Comparable 조건에 따라 정렬
					Collections.sort(cntList);
					
					int colCnt = 1;
					for(int i = 0; i < cntList.size(); i++) {
						//숫자 0 이나 숫자의 등장 횟수가 0인 경우는 무의미한 데이터이므로 추가하지 않음
						if(cntList.get(i).index != 0 && cntList.get(i).value != 0) {
							//ATemp 배열 범위를 초과하면 멈춤
							if(colCnt == 101) break;
							ATemp[row][colCnt++] = cntList.get(i).index;
							ATemp[row][colCnt++] = cntList.get(i).value;
						}
					}
					
					if(maxCol < colCnt - 1) maxCol = colCnt - 1;
				}
				
				//모든 과정이 끝나면 배열 복사, 원본 배열 갱신
				A = ATemp;
				//모든 행을 순회하면서 가장 큰 열의 수를 해당 배열의 열 개수로 갱신함
				COL = maxCol;
			} else {
				ATemp = new int[101][101];
				int maxRow = 0;
				for(int col = 1; col <= COL; col++) {
					cntList = new ArrayList<>();
					cntArray = new int[101];
					
					for(int row = 1; row <= ROW; row++) {
						cntArray[A[row][col]]++;
					}
					
					for(int i = 0; i <= 100; i++) {
						cntList.add(new MyArray(i, cntArray[i]));
					}
					
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
