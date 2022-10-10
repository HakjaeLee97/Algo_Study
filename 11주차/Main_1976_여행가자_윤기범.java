import java.util.*;
import java.io.*;

public class Main {
	public static int N, M;
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> graph2 = new ArrayList<ArrayList<Integer>>();
	public static int[] arr;
	public static boolean[] visited;
	public static boolean flag = true;
	
	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			for(int i = 0; i < graph2.get(x).size(); i++) {
				int y = graph2.get(x).get(i);
				if (!visited[y]) {
					q.offer(y);
					visited[y] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		// 초기화
		arr = new int[M + 1];
		for(int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
			graph2.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if(a == 1) {
					graph.get(i).add(j);
					graph.get(j).add(i);
				}
			}
		}
		
		// 중복제거
		for(int i = 0; i < N + 1; i++)  {
			HashSet<Integer> arr2 = new HashSet<Integer>(graph.get(i));
			ArrayList<Integer> arr3 = new ArrayList<>(arr2);
			for(int j = 0; j < arr3.size(); j++) {
				graph2.get(i).add(arr3.get(j));
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
 		for(int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
 		visited = new boolean[N + 1];
 		bfs(arr[0]);
 		
		for(int i = 0; i < M; i++) {
			if(visited[arr[i]] == false)
				flag = false;
		}
		
		if(flag)
			System.out.println("YES");
		else
			System.out.println("NO");
	}
}
