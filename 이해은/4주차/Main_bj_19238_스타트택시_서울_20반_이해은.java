package a0819.camp;

import java.io.*;
import java.util.*;

public class Main_bj_19238_스타트택시_서울_20반_이해은 {

	static int N, M, E, answer;
	static int oilBeforeGetPassengers;
	static int oilBeforeDestinationPoint;
	static int minDistanceDst;
	static int[][] map;
	static boolean[][] v;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static Map<Point, Point> passengersHashMap;
	
	static class Point implements Comparable<Point> {
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Point o) {
			if(row != o.row) return Integer.compare(row, o.row);
			return Integer.compare(col, o.col);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		passengersHashMap = new HashMap<>();
		
		for(int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int col = 1; col <= N; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int texiStart_row = Integer.parseInt(st.nextToken());
		int texiStart_col = Integer.parseInt(st.nextToken());
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int startRow = Integer.parseInt(st.nextToken());
			int startCol = Integer.parseInt(st.nextToken());
			int endRow = Integer.parseInt(st.nextToken());
			int endCol = Integer.parseInt(st.nextToken());
			
			passengersHashMap.put(new Point(startRow, startCol),  new Point(endRow, endCol));
			map[startRow][startCol] = 2;
		}
		
		answer = 0;
		int m = 0;
		for(m = 0; m < M; m++) {
			//방문 배열 v 초기화
			v = new boolean[N + 1][N + 1];
			Point targetPassenger = searchPassengers(texiStart_row, texiStart_col, 0);
			if(targetPassenger == null) {
				answer = -1;
				break;
			}
			
			//승객을 태우러 가기 전까지 연료 소비
			E -= oilBeforeGetPassengers;

			//방문 배열 v 초기화
			v = new boolean[N + 1][N + 1];
			
			Point dstPoint = minDistanceDstPoint(targetPassenger.row, targetPassenger.col, 0);
			
			if(dstPoint == null) {
				answer = -1;
				break;
			}
			
			E -= oilBeforeDestinationPoint;
			E += oilBeforeDestinationPoint * 2;
			
			//태웠던 승객을 빈 칸으로 만듦
			map[targetPassenger.row][targetPassenger.col] = 0;

			texiStart_row = dstPoint.row;
			texiStart_col = dstPoint.col;
		}
		
		if(answer == - 1 || m != M)
			System.out.println(-1);
		else
			System.out.println(E);
	}
	
	//손님을 태운 후 목적지까지 최단 경로를 탐색
	
	//현재 택시 위치에서 가장 가까운 손님을 탐색 (BFS)
	//탐색된 승객의 row, col 좌표를 반환
	//승객이 탐색되지 않았거나, 현재 들고 있는 연료내에서 찾을 수 없을 경우 null 을 반환
	static Point searchPassengers(int row, int col, int depth) {
		//같은 거리 내 승객들의 집합을 담을 리스트
		List<Point> sameDistancePassengersList = new ArrayList<>();
		//승객을 태우기 전까지 소비할 연료량
		oilBeforeGetPassengers = 0;
		//승객 탐색 완료 여부 플래그 변수
		boolean searchComplete = false;
		
		ArrayDeque<int[]> q = new ArrayDeque<>();
		v[row][col] = true;
		q.offer(new int[] {row, col, depth});
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			row = rc[0];
			col = rc[1];
			depth = rc[2];
			
			//최단 경로 이상의 거리를 탐색하려고 할 경우
			if(searchComplete == true && oilBeforeGetPassengers != depth) {
				//최단 경로 내 승객들 중 최적의 승객을 선정
				Collections.sort(sameDistancePassengersList);
				return new Point(sameDistancePassengersList.get(0).row, sameDistancePassengersList.get(0).col);
			}
			
			//현재 연료보다 최단 경로의 거리가 커질 경우
			if(depth > E) {
				return null;
			}
			
			//최단 경로에서 승객이 발견된 경우
			if(map[row][col] == 2) {
				searchComplete = true;
				//승객을 태우기까지 소비할 연료 갱신
				oilBeforeGetPassengers = depth;
				//같은 거리에 있는 승객들을 취합
				sameDistancePassengersList.add(new Point(row, col));
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				
				if(nr >= 1 && nr <= N && nc >= 1 && nc <= N && !v[nr][nc] && map[nr][nc] != 1) {
					v[nr][nc] = true;
					q.offer(new int[] {nr, nc, depth + 1});
				}
			}
		}
		
		if(!sameDistancePassengersList.isEmpty()) {
			Collections.sort(sameDistancePassengersList);
			return new Point(sameDistancePassengersList.get(0).row, sameDistancePassengersList.get(0).col);
		}
		
		return null;
	}

	//출발지를 입력하면 해당하는 목적지 좌표를 반환
	//내부에서 최단 경로 값 갱신(BFS)
	static Point minDistanceDstPoint(int row, int col, int depth) {
		//도착 지점 확인
		int dstPoint_row = passengersHashMap.get(new Point(row, col)).row;
		int dstPoint_col = passengersHashMap.get(new Point(row, col)).col;
		
		oilBeforeDestinationPoint = 0;
		ArrayDeque<int[]> q = new ArrayDeque<>();
		v[row][col] = true;
		q.offer(new int[] {row, col, depth});
		while(!q.isEmpty()) {
			int[] rc = q.poll();
			row = rc[0];
			col = rc[1];
			depth = rc[2];
			
			//승객을 태우고 도착지까지 가는 동안 연료가 부족할 경우
			if(depth > E) {
				return null;
			}
			
			//목적지를 발견했을 경우 최단 경로
			if(row == dstPoint_row && col == dstPoint_col) {
				oilBeforeDestinationPoint = depth;
				return new Point(dstPoint_row, dstPoint_col);
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				
				if(nr >= 1 && nr <= N && nc >= 1 && nc <= N && !v[nr][nc] && map[nr][nc] != 1) {
					v[nr][nc] = true;
					q.offer(new int[] {nr, nc, depth + 1});
				}
			}
		}
		
		//목적지를 발견하지 못했을 경우
		return null;
	}
}
