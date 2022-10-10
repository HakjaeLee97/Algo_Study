import java.util.*;
import java.io.*;

public class Main {
	public static int N, D;
	public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	public static int[] d;
	public static final int INF = (int)1e9;

	static class Node implements Comparable<Node>{
		public int node;
		public int dist;
		
		public Node(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}		
		
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}

		@Override
		public String toString() {
			return "[node=" + node + ", dist=" + dist + "]";
		}
		
		
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int dist = node.dist;
			int now = node.node;
			
			if(d[now] < dist)
				continue;
			
			for(int i = 0; i < graph.get(now).size(); i++) {
				int cost = dist + graph.get(now).get(i).dist;
				if(cost < d[graph.get(now).get(i).node]) {
					d[graph.get(now).get(i).node] = cost;
					pq.offer(new Node(graph.get(now).get(i).node, cost));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		 
		// 초기화
		for(int i = 0; i < D + 1; i++) { 
			graph.add(new ArrayList<>());
		}
		d = new int[D + 1];
		for(int i = 0; i < D + 1; i++) {
			d[i] = INF;
		}
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			
			// 도착 지점이 고속도로의 길이보다 크다면 graph에 추가 하지 않음
			if (end > D)
				continue;
			graph.get(start).add(new Node(end, length));
			
			// 지름길이 아닌 길도 graph에 추가
			if(!graph.get(start).contains(new Node(end, end-start))) {
				graph.get(start).add(new Node(end, end-start));
			}
		}
		
		// graph의 현재 위치에 다음 칸 까자의 거리 추가
		for(int i = 0; i < D; i++) {
			graph.get(i).add(new Node(i + 1, 1));
		}
		
		dijkstra(0);
		System.out.println(d[D]);
	}
}
