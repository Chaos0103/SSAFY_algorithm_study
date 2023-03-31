#include <iostream>
#include <queue>
#include <cstring>

using namespace std;

int l, w;
char arr[51][51];
bool visited[51][51];
int max_dist = 0;

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

struct location {
	int x;
	int y;
	int cnt;
};

void reset() {

	memset(visited, false, sizeof(visited));
	for (int i = 0; i < l; i++) {
		for (int j = 0; j < w; j++) {
			if (arr[i][j] == 'W') {
				visited[i][j] = true;
			}
		}
	}
}
void input() {
	cin >> l >> w;

	for (int i = 0; i < l; i++) {
		string st;
		cin >> st;
		for (int j = 0; j < st.size(); j++) {
			arr[i][j] = st[j];
		}
	}
	
}

void bfs(int x, int y) {
	queue <pair<location, int>> q;
	q.push({ {x,y}, 0 });
	visited[x][y] = true;

	while (!q.empty()) {
		int x = q.front().first.x;
		int y = q.front().first.y;
		int cnt = q.front().second;
		max_dist = max(max_dist, cnt);

		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (0 <= nx && nx < l && 0 <= ny && ny < w && !visited[nx][ny]) {
				q.push({ {nx,ny}, cnt + 1 });
				visited[nx][ny] = true;
			}
		}
	}
	
}
void solution() {
	for (int i = 0; i < l; i++) {
		for (int j = 0; j < w; j++) {
			if (arr[i][j] == 'L') {
				reset();
				bfs(i,j);
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
	cout << max_dist;

}