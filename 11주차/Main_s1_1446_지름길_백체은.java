package a10월2주차;

import java.util.*;
import java.io.*;

public class Main_s1_1446_지름길 {

	static class Node {

		int from, to, dist;

		public Node(int from, int to, int dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		ArrayList<Node> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			if (to > D) // 역주행
				continue; 
			if (to - from <= dist) // 지름길 아님
				continue; 
			list.add(new Node(from, to, dist));
		}

		Collections.sort(list, new Comparator<Node>() {
			
			public int compare(Node o1, Node o2) {
				if (o1.from == o2.from) {
					return Integer.compare(o1.to, o2.to);
				}
				return Integer.compare(o1.from, o2.from);
			}
		});

		int start = 0;
		int d[] = new int[D + 1];
		for (int i = 0; i <= D; i++) {
			d[i] = i;
		}
		
		int i = 0;
		while (start < D) {
			if (i < list.size()) {
				Node shortCut = list.get(i);
				if (start == shortCut.from) {
					d[shortCut.to] = Math.min(d[shortCut.from] + shortCut.dist, d[shortCut.to]);
					i++;
				} else {
					d[start + 1] = Math.min(d[start + 1], d[start] + 1);
					start++;
				}
			} else {
				d[start + 1] = Math.min(d[start + 1], d[start] + 1);
				start++;
			}
		}
		System.out.println(d[D]);
	}
}
