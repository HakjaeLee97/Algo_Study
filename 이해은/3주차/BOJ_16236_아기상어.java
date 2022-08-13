package study;


import java.io.*;
import java.util.*;

public class BOJ_16236_아기상어 {
	
	static int[][] map;
	static boolean[][] visit;
	static int N;
	static int curSharkRow;
	static int curSharkCol;
	static int time;
	static int curEatFish;      //현재 상어가 먹은 물고기
	static int sizeShark;       //현재 상어의 사이즈
	static boolean findEatFish; //현 탐색에서 먹을 물고기를 찾았는지
	static int findDistance;    //먹을 물고기를 찾은 최초 최단 거리
	static int rowMinFish;      //발견한 먹이의 row 좌표
	static int colMinFish;      //발견한 먹이의 col 좌표
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		//초기화
		curSharkRow = 0;      //현 시점 상어 row 좌표
		curSharkCol = 0;      //현 시점 상어 col 좌표
		time = 0;             //경과 시간
		curEatFish = 0;       //현재 크기에서 먹은 물고기 수
		sizeShark = 2;        //현재 상어 크기
		findEatFish = false;  //다음 이동 과정에서 먹을 수 있는 물고기가 있는지
		findDistance = 0;     //먹을 수 있는 물고기 최소 거리
		
		//데이터 입력
		for(int row = 0; row < N; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col] == 9) {
					curSharkRow = row;
					curSharkCol = col;
				}
			}
		}
		
		while(true) {
			//int curSharkRowTemp = curSharkRow;
			//int curSharkColTemp = curSharkCol;
			
			findEatFish = false;
			findDistance = 0;
			rowMinFish = Integer.MAX_VALUE;
			colMinFish = Integer.MAX_VALUE;
			visit = new boolean[N][N];
			BFS(curSharkRow, curSharkCol, 0);
			
			if(findEatFish) {
				map[curSharkRow][curSharkCol] = 0;
				curSharkRow = rowMinFish;
				curSharkCol = colMinFish;
				curEatFish ++;
				
				//상어의 성장
				if(sizeShark == curEatFish) {
					curEatFish = 0;
					sizeShark++;
				}
				
				map[curSharkRow][curSharkCol] = 0;
				
				time += findDistance;
			}
			//한번의 BFS 에서 잡은 물고기가 0마리인 경우 탈출
			//위치가 이전과 그대로인 경우
			//if(curSharkRowTemp == curSharkRow && curSharkColTemp == curSharkCol) break;
			if(!findEatFish) break;
		}
		
		System.out.println(time);
	}
	
	static void BFS(int row, int col, int distance) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visit[row][col] = true;
		q.offer(new int[] {row, col, distance});
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			row = rc[0];
			col = rc[1];
			distance = rc[2];
			
			//최초 먹이를 발견한 거리보다 더 먼 거리 탐색을 시작할 경우 탐색 중지
			if(findEatFish && distance > findDistance) break;
			
			//이동한 후에 먹이가 있는 지 확인
			if(map[row][col] > 0 && map[row][col] < sizeShark) {
				findEatFish = true;
				findDistance = distance;
				
				//동일 거리 상에서 더 최적의 먹이가 있을 경우 해당 값 갱신
				if(rowMinFish > row) {
					//더 위쪽 행이 존재할 경우 갱신
					rowMinFish = row;
					colMinFish = col;
				} else if(rowMinFish == row) {
					//같은 행일 때 더 왼쪽 열이 존재할 경우 갱신
					if(colMinFish > col) colMinFish = col;
				}
			}
			
			//갈 수 있는 지 여부만 확인하고 이동
			for(int d = 0; d < 4; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc] && map[nr][nc] <= sizeShark) {
					visit[nr][nc] = true;
					q.offer(new int[] {nr, nc, distance + 1});
				}
			}
		}
	}
}
