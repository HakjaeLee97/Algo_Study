package study;

import java.io.*;
import java.util.*;

public class BOJ_15684_사다리조작 {

	static int N; //세로선 개수
	static int M; //가로선 개수
	static int H; //가로선을 놓을 수 있는 위치의 개수
	static int ans;
	//세로선 1 ~ 5 까지
	//1,1 과 1, 2 가 연결되어 있음을 나타내려면
	//(1,1) 인덱스에 true (1, 1)과 (1, 1 + 1) 이 이어져있음을 의미
	//가로선 6개인 경우 (1~6. 000)
	//세로선 5개인 경우 (000, 1 ~ 4)
	//정보 배열은 [M + 1][N] 로 만들고 인덱스는 1부터 활용
	static boolean[][] connectInformationArray;
	static boolean[][] connectInformationArrayTemp;
	static int minResult;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		connectInformationArray = new boolean[H + 1][N];
		connectInformationArrayTemp = new boolean[H + 1][N];
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connectInformationArray[a][b] = true;
			connectInformationArrayTemp[a][b] = true;
		}
		
		ans = -1;
		minResult = Integer.MAX_VALUE;
		subset(0, 1, 1);
		if(minResult != Integer.MAX_VALUE) ans = minResult;
		System.out.println(ans);
	}
	
	static void subset(int depth, int startRow, int startCol) {
		if(isOK()) {
			if(minResult > depth) minResult = depth;
			return;
		}
		
		if(startRow >= H + 1) return;
		
		if(depth == 3) return;
		
		if(connectInformationArray[startRow][startCol]) {
			if(startCol + 2 >= N) subset(depth, startRow + 1, 1);
			else subset(depth, startRow, startCol + 2);
		} else {
			connectInformationArray[startRow][startCol] = true;
			if(startCol + 2 >= N) subset(depth + 1, startRow + 1, 1);
			else subset(depth + 1, startRow, startCol + 2);
			connectInformationArray[startRow][startCol] = false;
			if(startCol + 1 >= N) subset(depth, startRow + 1, 1);
			else subset(depth, startRow, startCol + 1);
		}
	}
	
	//i 시작해서 i 도착하는지 여부를 반환하는 함수
	static boolean isOK() {
		for(int n = 1; n <= N; n++) {
			if(n != searchEndPoint(n)) {
				return false;
			}
		}
		return true;
	}
	
	//START 입력시 도착 위치 END 를 반환하는 함수
	static int searchEndPoint(int start) {
		//현재 플레이어 위치
		int curRow = 0;
		int curCol = start; 
		
		while(true) {
			curRow++;
			if(curRow > H) break;
			if(curCol - 1 >= 1 && connectInformationArray[curRow][curCol - 1] == true) {
				curCol--;
			} else if(curCol < N && connectInformationArray[curRow][curCol] == true) {
				curCol++;
			}
		}
		
		return curCol;
	}
}