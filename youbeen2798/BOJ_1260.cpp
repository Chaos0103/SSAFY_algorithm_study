#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
#include <algorithm>
using namespace std;

int n; //정점 개수
int m; //간선 개수
int v; //탐색을 시작할 시작 정점

vector<int> vt[1001];
bool visited[1001];
queue<int> q;

void dfs(int start) {

	q.push(start);
	cout << start << " ";
	visited[start] = true;

	for (int i = 0; i < vt[start].size(); i++) {
		if (!visited[vt[start][i]]) {
			dfs(vt[start][i]);
		}
	}
}
void bfs(int start) {
	queue<int> q;

	memset(visited, false, sizeof(visited));

	q.push(start);
	visited[start] = true;

	while (!q.empty()) {
		int a = q.front();
		cout << a << " ";
		q.pop();

		for (int i = 0; i < vt[a].size(); i++) {
			int num = vt[a][i];

			if (!visited[num]) {
				visited[num] = true;
				q.push(num);
			}
		}
	}
	cout << "\n";
}

void input() {
	cin >> n >> m >> v;

	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		vt[a].push_back(b);
		vt[b].push_back(a); 
	}

	for (int i = 1; i <= 1000; i++) {
		sort(vt[i].begin(), vt[i].end());
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	dfs(v);
	cout << "\n";
	bfs(v);
}