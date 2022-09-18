class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
 
        int[][] sum = new int[N + 1][M + 1];
        for (int[] s : skill) {
            int r1 = s[1], c1 = s[2];
            int r2 = s[3], c2 = s[4];
            int degree = 0;
            if(s[0] == 1) {
            	degree = -s[5];
            } else {
            	degree = s[5];
            }
 
            sum[r1][c1] += degree;
            sum[r1][c2 + 1] -= degree;
            sum[r2 + 1][c1] -= degree;
            sum[r2 + 1][c2 + 1] += degree;
        }
        // 상하
        for (int r = 1; r < N; r++) {
        	for (int c = 0; c < M; c++) {
        		sum[r][c] += sum[r - 1][c];
        	}
        }
        // 좌우
        for (int c = 1; c < M; c++) {
        	for (int r = 0; r < N; r++) {
        		sum[r][c] += sum[r][c - 1];
        	}
        }
 
        // 살아남은 건물 확인
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + sum[i][j] > 0) answer++;
            }
        }
        return answer;
    }
}