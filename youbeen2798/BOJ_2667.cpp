#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

int n;
int arr[26][26];
bool visited[26][26];

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
vector<int> v;

void bfs(int x, int y) {
	queue<pair<int, int>> q;
	visited[x][y] = true;
	q.push({ x,y });


	int tmp_cnt = 1;

	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;

		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			if (0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] == 1 && !visited[nx][ny]) {
				q.push({ nx,ny });
				visited[nx][ny] = true;
				tmp_cnt++;
			}
		}
	}
	
	v.push_back(tmp_cnt);
}
void solution() {
	
	int count = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!visited[i][j] && arr[i][j] == 1) {
				bfs(i, j);
				count++;
			}
		}
	}
	cout << count << "\n";

	sort(v.begin(), v.end());

	for (int i = 0; i < v.size(); i++) {
		cout << v[i] << "\n";
	}
}

void input() {
	cin >> n;

	for (int i = 0; i < n; i++) {
		string st;
		cin >> st;

		for (int j = 0; j < st.size(); j++) {
			arr[i][j] = st[j] - '0';
		}
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	input();
	solution();
}