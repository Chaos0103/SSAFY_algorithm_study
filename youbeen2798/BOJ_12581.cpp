#include <iostream>
using namespace std;

int n; //수빈 위치
int k; //동생 위치

pair<int, int> v[100001];
// 시간과 횟수

void reset() {
	for (int i = 0; i <= 100000; i++) {
		v[i] = { 999999, 0 };
	}
}

bool inrange(int time) {
	if (0 <= time && time <= 100000) {
		return true;
	}
	return false;
}
void dfs(int position, int time) {
	//시간과 횟수

	if (position > k + 1) {
		return;
	}

	/*
	if (position == 17 && time == 3) {
		cout << "########" << "\n";
	}

	if (v[17].first == 3) {
		cout << "#########" << "\n";
	}
	cout << "position: " << position << " time: " << time << " " << v[position].second << "\n";
	*/

	if (inrange(position - 1)) { //범위 내 존재하고
		if (time + 1< (v[position - 1].first)) {
			v[position - 1].first = time + 1;
			v[position - 1].second = 1;
			dfs(position - 1, time + 1);
		}
		else if (time + 1 == v[position - 1].first) {
			v[position - 1].second++;
			dfs(position - 1, time + 1);
		}
	}

	if (inrange(position + 1)) {
		if (time + 1 < (v[position + 1].first)) {
			v[position + 1].first = time + 1;
			v[position + 1].second = 1;
			dfs(position + 1, time + 1);
		}
		else if (time + 1 == v[position + 1].first) {
			v[position + 1].second++;
			dfs(position + 1, time + 1);
		}
	}

	if (inrange(position * 2)) {
		if (time + 1 < (v[position * 2].first)) {
			v[position * 2].first = time + 1;
			v[position * 2].second = 1;
			dfs(position * 2, time + 1);
		}
		else if (time + 1 == v[position * 2].first) {
			v[position * 2].second++;
			dfs(position * 2, time + 1);
		}
	}
}
void solution() {
	reset();

	dfs(n, 0);

	cout << v[k].first << "\n" << v[k].second;
}


void input() {
	cin >> n >> k;
}

int main() {

	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}