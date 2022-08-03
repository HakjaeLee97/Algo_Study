import sys
sys.stdin = open(r"D:\Programming\Problem solving\SWEA\input.txt" ,"r")

from collections import defaultdict , deque

def get_dist(pos1,pos2):
    return(abs(pos1[0]-pos2[0]) + abs(pos1[1]-pos2[1]))

def get_result(seq,home,work,threshold):
    result = 0
    result += get_dist(home,seq[0])
    for i in range(len(seq)-1):
        result += get_dist(seq[i],seq[i+1])
    result += get_dist(seq[-1],work)
    if result > threshold:
        return 100000
    return result


def DFS(graph,start_node):
    need_visited = deque()
    visited = []
    dists = []
    need_visited.append(start_node)

    while need_visited:
        node = need_visited.pop()
        if node not in visited:
            visited.append(node)
            need_visited.extend(graph[node])
    return visited 



T = int(input())

for tc in range(1,T+1):
    N = int(input())
    poses = list(map(int,(input().split())))
    pos_home = poses[0:2]
    pos_work = poses[2:4]
    graph = defaultdict(list)


    pos_clients = []
    for i in range(4,2*N+4,2):
        pos_clients.append(tuple(poses[i:i+2]))


    for i in range(N):
        for j in range(N):
            if i != j:
                graph[pos_clients[i]].append(pos_clients[j])

    print(DFS(graph,pos_clients[0]))
    
   # min = 100000
 

   # print("#%d"%tc,min)
