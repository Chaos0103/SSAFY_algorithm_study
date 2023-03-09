#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int n;
vector<int> adj[3001];
int check[3001];
int cycle[3001];

void dfs(int start, int cur, int d) {
    check[cur] = true;
    for (int i = 0; i < adj[cur].size(); i++) {
        int next = adj[cur][i];
        if (d >= 2 && start == next) {
            for (int j = 1; j <= n; j++)
                if (check[j]) cycle[j] = true;
        }
        if (!check[next]) dfs(start, next, d + 1);
    }
    check[cur] = false;
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    for (int i = 1; i <= n; i++) {
        if (!cycle[i]) dfs(i, i, 0);
    }
    for (int i = 1; i <= n; i++) {
        if (cycle[i]) {
            cout << 0 << " ";
        }
        else {
            queue<pair<int, int>> q;
            q.push({ i, 0 });
            vector<bool> visited(n + 1, 0);
            visited[i] = true;
            while (!q.empty()) {
                int x = q.front().first;
                int d = q.front().second;
                if (cycle[x]) {
                    cout << d << " ";
                    break;
                }
                q.pop();
                for (int i = 0; i < adj[x].size(); i++) {
                    int next = adj[x][i];
                    if (!visited[next]) {
                        visited[next] = true;
                        q.push({ next, d + 1 });
                    }
                }
            }
        }
    }
}