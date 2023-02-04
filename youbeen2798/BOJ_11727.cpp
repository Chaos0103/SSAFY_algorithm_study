#include <iostream>

using namespace std;

int n;
int dp[1001];

int solution(int n) {

	if (n == 1) {
		return dp[1];
	}
	else if (n == 2) {
		return dp[2];
	}
	if (dp[n]) {
		return dp[n];
	}
	else {
		return dp[n] = (solution(n - 1) + 2 * solution(n - 2)) % 10007;
	}
}

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	cin >> n;

	dp[1] = 1;
	dp[2] = 3;

	cout << solution(n);
}