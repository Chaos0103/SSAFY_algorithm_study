#include <iostream>
#include <queue>
using namespace std;

int m, n;
int arr[101][101];
bool visited[101][101];

queue < pair<pair<int, int>, int>> q;

int dx[4] = { 1, -1, 0, 0 };
int dy[4] = { 0, 0, 1, -1 };
int ans = 0;

void solution() {

	while (!q.empty()) {
		int a = q.front().first.first; //x
		int b = q.front().first.second; //y
		int days = q.front().second; //일수

		ans = max(ans, days);

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny]) {
				visited[nx][ny] = true;
				q.push({ {nx,ny }, days + 1 });
			}
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (!visited[i][j]) {
				cout << -1;
				exit(0);
			}
		}
	}

	cout << ans;
}
void input() {
	cin >> m >> n; //가로칸 수, 세로칸 수

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> arr[i][j];
			if (arr[i][j] != 0) { //익은 토마토 혹은 토마토가 없다면
				visited[i][j] = true;
			}
			if (arr[i][j] == 1) { //익은 토마토이면
				q.push({ { i,j }, 0 });
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
}