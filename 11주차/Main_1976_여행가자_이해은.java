import java.io.*;
import java.util.*;

//시작시간 : ...
//종료시간 : ...
//걸린시간 : ...
//분 류    : 
//특이사항 :
//	- 최소 스패닝 트리 (크루스칼)
public class Main_1976_여행가자_이해은 {

	static int[] parents;
	static void make() {
		parents = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}
	
	//static Edge[] edgeList;
	static List<Edge> edgeList;
	
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		edgeList = new ArrayList<>();
		
		for(int row = 1; row <= N; row++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int col = 1; col <= N; col++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					edgeList.add(new Edge(row, col, 1));	
				}
			}
		}
		
		make();
		Collections.sort(edgeList);
		
		int cnt = 0;
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				if(++cnt == N - 1) break;
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int parentsIdx = find(Integer.parseInt(st.nextToken()));
		
		boolean answer = true;
		
		for(int m = 1; m < M; m++) {
			if(find(Integer.parseInt(st.nextToken())) != parentsIdx) {
				answer = false;
				break;
			}
		}
		
		if(answer) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

}
