#include <iostream>
#include <vector>

using namespace std;

int t;
int d; //두께(행 길이)
int w; //가로 크기(열 길이)
int k; //합격 기준
int arr[21][21];
int copy_arr[21][21];
int tc;
int mini_arr[21];
int mini_ans = 100;

void copy() {

	for (int i = 0; i < d; i++) {
		for (int j = 0; j < w; j++) {
			copy_arr[i][j] = arr[i][j];
		}
	}
}

void one_row_spread(int row_num, int type) {
	for (int j = 0; j < w; j++) {
		copy_arr[row_num][j] = type;
	}
}

bool check() {

	for (int j = 0; j < w; j++) {
		bool row_possible = false;
		for (int i = 0; i <= d - k; i++) {
			int num = copy_arr[i][j];
			bool mini_row_possible = true;
			for (int r = i + 1; r < i + k; r++) {
				if (num != copy_arr[r][j]) {
					mini_row_possible = false;
					break;
				}
			}
			if (mini_row_possible) {
				row_possible = true;
				break;
			}
		}
		if (!row_possible) {
			return false;
		}
	}
	return true;
}
void real_spread(vector<int> comb) {
	//실제로 약을 뿌린다. (comb에 있는 모든 행에 뿌린다.)

	copy();

	for (int i = 0; i < comb.size(); i++) {
		one_row_spread(comb[i], mini_arr[i]);
	}

	if (check()) {
		mini_ans = comb.size();
	}
}
void subset(int position, int num, vector<int> comb) {

	if (position == num) {
		//comb는 뿌릴 위치, mini_arr은 뿌릴 약의 종류

		if (mini_ans == 100) {
			real_spread(comb);
		}
		return;
	}

	mini_arr[position] = 0;
	subset(position + 1, num, comb);

	mini_arr[position] = 1;
	subset(position + 1, num, comb);
}
void mini_combination(vector<int> comb) {
	//행들의 집합
	//0 0
	//0 1
	//1 0
	//1 1
	subset(0, comb.size(), comb);
}
void combination(vector<int> arr, vector<int> comb, int r, int depth, int idx) {
	if (mini_ans != 100) {
		return;
	}
	else if (r == 0) {
		//1번 행과 2번 행이 나왔을 때
		mini_combination(comb);
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

	vector<int> arr;

	for (int i = 0; i < d; i++) {
		arr.push_back(i);
	}

	for (int i = 0; i <= k; i++) {
		//i개를 뽑는다.
		vector<int> comb(i);
		combination(arr, comb, i, 0, 0);
	}

	cout << "#" << tc << " " << mini_ans << endl;

}
void input() {

	cin >> t;

	for (tc = 1; tc <= t; tc++) {
		cin >> d >> w >> k;

		for (int i = 0; i < d; i++) {
			for (int j = 0; j < w; j++) {
				cin >> arr[i][j];
			}
		}

		mini_ans = 100;

		if (k == 1) {
			cout << "#" << tc << " " << 0 << endl;
		}
		else {
			solution();
		}
	}
}
int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	input();
}
