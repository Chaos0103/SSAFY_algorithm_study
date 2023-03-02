#include <iostream>
#include <vector>

using namespace std;

int fire_ball_size_num = 0;

struct fireball {
	int mi; //���̾ ����
	int si; //���̾ �ӷ�
	int di; //���̾ ����
};
int n, m, k;

vector<fireball> arr[51][51];
vector<fireball> tmp_arr[51][51];

int dx[8] = { -1,-1,0,1,1,1,0,-1 };
int dy[8] = { 0,1,1,1,0,-1,-1,-1 };

pair<int, int> check_over_n(int new_ri, int new_ci) {
	if (new_ri > n) {
		new_ri %= n;
		if (new_ri == 0) {
			new_ri = n;
		}
	}
	else if (new_ri == 0) {
		new_ri = n;
	}
	else if (new_ri < 0) {
		new_ri = n - abs(new_ri) % n;
	}

	if (new_ci > n) {
		new_ci %= n;
		if (new_ci == 0) {
			new_ci = n;
		}
	}
	else if (new_ci == 0) {
		new_ci = n;
	}
	else if (new_ci < 0) {
		new_ci = n - abs(new_ci) % n;
	}

	return { new_ri, new_ci };
}

void real_move_fireball(int ri, int ci) {
	//��ġ (ri, ci)�� ���̾�� si �ӷ����� di��ŭ �̵���Ű��
	vector<fireball> tmp_fireball = arr[ri][ci];

	for (int i = 0; i < tmp_fireball.size(); i++) {
		int mi = tmp_fireball[i].mi;
		int si = tmp_fireball[i].si;
		int di = tmp_fireball[i].di;

		int new_ri = ri + si * dx[di];
		int new_ci = ci + si * dy[di];

		pair<int, int> p = check_over_n(new_ri, new_ci);
		tmp_arr[p.first][p.second].push_back({ mi, si, di });
	}
	
}
void move_fireballs() {
	//���̾ �̵���Ű��
	
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			tmp_arr[i][j].clear();
		}
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (arr[i][j].size() > 0) {
				//���� ���̾�� �����Ѵٸ�
				real_move_fireball(i, j);
				arr[i][j].clear(); //���� �ִ� ���̾ ����
				//(i,j) ��ġ�� �ִ� fireball �̵���Ű��
			}
		}
	}
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (tmp_arr[i][j].size() > 0) {
				arr[i][j].insert(arr[i][j].end(), tmp_arr[i][j].begin(), tmp_arr[i][j].end());
			}
		}
	}
}

void over_two_fireball(int row, int col) {

	int total_weight = 0;
	int total_speed = 0;
	bool all_odd = true;
	bool all_even = true;

	vector<fireball> v = arr[row][col]; //Ư�� �࿡ �ִ� ���̾ ����
	arr[row][col].clear();

	for (int i = 0; i < v.size(); i++) {
		total_weight += v[i].mi; //���� ���ϱ�
		total_speed += v[i].si; //�ӵ� ���ϱ�

		if (v[i].di % 2 == 0) {
			//���̾ ������ ¦�����
			all_odd = false; //��� Ȧ���ΰ� false
		}
		else if (v[i].di % 2 != 0) {
			//���̾ ������ Ȧ�����
			all_even = false; //��� ¦���ΰ� false 
		}
	}

	int weight = total_weight / 5; //������ ������ / 5
	int speed = total_speed / v.size(); //�ӷ��� �� / ���̾ ����

	if (weight == 0) { //���� ������ 0�� ���̾�̶�� �Ҹ�
		return; 
	}
	if (all_odd || all_even) { 
		//�������� ���̾ ������ ��� Ȧ���̰ų� ��� ¦���̸�
		for (int i = 0; i < 4; i++) {
			arr[row][col].push_back({ weight, speed, i * 2 });
		}
	}
	else {
		for (int i = 0; i < 4; i++) {
			arr[row][col].push_back({ weight, speed, i * 2 + 1 });
		}
	}
}

void after_move() {
	//�̵��� ��� ������

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			//���� 2�� �̻��� ���̾�� �ִ� ĭ�̶��
			if (arr[i][j].size() >= 2) {
				over_two_fireball(i,j);
			}
		}
	}
}

int get_weight_sum() {
	//�����ִ� ���̾ ���� �� ���ϱ�

	int weight = 0;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (arr[i][j].size() > 0) {
				for (int k = 0; k < arr[i][j].size(); k++) {
					weight += arr[i][j][k].mi;
				}
			}
		}
	}

	return weight;
}
void solution() {

	for (int i = 0; i < k; i++) {
		move_fireballs();
		after_move();
	}
	
	cout << get_weight_sum();
}
void input() {
	cin >> n >> m >> k;

	for (int i = 0; i < m; i++) {
		int ri; //���̾ �� ��ġ
		int ci; //���̾ �� ��ġ
		int mi; //����
		int si; //�ӷ�
		int di; //����
		cin >> ri >> ci >> mi >> si >> di;
		arr[ri][ci].push_back({ mi,si,di });
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}