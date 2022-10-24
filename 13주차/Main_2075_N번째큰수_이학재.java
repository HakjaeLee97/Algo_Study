package b2075;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		//n번째 큰 수
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> {return o2-o1;}); 
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
		}
		for (int i = 0; i < N-1; i++) {
			pq.poll();
		}
		System.out.println(pq.poll());
		br.close();

	}

}
