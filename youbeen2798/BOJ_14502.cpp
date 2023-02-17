#include <iostream>
#include <vector>
#include <queue>
#include <cstring>

using namespace std;

int n;
int m;
int map[9][9];
bool visited[9][9];
bool spread[9][9];
int ans = 0;

vector<pair<int, int>> empty_room; //빈 방

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

void spread_bfs(int x, int y) {

	queue<pair<int, int>> q;
	q.push({ x,y });

	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			//빈칸이고, 방문을 하지 않았다면
			if (0 <= nx && nx < n && 0 <= ny && ny < m && !spread[nx][ny] && map[nx][ny] == 0) {

				spread[nx][ny] = true;
				q.push({ nx,ny });
			}
		}
	}
}

void count_safe_zone() {

	int tmp_cnt = 0;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (map[i][j] == 0 && !spread[i][j]) {
				tmp_cnt++;
			}
		}
	}

	ans = max(ans, tmp_cnt);
}
void check() {

	memset(spread, false, sizeof(spread));

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (map[i][j] == 2) {
				spread_bfs(i, j);
			}
		}
	}

	count_safe_zone();
}
void combination(vector<pair<int, int>> arr, vector<pair<int, int>> comb, int r, int depth, int idx) {
	if (r == 0) {
		for (int i = 0; i < comb.size(); i++) {
			map[comb[i].first][comb[i].second] = 1; //빈칸 벽으로 막기
		}
		check();

		for (int i = 0; i < comb.size(); i++) {
			map[comb[i].first][comb[i].second] = 0; //원래대로 돌려놓기
		}
		return;
	}
	else if (arr.size() == depth) {
		return;
	}
	else {
		comb[idx] = arr[depth];
		combination(arr, comb, r - 1, depth + 1, idx + 1);
		combination(arr, comb, r, depth + 1, idx);
	}
}
void solution() {

	vector<pair<int, int>> comb(3);

	combination(empty_room, comb, 3, 0, 0);
}

void input() {

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> map[i][j];
			if (map[i][j] == 0) {
				empty_room.push_back({ i,j });
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
	cout << ans;
}