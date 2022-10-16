package b13549;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[] visited = new boolean[100_001];
	
	static class Point implements Comparable<Point>{
		int pos;
		int time;
		
		@Override
		public int compareTo(Point o) {
			return this.time - o.time;
		}
		
		public Point(int pos, int time) {
			super();
			this.pos = pos;
			this.time = time;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(N,K,0));
		br.close();
	
	}
	public static int bfs(int N,int K, int time) {
		
		PriorityQueue<Point> q = new PriorityQueue<>();
		
		q.offer(new Point(N,0));
		while(!q.isEmpty()) {
			Point now = q.poll();
			visited[now.pos] = true;
			
			if(now.pos == K) {
				return now.time;
			}
			
			int nexttime = now.time + 1; // 걸었을 때 시간
			
			int next = now.pos * 2; //순간이동
			if(!(next < 0 || next > 100000 || visited[next] == true )) {
				visited[next] = true;
				q.offer(new Point(next,now.time));
			}
			
			next = now.pos + 1;
			if(!(next < 0 || next > 100000 || visited[next] == true   )) {
				q.offer(new Point(next,nexttime));
			}
			
			next = now.pos - 1;
			if(!( next < 0 || next > 100000 || visited[next] == true  )) {
				q.offer(new Point(next,nexttime));
			}
		}

		return 0;
		
	}
	
}
