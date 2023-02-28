#include <iostream>
#include <queue>
#include <cstring>
#include <cmath>

using namespace std;

int n, q;
int arr[101][101];
bool can_melt[101][101]; //녹을 수 있는지 확인
bool visited[101][101];

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
int real_ans = 0;

void rotate(int x, int y, int num) {
	//점 (x,y)를 num만큼 회전

	int tmp[101][101];

	for (int i = 0; i < num; i++) {
		for (int j = 0; j < num; j++) {
			tmp[i][j] = arr[x + num - 1 - j][y + i];
		}
	}

	for (int i = 0; i < num; i++) {
		for (int j = 0; j < num; j++) {
			arr[x + i][y + j] = tmp[i][j];
		}
	}
}

bool check_melt(int x, int y) {

	int cnt = 0;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] > 0) {
			cnt++;
		}
	}

	//얼음이 있는 칸이 3개가 안 넘으면
	if (cnt < 3) {
		return true;
	}
	return false;
}
void melt() {


	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			can_melt[i][j] = check_melt(i, j);
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			//녹을 수 있으며, 얼음이 존재한다면
			if (can_melt[i][j] && arr[i][j] >= 1) {
				arr[i][j]--;
			}
		}
	}
}

void mini_solution(int num) {


	int k = pow(2, num);
	//모든 부분 격자를 시계 방향으로 90도 회전
	for (int i = 0; i < n; i += k) {
		for (int j = 0; j < n; j += k) {
			rotate(i, j, k);
		}
	}

	//얼음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양이 1 줄어든다.
	memset(can_melt, false, sizeof(can_melt));

	melt();
}

int dfs(int x, int y) {

	int ans = 1;
	queue <pair<int, int>> q;

	q.push({ x,y });
	visited[x][y] = true;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] > 0 && !visited[nx][ny]) {
				ans++;
				q.push({ nx,ny });
				visited[nx][ny] = true;
			}
		}
	}

	return ans;
}

void output() {

	int sum = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			sum += arr[i][j];
		}
	}
	cout << sum << "\n";

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (!visited[i][j] && arr[i][j] > 0) {
				real_ans = max(real_ans, dfs(i, j));
			}
		}
	}

	cout << real_ans;
}

void input() {
	cin >> n >> q;

	n = pow(2, n);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
		}
	}

	for (int i = 0; i < q; i++) {
		int num;
		cin >> num;
		mini_solution(num);
	}

}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	output();
}
