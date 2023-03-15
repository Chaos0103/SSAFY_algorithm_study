#include <iostream>

using namespace std;

int n, k;
int coin[101];
int dp[10001];

void input() {
	cin >> n >> k; //n���� ������ ����,

	for (int i = 1; i <= n; i++) {
		cin >> coin[i]; //����
	}
}

void solution() {
	//������ �� ���� ����� �� ������, 
	//�� ��ġ�� ���� k���� �ǵ��� �Ѵ�.

	dp[0] = 1;
	for (int i = 1; i <= n; i++) {
		for (int j = coin[i]; j <= k; j++) {
			dp[j] += dp[j - coin[i]];
		}
	}

	cout << dp[k];

}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	input();
	solution();
}