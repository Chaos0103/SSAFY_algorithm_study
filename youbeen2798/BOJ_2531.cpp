#include <iostream>
#include <vector>
#include <set>

using namespace std;

int n; //ȸ�� �ʹ� ��Ʈ�� ���� ������ ��
int d; //�ʹ��� ������
int k; //�����ؼ� �Դ� ������ ��
int c; //������ ��
int real_ans = 0;
int sushi[300001];
int check[3001];

void solution() {

	for (int i = 0; i < n; i++) {
		int coupon = 1; //���� ���� �� �Ծ����� 1
		int flag = 0; //�ʹ��� ��ģ ��
		for (int j = i; j < i + k; j++) {
			if (check[sushi[j % n]] == 1) {
				flag++;
			}
			else {
				check[sushi[j % n]] = 1;
			}
			if (sushi[j % n] == c) {
				//�����̶��
				coupon = 0;
			}
		}

		memset(check, 0, sizeof(check));
		real_ans = max(real_ans, k - flag + coupon);
	}
}

void input() {

	cin >> n >> d >> k >> c;

	for (int i = 0; i < n; i++) {
		int num;
		cin >> num;
		sushi[i] = num;
	}
}

int main() {

	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
	cout << real_ans;
}