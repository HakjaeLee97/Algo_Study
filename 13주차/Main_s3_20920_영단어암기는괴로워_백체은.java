package a10월4주차;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main_s3_20920_영단어암기는괴로워 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<String, Integer> words = new HashMap<>();
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			if (word.length() >= M) {
				if (words.containsKey(word)) {
					words.put(word, words.get(word) + 1);
				} else {
					words.put(word, 1);
				}
			}
		}
		br.close();
		
		List<Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if(o2.getValue()-o1.getValue() == 0) {
					if(o2.getKey().length() == o1.getKey().length()) {
						return o1.getKey().compareTo(o2.getKey());
					}
					return Integer.compare(o2.getKey().length(), o1.getKey().length());
				}
				return Integer.compare(o2.getValue(), o1.getValue());
			}
		});

		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).getKey()).append("\n");
		}
		System.out.print(sb);
	}
}
