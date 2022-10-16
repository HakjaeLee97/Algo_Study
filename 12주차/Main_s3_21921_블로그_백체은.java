package a10월3주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_s3_21921_블로그 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int[] visiter = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			visiter[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		int max = 0;
		for (int i = 0; i < X; i++) {
			max += visiter[i];
		}
		
		int start = 1;
		int end = start + X - 1;
		int sum = max;
		int cnt = 1;
		while(end < N) {
			sum -= visiter[start-1];
			sum += visiter[end];
			if(max < sum) {
				max = sum;
				cnt = 1;
			} else if(max == sum) {
				cnt++;
			}
			start++;
			end++;
		}
		if(max == 0) {
			System.out.println("SAD");
		} else {
			System.out.println(max);
			System.out.println(cnt);
		}
	}
}
