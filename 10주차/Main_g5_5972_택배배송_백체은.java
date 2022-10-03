package a10월1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_g5_5972_택배배송 {
	
	static class Node {
		int v;
		int cost;
		
		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
	
	static ArrayList<Node>[] graph;
	static boolean[] visited;
	static int[] D;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		D = new int[N+1];
		visited = new boolean[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			D[i] = 987654321;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graph[A].add(new Node(B, C));
			graph[B].add(new Node(A, C));
		}
		br.close();
		
		// 다익스트라
		PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		q.offer(new Node(1, 0));
		D[1] = 0;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			if(!visited[now.v]) {
				visited[now.v] = true;
			}
			for (Node next : graph[now.v]) {
				if(!visited[next.v] && D[next.v] > now.cost + next.cost) {
					D[next.v] = now.cost + next.cost;
					q.offer(new Node(next.v, D[next.v]));
				}
			}
		}
		System.out.println(D[N]);
	}
}
