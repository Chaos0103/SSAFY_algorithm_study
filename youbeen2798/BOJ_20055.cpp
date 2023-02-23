#include <iostream>
#include <deque>

using namespace std;

int n, k;
int arr[201];
int ans = 0;

deque <pair<int, bool>> dq;

void one_move() {
	//��Ʈ�� �� ĭ ���� �ִ� �κ��� �Բ� �� ĭ ȸ���Ѵ�.
	pair<int, bool> tmp = dq.back();
	dq.pop_back();
	dq.push_front(tmp); //������ ��ĭ �̵�

	if (dq[n - 1].second) { // N�� ĭ�� �κ��� �ִٸ�
		dq[n - 1].second = false; //�κ��� ��� ������
	}
}

void robot_move() {

	pair<int, bool> before = dq[n - 2];
	pair<int, bool> after = dq[n - 1];

	//�κ��� ������ �������� 1�̻� ���� ������
	if (after.first >= 1 && !after.second && before.second) {
		dq[n - 2].second = false; //�κ� �ű�� ����
		dq[n - 1].first -= 1; //ĭ�� ������ 1 ����
	}
	for (int i = n - 2; i >= 0; i--) {
		pair<int, bool> now = dq[i];
		pair<int, bool> next = dq[i + 1]; //���� ��ġ ����
		if (next.first >= 1 && now.second && !next.second) {
			// ���� ��ġ�� �κ��� �ְ�
			// ���� ��ġ�� �������� 1�̻�, �κ��� ���ٸ�
			//�κ� �̵�
			dq[i].second = false;
			dq[i + 1].second = true;
			dq[i + 1].first -= 1; //ĭ�� ������ 1����
		}
	}
}

void put_robot() {
	if (dq[0].first > 0) { //�ø��� ��ġ�� ĭ�� �������� 0�� �ƴϸ�
		dq[0].first -= 1; //������ 1 ����
		dq[0].second = true; //�κ��� �ø���.
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
		one_move(); //�� ĭ ȸ��
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