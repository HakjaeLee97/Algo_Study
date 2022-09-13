

import java.io.*;
import java.util.*;

public class Main {

	static int N,M;
	static Map<String,Integer> map;
	
	static int answer;
	
	static void calc() throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = N;
		map = new HashMap<>();
		
		for(int i=0;i<N;i++) map.put(br.readLine(), 1);
		
		for(int i=0;i<M;i++) {
			
			String[]tmp = br.readLine().split(",");
			
			for(int j=0;j<tmp.length;j++) {
				
				if(map.containsKey(tmp[j]) && map.get(tmp[j])==1) {
					map.put(tmp[j], 0);
					answer--;
				}
				
			}
			System.out.println(answer);

		}
		
		
		

	}
	

	public static void main(String[] args) throws Exception {
		
		calc();
		
		
	}
	
}
