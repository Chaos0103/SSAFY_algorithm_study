#include <iostream>
#include <queue>
using namespace std;

int n, m;
int arr[301][301];
bool visited[301][301]; 

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

void bfs(int x, int y) {

	queue < pair<int, int>> q;
	q.push({ x,y });
	visited[x][y] = true;

	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny] && arr[nx][ny] > 0) {
				visited[nx][ny] = true;
				q.push({ nx,ny });
			}
		}
	}
}

bool over_two() {

	int ans = 0;

	memset(visited, false, sizeof(visited));

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (arr[i][j] > 0 && !visited[i][j]) {
				bfs(i, j);
				ans++;
			}
		}
	}

	if (ans >= 2) {
		return true;
	}
	return false;
}

int check_around(int x, int y) {

	int tmp = 0;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (0 <= nx && nx < n && 0 <= ny && ny < m && arr[nx][ny] == 0) {
			tmp++;
		}
	}
	return tmp;
}

void copy(int tmp[301][301]) {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			arr[i][j] = tmp[i][j];
		}
	}
}
void ice_melt() {

	int tmp[301][301] = { 0 };

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (arr[i][j] > 0) {
				tmp[i][j] = arr[i][j] - check_around(i, j);

				if (tmp[i][j] < 0) {
					tmp[i][j] = 0;
				}
			}
		}
	}
	copy(tmp);
}

void print() {
	
	cout << "######" << "\n";

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "\n";
	}
}

bool all_melt() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (arr[i][j] > 0) {
				return false;
			}
		}
	}
	return true;
}
void solution() {

	int time_answer = 0;

	while (true) {

		if (over_two()) { //빙산이 2개 이상이면
			cout << time_answer;
			exit(0);
		}
		if (all_melt()) {
			cout << 0;
			exit(0);
		}

		ice_melt(); //얼음 녹음

		print();
		time_answer++; //시간 1개 늘어남

//		print(); //출력
	}
}

void input() {
	cin >> n >> m; //행의 개수와 열의 개수

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> arr[i][j];
		}
	}

//	print(); //출력

}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}