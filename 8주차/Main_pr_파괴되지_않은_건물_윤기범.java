class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] temp = new int[board.length + 1][board[0].length + 1];
        
        for(int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            if(type == 1) { // 공격
                temp[r1][c1] -= degree;
                temp[r1][c2 + 1] += degree;
                temp[r2 + 1][c1] += degree;
                temp[r2 + 1][c2 + 1] -= degree;
            }
            else { // 회복
                temp[r1][c1] += degree;
                temp[r1][c2 + 1] -= degree;
                temp[r2 + 1][c1] -= degree;
                temp[r2 + 1][c2 + 1] += degree; 
            }
        }  
        
        for(int i = 0; i < temp.length-1; i++) {
            for(int j = 0; j < temp[0].length-1; j++) {
                temp[i][j + 1] += temp[i][j];
            }
        }
        
        for(int j = 0; j < temp[0].length - 1; j++) {
            for(int i = 0; i < temp.length - 1; i++) {
                temp[i + 1][j] += temp[i][j];
            }
        }
    
        int cnt = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] += temp[i][j];
                if(board[i][j] > 0) 
                    cnt++;
            }
        }
        return cnt;
    }
}
