package a11월1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_g4_9935_문자열폭발 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String original = br.readLine();
		String explosion = br.readLine();
		br.close();

		Stack<Character> stack = new Stack<>();
		int olen = original.length();
		int elen = explosion.length();
		for (int i = 0; i < olen; i++) {
			char now = original.charAt(i);
			stack.push(now);
			if(stack.size() >= elen) {
				boolean flag = true;
				for (int j = 0; j < elen; j++) {
					if(stack.get(stack.size()-elen+j) != explosion.charAt(j)) {
						flag = false;
						break;
					}
				}
				if(flag) {
					for (int j = 0; j < elen; j++) {
						stack.pop();
					}
				}
			}
		}
		if(stack.size() == 0) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stack.size(); i++) {
				sb.append(stack.get(i));
			}
			System.out.print(sb);
		}
	}
}
