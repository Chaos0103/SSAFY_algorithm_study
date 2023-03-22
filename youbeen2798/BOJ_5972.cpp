#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <cstring>
#include <string>
#include <queue>
#include <stack>
#include <cmath>
#define INF 999999999

using namespace std;

priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> q;
int N, M;
vector<pair<int, int>> line[500001];
long long Distance[500001];

void Dijstra() {
	Distance[1] = 0;
	q.push({ 0,1 });
	while (!q.empty()) {
		int x = q.top().second;
		long long cost = q.top().first;
		q.pop();
		for (int i = 0; i < line[x].size(); i++) {
			int xx = line[x][i].first;
			int Cost = line[x][i].second;
			if (Distance[xx] > Distance[x] + Cost) {
				Distance[xx] = Distance[x] + Cost;
				q.push({ Distance[xx], xx });
			}
		}
	}
}

void solve() {
	for (int i = 1; i <= N; i++) Distance[i] = INF;
	Dijstra();
	cout << Distance[N];
}

int main()
{
	cin.tie(0);
	cout.tie(0);
	cin >> N >> M;
	for (int i = 1; i <= M; i++) {
		int x, y, cost;
		cin >> x >> y >> cost;
		line[x].push_back({ y,cost });
		line[y].push_back({ x,cost });
	}
	solve();
	return 0;
}