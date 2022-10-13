import java.util.*;
import java.io.*;

public class Main {
	public static int N, X;
	public static PriorityQueue<Integer> heap = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			X = Integer.parseInt(br.readLine());
			if(X == 0) {
				if(heap.size() == 0)
					System.out.println(0);
				else {
					int num = heap.poll();
					System.out.println(num);
				}
			}
			else {
				heap.offer(X);
			}
		}
	}
}
