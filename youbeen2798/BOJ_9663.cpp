#include <iostream>
using namespace std;

int N, M;
int map[500][500] = { 0 };
int dp[500][500] = { 0 };
int visited[500][500] = { 0 };

int dy[4] = { 1,-1,0,0 };
int dx[4] = { 0,0,1,-1 };

int dfs(int y, int x) {

    if (y == M - 1 && x == N - 1)
        return 1;

    if (visited[y][x])
        return dp[y][x];

    visited[y][x] = 1;

    for (int i = 0; i < 4; i++) {
        int ny = y + dy[i];
        int nx = x + dx[i];

        if (ny < 0 || ny >= M || nx < 0 || nx >= N)
            continue;

        if (map[ny][nx] >= map[y][x])
            continue;

        dp[y][x] += dfs(ny, nx);
    }

    return dp[y][x];
}
int main() {

    scanf("%d %d", &M, &N);

    for (int i = 0; i < M; i++)
        for (int j = 0; j < N; j++)
            scanf("%d", &map[i][j]);


    cout << dfs(0, 0) << endl;
    return 0;
}

