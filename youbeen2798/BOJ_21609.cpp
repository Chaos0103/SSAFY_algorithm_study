#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;

int n, m; //격자 한변의 크기, 색상의 개수
int arr[21][21]; //격자
int visited[21][21];
int score = 0;

int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
int cnt = 0;

pair<pair<int, int>, pair<int, int>> bfs(int x, int y) {
	//기준 블록 x,y && 블록집합 사이즈, 무지개 블록

	memset(visited, false, sizeof(visited));

	queue <pair<int, int>> q;
	q.push({ x,y });
	int num = arr[x][y]; //블록 색깔
	visited[x][y] = cnt;

	int muzigae_num = 0;
	int total_num = 1;

	vector<pair<int, int>> general_blocks; //일반 블록 모음
	general_blocks.push_back({ x,y });

	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			if (0 <= nx && nx < n && 0 <= ny && ny < n && visited[nx][ny] != cnt) {

				if (arr[nx][ny] == num) { //일반 블록이면
					q.push({ nx,ny });
					general_blocks.push_back({ nx,ny }); //일반 블록 모음에 추가
					total_num++; //전체 개수
					visited[nx][ny] = cnt;
				}
				else if (arr[nx][ny] == 0) { //무지개 블록이면
					q.push({ nx,ny });
					visited[nx][ny] = cnt;
					muzigae_num++; //무지개 개수
					total_num++; //전체 개수
				}
			}
		}
	}

	sort(general_blocks.begin(), general_blocks.end());

	pair<int, int> gizun_block = general_blocks[0]; //기준 블록

	return { gizun_block, { total_num, muzigae_num } };
}

void bfs2(int x, int y) {

	queue<pair<int, int>> q;

	q.push({ x,y });
	visited[x][y] = true;
	int num = arr[x][y];
	arr[x][y] = -2;
	int tmp_num = 1;

	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) {
				if (arr[nx][ny] == num || arr[nx][ny] == 0) {
					arr[nx][ny] = -2; //제거
					q.push({ nx,ny });
					visited[nx][ny] = true;
					tmp_num++;
				}
			}
		}
	}

	score += tmp_num * tmp_num;

}

void print() {

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "\n";
	}

}
void find_biggest_block_group() {
	//크기가 가장 큰 블록 그룹을 찾는다.

	memset(visited, false, sizeof(visited));

	int x;
	int y;
	pair<int, int> gizun_block = { -1,-1 };
	int total_num = -1;
	int muzigae_num = -1;

	cnt = 0;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (1 <= arr[i][j] && arr[i][j] <= m && visited[i][j] == 0) {
				//방문하지 않은, 일반 블록
				cnt++;
				pair<pair<int, int>, pair<int, int>> p = bfs(i, j);
				pair<int, int> tmp_gizun_block = p.first; //기준 블록
				int tmp_total_num = p.second.first; //전채 개수
				int tmp_muzigae_num = p.second.second; //무지개 개수
				if (tmp_total_num == 1) { //만약 개수가 1개라면
					continue;
				}
				if (total_num == -1 || total_num < tmp_total_num) {
					//전체 블록 개수가 작거나
					gizun_block = tmp_gizun_block;
					total_num = tmp_total_num;
					muzigae_num = tmp_muzigae_num;
					x = i;
					y = j;
				}
				else if (total_num == tmp_total_num) {
					//전체 블록 개수가 같다면
					if (tmp_muzigae_num > muzigae_num) {
						gizun_block = tmp_gizun_block;
						total_num = tmp_total_num;
						muzigae_num = tmp_muzigae_num;
						x = i;
						y = j;
					}
					else if (tmp_muzigae_num == muzigae_num) {
						//무지개 개수가 같다면
						if (tmp_gizun_block > gizun_block) {
							gizun_block = tmp_gizun_block;
							total_num = tmp_total_num;
							muzigae_num = tmp_muzigae_num;
							x = i;
							y = j;
						}
					}
				}
			}
		}
	}

	if (total_num == -1) {
		cout << score;
		exit(0);
	}
	memset(visited, false, sizeof(visited));
	bfs2(x, y);
}

pair<int, int> down(int x, int y) {

	pair<int, int> start = { x,y };

	for (int i = x + 1; i < n; i++) {
		if (arr[i][y] != -2) { //검은색 블록 혹은 일반 블록 혹은 무지개 블록이 있다면
			return start;
		}
		start = { i,y };
	}
	return start;
}
void gravity() {
	for (int j = 0; j < n; j++) {
		for (int i = n - 1; i >= 0; i--) {
			int num = arr[i][j];
			if (num == -1 || num == -2) { //검은색 블록이면
				continue;
			}
			pair<int, int> next_position = down(i, j);
			arr[i][j] = -2; //빈칸처리
			arr[next_position.first][next_position.second] = num; //숫자 채우기
		}
	}
}

void rotate() {

	int arr2[100][100];

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			arr2[i][j] = arr[j][abs(n - 1 - i)];
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			arr[i][j] = arr2[i][j];
		}
	}
}
void solution() {
	//검은색블록: -1
	//무지개블록: 0
	//기준 블록 : 무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작고, 열의 번호가 가장 작은 블록

	while (true) {
		find_biggest_block_group();
		gravity();
		rotate(); //반시계 방향으로 회전
		gravity(); //다시 중력
	}

}
void input() {
	cin >> n >> m;

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