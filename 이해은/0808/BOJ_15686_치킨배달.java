package study;

import java.io.*;
import java.util.*;

class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_15686_치킨배달 {

	static int n; //도시 NxN 크기 N
	static int m; //고를 치킨 집의 수 M
	static int[][] map; //지도 정보
	static List<Point> house; //가정집 좌표 리스트
	static List<Point> chicken; //치킨집 좌표 리스트
	static int answer; //최소 도시 치킨 거리
	static boolean[] visited; //방문 여부 확인
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	       StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	       
	       n = Integer.parseInt(st.nextToken());
	       m = Integer.parseInt(st.nextToken());
	       map = new int[n][n];
	       house = new ArrayList<>();
	       chicken = new ArrayList<>();
	       
	       //map 데이터 초기화와 동시에 가정집과 치킨집 좌표 리스트에 저장 
	       for(int row = 0; row < n; row++) {
	    	   st = new StringTokenizer(br.readLine(), " ");
	    	   for(int col = 0; col < n; col++) {
	    		   map[row][col] = Integer.parseInt(st.nextToken());
	    		   
	    		   if(map[row][col] == 1) {
	    			   house.add(new Point(row, col));
	    		   } else if(map[row][col] == 2) {
	    			   chicken.add(new Point(row, col));
	    		   }
	    	   }
	       }
	       
	       //최솟값을 구하기 위해 Integer 최대값을 초기값으로 설정
	       answer = Integer.MAX_VALUE;
	       //치킨집 방문 여부 확인을 위해 치킨집 개수만큼 방문 체크 배열 초기화
	       visited = new boolean[chicken.size()];
	       
	       //DFS
	       DFS(0, 0);
	       
	       System.out.println(answer);
	}
	
	static void DFS(int start, int cnt) {
		if(cnt == m) {
			int cityDistance = 0;
			
			for(int i = 0; i < house.size(); i++) {
				int temp = Integer.MAX_VALUE;
				
				//temp : m 개의 선정된 치킨집중 집과 가장 가까운 거리
				for(int j = 0; j < chicken.size(); j++) {
					if(visited[j]) {
						int distance = Math.abs(house.get(i).x - chicken.get(j).x) + Math.abs(house.get(i).y - chicken.get(j).y);
						temp = Math.min(temp, distance);
					}
				}
				
				//모든 가정집을 순회하며 도시 전체의 치킨 거리를 획득함.
				cityDistance += temp;
			}
			
			//현재 선정된 노드가 지금까지 탐색한 노드와 비교하여 최솟값일 경우 갱신
			answer = Math.min(cityDistance, answer);
		}
		
		for(int i = start; i < chicken.size(); i++) {
			visited[i] = true;
			DFS(i + 1, cnt + 1);
			visited[i] = false;
		}
	}
}