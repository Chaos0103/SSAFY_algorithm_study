#include <iostream>
#define MOD 1000000009
using namespace std;

int T;

long dp[1000001];

void solution() {

	for (int i = 4; i <= 1000000; i++) {
		dp[i] = (dp[i - 3] + dp[i - 2] + dp[i - 1]) % MOD;
	}

}
void input() {
	
	cin >> T;

	for (int i = 0; i < T; i++) {
		int n;
		cin >> n;
		cout << dp[n] << "\n";
	}
}
int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	dp[1] = 1;
	dp[2] = 2;
	dp[3] = 4;
	solution();

	input();
}