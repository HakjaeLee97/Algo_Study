import java.io.*;
import java.util.*;

//시작시간 : 10-10 00:40
//종료시간 : 10-10 02:10
//걸린시간 : 01:30
//분 류    : 다익스트라?
//특이사항 :
//	- 명시되지 않은 노드와 간선들
public class Main_1446_지름길_이해은 {

	static int N, D;
	
	static class Node {
		int vertex;
		int weight;
		Node next;
		
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
		
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		Node[] adjList = new Node[10001];
		adjList[0] = new Node(D, D, adjList[0]);
		
		List<Integer> fromList = new ArrayList<>();
		List<Integer> toList = new ArrayList<>();
		
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			if(to <= D) {
				if(from <= D) {
					adjList[0] = new Node(from, from, adjList[0]);
					adjList[from] = new Node(to, weight, adjList[from]);
					adjList[to] = new Node(D, D - to, adjList[to]);
					
					for(int i = 0; i < fromList.size(); i++) {
						if(to <= fromList.get(i)) {
							adjList[to] = new Node(fromList.get(i), fromList.get(i) - to, adjList[to]);
						}
					}
					
					for(int i = 0; i < toList.size(); i++) {
						if(from >= toList.get(i)) {
							adjList[toList.get(i)] = new Node(from, from - toList.get(i), adjList[toList.get(i)]);
						}
					}
					
					fromList.add(from);
					toList.add(to);
				}
			}
		}
		
		int start = 0;
		int end = D;
		
		int[] dist = new int[10001];
		boolean[] v = new boolean[10001];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>((v1, v2)->v1.weight - v2.weight);
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node min = pq.poll();
			
			if(v[min.vertex]) continue;
			
			v[min.vertex] = true;
			
			for(Node temp = adjList[min.vertex]; temp != null; temp = temp.next) {
				if(!v[temp.vertex] && dist[temp.vertex] > dist[min.vertex] + temp.weight) {
					dist[temp.vertex] = dist[min.vertex] + temp.weight;
					pq.offer(new Node(temp.vertex, dist[temp.vertex]));
				}
			}
		}
		
		System.out.println(dist[end]);
		br.close();
	}

}
