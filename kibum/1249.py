from collections import deque

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def bfs(xPos, yPos, graph, visited):
    q = deque()
    q.append((xPos, yPos))
    visited[xPos][yPos] = graph[xPos][yPos]

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if (nx < 0 or ny < 0 or nx >= n or ny >= n):
                continue
            elif(visited[nx][ny] > graph[nx][ny] + visited[x][y]):
                visited[nx][ny] = graph[nx][ny] + visited[x][y]
                q.append((nx, ny))

tc = int(input())
for t in range(1, tc+1):
    INF = int(1e9)
    n = int(input())
    graph = []
    visited = [[INF] * n for _ in range(n)]

    for i in range(n):
        graph.append(list(map(int, input())))

    bfs(0, 0, graph, visited)
    print("#" + str(t) + " " + str(visited[n-1][n-1]))