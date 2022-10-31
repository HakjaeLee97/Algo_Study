package October_fourth;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0;t<T;t++) {
			
			int N = Integer.parseInt(br.readLine());
			String max="";
			String min ="";
			//쉬운 최대값먼저~
			for(int i=0;i<N/2;i++) max += "1";
			
			if(N%2==1)  max = "7"+max.substring(1);
			
			
			//최소값이요~
		
				while(true) {
					//11 17 23 즉, -11이 %6이 0이면 붙여주자!
						if(N==17){
							min = "200" +min;
							break;
						}else if(N==11) {
							min = "20" + min;
							break;
						
						}else if(N==10) {
							
						}else if(8<N) {
							min += "8";
							N -= 7;
						}else if(8==N) {
							min = "10" + min;
							break;
						}else {
							switch (N) {
							case 7: min = "8" + min; break; //7
							case 6: if(min.isEmpty()) min = "6";
									else if(min.charAt(0)=='8') min = "6" + min;
									else min = min + "0"; break; //6
							case 5: min = "2" + min; break; //5
							case 4: min = "4" + min; break; //4
							case 3: min = "7" + min; break; //3
							case 2: min = "1" + min; break; //2
							}
							
							break;
							
						}
											
					}
			

			sb.append(min).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
