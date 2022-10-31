package a10월5주차;

import java.io.*;
import java.util.*;

public class Main_s3_19637_IF문좀대신써줘 {
	
	static class Power {
		String title;
		int power;

		public Power(String title, int power) {
			this.title = title;
			this.power = power;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Power> powers = new ArrayList<>();
		Set<Integer> dup = new HashSet<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String title = st.nextToken();
			int power = Integer.parseInt(st.nextToken());
			// 중복된 칭호 처리
			if(dup.contains(power)) continue;
			powers.add(new Power(title, power));
		}
		Collections.sort(powers, (o1, o2) -> o1.power - o2.power);
		for (int i = 0; i < M; i++) {
			int player = Integer.parseInt(br.readLine());
			int start = 0;
			int end = powers.size()-1;
			int mid = 0;
			while(start <= end) {
				mid = (start + end)/2;
				if(player > powers.get(mid).power) {
					start = mid+1;
				} else {
					end = mid-1;
				}
			}
//			System.out.println(start + " "  + mid + " " + end);
			sb.append(powers.get(start).title).append("\n");
		}
		br.close();
		System.out.print(sb);
	}
}
