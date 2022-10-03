package Septempber_fourth;

import java.io.*;
import java.util.*;

public class Main_17615_볼모으기_서울_20반_박한샘 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int len = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		
		
		// R B B B R B R R R
		// 2
		// 하나만 옮길 수 있다.
		// 
		
		//왼 빨
		int answer = Integer.MAX_VALUE;
		
		int num  = 0;
		boolean flag = false;
		
		for(int i=0;i<len;i++) {
			
			char ch = str.charAt(i);
			
			if(ch=='B') flag= true;
			if(flag) if(ch=='R') num++;
			
		}
		answer = Math.min(answer, num);
		num = 0;
		flag = false;
		
		//왼 파
		for(int i=0;i<len;i++) {
			
			char ch = str.charAt(i);
			
			if(ch=='R') flag= true;
			if(flag) if(ch=='B') num++;
		}
		answer = Math.min(answer, num);
		num = 0;
		flag = false;
		
		//오 빨
		for(int i=len-1;i>-1;i--) {
			
			char ch = str.charAt(i);
			
			if(ch=='B') flag= true;
			if(flag) if(ch=='R') num++;
		}
		answer = Math.min(answer, num);
		num = 0;
		flag = false;
		
		//오 파
		for(int i=len-1;i>-1;i--) {
			char ch = str.charAt(i);
			
			if(ch=='R') flag= true;
			if(flag) if(ch=='B') num++;
		}
		answer = Math.min(answer, num);
		num = 0;
		flag = false;
		
		System.out.println(answer);
		
	}
}
