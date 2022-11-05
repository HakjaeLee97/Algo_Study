package a11월1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_g3_22866_탑보기 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] height = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		br.close();

		Stack<Integer> s = new Stack<>();
		// 사이즈 1차원 배열
		int[] size = new int[N+1];
		// 가까운 탑 1차원 배열
		int[] near = new int[N+1];
		
		
		// right 수행 먼저 한 후
		for (int i = N; i >= 1; i--) {
			while(!s.isEmpty() && height[s.peek()] <= height[i]) {
				s.pop();
			}
			size[i] += s.size();
			if(s.size() > 0) {
				near[i] = s.peek();
			}
			s.push(i);
		}

		s.clear();
		
		// left 수행하면서 바로 갱신
		for (int i = 1; i <= N; i++) {
			while(!s.isEmpty() && height[s.peek()] <= height[i]) {
				s.pop();
			}
			size[i] += s.size();
			if(s.size() > 0 && (i - s.peek() <= Math.abs(near[i] - i))) {
				near[i] = s.peek();
			}
			s.push(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(size[i]);
			if(size[i] > 0) {
				sb.append(" ").append(near[i]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
