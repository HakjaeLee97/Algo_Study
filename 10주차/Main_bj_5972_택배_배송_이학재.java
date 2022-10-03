package b5972;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 50000*1000+1;
	
	public static class Node implements Comparable<Node>{

		int d;
		int cost;
		
		public Node(int d, int cost) {
			this.d = d;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	
	static ArrayList<Node>[] list;
	static boolean[] visited;
	static int[] dist;
	static int N,M;
	
	public static void main(String[] args) throws Exception {
		
		//다익스트라
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//헛간 5만개, 길 5만개 -> 인접 행렬 사용 시 메모리 초과
		
		list = new ArrayList[N+1];
		
		//다익스트라 초기화
		for(int i = 1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		
		//가중치 입력
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b,c));
			list[b].add(new Node(a,c));
			
		}
		
		visited = new boolean[N+1];
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		dijkstra(1);
		System.out.println(dist[N]);
		br.close();
	}
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.offer(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.d]) continue;
			visited[cur.d] = true;
			
			for(int i =0; i<list[cur.d].size(); i++) {
				Node next = list[cur.d].get(i);
				if(dist[next.d]> dist[cur.d] + next.cost) {
					dist[next.d]= dist[cur.d]+ next.cost;
					pq.offer(new Node(next.d, dist[next.d]));
				}
			}
			
		}
	
	}

}
