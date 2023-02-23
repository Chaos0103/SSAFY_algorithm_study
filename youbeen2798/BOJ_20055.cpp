#include <iostream>
#include <deque>

using namespace std;

int n, k;
int arr[201];
int ans = 0;

deque <pair<int, bool>> dq;

void one_move() {
	//벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
	pair<int, bool> tmp = dq.back();
	dq.pop_back();
	dq.push_front(tmp); //앞으로 한칸 이동

	if (dq[n - 1].second) { // N번 칸에 로봇이 있다면
		dq[n - 1].second = false; //로봇을 즉시 내린다
	}
}

void robot_move() {

	pair<int, bool> before = dq[n - 2];
	pair<int, bool> after = dq[n - 1];

	//로봇이 없으며 내구도가 1이상 남아 있으며
	if (after.first >= 1 && !after.second && before.second) {
		dq[n - 2].second = false; //로봇 옮기고 제거
		dq[n - 1].first -= 1; //칸의 내구도 1 감소
	}
	for (int i = n - 2; i >= 0; i--) {
		pair<int, bool> now = dq[i];
		pair<int, bool> next = dq[i + 1]; //다음 위치 정보
		if (next.first >= 1 && now.second && !next.second) {
			// 현재 위치가 로봇이 있고
			// 다음 위치가 내구도가 1이상, 로봇이 없다면
			//로봇 이동
			dq[i].second = false;
			dq[i + 1].second = true;
			dq[i + 1].first -= 1; //칸의 내구도 1감소
		}
	}
}

void put_robot() {
	if (dq[0].first > 0) { //올리는 위치의 칸의 내구도가 0이 아니면
		dq[0].first -= 1; //내구도 1 감소
		dq[0].second = true; //로봇을 올린다.
	}
}

void check() {
	int num = 0;
	for (int i = 0; i < dq.size(); i++) {
		if (dq[i].first == 0) {
			num++;
		}
	}
	ans++;

	if (num >= k) {
		cout << ans;
		exit(0);
	}
}
void solution() {

	while (true) {
		one_move(); //한 칸 회전
		robot_move();
		put_robot();
		check();
	}
}

void input() {
	cin >> n >> k; //3 2

	for (int i = 0; i < 2 * n; i++) {
		int num;
		cin >> num; //1 2 1 2 1 2
		dq.push_back({ num, false });
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}