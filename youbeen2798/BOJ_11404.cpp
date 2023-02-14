#include <iostream>
#include <vector>
#include <queue>
#define INF 999999999;
using namespace std;

int v, e; //������ ����, ������ ����
int k; //���� ������ ��ȣ

struct connection {
	int destination; //���� ��� ��ȣ
	int dist; //�Ÿ�
};

vector<connection> nodes[20001]; //���������� (����, �Ÿ�)
int Dist[20001];

void dijkstra() {
	//k �������� destination���� �Ÿ�

	priority_queue<pair<int, int>> q; //���� �Ÿ�, ���� ����
	q.push({ 0,k });

	for (int i = 1; i <= v; i++) {
		Dist[i] = INF;
	}

	Dist[k] = 0;

	while (!q.empty()) {
		int dist = -q.top().first; //���� �Ÿ�
		int node = q.top().second; //����

		q.pop();

		for (int i = 0; i < nodes[node].size(); i++) {
			int next_node = nodes[node][i].destination; //���� ������ //2
			int next_dist = nodes[node][i].dist; //���� ���������� �Ÿ� //2

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
	cin >> v >> e; //������ ����, ������ ����
	cin >> k;

	for (int i = 0; i < e; i++) {
		int u, v, w;
		cin >> u >> v >> w; //u���� v�� ���� ����ġ w�� ������ �����Ѵ�.
		//u�� v�� �ٸ��� w�� 10 ������ �ڿ���
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