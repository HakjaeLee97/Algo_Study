package a10월4주차;

import java.io.*;
import java.util.*;

public class Main_s2_2075_N번째큰수 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o2, o1));
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans = pq.poll();
		}
		System.out.println(ans);
	}
}
