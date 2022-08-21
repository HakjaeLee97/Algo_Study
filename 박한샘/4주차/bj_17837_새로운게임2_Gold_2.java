package August_third;


import java.io.*;
import java.util.*;

class Piece
{
    int x,y,dir;

    public Piece(int x, int y, int dir)
    {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
public class bj_17837_새로운게임2_Gold_2 {
    static int N,K;
    static List<Integer>[][] map;
    static Piece [] piece;
    static int [] dx = {0,0,-1,1};
    static int [] dy = {1,-1,0,0}; //우 좌 상 하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new List[N][N];
        piece = new Piece[K+1];

        for(int i = 0; i < N; ++i) // 0흰 1빨 2파
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; ++j)
            {
                map[i][j] = new ArrayList<>();
                map[i][j].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i = 1; i <= K; ++i)
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken())-1;
            piece[i] = new Piece(x,y,dir);
            map[x][y].add(i);
        }

        int t = 0;
        boolean flag = false;
        loop : while(t++ < 1000)
        {
            for(int i = 1; i <= K; ++i)
            {
                flag = next(piece[i],i);
                if(flag) break loop;
            }

        }
        int answer = t == 1001 ? -1 : t;
        System.out.print(answer);
    }
    static boolean next(Piece p,int n)
    {
        int cur_x = p.x;
        int cur_y = p.y;
        int level = 0;
        int size = map[cur_x][cur_y].size();
        for(int i = 1; i < size; ++i)
        {
            if(map[cur_x][cur_y].get(i) == n)
            {
                level = i;
                break;
            }
        }
        int mx = cur_x + dx[p.dir];
        int my = cur_y + dy[p.dir];
        int nextBlock = 2;

        if(isValid(mx,my)) nextBlock = map[mx][my].get(0);

        if(nextBlock == 2) // 범위벗어났거나 파란블록
        {
            if(p.dir == 0) p.dir = 1;
            else if(p.dir == 1) p.dir = 0;
            else if(p.dir == 2) p.dir = 3;
            else p.dir = 2;

            mx = cur_x + dx[p.dir];
            my = cur_y + dy[p.dir];

            if(!isValid(mx,my) || map[mx][my].get(0) == 2) return false;
            else
            {
                return move(map[mx][my].get(0),level,size,cur_x,cur_y,mx,my);
            }
        }
        else
        {
            return move(nextBlock,level,size,cur_x,cur_y,mx,my);
        }
    }
    static boolean move(int color,int start, int end, int x, int y, int mx,int my)
    {
        List<Integer> tmp_list = map[x][y].subList(start,end);
        int start_size = tmp_list.size();
        int end_size = map[mx][my].size();
        if(start_size + end_size > 4) return true;
        if(color == 0) 
        {
            for(int i : tmp_list)
            {
                map[mx][my].add(i);
                piece[i].x = mx;
                piece[i].y = my;
            }
        }
        else
        {
            for(int i = start_size-1; i >= 0; --i)
            {
                int num = tmp_list.get(i);
                map[mx][my].add(num);
                piece[num].x = mx;
                piece[num].y = my;
            }
        }
        map[x][y].subList(start,end).clear();
        return false;
    }
    static boolean isValid(int x, int y)
    {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}