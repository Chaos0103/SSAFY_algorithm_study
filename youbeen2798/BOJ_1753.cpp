#include <iostream>
#include <vector>
#include <queue>
#define INF 999999999;
using namespace std;

int v, e; //정점의 개수, 간선의 개수
int k; //시작 정점의 번호

struct connection {
	int destination; //다음 노드 번호
	int dist; //거리
};

vector<connection> nodes[20001]; //시작정점과 (끝점, 거리)
int Dist[20001];

void dijkstra() {
	//k 정점부터 destination까지 거리

	priority_queue<pair<int, int>> q; //현재 거리, 현재 정점
	q.push({ 0,k });

	for (int i = 1; i <= v; i++) {
		Dist[i] = INF;
	}

	Dist[k] = 0;

	while (!q.empty()) {
		int dist = -q.top().first; //현재 거리
		int node = q.top().second; //정점

		q.pop();

		for (int i = 0; i < nodes[node].size(); i++) {
			int next_node = nodes[node][i].destination; //다음 목적지 //2
			int next_dist = nodes[node][i].dist; //다음 목적지까지 거리 //2

			int total_dist = dist + next_dist;
			if (total_dist < Dist[next_node]) {
				Dist[next_node] = total_dist;
				q.push({ -total_dist , next_node });
			}
		}
	}

	for (int i = 1; i <= v; i++) {
		if (Dist[i] == 999999999) {
			cout << "INF" << "\n";
			continue;
		}
		cout << Dist[i] << "\n";
	}


}


void input() {
	cin >> v >> e; //정점의 개수, 간선의 개수
	cin >> k;

	for (int i = 0; i < e; i++) {
		int u, v, w;
		cin >> u >> v >> w; //u에서 v로 가는 가중치 w인 간선이 존재한다.
		//u와 v는 다르며 w는 10 이하의 자연수
		nodes[u].push_back({ v, w });
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	dijkstra();
}