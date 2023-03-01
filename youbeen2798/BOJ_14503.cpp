#include <iostream>
#include <cstring>
using namespace std;

int n, m, r, c, d;
int arr[51][51];
bool visited[51][51] = { false, };
int answer = 0;

int dx[4] = { -1, 0, 1,  0 }; // �� �� �� ��
int dy[4] = { 0, 1, 0,  -1 }; // �� �� �� ��

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

	for (int i = 0; i < 4; i++) { //�������� ȸ���ϸ鼭 �� ���̸鼭 �湮���� ���� ĭ ã��

		int next_d = (d + 3 - i) % 4;
		int next_r = r + dx[next_d]; //��
		int next_c = c + dy[next_d]; //��

		if (next_r < 0 || next_r >= n || next_c < 0 || next_c >= m || arr[next_r][next_c] == 1) {
			continue;
		}

		if (arr[next_r][next_c] == 0 && visited[next_r][next_c] == false) { //û���� ������ ã����

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

	//�� ĭ ��� û�Ұ� �̹� �Ǿ��ְų� ���� ���

	int back_idx;

	if (d <= 1) {
		back_idx = d + 2;
	}
	else {
		back_idx = d - 2;
	}

	int back_r = r + dx[back_idx]; //���� �� ( ���� ) 
	int back_c = c + dy[back_idx]; //���� �� ( ���� )

	if (back_r >= 0 && back_r < n && back_c >= 0 && back_c < m) {

		if (arr[back_r][back_c] == 0) {
			//			cout << "r: " << r << "c: " << c << "\n";
			r = back_r; //���� ��
			c = back_c; //���� ��
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