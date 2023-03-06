#include <iostream>

using namespace std;

int t; //총 테스트 케이스 개수
int tc;
int n; //도시 크기
int m; //하나의 집이 지불할 수 있는 비용
int arr[21][21];
int original_home_num; //집 총 개수

int max_home_num = 0;

void mini_move(int x, int y, int k) {
	//서비스 영역의 중심 : (x, y)
	//서비스 영역의 크기 : k

	int cost = k * k + (k - 1) * (k - 1); //운영 비용
	int home_num = 0; //연결된 집의 개수

	k = k * 2 - 1;

	//k가 3이라고 가정
	int startt = y - k / 2;
	int endd = y + k / 2;

//	cout << "##startt : " << startt << " end : " << endd << "\n";
	for (int j = startt; j <= endd; j++) {
		if (x >= 0 && x < n && 0 <= j && j < n && arr[x][j]) {
//			cout << "###행: " << x << " 열: " << j << "\n";
			home_num += 1;
		}
	}

	for (int i = 1; i <= k / 2; i++) {
	
//		cout << "!i: " << i << "  startt : " << startt << " end : " << endd << "\n";
		startt += 1;
		endd -= 1;
		for (int j = startt; j <= endd; j++) {
			if (x - i >= 0 && x - i < n && 0 <= j && j < n && arr[x - i][j]) {
//				cout << "행: " << x - i << " 열: " << j << "\n";
				home_num += 1;
			}
			if (x + i >= 0 && x + i < n && 0 <= j && j < n && arr[x + i][j]) {
//				cout << "행: " << x + i << " 열: " << j << "\n";
				home_num += 1;
			}
		}
	}

	int tmp_benefit = m * home_num - cost;  //이익

	//손해를 보지 않으면서 가장 많은 집에 서비스를 제공한다면
	if (tmp_benefit >= 0 && home_num > max_home_num) {
//		cout << "x: " << x << " y: " << y << " k: " << k << "\n";
		max_home_num = max(home_num, max_home_num); 
	}
//	max_benefit = max(max_benefit, tmp_benefit); //최대 이익

	/*
	cout << "cost: " << cost << "\n";
	cout << "home_num: " << home_num << "\n";
	cout << "tmp_benefit: " << tmp_benefit << "\n";
	*/
}
void move(int k) {
	//크기가 k인 서비스 영역으로 탐색

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
		//	cout << "###############move: " << "i: " << i << " j: " << j << " k: " << k << "\n";
			mini_move(i, j, k);
		}
	}
}
void solution() {

	//3이면 3 * 2 - 1 = 5 까지 가능
	for (int k = 1; k <= n + 1; k++) {

		if (max_home_num == original_home_num) {
			break; 
		}
		move(k);
	}

//	cout << "ans: " << max_home_num << "\n";
}

void input() {

	cin >> t;

	for (tc = 1; tc <= t; tc++) {

		original_home_num = 0;
		cin >> n >> m;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> arr[i][j];
				if (arr[i][j]) {
					original_home_num += 1;
				}
			}
		}

		max_home_num = 0;
		solution();

		cout << "#" << tc << " " << max_home_num << "\n";
	}
}
int main() {

	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
//	mini_move(9, 9, 41);
}