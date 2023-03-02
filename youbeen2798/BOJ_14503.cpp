#include <iostream>
#include <cstring>
using namespace std;

int n, m, r, c, d;
int arr[51][51];
bool visited[51][51] = { false, };
int answer = 0;

int dx[4] = { -1, 0, 1,  0 }; // 북 동 남 서
int dy[4] = { 0, 1, 0,  -1 }; // 북 동 남 서

void input() {
	memset(visited, false, sizeof(visited));

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> arr[i][j];
		}
	}

	visited[r][c] = true;
	answer++;
}

void solution() {

	for (int i = 0; i < 4; i++) { //왼쪽으로 회전하면서 빈 방이면서 방문하지 않은 칸 찾기

		int next_d = (d + 3 - i) % 4;
		int next_r = r + dx[next_d]; //행
		int next_c = c + dy[next_d]; //열

		if (next_r < 0 || next_r >= n || next_c < 0 || next_c >= m || arr[next_r][next_c] == 1) {
			continue;
		}

		if (arr[next_r][next_c] == 0 && visited[next_r][next_c] == false) { //청소할 공간을 찾으면

			r = next_r;
			c = next_c;
			d = next_d;
			visited[r][c] = true;

			//			cout << "d: " << d << "\n";
			//			cout << "r: " << r << "c: " << c << "\n";

			answer++;
			solution();
		}
	}

	//네 칸 모두 청소가 이미 되어있거나 벽인 경우

	int back_idx;

	if (d <= 1) {
		back_idx = d + 2;
	}
	else {
		back_idx = d - 2;
	}

	int back_r = r + dx[back_idx]; //다음 행 ( 후진 ) 
	int back_c = c + dy[back_idx]; //다음 열 ( 후진 )

	if (back_r >= 0 && back_r < n && back_c >= 0 && back_c < m) {

		if (arr[back_r][back_c] == 0) {
			//			cout << "r: " << r << "c: " << c << "\n";
			r = back_r; //다음 행
			c = back_c; //다음 열
			solution();
		}

		else {
			cout << answer;
			exit(0);
		}
	}
}


int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	cin >> n >> m >> r >> c >> d;

	input();
	solution();
}
/*
set s1, set s2

for(int i = 0; 

*/