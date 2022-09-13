
import java.util.*;
import java.io.*;

public class Main {

	static String str;
	static int answer = Integer.MAX_VALUE;
	static void input()throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
	}
	
	static void output() {
		System.out.println(answer);
	}
	
	static void calc() {
	
		int ac = 0;
		
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='a') {
				ac++;
			}
		}
		
		
		for(int i=0;i<str.length();i++) {
			int bc = 0;
			for(int j=i;j<i+ac;j++) {
				int idx = j % str.length();
				if(str.charAt(idx)=='b') bc++;
			}
			
			answer = Math.min(answer, bc);
		}
		
	}
	public static void main(String[] args) throws Exception{
		input();
		calc();
		output();
	}
}
