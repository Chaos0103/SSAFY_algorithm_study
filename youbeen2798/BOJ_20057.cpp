#include <iostream>

using namespace std;

int n;
int original_amount = 0;
long arr[500][500];
int current_x ;
int current_y;

int spread(int total_amount, int x, int y, int amount) {
	
	int spread_amount = total_amount * amount / 100;
	if (0 <= x && x < n && 0 <= y && y < n) {
		arr[x][y] += spread_amount;
	}
	return spread_amount;
}

void left() {

	int total_num = arr[current_x][current_y - 1];

	arr[current_x][current_y - 1] = 0;

	int tmp = 0;
	
	tmp += spread(total_num, current_x - 1, current_y, 1);
	tmp += spread(total_num, current_x + 1, current_y, 1);

	tmp += spread(total_num, current_x - 1, current_y - 1, 7);
	tmp += spread(total_num, current_x + 1, current_y - 1, 7);
	
	tmp += spread(total_num, current_x - 2, current_y - 1, 2);
	tmp += spread(total_num, current_x + 2, current_y - 1, 2);
	
	tmp += spread(total_num, current_x - 1, current_y - 2, 10);
	tmp += spread(total_num, current_x + 1, current_y - 2, 10);
	
	tmp += spread(total_num, current_x, current_y - 3, 5);

	spread(total_num - tmp, current_x, current_y - 2, 100);

	current_y--; //열 왼쪽으로 1칸 이동
}

void right() {
	int total_num = arr[current_x][current_y + 1];
	arr[current_x][current_y + 1] = 0;

	int tmp = 0;
	tmp += spread(total_num, current_x - 1, current_y, 1);
	tmp +=  spread(total_num, current_x + 1, current_y, 1);

	tmp += spread(total_num, current_x - 1, current_y + 1, 7);
	tmp += spread(total_num, current_x + 1, current_y + 1, 7);

	tmp += spread(total_num, current_x - 2, current_y + 1, 2);
	tmp += spread(total_num, current_x + 2, current_y + 1, 2);

	tmp += spread(total_num, current_x - 1, current_y + 2, 10);
	tmp += spread(total_num, current_x + 1, current_y + 2, 10);

	tmp += spread(total_num, current_x, current_y + 3, 5);

	spread(total_num - tmp, current_x, current_y + 2, 100);

	current_y++; //열 오른쪽으로 1칸 이동
}

void down() {

	int total_num = arr[current_x + 1][current_y];
	arr[current_x + 1][current_y] = 0;

	int tmp = 0;
	tmp += spread(total_num, current_x, current_y - 1, 1);
	tmp += spread(total_num, current_x, current_y + 1, 1);

	tmp += spread(total_num, current_x + 1, current_y - 1, 7);
	tmp += spread(total_num, current_x + 1, current_y + 1, 7);

	tmp += spread(total_num, current_x + 1, current_y + 2, 2);
	tmp += spread(total_num, current_x + 1, current_y - 2, 2);

	tmp += spread(total_num, current_x + 2, current_y + 1, 10);
	tmp += spread(total_num, current_x + 2, current_y - 1, 10);

	tmp += spread(total_num, current_x + 3, current_y, 5);

	spread(total_num - tmp, current_x + 2, current_y, 100);

	current_x++; //행 아래쪽으로 1칸 이동
}

void up() {

	int total_num = arr[current_x - 1][current_y];
	arr[current_x - 1][current_y] = 0;

	int tmp = 0;
	tmp += spread(total_num, current_x, current_y - 1, 1);
	tmp += spread(total_num, current_x, current_y + 1, 1);

	tmp += spread(total_num, current_x - 1, current_y - 1, 7);
	tmp += spread(total_num, current_x - 1, current_y + 1, 7);

	tmp += spread(total_num, current_x - 1, current_y + 2, 2);
	tmp += spread(total_num, current_x - 1, current_y - 2, 2);

	tmp += spread(total_num, current_x - 2, current_y + 1, 10);
	tmp += spread(total_num, current_x - 2, current_y - 1, 10);

	tmp += spread(total_num, current_x - 3, current_y, 5);

	spread(total_num - tmp, current_x - 2, current_y, 100);

	current_x--; //행 위쪽으로 1칸 이동
}
void input() {
	
	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
			original_amount += arr[i][j];
		}
	}

	current_x = n / 2;
	current_y = n / 2;
}

void solution() {
	for (int i = 1; i <= n - 1; i += 2) {
		//i = 7
		for (int j = 1; j <= i; j++) {
			left();
		}
		for (int j = 1; j <= i; j++) {
			down();
		}
		for (int j = 1; j <= i + 1; j++) {
			right();
		}
		for (int j = 1; j <= i + 1; j++) {
			up();
		}
	}

	for (int i = 1; i <= n; i++) {
		left();
	}
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();

	current_x = n / 2;
	current_y = n / 2;
	solution();

	long left_amount = 0;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			left_amount += arr[i][j];
		}
	}

	cout << original_amount - left_amount;
}
