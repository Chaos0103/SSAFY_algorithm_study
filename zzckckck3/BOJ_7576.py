from collections import deque

def BFS(start):
    global grid, days, col, row

    dxy = [[-1, 0], [1, 0], [0, -1], [0, 1]]; #상하좌우
    queue = deque()
    for s in start:
        i, j = s[0], s[1]
        queue.append([i, j])
        grid[i][j] = 1
    while queue:
        days += 1
        for _ in range(len(queue)):
            y, x = queue.popleft()
            for i in range(4):

                dx = x + dxy[i][0]
                dy = y + dxy[i][1]

                if 0 <= dx < col and 0 <= dy < row and grid[dy][dx] == 0:
                    queue.append([dy, dx])
                    grid[dy][dx] = 1

    for i in range(row):
        for j in range(col):
            if grid[i][j] == 0:
                return -1
    return days


col, row = map(int, input().split())
grid = []
days = -1
for i in range(row):
    grid.append(list(map(int, input().split())))
start = []
for i in range(row):
    for j in range(col):
        if grid[i][j] == 1:
            start.append([i, j])

print(BFS(start))