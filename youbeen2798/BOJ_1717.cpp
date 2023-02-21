#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
using namespace std;

int n, m;
int parent[1000000];
int ans;
bool flag;

int find(int u)
{
    if (parent[u] == u) return u;
    else return parent[u] = find(parent[u]);
}

void union_node(int u, int v)
{
    u = find(u);
    v = find(v);

    if (u == v) {
        return;
    }
    else // 노드 결합
    {
        parent[u] = v;
        return;
    }
}

bool check(int u, int v)
{
    u = find(u);
    v = find(v);

    if (u == v) return true;
    else  return false;
}

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int f, u, v;
    cin >> n >> m;

    for (int i = 0; i < n; i++)
        parent[i] = i;

    for (int i = 1; i <= m; i++)
    {
        cin >> f >> u >> v;

        if (f == 1) {
            if (check(u, v)) {
                cout << "YES" << "\n";
            }
            else {
                cout << "NO" << "\n";
            }
        }
        else // (f == 0)
            union_node(u, v);
    }
}