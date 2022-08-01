import heapq


T= int(input())

dx,dy = [-1,1,0,0],[0,0,-1,1]

for test_case in range(1,T+1):
    N = int(input())
    road_map = [list(map(int,list(input()))) for _ in range(N)]
    distance = [[int(10e9)]*N for _ in range(N)]
    x, y = 0,0
    heap = [(road_map[x][y], x,y)]
    distance[x][y] = road_map[x][y]

    while heap:
        dist, x, y = heapq.heappop(heap)
        if distance[x][y] < dist:
            continue
        for i in range(4):
            nx, ny = x +dx[i] , y+ dy[i]
            if nx < 0 or nx >= N or ny < 0 or ny >= N:
                continue
            cost = dist + road_map[nx][ny]
            if cost < distance[nx][ny]:
                distance[nx][ny] = cost
                heapq.heappush(heap,[cost,nx,ny])

    print("#%d"%test_case,distance[N-1][N-1])

