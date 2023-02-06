#include <iostream>
#include <vector>
#include <string.h>
#include <algorithm>
using namespace std;

int real_arr[21][21];
int arr[21][21];
int move_arr[6]; 
int ans = 0;

int n;

void input() {
	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> real_arr[i][j];
		}
	}
}

void real_move(int dir) {
	//실제 방향으로 움직임

	int tmp_arr[21][21] = { 0, };

	if (dir == 1) { //위쪽으로
		for (int j = 0; j < n; j++) {
			vector<int> tmp;
			for (int i = 0; i < n; i++) {
				if (arr[i][j] != 0) {
					tmp.push_back(arr[i][j]);
				}
			}

			vector<int> tmp2;

			for (int i = 0; i < tmp.size(); i++) {
				if (i == tmp.size() -1) {
					tmp2.push_back(tmp.back());
					continue;
				}
				if (tmp[i] != tmp[i + 1]) {
					tmp2.push_back(tmp[i]);
					continue;
				}
				tmp2.push_back(tmp[i] * 2);
				i++;
			}
	
			for (int i = 0; i < tmp2.size(); i++) {
				tmp_arr[i][j] = tmp2[i];
				ans = max(ans, tmp2[i]);
			}
		}
	}
	else if (dir == 2) { //오른쪽으로
		for (int i = 0; i < n; i++) {
			vector<int> tmp;
			for (int j = n - 1; j >= 0; j--) {
				if (arr[i][j] != 0) {
					tmp.push_back(arr[i][j]);
				} 
			}

			vector<int> tmp2;
			
			for (int j = tmp.size() - 1; j >= 0; j--) {
				if (j == 0) {
					tmp2.push_back(tmp[j]);
					continue;
				}
				if (tmp[j] != tmp[j - 1]) {
					tmp2.push_back(tmp[j]);
					continue;
				}
				tmp2.push_back(tmp[j] * 2);
				j--;
			}
		
//			reverse(tmp2.begin(), tmp2.end());

			for (int j = n - 1; j >= 0; j--) {
				if (tmp2.empty()) {
					break;
				}
				tmp_arr[i][j] = tmp2.back();
				ans = max(ans, tmp_arr[i][j]);
				tmp2.pop_back();
			}
		}
	}
	else if (dir == 3) { //아래쪽으로
		for (int j = 0; j < n; j++) {
			vector<int> tmp;
			for (int i = n - 1; i >= 0; i--) {
				if (arr[i][j] != 0) {
					tmp.push_back(arr[i][j]);
				}
			}

			vector<int> tmp2;
			int size = tmp.size() - 1;
			for (int i = size; i >= 0; i--) {
				if (i == 0) {
					tmp2.push_back(tmp[0]);
					continue;
				}
				if (tmp[i] != tmp[i - 1]) {
					tmp2.push_back(tmp[i]);
					continue;
				}
				tmp2.push_back(tmp[i] * 2);
				i--; 
			}
		
//			reverse(tmp2.begin(), tmp2.end());

			for (int i = n - 1; i >= 0; i--) {
				if (tmp2.empty()) {
					break;
				}
				tmp_arr[i][j] = tmp2.back();
				ans = max(ans, tmp_arr[i][j]);
				tmp2.pop_back();
			}
		}
	}
	else if (dir == 4) { //왼쪽으로
		for (int i = 0; i < n; i++) {
			vector<int> tmp;
			for (int j = 0; j < n; j++) {
				if (arr[i][j] != 0) {
	//				cout << arr[i][j] << " ";
					tmp.push_back(arr[i][j]);
				}
			}
	//		cout << "\n";

			int size = tmp.size();

			vector<int> tmp2;
			for (int j = 0; j < size; j++) {
				if (j == size - 1) {
					tmp2.push_back(tmp[j]);
					continue;
				}
				if (tmp[j] != tmp[j + 1]) {
					tmp2.push_back(tmp[j]);
					continue;
				}
				tmp2.push_back(tmp[j] * 2);
				j++;
			}

			for (int j = 0; j < tmp2.size(); j++) {
				tmp_arr[i][j] = tmp2[j];
				ans = max(ans, tmp2[j]);
			}
		}
	}

	
	cout << "###tmp_arr" << "\n";

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << tmp_arr[i][j] << " ";
		}
		cout << "\n";
	}
	


	memcpy(arr, tmp_arr, sizeof(tmp_arr)); // 꼭 써먹기

	
	cout << "###_arr" << "\n";
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "\n";
	}
	

}
void mini_solution(int times) {

	for (int i = 0; i < times; i++) {
		int dir = move_arr[i];
		real_move(dir);
	}
}
void move_times(int current_time, int times) {
	//현재 움직인 횟수, 움직일 목표 횟수
	if (current_time == times) {
//		cout << "총 움직인 방향" << "\n";
		for (int i = 0; i < times; i++) {
			memcpy(real_arr,arr, sizeof(arr));
			mini_solution(times);
//			cout << move_arr[i] << " ";
		}
//		cout << "\n";
		return;
	}
	
	for (int dir = 1; dir <= 4; dir++) {
		move_arr[current_time] = dir; //move_arr[0] = 1;
		move_times(current_time + 1, times); 
	}
}
void solution() {
	//위 아래 왼쪽 오른쪽
	//1, 2, 3, 4 로 만들 수 있는 모든 순열의 수

	move_times(0, 5);
	/*
	for (int i = 1; i <= 5; i++) {
		//1번부터 5번까지 횟수
		move_times(0,i);
	}
	*/
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();

	memcpy(arr, real_arr, sizeof(real_arr));

	real_move(2);
	real_move(1);
	real_move(2);
	real_move(1);

//	real_move(3);
//	solution();
	cout <<  ans;
}