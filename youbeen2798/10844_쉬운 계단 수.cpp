#include <iostream>
#define N 1000000000
using namespace std;

int n;
int dp[101][10];

void solution() {

	dp[1][0] = 0;
	for (int i = 1; i <= 9; i++) {
		dp[1][i] = 1;
	}

	for (int i = 2; i <= n; i++) {
		for (int j = 1; j <= 8; j++) {
			dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % N;
		}
		dp[i][9] = dp[i - 1][8] % N;
		dp[i][0] = dp[i - 1][1] % N;
	}

	long ans = 0;
	for (int i = 0; i <= 9; i++) {
		ans += dp[n][i];
	}

	cout <<  ans % N;
}
void input() {
	cin >> n;
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0); 
	cout.tie(0);

	input();
	solution();
}