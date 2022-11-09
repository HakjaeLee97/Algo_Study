package November_first;

import java.util.*;
import java.io.*;

public class Main_2304_창고다각형_Silver_2 {
	

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int N  =Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		int[][] check = new int[N][2];
		int maxValue = 0;
		int maxIdx = 0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int idx = Integer.parseInt(st.nextToken());
			int hi = Integer.parseInt(st.nextToken());
			
			check[i][0] = idx;
			check[i][1] = hi;
			
			if(maxValue<=hi) {
				maxValue = hi;
				maxIdx= idx;
			}
			
			
		}
		
		Arrays.sort(check, (o1,o2) -> o1[0] - o2[0] );
		
		int max =  0;
		int answer = 0;
	
		
		int[]map = new int[check[N-1][0]+1];
		
	
		for(int i=0;i<N;i++) map[check[i][0]] = check[i][1]; 
		
		System.out.println(Arrays.toString(map));
		
		for(int i=0;i<maxIdx;i++) {
			
			int value =0;
			for(int j=0;j<N;j++) {
				if(i == check[j][0]) {
					value = check[j][1];
					break;
				}
			}
			
			if(max< value) max = value;
		
			
			answer += max;
			
		}
		
		max = 0;
		
		for(int i=check[N-1][0];i>maxIdx;i--) {
		
			int value =0;
			for(int j=0;j<N;j++) {
				if(i == check[j][0]) {
					value = check[j][1];
					break;
				}
			}
			
			if(max< value) max = value;
		

			answer += max;
		
		}
		
		answer += maxValue;
		
		System.out.println(answer);
	
		
	
	}
}
