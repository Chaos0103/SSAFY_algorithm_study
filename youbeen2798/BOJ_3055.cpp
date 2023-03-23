#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
#include <algorithm>

using namespace std;

int r;
int c;
char arr[51][51];
bool visited[51][51];
int water[51][51];
int gosum[51][51];

pair<int, int> biber;
pair<int, int> gosumdochi;
int times = 0;

//비어있는 곳 : .
//물이 차있는 지역 : *
//돌 : X

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

void water_spread() {

	queue<pair<pair<int, int>, int>> q;

	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			//물이 차있다면
			if (arr[i][j] == '*') {
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];

					if (0 <= nx && nx < r && 0 <= ny && ny < c && (arr[nx][ny] == '.' || arr[nx][ny] == 'S')) {
						q.push({{ nx,ny }, 1});
						visited[nx][ny] = true;
					}
				}
			}
		}
	}


	while (!q.empty()) {
		int a = q.front().first.first;
		int b = q.front().first.second;
		int time = q.front().second; //시간
		water[a][b] = time;
		
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];
			if (0 <= nx && nx < r && 0 <= ny && ny < c && (arr[nx][ny] == '.' || arr[nx][ny] == 'S') && !visited[nx][ny]) {
				q.push({ {nx,ny}, time + 1 });
				visited[nx][ny] = true;
			}
		}
	}
	
	/*
	cout << "\n" << "물" << "\n";
	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			cout << water[i][j] << " ";
		}
		cout << "\n";
	}
	*/
	
}

void gosumdochi_move() {
	//고슴도치가 이동한다.

	memset(visited, false, sizeof(visited));

	int gosumdochi_x = gosumdochi.first;
	int gosumdochi_y = gosumdochi.second;

	queue<pair<pair<int, int>, int>> q;
	q.push({ { gosumdochi_x, gosumdochi_y }, 0 });

	while (!q.empty()) {
		int a = q.front().first.first;
		int b = q.front().first.second;
		int time = q.front().second;
		gosum[a][b] = time;

		
		if (arr[a][b] == 'D') {		

			cout << time;
			exit(0);
		}
		

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			if (0 <= nx && nx < r && 0 <= ny && ny < c && (arr[nx][ny] == '.' || arr[nx][ny] == 'D') && !visited[nx][ny] && ((water[nx][ny] > time + 1) || water[nx][ny] == 0)) {
				q.push({ { nx,ny }, time + 1 });
				visited[nx][ny] = true;
			}
		}
	}


	
}
void solution() {

	water_spread();
	gosumdochi_move();

	int ans = 999;
	for (int i = 0; i < 4; i++) {
		int nx = biber.first + dx[i];
		int ny = biber.second + dy[i];

		if (0 <= nx && nx < r && 0 <= ny && ny < c && ((water[nx][ny] > gosum[nx][ny]) || water[nx][ny] == 0) && gosum[nx][ny] != 0) {
			ans = min(ans, gosum[nx][ny]);
		}
	}

	if (ans == 999) {
		cout << "KAKTUS";
	}
	else {
		cout << ans + 1;
	}
}

void input() {

	cin >> r >> c;

	for (int i = 0; i < r; i++) {
		string st;
		cin >> st;
		for (int j = 0; j < c; j++) {
			arr[i][j] = st[j];
			if (arr[i][j] == 'D') {
				biber = { i,j }; //비버
			}
			else if (arr[i][j] == 'S') {
				gosumdochi = { i,j }; //고슴도치
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