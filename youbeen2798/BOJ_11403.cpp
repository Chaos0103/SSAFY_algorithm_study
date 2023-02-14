#include <iostream>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

int n; //정점 개수
vector<int> v[101];
bool visited[101] = { false, };
bool answer_arr[101][101] = { false, };

bool bfs(int start, int end) {
	queue<int> q;
	q.push(start);

	while (!q.empty()) {
		int node = q.front();

		q.pop();

		for (int i = 0; i < v[node].size(); i++) {
			int next_node = v[node][i];

			if (!visited[next_node]) {
				visited[next_node] = true;
				q.push(next_node);
				answer_arr[start][next_node] = true;
			}
		}
	}

	return false;
}
void solution() {

	for (int i = 1; i <= n; i++) {
		int size = v[i].size();
		if (size > 0) {
			for (int j = 0; j < size; j++) {
				memset(visited, false, sizeof(visited));
				int node = v[i][j];
	//			cout << "solution_node: " << node << "\n";
				bfs(i, node); //시작과 끝
			}
		}
	}
}

void output() {
	
	//cout << "########" << "\n";

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cout << answer_arr[i][j] << " ";
		}
		cout << "\n";
	}
}
void input() {
	cin >> n;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			int num;
			cin >> num;
			
			if (num == 1) {
				
				v[i].push_back(j);
			}
		}
	}
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
	output();
}