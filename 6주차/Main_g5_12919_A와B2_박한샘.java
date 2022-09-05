package September_first;

import java.util.*;
import java.io.*;

/*
 S를 T로 바꾸는 게임
 문자열 뒤에 A를 추가
 문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.(아마 스택?)
  
 
 * */
public class Main {
	
	static String S;
	static String T;
	static Stack<Character> st;
	static int answer = 0;
	static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		T = br.readLine();
	}
	
	static void calc(String s,String t) {
		if(s.length() >= t.length()) {
			if(s.equals(t)) {
				answer = 1;
			}
			return;
		}
		
		if(t.charAt(t.length()-1)=='A') {
			calc(s, t.substring(0,t.length()-1));
		}
		if(t.charAt(0)=='B') {
			StringBuilder sb = new StringBuilder();
			sb.append(t).reverse();
			calc(s,sb.substring(0,t.length()-1).toString());
		}
		
	}
	
	static void output() {
		System.out.println(answer);
	}
	
	public static void main(String[] args)throws Exception {
		
		input();
		
		calc(S,T);
		
		output();
		
	}
	
	
}
