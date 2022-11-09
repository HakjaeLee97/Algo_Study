package November_first;

import java.io.*;
import java.util.*;

public class bj_3687_성냥개비_Gold_2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0;t<T;t++) {
			
			int N = Integer.parseInt(br.readLine());
			String max="";
			
			//쉬운 최대값먼저~
			for(int i=0;i<N/2;i++) max += "1";
			
			if(N%2==1)  max = "7"+max.substring(1);
			
			
			//최소값이요~
		
			long[] minDp = new long[101];


            Arrays.fill(minDp, Long.MAX_VALUE);
            minDp[2] = 1;
            minDp[3] = 7;
            minDp[4] = 4;
            minDp[5] = 2;
            minDp[6] = 6;
            minDp[7] = 8;
            minDp[8] = 10;

            String[] add = {"1", "7", "4", "2", "0", "8"};
         
            for (int j = 9; j <= 100; j++) {
                for (int k = 2; k <= 7; k++) {
                    String line = "" + minDp[j - k] + add[k - 2];
                    minDp[j] = Math.min(Long.parseLong(line), minDp[j]);
                }
            }
			

			sb.append(minDp[N]).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
