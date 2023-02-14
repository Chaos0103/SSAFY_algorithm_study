#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n; //���� ����
int m; //���� ����
int k; //�Ÿ� ����
int x; //��� ���� ��ȣ

vector<int> v[1000001];
int dists[1000001];
vector<int> answers;


void print() {

	for (int i = 1; i <= n; i++) {
		if (dists[i] == k) {
			answers.push_back(i);
		}
	}

	if (answers.size() == 0) {
		cout << -1;
		exit(0);
	}
	
	for (int i = 0; i < answers.size(); i++) {
		cout << answers[i] << "\n";
	}
}
void solution() {
	queue<pair<int, int>> q;

	q.push({ 0, x });

	for (int i = 1; i <= n; i ++) {
		dists[i] = 999999999;
	}

	dists[x] = 0;

	while (!q.empty()) {
		int dist = - q.front().first;  //�Ÿ�
		int node = q.front().second; //���

		q.pop();

		for (int i = 0; i < v[node].size(); i++) {
			int next_node = v[node][i];
			int next_dist = dist + 1;

			if (next_dist < dists[next_node]) {
				dists[next_node] = next_dist;
				q.push({ -next_dist, next_node });
			}
			
		}
	}
}

void input() {
	cin >> n >> m >> k >> x;

	for (int i = 0; i < m; i++) {
		int a, b; 
		cin >> a >> b; //a���� b�� �̵��ϴ� �ܹ��� ����
		v[a].push_back(b);
	}
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
	print();
}