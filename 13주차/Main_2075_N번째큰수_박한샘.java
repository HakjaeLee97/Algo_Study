package October_third;

import java.util.*;
import java.io.*;

public class bj_2075_N번째큰수_Silver_2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i=0;i<N-1;i++) pq.poll();
		
		System.out.println(pq.poll());
		
	}
}
