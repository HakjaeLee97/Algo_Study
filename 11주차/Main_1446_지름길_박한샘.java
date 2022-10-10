
import java.util.*;
import java.io.*;

public class Main {
	

	static class Node  implements Comparable<Node>{
		
		int from, to, weight;

		public Node(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			
			return weight - o.weight;
		}
		

	
	}
	
	
	public static void main(String[] args) throws Exception{
		
		//전체 최단거리를 dijikstra로 구한 뒤, 가장 가까운 값 뽑아서 + 해줘볼까? ㅠ
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		

		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		
		int[] dist = new int[10001];
		List<Node> list = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			
			st = new StringTokenizer(br.readLine()," ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			if(to > D) continue;
			
			list.add(new Node(from,to,weight));
			
		}
		
		
		int INF = Integer.MAX_VALUE;
		Arrays.fill(dist, INF);
		dist[0] = 0;
		
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		int min = INF;
		for(int i=0;i<list.size();i++) {
			Node node = list.get(i);
			
			if(node.from==0) {
				if(min < node.weight) {
					min = node.weight;
					pq.offer(new Node(0,node.to,node.weight));
					
				}
			}
		}
		
		while(!pq.isEmpty()) {
			
			Node node = pq.poll();
			
			for(int i=0;i<list.size();i++) {
				if(list.get(i).from==node.to) {
					
				}
			}
			
		}
		
		
		
	}
}
