package b20920;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		//빈도수 정렬 -> 길이 정렬 -> 사전순 정렬 
		//빈도를 확인하기 위해선 N입력을 끝까지 받고 정렬해야함
		//O(N + NlogN)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<String, Integer> hm = new HashMap<>();
		String tmp;
		for (int i = 0; i < N; i++) {
			tmp = br.readLine();
			if(tmp.length()<M) continue;
			hm.compute(tmp,(k,v) -> (v==null) ? 1 : v + 1);
		}
		List<String> keys = new ArrayList<>(hm.keySet());
		keys.sort((s1,s2) -> 
		{
			int res =  -(hm.get(s1) - hm.get(s2));
			if(res == 0) {
				res = -(s1.length() - s2.length());
				if(res == 0) {
					res = s1.compareTo(s2);
				}
			}
			return res;
		});
		for(String s : keys) {
			bw.write((s+'\n'));
		}
		bw.flush();
		br.close();
	}

}
