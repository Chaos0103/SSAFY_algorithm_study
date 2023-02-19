#include <iostream>
#include <queue>
#include <cstring>
#include <vector>

using namespace std;

int n, l, r;
int arr[51][51];
bool visited[51][51];
bool changed = false;

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

void reset() {
	changed = false;
	memset(visited, false, sizeof(visited));
}

void bfs(int x, int y) {
	
	vector<pair<int, int>> v; //연결된 나라들
	int sum = 0; //연결된 나라들 합

	v.push_back({ x,y });
	queue<pair<int, int>> q;
	visited[x][y] = true;
	sum += arr[x][y];
	q.push({ x,y });

	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;
		int num = arr[a][b];

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];
			int num2 = arr[nx][ny];
			int diff = abs(num - num2);

			//방문한적 없고 차이가 l과 r 사이일때
			if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) {
				if (l <= diff && diff <= r) {
					q.push({ nx,ny });
					v.push_back({ nx,ny });
					visited[nx][ny] = true;
					sum += arr[nx][ny];
				}
			}
		}
	}

	int avg = sum / v.size(); //나라 평균

	if (v.size() > 1) {
		changed = true;
	}
	for (int i = 0; i < v.size(); i++) {
		arr[v[i].first][v[i].second] = avg;
	}
}

void solution() {

	int answer = 0;
	while (true) {

		reset();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
				}
			}
		}

		if (!changed) {
			break;
		}

		answer++;
	}

	cout <<  answer;
}

void input() {
	cin >> n >> l >> r;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
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