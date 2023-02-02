#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

int w, h;
int arr[51][51];
bool visited[51][51];

int dx[8] = { 1,-1,0,0, 1,  1, -1, -1 };
int dy[8] = { 0,0,1,-1, -1, 1, -1,  1 };

void bfs(int startx, int starty) {
	queue <pair<int, int>> q;

	q.push({ startx,starty });
	visited[startx][starty] = true;

	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;

		q.pop();

		for (int i = 0; i < 8; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			if (0 <= nx && nx < h && 0 <= ny && ny < w && !visited[nx][ny] && arr[nx][ny] == 1) {
				q.push({ nx,ny });
				visited[nx][ny] = true;
			}
		}
	}
}
void input() {

	while (true) {
		memset(visited, false, sizeof(visited));
		int cnt = 0;

		cin >> w >> h; //너비와 높이

		if (w == 0 && h == 0) {
			break;
		}

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				cin >> arr[i][j];
			}
		}

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					cout << "i: " << i << " j: " << j << "\n";
					bfs(i, j);
					cnt++;
				}
			}
		}
		cout << "#####cnt: " << cnt << "\n";
	}
	
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input(); 
}