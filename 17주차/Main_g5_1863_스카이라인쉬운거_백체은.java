package a11월3주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_g5_1863_스카이라인쉬운거 {

	public static void main(String[] args) throws Exception {

		// 입력값보다 큰 거 pop하면서 cnt++
		// 마지막은 무조건 0으로 상정

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		Stack<Integer> s = new Stack<>();
		s.push(-1);
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			while(!s.isEmpty() && s.peek() >= y) {
				if(s.peek() != y) cnt++;
				s.pop();
			} 
			if(!s.isEmpty() && s.peek() <= y){
				s.push(y);
			} 
		}
		int y = 0;
		while(!s.isEmpty() && s.peek() >= y) {
			if(s.peek() != y) cnt++;
			s.pop();
		} 
		if(!s.isEmpty() && s.peek() <= y){
			s.push(y);
		} 
		System.out.println(cnt);
		br.close();
	}
}
