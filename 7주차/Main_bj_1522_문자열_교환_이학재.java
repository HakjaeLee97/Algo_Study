package b1522;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = in.readLine();
		int min = Integer.MAX_VALUE;
		int a_len = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'a')
				a_len++;
		}
		for (int i = 0; i < s.length(); i++) {
			int b_cnt = 0;
			for (int j = i; j < a_len+i; j++) {
				if(s.charAt(j % s.length()) == 'b')
					b_cnt++;
			}
			min = Math.min(min, b_cnt);
		}
		System.out.println(min);
	}

}
