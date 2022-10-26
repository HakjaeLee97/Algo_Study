package October_third;

import java.util.*;
import java.io.*;

public class bj_20920_영단어암기는괴로워_Silver_3 {
	
	static class word implements Comparable<word>{

		String word;
		int cnt;
		
		@Override
		public int compareTo(word o) {
			
			if(cnt==o.cnt) {
				if(word.length()==o.word.length()) {
					return word.compareTo(o.word);
				}else return -(word.length() - o.word.length());
			}else return -(cnt-o.cnt);
	
		}

		public word(String word, int cnt) {
			super();
			this.word = word;
			this.cnt = cnt;
		}
		
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder sb = new StringBuilder();
		//자주
		//해당 단어의 길이
		//알파벳 사전 순으로 앞
		//M길이 이상만
		
		int N = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(st.nextToken());
		
		Map<String,Integer> map = new HashMap<>();
		
		for(int i=0;i<N;i++) {
			
			String str = br.readLine();
			if(str.length()<M) continue;
			if(!map.containsKey(str)) map.put(str, 0);
			else map.put(str, map.get(str)+1);
			
		}
		
		List<word> list = new ArrayList<>();
		
		for(String key : map.keySet()) list.add(new word(key,map.get(key)));
		
		Collections.sort(list);
		
		for(int i=0;i<list.size();i++) sb.append(list.get(i).word+"\n");
		
		System.out.println(sb.toString());
	}
}
