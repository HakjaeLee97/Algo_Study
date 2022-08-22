import java.io.*;
import java.util.*;

public class Main {
	public static int N, t, x, y;
	public static boolean[][] visited_blue = new boolean[6][6];
	public static boolean[][] visited_green = new boolean[6][6];
	public static int score = 0;
	public static int cnt = 0;

	public static void fillBlue(int t, int x, int y) {
		if (t == 1) {
			int max = 0;
			for (int i = 0; i <= 5; i++) {
				if (!visited_blue[x][i]) {
					max = i;
				} else {
					break;
				}
			}
			visited_blue[x][max] = true;
		} else if (t == 2) {
			int max = 1;
			for (int i = 1; i <= 5; i++) {
				if (!visited_blue[x][i] && !visited_blue[x][i - 1]) {
					max = i;
				} else {
					break;
				}
			}
			visited_blue[x][max] = true;
			visited_blue[x][max - 1] = true;
		} else if (t == 3) {
			int max = 0;
			for (int i = 0; i <= 5; i++) {
				if (!visited_blue[x][i] && !visited_blue[x + 1][i]) {
					max = i;
				} else {
					break;
				}
			}
			visited_blue[x][max] = true;
			visited_blue[x + 1][max] = true;
		}
	}
	
	public static void fillGreen(int t, int x, int y) {
		if (t == 1) {
			int max = 0;
			for (int i = 0; i <= 5; i++) {
				if (!visited_green[i][y]) {
					max = i;
				} else {
					break;
				}
			}
			visited_green[max][y] = true;
		} else if (t == 2) {
			int max = 0;
			for (int i = 0; i <= 5; i++) {
				if (!visited_green[i][y] && !visited_green[i][y + 1]) {
					max = i;
				} else {
					break;
				}
			}
			visited_green[max][y] = true;
			visited_green[max][y + 1] = true;
		} else if (t == 3) {
			int max = 1;
			for (int i = 1; i <= 5; i++) {
				if (!visited_green[i][y] && !visited_green[i - 1][y]) {
					max = i;
				} else {
					break;
				}
			}
			visited_green[max][y] = true;
			visited_green[max - 1][y] = true;
		}
	}
	
	public static void checkBlue() {
		ArrayList<Integer> list = new ArrayList<>();
		
		// 2~5줄 확인
		for (int j = 5; j >= 2; j--) {
			int column = 0;
			for (int i = 0; i < 4; i++) {
				if (visited_blue[i][j]) {
					column++;
				}
			}
			if (column == 4) { // 한줄이 채워진 경우
				list.add(j);
			}
		}
		if (list.size() > 0) {
			moveBlue(list);
		}
		
		list = new ArrayList<>();
		
		// 0~1줄 확인
		for (int j = 1; j >= 0; j--) {
			for (int i = 0; i < 4; i++) {
				if (visited_blue[i][j]) {
					list.add(j);
					break;
				}
			}
		}
		if (list.size() > 0) {
			moveBlue(list);
		}
	}
	
	public static void checkGreen() {
		ArrayList<Integer> list = new ArrayList<>();
		
		for (int i = 5; i >= 2; i--) {
			int row = 0;
			for (int j = 0; j < 4; j++) {
				if (visited_green[i][j]) {
					row++;
				}
			}
			if (row == 4) {
				list.add(i);
			}
		}
		if (list.size() > 0) {
			moveGreen(list);
		}
		
		list = new ArrayList<>();
		
		for (int i = 1; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				if (visited_green[i][j]) {
					list.add(i);
					break;
				}
			}
		}
		if (list.size() > 0) {
			moveGreen(list);
		}
	}
	
	public static void moveBlue(ArrayList<Integer> list) {
		Collections.reverse(list);
		
		for (Integer li : list) {
			if (li > 1) { // 2~5인 경우
				for (int j = li; j > 0; j--) {
					for (int i = 0; i < 4; i++) {
						visited_blue[i][j] = visited_blue[i][j - 1];
					}
				}
				for (int i = 0; i < 4; i++) {
					visited_blue[i][0] = false;
				}
				score++;
			} else { // 0~1인 경우
				for (int j = 5; j > 0; j--) {
					for (int i = 0; i < 4; i++) {
						visited_blue[i][j] = visited_blue[i][j - 1];
					}
				}
				for (int i = 0; i < 4; i++) {
					visited_blue[i][0] = false;
				}
			}
		}
	}
	
	public static void moveGreen(ArrayList<Integer> list) {
		Collections.reverse(list);
		
		for (Integer li : list) {
			if (li > 1) { // 2~5인 경우
				for (int i = li; i > 0; i--) {
					for (int j = 0; j < 4; j++) {
						visited_green[i][j] = visited_green[i - 1][j];
					}
				}
				for (int j = 0; j < 4; j++) {
					visited_green[0][j] = false;
				}
				score++;
			} else {
				for (int i = 5; i > 0; i--) {
					for (int j = 0; j < 4; j++) {
						visited_green[i][j] = visited_green[i - 1][j];
					}
				}
				for (int j = 0; j < 4; j++) {
					visited_green[0][j] = false;
				}
			}
		}
	}
	
	public static void count() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				if (visited_blue[i][j]) {
					cnt++;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (visited_green[i][j]) {
					cnt++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); 

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			fillBlue(t, x, y);
			fillGreen(t, x, y);

			checkBlue();
			checkGreen();
		}

		count();

		System.out.println(score);
		System.out.println(cnt);

		br.close();
	}

}
