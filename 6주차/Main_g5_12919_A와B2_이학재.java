package b12919;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String a = br.readLine();
		String b = br.readLine();
		modify(b,a);
		System.out.println(0);
		br.close();
	}
	
	public static void modify(String b, String a) {
		if(b.length() < a.length() ) return;
		
		if(b.equals(a)) {
			System.out.println(1);
			System.exit(0);
		}
		StringBuffer sb = new StringBuffer(b);

		if(b.charAt(b.length()-1) == 'A'){
			b = b.substring(0,b.length()-1);
			modify(b,a);			
		}
		b= sb.reverse().toString();
		if(b.charAt(b.length()-1) =='B') {
			b = b.substring(0,b.length()-1);
			modify(b,a);
			
		}
	
		
	}
	

}
