#include <iostream>

using namespace std;

int n, k;
int coin[101];
int dp[10001];

void input() {
	cin >> n >> k; //n가지 종류의 동전,

	for (int i = 1; i <= n; i++) {
		cin >> coin[i]; //동전
	}
}

void solution() {
	//동전은 몇 개라도 사용할 수 있으며, 
	//그 가치의 합이 k원이 되도록 한다.

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