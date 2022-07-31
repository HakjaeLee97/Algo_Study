from collections import deque
from collections import defaultdict


T = 10

def BFS(graph,queue,visited):
    result = max(queue)
    next_queue = deque()
    while queue:
        n = queue.popleft()
        for neighbor in graph[n]:
            if neighbor not in visited:
                visited.append(neighbor)
                next_queue.append(neighbor)
    if next_queue:
       return( BFS(graph,next_queue,visited))
    else:
        return result


for test_case in range(1,11):
    length,start_node = map(int,(input().rstrip().split()))
    graph = defaultdict(list)
    visited =[]
    n = list(map(int,input().rstrip().split()))
    for i in range(0,length,2):
        """ n[0] 부터 [n]23]까지 존재
        0,2,4,6,8,10,12,14,16,18,20,22
        12번 루프 돌면 됨

        """
        n1,n2 = [n[i],n[i+1]]
        if n1 not in graph:
            graph[n1] = [n2]
        elif n2 not in graph[n1]:
            graph[n1].append(n2)
    queue = deque()
    queue.append(start_node)
    print("#%d"%test_case,BFS(graph,queue,visited))
    
