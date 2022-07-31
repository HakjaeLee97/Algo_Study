package study;

import java.util.Scanner;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;

class SWEA_1249
{
    public static class Node{
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	//데이터가 들어있는 배열
	static int[][] arr = new int[100][100];
	//특정 칸 까지 이동하는데 소요된 최소 복구 시간 기록 배열
	static int[][] depArr = new int[100][100];
	
	//상 우 하 좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			int n = sc.nextInt();
			Queue<Node> q = new LinkedList<>();
			
			//데이터 입력
			for(int row = 0; row < n; row++) {
				String str = sc.next();
				
				for(int col = 0; col < n; col++) {
					arr[row][col] = str.charAt(col) - '0';
					depArr[row][col] = Integer.MAX_VALUE;
				}
			}
			
			//출발지와 도착지는 비용이 0
			depArr[0][0] = 0;
			
			/////////////여기까지 데이터 입력 받기 완료
			
			//Dfs
			//시작 노드를 큐에 넣고
			//====================================== while(!q.isEmpty)
			//큐에서 원소를 빼고,
			//뺀 노드에 대한 작업을 수행하고
			//뺀 노드에 인접한 노드들 중 조건에 맞는 노드를 큐에 집어넣고
			//큐가 비어질 때 까지 반복
			
			q.add(new Node(0, 0));
			
			//큐가 빌 때 까지 반복
			while(!q.isEmpty()) {
				int idx_x = q.peek().x;
				int idx_y = q.peek().y;
				//Dequeue
				q.poll();
				
				for(int d = 0; d < 4; d++) {
					//Dequeue 한 좌표의 상하좌우 인접 좌표 탐색
					int next_x = idx_x + dx[d];
					int next_y = idx_y + dy[d];
					
					if(next_x >= 0 && next_x < n && next_y >= 0 && next_y < n) {
						if(depArr[next_x][next_y] > depArr[idx_x][idx_y] + arr[next_x][next_y]) {
							depArr[next_x][next_y] = depArr[idx_x][idx_y] + arr[next_x][next_y];
							q.add(new Node(next_x, next_y));
						}
					}
				}
			}
			
			System.out.println("#" + t + " " + depArr[n -1][n -1]);
		}
	}
}