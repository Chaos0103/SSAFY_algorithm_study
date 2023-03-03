#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

int n; //세로 크기
int m; //가로 크기
int k; //이동 회수

int arr[21][21];
int score = 0;
int move_dir = 0; //주사위 이동방향
int dice_x = 1;
int dice_y = 1;
//순서 : 동 남 서 북
int dx[4] = { 0,1,0,-1 };
int dy[4] = { 1,0,-1,0 };
int dice[6] = { 2,4,1,3,5,6 };
bool visited[21][21];

void rotate_dice() {
	//주사위를 회전시킨다. 
	if (move_dir == 0) {
		//동쪽으로 회전했으면
		int tmp_dice[6] = { dice[0], dice[5], dice[1], dice[2], dice[4], dice[3] };

		for (int i = 0; i < 6; i++) {
			dice[i] = tmp_dice[i];
		}
	}
	else if (move_dir == 1) {
		//남쪽으로 회전했으면
		int tmp_dice[6] = { dice[5], dice[1], dice[0], dice[3], dice[2], dice[4] };

		for (int i = 0; i < 6; i++) {
			dice[i] = tmp_dice[i];
		}
	}
	else if (move_dir == 2) {
		//서쪽으로 회전했으면
		int tmp_dice[6] = { dice[0], dice[2], dice[3], dice[5], dice[4], dice[1] };

		for (int i = 0; i < 6; i++) {
			dice[i] = tmp_dice[i];
		}
	}
	else if (move_dir == 3) {
		//북쪽으로 회전했으면
		int tmp_dice[6] = { dice[2], dice[1], dice[4], dice[3], dice[5], dice[0] };

		for (int i = 0; i < 6; i++) {
			dice[i] = tmp_dice[i];
		}
	}
}
pair<pair<int, int>, int> move() {
	//주사위가 이동 방향으로 한 칸 굴러간다.
	//리턴 값 : 이동한 위치 칸의 수
	int nx = dice_x + dx[move_dir];
	int ny = dice_y + dy[move_dir];

	//이동 방향으로 이동한다면 주사위 이동
	if (1 <= nx && nx <= n && 1 <= ny && ny <= m) {
		dice_x = nx;
		dice_y = ny;

		rotate_dice(); //주사위 이동 방향에 따라 모양 변형시키기

		pair<pair<int, int>, int> return_p = { {dice_x, dice_y}, arr[dice_x][dice_y] };
		return  return_p;
	}
	else {
		// 이동 방향에 칸이 없다면 이동 방향을 반대로 한다.
		if (move_dir <= 1) {
			move_dir += 2;
		}
		else {
			move_dir -= 2;
		}

		nx = dice_x + dx[move_dir];
		ny = dice_y + dy[move_dir];

		dice_x = nx;
		dice_y = ny;

		rotate_dice();
		pair<pair<int, int>, int> return_p = { {dice_x, dice_y}, arr[dice_x][dice_y] };
		return return_p;
	}
}

void make_decision_dice_dir() {
	//주사위 아랫면에 있는 정수 A와 주사위가 있는 칸 (x,y)에 있는 정수 B를 비교해 이동 방향을 결정한다.

	int bottom = dice[5]; //주사위 아랫면에 있는 정수
	int can = arr[dice_x][dice_y]; //주사위가 있는 칸(x,y)에 있는 정수


	//	cout << "move_dir: " << move_dir << "\n";
	if (bottom > can) {
		//이동 방향을 90도 시계 방향으로 회전
		move_dir = (move_dir + 1) % 4;
	}
	else if (bottom < can) {
		//이동 방향을 90도 반시계 방향으로 회전
		if (1 <= move_dir) {
			move_dir -= 1;
		}
		else {
			move_dir += 3;
		}
	}
	//	cout << "move_dir: " << move_dir << "\n";

}

int bfs(int can) {
	queue<pair<int, int>> q;

	q.push({ dice_x, dice_y });
	visited[dice_x][dice_y] = true;

	int cnt = 1;
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;

		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (1 <= nx && nx <= n && 1 <= ny && ny <= m && arr[nx][ny] == can && !visited[nx][ny]) {
				q.push({ nx,ny });
				visited[nx][ny] = true;
				cnt++;
			}
		}
	}

	return cnt;
}
void solution() {

	for (int i = 0; i < k; i++) {

		memset(visited, false, sizeof(visited));

		pair<pair<int, int>, int> p = move(); //주사위가 굴러간다.
		//		cout << "주사위 위치: " << p.first.first << " , " << p.first.second << "\n";
		int can = p.second;

		int can_move_num = bfs(can);

		score += can * can_move_num;

		make_decision_dice_dir();

		//		cout << "주사위 방향: " << move_dir << "\n";
				 //주사위 다음 이동 방향 결정


	}
	cout << score;
}

void input() {
	cin >> n >> m >> k; //세로 크기, 가로 크기, 이동 회수

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
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
//1 2 4 5 5 6 6 7
//ST=3 EN=7
//MID = 5 EN = 6 