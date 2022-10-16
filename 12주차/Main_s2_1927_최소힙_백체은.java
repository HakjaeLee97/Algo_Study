package a10월3주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_s2_1927_최소힙 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int in = Integer.parseInt(br.readLine());
			if(in > 0) {
				pq.offer(in);
			} else {
				sb.append(pq.isEmpty()? 0: pq.poll()).append("\n");
			}
		}
		br.close();
		System.out.print(sb);
	}
}
