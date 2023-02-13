#include<iostream>
#include<algorithm>
#include<vector>

#define MAX_N 501
#define INF 9999999999999
using namespace std;
int N, M;
vector<pair<int, int>> adj[MAX_N];

void bellman() {
	vector<long long> upper(N + 1, INF);
	upper[1] = 0;

	int updated = 0;
	for (int iter = 0; iter < N; iter++)
	{
		updated = 0;
		for (int here = 1; here <= N; here++)
			for (int j = 0; j < adj[here].size(); j++)
			{
				int end = adj[here][j].first;
				int cost = adj[here][j].second;

				if (upper[here] == INF)
					continue;

				if (upper[end] > upper[here] + cost) {
					upper[end] = upper[here] + cost;
					updated = 1;
				}
			}
		if (updated == 0)
			break;
	}
	if (updated == 1) {
		upper.clear();
		cout << -1 << '\n';
		return;
	}
	for (int i = 2; i <= N; i++)
	{
		if (upper[i] != INF)
			cout << upper[i] << '\n';
		else
			cout << -1 << '\n';
	}
}
int main() {
	cin >> N >> M;

	int A, B, C;
	for (int i = 0; i < M; i++)
	{
		cin >> A >> B >> C;
		adj[A].push_back({ B,C });
	}
	bellman();
}