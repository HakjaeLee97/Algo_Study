package Septempber_fourth;

import java.util.*;

public class Main_15989_123더하기4_서울_20반_박한샘 {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for(int tc=0;tc<t;tc++) {
			
			int num = sc.nextInt();
			
			int[]memo = new int[num+1];
			
		
			
			
			memo[0] = 1;
			memo[1] = 1;
			
			int up = 1;
			int check = 0;
			for(int j=2;j<=num;j++) {

				memo[j] = memo[j-1] + up;
				
				if(check==2) {
					up++;
					check = 0;
					memo[j] = memo[j-1] + up;
					
				}
				
				
				if(check==1) check = 2;
				
				if(j%6==0) {
					memo[j] = memo[j-1] + up + 1; 
					check = 1;
				}
				
			}
			
			
			System.out.println(memo[num]);
		
			
			
			
			
			
			
		}
		
	}
}
