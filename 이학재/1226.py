from collections import deque

import sys
sys.stdin = open("input.txt","r")

def bfs(road_map,start):
    queue = deque()
    queue.append(start)

    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx, ny = x +dx[i] , y+ dy[i]

            if nx < 0 or nx >= N or ny < 0 or ny >= N:
                continue
            if road_map[nx][ny] == 3:
                return("#%d"%test_case+' 1')
    
            elif road_map[nx][ny] >=1:
                continue
            elif road_map[nx][ny]== 0:
                road_map[nx][ny] = road_map[x][y] + 4
                queue.append((nx,ny))
            
    return("#%d"%test_case+' 0')

T= 10
N = 16

dx,dy = [-1,1,0,0],[0,0,-1,1]

for test_case in range(1,T+1):
    tc = int(input())
    road_map = [list(map(int,list(input()))) for _ in range(16)]
    for row in range(N):
        for col in range(N):
            if road_map[row][col] == 2:
                x, y = row,col
            elif road_map[row][col] == 3:
                end_pos = (row,col)
    queue = deque()
    queue.append((x,y))

#     while queue:
#         x, y = queue.popleft()
#         for i in range(4):
#             nx, ny = x +dx[i] , y+ dy[i]

#             if nx < 0 or nx >= N or ny < 0 or ny >= N:
#                 continue
#             if road_map[nx][ny] ==1:
#                 continue
#             if road_map[nx][ny]== 0:
#                 road_map[nx][ny] = road_map[x][y] + 1
#                 queue.append((nx,ny))
#             if road_map[nx][ny] == 3:
#                 print("#%d"%test_case,1)
#                 break
#     print("#%d"%test_case,0)
    print(bfs(road_map,(x,y)))
