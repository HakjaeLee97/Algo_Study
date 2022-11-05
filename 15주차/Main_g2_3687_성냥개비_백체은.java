package a11월1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_g2_3687_성냥개비 {
	
	// 	0	1	2	3	4	5	6	7	8	9
	// 	6	2	5	5	4	5	6	3	7	6
	
	// 		1	2	3	4	5	6	7	
	//			1	7	4	2	0	8
	
	// 		8	9	10	11	12	13	14	
	// 		10	18	22	20	28	68	88
	
	//		15	16	17	18	19	20	21
	//		108	188	200	208	288	688	888
	
	//		22		23		24		25		26		27		28
	//		1088	1888	2008	2088
	
	public static void main(String[] args) throws Exception {
		
		String[] dp = new String[101]; // 최소
		dp[2] = "1"; dp[3] = "7"; dp[4] = "4"; dp[5] = "2"; dp[6] = "6"; dp[7] = "8";
		dp[8] = "10"; dp[9] = "18"; dp[10] = "22"; dp[11] = "20"; dp[12] = "28"; dp[13] = "68"; dp[14] = "88";
		dp[15] = "108"; dp[16] = "188"; dp[17] = "200"; dp[18] = "208"; dp[19] = "288"; dp[20] = "688"; dp[21] = "888";

		for (int i = 22; i <= 100; i++) {
			dp[i] = dp[i-7] + "8";
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(dp[n]).append(" ");
			String max = "";
			if(n%2 == 0) {
				max = "1";
				n -= 2;
			} else {
				max = "7";
				n -= 3;
			}
			while(n > 0) {
				max += "1";
				n -= 2;
			}
			sb.append(max).append("\n");
		}
		br.close();
		System.out.print(sb);
	}
}
