#include <iostream>

using namespace std;

int t; //�� �׽�Ʈ ���̽� ����
int tc;
int n; //���� ũ��
int m; //�ϳ��� ���� ������ �� �ִ� ���
int arr[21][21];
int original_home_num; //�� �� ����

int max_home_num = 0;

void mini_move(int x, int y, int k) {
	//���� ������ �߽� : (x, y)
	//���� ������ ũ�� : k

	int cost = k * k + (k - 1) * (k - 1); //� ���
	int home_num = 0; //����� ���� ����

	k = k * 2 - 1;

	//k�� 3�̶�� ����
	int startt = y - k / 2;
	int endd = y + k / 2;

//	cout << "##startt : " << startt << " end : " << endd << "\n";
	for (int j = startt; j <= endd; j++) {
		if (x >= 0 && x < n && 0 <= j && j < n && arr[x][j]) {
//			cout << "###��: " << x << " ��: " << j << "\n";
			home_num += 1;
		}
	}

	for (int i = 1; i <= k / 2; i++) {
	
//		cout << "!i: " << i << "  startt : " << startt << " end : " << endd << "\n";
		startt += 1;
		endd -= 1;
		for (int j = startt; j <= endd; j++) {
			if (x - i >= 0 && x - i < n && 0 <= j && j < n && arr[x - i][j]) {
//				cout << "��: " << x - i << " ��: " << j << "\n";
				home_num += 1;
			}
			if (x + i >= 0 && x + i < n && 0 <= j && j < n && arr[x + i][j]) {
//				cout << "��: " << x + i << " ��: " << j << "\n";
				home_num += 1;
			}
		}
	}

	int tmp_benefit = m * home_num - cost;  //����

	//���ظ� ���� �����鼭 ���� ���� ���� ���񽺸� �����Ѵٸ�
	if (tmp_benefit >= 0 && home_num > max_home_num) {
//		cout << "x: " << x << " y: " << y << " k: " << k << "\n";
		max_home_num = max(home_num, max_home_num); 
	}
//	max_benefit = max(max_benefit, tmp_benefit); //�ִ� ����

	/*
	cout << "cost: " << cost << "\n";
	cout << "home_num: " << home_num << "\n";
	cout << "tmp_benefit: " << tmp_benefit << "\n";
	*/
}
void move(int k) {
	//ũ�Ⱑ k�� ���� �������� Ž��

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
		//	cout << "###############move: " << "i: " << i << " j: " << j << " k: " << k << "\n";
			mini_move(i, j, k);
		}
	}
}
void solution() {

	//3�̸� 3 * 2 - 1 = 5 ���� ����
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